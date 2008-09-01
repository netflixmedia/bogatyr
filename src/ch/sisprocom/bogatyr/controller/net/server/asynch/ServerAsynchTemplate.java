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
package ch.sisprocom.bogatyr.controller.net.server.asynch;

import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import ch.sisprocom.bogatyr.controller.net.server.ServerTemplate;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.exception.ExceptionInvalidUserKey;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is the skeleton for asynchron servers
 *
 * @author Stefan Laubenberger
 * @version 20080901
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "addAsynchServerThread", new Object[]{uniqueKey, asynchServerThread}); //$NON-NLS-1$

		if (HelperGeneral.isValidString(uniqueKey) && HelperGeneral.isValidObject(asynchServerThread)) {
            mapThread.put(uniqueKey, asynchServerThread);
		} else {
			throw new ExceptionInvalidUserKey();
		}

		Logger.getInstance().writeMethodExit(this.getClass(), "addAsynchServerThread"); //$NON-NLS-1$

	}

	/**
	 * Removes the thread with the given unique key from the {@link #mapThread}.
	 * 
	 * @param uniqueKey A unique Key for the {@link #mapThread} as Map index
	 * @see Map
	 */
	public void removeAsynchServerThread(final String uniqueKey) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "removeAsynchServerThread", uniqueKey); //$NON-NLS-1$

		mapThread.remove(uniqueKey);

		Logger.getInstance().writeMethodExit(this.getClass(), "removeAsynchServerThread"); //$NON-NLS-1$
	}
	
	/**
	 * Returns all threads
	 * 
	 * @return list containing all threads
	 * @see Map
	 */
	protected Map<String, ThreadServerAsynchTemplate> getThreads() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getThreads"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getThreads", mapThread); //$NON-NLS-1$

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
