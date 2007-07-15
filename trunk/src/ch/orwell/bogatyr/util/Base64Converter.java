package ch.orwell.bogatyr.util;

import ch.orwell.bogatyr.Context;

/**
 * Base64Converter
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070715
 */
public abstract class Base64Converter {
	// Resources
	private final static String	RES_ILLEGAL_CHARACTER = "Base64Converter.illegal"; //$NON-NLS-1$
	private final static String	RES_INVALID_STRING 	  = "Base64Converter.invalid"; //$NON-NLS-1$

	private static char[] map1 = new char[64];
	private static byte[] map2 = new byte[128];

	// Mapping table from 6-bit nibbles to Base64 characters.
	static {
		int ii = 0;
		for (char c = 'A'; c <= 'Z'; c++) {
			map1[ii++] = c;
		}
		for (char c = 'a'; c <= 'z'; c++) {
			map1[ii++] = c;
		}
		for (char c = '0'; c <= '9'; c++) {
			map1[ii++] = c;
		}
		map1[ii++] = '+';
		map1[ii++] = '/';
	}

	// Mapping table from Base64 characters to 6-bit nibbles.
	static {
		for (int ii = 0; ii < map2.length; ii++) {
			map2[ii] = -1;
		}
		for (int ii = 0; ii < 64; ii++) {
			map2[map1[ii]] = (byte) ii;
		}
	}

	/**
	 * Encodes a string into Base64 format. 
	 * No blanks or line breaks are inserted.
	 * 
	 * @param s String to be encoded.
	 * @return String with the Base64 encoded data.
	 */
	public static String encodeString(String s) {
		return new String(encode(s.getBytes()));
	}

	/**
	 * Encodes a byte array into Base64 format. 
	 * No blanks or line breaks are inserted.
	 * 
	 * @param in Array containing the data bytes to be encoded.
	 * @return Character array with the Base64 encoded data.
	 */
	public static char[] encode(byte[] in) {
		return encode(in, in.length);
	}

	/**
	 * Encodes a byte array into Base64 format. 
	 * No blanks or line breaks are inserted.
	 * 
	 * @param in Array containing the data bytes to be encoded.
	 * @param iLen Number of bytes to process in <code>in</code>.
	 * @return Character array with the Base64 encoded data.
	 */
	public static char[] encode(byte[] in, int iLen) {
		int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
		int oLen = ((iLen + 2) / 3) * 4; // output length including padding
		char[] out = new char[oLen];
		int ip = 0;
		int op = 0;

		while (ip < iLen) {
			int i0 = in[ip++] & 0xff;
			int i1 = ip < iLen ? in[ip++] & 0xff : 0;
			int i2 = ip < iLen ? in[ip++] & 0xff : 0;
			int o0 = i0 >>> 2;
			int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
			int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
			int o3 = i2 & 0x3F;
			out[op++] = map1[o0];
			out[op++] = map1[o1];
			out[op] = op < oDataLen ? map1[o2] : '=';
			op++;
			out[op] = op < oDataLen ? map1[o3] : '=';
			op++;
		}
		return out;
	}

	/**
	 * Decodes a string from Base64 format.
	 * 
	 * @param s Base64 String to be decoded.
	 * @return String containing the decoded data.
	 * @throws IllegalArgumentException
	 */
	public static String decodeString(String s) {
		return new String(decode(s));
	}

	/**
	 * Decodes a byte array from Base64 format.
	 * 
	 * @param s Base64 String to be decoded.
	 * @return Array containing the decoded data bytes.
	 * @throws IllegalArgumentException
	 */
	public static byte[] decode(String s) {
		return decode(s.toCharArray());
	}

	/**
	 * Decodes a byte array from Base64 format. 
	 * No blanks or line breaks are allowed within the Base64 encoded data.
	 * 
	 * @param in Character array containing the Base64 encoded data.
	 * @return Array containing the decoded data bytes.
	 * @throws IllegalArgumentException
	 */
	public static byte[] decode(char[] in) {
		int iLen = in.length;
		int ip = 0;
		int op = 0;

		if (iLen % 4 != 0) throw new IllegalArgumentException(Context.getInstance().getLocalizer().getValue(RES_INVALID_STRING));
		
		while (iLen > 0 && in[iLen - 1] == '=') {
			iLen--;
		}
		
		int oLen = (iLen * 3) / 4;
		byte[] out = new byte[oLen];

		while (ip < iLen) {
			int i0 = in[ip++];
			int i1 = in[ip++];
			int i2 = ip < iLen ? in[ip++] : 'A';
			int i3 = ip < iLen ? in[ip++] : 'A';

			if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127) throw new IllegalArgumentException(Context.getInstance().getLocalizer().getValue(RES_ILLEGAL_CHARACTER));
			int b0 = map2[i0];
			int b1 = map2[i1];
			int b2 = map2[i2];
			int b3 = map2[i3];

			if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) throw new IllegalArgumentException(Context.getInstance().getLocalizer().getValue(RES_ILLEGAL_CHARACTER));

			int o0 = (b0 << 2) | (b1 >>> 4);
			int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
			int o2 = ((b2 & 3) << 6) | b3;
			out[op++] = (byte) o0;
			if (op < oLen)
				out[op++] = (byte) o1;
			if (op < oLen)
				out[op++] = (byte) o2;
		}
		return out;
	}
}
