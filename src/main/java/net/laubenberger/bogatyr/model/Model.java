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

package net.laubenberger.bogatyr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import net.laubenberger.bogatyr.misc.HolderObserver;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObject;

/**
 * Defines the methods for all models.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public interface Model extends ExtendedObject, Serializable, HolderObserver {
	String MEMBER_INSTANTIATION_DATE = "instantiationDate"; //$NON-NLS-1$
	String MEMBER_TAGS = "tags"; //$NON-NLS-1$
	String METHOD_ADD_TAG = "addTag"; //$NON-NLS-1$
	String METHOD_REMOVE_TAG = "removeTag"; //$NON-NLS-1$

	/**
	 * Sets the instantiation date of the model.
	 *
	 * @param instantiationDate of the model
	 * @since 0.9.0
	 */
	void setInstantiationDate(Date instantiationDate);

	/**
	 * Returns all tags.
	 *
	 * @return {@link Map} containing all tags
	 * @since 0.9.1
	 */
	Map<String, String> getTags();

	/**
	 * Sets all tags.
	 *
	 * @param tags {@link Map} containing all tags
	 * @since 0.9.1
	 */
	void setTags(Map<String, String> tags);

	/**
	 * Returns the value of a tag for a given key.
	 *
	 * @param key for the tag
	 * @return tag value
	 * @since 0.9.1
	 */
	String getTag(String key);

	/**
	 * Adds a key and value for a tag.
	 *
	 * @param key	of the tag
	 * @param value of the tag
	 * @since 0.9.1
	 */
	void addTag(String key, String value);

	/**
	 * Removes a tag.
	 *
	 * @param key to remove the tag
	 * @since 0.9.1
	 */
	void removeTag(String key);
}