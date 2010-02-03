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
package ch.sisprocom.bogatyr.model.context;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import ch.sisprocom.bogatyr.model.Model;


/**
 * Interface for the context in applications.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100202)
 * @since 0.6.0
 */
public interface Context extends Model {
    String MEMBER_DATA     	   = Context.class.getName() + ".data"; //$NON-NLS-1$
    String METHOD_ADD_VALUE    = Context.class.getName() + ".addValue()"; //$NON-NLS-1$
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
     * @param key for the context
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
     * Returns a {@link String} associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link String} associated to the given key
     * @since 0.6.0 
     */	
	String getString(Object key);
	
	/**
     * Returns a {@link Boolean} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Boolean} associated to the given key
     * @since 0.7.0 
     */	
	Boolean getBoolean(Object key);
	
	/**
     * Returns a {@link Double} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Double} associated to the given key
     * @since 0.7.0 
     */	
	Double getDouble(Object key);
	
	/**
     * Returns an {@link Integer} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Integer} associated to the given key
     * @since 0.7.0 
     */	
	Integer getInteger(Object key);
	
	/**
     * Returns a {@link Float} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Float} associated to the given key
     * @since 0.7.0 
     */	
	Float getFloat(Object key);
	
	/**
     * Returns a {@link Byte} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Byte} associated to the given key
     * @since 0.7.0 
     */	
	Byte getByte(Object key);
	
	/**
     * Returns a {@link Long} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Long} associated to the given key
     * @since 0.7.0 
     */	
	Long getLong(Object key);

	/**
     * Returns a {@link Short} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Short} associated to the given key
     * @since 0.7.0 
     */	
	Short getShort(Object key);
	
	/**
     * Returns a {@link BigInteger} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link BigInteger} associated to the given key
     * @since 0.7.0 
     */	
	BigInteger getBigInteger(Object key);
	
	/**
     * Returns a {@link BigDecimal} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link BigDecimal} associated to the given key
     * @since 0.7.0 
     */	
	BigDecimal getBigDecimal(Object key);

	/**
     * Returns a {@link Date} associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link Date} associated to the given key
     * @since 0.7.0 
     */	
	Date getDate(Object key);
	
	/**
     * Returns a {@link File} associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link File} associated to the given key
     * @since 0.9.0 
     */	
	File getFile(Object key);
	
	/**
     * Returns a {@link URL} associated to the given key from the context.
     * 
     * @param key for the context
     * @return {@link URL} associated to the given key
     * @since 0.9.0 
     */	
	URL getURL(Object key);
}
