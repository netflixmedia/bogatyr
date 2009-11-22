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

import java.util.UUID;


/**
 * This is a helper class for cryptography (e.g. random keys).
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091122)
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
			throw new IllegalArgumentException("digits must be greater than 0: " + digits); //$NON-NLS-1$
		}
		if (null == seed || 0 == seed.length) {
			throw new IllegalArgumentException("seed is null or empty!"); //$NON-NLS-1$
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
}