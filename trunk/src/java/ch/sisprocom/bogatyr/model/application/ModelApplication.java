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
package ch.sisprocom.bogatyr.model.application;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.model.Model;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.model.misc.Manufacturer;
import ch.sisprocom.bogatyr.service.localizer.Localizer;
import ch.sisprocom.bogatyr.service.property.Property;

/**
 * The interface for the application model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(ModelApplicationImpl.XmlAdapter.class)
public interface ModelApplication extends Model {
	String MEMBER_HASHS      	   = ModelApplication.class.getName() + ".hashs"; //$NON-NLS-1$
	String MEMBER_NAME      	   = ModelApplication.class.getName() + ".name"; //$NON-NLS-1$
    String MEMBER_VERSION   	   = ModelApplication.class.getName() + ".version"; //$NON-NLS-1$
    String MEMBER_BUILD     	   = ModelApplication.class.getName() + ".build"; //$NON-NLS-1$
    String MEMBER_CREATED		   = ModelApplication.class.getName() + ".created"; //$NON-NLS-1$
    String MEMBER_MANUFACTURER     = ModelApplication.class.getName() + ".manufacturer"; //$NON-NLS-1$
    String MEMBER_UUID     		   = ModelApplication.class.getName() + ".UUID"; //$NON-NLS-1$
    String MEMBER_DEBUG     	   = ModelApplication.class.getName() + ".debug"; //$NON-NLS-1$
    String MEMBER_PROPERTY  	   = ModelApplication.class.getName() + ".property"; //$NON-NLS-1$
    String MEMBER_LOCALIZER 	   = ModelApplication.class.getName() + ".localizer"; //$NON-NLS-1$
    String METHOD_ADD_HASH         = ModelApplication.class.getName() + ".addHash()"; //$NON-NLS-1$
    String METHOD_REMOVE_HASH	   = ModelApplication.class.getName() + ".removeHash()"; //$NON-NLS-1$
	
    /**
     * Returns all hashs.
     *
     * @return {@link Map} containing all hashs
     * @since 0.9.0
     */
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
	
	void addHash(HashCode hashCode, String hash);

	void removeHash(HashCode hashCode);
	
	String getName();
	void setName(String name);
    
    BigDecimal getVersion();
    void setVersion(BigDecimal version);

    Integer getBuild();
    void setBuild(int build);

    Date getCreated();
    void setCreated(Date created);
    
    Manufacturer getManufacturer();
    void setManufacturer(Manufacturer manufacturer);

    UUID getUUID();
    void setUUID(UUID uuid);

    Boolean isDebug();
    void setDebug(boolean isDebug);

    void setProperty(Property property);
    Property getProperty();

    void setLocalizer(Localizer localizer);
    Localizer getLocalizer(); 
}
