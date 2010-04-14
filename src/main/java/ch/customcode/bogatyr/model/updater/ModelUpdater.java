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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.model.updater;

import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.customcode.bogatyr.model.crypto.HashCodeAlgo;
import ch.customcode.bogatyr.model.misc.Document;
import ch.customcode.bogatyr.model.misc.Platform;

/**
 * The interface for the updater model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(ModelUpdaterImpl.XmlAdapter.class)
public interface ModelUpdater extends Document {
	String MEMBER_LOCATIONS    = "locations"; //$NON-NLS-1$
	String MEMBER_HASHS        = "hashs"; //$NON-NLS-1$


    /**
     * Returns all locations.
     *
     * @return {@link Map} containing all locations
     * @since 0.9.0
     */
    Map<Platform, String> getLocations();

    /**
     * Sets all locations.
     *
     * @param locations {@link Map} containing all locations
     * @since 0.9.0
     */
    void setLocations(Map<Platform, String> locations);

    /**
     * Returns the location for a given {@link Platform}.
     * 
     * @param platform for the location
     * @return location 
     * @since 0.9.0
     */	
	String getLocation(Platform platform);

	/**
     * Returns the default location (ANY platform).
     * 
     * @return default location 
     * @since 0.9.0
     */	
	String getLocation();

	/**
     * Returns all hashs.
     *
     * @return {@link Map} containing all hashs
     * @since 0.9.0
     */
    Map<HashCodeAlgo, String> getHashs();

    /**
     * Sets all hashs.
     *
     * @param hashs {@link Map} containing all hashs
     * @since 0.9.0
     */
    void setHashs(Map<HashCodeAlgo, String> hashs);

    /**
     * Returns the hash for a given {@link HashCodeAlgo}.
     * 
     * @param hashCodeAlgo for the hash
     * @return hash 
     * @since 0.9.0
     */	
	String getHash(HashCodeAlgo hashCodeAlgo);

	/**
     * Returns the default hash (generated with SHA256).
     * 
     * @return default hash 
     * @since 0.9.0
     */	
	String getHash();
}
