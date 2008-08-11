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
import java.util.Arrays;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the communication container for the client/server-communication.
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public final class ComContainer implements Serializable {
	private static final long serialVersionUID = 1639137536981333349L;

	private final long createTime = System.currentTimeMillis();

	private final String key;
	private final byte[] data ;


	public ComContainer(final String key, final byte[] data) {
        super();
        this.key = key;
		this.data = data;
		
		init();
	}

	public String getKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getKey", key); //$NON-NLS-1$

		return key;
	}

	public byte[] getData() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getData"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getData", data); //$NON-NLS-1$

		return data;
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
		result = PRIME * result + Arrays.hashCode(data);
		result = PRIME * result + (key == null ? 0 : key.hashCode());
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
		final ComContainer other = (ComContainer) obj;
		if (createTime != other.createTime) {
            return false;
        }
		if (!Arrays.equals(data, other.data)) {
            return false;
        }
		if (key == null) {
			if (other.key != null) {
                return false;
            }
		} else if (!key.equals(other.key)) {
            return false;
        }
		return true;
	}
}
