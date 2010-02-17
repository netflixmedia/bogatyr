/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.model.misc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.model.Model;

/**
 * The interface for the document model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.9.1
 */
@XmlJavaTypeAdapter(DocumentImpl.XmlAdapter.class)
public interface Document extends Model {
    String MEMBER_NAME     	   = "name"; //$NON-NLS-1$
    String MEMBER_VERSION      = "version"; //$NON-NLS-1$
    String MEMBER_BUILD        = "build"; //$NON-NLS-1$
    String MEMBER_CREATED	   = "created"; //$NON-NLS-1$
    String MEMBER_MANUFACTURER = "manufacturer"; //$NON-NLS-1$
    String MEMBER_OWNER		   = "owner"; //$NON-NLS-1$
    String MEMBER_PUBLISHER    = "publisher"; //$NON-NLS-1$
    String MEMBER_UUID     	   = "UUID"; //$NON-NLS-1$

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
