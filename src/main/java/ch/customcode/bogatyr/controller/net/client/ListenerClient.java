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
package ch.customcode.bogatyr.controller.net.client;

import ch.customcode.bogatyr.misc.Event;
import ch.customcode.bogatyr.misc.Listener;

/**
 * ListenerClient
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20100209)
 * @since 0.7.0
 */
public interface ListenerClient extends Listener {
	/**
	 * Sends the read data from the client.
     *
     * @since 0.7.0
	 */
	void clientStreamRead(Event<Client> event);
	
	/**
	 * Informs the listener that the client has started.
	 * 
	 * @since 0.7.0
	 */
	void clientStarted(Event<Client> event);
	
	/**
	 * Informs the listener that the client has stopped.
	 * 
	 * @since 0.7.0
	 */
	void clientStopped(Event<Client> event);
}
