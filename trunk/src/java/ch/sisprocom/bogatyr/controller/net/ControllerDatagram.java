/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Collection;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.HelperString;

/**
 * This is a datagram controller to analyse network packets (UDP) on a given port.
 *
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.8.0
 */
public class ControllerDatagram implements IControllerDatagram {
    private final long createTime = System.currentTimeMillis();

    private Thread thread;
    
	private Collection<ListenerDatagram> listListener = new ArrayList<ListenerDatagram>();

    private int port;
    private DatagramSocket socket;
    final byte[] buffer = new byte[Short.MAX_VALUE];
    final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    private boolean isRunning;
    
    protected ControllerDatagram() {
    	super();
    }

    protected ControllerDatagram(final int port) {
        super();

        setPort(port);
    }

	/**
     * Returns the instantiation time of the datagram controller.
     *
     * @return instantiation time of the datagram controller
     * @since 0.8.0
     */
	public long getCreateTime() {
		return createTime;
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
	private void firePacketReceived(final String host, final int port, final String data, final DatagramPacket packet) {
		for (final ListenerDatagram listener : listListener) {
			listener.packetReceived(host, port, data, packet);
		}	
	}
	
	private void fireStarted() {
		isRunning = true;
		
		for (final ListenerDatagram listener : listListener) {
			listener.datagramStarted();
		}	
	}
	
	private void fireStopped() {
		isRunning = false;
		
		for (final ListenerDatagram listener : listListener) {
			listener.datagramStopped();
		}	
	}
    

    /*
     * Overridden methods
     */
    @Override
    public String toString() {
        return HelperObject.toString(this);
    }
    
	
    /*
     * Implemented methods
     */
    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
    	if (0 >= port || HelperNumber.VALUE_65536 <= port) {
    		throw new IllegalArgumentException("port outside of the valid range (0 - 65535): " + port); //$NON-NLS-1$
    	}
        this.port = port;
    }

    public void start() throws IOException {
		socket = new DatagramSocket(port);
		
		thread = new Thread(this);
        thread.start();
        
        fireStarted();
    }

    public void stop() throws IOException {
        if (null != socket) {
        	socket.close();
        }
        
        fireStopped();
        
		if (thread != null) {
			if (thread.isAlive()) {
				thread.interrupt();
			} else {
				thread = null;
			}
        }
    }

    public boolean isRunning() {
        return isRunning;
    } 
    
	@Override
	public void run() {
		try	{
			while(!getThread().isInterrupted()) {
				socket.receive(packet);
				
				firePacketReceived(packet.getAddress().getHostAddress(), port, HelperString.toString(buffer, packet.getLength(), Constants.ENCODING_ASCII), packet);
			}
//		} catch (SocketException ex) {
//			//do nothing
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    public synchronized void addListener(final ListenerDatagram listener) {
        listListener.add(listener);
    }

    public synchronized void removeListener(final ListenerDatagram listener) {
        listListener.remove(listener);
    }

    public synchronized void removeAllListener() {
        listListener = new ArrayList<ListenerDatagram>();
    }
}
