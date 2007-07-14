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
package ch.orwell.bogatyr.net.server.asynch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.exception.InvalidUserKeyException;
import ch.orwell.bogatyr.net.server.Server;
import ch.orwell.bogatyr.util.GeneralHelper;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the skeleton for all asynchron servers
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class AsynchServer extends Server {

	protected Map<String, AsynchServerThread> threadList = new ConcurrentHashMap<String, AsynchServerThread>();

	private Thread thread;

	/**
	 * Constructs a AsynchServer.
	 * Starts a new Thread.
	 * 
	 * @param propertiesFileName Properties file for the server configuration.
	 */
	public AsynchServer(String propertiesFileName) {
		super(propertiesFileName);
		
		this.thread = new Thread(this);
		this.thread.start();
		
		Context.getInstance().addData(ATT_INSTANCE, this);
	}
	
	/**
	 * Adds a new thread to the {@link #threadList}.
	 * 
	 * @param uniqueKey A unique Key for the {@link #threadList} as Map index
	 * @param asynchServerThread An asynchron server thread
	 * @throws InvalidUserKeyException
	 * @see Map
	 */
	public final void addAsynchServerThread(String uniqueKey, AsynchServerThread asynchServerThread) throws InvalidUserKeyException {
		if (GeneralHelper.isValidString(uniqueKey) && GeneralHelper.isValidObject(asynchServerThread)) {
			Logger.getInstance().writeDebug(className + "::addAsynchServerThread", "uniqueKey: " + uniqueKey + " asynchServerThread: " + asynchServerThread);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			this.threadList.put(uniqueKey, asynchServerThread);
		} else {
			throw new InvalidUserKeyException();
		}
	}

	/**
	 * Removes the thread with the given unique key from the {@link #threadList}.
	 * 
	 * @param uniqueKey A unique Key for the {@link #threadList} as Map index
	 * @see Map
	 */
	public final void removeAsynchServerThread(String uniqueKey) {
		Logger.getInstance().writeDebug(className + "::removeAsynchServerThread", "uniqueKey: " + uniqueKey);  //$NON-NLS-1$//$NON-NLS-2$
		this.threadList.remove(uniqueKey);
	}

	
	/*
	 * Overriden methods
	 */
	@Override
	public void removeAllData(String uniqueKey) {
		super.removeAllData(uniqueKey);
		removeAsynchServerThread(uniqueKey);
	}
}
