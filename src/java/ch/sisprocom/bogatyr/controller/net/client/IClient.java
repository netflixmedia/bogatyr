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
 * @version 20090521
 */
public interface IClient extends Runnable {

	String getHost();
	int getPort();
	Socket getSocket();
	void setHost(String host);
	void setPort(int port);
	boolean isStopped();
	
	/**
     * Start the client and open the {@link Socket}.
	 * @return 
     * 
     * @throws IOException
     */
	void start() throws IOException;
	
	/**
     * Stop the client and close the {@link Socket}.
     * 
     * @throws IOException
     */
	void stop() throws IOException;

	/**
     * Reads a socket-stream to a byte-array.
     * 
     * @return byte-array
	 * @throws IOException 
     */
	byte[] readStream() throws IOException;
	
    /**
     * Writes on a socket-stream from a byte-array.
     * 
     * @param data a byte-array
     * @throws Exception 
     */
    void writeStream(final byte[] data) throws IOException;
}   

