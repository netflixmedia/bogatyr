/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This is a general helper class for general problems
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080725
 */
public abstract class HelperGeneral {
	private static final String CHECKSUM_ALGORITHM_SHA256 = "SHA-256"; //$NON-NLS-1$
	private static final char[] RANDOMKEY_SEED_DEFAULT    = new char[]{'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	/**
     * Creates an object with parameters via Reflection-API
     * 
     * @param clazz Full qualified class name
     * @param parameter Parameter-array
     * @return instanciated object
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
     */
	public static Object createObject(final String clazz, final Object[] parameter) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		final Object object;

		final Class<?> classDefinition = Class.forName(clazz);
			
		if (parameter == null) {
			object = classDefinition.newInstance();
		} else {
            final Class<?>[] stringArgsClass = new Class[]{String.class};
            final Constructor<?> classConstructor = classDefinition.getConstructor(stringArgsClass);
			object = classConstructor.newInstance(parameter);
		}
		return object;
	}

	/**
     * Checks if a String is valid.
     * 
     * @param arg String to check
     * @return true/false
     */	
	public static boolean isValidString(final String arg) {
        return !(!isValidObject(arg) || arg.length() == 0);
    }

	/**
     * Checks if a String is full numeric.
     * 
     * @param arg String to check
     */	
	public static boolean isStringNumeric(final String arg) {
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
        return arg != 0;
    }
	
	/**
     * Checks if a double is not 0.
     * 
     * @param arg double to check
     */	
	public static boolean isValidDouble(final double arg) {
        return arg != 0.0D;
    }
	
	/**
     * Checks if a long is not 0.
     * 
     * @param arg long to check
     * @return true/false
     */
	public static boolean isValidLong(final long arg) {
        return arg != 0L;
    }

	/**
     * Checks if a float is not 0.
     * 
     * @param arg float to check
     */	
	public static boolean isValidFloat(final float arg) {
        return arg != 0.0F;
    }

	/**
     * Checks if a {@link Object} is not null.
     * 
     * @param arg Object to check
     * @return true/false
     */	
	public static boolean isValidObject(final Object arg) {
        return arg != null;
    }

	/**
     * Checks if a array is valid.
     * 
     * @param arg Object array to check
     * @return true/false
     */	
	public static boolean isValidArray(final Object[] arg) {
        return !(!isValidObject(arg) || arg.length == 0);
    }

	/**
     * Checks if a {@link ArrayList} is valid.
     * 
     * @param list ArrayList to check
     */
	public static boolean isValidList(final List<?> list) {
        return !(!isValidObject(list) || list.isEmpty());
    }
	
	/**
	 * Converts an {@link Object} into a byte-array.
	 * It uses the {@link ByteArrayOutputStream} and the {@link ObjectOutputStream}.
	 * 
	 * @param obj The Object to convert into a byte-array.
	 * @return The given Object as byte-array.
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
	 * @return Returns a valid Object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @see ByteArrayInputStream
	 * @see ObjectInputStream
	 */
	public static Object getObjectFromBytes(final byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        } finally {
        	if (ois != null) {
        		ois.close();
        	}
        }
	}
	
	/**
	 * Appends a given byte-array to an existing byte-array.
	 * 
	 * @param inA The first array.
	 * @param inB The second array.
	 * @return The array a & b as one new byte-array.
	 */
    public static byte[] appendByteArray(final byte[] inA, final byte[] inB) {
    	byte[] a = inA;
    	byte[] b = inB;
    	
    	if (a == null) {
            a = new byte[0];
        }

    	if (b == null) {
            b = new byte[0];
        }
    	
    	final byte[] z = new byte[a.length + b.length];
        System.arraycopy(a, 0, z, 0, a.length);
        System.arraycopy(b, 0, z, a.length, b.length);
        return z;
    }
    
	/**
	 * Removes duplicate objects from list
	 * 
	 * @param list containing duplicate objects
	 * @return list without duplicates
	 */
    public static List<?> removeDuplicates(List<?> list) {
    	Set<?> set = new HashSet<Object>(list);
    	
        return new ArrayList<Object>(set);
    }
	
    /**
	 * Removes duplicate objects from array
	 * 
	 * @param array containing duplicate objects
	 * @return array without duplicates
	 */
    public static Object[] removeDuplicates(Object[] array) {
        List<?> list = removeDuplicates(Arrays.asList(array));

        Object[] result = new Object[list.size()];
 
        return list.toArray(result);
    }

    /**
     * Generates an unique string from an input string.
     * This is used for unique keys.
     *
     * @param algo The algorithm to use.
     * @param data The data to encrypt.
     * @return The generated unique String.
     * @throws NoSuchAlgorithmException
     */
    public static String getChecksum(final String algo, final String data) throws NoSuchAlgorithmException {
		final MessageDigest algorithm = MessageDigest.getInstance(algo);

		final byte[] input = data.getBytes();
		algorithm.reset();
		algorithm.update(input);
		final byte[] messageDigest = algorithm.digest();
        final StringBuilder hexString = new StringBuilder();

		for (final byte digest : messageDigest) {
			final String hex = Integer.toHexString(0xFF & (int) digest);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

    /**
     * Generates an unique string with SHA-256 from an input string.
     *
     * @param data The data to encrypt.
     * @return The generated unique String.
     * @throws NoSuchAlgorithmException
     * @see #getChecksum(String, String)
     */
    public static String getChecksum(final String data) throws NoSuchAlgorithmException {
		return getChecksum(CHECKSUM_ALGORITHM_SHA256, data);
    }

    /**
     * Generates an unique string.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits Length of result string
     * @param seed for the string (e.g. "1,2...0,A,B...Z)
     * @return The generated unique String.
     */
    public static String getRandomKey(final int digits, final char[] seed) {
        StringBuilder sb = new StringBuilder();

        for (int ii = 0; ii < digits; ii++) {
            sb.append(seed[HelperMath.random(seed.length)]);
        }
        return sb.toString();
    }

    /**
     * Generates an unique string with default seed.
     * This is used for unique keys (e.g. for product keys).
     *
     * @param digits Length of result string
     * @return The generated unique String.
     */
    public static String getRandomKey(final int digits) {
        return getRandomKey(digits, RANDOMKEY_SEED_DEFAULT);
    }
    
    /**
     * Fill a string with a char
     * @param fillChar
     * @param fillLength
     * @return filled string
     */
    public static String fillString(final char fillChar, final int fillLength) {
        int length = fillLength;
    	final char[] chars = new char[length];
        
    	while (length > 0) {
            chars[--length] = fillChar;
        }
        
    	return new String(chars);
    }
    
    /**
     * Reverses a string
     * @param input string
     * @return reversed string
     */
    public static String reverseString(final String input) {
    	return new StringBuffer(input).reverse().toString();
    }

    /**
     * Dump a list
     * @param list
     * @return dump string
     */
    public static String dump(final List<?> list) {
        final StringBuilder sb = new StringBuilder();

        for (final Object value : list) {
            sb.append(value);
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Dump a map
     * @param map
     * @return dump string
     */
    public static String dump(final Map<?, ?> map) {
        final StringBuilder sb = new StringBuilder();

        for (final Map.Entry<?, ?> pair : map.entrySet()) {
            sb.append(pair.getKey());
            sb.append('=');
            sb.append(pair.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }

     /**
     * Dump an array
     * @param array
     * @return dump string
     */
    public static String dump(final Object[] array) {
         final StringBuilder sb = new StringBuilder();

       for (final Object value : array) {
            sb.append(value);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static String toString(final Object object) {
    	final List<String> list = new ArrayList<String>();
    	toString(object, object.getClass(), list);

    	return object.getClass().getName() + list.toString();
    }

    
    /*
     * Private methods
     */
    private static void toString(final Object object, final Class<?> clazz, final List<String> list) {
    	final Field[] fields = clazz.getDeclaredFields();
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
//    	if (clazz.getSuperclass() != null) {    		
//    		toString(object, clazz.getSuperclass(), list);
//    	}
    }

//    /**
//     * Wait for some milli seconds
//     *
//     * @param time The milli seconds to wait for.
//     */
//    public static synchronized void suspendMilliseconds(long time){
//    	long startTime = System.currentTimeMillis();
//
//    	while(System.currentTimeMillis() - time < startTime) {
//    		//let everything wait...
//    	}
//     }
}