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
package ch.sisprocom.bogatyr.controller.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is a skeleton for server threads.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.7.0
 */
public abstract class ServerThreadAbstract implements IServerThread {
    private final long createTime = System.currentTimeMillis();

	private Thread thread;
	
	private Collection<ListenerServerThread> listListener = new HashSet<ListenerServerThread>();

	private final Socket socket;
	
	private boolean isRunning;
	

	protected ServerThreadAbstract(final Socket socket) {
        super();
		this.socket = socket;
	}

	/**
     * Returns the instantiation time of the server thread.
     *
     * @return instantiation time of the server thread
     * @since 0.7.0
     */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * Returns the current {@link Thread}.
	 * 
	 * @return thread
	 * @since 0.7.0
	 */
	public Thread getThread() {
		return thread;
	}
	
	
	/*
	 * Private methods
	 */
	protected void fireStreamRead(final byte[] data) {
		for (final ListenerServerThread listener : listListener) {
			listener.serverThreadStreamRead(this, data);
		}	
	}
	
	protected void fireStarted() {
		isRunning = true;
		
		for (final ListenerServerThread listener : listListener) {
			listener.serverThreadStarted(this);
		}	
	}
	
	protected void fireStopped() {
		isRunning = false;
		
		for (final ListenerServerThread listener : listListener) {
			listener.serverThreadStopped(this);
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
	public Socket getSocket() {
		return socket;
	}

	public byte[] readStream() throws IOException {
		final InputStream is = socket.getInputStream();
		byte[] result = null;
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

    public void writeStream(final byte[] data) throws IOException {
    	HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{(byte) -1}));
    }


	public void start() {		
		if (thread == null) {
			thread = new Thread(this);
            thread.start();
		}
		
		fireStarted();
	}	

	public void stop() throws IOException {
		fireStopped();

        if (null != socket) {
        	socket.close();
        }
		
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
	
    public synchronized void addListener(final ListenerServerThread listener) {
        listListener.add(listener);
    }

    public synchronized void removeListener(final ListenerServerThread listener) {
        listListener.remove(listener);
    }

    public synchronized void removeAllListener() {
        listListener = new HashSet<ListenerServerThread>();
    }
}
