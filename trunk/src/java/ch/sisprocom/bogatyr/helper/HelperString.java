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

import java.math.BigDecimal;


/**
 * This is a general helper class for general problems.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090520
 */
public abstract class HelperString {
	public static final String NEW_LINE = System.getProperty("line.separator"); //$NON-NLS-1$
	public static final String FILE_SEPARATOR = System.getProperty("file.separator"); //$NON-NLS-1$
	public static final String PATH_SEPARATOR = System.getProperty("path.separator"); //$NON-NLS-1$

	public static final String EMPTY_STRING = ""; //$NON-NLS-1$
	public static final String TAB = "\t"; //$NON-NLS-1$
	public static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	public static final String DOUBLE_QUOTE = "\""; //$NON-NLS-1$
	public static final String SPACE = " "; //$NON-NLS-1$
	public static final String PERIOD = "."; //$NON-NLS-1$
	public static final String SEMICOLON = ";"; //$NON-NLS-1$	  
	
	/**
     * Checks if a {@link CharSequence} is valid.
     * 
     * @param arg to check
     * @return true/false
     */	
	public static boolean isValid(final CharSequence arg) { //$JUnit
        return !(null == arg || 0 == arg.length());
    }

	/**
     * Checks if a {@link String} is full numeric.
     * 
     * @param arg to check
     * @return true/false
     */	
	public static boolean isNumeric(final String arg) { //$JUnit
		//TODO a bit lazy implemented... improve with regex if possible
		if (isValid(arg)) {
			try{
				new BigDecimal(arg);
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
     * @return filled string
     */
    public static CharSequence fillString(final char fillChar, final int fillLength) { //$JUnit
		if (0 >= fillLength) {
			throw new IllegalArgumentException("fillLength must be greater than 0: " + fillLength); //$NON-NLS-1$
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
     * @param input string
     * @return reversed string
     */
    public static String reverseString(final String input) { //$JUnit
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}

    	return new StringBuilder(input).reverse().toString();
    }

    /**
     * Clean a {@link String} to numeric chars.
     * 
     * @param text string
     * @return numeric string
     */
    public static String getValidNumericString(final String text) { //$JUnit
    	
    	if (!isValid(text)) {
    		return null;
    	}

        boolean isNegative = false;
        if (text.startsWith(Const.NEGATIVE_SIGN)) {
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
    	
    	return isNegative ? Const.NEGATIVE_SIGN + sb.toString() : sb.toString();
    }
    
    /**
     * Concatenates strings with a separator (e.g. for CSV export)
     *
     * @param strings to concatenate
     * @param separator between the strings
     * @param trimmed
     * @return concatenated string
     */
    public static String concatenate(final String[] strings, final char separator, final boolean trimmed) {
        if (!HelperArray.isValid(strings)) {
        	return null;
        }

        final StringBuilder sb = new StringBuilder();
        
        for (final String strValue : strings) {
            if (isValid(strValue)) {
                if (0 < sb.length()) {
                    sb.append(separator);
                }

                if (trimmed) {
                    sb.append(strValue.trim());
                } else {
                    sb.append(strValue);
                }
            }
        }
        return sb.toString();
    }
}