/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.controller.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

import ch.customcode.bogatyr.helper.HelperArray;
import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperNumber;
import ch.customcode.bogatyr.misc.Event;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;
import ch.customcode.bogatyr.misc.extendedObject.ExtendedObjectAbstract;

/**
 * This is the skeleton for clients.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100216)
 * @since 0.7.0
 */
public abstract class ClientAbstract extends ExtendedObjectAbstract implements Client {
    private final Event<Client> event = new Event<Client>(this);
    
    private Thread thread;
    
	private Collection<ListenerClient> listListener = new HashSet<ListenerClient>();

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
	 * Returns the current {@link Thread} of the client.
	 * 
	 * @return thread of the client
	 * @see Thread
	 * @since 0.7.0
	 */
	public Thread getThread() {
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
    	if (null == thread) {
    		throw new RuntimeExceptionIsNull("thread"); //$NON-NLS-1$
    	}

    	this.thread = thread;
	}


	/*
	 * Private methods
	 */
	protected void fireStreamRead() {
		for (final ListenerClient listener : listListener) {
			listener.clientStreamRead(event);
		}	
	}
	
	protected void fireStarted() {
		isRunning = true;
		
		for (final ListenerClient listener : listListener) {
			listener.clientStarted(event);
		}	
	}
	
	protected void fireStopped() {
		isRunning = false;
		
		for (final ListenerClient listener : listListener) {
			listener.clientStopped(event);
		}	
	}
    
	
    /*
     * Implemented methods
     */
	@Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public void setHost(final String host) {
    	if (null == host) {
    		throw new RuntimeExceptionIsNull("host"); //$NON-NLS-1$
    	}

    	this.host = host;
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
    public void setSocket(final Socket socket) {
    	if (null == socket) {
    		throw new RuntimeExceptionIsNull("socket"); //$NON-NLS-1$
    	}

		this.socket = socket;
	}
    
    @Override
    public void start() throws IOException {
		socket = new Socket(host, port);
		
		thread = new Thread(this);
        thread.start();
        
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
    public byte[] readStream() throws IOException {
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
        return result;
    }

    @Override
    public void writeStream(final byte[] data) throws IOException {
        HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{(byte) -1}));
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public synchronized void addListener(final ListenerClient listener) {
        if (null == listener) {
            throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
        }

		listListener.add(listener);
    }

    @Override
    public synchronized void deleteListener(final ListenerClient listener) {
        if (null == listener) {
            throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
        }

        listListener.remove(listener);
    }

    @Override
    public synchronized void deleteListeners() {
        listListener = new HashSet<ListenerClient>();
    }
    
    @Override
    public int countListeners() {
    	return listListener.size();
    }
}
