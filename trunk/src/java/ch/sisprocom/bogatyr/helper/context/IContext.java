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
package ch.sisprocom.bogatyr.helper.context;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;




/**
 * Interface for the context in applications.
 * 
 * @author Stefan Laubenberger
 * @version 20090507
 */
public interface IContext {
	
	/**
     * Adds a key/value pair to the context.
     * 
     * @param key for the context
     * @param value associated to the given key 
     */	
	void addValue(Object key, Object value);
	
	/**
     * Removes a key/value pair from the context.
     * 
     * @param key for the context
     */	
	void removeValue(Object key);
	
	/**
     * Returns a {@link Object} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Object associated to the given key 
     */	
	Object getValue(Object key);
	
	/**
     * Returns a {@link String} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return String associated to the given key 
     */	
	String getStringValue(Object key);
	
	/**
     * Returns a {@link Boolean} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Boolean associated to the given key 
     */	
	Boolean getBooleanValue(Object key);
	
	/**
     * Returns a {@link Double} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Double associated to the given key 
     */	
	Double getDoubleValue(final Object key);
	
	/**
     * Returns an {@link Integer} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Integer associated to the given key 
     */	
	Integer getIntegerValue(final Object key);
	
	/**
     * Returns a {@link Float} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Float associated to the given key 
     */	
	Float getFloatValue(final Object key);
	
	/**
     * Returns a {@link Byte} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Byte associated to the given key 
     */	
	public Byte getByteValue(final Object key);
	
	/**
     * Returns a {@link Long} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Long associated to the given key 
     */	
	public Long getLongValue(final Object key);

	/**
     * Returns a {@link Short} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Short associated to the given key 
     */	
	public Short getShortValue(final Object key);
	
	/**
     * Returns a {@link BigInteger} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return BigInteger associated to the given key 
     */	
	public BigInteger getBigIntegerValue(final Object key);
	
	/**
     * Returns a {@link BigDecimal} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return BigDecimal associated to the given key 
     */	
	public BigDecimal getBigDecimalValue(final Object key);

	/**
     * Returns a {@link Date} value associated to the given key from the context.
     * 
     * @param key for the context
     * @return Date associated to the given key 
     */	
	public Date getDateValue(final Object key);
}
