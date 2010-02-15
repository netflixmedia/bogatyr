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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.updater;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.model.Model;
import ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo;
import ch.sisprocom.bogatyr.model.misc.Manufacturer;
import ch.sisprocom.bogatyr.model.misc.Owner;
import ch.sisprocom.bogatyr.model.misc.Platform;
import ch.sisprocom.bogatyr.model.misc.Publisher;

/**
 * The interface for the document model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(DocumentImpl.XmlAdapter.class)
public interface Document extends Model {
	String MEMBER_LOCATIONS    = Document.class.getName() + ".locations"; //$NON-NLS-1$
	String MEMBER_HASHS        = Document.class.getName() + ".hashs"; //$NON-NLS-1$
    String MEMBER_NAME     	   = Document.class.getName() + ".name"; //$NON-NLS-1$
    String MEMBER_VERSION      = Document.class.getName() + ".version"; //$NON-NLS-1$
    String MEMBER_BUILD        = Document.class.getName() + ".build"; //$NON-NLS-1$
    String MEMBER_CREATED	   = Document.class.getName() + ".created"; //$NON-NLS-1$
    String MEMBER_MANUFACTURER = Document.class.getName() + ".manufacturer"; //$NON-NLS-1$
    String MEMBER_OWNER		   = Document.class.getName() + ".owner"; //$NON-NLS-1$
    String MEMBER_PUBLISHER    = Document.class.getName() + ".publisher"; //$NON-NLS-1$
    String MEMBER_UUID     	   = Document.class.getName() + ".UUID"; //$NON-NLS-1$


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
     * Returns the hash for a given {@link ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo}.
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

	String getName();
	void setName(String name);
    
    BigDecimal getVersion();
    void setVersion(BigDecimal version);

    int getBuild();
    void setBuild(int build);

    Date getCreated();
    void setCreated(Date created);
    
    Manufacturer getManufacturer();
    void setManufacturer(Manufacturer manufacturer);
    
    Owner getOwner();
    void setOwner(Owner owner);
    
    Publisher getPublisher();
    void setPublisher(Publisher publisher);
    
    UUID getUUID();
    void setUUID(UUID uuid);
}
