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

package net.laubenberger.bogatyr.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for strings.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public abstract class HelperString {
	private static final Logger log = LoggerFactory.getLogger(HelperString.class);

	public static final String NEW_LINE = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final String EMPTY_STRING = ""; //$NON-NLS-1$
	public static final String TAB = "\t"; //$NON-NLS-1$
	public static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	public static final String DOUBLE_QUOTE = "\""; //$NON-NLS-1$
	public static final String SPACE = " "; //$NON-NLS-1$
	public static final String PERIOD = "."; //$NON-NLS-1$
	public static final String COMMA = ","; //$NON-NLS-1$
	public static final String SEMICOLON = ";"; //$NON-NLS-1$

	/**
	 * Checks if a {@link CharSequence} is valid.
	 *
	 * @param input to check
	 * @return true/false
	 * @see CharSequence
	 * @since 0.7.0
	 */
	public static boolean isValid(final CharSequence input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));

		final boolean result = !(null == input || 0 == input.length());

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Checks if a {@link String} is full numeric.
	 *
	 * @param input to check
	 * @return true/false
	 * @since 0.7.0
	 */
	public static boolean isNumeric(final String input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}

		boolean result = false;

		//TODO a bit lazy implemented... improve with regex if possible
		try {
			new BigDecimal(input);
			result = true;
		} catch (NumberFormatException ex) {
			log.info("NumberFormat invalid", ex); //$NON-NLS-1$
		}

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Fill a {@link CharSequence} with a char.
	 *
	 * @param fillChar	char to fill the string
	 * @param fillLength length of the filled string
	 * @return filled {@link CharSequence}
	 * @since 0.7.0
	 */
	public static CharSequence fill(final char fillChar, final int fillLength) { //$JUnit$
		log.debug(HelperLog.methodStart(fillChar, fillLength));
		if (0 >= fillLength) {
			throw new RuntimeExceptionMustBeGreater("fillLength", fillLength, 0); //$NON-NLS-1$
		}

		int length = fillLength;
		final char[] chars = new char[length];

		while (0 < length) {
			--length;
			chars[length] = fillChar;
		}
		final String result = new String(chars);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Reverses a {@link String}.
	 *
	 * @param input {@link String}
	 * @return reversed {@link String}
	 * @since 0.7.0
	 */
	public static String reverse(final String input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (!isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}

		final String result = new StringBuilder(input).reverse().toString();

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Clean a {@link String} to numeric chars.
	 *
	 * @param text {@link String}
	 * @return numeric {@link String}
	 * @since 0.7.0
	 */
	public static String getValidNumericString(final String text) { //$JUnit$
		log.debug(HelperLog.methodStart(text));
		if (!isValid(text)) {
			throw new RuntimeExceptionIsNullOrEmpty("text"); //$NON-NLS-1$
		}

		boolean isNegative = false;
		if (text.startsWith(Constants.NEGATIVE_SIGN)) {
			isNegative = true;
		}

		final String temp = text.replaceAll("[^0-9.]+", EMPTY_STRING); //$NON-NLS-1$

		boolean isPeriod = false;
		final StringBuilder sb = new StringBuilder(temp.length());

		// remove multiple periods
		for (int ii = 0; ii < temp.length(); ii++) {
			final char character = temp.charAt(ii);

			if ('.' == character) {
				if (!isPeriod) {
					sb.append(PERIOD);
					isPeriod = true;
				}
			} else {
				sb.append(character);
			}
		}

		String result = null;

		if (!(temp.isEmpty() || isPeriod && 1 == sb.length())) {
			result = isNegative ? Constants.NEGATIVE_SIGN + sb.toString() : sb.toString();
		}

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Concatenates strings with a separator (e.g. for CSV export).
	 *
	 * @param separator between the strings
	 * @param isTrimmed true/false
	 * @param strings	to concatenate
	 * @return concatenated {@link String}
	 * @since 0.7.0
	 */
	public static String concatenate(final String[] strings, final String separator, final boolean isTrimmed) { //$JUnit$
		log.debug(HelperLog.methodStart(strings, separator, isTrimmed));
		if (!HelperArray.isValid(strings)) {
			throw new RuntimeExceptionIsNullOrEmpty("strings"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();

		for (final String string : strings) {
			if (isValid(string)) { //TODO correct or should it be an exception
				if (null != separator && 0 < sb.length()) {
					sb.append(separator);
				}

				if (isTrimmed) {
					sb.append(string.trim());
				} else {
					sb.append(string);
				}
			}
		}
		final String result = sb.toString();

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Concatenates strings.
	 *
	 * @param strings to concatenate
	 * @return concatenated {@link String}
	 * @since 0.9.0
	 */
	public static String concatenate(final String... strings) { //$JUnit$
		log.debug(HelperLog.methodStart(strings));

		final String result = concatenate(strings, null, true);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link String} from a given byte-array and encoding.
	 *
	 * @param data	  for the {@link String}
	 *                 //     * @param length of the {@link String}
	 * @param encoding of the given data
	 * @return new {@link String}
	 * @throws UnsupportedEncodingException
	 * @since 0.8.0
	 */
	public static String toString(final byte[] data, final String encoding) throws UnsupportedEncodingException {
		log.debug(HelperLog.methodStart(data, encoding));
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!isValid(encoding)) {
			throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
		}

		final String result = new String(data, encoding);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a byte-array from a given {@link String} and encoding.
	 *
	 * @param input	 {@link String} for the byte-array
	 * @param encoding of the given {@link String}
	 * @return {@link String} as byte-array
	 * @throws UnsupportedEncodingException
	 * @since 0.8.0
	 */
	public static byte[] toBytes(final String input, final String encoding) throws UnsupportedEncodingException {
		log.debug(HelperLog.methodStart(input, encoding));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (!isValid(encoding)) {
			throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
		}

		final byte[] result = input.getBytes(encoding);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * The same as startsWith() from {@link String}, but ignores the case.
	 *
	 * @param string to inspect
	 * @param prefix to find at the start of the string
	 * @return true/false
	 * @since 0.9.0
	 */
	public static boolean startsWith(final String string, final String prefix) { //$JUnit$
		log.debug(HelperLog.methodStart(string, prefix));
		if (null == string) {
			throw new RuntimeExceptionIsNull("string"); //$NON-NLS-1$
		}
		if (!isValid(prefix)) {
			throw new RuntimeExceptionIsNullOrEmpty("prefix"); //$NON-NLS-1$
		}

		final boolean result = string.matches("(?i)" + prefix + ".*");  //$NON-NLS-1$//$NON-NLS-2$

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * The same as endsWith() from {@link String}, but ignores the case.
	 *
	 * @param string to inspect
	 * @param suffix to find at the end of the string
	 * @return true/false
	 * @since 0.9.0
	 */
	public static boolean endsWith(final String string, final String suffix) { //$JUnit$
		log.debug(HelperLog.methodStart(string, suffix));
		if (null == string) {
			throw new RuntimeExceptionIsNull("string"); //$NON-NLS-1$
		}
		if (!isValid(suffix)) {
			throw new RuntimeExceptionIsNullOrEmpty("suffix"); //$NON-NLS-1$
		}

		final boolean result = string.matches("(?i).*" + suffix); //$NON-NLS-1$

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * The same as contains() from {@link String}, but ignores the case.
	 *
	 * @param string to inspect
	 * @param part	to find in the string
	 * @return true/false
	 * @since 0.9.0
	 */
	public static boolean contains(final String string, final String part) { //$JUnit$
		log.debug(HelperLog.methodStart(string, part));
		if (null == string) {
			throw new RuntimeExceptionIsNull("string"); //$NON-NLS-1$
		}
		if (!isValid(part)) {
			throw new RuntimeExceptionIsNullOrEmpty("part"); //$NON-NLS-1$
		}

		final boolean result = string.matches("(?i).*" + part + ".*");  //$NON-NLS-1$//$NON-NLS-2$

		log.debug(HelperLog.methodExit(result));
		return result;
	}
}