/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
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

import ch.sisprocom.bogatyr.misc.HolderListener;
import ch.sisprocom.bogatyr.misc.extendedObject.ExtendedObject;



/**
 * Defines the methods for the implementation of the server thread.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.7.0
 */
public interface ServerThread extends ExtendedObject, Runnable, HolderListener<ListenerServerThread> {
	/**
	 * Returns the current data from readStream().
	 * 
	 * @return current readed data
	 * @since 0.9.0
	 */
	byte[] getData();

	/**
	 * Returns the current {@link Socket} of the thread.
	 * 
	 * @return {@link Socket}
	 * @see Socket
	 * @since 0.7.0
	 */
	Socket getSocket();
	
	/**
	 * Sets the {@link Socket} for the thread.
	 * 
	 * @param socket for the client
	 * @see Socket
	 * @since 0.9.0
	 */
    void setSocket(Socket socket);

	/**
     * Reads a socket-stream.
     * 
     * @return byte array from stream
	 * @throws IOException
	 * @since 0.7.0
     */
	byte[] readStream() throws IOException;

    /**
     * Writes on a socket-stream.
     * 
     * @param data to write on a socket
     * @throws IOException 
     * @since 0.7.0
     */
    void writeStream(byte[] data) throws IOException;

	/**
	 * Stops the thread. And closes the open socket.
	 * 
	 * @throws IOException 
	 * @since 0.7.0
	 */
	void stop() throws IOException; 

	/**
	 * Starts the server thread.
	 * 
	 * @since 0.7.0
	 */
	void start();
    
	/**
	 * Returns the state of the thread.
	 * 
	 * @return true/false
	 * @since 0.7.0
	 */
    boolean isRunning();
}   
