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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This is a helper class for objects.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100216)
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
	 * @see Class
	 * @since 0.7.0
     */
	public static <T> T createInstance(final Class<T> clazz) throws SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException { //$JUnit$
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
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
 	 * @see Class
	 * @since 0.7.0
     */
	public static <T> T createInstance(final Class<T> clazz, final Class<?>[] paramClazzes, final Object[] params) throws SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException { //$JUnit$
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
		}
		if (null == paramClazzes) {
			throw new RuntimeExceptionIsNull("paramClazzes"); //$NON-NLS-1$
		}
		if (null == params) {
			throw new RuntimeExceptionIsNull("params"); //$NON-NLS-1$
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
			throw new RuntimeExceptionIsNull("object"); //$NON-NLS-1$
		}	

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();

            return baos.toByteArray();
        } finally {
        	if (null != oos) {
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
			throw new RuntimeExceptionIsNullOrEmpty("data"); //$NON-NLS-1$
		}
		
		ObjectInputStream ois = null;
		
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            return ois.readObject();
        } finally {
        	if (null != ois) {
        		ois.close();
        	}
        }
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
    public static boolean isMethodAvailable(final Class<?> clazz, final String methodName) { //$JUnit$
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
		}
		if (null == methodName) {
			throw new RuntimeExceptionIsNull("methodName"); //$NON-NLS-1$
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
		if (null == object) {
			throw new RuntimeExceptionIsNull("object"); //$NON-NLS-1$
		}

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
	public static boolean isEquals(final Object objectA, final Object objectB) { //$JUnit$
		return !((null == objectB && null != objectA) || (null != objectB && !objectB.equals(objectA)));
	}
	
	/**
     * Clones an object via serialize (deep clone).
     * 
     * @param original to clone
     * @return cloned object
     * @since 0.9.0
     */	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(final T original) throws IOException, ClassNotFoundException {
		if (null == original) {
			throw new RuntimeExceptionIsNull("original"); //$NON-NLS-1$
		}

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CloneOutput co = null;
        ObjectInputStream ois = null;

        try {
            co = new CloneOutput(baos);
		    co.writeObject(original);

			final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new CloneInput(bais, co);

            return (T) ois.readObject();
        } finally {
            if (null != co) {
                co.close();
            }
            if (null != ois) {
                ois.close();
            }
        }
    }
	
    /**
     * Quotes a given {@link Object} for different purposes (e.g. logging).
     * 
     * @param object to quote
     * @return quoted object string
     * @since 0.9.1
     */
    public static String quote(final Object object) {
		if (null == object) {
			throw new RuntimeExceptionIsNull("object"); //$NON-NLS-1$
		}

		return HelperString.SINGLE_QUOTE + String.valueOf(object) + HelperString.SINGLE_QUOTE;
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
    
    
    /*
     * Inner classes
     */
    private static class CloneOutput extends ObjectOutputStream {
    	final Queue<Class<?>> classQueue = new LinkedList<Class<?>>();

    	CloneOutput(final OutputStream os) throws IOException {
    		super(os);
    	}

		@Override
		protected void annotateClass(final Class<?> c) {
		    classQueue.add(c);
		}
	
		@Override
		protected void annotateProxyClass(final Class<?> c) {
		    classQueue.add(c);
		}
    }

    private static class CloneInput extends ObjectInputStream {
    	private final CloneOutput output;

		CloneInput(final InputStream is, final CloneOutput output) throws IOException {
		    super(is);
		    this.output = output;
		}

    	@Override
    	protected Class<?> resolveClass(final ObjectStreamClass osc) throws IOException, ClassNotFoundException {
		    final Class<?> c = output.classQueue.poll();
		    final String expected = osc.getName();
		    final String found = (null == c) ? null : c.getName();
		    if (!expected.equals(found)) {
		    	throw new InvalidClassException("Classes desynchronized - found: " + found + " when expecting: " + expected); //$NON-NLS-1$ //$NON-NLS-2$
		    }
		    return c;
    	}

    	@Override
    	protected Class<?> resolveProxyClass(final String[] interfaceNames) throws IOException, ClassNotFoundException {
    	    return output.classQueue.poll();
    	}
    }
}