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

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This is a helper class for arrays.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091210)
 * @since 0.7.0
 */
public abstract class HelperArray {
	public static final Class<?>[] EMPTY_ARRAY_CLASS = new Class[0];
	public static final Object[] EMPTY_ARRAY_OBJECT = new Object[0];
	public static final String[] EMPTY_ARRAY_STRING = new String[0];
	public static final Boolean[] EMPTY_ARRAY_BOOLEAN = new Boolean[0];
	public static final Double[] EMPTY_ARRAY_DOUBLE = new Double[0];
	public static final Integer[] EMPTY_ARRAY_INTEGER = new Integer[0];
	public static final Float[] EMPTY_ARRAY_FLOAT = new Float[0];
	public static final byte[] EMPTY_ARRAY_BYTE = new byte[0];
    public static final char[] EMPTY_ARRAY_CHAR = new char[0];
	public static final Long[] EMPTY_ARRAY_LONG = new Long[0];
	public static final Short[] EMPTY_ARRAY_SHORT = new Short[0];
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
        return !(null == arg || 0 == arg.length);
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
	 * Concatenate 1-n arrays to one array.
	 * 
	 * @param arrays to concatenate
	 * @return concatenated array
	 * @since 0.7.0
	 */
    @SuppressWarnings("unchecked")
	public static <T> T[] concatenate(final T[]... arrays) { //$JUnit$
        final List<T> result = new ArrayList<T>();
        
        for (final T[] array : arrays) {
        	if (null != array) {
        		result.addAll(Arrays.asList(array));
        	}
        }
        
        final T[] array = (T[])Array.newInstance(arrays.getClass().getComponentType().getComponentType(), result.size());
        
        for (int ii = 0; ii < result.size(); ii++) {
        	array[ii] = result.get(ii);
        }
        
        return array;
    }
    
	/**
	 * Concatenate two byte-arrays to one byte-array.
	 * 
	 * @param inA first array
	 * @param inB second array
	 * @return array a & b as one new byte-array
	 * @since 0.7.0
	 */
    public static byte[] concatenate(final byte[] inA, final byte[] inB) { //$JUnit$
    	byte[] a = inA;
    	byte[] b = inB;
    	
    	if (null == a) {
            a = EMPTY_ARRAY_BYTE;
        }

    	if (null == b) {
            b = EMPTY_ARRAY_BYTE;
        }
    	
    	final byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        
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
			throw new IllegalArgumentException("array is null!"); //$NON-NLS-1$
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
    public static <T> String dump(final T[] array) { //$JUnit$
		if (null == array) {
			throw new IllegalArgumentException("array is null!"); //$NON-NLS-1$
		}
		
		final StringBuilder sb = new StringBuilder();

        int ii = 0;
        for (final T element : array) {
            if (0 < ii) {
                sb.append(HelperString.NEW_LINE);
            }
        	sb.append(element);
        	ii++;
        }
        return sb.toString();
    }
}