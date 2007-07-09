/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.net.common.dto;

import java.io.Serializable;
import java.util.Arrays;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the communication container for the client/server-communication.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class ComContainer implements Serializable {
	private static final long serialVersionUID = 2670074600547970553L;
	private String className;
	private long createTime = System.currentTimeMillis();

	private String key;
	private byte[] data;

	/**
	 * Constructs a ComContainer.
	 * 
	 * @param key The key for authentication
	 * @param data The Data as a byte-array
	 */
	public ComContainer(String key, byte[] data) {
		this.key = key;
		this.data = data;
		
		init();
	}

	public String getKey() {
		return this.key;
	}

	public byte[] getData() {
		return this.data;
	}
	
	
	/*
	 * Private methods
	 */
	/**
	 * Initialize the object
	 * Do some logging.
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED) + toString()); //$NON-NLS-1$
	}
	
	
	/*
	 * Overriden methods
	 */
	@Override
	public String toString() {
		return "\nclassName: " + this.className + //$NON-NLS-1$
			"\ncreateTime: " + this.createTime + //$NON-NLS-1$
			"\nkey: " + this.key + //$NON-NLS-1$
			"\ndata: " + this.data; //$NON-NLS-1$
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.className == null) ? 0 : this.className.hashCode());
		result = PRIME * result + (int) (this.createTime ^ (this.createTime >>> 32));
		result = PRIME * result + Arrays.hashCode(this.data);
		result = PRIME * result + ((this.key == null) ? 0 : this.key.hashCode());
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
		final ComContainer other = (ComContainer) obj;
		if (this.className == null) {
			if (other.className != null)
				return false;
		} else if (!this.className.equals(other.className))
			return false;
		if (this.createTime != other.createTime)
			return false;
		if (!Arrays.equals(this.data, other.data))
			return false;
		if (this.key == null) {
			if (other.key != null)
				return false;
		} else if (!this.key.equals(other.key))
			return false;
		return true;
	}
}
