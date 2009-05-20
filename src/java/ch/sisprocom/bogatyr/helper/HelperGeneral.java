/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * This is a general helper class for general problems.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090520
 */
public abstract class HelperGeneral {
	private static final String HASHCODE_ALGORITHM_SHA256 = "SHA-256"; //$NON-NLS-1$
	private static final char[] RANDOMKEY_SEED_DEFAULT    = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
   
    /**
     * Generates a hash (unique {@link String}) from an input {@link Object}.
     * This is also used for unique keys.
     *
     * @param algo to use
     * @param data to generate a hash
     * @return generated hash value
     * @throws NoSuchAlgorithmException
     */
    public static String getHashCode(final String algo, final Object data) throws NoSuchAlgorithmException { //$JUnit
		if (!HelperString.isValid(algo)) {
			throw new IllegalArgumentException("algo is null or empty!"); //$NON-NLS-1$
		}
		if (null == data) {
			throw new IllegalArgumentException("data is null!"); //$NON-NLS-1$
		}

    	final MessageDigest algorithm = MessageDigest.getInstance(algo);
		final byte[] input = HelperObject.toString(data).getBytes();

		algorithm.reset();
		algorithm.update(input);
		
		final byte[] messageDigest = algorithm.digest();
        final StringBuilder hexString = new StringBuilder();

		for (final byte digest : messageDigest) {
			final String hex = Integer.toHexString(0xFF & (int) digest);
			if (1 == hex.length()) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

    /**
     * Generates a hash (unique {@link String}) with SHA-256 from an input object.
     *
     * @param data to generate a hash
     * @return generated hash value
     * @throws NoSuchAlgorithmException
     */
    public static String getHashCode(final Object data) throws NoSuchAlgorithmException { //$JUnit
    	return getHashCode(HASHCODE_ALGORITHM_SHA256, data);
    }

    /**
     * Generates an unique {@link String} with a given seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @param seed for the string (e.g. "1,2...0,A,B...Z)
     * @return generated unique string
     */
    public static String getRandomKey(final int digits, final char[] seed) { //$JUnit
		if (0 >= digits) {
			throw new IllegalArgumentException("digits must be greater than 0: " + digits); //$NON-NLS-1$
		}
		if (null == seed || 0 == seed.length) {
			throw new IllegalArgumentException("seed is null or empty!"); //$NON-NLS-1$
		}

    	final StringBuilder sb = new StringBuilder();

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
     */
    public static String getRandomKey(final int digits) { //$JUnit
    	return getRandomKey(digits, RANDOMKEY_SEED_DEFAULT);
    }

    /**
     * Generates an universally unique identifier (UUID).
     *
     * @return generated universally unique identifier
     */
    public static UUID getUUID() { //$JUnit
    	return UUID.randomUUID();
    }    
}