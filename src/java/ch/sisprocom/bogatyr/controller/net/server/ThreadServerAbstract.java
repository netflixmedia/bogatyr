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

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperCrypto;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.UUID;


/**
 * This is a skeleton for server threads.
 * 
 * @author Stefan Laubenberger
 * @version 20090522
 */
public abstract class ThreadServerAbstract implements IThreadServer {
    private final long createTime = System.currentTimeMillis();

	private Thread thread;
	
	private final UUID uuid = HelperCrypto.getUUID();

	private final Socket socket;
	private final ServerAbstract server;
	
	private boolean isRunning;
	

	protected ThreadServerAbstract(final ServerAbstract server, final Socket socket) {
        super();
        this.server = server;
		this.socket = socket;
	}

	/**
     * Returns the instantiation time of the server thread.
     *
     * @return instantiation time of the controller
     */
	public long getCreateTime() {
		return createTime;
	}


	/*
	 * Implemented methods
	 */
	public Thread getThread() {
		return thread;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Socket getSocket() {
		return socket;
	}

	public ServerAbstract getServer() {
		return server;
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

	public void stop() throws IOException {
		isRunning = false;
		
		if (thread != null && thread.isAlive()) {
            thread = null;
        }
		server.removeServerThread(uuid);
		socket.close();
	}  

	public void start() {
		isRunning = true;
		
		if (thread == null) {
			server.addServerThread(uuid, this);
			thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
		}
	}	
    
	public boolean isRunning() {
		return isRunning;
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}
