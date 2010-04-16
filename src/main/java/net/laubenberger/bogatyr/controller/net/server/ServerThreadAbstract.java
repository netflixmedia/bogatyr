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
import java.io.InputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperIO;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObjectAbstract;


/**
 * This is a skeleton for server threads.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public abstract class ServerThreadAbstract extends ExtendedObjectAbstract implements ServerThread {
	private final Event<ServerThread> event = new Event<ServerThread>(this);

	private Thread thread;

	private Collection<ListenerServerThread> listListener = new HashSet<ListenerServerThread>();

	private Socket socket;

	private boolean isRunning;


	protected ServerThreadAbstract(final Socket socket) {
		super();
		setSocket(socket);
	}

	/**
	 * Returns the current {@link Thread}.
	 *
	 * @return thread
	 * @see Thread
	 * @since 0.7.0
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Sets the current {@link Thread}.
	 *
	 * @param thread for the client
	 * @see Thread
	 * @since 0.9.0
	 */
	protected void setThread(final Thread thread) {
		if (null == thread) {
			throw new RuntimeExceptionIsNull("thread"); //$NON-NLS-1$
		}

		this.thread = thread;
	}


	/*
	 * Private methods
	 */
	protected void fireStreamRead() {
		for (final ListenerServerThread listener : listListener) {
			listener.serverThreadStreamRead(event);
		}
	}

	protected void fireStarted() {
		isRunning = true;

		for (final ListenerServerThread listener : listListener) {
			listener.serverThreadStarted(event);
		}
	}

	protected void fireStopped() {
		isRunning = false;

		for (final ListenerServerThread listener : listListener) {
			listener.serverThreadStopped(event);
		}
	}


	/*
	 * Implemented methods
	 */
	@Override
	public Socket getSocket() {
		return socket;
	}

	@Override
	public void setSocket(final Socket socket) {
		if (null == socket) {
			throw new RuntimeExceptionIsNull("socket"); //$NON-NLS-1$
		}

		this.socket = socket;
	}

	@Override
	public byte[] readStream() throws IOException {
		final InputStream is = socket.getInputStream();
		byte[] result = HelperArray.EMPTY_ARRAY_BYTE;
		byte input;

		do {
			input = (byte) is.read();

			if (-1 != input) {
				result = HelperArray.concatenate(result, new byte[]{input});
			}
		} while (-1 != input);

		if (null == result) { //client lost
			stop();
		}
		return result;
	}

	@Override
	public void writeStream(final byte[] data) throws IOException {
		HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{(byte) -1}));
	}


	@Override
	public void start() {
//		if (null == thread) {
		thread = new Thread(this);
		thread.start();
//		}

		fireStarted();
	}

	@Override
	public void stop() throws IOException {
		fireStopped();

		if (null != socket && !socket.isClosed()) {
			socket.close();
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
	public synchronized void addListener(final ListenerServerThread listener) {
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.add(listener);
	}

	@Override
	public synchronized void deleteListener(final ListenerServerThread listener) {
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.remove(listener);
	}

	@Override
	public synchronized void deleteListeners() {
		listListener = new HashSet<ListenerServerThread>();
	}

	@Override
	public int countListeners() {
		return listListener.size();
	}
}
