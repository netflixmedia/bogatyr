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
import java.net.Socket;
import java.util.UUID;



/**
 * Defines the methods for the implementation of the server thread.
 * 
 * @author Stefan Laubenberger
 * @version 20090522
 */
public interface IServerThread extends Runnable {
	/**
	 * Returns the current {@link Thread}.
	 * 
	 * @return thread
	 */
	Thread getThread();

	/**
	 * Returns the an universally unique identifier {@link UUID} of the thread.
	 * 
	 * @return universally unique identifier
	 */
	UUID getUuid();

	/**
	 * Returns the current {@link Socket} of the thread.
	 * 
	 * @return socket
	 */
	Socket getSocket();

	/**
	 * Returns the server (owner) of this thread.
	 * 
	 * @return server
	 */
	IServer getServer();

	/**
     * Reads a socket-stream.
     * 
     * @return byte array from stream
	 * @throws IOException
     */
	byte[] readStream() throws IOException;

    /**
     * Writes on a socket-stream.
     * 
     * @param data
     * @throws IOException 
     */
    void writeStream(final byte[] data) throws IOException;

	/**
	 * Stops the thread. And closes the open socket.
	 * 
	 * @throws IOException 
	 */
	void stop() throws IOException; 

	/**
	 * Starts the thread with {@link Thread#MIN_PRIORITY}.
	 */
	void start();
    
	/**
	 * Returns the state of the thread.
	 * 
	 * @return true/false
	 */
    boolean isRunning();
}   
