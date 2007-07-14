/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.net.server.synch;

import java.net.Socket;

import ch.orwell.bogatyr.net.server.ServerThread;


/**
 * This is the Skeleton for all synchron server threads
 *  
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class SynchServerThread extends ServerThread {

	/**
	 * Constructs a synchronized server.
	 * 
	 * @param socket The Socket to start the server thread.
	 */
	public SynchServerThread(Socket socket) {
		super(socket);
	}
}
