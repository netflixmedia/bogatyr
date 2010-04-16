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
package net.laubenberger.bogatyr.controller.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import net.laubenberger.bogatyr.helper.HelperNumber;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObjectAbstract;


/**
 * This is the skeleton for servers.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public abstract class ServerAbstract extends ExtendedObjectAbstract implements Server, ListenerServerThread {
	private Thread thread;

	private final Collection<ServerThread> listThread = new HashSet<ServerThread>();

//    private final Map<UUID, ServerThread> mapThread = new ConcurrentHashMap<UUID, ServerThread>();

	private ServerSocket serverSocket;
	private int port;
	private int timeout; //ServerSocketSocket timeout in milliseconds

	private boolean isRunning;

	protected ServerAbstract() {
		super();
	}

	protected ServerAbstract(final int port, final int timeout) {
		super();

		setPort(port);
		setTimeout(timeout);
	}

	protected ServerAbstract(final int port) {
		this(port, 0);
	}

	/**
	 * Returns the current {@link Thread} of the server.
	 *
	 * @return thread of the server
	 * @see Thread
	 * @since 0.7.0
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Sets the current {@link Thread} for the server.
	 *
	 * @param thread for the server
	 * @see Thread
	 * @since 0.8.0
	 */
	protected void setThread(final Thread thread) {
		if (null == thread) {
			throw new RuntimeExceptionIsNull("thread"); //$NON-NLS-1$
		}

		this.thread = thread;
	}

	protected void setRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}


	/*
	 * Implemented methods
	 */
	@Override
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public int getTimeout() {
		return timeout;
	}

	@Override
	public void setPort(final int port) {
		if (0 >= port) {
			throw new RuntimeExceptionMustBeGreater("port", port, 0); //$NON-NLS-1$
		}
		if (HelperNumber.NUMBER_65536.intValue() <= port) {
			throw new RuntimeExceptionMustBeSmaller("port", port, 65535); //$NON-NLS-1$
		}

		this.port = port;
	}

	@Override
	public void setServerSocket(final ServerSocket serverSocket) {
		if (null == serverSocket) {
			throw new RuntimeExceptionIsNull("serverSocket"); //$NON-NLS-1$
		}

		this.serverSocket = serverSocket;
	}

	@Override
	public void setTimeout(final int timeout) {
		if (0 > timeout) {
			throw new RuntimeExceptionMustBeGreater("timeout", timeout, 0); //$NON-NLS-1$
		}

		this.timeout = timeout;
	}

//    public void addServerThread(final UUID uuid, final ServerThread serverThread) {
//        mapThread.put(uuid, serverThread);
//    }
//
//    public void removeServerThread(final UUID uuid) {
//        mapThread.remove(uuid);
//    }
//

	@Override
	public Collection<ServerThread> getServerThreads() {
		return Collections.unmodifiableCollection(listThread);
	}

	@Override
	public void start() throws IOException {
		serverSocket = new ServerSocket(port);

		if (0 < timeout) {
			serverSocket.setSoTimeout(timeout);
		}

		thread = new Thread(this);
		thread.start();

		isRunning = true;
	}

	@Override
	public void stop() throws IOException {
		isRunning = false;

		if (null != serverSocket && !serverSocket.isClosed()) {
			serverSocket.close();
		}

		for (final ServerThread thread : listThread) {
			thread.stop();
		}

		if (null != thread) {
			if (thread.isAlive()) {
				thread.interrupt();
//			} else {
//				thread = null;
			}
		}
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void serverThreadStarted(final Event<ServerThread> event) {
		if (null == event) {
			throw new RuntimeExceptionIsNull("event"); //$NON-NLS-1$
		}

		listThread.add(event.getSource());
	}

	@Override
	public void serverThreadStopped(final Event<ServerThread> event) {
		if (null == event) {
			throw new RuntimeExceptionIsNull("event"); //$NON-NLS-1$
		}

		listThread.remove(event.getSource());
	}
}
