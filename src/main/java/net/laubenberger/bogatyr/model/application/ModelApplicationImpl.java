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

package net.laubenberger.bogatyr.model.application;


import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.model.misc.DocumentImpl;
import net.laubenberger.bogatyr.model.misc.Organization;
import net.laubenberger.bogatyr.model.misc.Person;
import net.laubenberger.bogatyr.model.worker.ModelWorker;
import net.laubenberger.bogatyr.model.worker.ModelWorkerImpl;
import net.laubenberger.bogatyr.service.localizer.Localizer;
import net.laubenberger.bogatyr.service.property.Property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The implementation of the application model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.6 (20110502)
 * @since 0.9.0
 */
@XmlRootElement(name = "modelApplication")
@XmlType(propOrder = {ModelApplication.MEMBER_UPDATE_LOCATION, ModelApplication.MEMBER_DEBUG})
public class ModelApplicationImpl extends DocumentImpl implements ModelApplication {
	private static final long serialVersionUID = -2826684498598090349L;

	private static final Logger log = LoggerFactory.getLogger(ModelApplicationImpl.class);

	private URL updateLocation;
	private boolean isDebug;

	private transient Localizer localizer;
	private transient Property property;

	private final transient ModelWorker modelWorker = new ModelWorkerImpl();


	public ModelApplicationImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public ModelApplicationImpl(final String name, final BigDecimal version, final int build,
										 final Date created, final URL url, final List<Organization> listOrganization, final List<Person> listPerson, final URL updateLocation, final boolean isDebug,
										 final Localizer localizer, final Property property, final UUID uuid, final Map<String, String> mapTag) {
		super(name, version, build, created, /*uuid,*/ url, listOrganization, listPerson, uuid, mapTag);

		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, version, build, created, url, listOrganization, listPerson, updateLocation, isDebug, localizer, property, uuid, mapTag));

		this.updateLocation = updateLocation;
		this.isDebug = isDebug;
		this.localizer = localizer;
		this.property = property;
	}


	/*
	 * Overridden methods
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isDebug ? 1231 : 1237);
		result = prime * result + ((null == updateLocation) ? 0 : updateLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		final ModelApplicationImpl other = (ModelApplicationImpl) obj;
		if (isDebug != other.isDebug) return false;
		if (null == updateLocation) {
			if (null != other.updateLocation) return false;
		} else if (!updateLocation.equals(other.updateLocation)) return false;
		return true;
	}


	/*
	 * Implemented methods
	 */

	@Override
	@XmlTransient
	public Localizer getLocalizer() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(localizer));
		return localizer;
	}

	@Override
	@XmlTransient
	public Property getProperty() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(property));
		return property;
	}

	@Override
	@XmlTransient
	public ModelWorker getModelWorker() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(modelWorker));
		return modelWorker;
	}
	
	@Override
	@XmlElement
	public URL getUpdateLocation() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(updateLocation));
		return updateLocation;
	}

	@Override
	@XmlElement
	public Boolean isDebug() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(isDebug));
		return isDebug;
	}

	@Override
	public void setDebug(final boolean isDebug) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(isDebug));

		if (isDebug != this.isDebug) {
			this.isDebug = isDebug;
			setChanged();
			notifyObservers(MEMBER_DEBUG);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setLocalizer(final Localizer localizer) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(localizer));

		if (!HelperObject.isEquals(localizer, this.localizer)) {
			this.localizer = localizer;
			setChanged();
			notifyObservers(MEMBER_LOCALIZER);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setProperty(final Property property) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(property));

		if (!HelperObject.isEquals(property, this.property)) {
			this.property = property;
			setChanged();
			notifyObservers(MEMBER_PROPERTY);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setUpdateLocation(final URL updateLocation) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(updateLocation));

		if (!HelperObject.isEquals(updateLocation, this.updateLocation)) {
			this.updateLocation = updateLocation;
			setChanged();
			notifyObservers(MEMBER_UPDATE_LOCATION);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
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