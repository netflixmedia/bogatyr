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

package net.laubenberger.bogatyr.model;

import java.util.Date;
import java.util.Map;
import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.xml.adapter.MapAdapterString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is the skeleton for all models.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100509)
 * @since 0.7.0
 */
@XmlRootElement(name = "model")
public abstract class ModelAbstract extends Observable implements Model {
	private static final long serialVersionUID = 3491320587479082917L;

	private static final Logger log = LoggerFactory.getLogger(ModelAbstract.class);

	private Map<String, String> mapTag;

	private boolean isNotifyEnabled = true;
	private Date instantiationDate = new Date();

	protected ModelAbstract() {
		super();
		log.trace(HelperLog.constructor());
	}

	protected ModelAbstract(final Map<String, String> mapTag) {
		super();
		log.trace(HelperLog.constructor(mapTag));
		this.mapTag = mapTag;
	}


	/*
	 * Overridden methods
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((null == instantiationDate) ? 0 : instantiationDate
				.hashCode());
		result = prime * result + (isNotifyEnabled ? 1231 : 1237);
		result = prime * result + ((null == mapTag) ? 0 : mapTag.hashCode());
		return result;
	}


	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ModelAbstract other = (ModelAbstract) obj;
		if (null == instantiationDate) {
			if (null != other.instantiationDate) {
				return false;
			}
		} else if (!instantiationDate.equals(other.instantiationDate)) {
			return false;
		}
		if (isNotifyEnabled != other.isNotifyEnabled) {
			return false;
		}
		if (null == mapTag) {
			if (null != other.mapTag) {
				return false;
			}
		} else if (!mapTag.equals(other.mapTag)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}

	@Override
	public void notifyObservers() {
		if (isNotifyEnabled) {
			super.notifyObservers();
		}
	}

	@Override
	public void notifyObservers(final Object arg) {
		if (isNotifyEnabled) {
			super.notifyObservers(arg);
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	@XmlTransient
	public boolean isNotifyEnabled() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(isNotifyEnabled));
		return isNotifyEnabled;
	}

	@Override
	@XmlElement
	public Date getInstantiationDate() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(instantiationDate));
		return instantiationDate;
	}

	@Override
	@XmlElement
	@XmlJavaTypeAdapter(MapAdapterString.class)
	public Map<String, String> getTags() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(mapTag));
		return mapTag;
	}
//
//	@Override
//	public String getTag(final String key) {
//		log.debug(HelperLog.methodStart(key));
//		if (null == key) {
//			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
//		}
//
//		String result = null;
//		if (null != mapTag) {
//			result = mapTag.get(key);
//		}
//
//		log.debug(HelperLog.methodExit(result));
//		return result;
//	}

	@Override
	public void setNotifyEnabled(final boolean enabled) {
		log.debug(HelperLog.methodStart(enabled));

		isNotifyEnabled = enabled;

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setInstantiationDate(final Date instantiationDate) {
		log.debug(HelperLog.methodStart(instantiationDate));

		this.instantiationDate = instantiationDate;

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setTags(final Map<String, String> tags) {
		log.debug(HelperLog.methodStart(tags));

		if (!HelperObject.isEquals(tags, mapTag)) {
			mapTag = tags;
			setChanged();
			notifyObservers(MEMBER_TAGS);
		}

		log.debug(HelperLog.methodExit());
	}

//	@Override
//	public void addTag(final String key, final String value) {
//		log.debug(HelperLog.methodStart(key, value));
//		if (null == key) {
//			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
//		}
//
//		if (null == mapTag) {
//			mapTag = new HashMap<String, String>();
//		}
//		mapTag.put(key, value);
//		setChanged();
//		notifyObservers(METHOD_ADD_TAG);
//
//		log.debug(HelperLog.methodExit());
//	}
//
//	@Override
//	public void removeTag(final String key) {
//		log.debug(HelperLog.methodStart(key));
//		if (null == key) {
//			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
//		}
//
//		if (null != mapTag) {
//			mapTag.remove(key);
//			setChanged();
//			notifyObservers(METHOD_REMOVE_TAG);
//		}
//
//		log.debug(HelperLog.methodExit());
//	}
}