/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.model.updater;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.xml.adapter.MapAdapterHashCode;
import ch.customcode.bogatyr.misc.xml.adapter.MapAdapterPlatform;
import ch.customcode.bogatyr.model.crypto.HashCodeAlgo;
import ch.customcode.bogatyr.model.misc.DocumentImpl;
import ch.customcode.bogatyr.model.misc.Manufacturer;
import ch.customcode.bogatyr.model.misc.Owner;
import ch.customcode.bogatyr.model.misc.Platform;
import ch.customcode.bogatyr.model.misc.Publisher;


/**
 * The implementation of the updater model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100414)
 * @since 0.9.0
 */
@XmlRootElement(name = "modelDocument")
@XmlType(propOrder={"locations", "hashs"})
public class ModelUpdaterImpl extends DocumentImpl implements ModelUpdater {
	private static final long serialVersionUID = -2826684498598090349L;

	private static final Logger log = LoggerFactory.getLogger(ModelUpdaterImpl.class);
	
	private Map<Platform, String> mapLocation = new HashMap<Platform, String>();
	private Map<HashCodeAlgo, String> mapHash = new HashMap<HashCodeAlgo, String>();

    public ModelUpdaterImpl() {
        super();
        log.trace(HelperLog.constructor());
    }

	public ModelUpdaterImpl(final String name, final BigDecimal version, final int build,
			final Date created, final Manufacturer manufacturer, final Owner owner,
			final Publisher publisher, final UUID uuid, final Map<Platform, String> mapLocation, final Map<HashCodeAlgo, String> mapHash, final Map<String, String> mapTag) {
		super(name, version, build, created, manufacturer, owner, publisher, uuid, mapTag);
		log.trace(HelperLog.constructor(name, version, build, created, manufacturer, owner, publisher, uuid, mapLocation, mapHash, mapTag));
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
		log.debug(HelperLog.methodStart());
		
		final String result = getLocation(Platform.ANY);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public String getLocation(final Platform platform) {
		log.debug(HelperLog.methodStart(platform));
		if (null == platform) {
			throw new RuntimeExceptionIsNull("platform"); //$NON-NLS-1$
		}
		
		String result = null;
		if (null != mapLocation) {
			result = mapLocation.get(platform);
		}
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

    @Override
    @XmlElement
    @XmlJavaTypeAdapter(MapAdapterPlatform.class)
	public Map<Platform, String> getLocations() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(mapLocation));
		return mapLocation;
	}
    
	@Override
	public String getHash() {
		log.debug(HelperLog.methodStart());
		
		final String result = getHash(HashCodeAlgo.SHA256);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
    @Override
	public String getHash(final HashCodeAlgo hashCodeAlgo) {
		log.debug(HelperLog.methodStart(hashCodeAlgo));
		if (null == hashCodeAlgo) {
			throw new RuntimeExceptionIsNull("hashCodeAlgo"); //$NON-NLS-1$
		}
		
		String result = null;
		if (null != mapHash) {
			result = mapHash.get(hashCodeAlgo);
		}
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
    
    @Override
    @XmlElement
    @XmlJavaTypeAdapter(MapAdapterHashCode.class)
	public Map<HashCodeAlgo, String> getHashs() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(mapHash));
		return mapHash;
	}
	
    @Override
	public void setLocations(final Map<Platform, String> locations) {
    	log.debug(HelperLog.methodStart(locations));
    	
    	if (!HelperObject.isEquals(locations, mapLocation)) {
    		mapLocation = locations;
            setChanged();
            notifyObservers(MEMBER_LOCATIONS);
        }
    	
    	log.debug(HelperLog.methodExit());
	}

	@Override
	public void setHashs(final Map<HashCodeAlgo, String> hashs) {
		log.debug(HelperLog.methodStart(hashs));
		
		if (!HelperObject.isEquals(hashs, mapHash)) {
    		mapHash = hashs;
            setChanged();
            notifyObservers(MEMBER_HASHS);
        }
		
		log.debug(HelperLog.methodExit());
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