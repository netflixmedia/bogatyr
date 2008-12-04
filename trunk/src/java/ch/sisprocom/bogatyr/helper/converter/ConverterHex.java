/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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



/**
 * Encodes and decodes data to Hex format.
 * 
 * @author Stefan Laubenberger
 * @version 20081204
 */
public abstract class ConverterHex {
    private static final CharSequence DIGITS = "0123456789abcdef"; //$NON-NLS-1$
    

    /**
     * Encodes a byte array to a hex string.
     * 
     * @param data the bytes to be converted
     * @return hex representation of a byte array
     */
    public static String encode(final byte[] data){
		final StringBuffer buf = new StringBuffer();

        for (int ii = 0; ii != data.length; ii++) {

            int	v = data[ii] & 0xff;

            buf.append(DIGITS.charAt(v >> 4));
            buf.append(DIGITS.charAt(v & 0xf));
        }
        return buf.toString();
    }
    
    /**
     * Decodes a hex string to a byte-array.
     * 
     * @param data hex string to be converted
     * @return byte-array representation of a hex string
     */
    public static byte[] decode(final String data) {
		final byte[] bts = new byte[data.length() / 2];

    	for (int ii = 0; ii < bts.length; ii++) {
    		bts[ii] = (byte) Integer.parseInt(data.substring(2 * ii, 2 * ii + 2), 16);
    	}
    	return bts;
    }
}
