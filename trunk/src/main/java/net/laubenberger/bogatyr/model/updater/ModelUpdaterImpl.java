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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.model.updater;


import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.xml.adapter.MapAdapterHashCode;
import net.laubenberger.bogatyr.model.crypto.HashCodeAlgo;
import net.laubenberger.bogatyr.model.misc.DocumentImpl;
import net.laubenberger.bogatyr.model.misc.Organization;
import net.laubenberger.bogatyr.model.misc.Person;
import net.laubenberger.bogatyr.model.misc.Platform;
import net.laubenberger.bogatyr.service.updater.MapAdapterPlatformURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The implementation of the updater model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.9.0
 */
@XmlRootElement(name = "modelUpdater")
@XmlType(propOrder = {"locations", "hashs"})
public class ModelUpdaterImpl extends DocumentImpl implements ModelUpdater {
	private static final long serialVersionUID = -2826684498598090349L;

	private static final Logger log = LoggerFactory.getLogger(ModelUpdaterImpl.class);

	private Map<Platform, URL> mapLocation;
	private Map<HashCodeAlgo, String> mapHash;

	public ModelUpdaterImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public ModelUpdaterImpl(final String name, final BigDecimal version, final int build,
									final Date created, final UUID uuid, final URL url, final List<Organization> listOrganization, final List<Person> listPerson, final Map<Platform, URL> mapLocation, final Map<HashCodeAlgo, String> mapHash, final Map<String, String> mapTag) {
		super(name, version, build, created, uuid, url, listOrganization, listPerson, mapTag);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, version, build, created, uuid, url, listOrganization, listPerson, mapLocation, mapHash, mapTag));
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
	public URL getLocation() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final URL result = getLocation(Platform.ANY);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public URL getLocation(final Platform platform) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(platform));
		if (null == platform) {
			throw new RuntimeExceptionIsNull("platform"); //$NON-NLS-1$
		}

		URL result = null;
		if (null != mapLocation) {
			result = mapLocation.get(platform);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	@XmlElement
	@XmlJavaTypeAdapter(MapAdapterPlatformURL.class)
	public Map<Platform, URL> getLocations() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(mapLocation));
		return mapLocation;
	}

	@Override
	public String getHash() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = getHash(HashCodeAlgo.SHA256);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public String getHash(final HashCodeAlgo hashCodeAlgo) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(hashCodeAlgo));
		if (null == hashCodeAlgo) {
			throw new RuntimeExceptionIsNull("hashCodeAlgo"); //$NON-NLS-1$
		}

		String result = null;
		if (null != mapHash) {
			result = mapHash.get(hashCodeAlgo);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	@XmlElement
	@XmlJavaTypeAdapter(MapAdapterHashCode.class)
	public Map<HashCodeAlgo, String> getHashs() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(mapHash));
		return mapHash;
	}

	@Override
	public void setLocations(final Map<Platform, URL> locations) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(locations));

		if (!HelperObject.isEquals(locations, mapLocation)) {
			mapLocation = locations;
			setChanged();
			notifyObservers(MEMBER_LOCATIONS);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setHashs(final Map<HashCodeAlgo, String> hashs) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(hashs));

		if (!HelperObject.isEquals(hashs, mapHash)) {
			mapHash = hashs;
			setChanged();
			notifyObservers(MEMBER_HASHS);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
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