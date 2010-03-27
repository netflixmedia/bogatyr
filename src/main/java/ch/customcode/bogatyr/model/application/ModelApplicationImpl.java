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
package ch.customcode.bogatyr.model.application;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.xml.adapter.MapAdapterHashCode;
import ch.customcode.bogatyr.model.crypto.HashCodeAlgo;
import ch.customcode.bogatyr.model.misc.DocumentImpl;
import ch.customcode.bogatyr.model.misc.Manufacturer;
import ch.customcode.bogatyr.model.misc.Owner;
import ch.customcode.bogatyr.model.misc.Publisher;
import ch.customcode.bogatyr.model.worker.ModelWorker;
import ch.customcode.bogatyr.model.worker.ModelWorkerImpl;
import ch.customcode.bogatyr.service.localizer.Localizer;
import ch.customcode.bogatyr.service.property.Property;


/**
 * The implementation of the application model.
 * 
 * @author SiSprocom GmbH, Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.9.0
 */
@XmlRootElement(name = "modelApplication")
@XmlType(propOrder={"debug", "hashs"})
public class ModelApplicationImpl extends DocumentImpl implements ModelApplication {
	private static final long serialVersionUID = -2826684498598090349L;

	private boolean isDebug;
	private Map<HashCodeAlgo, String> mapHash = new HashMap<HashCodeAlgo, String>(3);

	private transient Localizer localizer;
	private transient Property property;

	private final transient ModelWorker modelWorker = new ModelWorkerImpl();

	
    public ModelApplicationImpl() {
        super();
    }
    
	public ModelApplicationImpl(final String name, final BigDecimal version, final int build,
			final Date created, final Manufacturer manufacturer, final Owner owner,
			final Publisher publisher, final UUID uuid, final boolean isDebug,
			final Localizer localizer, final Property property, final Map<HashCodeAlgo, String> mapHash) {
		super(name, version, build, created, manufacturer, owner, publisher, uuid);
		this.isDebug = isDebug;
		this.localizer = localizer;
		this.property = property;
		this.mapHash = mapHash;
	}


	/*
     * Overridden methods
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isDebug ? 1231 : 1237);
		result = prime * result + ((null == mapHash) ? 0 : mapHash.hashCode());
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
		final ModelApplicationImpl other = (ModelApplicationImpl) obj;
        if (isDebug != other.isDebug) {
            return false;
        }
		if (null == mapHash) {
            if (null != other.mapHash) {
                return false;
            }
		} else if (!mapHash.equals(other.mapHash)) {
            return false;
        }
		return true;
	}


	/*
     * Implemented methods
     */
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

	@Override
	public void addHash(final HashCodeAlgo hashCodeAlgo, final String hash) {
		mapHash.put(hashCodeAlgo, hash);
        setChanged();
        notifyObservers(METHOD_ADD_HASH);
	}

	@Override
	public void removeHash(final HashCodeAlgo hashCodeAlgo) {
		mapHash.remove(hashCodeAlgo);
        setChanged();
        notifyObservers(METHOD_REMOVE_HASH);
	}

	@Override
	public Localizer getLocalizer() {
		return localizer;
	}

	@Override
	public Property getProperty() {
		return property;
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
	public void setProperty(final Property property) {
        if (!HelperObject.isEquals(property, this.property)) {
            this.property = property;
            setChanged();
            notifyObservers(MEMBER_PROPERTY);
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