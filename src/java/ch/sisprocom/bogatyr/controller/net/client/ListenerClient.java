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
package ch.sisprocom.bogatyr.controller.net.client;

import ch.sisprocom.bogatyr.misc.Listener;

/**
 * ListenerClient
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public interface ListenerClient extends Listener {
	/**
	 * Sends the read data from the client.
     *
     * @param data read data from the client
     * @since 0.7.0
	 */
	void clientStreamRead(byte[] data);
	
	/**
	 * Informs the listener that the client has started.
	 * 
	 * @since 0.7.0
	 */
	void clientStarted();
	
	/**
	 * Informs the listener that the client has stopped.
	 * 
	 * @since 0.7.0
	 */
	void clientStopped();
}
