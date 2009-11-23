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
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.service.localizer.Localizer;
import ch.sisprocom.bogatyr.service.property.Property;


/**
 * The implementation of the application model.
 * 
 * @author SiSprocom GmbH, Stefan Laubenberger
 * @version 20091122
 */
public class ModelApplicationImpl extends ModelAbstract implements ModelApplication {
	private static final long serialVersionUID = -2826684498598090349L;

	private Map<HashCode, String> mapHash = new HashMap<HashCode, String>();
	private String name;
	private BigDecimal version;
	private int build;
	private Date created;
	private String manufacturer;
	private URL manufacturerURL;
	private UUID uuid;
	private boolean isDebug;
	private Localizer localizer;
	private Property property;
    
	
    /*
     * Implemented methods
     */
	@Override
	public Integer getBuild() {
		return build;
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
	public void addHash(final HashCode hashCode, final String hash) {
		mapHash.put(hashCode, hash);
        setChanged();
        notifyObservers(METHOD_ADD_HASH);
	}

	@Override
	public void removeHash(final HashCode hashCode) {
		mapHash.remove(hashCode);
        setChanged();
        notifyObservers(METHOD_REMOVE_HASH);
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
	public Localizer getLocalizer() {
		return localizer;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Property getProperty() {
		return property;
	}

	@Override
	public BigDecimal getVersion() {
		return version;
	}

	@Override
	public Boolean isDebug() {
		return isDebug;
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
	public void setDebug(final boolean isDebug) {
        if (isDebug != this.isDebug) {
            this.isDebug = isDebug;
            setChanged();
            notifyObservers(METHOD_SET_DEBUG);
        }
	}

	@Override
	public void setLocalizer(final Localizer localizer) {
        if (!HelperObject.isEquals(localizer, this.localizer)) {
            this.localizer = localizer;
            setChanged();
            notifyObservers(METHOD_SET_LOCALIZER);
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
	public void setProperty(final Property property) {
        if (!HelperObject.isEquals(property, this.property)) {
            this.property = property;
            setChanged();
            notifyObservers(METHOD_SET_PROPERTY);
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