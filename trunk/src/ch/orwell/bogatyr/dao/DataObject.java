/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.dao;

import java.io.Serializable;
import java.util.ArrayList;

import ch.orwell.bogatyr.exception.ValidationException;
import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * This is the super-class for all value objects of the system
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class DataObject implements Serializable, ValidaterInterface {
	protected String className;
	protected final long createTime = System.currentTimeMillis();
	protected boolean isEdited = false;
	
	/**
	 * Constructs a DataObject.
	 */
	public DataObject() {
		init();
	}
	
	
	/*
	 * Implemented methods
	 */
	/**
	 * Validate a String with {@link GeneralHelper#isValidString(String)}. 
	 */
	public void validateString(String variable, String arg) throws ValidationException {
		if (!GeneralHelper.isValidString(arg)) {
			throw new ValidationException(this.className + "::validateString - " + variable + " == 'null' / ''" + this.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	/**
	 * Validate an int with {@link GeneralHelper#isValidInt(int)}
	 */
	public void validateInt(String variable, int arg) throws ValidationException {
		if (!GeneralHelper.isValidInt(arg)) {
			throw new ValidationException(this.className + "::validateInt - " + variable + " == 0" + this.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Validate a long with {@link GeneralHelper#isValidLong(long)}
	 */
	public void validateLong(String variable, long arg) throws ValidationException {
		if (!GeneralHelper.isValidLong(arg)) {
			throw new ValidationException(this.className + "::validateLong - " + variable + " == 0" + this.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Validate an Object with {@link GeneralHelper#isValidObject(Object)}
	 */
	public void validateObject(String variable, Object arg) throws ValidationException {
		if (!GeneralHelper.isValidObject(arg)) {
			throw new ValidationException(this.className + "::validateObject - " + variable + " == 'null'" + this.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Validate an array with {@link GeneralHelper#isValidArray(Object[])}
	 */
	public void validateArray(String variable, Object[] arg) throws ValidationException {
		if (!GeneralHelper.isValidArray(arg)) {
			throw new ValidationException(this.className + "::validateArray - " + variable + ".length() == 0" + this.toString());  //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Validate an ArrayList with {@link GeneralHelper#isValidArrayList(ArrayList)}
	 */
	@SuppressWarnings("unchecked")
	public void validateArrayList(String variable, ArrayList arg) throws ValidationException {
		if (!GeneralHelper.isValidArrayList(arg)) {
			throw new ValidationException(this.className + "::validateArrayList - " + variable + ".size() == 0" + this.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	

	/*
	 * Private methods
	 */
	/**
	 * Initialize the object.
	 */
	private void init() {
		this.className = this.getClass().getName();
	}
	
	
	/*
	 * Overriden methods
	 */
	@Override
	public String toString() {
		return "\nclassName: " + this.className + //$NON-NLS-1$
			"\ncreateTime: " + this.createTime + //$NON-NLS-1$
			"\nisEdited: " + this.isEdited; //$NON-NLS-1$
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.className == null) ? 0 : this.className.hashCode());
		result = PRIME * result + (int) (this.createTime ^ (this.createTime >>> 32));
		result = PRIME * result + (this.isEdited ? 1231 : 1237);
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
		final DataObject other = (DataObject) obj;
		if (this.className == null) {
			if (other.className != null)
				return false;
		} else if (!this.className.equals(other.className))
			return false;
		if (this.createTime != other.createTime)
			return false;
		if (this.isEdited != other.isEdited)
			return false;
		return true;
	}
}
