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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.xml.adapter.MapAdapterHashCode;
import ch.sisprocom.bogatyr.model.ModelAbstract;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.model.misc.Manufacturer;
import ch.sisprocom.bogatyr.model.worker.ModelWorker;
import ch.sisprocom.bogatyr.model.worker.ModelWorkerImpl;
import ch.sisprocom.bogatyr.service.localizer.Localizer;
import ch.sisprocom.bogatyr.service.property.Property;


/**
 * The implementation of the application model.
 * 
 * @author SiSprocom GmbH, Stefan Laubenberger
 * @version 0.9.0 (20100120)
 * @since 0.9.0
 */
@XmlRootElement(name = "application")
@XmlType(propOrder={"name", "version", "build", "created", "manufacturer", "UUID", "debug", "hashs"})
public class ModelApplicationImpl extends ModelAbstract implements ModelApplication {
	private static final long serialVersionUID = -2826684498598090349L;

	private Map<HashCode, String> mapHash = new HashMap<HashCode, String>(3);
	private String name;
	private BigDecimal version;
	private int build;
	private Date created;
	private Manufacturer manufacturer;
	private UUID uuid;
	private boolean isDebug;
	private transient Localizer localizer;
	private transient Property property;
	private final transient ModelWorker modelWorker = new ModelWorkerImpl();

	
    public ModelApplicationImpl() {
        super();
    }
    
	
	/*
     * Overridden methods
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + build;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + (isDebug ? 1231 : 1237);
		result = prime * result
				+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((mapHash == null) ? 0 : mapHash.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelApplicationImpl other = (ModelApplicationImpl) obj;
		if (build != other.build)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (isDebug != other.isDebug)
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (mapHash == null) {
			if (other.mapHash != null)
				return false;
		} else if (!mapHash.equals(other.mapHash))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}


	/*
     * Implemented methods
     */
	@XmlElement
	@Override
	public int getBuild() {
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
            notifyObservers(MEMBER_HASHS);
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
	@XmlElement
	public Date getCreated() {
		return created;
	}

	@Override
	@XmlElement
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	@Override
	@XmlElement
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public Localizer getLocalizer() {
		return localizer;
	}

	@Override
	@XmlElement
	public String getName() {
		return name;
	}

	@Override
	public Property getProperty() {
		return property;
	}

	@Override
	@XmlElement
	public BigDecimal getVersion() {
		return version;
	}

	@Override
	public ModelWorker getModelWorker() {
		return modelWorker;
	}

	@Override
	@XmlElement
	public Boolean isDebug() {
		return isDebug;
	}

	@Override
	public void setBuild(final int build) {
        if (build != this.build) {
            this.build = build;
            setChanged();
            notifyObservers(MEMBER_BUILD);
        }
	}
	@Override
	public void setCreated(final Date created) {
        if (!HelperObject.isEquals(created, this.created)) {
            this.created = created;
            setChanged();
            notifyObservers(MEMBER_CREATED);
        }
	}

	@Override
	public void setManufacturer(final Manufacturer manufacturer) {
        if (!HelperObject.isEquals(manufacturer, this.manufacturer)) {
            this.manufacturer = manufacturer;
            setChanged();
            notifyObservers(MEMBER_MANUFACTURER);
        }
	}

	@Override
	public void setUUID(final UUID uuid) {
        if (!HelperObject.isEquals(uuid, this.uuid)) {
            this.uuid = uuid;
            setChanged();
            notifyObservers(MEMBER_UUID);
        }
	}

	@Override
	public void setDebug(final boolean isDebug) {
        if (isDebug != this.isDebug) {
            this.isDebug = isDebug;
            setChanged();
            notifyObservers(MEMBER_DEBUG);
        }
	}

	@Override
	public void setLocalizer(final Localizer localizer) {
        if (!HelperObject.isEquals(localizer, this.localizer)) {
            this.localizer = localizer;
            setChanged();
            notifyObservers(MEMBER_LOCALIZER);
        }
	}

	@Override
	public void setName(final String name) {
        if (!HelperObject.isEquals(name, this.name)) {
            this.name = name;
            setChanged();
            notifyObservers(MEMBER_NAME);
        }
	}

	@Override
	public void setProperty(final Property property) {
        if (!HelperObject.isEquals(property, this.property)) {
            this.property = property;
            setChanged();
            notifyObservers(MEMBER_PROPERTY);
        }
	}

	@Override
	public void setVersion(final BigDecimal version) {
        if (!HelperObject.isEquals(version, this.version)) {
            this.version = version;
            setChanged();
            notifyObservers(MEMBER_VERSION);
        }
	}
	
	
	/*
	 * Inner classes
	 */
	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<ModelApplicationImpl, ModelApplication> {

		@Override
		public ModelApplicationImpl marshal(final ModelApplication model) {
			return (ModelApplicationImpl) model;
		}

		@Override
		public ModelApplication unmarshal(final ModelApplicationImpl model) {
			return model;
		}
	}
}