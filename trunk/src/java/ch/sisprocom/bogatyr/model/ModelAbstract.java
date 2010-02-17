/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model;

import java.util.Date;
import java.util.Observable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ch.sisprocom.bogatyr.helper.HelperObject;

import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * This is the skeleton for all models.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.7.0
 */
@XmlRootElement(name = "model")
public abstract class ModelAbstract extends Observable implements Model {
	private static final long serialVersionUID = 3491320587479082917L;

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
				+ ((null == instantiationDate) ? 0 : instantiationDate
						.hashCode());
		result = prime * result + (isNotifyEnabled ? 1231 : 1237);
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
}