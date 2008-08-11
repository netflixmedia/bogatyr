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
package ch.orwell.bogatyr.controller.net.common.dto;

import java.io.Serializable;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the communication object for the client/server-communication.
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public final class ComObject implements Serializable {
	private static final long serialVersionUID = 4563277188299092069L;

	private final long createTime = System.currentTimeMillis();
	
	private final String userKey;
	private String methodName;
	private Object data;
	private Exception exception;

	
	public ComObject(final String userKey, final String methodName, final Object data) {
        this(userKey, data);
		this.methodName = methodName;
	}
	
	public ComObject(final String userKey, final Object data) {
        super();
        this.userKey = userKey;
		this.data = data;

		init();
	}
	
	public Object getData() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getData"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getData", data); //$NON-NLS-1$

		return data;
	}
	
	public void setData(final Object data) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setData", data); //$NON-NLS-1$

		this.data = data;

		Logger.getInstance().writeMethodExit(this.getClass(), "setData"); //$NON-NLS-1$
	}
	
	public Exception getException() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getException"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getException", exception); //$NON-NLS-1$

		return exception;
	}
	
	public void setException(final Exception exception) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setException", exception); //$NON-NLS-1$

		this.exception = exception;

		Logger.getInstance().writeMethodExit(this.getClass(), "setException"); //$NON-NLS-1$
	}
	
	public String getMethodName() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getMethodName"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getMethodName", methodName); //$NON-NLS-1$

		return methodName;
	}
	
	public void setMethodName(final String methodName) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setMethodName", methodName); //$NON-NLS-1$

		this.methodName = methodName;

		Logger.getInstance().writeMethodExit(this.getClass(), "setMethodName"); //$NON-NLS-1$
	}

	public String getUserKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUserKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getUserKey", userKey); //$NON-NLS-1$

		return userKey;
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
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
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (createTime ^ createTime >>> 32);
		result = PRIME * result + (data == null ? 0 : data.hashCode());
		result = PRIME * result + (methodName == null ? 0 : methodName.hashCode());
		result = PRIME * result + (userKey == null ? 0 : userKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null) {
            return false;
        }
		if (getClass() != obj.getClass()) {
            return false;
        }
		final ComObject other = (ComObject) obj;
		if (createTime != other.createTime) {
            return false;
        }
		if (data == null) {
			if (other.data != null) {
                return false;
            }
		} else if (!data.equals(other.data)) {
            return false;
        }
		if (methodName == null) {
			if (other.methodName != null) {
                return false;
            }
		} else if (!methodName.equals(other.methodName)) {
            return false;
        }
		if (userKey == null) {
			if (other.userKey != null) {
                return false;
            }
		} else if (!userKey.equals(other.userKey)) {
            return false;
        }
		return true;
	}
}
