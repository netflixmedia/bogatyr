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

package net.laubenberger.bogatyr.service.property;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Properties;

import net.laubenberger.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of the properties.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
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
	 * Returns the value of a property as {@link BigDecimal}.
	 *
	 * @param key of the property
	 * @return {@link BigDecimal} associated to the given key
	 * @since 0.9.1
	 */
	BigDecimal getNumber(String key);

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

