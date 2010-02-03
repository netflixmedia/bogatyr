/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.service.localizer;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Locale;

import javax.swing.KeyStroke;

import ch.sisprocom.bogatyr.misc.HolderListener;
import ch.sisprocom.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of the localizer for different languages (i18n standard).
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.6.0
 */
public interface Localizer extends Service, HolderListener<ListenerLocale> {
	/**
	 * Returns the current locale {@link Locale} of the localizer.
	 * 
	 * @return current {@link Locale} of the localizer
	 * @see Locale
	 * @since 0.6.0
	 */
	Locale getLocale();
	
	/**
	 * Sets the current locale {@link Locale} of the localizer.
	 * 
	 * @param locale of the localizer
	 * @see Locale
	 * @since 0.6.0
	 */
	void setLocale(Locale locale);
	
	/**
	 * Returns the value for a key.
	 *
     * @param key for the value
	 * @return value for the key
	 * @since 0.6.0
	 */
	String getValue(String key);
	
	/**
     * Returns the value of a key as {@link Boolean}.
     * 
     * @param key
     * @return {@link Boolean} associated to the given key
     * @since 0.9.0 
     */
    Boolean getBoolean(String key);
    
	/**
     * Returns the value of a key as {@link Double}.
     * 
     * @param key
     * @return {@link Double} associated to the given key
     * @since 0.9.0 
     */
	Double getDouble(String key);
	
	/**
     * Returns the value of a key as {@link Integer}.
     * 
     * @param key
     * @return {@link Integer} associated to the given key 
     * @since 0.9.0
     */
	Integer getInteger(String key);
	
	/**
     * Returns the value of a key as {@link Float}.
     * 
     * @param key
     * @return {@link Float} associated to the given key
     * @since 0.9.0 
     */
	Float getFloat(String key);
	
	/**
     * Returns the value of a key as {@link Byte}.
     * 
     * @param key
     * @return {@link Byte} associated to the given key
     * @since 0.9.0 
     */
	Byte getByte(String key);
	
	/**
     * Returns the value of a key as {@link Long}.
     * 
     * @param key
     * @return {@link Long} associated to the given key
     * @since 0.9.0 
     */
	Long getLong(String key);
	
	/**
     * Returns the value of a key as {@link Short}.
     * 
     * @param key
     * @return {@link Short} associated to the given key
     * @since 0.9.0 
     */	
	Short getShort(String key);
	
	/**
     * Returns the value of a key as {@link BigInteger}.
     * 
     * @param key
     * @return {@link BigInteger} associated to the given key
     * @since 0.9.0 
     */	
	BigInteger getBigInteger(String key);
	
	/**
     * Returns the value of a key as {@link BigDecimal}.
     * 
     * @param key
     * @return {@link BigDecimal} associated to the given key
     * @since 0.9.0 
     */	
	BigDecimal getBigDecimal(String key);

	/**
	 * Returns the accelerator ({@link KeyStroke}) for a key.
	 *
     * @param key for the accelerator
     * @return accelerator ({@link KeyStroke}) for the key
     * @see KeyStroke
     * @since 0.6.0
	 */
	KeyStroke getAccelerator(String key);
	
	/**
	 * Returns the mnemonic for a key.
	 *
     * @param key for the mnemonic
	 * @return mnemonic for the key
	 * @since 0.6.0
	 */
	int getMnemonic(String key);
	
	/**
	 * Returns the tooltip for a key.
	 *
     * @param key for the tooltip
	 * @return tooltip for the key
	 * @since 0.6.0
	 */
	String getTooltip(String key);
	
	/**
     * Returns the {@link File} for a key.
     * 
     * @param key
     * @return {@link File} associated to the given key
     * @since 0.9.0 
     */	
	File getFile(String key);
	
	/**
     * Returns the {@link URL} for a key.
     * 
     * @param key
     * @return {@link URL} associated to the given key
     * @since 0.9.0 
     */	
	URL getURL(String key);

}
