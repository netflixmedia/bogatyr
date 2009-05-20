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
package ch.sisprocom.bogatyr.helper.converter;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperString;


/**
 * Encodes and decodes data to Hex format.
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public abstract class ConverterHex {
    private static final CharSequence DIGITS = "0123456789abcdef"; //$NON-NLS-1$

    /**
     * Encodes a byte-array to a hex {@link String}.
     * 
     * @param input bytes to be converted
     * @return hex representation of a byte array
     */
    public static String encode(final byte[] input) { //$JUnit
		if (!HelperArray.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
		
		final StringBuilder buf = new StringBuilder();

        for (int ii = 0; ii != input.length; ii++) {

            final int	v = input[ii] & 0xff;

            buf.append(DIGITS.charAt(v >> 4));
            buf.append(DIGITS.charAt(v & 0xf));
        }
        return buf.toString();
    }
    
    /**
     * Decodes a hex {@link String} to a byte-array.
     * 
     * @param input hex string to be converted
     * @return byte-array representation of a hex string
     */
    public static byte[] decode(final String input) { //$JUnit
		if (!HelperString.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
		final byte[] bts = new byte[input.length() / 2];

    	for (int ii = 0; ii < bts.length; ii++) {
    		bts[ii] = (byte) Integer.parseInt(input.substring(2 * ii, 2 * ii + 2), HelperNumber.VALUE_16);
    	}
    	return bts;
    }
}
