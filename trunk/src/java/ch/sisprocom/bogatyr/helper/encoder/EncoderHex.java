/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.encoder;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperString;


/**
 * Encodes and decodes data to Hex format.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091110)
 * @since 0.1.0
 */
public abstract class EncoderHex {

	/**
     * Encodes a byte-array to a hex {@link String}.
     * 
     * @param input bytes to be converted
     * @return hex representation of a byte array
     * @since 0.1.0
     */
    public static String encode(final byte[] input) { //$JUnit$
		if (!HelperArray.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("the doubled input (" + input.length * 2 + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
		
		final StringBuilder hexString = new StringBuilder(input.length * 2);
		
		for (final byte digest : input) {
			final String hex = Integer.toHexString(0xFF & (int) digest);
			if (1 == hex.length()) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
    }
    
    /**
     * Decodes a hex {@link String} to a byte-array.
     * 
     * @param input hex string to be converted
     * @return byte-array representation of a hex string
     * @since 0.1.0
     */
    public static byte[] decode(final String input) { //$JUnit$
		if (!HelperString.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
        if (input.length() * 2 > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("the doubled input (" + input.length() * 2 + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

		final byte[] bts = new byte[input.length() / 2];

    	for (int ii = 0; ii < bts.length; ii++) {
    		bts[ii] = (byte) Integer.parseInt(input.substring(2 * ii, 2 * ii + 2), HelperNumber.INT_16);
    	}
    	return bts;
    }
}
