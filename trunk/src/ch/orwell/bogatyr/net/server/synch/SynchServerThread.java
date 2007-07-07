/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.net.server.synch;

import java.net.Socket;

import ch.orwell.bogatyr.net.server.ServerThread;


/**
 * This is the Skeleton for all synchron server threads
 *  
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public abstract class SynchServerThread extends ServerThread {

	/**
	 * Constructs a synchronized server.<br>
	 * @param socket The Socket to start the server thread.
	 */
	public SynchServerThread(Socket socket) {
		super(socket);
	}
}
