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
package ch.sisprocom.bogatyr.controller.net.client.asynch;

import java.io.IOException;
import java.security.Key;

import ch.sisprocom.bogatyr.controller.net.client.ClientAbstract;
import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;


/**
 * This is the skeleton for asynchron clients.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class ClientAsynchAbstract extends ClientAbstract { //TODO document in Wiki!
//	protected Thread thread;
	

	protected ClientAsynchAbstract() throws Exception {
		super();
		
		openStream();
//		start();
	}
    
    
	/*
	 * Private methods
	 */
// 	/**
//	 * Starts the socket connection to the server
//	 * 
//	 * @see Socket
//	 */
//	private void start()	{
//		if (this.thread == null) {
//			this.thread = new Thread(this);
//			this.thread.setPriority(Thread.MIN_PRIORITY);
//			this.thread.start();
//		}
//	}
	
	/**
	 * Stops the Socket Connection.
	 * 
	 * @throws IOException 
	 */
	public void stop() throws IOException {
//		if ((this.thread != null) && this.thread.isAlive()) {
//			this.thread = null;
//		}
		
		closeStream();
	}


	/*
	 * Overridden methods
	 */
	/**
	 * Send the ComObject to the Server.
	 * The Server will execute the given method name.
	 * 
     * @param  comObject ComObject
     * @see ComObject
     * @throws Exception
     */
    @Override
    protected Object execute(final ComObject comObject) throws Exception {
    	//ComObject comObject = data;

//    	Logger.getInstance().writeDebug(this.getClass(), "execute", "comObject: " + data.toString());  //$NON-NLS-1$//$NON-NLS-2$
		
		// write the ComObject on the socket-stream
		writeObject(comObject);

    	return null; // always return null (because it's an async client)
	}

    
    /*
	 * Implemented methods
	 */
	/**
	 * Send a asymm key.
	 * 
	 * @param comObject The {@link ComObject} with the asymm key
	 * @see ComObject
	 */
	public void sendAsymmKey(final ComObject comObject) {
		setCryptoKey((Key)comObject.getData());
	}

	/**
	 * Send a symm key.
	 * 
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(final ComObject comObject) {
		setCryptoKey((Key)comObject.getData());
	}
}
