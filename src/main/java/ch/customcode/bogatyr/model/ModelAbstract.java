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
package ch.customcode.bogatyr.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.xml.adapter.MapAdapterString;


/**
 * This is the skeleton for all models.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100413)
 * @since 0.7.0
 */
@XmlRootElement(name = "model")
public abstract class ModelAbstract extends Observable implements Model {
	private static final long serialVersionUID = 3491320587479082917L;

	private Map<String, String> mapTag = new HashMap<String, String>();
	
	private boolean isNotifyEnabled = true;
	private Date instantiationDate = new Date();

    protected ModelAbstract() {
        super();
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
				+ ((instantiationDate == null) ? 0 : instantiationDate
						.hashCode());
		result = prime * result + (isNotifyEnabled ? 1231 : 1237);
		result = prime * result + ((mapTag == null) ? 0 : mapTag.hashCode());
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
		ModelAbstract other = (ModelAbstract) obj;
		if (instantiationDate == null) {
			if (other.instantiationDate != null)
				return false;
		} else if (!instantiationDate.equals(other.instantiationDate))
			return false;
		if (isNotifyEnabled != other.isNotifyEnabled)
			return false;
		if (mapTag == null) {
			if (other.mapTag != null)
				return false;
		} else if (!mapTag.equals(other.mapTag))
			return false;
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
	public void setNotifyEnabled(final boolean enabled) {
        isNotifyEnabled = enabled;
	}
	
	@Override
	@XmlTransient
	public boolean isNotifyEnabled() {
		return isNotifyEnabled;
	}

	@Override
    @XmlElement
	public Date getInstantiationDate() {
		return instantiationDate;
	}

    @Override
	public void setInstantiationDate(final Date instantiationDate) {
		this.instantiationDate = instantiationDate;
	}
    
	@Override
	@XmlElement
    @XmlJavaTypeAdapter(MapAdapterString.class)
	public Map<String, String> getTags() {
		return mapTag;
	}

	@Override
	public void setTags(final Map<String, String> tags) {
        if (!HelperObject.isEquals(tags, mapTag)) {
        	mapTag = tags;
            setChanged();
            notifyObservers(MEMBER_TAGS);
        }
	}

	@Override
	public void addTag(final String key, final String value) {
		mapTag.put(key, value);
        setChanged();
        notifyObservers(METHOD_ADD_TAG);
	}

	@Override
	public void removeTag(final String key) {
		mapTag.remove(key);
        setChanged();
        notifyObservers(METHOD_REMOVE_TAG);
	}
}