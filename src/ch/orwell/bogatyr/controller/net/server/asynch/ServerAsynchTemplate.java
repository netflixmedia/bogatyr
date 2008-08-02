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
package ch.orwell.bogatyr.controller.net.server.asynch;

import java.util.Map;import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.controller.net.server.ServerTemplate;
import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.exception.ExceptionInvalidUserKey;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the skeleton for asynchron servers
 *
 * @author Stefan Laubenberger
 * @version 20080723
 */
public abstract class ServerAsynchTemplate extends ServerTemplate {
	private final Map<String, ThreadServerAsynchTemplate> mapThread = new ConcurrentHashMap<String, ThreadServerAsynchTemplate>();

//	private Thread thread;

	
	protected ServerAsynchTemplate(final String propertiesStreamName) throws Exception {
		super(propertiesStreamName);
		
//		this.thread = new Thread(this);
//		this.thread.start();
	}
	
	/**
	 * Adds a new thread to the {@link #mapThread}.
	 * 
	 * @param uniqueKey A unique Key for the {@link #mapThread} as Map index
	 * @param asynchServerThread An asynchron server thread
	 * @throws ExceptionInvalidUserKey 
	 * @see Map
	 */
	public void addAsynchServerThread(final String uniqueKey, final ThreadServerAsynchTemplate asynchServerThread) throws ExceptionInvalidUserKey {
		if (HelperGeneral.isValidString(uniqueKey) && HelperGeneral.isValidObject(asynchServerThread)) {
			Logger.getInstance().writeDebug(this, "addAsynchServerThread", "uniqueKey: " + uniqueKey + " asynchServerThread: " + asynchServerThread);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            mapThread.put(uniqueKey, asynchServerThread);
		} else {
			throw new ExceptionInvalidUserKey();
		}
	}

	/**
	 * Removes the thread with the given unique key from the {@link #mapThread}.
	 * 
	 * @param uniqueKey A unique Key for the {@link #mapThread} as Map index
	 * @see Map
	 */
	public void removeAsynchServerThread(final String uniqueKey) {
		Logger.getInstance().writeDebug(this, "removeAsynchServerThread", "uniqueKey: " + uniqueKey);  //$NON-NLS-1$//$NON-NLS-2$
        mapThread.remove(uniqueKey);
	}
	
	/**
	 * Returns all threads
	 * 
	 * @return list containing all threads
	 * @see Map
	 */
	protected Map<String, ThreadServerAsynchTemplate> getThreads() {
		return Collections.unmodifiableMap(mapThread);
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public void removeAllData(final String uniqueKey) {
		super.removeAllData(uniqueKey);
		removeAsynchServerThread(uniqueKey);
	}
}
