/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for strings.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20100212)
 * @since 0.7.0
 */
public abstract class HelperString {
	public static final String NEW_LINE = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final String EMPTY_STRING = ""; //$NON-NLS-1$
	public static final String TAB 			= "\t"; //$NON-NLS-1$
	public static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	public static final String DOUBLE_QUOTE = "\""; //$NON-NLS-1$
	public static final String SPACE 		= " "; //$NON-NLS-1$
	public static final String PERIOD 		= "."; //$NON-NLS-1$
	public static final String COMMA 		= ","; //$NON-NLS-1$
	public static final String SEMICOLON 	= ";"; //$NON-NLS-1$	  
	
	/**
     * Checks if a {@link CharSequence} is valid.
     * 
     * @param input to check
     * @return true/false
     * @see CharSequence
     * @since 0.7.0
     */	
	public static boolean isValid(final CharSequence input) { //$JUnit$
        return !(null == input || 0 == input.length());
    }

	/**
     * Checks if a {@link String} is full numeric.
     * 
     * @param input to check
     * @return true/false
     * @since 0.7.0
     */	
	public static boolean isNumeric(final String input) { //$JUnit$
		//TODO a bit lazy implemented... improve with regex if possible
		if (isValid(input)) {
			try{
				new BigDecimal(input);
				return true;
			} catch (NumberFormatException ex) {
				return false;
			}
		}
		return false;
	}

	/**
     * Fill a {@link CharSequence} with a char.
     * 
     * @param fillChar char to fill the string
     * @param fillLength length of the filled string 
     * @return filled {@link CharSequence}
     * @since 0.7.0
     */
    public static CharSequence fill(final char fillChar, final int fillLength) { //$JUnit$
		if (0 >= fillLength) {
			throw new RuntimeExceptionMustBeGreater("fillLength", fillLength, 0); //$NON-NLS-1$
		}

    	int length = fillLength;
    	final char[] chars = new char[length];
        
    	while (0 < length) {
            --length;
            chars[length] = fillChar;
        }
    	return new String(chars);
    }
    
    /**
     * Reverses a {@link String}.
     * 
     * @param input {@link String}
     * @return reversed {@link String}
     * @since 0.7.0
     */
    public static String reverse(final String input) { //$JUnit$
//		if (null == input) {
//			throw new RuntimeExceptionArgumentIsNull("input"); //$NON-NLS-1$
//		}

		if (null != input) {
			return new StringBuilder(input).reverse().toString();
		}
		return null;
    }

    /**
     * Clean a {@link String} to numeric chars.
     * 
     * @param text {@link String}
     * @return numeric {@link String}
     * @since 0.7.0
     */
    public static String getValidNumericString(final String text) { //$JUnit$
    	if (!isValid(text)) {
    		return null;
    	}

        boolean isNegative = false;
        if (text.startsWith(Constants.NEGATIVE_SIGN)) {
    		isNegative = true;
    	}
    	
    	final String result = text.replaceAll("[^0-9.]+", EMPTY_STRING); //$NON-NLS-1$

    	boolean isPeriod = false;
    	final StringBuilder sb = new StringBuilder(result.length());
   
    	// remove multiple periods
    	for (int ii = 0; ii < result.length(); ii++) {
    		final char character = result.charAt(ii);
   
    		if ('.' == character) {
    			if (!isPeriod) {
    		  		sb.append(PERIOD);
    		  		isPeriod = true;
    		 	}
    		} else {
    	  		sb.append(character);
    		}
    	}
    	
    	if (result.isEmpty() || isPeriod && 1 == sb.length()) {
    		return null;
    	}
    	
    	return isNegative ? Constants.NEGATIVE_SIGN + sb : sb.toString();
    }
    
    /**
     * Concatenates strings with a separator (e.g. for CSV export).
     *
     * @param separator between the strings
     * @param isTrimmed true/false
     * @param strings to concatenate
     * @return concatenated {@link String}
     * @since 0.7.0
     */
    public static String concatenate(final String separator, final boolean isTrimmed, final String... strings) { //$JUnit$
        if (!HelperArray.isValid(strings)) {
        	return null;
        }

        final StringBuilder sb = new StringBuilder();
        
        for (final String strValue : strings) {
            if (isValid(strValue)) {
                if (null != separator && 0 < sb.length()) {
                    sb.append(separator);
                }

                if (isTrimmed) {
                    sb.append(strValue.trim());
                } else {
                    sb.append(strValue);
                }
            }
        }
        return sb.toString();
    }
//    
//    /**
//     * Concatenates strings with a separator (e.g. for CSV export).
//     *
//     * @param separator between the strings
//     * @param strings to concatenate
//     * @return concatenated {@link String}
//     * @since 0.8.0
//     */
//    public static String concatenate(final String separator, final String... strings) {
//    	return concatenate(separator, true, strings);
//    }
    
    /**
     * Concatenates strings.
     *
     * @param strings to concatenate
     * @return concatenated {@link String}
     * @since 0.9.0
     */
    public static String concatenate(final String... strings) { //$JUnit$
    	return concatenate(null, true, strings);
    }
    
    /**
     * Returns a {@link String} from a given byte-array and encoding.
     *
     * @param data for the {@link String}
//     * @param length of the {@link String}
     * @param encoding of the given data
     * @return new {@link String}
     * @throws UnsupportedEncodingException 
     * @since 0.8.0
     */
	public static String toString(final byte[] data, /*final int length, */final String encoding) throws UnsupportedEncodingException {
		if (null != data) {
			if (!isValid(encoding)) {
				throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
			}
	
	//		return new String(data, 0, length, encoding);
			return new String(data, encoding);
		}
		return null;
	}
	
    /**
     * Returns a byte-array from a given {@link String} and encoding.
     *
     * @param input {@link String} for the byte-array
     * @param encoding of the given {@link String}
     * @return {@link String} as byte-array
     * @throws UnsupportedEncodingException 
     * @since 0.8.0
     */
	public static byte[] toBytes(final String input, final String encoding) throws UnsupportedEncodingException {
		if (null != input) {
			if (!isValid(encoding)) {
				throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
			}
	
			return input.getBytes(encoding);
		}
		return null;
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
		if (null == string) {
			throw new RuntimeExceptionIsNull("string"); //$NON-NLS-1$
		}

		return string.matches("(?i)" + prefix + ".*");  //$NON-NLS-1$//$NON-NLS-2$
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
		if (null == string) {
			throw new RuntimeExceptionIsNull("string"); //$NON-NLS-1$
		}

		return string.matches("(?i).*" + suffix); //$NON-NLS-1$
	}

	/**
     * The same as contains() from {@link String}, but ignores the case.
     *
     * @param string to inspect
     * @param part to find in the string
     * @return true/false
     * @since 0.9.0
     */
	public static boolean contains(final String string, final String part) { //$JUnit$
		if (null == string) {
			throw new RuntimeExceptionIsNull("string"); //$NON-NLS-1$
		}

		return string.matches("(?i).*" + part + ".*");  //$NON-NLS-1$//$NON-NLS-2$
	}
}