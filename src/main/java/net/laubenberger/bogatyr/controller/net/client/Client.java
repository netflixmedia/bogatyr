/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.controller.net.client;

import java.io.IOException;
import java.net.Socket;

import net.laubenberger.bogatyr.misc.HolderListener;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObject;


/**
 * Defines the methods for the implementation of the client.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.4 (20101103)
 * @since 0.7.0
 */
public interface Client extends ExtendedObject, Runnable, HolderListener<ListenerClient> {
	/**
	 * Returns the current data from readStream().
	 *
	 * @return current read data
	 * @since 0.9.0
	 */
	byte[] getData();

	/**
	 * Returns the host of the {@link Socket}.
	 *
	 * @return host
	 * @since 0.7.0
	 */
	String getHost();

	/**
	 * Sets the host for the {@link Socket}.
	 *
	 * @param host of the server
	 * @since 0.7.0
	 */
	void setHost(String host);

	/**
	 * Returns the port of the {@link Socket}.
	 *
	 * @return port
	 * @since 0.7.0
	 */
	int getPort();

	/**
	 * Sets the port (0 - 65535) for the {@link Socket}.
	 *
	 * @param port of the server
	 * @since 0.7.0
	 */
	void setPort(int port);

	/**
	 * Returns the current {@link Socket} of the client.
	 *
	 * @return {@link Socket} of the client
	 * @since 0.7.0
	 */
	Socket getSocket();

	/**
	 * Sets the {@link Socket} for the client.
	 *
	 * @param socket for the client
	 * @see Socket
	 * @since 0.9.0
	 */
	void setSocket(Socket socket);

	/**
	 * Start the client and open the {@link Socket}.
	 *
	 * @throws IOException
	 * @since 0.7.0
	 */
	void start() throws IOException;

	/**
	 * Stop the client and close the {@link Socket}.
	 *
	 * @throws IOException
	 * @since 0.7.0
	 */
	void stop() throws IOException;

	/**
	 * Returns the state of the connection from the client to the server.
	 *
	 * @return true/false
	 * @since 0.7.0
	 */
	boolean isRunning();

	/**
	 * Reads a socket-stream to a byte-array.
	 *
	 * @return byte-array
	 * @throws IOException
	 * @since 0.7.0
	 */
	byte[] readStream() throws IOException;

	/**
	 * Writes on a socket-stream from a byte-array.
	 *
	 * @param data to write on a socket
	 * @throws IOException
	 * @since 0.7.0
	 */
	void writeStream(byte... data) throws IOException;
}   
