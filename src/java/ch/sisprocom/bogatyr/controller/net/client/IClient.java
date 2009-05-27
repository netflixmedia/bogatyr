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
package ch.sisprocom.bogatyr.controller.net.client;

import java.io.IOException;
import java.net.Socket;


/**
 * Defines the methods for the implementation of the client.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.8.0 (20090527)
 * @since 0.7.0
 */
public interface IClient extends Runnable {

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
	 * @return socket
	 * @since 0.7.0
	 */
	Socket getSocket();
	
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
    void writeStream(final byte[] data) throws IOException;
    
	/**
	 * Adds a listener for this client.
	 * 
	 * @param listener to add
	 * @since 0.7.0
	 */
	void addListener(ListenerClient listener);
	
	/**
	 * Remove a listener for this client.
	 * 
	 * @param listener to remove
	 * @since 0.7.0
	 */
	void removeListener(ListenerClient listener);

	/**
	 * Remove all listeners for this client.
	 * 
	 * @since 0.7.0
	 */
	void removeAllListener();
}   
