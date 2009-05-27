/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;


/**
 * This is the skeleton for SSL secured servers.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.8.0 (20090527)
 * @since 0.8.0
 */
public abstract class ServerSSLAbstract extends ServerAbstract {
    protected ServerSSLAbstract() {
    	super();
    }

    protected ServerSSLAbstract(final int port, final int timeout) {
        super(port, timeout);
    }

    protected ServerSSLAbstract(final int port) {
        super(port);
    }


	/*
	 * Overridden methods
	 */
    @Override
	public void start() throws IOException {
    	setRunning(true);

    	final ServerSocketFactory sslFactory = SSLServerSocketFactory.getDefault();
    	final ServerSocket serverSocket = sslFactory.createServerSocket(getPort());

        if (0 < getTimeout()) {
            serverSocket.setSoTimeout(getTimeout());
        }

    	setServerSocket(serverSocket);

		setThread(new Thread(this));
        getThread().start();
    }
}
