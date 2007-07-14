/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.net.server.asynch;

import java.io.IOException;
import java.net.Socket;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.exception.ExceptionHelper;
import ch.orwell.bogatyr.net.common.ComInterface;
import ch.orwell.bogatyr.net.common.dto.ComObject;
import ch.orwell.bogatyr.net.server.Server;
import ch.orwell.bogatyr.net.server.ServerThread;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the skeleton for all asynchron server threads
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class AsynchServerThread extends ServerThread {
	protected Thread thread;
	
	private AsynchServer server;
	
	/**
	 * Constructs a asynchron Server.
	 * 
	 * @param socket The Socket to start the server thread.
	 */
	public AsynchServerThread(Socket socket) {
		super(socket);
		init();
		start();
	}
  

	/*
	 * Private methods
	 */
	/**
	 * Initialize the object
	 */
	private void init() {
		this.server = (AsynchServer)Context.getInstance().getData(Server.ATT_INSTANCE);
	}

	/**
	 * Starts the thread with {@link Thread#MIN_PRIORITY}.
	 */
	private void start() {
		if (this.thread == null) {
			this.thread = new Thread(this);
			this.thread.setPriority(Thread.MIN_PRIORITY);
			this.thread.start();
		}
	}

	/**
	 * Stops the thread. And closes the open socket.
	 */
	public void stop() {
		if ((this.thread != null) && this.thread.isAlive()) {
			this.thread = null;
		}

		try {
			this.socket.close(); 
		} catch (IOException ex) {
			Logger.getInstance().writeException(this.className + "::stop", ExceptionHelper.EX_COMMUNICATION, ex); //$NON-NLS-1$
		}
	}
	
	
	/*
	 * Overriden methods
	 */
	@Override
	public void connect(ComObject comObject) throws Exception {
		super.connect(comObject);
		this.server.addAsynchServerThread(comObject.getUserKey(), this);
	}

	@Override
	public void sendAsymmKey(ComObject comObject) throws Exception {
		super.sendAsymmKey(comObject);
		writeObject(new ComObject(comObject.getUserKey(), ComInterface.METHOD_SEND_ASYMMKEY, this.server.getAsymmCrypto().getPublicKey()));
	}

//	public void sendSymmKey(ComObject comObject) throws Exception {
//		super.sendSymmKey(comObject);
////		writeObject(new ComObject(comObject.getUser(), ComInterface.METHOD_SEND_ASYMMKEY, this.server.getSymmCrypto().getKey()));
//	}
}
