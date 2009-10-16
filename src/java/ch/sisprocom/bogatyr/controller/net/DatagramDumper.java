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
package ch.sisprocom.bogatyr.controller.net;

import java.io.IOException;
import java.net.Socket;


/**
 * Defines the methods for the implementation of the datagram dumper.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091016)
 * @since 0.8.0
 */
public interface DatagramDumper extends Runnable {
	/**
     * Start the datagram controller on the given port.
     * 
     * @throws IOException
     * @since 0.8.0
     */
	void start() throws IOException;
	
	/**
     * Stop the datagram controller.
     * 
     * @since 0.8.0
     */
	void stop();
	
	/**
	 * Returns the state of the datagram controller.
	 * 
	 * @return true/false
	 * @since 0.8.0
	 */
    boolean isRunning();
    
	/**
	 * Returns the port of the {@link Socket}.
	 * 
	 * @return port
	 * @since 0.8.0
	 */
	int getPort();

	/**
	 * Sets the port (0 - 65535) for the {@link Socket}.
	 * 
	 * @param port of the server
	 * @since 0.8.0
	 */
	void setPort(int port);
	
	/**
	 * Adds a listener for packets.
	 * 
	 * @param listener to add
	 * @since 0.8.0
	 */
	void addListener(ListenerDatagram listener);
	
	/**
	 * Remove a listener for packets.
	 * 
	 * @param listener to remove
	 * @since 0.8.0
	 */
	void removeListener(ListenerDatagram listener);

	/**
	 * Remove all listeners for packets.
	 * 
	 * @since 0.8.0 
	 */
	void removeAllListener();
}
