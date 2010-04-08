/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.controller.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;

import ch.customcode.bogatyr.misc.HolderListener;
import ch.customcode.bogatyr.misc.extendedObject.ExtendedObject;


/**
 * Defines the methods for the implementation of the datagram dumper.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.8.0
 */
public interface DatagramDumper extends ExtendedObject, Runnable, HolderListener<ListenerDatagram> {
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
	 * Returns the current {@link DatagramPacket}.
	 * 
	 * @return current {@link DatagramPacket}
	 * @see DatagramPacket
	 * @since 0.9.0
	 */
	DatagramPacket getPacket();
}