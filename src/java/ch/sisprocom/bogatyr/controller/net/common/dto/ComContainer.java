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

import java.io.Serializable;
import java.util.Arrays;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is the communication container for the client/server-communication.
 * 
 * @author Stefan Laubenberger
 * @version 20081026
 */
public final class ComContainer implements Serializable { //TODO document in Wiki!
	private static final long serialVersionUID = 1639137536981333349L;

	private final long createTime = System.currentTimeMillis();

	private final String key;
	private final byte[] data ;


	public ComContainer(final String key, final byte[] data) {
        super();
        this.key = key;
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public byte[] getData() {
		return data;
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
