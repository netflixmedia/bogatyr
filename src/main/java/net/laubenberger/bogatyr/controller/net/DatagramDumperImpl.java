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
package net.laubenberger.bogatyr.controller.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Collection;
import java.util.HashSet;

import net.laubenberger.bogatyr.helper.HelperNumber;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObjectAbstract;

/**
 * This is a datagram dumper to analyse network packets (UDP) on a given port.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.8.0
 */
public class DatagramDumperImpl extends ExtendedObjectAbstract implements DatagramDumper {
	private final Event<DatagramDumper> event = new Event<DatagramDumper>(this);

	private Thread thread;

	private Collection<ListenerDatagram> listListener = new HashSet<ListenerDatagram>();

	private int port;
	private DatagramSocket socket;
	final byte[] buffer = new byte[Short.MAX_VALUE];
	final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

	private boolean isRunning;


	public DatagramDumperImpl() {
		super();
	}

	public DatagramDumperImpl(final int port) {
		super();

		setPort(port);
	}

	/**
	 * Returns the current {@link Thread} of the controller.
	 *
	 * @return thread of the controller
	 * @since 0.8.0
	 */
	public Thread getThread() {
		return thread;
	}


	/*
	 * Private methods
	 */
	private void firePacketReceived() {
		for (final ListenerDatagram listener : listListener) {
			listener.packetReceived(event);
		}
	}

	private void fireStarted() {
		isRunning = true;

		for (final ListenerDatagram listener : listListener) {
			listener.datagramStarted(event);
		}
	}

	private void fireStopped() {
		isRunning = false;

		for (final ListenerDatagram listener : listListener) {
			listener.datagramStopped(event);
		}
	}


	/*
	 * Implemented methods
	 */
	@Override
	public DatagramPacket getPacket() {
		return packet;
	}

	@Override
	public int getPort() {
		return port;
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
	public void start() throws IOException {
		socket = new DatagramSocket(port);

		thread = new Thread(this);
		thread.start();

		fireStarted();
	}

	@Override
	public void stop() {
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
	public void run() {
		try {
			while (!thread.isInterrupted()) {
				socket.receive(packet);

				firePacketReceived();
			}
		} catch (IOException ex) {
			//do nothing
		}
	}

	@Override
	public synchronized void addListener(final ListenerDatagram listener) {
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.add(listener);
	}

	@Override
	public synchronized void deleteListener(final ListenerDatagram listener) {
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.remove(listener);
	}

	@Override
	public synchronized void deleteListeners() {
		listListener = new HashSet<ListenerDatagram>();
	}

	@Override
	public int countListeners() {
		return listListener.size();
	}
}
