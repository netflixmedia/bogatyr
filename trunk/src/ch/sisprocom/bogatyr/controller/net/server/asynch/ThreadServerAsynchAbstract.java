/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.net.server.asynch;

import java.io.IOException;
import java.net.Socket;

import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;
import ch.sisprocom.bogatyr.controller.net.server.ThreadServerAbstract;


/**
 * This is the skeleton for asynchron server threads
 * 
 * @author Stefan Laubenberger
 * @version 20081029
 */
public abstract class ThreadServerAsynchAbstract extends ThreadServerAbstract {
	private Thread thread;
	
	private final ServerAsynchAbstract server;
	

	protected ThreadServerAsynchAbstract(final ServerAsynchAbstract server, final Socket socket) {
		super(server, socket);
		
		this.server = server;
//		start();
	}

	/**
	 * Returns the thread
     *
     * @return Thread
	 */
	protected Thread getThread() {
		return thread;
	}  

	/**
	 * Stops the thread. And closes the open socket
	 * 
	 * @throws IOException 
	 */
	public void stop() throws IOException {
		if (thread != null && thread.isAlive()) {
            thread = null;
        }
		getSocket().close();
	}  

	/**
	 * Starts the thread with {@link Thread#MIN_PRIORITY}.
	 */
	protected void start() {
		if (thread == null) {
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
		}
	}

	
	
	/*
	 * Overridden methods
	 */
	@Override
	public void sendAsymmKey(final ComObject comObject) throws Exception {
		super.sendAsymmKey(comObject);
		writeObject(new ComObject(comObject.getUserKey(), METHOD_SEND_ASYMMKEY, server.getAsymmKey().getPublic()));
	}

//	public void sendSymmKey(ComObject comObject) throws Exception {
//		super.sendSymmKey(comObject);
////		writeObject(new ComObject(comObject.getUser(), ComInterface.METHOD_SEND_ASYMMKEY, this.server.getSymmCrypto().getKey()));
//	}

	
	/*
	 * Implemented methods
	 */
	public void connect(final ComObject comObject) throws Exception {
		//		super.connect(comObject);
        server.addAsynchServerThread(comObject.getUserKey(), this);
	}

}
