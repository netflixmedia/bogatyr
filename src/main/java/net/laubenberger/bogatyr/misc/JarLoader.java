/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.misc;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * Loads JAR files during runtime.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100618)
 * @since 0.9.2
 */
public class JarLoader extends URLClassLoader {
	private static final Logger log = LoggerFactory.getLogger(JarLoader.class);
	
	
	public JarLoader(final URL... urls){
		super(urls);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(urls));
	}

	/**
	 * Creates an instance of a class.
	 *
	 * @param clazz full qualified class name
	 * @return instantiated object
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @since 0.9.2
	 */
	public Object createInstance(final String clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(clazz));

		final Object result = HelperObject.createInstance(loadClass(clazz, true));

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Creates an instance of a class with parameters.
	 *
	 * @param clazz		  full qualified class name
	 * @param paramClazzes classes for the constructor
	 * @param params		 parameters for the constructor
	 * @return instantiated object
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @see Class
	 * @since 0.9.2
	 */
	public Object createInstance(final String clazz, final Class<?>[] paramClazzes, final Object[] params) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, InvocationTargetException, NoSuchMethodException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(clazz, paramClazzes, params));

		final Object result = HelperObject.createInstance(loadClass(clazz, true), paramClazzes, params);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}
