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

package net.laubenberger.bogatyr.controller.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperIO;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperNumber;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObjectAbstract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the skeleton for clients.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.4 (20101103)
 * @since 0.7.0
 */
public abstract class ClientAbstract extends ExtendedObjectAbstract implements Client {
	private static final Logger log = LoggerFactory.getLogger(ClientAbstract.class);
	
	private final Event<Client> event = new Event<Client>(this);

	private Thread thread;

	private final Collection<ListenerClient> listeners = new HashSet<ListenerClient>();

	private String host;
	private int port;
	private Socket socket;

	private boolean isRunning;

	protected ClientAbstract() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	protected ClientAbstract(final String host, final int port) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(host, port));
		
		setHost(host);
		setPort(port);
	}

	/**
	 * Returns the current {@link Thread} of the client.
	 *
	 * @return thread of the client
	 * @see Thread
	 * @since 0.7.0
	 */
	public Thread getThread() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(thread));
		return thread;
	}

	/**
	 * Sets the current {@link Thread} for the client.
	 *
	 * @param thread for the client
	 * @see Thread
	 * @since 0.8.0
	 */
	protected void setThread(final Thread thread) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(thread));
		
		if (null == thread) {
			throw new RuntimeExceptionIsNull("thread"); //$NON-NLS-1$
		}

		this.thread = thread;
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}


	/*
	 * Private methods
	 */

	protected void fireStreamRead() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		for (final ListenerClient listener : listeners) {
			listener.clientStreamRead(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	protected void fireStarted() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		isRunning = true;

		for (final ListenerClient listener : listeners) {
			listener.clientStarted(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	protected void fireStopped() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		isRunning = false;

		for (final ListenerClient listener : listeners) {
			listener.clientStopped(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public String getHost() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(host));
		return host;
	}

	@Override
	public int getPort() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(port));
		return port;
	}

	@Override
	public Socket getSocket() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(socket));
		return socket;
	}

	@Override
	public void setHost(final String host) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(host));
		
		if (null == host) {
			throw new RuntimeExceptionIsNull("host"); //$NON-NLS-1$
		}

		this.host = host;
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setPort(final int port) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(port));
		
		if (0 >= port) {
			throw new RuntimeExceptionMustBeGreater("port", port, 0); //$NON-NLS-1$
		}
		if (HelperNumber.NUMBER_65536.intValue() <= port) {
			throw new RuntimeExceptionMustBeSmaller("port", port, 65535); //$NON-NLS-1$
		}

		this.port = port;
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setSocket(final Socket socket) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(socket));
		
		if (null == socket) {
			throw new RuntimeExceptionIsNull("socket"); //$NON-NLS-1$
		}

		this.socket = socket;
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void start() throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		socket = new Socket(host, port);

		thread = new Thread(this);
		thread.start();

		fireStarted();
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void stop() throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
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
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public byte[] readStream() throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		byte[] result = HelperArray.EMPTY_ARRAY_BYTE;

		if (socket.isClosed()) {
			stop();
		} else {
			final InputStream is = socket.getInputStream();
			byte input;

			do {
				input = (byte) is.read();

				if (-1 != input) {
					result = HelperArray.concatenate(result, new byte[]{input});
				}
			} while (-1 != input);

			if (null == result) { //server lost
				stop();
			}
		}
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public void writeStream(final byte... data) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(data));
		
		HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{(byte) -1}));
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public boolean isRunning() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(isRunning));
		return isRunning;
	}

	@Override
	public void addListener(final ListenerClient listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.add(listener);
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void deleteListener(final ListenerClient listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.remove(listener);
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}
