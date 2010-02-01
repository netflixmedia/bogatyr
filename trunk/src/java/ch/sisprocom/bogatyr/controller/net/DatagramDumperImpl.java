/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.HelperString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Collection;
import java.util.HashSet;

/**
 * This is a datagram dumper to analyse network packets (UDP) on a given port.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.8.0
 */
public class DatagramDumperImpl implements DatagramDumper {
    private final long createTime = System.currentTimeMillis();

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
    @Override
    public long getCreateTime() {
		return createTime;
	}

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(final int port) {
    	if (0 >= port || HelperNumber.INT_65536 <= port) {
    		throw new IllegalArgumentException("port outside of the valid range (0 - 65535): " + port); //$NON-NLS-1$
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
        if (null != socket) {
        	socket.close();
        }
        
        fireStopped();
        
		if (null != thread) {
			if (thread.isAlive()) {
				thread.interrupt();
			} else {
				thread = null;
			}
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    } 
    
	@Override
	public void run() {
		try	{
			while(!thread.isInterrupted()) {
				socket.receive(packet);
				
				firePacketReceived(packet.getAddress().getHostAddress(), port, HelperString.toString(buffer, packet.getLength(), Constants.ENCODING_ASCII), packet);
			}
//		} catch (SocketException ex) {
//			//do nothing
		} catch (IOException ex) {
            //do nothing
//			ex.printStackTrace();
		}
	}

    @Override
    public synchronized void addListener(final ListenerDatagram listener) {
    	if (null == listener) {
    		throw new IllegalArgumentException("listener is null!"); //$NON-NLS-1$
    	}

    	listListener.add(listener);
    }

    @Override
    public synchronized void deleteListener(final ListenerDatagram listener) {
    	if (null == listener) {
    		throw new IllegalArgumentException("listener is null!"); //$NON-NLS-1$
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
