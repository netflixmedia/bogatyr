/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.controller.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;

import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObject;


/**
 * Defines the methods for the implementation of the server.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.2 (20100512)
 * @since 0.7.0
 */
public interface Server extends ExtendedObject, Runnable {
	/**
	 * Returns the {@link ServerSocket} of the current server.
	 *
	 * @return {@link ServerSocket}
	 * @see ServerSocket
	 * @since 0.7.0
	 */
	ServerSocket getServerSocket();

	/**
	 * Sets the {@link ServerSocket} for the server.
	 *
	 * @param serverSocket for the server
	 * @see ServerSocket
	 * @since 0.9.0
	 */
	void setServerSocket(ServerSocket serverSocket);

	/**
	 * Returns the port of the {@link ServerSocket}.
	 *
	 * @return port
	 * @since 0.7.0
	 */
	int getPort();

	/**
	 * Sets the port (0 - 65535) for the {@link ServerSocket}.
	 *
	 * @param port for the server
	 * @since 0.7.0
	 */
	void setPort(int port);

	/**
	 * Returns the timeout in ms of the {@link ServerSocket}.
	 *
	 * @return timeout in ms
	 * @since 0.7.0
	 */
	int getTimeout();

	/**
	 * Sets the timeout in ms for the {@link ServerSocket}.
	 *
	 * @param timeout for the server
	 * @since 0.7.0
	 */
	void setTimeout(int timeout);

	/**
	 * Start the server and open the {@link ServerSocket}.
	 *
	 * @throws IOException
	 * @since 0.7.0
	 */
	void start() throws IOException;

	/**
	 * Stop the server and close the {@link ServerSocket} and quit all threads.
	 *
	 * @throws IOException
	 * @since 0.7.0
	 */
	void stop() throws IOException;

	/**
	 * Returns the state of the server.
	 *
	 * @return true/false
	 * @since 0.7.0
	 */
	boolean isRunning();

	/**
	 * Returns a {@link Collection} of all server threads.
	 *
	 * @return {@link Collection} containing all {@link ServerThread}
	 * @since 0.8.0
	 */
	Collection<ServerThread> getServerThreads();
}   

