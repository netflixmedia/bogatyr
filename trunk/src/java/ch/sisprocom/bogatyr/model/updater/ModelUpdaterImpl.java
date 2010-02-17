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
package ch.sisprocom.bogatyr.model.updater;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.misc.xml.adapter.MapAdapterHashCode;
import ch.sisprocom.bogatyr.misc.xml.adapter.MapAdapterPlatform;
import ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo;
import ch.sisprocom.bogatyr.model.misc.DocumentImpl;
import ch.sisprocom.bogatyr.model.misc.Manufacturer;
import ch.sisprocom.bogatyr.model.misc.Owner;
import ch.sisprocom.bogatyr.model.misc.Platform;
import ch.sisprocom.bogatyr.model.misc.Publisher;


/**
 * The implementation of the updater model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.9.0
 */
@XmlRootElement(name = "modelDocument")
@XmlType(propOrder={"locations", "hashs"})
public class ModelUpdaterImpl extends DocumentImpl implements ModelUpdater {
	private static final long serialVersionUID = -2826684498598090349L;

	private Map<Platform, String> mapLocation = new HashMap<Platform, String>();
	private Map<HashCodeAlgo, String> mapHash = new HashMap<HashCodeAlgo, String>();

    public ModelUpdaterImpl() {
        super();
    }

	public ModelUpdaterImpl(final String name, final BigDecimal version, final int build,
			final Date created, final Manufacturer manufacturer, final Owner owner,
			final Publisher publisher, final UUID uuid, final Map<Platform, String> mapLocation, final Map<HashCodeAlgo, String> mapHash) {
		super(name, version, build, created, manufacturer, owner, publisher, uuid);
		this.mapLocation = mapLocation;
		this.mapHash = mapHash;
	}


	/*
     * Overridden methods
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((null == mapHash) ? 0 : mapHash.hashCode());
		result = prime * result
				+ ((null == mapLocation) ? 0 : mapLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
		final ModelUpdaterImpl other = (ModelUpdaterImpl) obj;
		if (null == mapHash) {
            if (null != other.mapHash) {
                return false;
            }
		} else if (!mapHash.equals(other.mapHash)) {
            return false;
        }
		if (null == mapLocation) {
            if (null != other.mapLocation) {
                return false;
            }
		} else if (!mapLocation.equals(other.mapLocation)) {
            return false;
        }
		return true;
	}


	/*
     * Implemented methods
     */
	@Override
	public String getLocation() {
		return getLocation(Platform.ANY);
	}

	@Override
	public String getLocation(final Platform platform) {
		return mapLocation.get(platform);
	}

    @Override
    @XmlElement
    @XmlJavaTypeAdapter(MapAdapterPlatform.class)
	public Map<Platform, String> getLocations() {
		return mapLocation;
	}
    
	@Override
	public void setLocations(final Map<Platform, String> locations) {
        if (!HelperObject.isEquals(locations, mapLocation)) {
    		mapLocation = locations;
            setChanged();
            notifyObservers(MEMBER_LOCATIONS);
        }
	}

	@Override
	public String getHash() {
		return getHash(HashCodeAlgo.SHA256);
	}
	
    @Override
	public String getHash(final HashCodeAlgo hashCodeAlgo) {
		return mapHash.get(hashCodeAlgo);
	}
    
    @Override
    @XmlElement
    @XmlJavaTypeAdapter(MapAdapterHashCode.class)
	public Map<HashCodeAlgo, String> getHashs() {
		return mapHash;
	}

	@Override
	public void setHashs(final Map<HashCodeAlgo, String> hashs) {
        if (!HelperObject.isEquals(hashs, mapHash)) {
    		mapHash = hashs;
            setChanged();
            notifyObservers(MEMBER_HASHS);
        }
	}
	
	
	/*
	 * Inner classes
	 */
	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<ModelUpdaterImpl, ModelUpdater> {

		@Override
		public ModelUpdaterImpl marshal(final ModelUpdater model) {
			return (ModelUpdaterImpl) model;
		}

		@Override
		public ModelUpdater unmarshal(final ModelUpdaterImpl model) {
			return model;
		}
	}
}