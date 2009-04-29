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
package ch.sisprocom.bogatyr.helper.converter;


/**
 * Encodes and decodes data to Base64 format.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public abstract class ConverterBase64 {
    private static final String ERROR_ILLEGAL_CHARACTER = "Illegal character in Base64 encoded data";

	private static final char[] map1 = new char[64];
	private static final byte[] map2 = new byte[128];

	// Mapping table from 6-bit nibbles to Base64 characters.
	static {
		int ii = 0;
		for (char c = 'A'; 'Z' >= c; c++) {
			map1[ii] = c;
            ii++;
        }
		for (char c = 'a'; 'z' >= c; c++) {
			map1[ii] = c;
            ii++;
        }
		for (char c = '0'; '9' >= c; c++) {
			map1[ii] = c;
            ii++;
        }
		map1[ii] = '+';
        ii++;
        map1[ii] = '/';
	}

	// Mapping table from Base64 characters to 6-bit nibbles.
	static {
		for (int ii = 0; ii < map2.length; ii++) {
			map2[ii] = (byte) -1;
		}
		for (int ii = 0; 64 > ii; ii++) {
			map2[map1[ii]] = (byte) ii;
		}
	}

	/**
	 * Encodes a string into Base64 format. 
	 * No blanks or line breaks are inserted.
	 * 
	 * @param data String to be encoded
	 * @return String with the Base64 encoded data
	 */
	public static String encode(final String data) {
		return new String(encode(data.getBytes()));
	}

	/**
	 * Encodes a byte-array into Base64 format. 
	 * No blanks or line breaks are inserted.
	 * 
	 * @param data Array containing the data bytes to be encoded
	 * @return Character array with the Base64 encoded data
	 */
	public static char[] encode(final byte[] data) {
		return encode(data, data.length);
	}

//	/**
//	 * Decodes a string from Base64 format.
//	 * 
//	 * @param string Base64 String to be decoded.
//	 * @return String containing the decoded data.
//	 * @throws IllegalArgumentException
//	 */
//	public static String decodeToString(final String string) {
//		Logger.getInstance().writeMethodEntry(ConverterBase64.class, "decodeToString", string);  //$NON-NLS-1$
//
//		final String str = new String(decode(string));
//		
//		Logger.getInstance().writeMethodExit(ConverterBase64.class, "decodeToString", str);  //$NON-NLS-1$
//		return str;
//	}

	/**
	 * Decodes a byte-array from Base64 format.
	 * 
	 * @param data Base64 String to be decoded
	 * @return Array containing the decoded data bytes
	 * @throws IllegalArgumentException
	 */
	public static byte[] decode(final String data) {
		return decode(data.toCharArray());
	}

	/**
	 * Decodes a byte array from Base64 format. 
	 * No blanks or line breaks are allowed within the Base64 encoded data.
	 * 
	 * @param data Character array containing the Base64 encoded data
	 * @return Array containing the decoded data bytes
	 * @throws IllegalArgumentException
	 */
	public static byte[] decode(final char[] data) {
		int iLen = data.length;

		if (0 != iLen % 4) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4");
        }
		
		while (0 < iLen && '=' == data[iLen - 1]) {
			iLen--;
		}
		
		final int oLen = iLen * 3 / 4;
		final byte[] out = new byte[oLen];

        int ip = 0;
        int op = 0;
        while (ip < iLen) {
			final int i0 = data[ip];
            ip++;
            final int i1 = data[ip];
            ip++;
            final int i2 = (int) (ip < iLen ? data[ip] : 'A');
            ip++;
            final int i3 = (int) (ip < iLen ? data[ip] : 'A');
            ip++;

            if (127 < i0 || 127 < i1 || 127 < i2 || 127 < i3) {
                throw new IllegalArgumentException(ERROR_ILLEGAL_CHARACTER);
            }
			final int b0 = map2[i0];
			final int b1 = map2[i1];
			final int b2 = map2[i2];
			final int b3 = map2[i3];

			if (0 > b0 || 0 > b1 || 0 > b2 || 0 > b3) {
                throw new IllegalArgumentException(ERROR_ILLEGAL_CHARACTER);
            }

			final int o0 = b0 << 2 | b1 >>> 4;
			final int o1 = (b1 & 0xf) << 4 | b2 >>> 2;
			final int o2 = (b2 & 3) << 6 | b3;
			out[op] = (byte) o0;
            op++;
            if (op < oLen) {
                out[op] = (byte) o1;
                op++;
            }
			if (op < oLen) {
                out[op] = (byte) o2;
                op++;
            }
		}
		return out;
	}
	
	
	/*
	 * Private methods
	 */
	/**
	 * Encodes a byte-array into Base64 format. 
	 * No blanks or line breaks are inserted.
	 * 
	 * @param in Array containing the data bytes to be encoded
	 * @param iLen Number of bytes to process in <code>in</code>
	 * @return Character array with the Base64 encoded data
	 */
	private static char[] encode(final byte[] in, final int iLen) {
		final int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
		final int oLen = (iLen + 2) / 3 * 4; // output length including padding
		final char[] out = new char[oLen];
		int ip = 0;
		int op = 0;

		while (ip < iLen) {
			final int i0 = in[ip] & 0xff;
            ip++;
            final int i1 = ip < iLen ? in[ip] & 0xff : 0;
            ip++;
            final int i2 = ip < iLen ? in[ip] & 0xff : 0;
            ip++;
            final int o0 = i0 >>> 2;
			final int o1 = (i0 & 3) << 4 | i1 >>> 4;
			final int o2 = (i1 & 0xf) << 2 | i2 >>> 6;
			final int o3 = i2 & 0x3F;
			out[op] = map1[o0];
            op++;
            out[op] = map1[o1];
            op++;
            out[op] = op < oDataLen ? map1[o2] : '=';
			op++;
			out[op] = op < oDataLen ? map1[o3] : '=';
			op++;
		}
		return out;
	}
}
