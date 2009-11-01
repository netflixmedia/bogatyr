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
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;


/**
 * This is a helper class for objects.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091101)
 * @since 0.7.0
 */
public abstract class HelperObject {
	
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
	 * @see Class
	 * @since 0.7.0
     */
	public static <T> T createInstance(final Class<T> clazz) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException { //$JUnit$
		if (null == clazz) {
			throw new IllegalArgumentException("clazz is null!"); //$NON-NLS-1$
		}
		
		return clazz.getConstructor(HelperArray.EMPTY_ARRAY_CLASS).newInstance();
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
 	 * @see Class
	 * @since 0.7.0
     */
	public static <T> T createInstance(final Class<T> clazz, final Class<?>[] paramClazzes, final Object[] params) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException { //$JUnit$
		if (null == clazz) {
			throw new IllegalArgumentException("clazz is null!"); //$NON-NLS-1$
		}
		if (null == paramClazzes) {
			throw new IllegalArgumentException("paramClazzes is null!"); //$NON-NLS-1$
		}
		if (null == params) {
			throw new IllegalArgumentException("params is null!"); //$NON-NLS-1$
		}
		
		return clazz.getConstructor(paramClazzes).newInstance(params);
	}
	
	/**
	 * Serialize an {@link Object} into a byte-array.
	 * 
	 * @param object to convert into a byte-array.
	 * @return object as byte-array
	 * @throws IOException
	 * @see Serializable
	 * @since 0.7.0
	 */
	public static byte[] serialize(final Serializable object) throws IOException { //$JUnit$
		if (null == object) {
			throw new IllegalArgumentException("obj is null!"); //$NON-NLS-1$
		}	

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();

            return baos.toByteArray();
        } finally {
        	if (oos != null) {
        		oos.close();	
        	}
//                bos.close();
        }
	}
	
	/**
	 * Deserialize a given byte-array into an {@link Object}.
	 * 
	 * @param data byte-array to convert into an {@link Object}
	 * @return {@link Object} from given byte-array
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @since 0.7.0
	 */
	public static Object deserialize(final byte[] data) throws IOException, ClassNotFoundException { //$JUnit$
		if (!HelperArray.isValid(data)) {
			throw new IllegalArgumentException("data is null or empty!"); //$NON-NLS-1$
		}
		
		ObjectInputStream ois = null;
		final Object obj;
		
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            obj = ois.readObject();
        } finally {
        	if (ois != null) {
        		ois.close();
        	}
        }
        return obj;
	}

	/**
     * Checks if a {@link Class} implements the given method name.
     *
     * @param clazz for searching
     * @param methodName to check
     * @return true/false
     * @see Class
     * @since 0.7.0
     */
    public static boolean isMethodAvailable(final Class<?> clazz, final String methodName) {
		if (null == clazz) {
			throw new IllegalArgumentException("clazz is null!"); //$NON-NLS-1$
		}

    	final Method[] methods = clazz.getMethods();

        for (final Method method : methods) {
            if (method.getName().equals(methodName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Generic toString() method for {@link Object} and different purposes.
     * 
     * @param object to dump
     * @return dumped object string
     * @since 0.7.0
     */
    public static String toString(final Object object) {
    	final Collection<String> list = new ArrayList<String>();
    	toString(object, list);

    	return object.getClass().getName() + list.toString();
    }


	/**
     * Compare if two objects are equals.
     * 
     * @param objectA first object to compare
     * @param objectB second object to compare
     * @return true/false
     * @since 0.8.0
     */	
	public static <T> boolean isEquals(final T objectA, final T objectB) { //$JUnit$
		return !((null == objectB && null != objectA) || (null != objectB && !objectB.equals(objectA)));
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