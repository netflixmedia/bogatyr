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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.controller.net.server;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;


/**
 * This is the skeleton for SSL secured servers.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100416)
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
