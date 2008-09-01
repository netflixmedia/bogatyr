/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.net.server.synch;

import java.net.Socket;

import ch.sisprocom.bogatyr.controller.net.server.ServerTemplate;
import ch.sisprocom.bogatyr.controller.net.server.ThreadServerTemplate;


/**
 * This is the Skeleton for synchron server threads
 *  
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class ThreadServerSynchTemplate extends ThreadServerTemplate {

	protected ThreadServerSynchTemplate(final ServerTemplate server, final Socket socket) {
		super(server, socket);
	}
}
