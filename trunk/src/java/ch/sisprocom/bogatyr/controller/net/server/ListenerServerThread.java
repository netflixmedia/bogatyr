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

import ch.sisprocom.bogatyr.misc.Listener;


/**
 * ListenerServerThread
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20100201)
 * @since 0.8.0
 */
public interface ListenerServerThread extends Listener {
	/**
	 * Sends the read data from the server thread.
     *
     * @param serverThread as reference
     * @param data read data from the server thread
     * @since 0.8.0
	 */
	void serverThreadStreamRead(ServerThread serverThread, byte[] data);
	
	/**
	 * Informs the listener that the server thread has started.
	 * 
     * @param serverThread as reference
     * 
	 * @since 0.8.0
	 */
	void serverThreadStarted(ServerThread serverThread);
	
	/**
	 * Informs the listener that the server thread has stopped.
	 * 
	 * @param serverThread as reference
	 * @since 0.8.0
	 */
	void serverThreadStopped(ServerThread serverThread);
}
