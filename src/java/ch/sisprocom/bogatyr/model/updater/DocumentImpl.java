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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.model.misc.Platform;
import ch.sisprocom.bogatyr.model.updater.adapter.MapAdapterHashCode;
import ch.sisprocom.bogatyr.model.updater.adapter.MapAdapterPlatform;


/**
 * The implementation of the document model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091205)
 * @since 0.9.0
 */
@XmlRootElement
public class DocumentImpl extends ModelAbstract implements Document {
	private static final long serialVersionUID = -2826684498598090349L;

	private Map<Platform, String> mapLocation = new HashMap<Platform, String>();
	private Map<HashCode, String> mapHash = new HashMap<HashCode, String>();
	private String name;
	private BigDecimal version;
	private int build;
	private Date created;
	private String manufacturer;
	private URL manufacturerURL;
	private UUID uuid;

    
	
    /*
     * Implemented methods
     */
	@Override
    @XmlElement
	public Integer getBuild() {
		return build;
	}
	
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
            notifyObservers(METHOD_SET_LOCATIONS);
        }
	}

	@Override
	public String getHash() {
		return getHash(HashCode.SHA256);
	}
	
    @Override
	public String getHash(final HashCode hashCode) {
		return mapHash.get(hashCode);
	}
    
    @Override
    @XmlElement
    @XmlJavaTypeAdapter(MapAdapterHashCode.class)
	public Map<HashCode, String> getHashs() {
		return mapHash;
	}

	@Override
	public void setHashs(final Map<HashCode, String> hashs) {
        if (!HelperObject.isEquals(hashs, mapHash)) {
    		mapHash = hashs;
            setChanged();
            notifyObservers(METHOD_SET_HASHS);
        }
	}

	@Override
    @XmlElement
	public Date getCreated() {
		return created;
	}

	@Override
    @XmlElement
	public String getManufacturer() {
		return manufacturer;
	}

	@Override
    @XmlElement
	public URL getManufacturerURL() {
		return manufacturerURL;
	}

	@Override
    @XmlElement
	public UUID getUUID() {
		return uuid;
	}
    
	@Override
    @XmlElement
	public String getName() {
		return name;
	}

	@Override
    @XmlElement
	public BigDecimal getVersion() {
		return version;
	}

	@Override
	public void setBuild(final int build) {
        if (build != this.build) {
            this.build = build;
            setChanged();
            notifyObservers(METHOD_SET_BUILD);
        }
	}

	@Override
	public void setCreated(final Date created) {
        if (!HelperObject.isEquals(created, this.created)) {
            this.created = created;
            setChanged();
            notifyObservers(METHOD_SET_CREATED);
        }
	}

	@Override
	public void setManufacturer(final String manufacturer) {
        if (!HelperObject.isEquals(manufacturer, this.manufacturer)) {
            this.manufacturer = manufacturer;
            setChanged();
            notifyObservers(METHOD_SET_MANUFACTURER);
        }
	}

	@Override
	public void setManufacturerURL(final URL url) {
        if (!HelperObject.isEquals(url, manufacturerURL)) {
            manufacturerURL = url;
            setChanged();
            notifyObservers(METHOD_SET_MANUFACTURER_URL);
        }
	}

	@Override
	public void setUUID(final UUID uuid) {
        if (!HelperObject.isEquals(uuid, this.uuid)) {
            this.uuid = uuid;
            setChanged();
            notifyObservers(METHOD_SET_UUID);
        }
	}

	@Override
	public void setName(final String name) {
        if (!HelperObject.isEquals(name, this.name)) {
            this.name = name;
            setChanged();
            notifyObservers(METHOD_SET_NAME);
        }
	}

	@Override
	public void setVersion(final BigDecimal version) {
        if (!HelperObject.isEquals(version, this.version)) {
            this.version = version;
            setChanged();
            notifyObservers(METHOD_SET_VERSION);
        }
	}
}