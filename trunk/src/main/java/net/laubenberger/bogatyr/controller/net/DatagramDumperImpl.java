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

package net.laubenberger.bogatyr.controller.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Collection;
import java.util.HashSet;

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
 * This is a datagram dumper to analyse network packets (UDP) on a given port.
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100813)
 * @since 0.8.0
 */
public class DatagramDumperImpl extends ExtendedObjectAbstract implements DatagramDumper {
	private static final Logger log = LoggerFactory.getLogger(DatagramDumperImpl.class);
	
	private final Event<DatagramDumper> event = new Event<DatagramDumper>(this);

	private Thread thread;

	private final Collection<ListenerDatagram> listeners = new HashSet<ListenerDatagram>();

	private int port;
	private DatagramSocket socket;
	final byte[] buffer = new byte[Short.MAX_VALUE];
	final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

	private boolean isRunning;


	public DatagramDumperImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public DatagramDumperImpl(final int port) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(port));

		setPort(port);
	}

	/**
	 * Returns the current {@link Thread} of the controller.
	 *
	 * @return thread of the controller
	 * @since 0.8.0
	 */
	public Thread getThread() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		return thread;
	}


	/*
	 * Private methods
	 */

	private void firePacketReceived() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		for (final ListenerDatagram listener : listeners) {
			listener.packetReceived(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	private void fireStarted() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		isRunning = true;

		for (final ListenerDatagram listener : listeners) {
			listener.datagramStarted(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	private void fireStopped() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		isRunning = false;

		for (final ListenerDatagram listener : listeners) {
			listener.datagramStopped(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}


	/*
	 * Implemented methods
	 */
	
	@Override
	public void run() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		try {
			while (!thread.isInterrupted()) {
				socket.receive(packet);
				
				firePacketReceived();
			}
		} catch (IOException ex) {
			log.error("Could not receive datagrams", ex); //$NON-NLS-1$
		}
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public DatagramPacket getPacket() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(packet));
		return packet;
	}

	@Override
	public int getPort() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(port));
		return port;
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
	public void start() throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		socket = new DatagramSocket(port);

		thread = new Thread(this);
		thread.start();

		fireStarted();
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void stop() {
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
	public boolean isRunning() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(isRunning));
		return isRunning;
	}

	@Override
	public void addListener(final ListenerDatagram listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.add(listener);
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void deleteListener(final ListenerDatagram listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.remove(listener);
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}
