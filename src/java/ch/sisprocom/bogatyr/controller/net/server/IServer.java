/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
import java.util.Map;
import java.util.UUID;



/**
 * Defines the methods for the implementation of the server.
 * 
 * @author Stefan Laubenberger
 * @version 20090521
 */
public interface IServer extends Runnable {
	
	ServerSocket getServerSocket();

	int getPort();

	int getTimeout();

	void setPort(int port);

	void setTimeout(int timeout);
	void start() throws IOException;
	
	void stop() throws IOException;

	boolean isStopped();
	
	/**
	 * Adds a new thread to the {@link #mapThread}.
	 * 
	 * @param uniqueKey unique Key for the {@link #mapThread} as Map index
	 * @param serverThread asynchron server thread
	 * @see Map
	 */
	void addServerThread(final UUID uuid, final ThreadServerAbstract serverThread);

	/**
	 * Removes the thread with the given unique key from the {@link #mapThread}.
	 * 
	 * @param uniqueKey unique Key for the {@link #mapThread} as Map index
	 * @see Map
	 */
	void removeServerThread(final UUID uuid);
	
	/**
	 * Returns all threads.
	 * 
	 * @return list containing all threads
	 * @see Map
	 */
	Map<UUID, ThreadServerAbstract> getServerThreads();
}   

