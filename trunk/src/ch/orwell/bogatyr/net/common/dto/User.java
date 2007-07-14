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
package ch.orwell.bogatyr.net.common.dto;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.dao.DataObject;
import ch.orwell.bogatyr.exception.ValidationException;
import ch.orwell.bogatyr.util.GeneralHelper;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * The data object for a user
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class User extends DataObject {
	private static final long serialVersionUID = -2688333932057727527L;

	private String username;
	private String password;
	
	
	/**
	 * Constructs a ComContainer.
	 * 
	 * @param username Username as a String
	 * @param password Password as a String
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;

		init();
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.isEdited = true;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}

	public long getCreateTime() {
		return this.createTime;
	}

	public String getUniqueKey() {
		return GeneralHelper.generateMd5(toString());
	}
	
	/*
	 * Private methods
	 */
	/**
	 * Initialize the object
	 * Do some logging.
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED) + toString()); //$NON-NLS-1$
	}
	
	
	/*
	 * Implemented methods
	 */
	/**
	 * Validates the attributes {@link #username} and {@link #password}
	 * 
	 * @throws ValidationException
	 * @see DataObject 
	 */
	public void validate() throws ValidationException {
		validateString("username", this.username); //$NON-NLS-1$
		validateString("password", this.password); //$NON-NLS-1$
	}

	
	/*
	 * Overriden methods
	 */
	@Override
	public String toString() {
		return super.toString() + 
			"\nusername: " + this.username + //$NON-NLS-1$
			"\npassword: " + this.password; //$NON-NLS-1$
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((this.password == null) ? 0 : this.password.hashCode());
		result = PRIME * result + ((this.username == null) ? 0 : this.username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (this.password == null) {
			if (other.password != null)
				return false;
		} else if (!this.password.equals(other.password))
			return false;
		if (this.username == null) {
			if (other.username != null)
				return false;
		} else if (!this.username.equals(other.username))
			return false;
		return true;
	}
}
