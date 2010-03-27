/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.service.property;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Properties;

import ch.customcode.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of the properties.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.6.0
 */
public interface Property extends Service {
	/**
     * Returns the properties {@link Properties}.
     * 
     * @return {@link Properties}
     * @see Properties
     * @since 0.6.0
     */
	Properties getProperties();
    
	/**
     * Returns the value of a property as {@link String}.
     * 
     * @param key of the property
     * @return {@link String} associated to the given key
     * @since 0.6.0
     */
    String getValue(String key);

	/**
     * Returns the value of a property as {@link Boolean}.
     * 
     * @param key of the property
     * @return {@link Boolean} associated to the given key
     * @since 0.6.0 
     */
    Boolean getBoolean(String key);
    
	/**
     * Returns the value of a property as {@link Double}.
     * 
     * @param key of the property
     * @return {@link Double} associated to the given key
     * @since 0.7.0 
     */
	Double getDouble(String key);
	
	/**
     * Returns the value of a property as {@link Integer}.
     * 
     * @param key of the property
     * @return {@link Integer} associated to the given key 
     * @since 0.7.0
     */
	Integer getInteger(String key);
	
	/**
     * Returns the value of a property as {@link Float}.
     * 
     * @param key of the property
     * @return {@link Float} associated to the given key
     * @since 0.7.0 
     */
	Float getFloat(String key);
	
	/**
     * Returns the value of a property as {@link Byte}.
     * 
     * @param key of the property
     * @return {@link Byte} associated to the given key
     * @since 0.7.0 
     */
	Byte getByte(String key);
	
	/**
     * Returns the value of a property as {@link Long}.
     * 
     * @param key of the property
     * @return {@link Long} associated to the given key
     * @since 0.7.0 
     */
	Long getLong(String key);
	
	/**
     * Returns the value of a property as {@link Short}.
     * 
     * @param key of the property
     * @return {@link Short} associated to the given key
     * @since 0.7.0 
     */	
	Short getShort(String key);
	
	/**
     * Returns the value of a property as {@link BigInteger}.
     * 
     * @param key of the property
     * @return {@link BigInteger} associated to the given key
     * @since 0.7.0 
     */	
	BigInteger getBigInteger(String key);
	
	/**
     * Returns the value of a property as {@link BigDecimal}.
     * 
     * @param key of the property
     * @return {@link BigDecimal} associated to the given key
     * @since 0.7.0 
     */	
	BigDecimal getBigDecimal(String key);
	
	/**
     * Returns the value of a property as {@link File}.
     * 
     * @param key for the property
     * @return {@link File} associated to the given key
     * @since 0.9.0 
     */	
	File getFile(String key);
	
	/**
     * Returns the value of a property as {@link URL}.
     * 
     * @param key for the property
     * @return {@link URL} associated to the given key
     * @since 0.9.0 
     */	
	URL getURL(String key);
}   

