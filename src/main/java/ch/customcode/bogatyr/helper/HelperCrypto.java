/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.helper;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for cryptography (e.g. random keys).
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.7.0
 */
public abstract class HelperCrypto {
	private static final Logger log = LoggerFactory.getLogger(HelperCrypto.class);
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
    	log.debug(HelperLog.methodStart(digits, seed));
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
        
        final String result = sb.toString();
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(digits));
        
    	final String result = getRandomKey(digits, DEFAULT_RANDOMKEY_SEED);
        
        log.debug(HelperLog.methodExit(result));
        return result;
    }

    /**
     * Generates an universally unique identifier ({@link UUID}).
     *
     * @return generated {@link UUID}
     * @see UUID
     * @since 0.7.0
     */
    public static UUID getUUID() { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
    	final UUID result = UUID.randomUUID();
        
        log.debug(HelperLog.methodExit(result));
        return result;
    }  
    
    /**
     * Returns all installed security {@link Provider}.
     *
     * @return installed security {@link Provider}
     * @see Provider
     * @since 0.9.1
     */
    public static Collection<Provider> getProviders() {
    	log.debug(HelperLog.methodStart());
    	
    	final Collection<Provider> result = Arrays.asList(Security.getProviders());
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "Cipher."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "KeyAgreement."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "Mac."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "MessageDigest."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "Signature."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
		log.debug(HelperLog.methodStart(provider));
		
		final Collection<String> result = getInformation(provider, "KeyPairGenerator."); //$NON-NLS-1$
		
		log.debug(HelperLog.methodExit(result));
		return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "KeyFactory."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(provider));
    	
    	final Collection<String> result = getInformation(provider, "KeyGenerator."); //$NON-NLS-1$
        
        log.debug(HelperLog.methodExit(result));
        return result;
    }

    
    /*
     * Private methods
     */
    private static Collection<String> getInformation(final Provider provider, final String id) {
    	log.trace(HelperLog.methodStart(provider, id));
    	if (null == provider) {
			throw new RuntimeExceptionIsNull("provider"); //$NON-NLS-1$
		}
    	if (null == id) {
			throw new RuntimeExceptionIsNull("id"); //$NON-NLS-1$
		}

		final Set<String> result = new HashSet<String>();

        for (final Map.Entry<?, ?> pair : provider.entrySet()) {
        	final String entry = (String) pair.getKey();
        	
        	if (HelperString.startsWith(entry, id) && !HelperString.isNumeric(entry.substring(id.length(), id.length() + 1))) {
        		result.add(entry.substring(id.length()));
            }
        }
        log.trace(HelperLog.methodExit(result));
    	return result;
    }
}