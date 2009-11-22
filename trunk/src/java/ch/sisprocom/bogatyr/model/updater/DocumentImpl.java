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

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.model.misc.Platform;


/**
 * The implementation of the document model.
 * 
 * @author Stefan Laubenberger
 * @version 20091122
 */
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
	public Integer getBuild() {
		return build;
	}
	
	@Override
	public String getLocation() {
		return getLocation(Platform.ANY);
	}

	@Override
	public String getLocation(Platform platform) {
		return mapLocation.get(platform);
	}

	@Override
	public Map<Platform, String> getLocations() {
		return mapLocation;
	}

	@Override
	public void setLocations(Map<Platform, String> locations) {
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
	public String getHash(HashCode hashCode) {
		return mapHash.get(hashCode);
	}

	@Override
	public Map<HashCode, String> getHashs() {
		return mapHash;
	}

	@Override
	public void setHashs(Map<HashCode, String> hashs) {
        if (!HelperObject.isEquals(hashs, mapHash)) {
    		mapHash = hashs;
            setChanged();
            notifyObservers(METHOD_SET_HASHS);
        }
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public String getManufacturer() {
		return manufacturer;
	}

	@Override
	public URL getManufacturerURL() {
		return manufacturerURL;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BigDecimal getVersion() {
		return version;
	}

	@Override
	public void setBuild(int build) {
        if (build != this.build) {
            this.build = build;
            setChanged();
            notifyObservers(METHOD_SET_BUILD);
        }
	}
	@Override
	public void setCreated(Date created) {
        if (!HelperObject.isEquals(created, this.created)) {
            this.created = created;
            setChanged();
            notifyObservers(METHOD_SET_CREATED);
        }
	}

	@Override
	public void setManufacturer(String manufacturer) {
        if (!HelperObject.isEquals(manufacturer, this.manufacturer)) {
            this.manufacturer = manufacturer;
            setChanged();
            notifyObservers(METHOD_SET_MANUFACTURER);
        }
	}

	@Override
	public void setManufacturerURL(URL url) {
        if (!HelperObject.isEquals(url, this.manufacturerURL)) {
            this.manufacturerURL = url;
            setChanged();
            notifyObservers(METHOD_SET_MANUFACTURER_URL);
        }
	}

	@Override
	public void setUUID(UUID uuid) {
        if (!HelperObject.isEquals(uuid, this.uuid)) {
            this.uuid = uuid;
            setChanged();
            notifyObservers(METHOD_SET_UUID);
        }
	}

	@Override
	public void setName(String name) {
        if (!HelperObject.isEquals(name, this.name)) {
            this.name = name;
            setChanged();
            notifyObservers(METHOD_SET_NAME);
        }
	}

	@Override
	public void setVersion(BigDecimal version) {
        if (!HelperObject.isEquals(version, this.version)) {
            this.version = version;
            setChanged();
            notifyObservers(METHOD_SET_VERSION);
        }
	}
}