/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;


/**
 * This is a general helper class for general problems
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public abstract class GeneralHelper {

	public static final String CHECKSUM_ALGORITHM_MD5  = "MD5"; //$NON-NLS-1$
	public static final String CHECKSUM_ALGORITHM_SHA1 = "SHA-1"; //$NON-NLS-1$
	
	/**
     * Checks if a long is greater than 0
     * @param value long-Value to check
     */	
	public static boolean isGreaterThanZero(long value) {
		if (value <= 0L) {
			return false;
		}
		return true;
	}

	/**
     * Convert a double to an int-Value.<br>
     * Rounds the double value with {@link Math#round(double)}.
     * @param value double-Value
     * @return int-Value
     * @see Math
     */	
	public static int convertDoubleToInt(double value) {
		return Integer.parseInt(Double.toString(Math.round(value))); 
	}
	
	/**
     * Creates an object with parameters via Reflection-API
     * @param className Full qualified class name
     * @param parameter Parameter-array
     * @return Rueckgabewert: instanciated object
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
     */	
	@SuppressWarnings("unchecked")
	public static Object createObject(String className, Object[] parameter) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		Object object = null;

		Class[] stringArgsClass = new Class[] {String.class};
			
		Class classDefinition = Class.forName(className);
			
		if (parameter == null) {
			object = classDefinition.newInstance();
		} else {
			Constructor classConstructor = classDefinition.getConstructor(stringArgsClass);
			object = classConstructor.newInstance(parameter);
		}
		return object;
	}

	/**
     * Checks if a String is valid.
     * @param arg String to check
     */	
	public static boolean isValidString(String arg) {
		if (!isValidObject(arg) || arg.trim().equals("")) { //$NON-NLS-1$
			return false;
		}
		return true;
	}
	
	/**
     * Checks if a int is not 0.
     * @param arg int to check
     */	
	public static boolean isValidInt(int arg) {
		if (arg == 0) {
			return false;
		}
		return true;
	}

	/**
     * Checks if a long is not 0.
     * @param arg long to check
     */	
	public static boolean isValidLong(long arg) {
		if (arg == 0) {
			return false;
		}
		return true;
	}

	/**
     * Checks if a {@link Object} is not null.
     * @param arg Object to check
     */	
	public static boolean isValidObject(Object arg) {
		if (arg == null) {
			return false;
		}
		return true;
	}

	/**
     * Checks if a array is valid.
     * @param arg Object array to check
     */	
	public static boolean isValidArray(Object[] arg) {
		if (!isValidObject(arg) || arg.length == 0) {
			return false;
		}
		return true;
	}

	/**
     * Checks if a {@link ArrayList} is valid.
     * @param arg ArrayList to check
     */	
	public static boolean isValidArrayList(ArrayList arg) {
		if (!isValidObject(arg) || arg.size() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Converts an {@link Object} into a byte-array.<br>
	 * It uses the {@link ByteArrayOutputStream} and the {@link ObjectOutputStream}.
	 * 
	 * @param obj The Object to convert into a byte-array.
	 * @return The given Object as byte-array.
	 * @throws IOException
	 * @see ByteArrayOutputStream
	 * @see ObjectOutputStream
	 */
	public static byte[] getBytes(Object obj) throws IOException {
		byte[] data = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos;
		
	    if (obj != null) {
			oos = new ObjectOutputStream(bos);
		    oos.writeObject(obj);
		    oos.flush();

		    data = bos.toByteArray();
		    
		    oos.close();
		    bos.close();
	    }
		return data;
	}
	
	/**
	 * Converts a given byte-array into a valid {@link Object}.<br>
	 * It uses the {@link ByteArrayInputStream} and the {@link ObjectInputStream}.
	 * 
	 * @param bytes The byte-array to convert into an {@link Object}.
	 * @return Returns a valid Object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @see ByteArrayInputStream
	 * @see ObjectInputStream
	 */
	public static Object getObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object object = null;
		 
		if (bytes.length > 0) {
			object = new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
		}
		return object;
	}
	
	/**
	 * Appends a given byte-array to an existing byte-array.
	 * 
	 * @param a The first array.
	 * @param b The second array.
	 * @return The array a & b as one new byte-array.
	 */
    public static byte[] appendByteArray(byte[] a, byte[] b) {
    	byte[] z = new byte[a.length + b.length];
        System.arraycopy(a, 0, z, 0, a.length);
        System.arraycopy(b, 0, z, a.length, b.length);
        return z;
    }
    
    /**
     * Generates an unique String.<br>
     * This is used for unique keys.<br>
     * For example this is used for {@link Map} to store values in it.
     * 
     * @param algo The algorithm to use.
     * @param data The data to encrypt.
     * @return The generated unique String.
     * @throws NoSuchAlgorithmException
     */
    public static String generateChecksum(String algo, String data) throws NoSuchAlgorithmException {
		MessageDigest algorithm = MessageDigest.getInstance(algo);

		byte[] input = data.getBytes();
		algorithm.reset();
		algorithm.update(input);
		byte[] messageDigest = algorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for (int ii = 0; ii < messageDigest.length; ii++) {
			String hex = Integer.toHexString(0xFF & messageDigest[ii]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

    /**
     * Generates an unique String with MD5.<br>
     *
     * @param data The data to encrypt.
     * @return The generated unique String.
     * @see #generateChecksum(String, String)
     */
    public static String generateMd5(String data) {
    	try {
			return generateChecksum(CHECKSUM_ALGORITHM_MD5, data);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
    }

    /**
     * Generates an unique String with Sha1.<br>
     *
     * @param data The data to encrypt.
     * @return The generated unique String.
     * @see #generateChecksum(String, String)
     */
    public static String generateSha1(String data) {
    	try {
			return generateChecksum(CHECKSUM_ALGORITHM_SHA1, data);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
    }
    
    /**
     * wait for some milli seconds
     * @param time The milli seconds to wait for.
     */
    public static synchronized void suspendMilliseconds(long time){
    	long startTime = System.currentTimeMillis();
    	
    	while(System.currentTimeMillis() - time < startTime) {
    		//let everything wait...
    	}
    }
}