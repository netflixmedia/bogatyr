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
package ch.sisprocom.bogatyr.controller.net.client;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.HelperNumber;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is the skeleton for clients.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.70 (20090527)
 * @since 0.70
 */
public abstract class ClientAbstract implements IClient {
    private final long createTime = System.currentTimeMillis();

    private Thread thread;
    
	private Collection<ListenerClient> listListener = new ArrayList<ListenerClient>();

    private String host;
    private int port;
    private Socket socket;

    private boolean isRunning;
    
    protected ClientAbstract() {
    	super();
    }


    protected ClientAbstract(final String host, final int port) {
        super();

        setHost(host);
        setPort(port);
    }


	/**
     * Returns the instantiation time of the client.
     *
     * @return instantiation time of the controller
     */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * Returns the current {@link Thread}.
	 * 
	 * @return thread
	 */
	public Thread getThread() {
		return thread;
	}	
	
    /*
      * Implemented methods
      */
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setHost(final String host) {
    	if (null == host) {
    		throw new IllegalArgumentException("host is null!"); //$NON-NLS-1$
    	}

    	this.host = host;
    }

    public void setPort(final int port) {
    	if (0 >= port || HelperNumber.VALUE_65536 <= port) {
    		throw new IllegalArgumentException("port outside of the valid range (0 - 65535): " + port); //$NON-NLS-1$
    	}
        this.port = port;
    }

    public void start() throws IOException {
//		if (thread == null) {
			socket = new Socket(host, port);
			
			thread = new Thread(this);
//			thread.setDaemon(true);
//            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
//		}
        
        fireClientStarted();
    }

    public void stop() throws IOException {
        if (null != socket) {
        	socket.close();
        }
        
        fireClientStopped();
        
		if (thread != null) {
			if (thread.isAlive()) {
				thread.interrupt();
			} else {
				thread = null;
			}
        }
    }

    public byte[] readStream() throws IOException {
    	byte[] result = null;
    	
    	if (!socket.isClosed()) {
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
        } else {
        	stop();
        }
        return result;
    }

    public void writeStream(final byte[] data) throws IOException {
        HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{(byte) -1}));
    }

    public boolean isRunning() {
        return isRunning;
    }
    
    
	/*
	 * Private methods
	 */
	protected void fireClientStreamRead(final byte[] data) {
		for (final ListenerClient listener : listListener) {
			listener.clientStreamRead(data);
		}	
	}
	
	protected void fireClientStarted() {
		isRunning = true;
		
		for (final ListenerClient listener : listListener) {
			listener.clientStarted();
		}	
	}
	
	protected void fireClientStopped() {
		isRunning = false;
		
		for (final ListenerClient listener : listListener) {
			listener.clientStopped();
		}	
	}

	
    /*
      * Implemented methods
      */
    public synchronized void addListener(final ListenerClient listener) {
        listListener.add(listener);
    }

    public synchronized void removeListener(final ListenerClient listener) {
        listListener.remove(listener);
    }

    public synchronized void removeAllListener() {
        listListener = new ArrayList<ListenerClient>();
    }
    

    /*
      * Overridden methods
      */
    @Override
    public String toString() {
        return HelperObject.toString(this);
    }
}
