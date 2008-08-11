/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.dao;

import java.io.Serializable;
import java.util.List;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.exception.ExceptionValidation;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the super-class for all value objects of the system
 * 
 * @author Stefan Laubenberger
 * @version 20080613
 */
public abstract class DataObject implements Serializable, IValidator {
    private static final long serialVersionUID = -3023201860307136848L;
	//TODO generate hashCode() & equals()!
	
	public static final int PERSISTENCE_CHANGED = 1;
	public static final int PERSISTENCE_NEW     = 2;
	public static final int PERSISTENCE_DELETED = 3;

	private final long createTime = System.currentTimeMillis();
	
	private int persistenceState ;


    protected DataObject() {
        super();
        init();
	}
		
	public int getPersistenceState() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getPersistenceState");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getPersistenceState", persistenceState);  //$NON-NLS-1$

		return persistenceState;
	}


	public void setPersistenceState(final int persistenceState) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setPersistenceState", persistenceState);  //$NON-NLS-1$

		if (persistenceState > this.persistenceState) {
			this.persistenceState = persistenceState;
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "setPersistenceState");  //$NON-NLS-1$
	}

	public long getCreateTime() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getCreateTime");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getCreateTime", createTime);  //$NON-NLS-1$

		return createTime;
	}


	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init",  toString()); //$NON-NLS-1$
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
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

	public void validateList(final String variable, final List<?> list) throws ExceptionValidation {
		if (!HelperGeneral.isValidCollection(list)) {
			throw new ExceptionValidation(getClass().getName() + "::validateArrayList - " + variable + ".size() == 0" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
