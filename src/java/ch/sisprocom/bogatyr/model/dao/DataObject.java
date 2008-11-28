/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.model.dao;

import java.io.Serializable;
import java.util.Collection;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.exception.ExceptionValidation;


/**
 * This is the super-class for all value objects of the Bogatyr system.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class DataObject implements Serializable, IValidator { //TODO document in Wiki!
    private static final long serialVersionUID = -3023201860307136848L;
	
	public static final int PERSISTENCE_CHANGED = 1;
	public static final int PERSISTENCE_NEW     = 2;
	public static final int PERSISTENCE_DELETED = 3;

	private final long createTime = System.currentTimeMillis();
	
	private int persistenceState;


    protected DataObject() {
        super();
	}
		
	public int getPersistenceState() {
		return persistenceState;
	}


	public void setPersistenceState(final int persistenceState) {
		if (persistenceState > this.persistenceState) {
			this.persistenceState = persistenceState;
		}
	}

	public long getCreateTime() {
		return createTime;
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (createTime ^ (createTime >>> 32));
		result = prime * result + persistenceState;
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
		DataObject other = (DataObject) obj;
		if (createTime != other.createTime)
			return false;
		if (persistenceState != other.persistenceState)
			return false;
		return true;
	}

	
	/*
	 * Implemented methods
	 */
	//TODO Logger also for validation?
	public void validateString(final String variable, final String arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidString(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateString - " + variable + " == 'null' / ''" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	public void validateInt(final String variable, final int arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidInt(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateInt - " + variable + " == 0" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	public void validateDouble(final String variable, final double arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidDouble(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateDouble - " + variable + " == 0" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	public void validateLong(final String variable, final long arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidLong(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateLong - " + variable + " == 0" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void validateFloat(final String variable, final float arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidFloat(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateFloat - " + variable + " == 0" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void validateObject(final String variable, final Object arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidObject(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateObject - " + variable + " == 'null'" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void validateArray(final String variable, final Object[] arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidArray(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateArray - " + variable + ".length() == 0" + toString());  //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void validateCollection(final String variable, final Collection<?> arg) throws ExceptionValidation {
		if (!HelperGeneral.isValidCollection(arg)) {
			throw new ExceptionValidation(getClass().getName() + "::validateCollection - " + variable + ".size() == 0" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
