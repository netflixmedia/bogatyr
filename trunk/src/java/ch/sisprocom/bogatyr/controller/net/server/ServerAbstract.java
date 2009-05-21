/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This is the skeleton for servers.
 *
 * @author Stefan Laubenberger
 * @version 20090521
 */
public abstract class ServerAbstract { //TODO document in Wiki!
	private final Map<UUID, ThreadServerAbstract> mapThread = new ConcurrentHashMap<UUID, ThreadServerAbstract>();

	
	private ServerSocket serverSocket;
	private final int port;
	private final int timeout; //ServerSocketSocket timeout in milliseconds


	protected ServerAbstract(final int port, final int timeout) {
		super();
		
		this.port = port;
		this.timeout = timeout;
		
		init();
	}

	protected ServerAbstract(final int port) {
		this(port, 0); //TODO set default parameters
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public int getPort() {
		return port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	/**
	 * Adds a new thread to the {@link #mapThread}.
	 * 
	 * @param uniqueKey unique Key for the {@link #mapThread} as Map index
	 * @param serverThread asynchron server thread
	 * @see Map
	 */
	public void addServerThread(final UUID uuid, final ThreadServerAbstract serverThread) {
            mapThread.put(uuid, serverThread);

	}

	/**
	 * Removes the thread with the given unique key from the {@link #mapThread}.
	 * 
	 * @param uniqueKey unique Key for the {@link #mapThread} as Map index
	 * @see Map
	 */
	public void removeServerThread(final UUID uuid) {
		mapThread.remove(uuid);
	}
	
	/**
	 * Returns all threads.
	 * 
	 * @return list containing all threads
	 * @see Map
	 */
	protected Map<UUID, ThreadServerAbstract> getThreads() {
		return Collections.unmodifiableMap(mapThread);
	}

	/*
	 * Private methods
	 */
	private void init() {
        try {
			serverSocket = new ServerSocket(port);
		
			if (timeout > 0) {
	            serverSocket.setSoTimeout(timeout);
	        }
		} catch (IOException ex) {
			throw new RuntimeException("Couldn't create server socket: " + ex.getLocalizedMessage()); //$NON-NLS-1$
		}

	}
}
