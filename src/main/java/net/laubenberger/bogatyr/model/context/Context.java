/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.model.context;

import java.util.Map;

import net.laubenberger.bogatyr.model.Model;


/**
 * Interface for the context in applications.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.6.0
 */
public interface Context extends Model {
	String MEMBER_DATA = Context.class.getName() + ".data"; //$NON-NLS-1$
	String METHOD_ADD_VALUE = Context.class.getName() + ".addValue()"; //$NON-NLS-1$
	String METHOD_REMOVE_VALUE = Context.class.getName() + ".removeValue()"; //$NON-NLS-1$

	/**
	 * Returns the data of the context.
	 *
	 * @return {@link Map} containing the data
	 * @since 0.9.0
	 */
	Map<Object, Object> getData();

	/**
	 * Sets the data of the context.
	 *
	 * @param data {@link Map} containing the data
	 * @since 0.9.0
	 */
	void setData(Map<Object, Object> data);

	/**
	 * Adds a key/value pair to the context.
	 *
	 * @param key	for the context
	 * @param value associated to the given key
	 * @since 0.6.0
	 */
	void addValue(Object key, Object value);

	/**
	 * Removes a key/value pair from the context.
	 *
	 * @param key for the context
	 * @since 0.6.0
	 */
	void removeValue(Object key);

	/**
	 * Returns a {@link Object} value associated to the given key from the context.
	 *
	 * @param key for the context
	 * @return Object associated to the given key
	 * @since 0.6.0
	 */
	Object getValue(Object key);

	/**
	 * Returns an object associated to the given key and {@link Class} from the context.
	 *
	 * @param key	for the context
	 * @param clazz of the object
	 * @return object associated to the given key and {@link Class}
	 * @since 0.9.1
	 */
	<T> T getValue(Object key, Class<T> clazz);
}
