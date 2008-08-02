/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.controller.net.client.asynch;

import java.io.IOException;
import java.security.Key;

import ch.orwell.bogatyr.controller.net.client.ClientTemplate;
import ch.orwell.bogatyr.controller.net.common.dto.ComObject;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the skeleton for asynchron clients
 * 
 * @author Stefan Laubenberger
 * @version 20080724
 */
public abstract class ClientAsynchTemplate extends ClientTemplate {
//	protected Thread thread;
	

	protected ClientAsynchTemplate(final String propertiesStreamName) throws Exception {
		super(propertiesStreamName);
		
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
	 * Stops the Socket Connection
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
     * @param  data ComObject
     * @see ComObject
     * @throws Exception
     */
    @Override
    protected Object execute(final ComObject data) throws Exception {
    	Logger.getInstance().writeDebug(this, "execute", "data: " + data); //$NON-NLS-1$ //$NON-NLS-2$
    	
    	//ComObject comObject = data;

    	Logger.getInstance().writeDebug(this, "execute", "comObject: " + data.toString());  //$NON-NLS-1$//$NON-NLS-2$
		
		// write the ComObject on the socket-stream
		writeObject(data);
		return null; // always return null (because it's an async client)
	}

    
    /*
	 * Implemented methods
	 */
	/**
	 * Send a asymm key
	 * 
	 * @param comObject The {@link ComObject} with the asymm key
	 * @see ComObject
	 */
	public void sendAsymmKey(final ComObject comObject) {
		Logger.getInstance().writeDebug(this, "sendAsymmKey", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		setCryptoKey((Key)comObject.getData());
	}

	/**
	 * Send a symm key
	 * 
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(final ComObject comObject) {
		Logger.getInstance().writeDebug(this, "sendSymmKey", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		setCryptoKey((Key)comObject.getData());
	}
}
