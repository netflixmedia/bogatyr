/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.property;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Properties;


/**
 * Defines the methods for the implementation of the properties.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.6.0
 */
public interface Property {
	/**
     * Returns the properties {@link Properties}.
     * 
     * @return Properties object
     * @see Properties
     * @since 0.6.0
     */
	Properties getProperties();
    
	/**
     * Returns the value of a property as {@link String}.
     * 
     * @param key of the property
     * @return String associated to the given key
     * @since 0.6.0
     */
    String getValue(String key);

	/**
     * Returns the value of a property as {@link Boolean}.
     * 
     * @param key of the property
     * @return Boolean associated to the given key
     * @since 0.6.0 
     */
    Boolean getBooleanValue(String key);
    
	/**
     * Returns the value of a property as {@link Double}.
     * 
     * @param key of the property
     * @return Double associated to the given key
     * @since 0.7.0 
     */
	Double getDoubleValue(String key);
	
	/**
     * Returns the value of a property as {@link Integer}.
     * 
     * @param key of the property
     * @return Integer associated to the given key 
     * @since 0.7.0
     */
	Integer getIntegerValue(String key);
	
	/**
     * Returns the value of a property as {@link Float}.
     * 
     * @param key of the property
     * @return Float associated to the given key
     * @since 0.7.0 
     */
	Float getFloatValue(String key);
	
	/**
     * Returns the value of a property as {@link Byte}.
     * 
     * @param key of the property
     * @return Byte associated to the given key
     * @since 0.7.0 
     */
	Byte getByteValue(String key);
	
	/**
     * Returns the value of a property as {@link Long}.
     * 
     * @param key of the property
     * @return Long associated to the given key
     * @since 0.7.0 
     */
	Long getLongValue(String key);
	
	/**
     * Returns the value of a property as {@link Short}.
     * 
     * @param key of the property
     * @return Short associated to the given key
     * @since 0.7.0 
     */	
	Short getShortValue(String key);
	
	/**
     * Returns the value of a property as {@link BigInteger}.
     * 
     * @param key of the property
     * @return BigInteger associated to the given key
     * @since 0.7.0 
     */	
	BigInteger getBigIntegerValue(String key);
	
	/**
     * Returns the value of a property as {@link BigDecimal}.
     * 
     * @param key of the property
     * @return BigDecimal associated to the given key
     * @since 0.7.0 
     */	
	BigDecimal getBigDecimalValue(String key);
}   

