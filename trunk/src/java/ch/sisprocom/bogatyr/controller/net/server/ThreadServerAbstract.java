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
import java.util.UUID;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperCrypto;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is a skeleton for server threads.
 * 
 * @author Stefan Laubenberger
 * @version 20090521
 */
public abstract class ThreadServerAbstract implements Runnable { //TODO document in Wiki!
	private final long startTime = System.currentTimeMillis();

	
	private Thread thread;
	
	private final UUID uuid = HelperCrypto.getUUID();

	private final Socket socket;
	private final ServerAbstract server;
	

	protected ThreadServerAbstract(final ServerAbstract server, final Socket socket) {
        super();
        this.server = server;
		this.socket = socket;
	}
	
	/**
	 * Get the start time of the thread.
     *
     * @return start time of the thread
	 */
	public long getStartTime() {
		return startTime;
	}

    protected Socket getSocket() {
		return socket;
    }

	/**
     * Reads a socket-stream.
     * 
     * @return byte array from stream
	 * @throws IOException
     */
	public byte[] readStream() throws IOException {
		System.out.println("start readStream: " +  (socket.isConnected() && !socket.isClosed() && socket.isBound()));
		
		byte[] temp = null;
		
		InputStream in = socket.getInputStream();
		byte b;
		do {
			b = (byte) in.read();
			  
			  
			  if (b != -1) {
				  temp = HelperArray.concatenate(temp, new byte[]{b});
			    System.out.println("The next byte is " + b);
			  }
			} while (b != -1);

			System.out.println("end readStream");
		
			if (null == temp) { //client lost
				stop();
			}
			return temp;
			
		
		
//		return HelperIO.readStream(socket.getInputStream());
    }

    /**
     * Writes on a socket-stream.
     * 
     * @param key
     * @param data
     * @throws IOException 
     */
    public void writeStream(final byte[] data) throws IOException {
    	System.out.println("writeStream: " + new String(data));
    	HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{-1}));

    }

	/**
	 * Returns the thread.
     *
     * @return Thread
	 */
	protected Thread getThread() {
		return thread;
	}  

	/**
	 * Stops the thread. And closes the open socket.
	 * 
	 * @throws IOException 
	 */
	public void stop() throws IOException {
		if (thread != null && thread.isAlive()) {
			server.removeServerThread(uuid);
            thread = null;
        }
		socket.close();
		
		System.out.println("Thread stopped");
	}  

	/**
	 * Starts the thread with {@link Thread#MIN_PRIORITY}.
	 */
	public void start() {
		if (thread == null) {
			server.addServerThread(uuid, this);
			thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
            
            System.out.println("Thread started");
		}
	}	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}
