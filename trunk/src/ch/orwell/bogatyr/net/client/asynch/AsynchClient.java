/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.net.client.asynch;

import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import ch.orwell.bogatyr.exception.ExceptionHelper;
import ch.orwell.bogatyr.net.client.Client;
import ch.orwell.bogatyr.net.common.dto.ComObject;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the skeleton for all asynchron clients
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public abstract class AsynchClient extends Client {
	protected Thread thread;
	
	/**
	 * Constructs an AsynchClient.
	 * @param propertiesFileName The property file for the configuration.
	 */
	public AsynchClient(String propertiesFileName) {
		super(propertiesFileName);
		
//		Application.getLocalizer().setLocale(Locale.GERMAN); //TODO for tests only!
		
		try {
			openStream();
		} catch (IOException ex) {
			Logger.getInstance().writeException(className + "::<constructor>", ExceptionHelper.EX_COMMUNICATION, ex); //$NON-NLS-1$
		}
		start();
	}
    
    
	/*
	 * Private methods
	 */
 	/**
	 * Starts the Socket Connection to the Server
	 * @see Socket
	 */
	private void start()	{
		if (this.thread == null) {
			this.thread = new Thread(this);
			this.thread.setPriority(Thread.MIN_PRIORITY);
			this.thread.start();
		}
	}
	
	/**
	 * Stops the Socket Connection
	 */
	public void stop() {
		if ((this.thread != null) && this.thread.isAlive()) {
			this.thread = null;
		}
		
		try {
			closeStream();
		} catch (IOException ex) {
			Logger.getInstance().writeException(className + "::stop", ExceptionHelper.EX_COMMUNICATION, ex); //$NON-NLS-1$
		}
	}


	/*
	 * Implemented methods
	 */
	
	/**
	 * Send a asymm key
	 * @param comObject The {@link ComObject} with the asymm key
	 * @see ComObject
	 */
	public void sendAsymmKey(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::sendAsymmKey", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		this.cryptoKey = (PublicKey)comObject.getData();
	}

	/**
	 * Send a symm key
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::sendSymmKey", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		this.cryptoKey = (SecretKey)comObject.getData();
	}


	/*
	 * Overriden methods
	 */
	/**
	 * Send the ComObject to the Server.<p>
	 * The Server will execute the given Methodname.
	 * 
     * @param  data ComObject
     * @see ComObject
     * @throws Exception
     */
    @Override
    protected Object execute(ComObject data) throws Exception {
    	Logger.getInstance().writeDebug(className + "::execute", "data: " + data); //$NON-NLS-1$ //$NON-NLS-2$
    	
    	ComObject comObject = data;

		Logger.getInstance().writeDebug(className + "::execute", "comObject: " + comObject.toString());  //$NON-NLS-1$//$NON-NLS-2$
		
		// write the ComObject on the socket-stream
		writeObject(comObject);
		return null; // always return null (because it's an async client)
	}
}
