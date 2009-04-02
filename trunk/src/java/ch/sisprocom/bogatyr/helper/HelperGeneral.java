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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This is a general helper class for general problems.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090325
 */
public abstract class HelperGeneral { //TODO are the methods isValidxxx still needed ore useful and is logging needed?
	private static final String HASHCODE_ALGORITHM_SHA256 = "SHA-256"; //$NON-NLS-1$
	private static final char[] RANDOMKEY_SEED_DEFAULT    = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	/**
     * Creates an object with parameters via reflection API.
     * 
     * @param clazz Full qualified class name
     * @return instanciated object
	 * @throws IllegalAccessException
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
     */
//	public static Object createObject(final String clazz, final Object[] parameter) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
//		final Object obj;
//
//		final Class<?> classDefinition = Class.forName(clazz);
//			
//		if (parameter == null) {
//			obj = classDefinition.newInstance();
//		} else {
//            final Class<?>[] stringArgsClass = new Class[]{String.class};
//            final Constructor<?> classConstructor = classDefinition.getConstructor(stringArgsClass);
//			obj = classConstructor.newInstance(parameter);
//		}
//		return obj;
//	}
	
	public static <T> Object newInstance(final Class<T> clazz) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return newInstance(clazz, new Class[0], new Object[0]);
	}

	public static <T> Object newInstance(final Class<T> clazz, final Class<?>[] paramClazzes, final Object[] params) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return clazz.getConstructor(paramClazzes).newInstance(params);
	}

    /**
     * Returns a line separator.
     *
     * @return line separator
     */
    public static String getLS() {
    	return System.getProperty("line.separator"); //$NON-NLS-1$
    }

	/**
     * Checks if a String is valid.
     * 
     * @param arg String to check
     * @return true/false
     */	
	public static boolean isValidString(final CharSequence arg) {
        return !(!isValidObject(arg) || 0 == arg.length());
    }

	/**
     * Checks if a String is full numeric.
     * 
     * @param arg String to check
     * @return true/false
     */	
	public static boolean isStringNumeric(final CharSequence arg) {
		if (isValidString(arg)) {
			final Pattern p = Pattern.compile("[0-9.]+"); //$NON-NLS-1$
			final Matcher m = p.matcher(arg);

			if (m.matches()) {
                return true;
            }
		}
		return false;
	}

	/**
     * Checks if a int is not 0.
     * 
     * @param arg int to check
     * @return true/false
     */
	public static boolean isValidInt(final int arg) {
        return 0 != arg;
    }
	
	/**
     * Checks if a double is not 0.
     * 
     * @param arg double to check
     * @return true/false
     */	
	public static boolean isValidDouble(final double arg) {
        return 0.0D != arg;
    }
	
	/**
     * Checks if a long is not 0.
     * 
     * @param arg long to check
     * @return true/false
     */
	public static boolean isValidLong(final long arg) {
        return 0L != arg;
    }

	/**
     * Checks if a float is not 0.
     * 
     * @param arg float to check
     * @return true/false
     */	
	public static boolean isValidFloat(final float arg) {
        return 0.0F != arg;
    }

	/**
     * Checks if a {@link Object} is not null.
     * 
     * @param arg object to check
     * @return true/false
     */	
	public static boolean isValidObject(final Object arg) {
        return arg != null;
    }

	/**
     * Checks if a array is valid.
     * 
     * @param arg object array to check
     * @return true/false
     */	
	public static boolean isValidArray(final Object[] arg) {
        return !(!isValidObject(arg) || 0 == arg.length);
    }

	/**
     * Checks if a collection is valid.
     * 
     * @param list ArrayList to check
     * @return true/false
     */
	public static boolean isValidCollection(final Collection<?> list) {
        return !(!isValidObject(list) || list.isEmpty());
    }
	
	/**
	 * Converts an {@link Object} into a byte-array.
	 * It uses the {@link ByteArrayOutputStream} and the {@link ObjectOutputStream}.
	 * 
	 * @param obj The Object to convert into a byte-array.
	 * @return object as byte-array
	 * @throws IOException
	 * @see ByteArrayOutputStream
	 * @see ObjectOutputStream
	 */
	public static byte[] getBytesFromObject(final Object obj) throws IOException {
		byte[] data = null;
		
		if (obj != null) {
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;

            try {
                oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);
                oos.flush();
	
        	    data = bos.toByteArray();
            } finally {
            	if (oos != null) {
            		oos.close();	
            	}
//                bos.close();
            }
        }
	    return data;
	}
	
	/**
	 * Converts a given byte-array into a valid {@link Object}.
	 * It uses the {@link ByteArrayInputStream} and the {@link ObjectInputStream}.
	 * 
	 * @param bytes The byte-array to convert into an {@link Object}.
	 * @return a valid object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @see ByteArrayInputStream
	 * @see ObjectInputStream
	 */
	public static Object getObjectFromBytes(final byte[] bytes) throws IOException, ClassNotFoundException {
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
	 * Concatenate two arrays to one array.
	 * 
	 * @param inA first array
	 * @param inB second array
	 * @return The array a & b as one new array
	 */
    public static Object[] concatenateArrays(final Object[] inA, final Object[] inB) {
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
	 * @return The array a & b as one new byte-array
	 */
    public static byte[] concatenateByteArrays(final byte[] inA, final byte[] inB) {
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
	 * Removes duplicate objects from list.
	 * 
	 * @param list containing duplicate objects
	 * @return list without duplicates
	 */
    public static <T> List<T> removeDuplicates(final List<T> list) {
		return new ArrayList<T>(new HashSet<T>(list));
    }
	
    /**
	 * Removes duplicate objects from array.
	 * 
	 * @param array containing duplicate objects
	 * @return array without duplicates
	 */
    public static Object[] removeDuplicates(final Object[] array) {
		final List<?> list = removeDuplicates(Arrays.asList(array));
		final Object[] temp = new Object[list.size()];
		return list.toArray(temp);
    }

    /**
     * Generates a hash (unique string) from an input object.
     * This is also used for unique keys.
     *
     * @param algo to use
     * @param data to generate a hash
     * @return generated hash value
     * @throws NoSuchAlgorithmException
     */
    public static Object getHashCode(final String algo, final Object data) throws NoSuchAlgorithmException {
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
     * Generates a hash (unique string) with SHA-256 from an input object.
     *
     * @param data to generate a hash
     * @return generated hash value
     * @throws NoSuchAlgorithmException
     */
    public static Object getHashCode(final Object data) throws NoSuchAlgorithmException {
    	return getHashCode(HASHCODE_ALGORITHM_SHA256, data);
    }

    /**
     * Generates an unique string.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @param seed for the string (e.g. "1,2...0,A,B...Z)
     * @return generated unique String
     */
    public static Object getRandomKey(final int digits, final char[] seed) {
		final StringBuilder sb = new StringBuilder();

        for (int ii = 0; ii < digits; ii++) {
            sb.append(seed[HelperMath.random(seed.length)]);
        }
        return sb.toString();
    }

    /**
     * Generates an unique string with default seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits length of result string
     * @return generated unique String.
     */
    public static Object getRandomKey(final int digits) {
    	return getRandomKey(digits, RANDOMKEY_SEED_DEFAULT);
    }
    
    /**
     * Fill a string with a char.
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
     * Reverses a string.
     * 
     * @param input string
     * @return reversed string
     */
    public static Object reverseString(final String input) {
    	return new StringBuffer(input).reverse().toString();
    }

    /**
     * Clean a string to numeric chars.
     * 
     * @param text string
     * @return numeric string
     */
    public static String getValidNumericString(final String text) { //TODO document in Wiki!
    	final String result = text.replaceAll("[^0-9.]+", ""); //$NON-NLS-1$ //$NON-NLS-2$ //TODO add minus (-) to regex?

    	if (0 == text.length()) {
    		return "0"; //$NON-NLS-1$
    	}
    	return result;
    }
    
    /**
     * Dump a list.
     * 
     * @param list for dump
     * @return dump string
     */
    public static String dump(final Iterable<?> list) {
        final StringBuilder sb = new StringBuilder();

        for (final Object value : list) {
            sb.append(value);
            sb.append(getLS());
        }
        return sb.toString();
    }

    /**
     * Dump a map.
     * 
     * @param map for dump
     * @return dump string
     */
    public static String dump(final Map<?, ?> map) {
        final StringBuilder sb = new StringBuilder();

        for (final Map.Entry<?, ?> pair : map.entrySet()) {
            sb.append(pair.getKey());
            sb.append('=');
            sb.append(pair.getValue());
            sb.append(getLS());
        }
        return sb.toString();
    }

     /**
     * Dump an array.
     * 
     * @param array for dump
     * @return dump string
     */
    public static String dump(final Object[] array) {
		final StringBuilder sb = new StringBuilder();

		for (final Object value : array) {
            sb.append(value);
            sb.append(getLS());
        }
		return sb.toString();
    }

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