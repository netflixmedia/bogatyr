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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.controller.net.server;

import ch.customcode.bogatyr.misc.Event;
import ch.customcode.bogatyr.misc.Listener;



/**
 * ListenerServerThread
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20100205)
 * @since 0.8.0
 */
public interface ListenerServerThread extends Listener {
	/**
	 * Sends the read data from the server thread.
     *
 	 * @param event for the listener
     * @since 0.8.0
	 */
	void serverThreadStreamRead(Event<ServerThread> event);
	
	/**
	 * Informs the listener that the server thread has started.
	 * 
	 * @param event for the listener
	 * @since 0.8.0
	 */
	void serverThreadStarted(Event<ServerThread> event);
	
	/**
	 * Informs the listener that the server thread has stopped.
	 * 
	 * @param event for the listener
	 * @since 0.8.0
	 */
	void serverThreadStopped(Event<ServerThread> event);
}
