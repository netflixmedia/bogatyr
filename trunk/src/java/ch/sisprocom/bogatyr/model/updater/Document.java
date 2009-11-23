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
package ch.sisprocom.bogatyr.model.updater;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.sisprocom.bogatyr.model.Model;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.model.misc.Platform;

/**
 * The interface for the document model.
 * 
 * @author Stefan Laubenberger
 * @version 20091122
 */
@XmlRootElement
public interface Document extends Model {
	String METHOD_SET_LOCATIONS    	   = "setLocations"; //$NON-NLS-1$
	String METHOD_SET_HASHS      	   = "setHashs"; //$NON-NLS-1$
    String METHOD_SET_NAME      	   = "setName"; //$NON-NLS-1$
    String METHOD_SET_VERSION   	   = "setVersion"; //$NON-NLS-1$
    String METHOD_SET_BUILD     	   = "setBuild"; //$NON-NLS-1$
    String METHOD_SET_CREATED		   = "setCreated"; //$NON-NLS-1$
    String METHOD_SET_MANUFACTURER     = "setManufacturer"; //$NON-NLS-1$
    String METHOD_SET_MANUFACTURER_URL = "setManufacturerURL"; //$NON-NLS-1$
    String METHOD_SET_UUID     		   = "setUUID"; //$NON-NLS-1$


    /**
     * Returns all locations.
     *
     * @retuns {@link Map} containing all locations
     * @since 0.9.0
     */
    @XmlElement
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
     * @retuns {@link Map} containing all hashs
     * @since 0.9.0
     */
    @XmlElement
    Map<HashCode, String> getHashs();

    /**
     * Sets all hashs.
     *
     * @param hashs {@link Map} containing all hashs
     * @since 0.9.0
     */
    void setHashs(Map<HashCode, String> hashs);

    /**
     * Returns the hash for a given {@link HashCode}.
     * 
     * @param hashCode for the hash
     * @return hash 
     * @since 0.9.0
     */	
	String getHash(HashCode hashCode);

	/**
     * Returns the default hash (generated with SHA256).
     * 
     * @return default hash 
     * @since 0.9.0
     */	
	String getHash();

	@XmlElement
	String getName();
	void setName(String name);
    
	@XmlElement
    BigDecimal getVersion();
    void setVersion(BigDecimal version);

	@XmlElement
    Integer getBuild();
    void setBuild(int build);

	@XmlElement
    Date getCreated();
    void setCreated(Date created);
    
	@XmlElement
    String getManufacturer();
    void setManufacturer(String manufacturer);

	@XmlElement
    URL getManufacturerURL();
    void setManufacturerURL(URL url);

	@XmlElement
    UUID getUUID();
    void setUUID(UUID uuid);
}
