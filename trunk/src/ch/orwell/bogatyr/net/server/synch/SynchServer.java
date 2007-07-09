/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.net.server.synch;

import ch.orwell.bogatyr.net.server.Server;


/**
 * This is the skeleton for all synchron servers
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class SynchServer extends Server {

	/**
	 * Constructs a synchron server.
	 * 
	 * @param propertiesFileName Properties file for the server configuration.
	 */
    public SynchServer(String propertiesFileName) {
		super(propertiesFileName);
 	}
}
