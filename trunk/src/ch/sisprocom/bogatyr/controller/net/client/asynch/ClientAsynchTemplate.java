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

import ch.sisprocom.bogatyr.controller.net.client.ClientTemplate;
import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is the skeleton for asynchron clients
 * 
 * @author Stefan Laubenberger
 * @version 20080901
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "stop"); //$NON-NLS-1$
//		if ((this.thread != null) && this.thread.isAlive()) {
//			this.thread = null;
//		}
		
		closeStream();

		Logger.getInstance().writeMethodExit(this.getClass(), "stop"); //$NON-NLS-1$
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "execute", comObject); //$NON-NLS-1$
		
    	//ComObject comObject = data;

//    	Logger.getInstance().writeDebug(this.getClass(), "execute", "comObject: " + data.toString());  //$NON-NLS-1$//$NON-NLS-2$
		
		// write the ComObject on the socket-stream
		writeObject(comObject);

		Logger.getInstance().writeMethodExit(this.getClass(), "execute", null); //$NON-NLS-1$
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "sendAsymmKey", comObject); //$NON-NLS-1$
		
		setCryptoKey((Key)comObject.getData());
		
		Logger.getInstance().writeMethodExit(this.getClass(), "sendAsymmKey"); //$NON-NLS-1$
	}

	/**
	 * Send a symm key
	 * 
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(final ComObject comObject) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "sendSymmKey", comObject); //$NON-NLS-1$
		
		setCryptoKey((Key)comObject.getData());
		
		Logger.getInstance().writeMethodExit(this.getClass(), "sendSymmKey"); //$NON-NLS-1$
	}
}
