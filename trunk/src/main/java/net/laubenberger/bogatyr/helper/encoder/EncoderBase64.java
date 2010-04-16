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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.helper.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperEnvironment;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * Encodes and decodes data to Base64 format.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.1.0
 */
public abstract class EncoderBase64 {
	private static final Logger log = LoggerFactory.getLogger(EncoderBase64.class);

	private static final String ERROR_ILLEGAL_CHARACTER = "Illegal character in Base64 encoded data"; //$NON-NLS-1$

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
	 * Encodes a {@link String} into Base64 format.
	 * No blanks or line breaks are inserted.
	 *
	 * @param input String to be encoded
	 * @return String with the Base64 encoded data
	 * @since 0.1.0
	 */
	public static String encode(final String input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (input.length() * 2 > HelperEnvironment.getMemoryFree()) {
			throw new RuntimeExceptionExceedsVmMemory("input", input.length() * 2); //$NON-NLS-1$
		}

		final String result = new String(encode(input.getBytes()));

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Encodes a byte-array into Base64 format.
	 * No blanks or line breaks are inserted.
	 *
	 * @param input byte-array to be encoded
	 * @return character array with the Base64 encoded data
	 * @since 0.1.0
	 */
	public static char[] encode(final byte[] input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
			throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
		}

		final char[] result = encode(input, input.length);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Decodes a {@link String} from Base64 format.
	 *
	 * @param input Base64 string to be decoded
	 * @return Array containing the decoded data bytes
	 * @since 0.1.0
	 */
	public static byte[] decode(final String input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (input.length() * 2 > HelperEnvironment.getMemoryFree()) {
			throw new RuntimeExceptionExceedsVmMemory("input", input.length() * 2); //$NON-NLS-1$
		}

		final byte[] result = decode(input.toCharArray());

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Decodes a byte array from Base64 format.
	 * No blanks or line breaks are allowed within the Base64 encoded data.
	 *
	 * @param input character array containing the Base64 encoded data
	 * @return Array containing the decoded data bytes
	 * @since 0.1.0
	 */
	public static byte[] decode(final char[] input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
			throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
		}

		int iLen = input.length;

		if (0 != iLen % 4) {
			throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4"); //$NON-NLS-1$
		}

		while (0 < iLen && '=' == input[iLen - 1]) {
			iLen--;
		}

		final int oLen = iLen * 3 / 4;
		final byte[] result = new byte[oLen];

		int ip = 0;
		int op = 0;
		while (ip < iLen) {
			final int i0 = input[ip];
			ip++;
			final int i1 = input[ip];
			ip++;
			final int i2 = (int) (ip < iLen ? input[ip] : 'A');
			ip++;
			final int i3 = (int) (ip < iLen ? input[ip] : 'A');
			ip++;

			if (Byte.MAX_VALUE < i0 || Byte.MAX_VALUE < i1 || Byte.MAX_VALUE < i2 || Byte.MAX_VALUE < i3) {
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
			result[op] = (byte) o0;
			op++;
			if (op < oLen) {
				result[op] = (byte) o1;
				op++;
			}
			if (op < oLen) {
				result[op] = (byte) o2;
				op++;
			}
		}
		log.debug(HelperLog.methodExit(result));
		return result;
	}


	/*
	 * Private methods
	 */

	/**
	 * Encodes a byte-array into Base64 format.
	 * No blanks or line breaks are inserted.
	 *
	 * @param in	Array containing the data bytes to be encoded
	 * @param iLen Number of bytes to process in
	 * @return Character array with the Base64 encoded data
	 * @since 0.1.0
	 */
	private static char[] encode(final byte[] in, final int iLen) {
		log.trace(HelperLog.methodStart(in, iLen));

		final int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
		final int oLen = (iLen + 2) / 3 * 4; // output length including padding
		final char[] result = new char[oLen];
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
			result[op] = map1[o0];
			op++;
			result[op] = map1[o1];
			op++;
			result[op] = op < oDataLen ? map1[o2] : '=';
			op++;
			result[op] = op < oDataLen ? map1[o3] : '=';
			op++;
		}
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
