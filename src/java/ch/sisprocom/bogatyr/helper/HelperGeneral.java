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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;


/**
 * This is a general helper class for general problems.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090508
 */
public abstract class HelperGeneral { //TODO are the methods isValidxxx still needed ore useful and is logging needed?
	private static final String HASHCODE_ALGORITHM_SHA256 = "SHA-256"; //$NON-NLS-1$ //TODO update in Wiki!
	private static final char[] RANDOMKEY_SEED_DEFAULT    = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	/**
     * Creates an instance of a class.
     * 
     * @param clazz full qualified class name
     * @return instantiated object
	 * @throws IllegalAccessException
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
     */
	public static <T> T createInstance(final Class<T> clazz) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (null == clazz) {
			throw new IllegalArgumentException("clazz is null!"); //$NON-NLS-1$
		}
		
		return clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
	}

	/**
     * Creates an instance of a class with parameters.
     * 
     * @param clazz full qualified class name
     * @param paramClazzes classes for the constructor
     * @param params parameters for the constructor
     * @return instantiated object
	 * @throws IllegalAccessException
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
     */
	public static <T> T createInstance(final Class<T> clazz, final Class<?>[] paramClazzes, final Object[] params) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (null == clazz) {
			throw new IllegalArgumentException("clazz is null!"); //$NON-NLS-1$
		}
		if (!isValid(paramClazzes)) {
			throw new IllegalArgumentException("paramClazzes is null or empty!"); //$NON-NLS-1$
		}
		if (!isValid(params)) {
			throw new IllegalArgumentException("params is null or empty!"); //$NON-NLS-1$
		}
		
		return clazz.getConstructor(paramClazzes).newInstance(params);
	}
	
    /**
     * Checks if a {@link CharSequence} is valid.
     * 
     * @param arg to check
     * @return true/false
     */	
	public static boolean isValid(final CharSequence arg) {
        return !(null == arg || 0 == arg.length());
    }

	/**
     * Checks if a {@link String} is full numeric.
     * 
     * @param arg to check
     * @return true/false
     */	
	public static boolean isStringNumeric(final String arg) { //TODO a bit lazy implemented... improve with regex if possible
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
     * Checks if an {@link Object} array is valid.
     * 
     * @param arg to check
     * @return true/false
     */	
	public static boolean isValid(final Object[] arg) {
        return !(null == arg || 0 == arg.length);
    }
	
	/**
     * Checks if a byte-array is valid.
     * 
     * @param arg to check
     * @return true/false
     */	
	public static boolean isValid(final byte[] arg) {
        return !(null == arg || 0 == arg.length);
    }
	
	/**
     * Checks if a {@link Collection} is valid.
     * 
     * @param arg to check
     * @return true/false
     */
	public static boolean isValid(final Collection<?> arg) {
        return !(null == arg || arg.isEmpty());
    }
	
	/**
	 * Serialize an {@link Object} into a byte-array.
	 * It uses the {@link ByteArrayOutputStream} and the {@link ObjectOutputStream}.
	 * 
	 * @param obj convert into a byte-array.
	 * @return object as byte-array
	 * @throws IOException
	 * @see ByteArrayOutputStream
	 * @see ObjectOutputStream
	 */
	public static byte[] serialize(final Object obj) throws IOException {
		if (null == obj) {
			throw new IllegalArgumentException("obj is null!"); //$NON-NLS-1$
		}	

		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();

            return bos.toByteArray();
        } finally {
        	if (oos != null) {
        		oos.close();	
        	}
//                bos.close();
        }
	}
	
	/**
	 * Deserialize a given byte-array into a valid {@link Object}.
	 * It uses the {@link ByteArrayInputStream} and the {@link ObjectInputStream}.
	 * 
	 * @param bytes convert into an object
	 * @return valid object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @see ByteArrayInputStream
	 * @see ObjectInputStream
	 */
	public static Object deserialize(final byte[] bytes) throws IOException, ClassNotFoundException {
		if (!isValid(bytes)) {
			throw new IllegalArgumentException("params is null or empty!"); //$NON-NLS-1$
		}
		
		ObjectInputStream ois = null;
		final Object obj;
		
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            obj = ois.readObject();
        } finally {
        	if (ois != null) {
        		ois.close();
        	}
        }
        return obj;
	}
	
	/**
	 * Concatenate two {@link Object} arrays to one array.
	 * 
	 * @param inA first array
	 * @param inB second array
	 * @return array a & b as one new array
	 */
    public static Object[] concatenate(final Object[] inA, final Object[] inB) {
    	Object[] a = inA;
    	Object[] b = inB;
    	
    	if (a == null) {
            a = new Object[0];
        }

    	if (b == null) {
            b = new Object[0];
        }
    	
    	final Object[] result = new Object[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        
        return result;
    }
    
	/**
	 * Concatenate two byte-arrays to one byte-array.
	 * 
	 * @param inA first array
	 * @param inB second array
	 * @return array a & b as one new byte-array
	 */
    public static byte[] concatenate(final byte[] inA, final byte[] inB) {
    	byte[] a = inA;
    	byte[] b = inB;
    	
    	if (a == null) {
            a = new byte[0];
        }

    	if (b == null) {
            b = new byte[0];
        }
    	
    	final byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        
        return result;
    }
    
	/**
	 * Removes duplicate objects from {@link Collection}.
	 * 
	 * @param list containing duplicate objects
	 * @return collection without duplicates
	 */
    public static <T> Collection<T> removeDuplicates(final Collection<T> list) {
		return new ArrayList<T>(new HashSet<T>(list));
    }
	
    /**
	 * Removes duplicate objects from an {@link Object} array.
	 * 
	 * @param array containing duplicate objects
	 * @return array without duplicates
	 */
    public static Object[] removeDuplicates(final Object[] array) {
		final Collection<?> list = removeDuplicates(Arrays.asList(array));
		final Object[] temp = new Object[list.size()];
		return list.toArray(temp);
    }

    /**
     * Generates a hash (unique {@link String}) from an input {@link Object}.
     * This is also used for unique keys.
     *
     * @param algo to use
     * @param data to generate a hash
     * @return generated hash value
     * @throws NoSuchAlgorithmException
     */
    public static String getHashCode(final String algo, final Object data) throws NoSuchAlgorithmException {
    	final MessageDigest algorithm = MessageDigest.getInstance(algo);
		final byte[] input = toString(data).getBytes();

		algorithm.reset();
		algorithm.update(input);
		
		final byte[] messageDigest = algorithm.digest();
        final StringBuilder hexString = new StringBuilder();

		for (final byte digest : messageDigest) {
			final String hex = Integer.toHexString(0xFF & (int) digest);
			if (1 == hex.length()) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

    /**
     * Generates a hash (unique {@link String}) with SHA-256 from an input object.
     *
     * @param data to generate a hash
     * @return generated hash value
     * @throws NoSuchAlgorithmException
     */
    public static String getHashCode(final Object data) throws NoSuchAlgorithmException {
    	return getHashCode(HASHCODE_ALGORITHM_SHA256, data);
    }

    /**
     * Generates an unique {@link String} with a given seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @param seed for the string (e.g. "1,2...0,A,B...Z)
     * @return generated unique string
     */
    public static String getRandomKey(final int digits, final char[] seed) {
		final StringBuilder sb = new StringBuilder();

        for (int ii = 0; ii < digits; ii++) {
            sb.append(seed[HelperMath.random(seed.length)]);
        }
        return sb.toString();
    }

    /**
     * Generates an unique {@link String} with default seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @return generated unique String
     */
    public static String getRandomKey(final int digits) {
    	return getRandomKey(digits, RANDOMKEY_SEED_DEFAULT);
    }

    /**
     * Generates an universally unique identifier (UUID).
     *
     * @return generated universally unique identifier
     */
    public static UUID getUUID() {
    	return UUID.randomUUID();
    }    

    /**
     * Fill a {@link CharSequence} with a char.
     * 
     * @param fillChar char to fill the string
     * @param fillLength length of the filled string 
     * @return filled string
     */
    public static CharSequence fillString(final char fillChar, final int fillLength) {
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
    public static String reverseString(final String input) {
    	return new StringBuffer(input).reverse().toString();
    }

    /**
     * Clean a {@link String} to numeric chars.
     * 
     * @param text string
     * @return numeric string
     */
    public static String getValidNumericString(final String text) { //TODO document in Wiki!
    	
    	if (!isValid(text)) {
    		return null;
    	}

        boolean isNegative = false;
        if (text.startsWith("-")) { //$NON-NLS-1$
    		isNegative = true;
    	}
    	
    	final String result = text.replaceAll("[^0-9.]+", ""); //$NON-NLS-1$ //$NON-NLS-2$

    	boolean isPeriod = false;
    	StringBuilder sb = new StringBuilder(result.length());
   
    	// remove multiple periods
    	for (int ii = 0; ii < result.length(); ii++) {
    		char character = result.charAt(ii);
   
    		if ('.' == character) {
    			if (!isPeriod) {
    		  		sb.append(character);
    		  		isPeriod = true;
    		 	}
    		} else {
    	  		sb.append(character);
    		}
    	}
    	
    	if (result.isEmpty() || (isPeriod && sb.length() == 1)) {
    		return null;
    	}
    	
    	return isNegative ? '-' + sb.toString() : sb.toString();
    }
    
    /**
     * Dump an {@link Iterable}.
     * 
     * @param list to dump
     * @return dump string
     */
    public static String dump(final Iterable<?> list) {
        final StringBuilder sb = new StringBuilder();

        for (final Object value : list) {
            sb.append(value);
            sb.append(Const.NEW_LINE);
        }
        return sb.toString();
    }

    /**
     * Dump a {@link Map}.
     * 
     * @param map to dump
     * @return dump string
     */
    public static String dump(final Map<?, ?> map) {
        final StringBuilder sb = new StringBuilder();

        for (final Map.Entry<?, ?> pair : map.entrySet()) {
            sb.append(pair.getKey());
            sb.append('=');
            sb.append(pair.getValue());
            sb.append(Const.NEW_LINE);
        }
        return sb.toString();
    }

     /**
     * Dump an {@link Object} array.
     * 
     * @param array to dump
     * @return dump string
     */
    public static String dump(final Object[] array) {
		final StringBuilder sb = new StringBuilder();

		for (final Object value : array) {
            sb.append(value);
            sb.append(Const.NEW_LINE);
        }
		return sb.toString();
    }

    /**
     * Generic toString() method for {@link Object}  and different purposes.
     * 
     * @param object to dump
     * @return dumped object string
     */
    public static String toString(final Object object) {
    	final Collection<String> list = new ArrayList<String>();
    	toString(object, list);

    	return object.getClass().getName() + list.toString();
    }

    
    /*
     * Private methods
     */
    private static void toString(final Object object, final Collection<String> list) {
    	final Field[] fields = object.getClass().getDeclaredFields();
    	AccessibleObject.setAccessible(fields, true);

    	for (final Field field : fields) {
    		try {  
//    			if (objectField == object ||
//            		(objectField instanceof Collection<?> && ((Collection<?>)objectField).contains(object)) || 
//            		(objectField instanceof Map<?, ?> && (((Map<?, ?>)objectField).containsKey(object) || ((Map<?, ?>)objectField).containsValue(object)))) {
//            		list.add(field.getName() + '=' + object.getClass().getName());
//              	} else {
//	               list.add(field.getName() + '=' + objectField);
//	            }
    			
       			if (field.getType().equals(object.getClass())) {
       				list.add(field.getName() + '=' + object.getClass().getName());
//       			} else if (objectField instanceof Collection<?> && ((Collection<?>)objectField).contains(object)) {
//       				System.out.println("COLL");
//            		list.add(field.getName() + '=' + object.getClass().getName());
//       			} else if (objectField instanceof Map<?, ?> && (((Map<?, ?>)objectField).containsKey(object) | ((Map<?, ?>)objectField).containsValue(object))) {
////       			} else if (objectField instanceof Map<?, ?>) {
//       				System.out.println("MAP");
//               		list.add(field.getName() + '=' + object.getClass().getName());
              	} else {
              		list.add(field.getName() + '=' + field.get(object));
	            }

    		} catch (Exception ex) {
                // do nothing
            }
    	}
    	
//    	if (clazz.getSuperclass().getSuperclass() != null) {
////    	if (clazz.getSuperclass() != null) {    		
//    		toString(object, clazz.getSuperclass(), list);
//    	}
    }
}