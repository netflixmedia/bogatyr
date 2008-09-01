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
package ch.sisprocom.bogatyr.controller.net.common.dto;

import java.security.NoSuchAlgorithmException;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.exception.ExceptionValidation;
import ch.sisprocom.bogatyr.helper.logger.Logger;
import ch.sisprocom.bogatyr.model.dao.DataObject;


/**
 * The data object for a user
 *
 * @author Stefan Laubenberger
 * @version 20080901
 */
public final class User extends DataObject {
	private static final long serialVersionUID = -4679247079715137984L;

	private final String username;
	private String password;
	
	
	public User(final String username, final String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$

		return password;
	}
	
	public void setPassword(final String password) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setPassword", password); //$NON-NLS-1$

		setPersistenceState(PERSISTENCE_CHANGED);
		this.password = password;

		Logger.getInstance().writeMethodExit(this.getClass(), "setPassword"); //$NON-NLS-1$
	}
	
	public String getUsername() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUsername"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getUsername", username); //$NON-NLS-1$

		return username;
	}

	public String getUniqueKey() throws NoSuchAlgorithmException {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUniqueKey"); //$NON-NLS-1$
		
		String uniqueKey = HelperGeneral.getChecksum(toString());

		Logger.getInstance().writeMethodExit(this.getClass(), "getUniqueKey", uniqueKey); //$NON-NLS-1$
		return uniqueKey;
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + (password == null ? 0 : password.hashCode());
		result = PRIME * result + (username == null ? 0 : username.hashCode());
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
		final User other = (User) obj;
		if (password == null) {
			if (other.password != null) {
                return false;
            }
		} else if (!password.equals(other.password)) {
            return false;
        }
		if (username == null) {
			if (other.username != null) {
                return false;
            }
		} else if (!username.equals(other.username)) {
            return false;
        }
		return true;
	}
	
	
	/*
	 * Implemented methods
	 */
	/**
	 * Validates the attributes {@link #username} and {@link #password}
	 * 
	 * @throws ExceptionValidation
	 * @see DataObject 
	 */
	public void validate() throws ExceptionValidation {
		validateString("username", username); //$NON-NLS-1$
		validateString("password", password); //$NON-NLS-1$
	}
}