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

package net.laubenberger.bogatyr.controller.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;

import net.laubenberger.bogatyr.misc.HolderListener;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObject;


/**
 * Defines the methods for the implementation of the datagram dumper.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
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
