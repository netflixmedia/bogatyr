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
 * @author Silvan Spross
 * @version 0.70 (20090527)
 * @since 0.70
 */
public interface IServer extends Runnable {
	
	/**
	 * Returns the {@link ServerSocket} of the current server.
	 * 
	 * @return server-side socket
	 */
	ServerSocket getServerSocket();

	/**
	 * Returns the port of the {@link ServerSocket}.
	 * 
	 * @return port
	 */
	int getPort();

	/**
	 * Sets the port (0 - 65535) for the {@link ServerSocket}.
	 * 
	 * @param port for the server
	 */
	void setPort(int port);

	/**
	 * Returns the timeout in ms of the {@link ServerSocket}.
	 * 
	 * @return timeout in ms
	 */
	int getTimeout();

	/**
	 * Sets the timeout in ms for the {@link ServerSocket}.
	 * 
	 * @param timeout for the server
	 */
	void setTimeout(int timeout);
	
	/**
     * Start the server and open the {@link ServerSocket}.
     * 
     * @throws IOException
     */
	void start() throws IOException;

	/**
     * Stop the server and close the {@link ServerSocket} and quit all threads.
     * 
     * @throws IOException
     */
	void stop() throws IOException;

	/**
	 * Returns the state of the server.
	 * 
	 * @return true/false
	 */
    boolean isRunning();
	
	/**
	 * Adds a new thread to the map.
	 * 
	 * @param uuid unique key for the {@link Map} index
	 * @param serverThread asynchron server thread
	 */
	void addServerThread(final UUID uuid, final IServerThread serverThread);

	/**
	 * Removes the thread with the given unique key from the {@link Map}.
	 * 
	 * @param uuid unique key for the map index
	 */
	void removeServerThread(final UUID uuid);
	
	/**
	 * Returns all threads.
	 * 
	 * @return list containing all threads
	 */
	Map<UUID, IServerThread> getServerThreads();
}   

