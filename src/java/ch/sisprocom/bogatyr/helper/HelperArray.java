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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This is a helper class for arrays.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100216)
 * @since 0.7.0
 */
public abstract class HelperArray {
	private static final Logger log = LoggerFactory.getLogger(HelperArray.class);

	public static final Class<?>[] EMPTY_ARRAY_CLASS 		 = new Class[0];
	public static final Object[] EMPTY_ARRAY_OBJECT 		 = new Object[0];
	public static final String[] EMPTY_ARRAY_STRING 		 = new String[0];
	public static final Boolean[] EMPTY_ARRAY_BOOLEAN 		 = new Boolean[0];
	public static final Double[] EMPTY_ARRAY_DOUBLE 		 = new Double[0];
	public static final Integer[] EMPTY_ARRAY_INTEGER 		 = new Integer[0];
	public static final Float[] EMPTY_ARRAY_FLOAT 			 = new Float[0];
	public static final byte[] EMPTY_ARRAY_BYTE 			 = new byte[0];
    public static final char[] EMPTY_ARRAY_CHAR 			 = new char[0];
	public static final Long[] EMPTY_ARRAY_LONG 			 = new Long[0];
	public static final Short[] EMPTY_ARRAY_SHORT 			 = new Short[0];
	public static final BigInteger[] EMPTY_ARRAY_BIG_INTEGER = new BigInteger[0];
	public static final BigDecimal[] EMPTY_ARRAY_BIG_DECIMAL = new BigDecimal[0];		

	/**
     * Checks if an array is valid.
     * 
     * @param arg to check
     * @return true/false
     * @since 0.7.0
     */	
	public static boolean isValid(final Object[] arg) { //$JUnit$
		log.debug(HelperLog.methodStart(arg));
		
		final boolean result = !(null == arg || 0 == arg.length);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
	
	/**
     * Checks if a byte-array is valid.
     * 
     * @param arg to check
     * @return true/false
     * @since 0.7.0
     */	
	public static boolean isValid(final byte[] arg) { //$JUnit$
        return !(null == arg || 0 == arg.length);
    }
	
	/**
     * Checks if a char-array is valid.
     * 
     * @param arg to check
     * @return true/false
     * @since 0.9.0
     */	
	public static boolean isValid(final char[] arg) { //$JUnit$
        return !(null == arg || 0 == arg.length);
    }

	/**
	 * Concatenate 1-n arrays to one array.
	 * 
	 * @param arrays to concatenate
	 * @return concatenated result array
	 * @since 0.7.0
	 */
	public static <T> T[] concatenate(final T[]... arrays) { //$JUnit$
        if (!isValid(arrays)) {
        	throw new RuntimeExceptionIsNullOrEmpty("arrays"); //$NON-NLS-1$
        }
        
        final List<T> result = new ArrayList<T>();
        
        for (final T[] array : arrays) {
    		result.addAll(Arrays.asList(array));
        }
        
        return result.toArray(arrays[0]);
    }
    
	/**
	 * Concatenate 1-n byte-arrays to one byte-array.
	 * 
	 * @param arrays to concatenate
	 * @return concatenated result array
	 * @since 0.7.0
	 */
	public static byte[] concatenate(final byte[]... arrays) { //$JUnit$
        if (!isValid(arrays)) {
        	throw new RuntimeExceptionIsNullOrEmpty("arrays"); //$NON-NLS-1$
        }

        int totalSize = 0;
        
        for (final byte[] array : arrays) {
        	totalSize += array.length;
        }
        
        final byte[] result = new byte[totalSize];
        int offset = 0;
        
        for (final byte[] array : arrays) {
        	System.arraycopy(array, 0, result, offset, array.length);
        	offset += array.length;
        }
        
        return result;
    }
    
	/**
	 * Concatenate 1-n char-arrays to one char-array.
	 * 
	 * @param arrays to concatenate
	 * @return concatenated result array
	 * @since 0.9.1
	 */
	public static char[] concatenate(final char[]... arrays) {
        if (!isValid(arrays)) {
        	throw new RuntimeExceptionIsNullOrEmpty("arrays"); //$NON-NLS-1$
        }

        int totalSize = 0;
        
        for (final char[] array : arrays) {
        	totalSize += array.length;
        }
        
        final char[] result = new char[totalSize];
        int offset = 0;
        
        for (final char[] array : arrays) {
        	System.arraycopy(array, 0, result, offset, array.length);
        	offset += array.length;
        }
        
        return result;
    }
	
    /**
	 * Removes duplicate objects from an array.
	 * 
	 * @param array containing duplicate objects
	 * @return array without duplicates
	 * @since 0.7.0
	 */
	public static <T> T[] removeDuplicates(final T[] array) { //$JUnit$
		if (null == array) {
			throw new RuntimeExceptionIsNull("array"); //$NON-NLS-1$
		}

		return HelperCollection.toArray(HelperCollection.removeDuplicates(Arrays.asList(array)));
    }

    /**
     * Dump an array.
     * 
     * @param array to dump
     * @return dump string
     * @since 0.7.0
     */
    public static String dump(final Object[] array) { //$JUnit$
		if (null == array) {
			throw new RuntimeExceptionIsNull("array"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();

        for (final Object element : array) {
            if (0 < sb.length()) {
                sb.append(HelperString.NEW_LINE);
            }
        	sb.append(element);
        }
        return sb.toString();
    }
    
    /**
     * Dump a byte-array.
     * 
     * @param array to dump
     * @return dump string
     * @since 0.9.1
     */
    public static String dump(final byte[] array) { //$JUnit$
		if (null == array) {
			throw new RuntimeExceptionIsNull("array"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();

        for (final byte element : array) {
            if (0 < sb.length()) {
                sb.append(HelperString.NEW_LINE);
            }
        	sb.append(element);
        }
        return sb.toString();
    }

    /**
     * Dump a char-array.
     * 
     * @param array to dump
     * @return dump string
     * @since 0.9.1
     */
    public static String dump(final char[] array) { //$JUnit$
		if (null == array) {
			throw new RuntimeExceptionIsNull("array"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();

        for (final char element : array) {
            if (0 < sb.length()) {
                sb.append(HelperString.NEW_LINE);
            }
        	sb.append(element);
        }
        return sb.toString();
    }
}