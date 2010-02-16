/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details:
 * ----------------------------------------------------
 * <http://www.gnu.org/licenses>
 * 
 * This distribution is available at:
 * ----------------------------------
 * <http://code.google.com/p/bogatyr/>
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for cryptography (e.g. random keys).
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.7.0
 */
public abstract class HelperCrypto {
	private static final char[] DEFAULT_RANDOMKEY_SEED    = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
   
    /**
     * Generates an unique {@link String} with a given seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @param seed for the string (e.g. "1,2...0,A,B...Z)
     * @return generated unique string
     * @since 0.7.0
     */
    public static String getRandomKey(final int digits, final char... seed) { //$JUnit$
		if (0 >= digits) {
			throw new RuntimeExceptionMustBeGreater("digits", digits, 0); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(seed)) {
			throw new RuntimeExceptionIsNullOrEmpty("seed"); //$NON-NLS-1$
		}

    	final StringBuilder sb = new StringBuilder(digits);

        for (int ii = 0; ii < digits; ii++) {
            sb.append(seed[HelperMath.getRandom(seed.length)]);
        }
        return sb.toString();
    }

    /**
     * Generates an unique {@link String} with default seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @return generated unique String
     * @since 0.7.0
     */
    public static String getRandomKey(final int digits) { //$JUnit$
    	return getRandomKey(digits, DEFAULT_RANDOMKEY_SEED);
    }

    /**
     * Generates an universally unique identifier ({@link UUID}).
     *
     * @return generated {@link UUID}
     * @see UUID
     * @since 0.7.0
     */
    public static UUID getUUID() { //$JUnit$
    	return UUID.randomUUID();
    }  
    
    /**
     * Returns all installed security {@link Provider}.
     *
     * @return installed security {@link Provider}
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<Provider> getProviders() {
    	return Arrays.asList(Security.getProviders());
    }
    
    /**
     * Returns a {@link Collection} of all available ciphers for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available ciphers
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<String> getCiphers(final Provider provider) {
    	return getInformation(provider, "Cipher."); //$NON-NLS-1$
    }
    
    /**
     * Returns a {@link Collection} of all available key agreements for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available key agreements
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<String> getKeyAgreements(final Provider provider) {
        return getInformation(provider, "KeyAgreement."); //$NON-NLS-1$
    }
    
    /**
     * Returns a {@link Collection} of all available macs for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available macs
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<String> getMacs(final Provider provider) {
        return getInformation(provider, "Mac."); //$NON-NLS-1$
    }
    
    /**
     * Returns a {@link Collection} of all available message digests for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available message digests
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<String> getMessageDigests(final Provider provider) {
        return getInformation(provider, "MessageDigest."); //$NON-NLS-1$
    }
    
    /**
     * Returns a {@link Collection} of all available signatures for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available signatures
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<String> getSignatures(final Provider provider) {
        return getInformation(provider, "Signature."); //$NON-NLS-1$
    }
    
    /**
     * Returns a {@link Collection} of all available key pair generators for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available key pair generators
     * @see Provider
     * @since 0.9.1
     */
   public static Collection<String> getKeyPairGenerators(final Provider provider) {
        return getInformation(provider, "KeyPairGenerator."); //$NON-NLS-1$
    }
    
   /**
    * Returns a {@link Collection} of all available key factories for a given security {@link Provider}.
    *
    * @param provider for the data
    * @return {@link Collection} containing all available key factories
    * @see Provider
    * @since 0.9.1
    */
    public static Collection<String> getKeyFactories(final Provider provider) {
        return getInformation(provider, "KeyFactory."); //$NON-NLS-1$
    }
    
    /**
     * Returns a {@link Collection} of all available key generators for a given security {@link Provider}.
     *
     * @param provider for the data
     * @return {@link Collection} containing all available key generators
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<String> getKeyGenerators(final Provider provider) {
        return getInformation(provider, "KeyGenerator."); //$NON-NLS-1$
    }

    
    /*
     * Private methods
     */
    private static Collection<String> getInformation(final Provider provider, final String id) {
		if (null == provider) {
			throw new RuntimeExceptionIsNull("provider"); //$NON-NLS-1$
		}

		final Set<String> set = new HashSet<String>();

        for (final Map.Entry<?, ?> pair : provider.entrySet()) {
        	final String entry = (String) pair.getKey();
        	
        	if (HelperString.startsWith(entry, id) && !HelperString.isNumeric(entry.substring(id.length(), id.length() + 1))) {
        		set.add(entry.substring(id.length()));
            }
        }
    	return set;
    }
}