/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.converter;

import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * Encodes data to Hex and decodes it.
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public abstract class ConverterHex {
    private static final String	DIGITS = "0123456789abcdef"; //$NON-NLS-1$
    

    /**
     * Encodes a byte array to a hex string.
     * 
     * @param data the bytes to be converted
     * @return hex representation of a byte array
     */
    public static String encode(byte[] data){
		Logger.getInstance().writeMethodEntry(ConverterHex.class, "encode", data);  //$NON-NLS-1$

		final StringBuffer buf = new StringBuffer();

        for (int ii = 0; ii != data.length; ii++) {

            int	v = data[ii] & 0xff;

            buf.append(DIGITS.charAt(v >> 4));
            buf.append(DIGITS.charAt(v & 0xf));
        }
        
        final String str = buf.toString();
        
		Logger.getInstance().writeMethodExit(ConverterHex.class, "encode", str);  //$NON-NLS-1$
        return str;
    }
    
    /**
     * Decodes a hex string to a byte array.
     * 
     * @param data hex string to be converted
     * @return byte array representation of a hex string
     */
    public static byte[] decode(String data) {
		Logger.getInstance().writeMethodEntry(ConverterHex.class, "decode", data);  //$NON-NLS-1$

		final byte[] bts = new byte[data.length() / 2];

    	for (int ii = 0; ii < bts.length; ii++) {
    		bts[ii] = (byte) Integer.parseInt(data.substring(2 * ii, 2 * ii + 2), 16);
    	}

    	Logger.getInstance().writeMethodExit(ConverterHex.class, "decode", bts);  //$NON-NLS-1$
    	return bts;
    }
}
