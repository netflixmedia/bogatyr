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
package ch.sisprocom.bogatyr.controller.net;

import java.net.DatagramPacket;

import ch.sisprocom.bogatyr.misc.Listener;

/**
 * ListenerDatagram
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.8.0
 */
public interface ListenerDatagram extends Listener {
	/**
	 * Informs the listener that a packet {@link DatagramPacket} was received.
	 * 
	 * @param host of the received packet
	 * @param port of the received packet
	 * @param data as ASCII encoded string
	 * @param packet {@link DatagramPacket}
	 * @see DatagramPacket
	 * @since 0.8.0
	 */
	void packetReceived(String host, int port, String data, DatagramPacket packet);
	
	/**
	 * Informs the listener that the datagram controller has started.
	 * 
	 * @since 0.8.0
	 */
	void datagramStarted();
	
	/**
	 * Informs the listener that the datagram controller has stopped.
	 * 
	 * @since 0.8.0
	 */
	void datagramStopped();
}
