/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package net.laubenberger.bogatyr.model.application;


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

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.xml.adapter.MapAdapterHashCode;
import net.laubenberger.bogatyr.model.crypto.HashCodeAlgo;
import net.laubenberger.bogatyr.model.misc.DocumentImpl;
import net.laubenberger.bogatyr.model.misc.Manufacturer;
import net.laubenberger.bogatyr.model.misc.Owner;
import net.laubenberger.bogatyr.model.misc.Publisher;
import net.laubenberger.bogatyr.model.worker.ModelWorker;
import net.laubenberger.bogatyr.model.worker.ModelWorkerImpl;
import net.laubenberger.bogatyr.service.localizer.Localizer;
import net.laubenberger.bogatyr.service.property.Property;


/**
 * The implementation of the application model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
@XmlRootElement(name = "modelApplication")
@XmlType(propOrder = {"debug", "hashs"})
public class ModelApplicationImpl extends DocumentImpl implements ModelApplication {
	private static final long serialVersionUID = -2826684498598090349L;

	private static final Logger log = LoggerFactory.getLogger(ModelApplicationImpl.class);

	private boolean isDebug;
	private Map<HashCodeAlgo, String> mapHash;

	private transient Localizer localizer;
	private transient Property property;

	private final transient ModelWorker modelWorker = new ModelWorkerImpl();


	public ModelApplicationImpl() {
		super();
		log.trace(HelperLog.constructor());
	}

	public ModelApplicationImpl(final String name, final BigDecimal version, final int build,
										 final Date created, final Manufacturer manufacturer, final Owner owner,
										 final Publisher publisher, final UUID uuid, final boolean isDebug,
										 final Localizer localizer, final Property property, final Map<HashCodeAlgo, String> mapHash, final Map<String, String> mapTag) {
		super(name, version, build, created, manufacturer, owner, publisher, uuid, mapTag);

		log.trace(HelperLog.constructor(name, version, build, created, manufacturer, owner, publisher, uuid, isDebug, localizer, property, mapHash, mapTag));

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
		log.debug(HelperLog.methodStart());

		final String result = getHash(HashCodeAlgo.SHA256);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public String getHash(final HashCodeAlgo hashCodeAlgo) {
		log.debug(HelperLog.methodStart(hashCodeAlgo));

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
	public void setHashs(final Map<HashCodeAlgo, String> hashs) {
		log.debug(HelperLog.methodStart(hashs));

		if (!HelperObject.isEquals(hashs, mapHash)) {
			mapHash = hashs;
			setChanged();
			notifyObservers(MEMBER_HASHS);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void addHash(final HashCodeAlgo hashCodeAlgo, final String hash) {
		log.debug(HelperLog.methodStart(hashCodeAlgo, hash));
		if (null == hashCodeAlgo) {
			throw new RuntimeExceptionIsNull("hashCodeAlgo"); //$NON-NLS-1$
		}

		if (null == mapHash) {
			mapHash = new HashMap<HashCodeAlgo, String>();
		}
		mapHash.put(hashCodeAlgo, hash);
		setChanged();
		notifyObservers(METHOD_ADD_HASH);

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void removeHash(final HashCodeAlgo hashCodeAlgo) {
		log.debug(HelperLog.methodStart(hashCodeAlgo));
		if (null == hashCodeAlgo) {
			throw new RuntimeExceptionIsNull("hashCodeAlgo"); //$NON-NLS-1$
		}

		if (null != mapHash) {
			mapHash.remove(hashCodeAlgo);
			setChanged();
			notifyObservers(METHOD_REMOVE_HASH);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public Localizer getLocalizer() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(localizer));
		return localizer;
	}

	@Override
	public Property getProperty() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(property));
		return property;
	}

	@Override
	public ModelWorker getModelWorker() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(modelWorker));
		return modelWorker;
	}

	@Override
	@XmlElement
	public Boolean isDebug() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(isDebug));
		return isDebug;
	}

	@Override
	public void setDebug(final boolean isDebug) {
		log.debug(HelperLog.methodStart(isDebug));

		if (isDebug != this.isDebug) {
			this.isDebug = isDebug;
			setChanged();
			notifyObservers(MEMBER_DEBUG);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setLocalizer(final Localizer localizer) {
		log.debug(HelperLog.methodStart(localizer));

		if (!HelperObject.isEquals(localizer, this.localizer)) {
			this.localizer = localizer;
			setChanged();
			notifyObservers(MEMBER_LOCALIZER);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setProperty(final Property property) {
		log.debug(HelperLog.methodStart(property));

		if (!HelperObject.isEquals(property, this.property)) {
			this.property = property;
			setChanged();
			notifyObservers(MEMBER_PROPERTY);
		}

		log.debug(HelperLog.methodExit());
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