/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.controller.net.server.synch;

import java.net.Socket;

import ch.orwell.bogatyr.controller.net.server.ServerTemplate;
import ch.orwell.bogatyr.controller.net.server.ThreadServerTemplate;


/**
 * This is the Skeleton for synchron server threads
 *  
 * @author Stefan Laubenberger
 * @version 20080526
 */
public abstract class ThreadServerSynchTemplate extends ThreadServerTemplate {

	protected ThreadServerSynchTemplate(final ServerTemplate server, final Socket socket) {
		super(server, socket);
	}
}
