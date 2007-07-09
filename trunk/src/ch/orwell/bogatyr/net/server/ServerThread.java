/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.net.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.exception.InvalidStreamSizeException;
import ch.orwell.bogatyr.net.common.ComInterface;
import ch.orwell.bogatyr.net.common.dto.ComContainer;
import ch.orwell.bogatyr.net.common.dto.ComObject;
import ch.orwell.bogatyr.net.common.dto.User;
import ch.orwell.bogatyr.util.GeneralHelper;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is a server thread
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class ServerThread implements Runnable, ComInterface {
	protected String className;
	protected final long startTime = System.currentTimeMillis();

	// Properties
	private static final String PROPERTY_NETWORK_SIZE = "network.size"; //$NON-NLS-1$
	
	protected Socket socket;

	private int size; // max. message size of a ComContainer
	private Server server;
	
	/**
	 * Constructs a ServerThread.
	 * 
	 * @param socket The socket where the server thread listen.
	 */
	public ServerThread(Socket socket) {
		this.socket = socket;
		init();
	}
	
	/**
     * Reads a socket-stream
     * 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
     */
	private final byte[] readStream() throws IOException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
	   	ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());

		ComContainer input = (ComContainer)ois.readObject();
		byte[] data = input.getData();
		Key cryptoKey = this.server.getKey(input.getKey());
		
    	if (cryptoKey instanceof PublicKey) {
    		data = this.server.getAsymmCrypto().decrypt(data);
    	} else if (cryptoKey instanceof SecretKey) {
    		data = this.server.getSymmCrypto().decrypt(data, cryptoKey);
    	}

    	return data;
    }

    /**
     * Writes on a socket-stream
     * 
     * @throws IOException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws InvalidStreamSizeException 
     */
    private final void writeStream(String key, byte[] data) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidStreamSizeException {
    	if (this.size == 0 || data.length < this.size) { 
	    	
	    	ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			
			byte[] temp = data;
			Key cryptoKey = this.server.getKey(key);
			
			if (cryptoKey instanceof PublicKey) {
		   		temp = this.server.getAsymmCrypto().encrypt(data, (PublicKey)cryptoKey);
			} else if (cryptoKey instanceof SecretKey) {
	    		temp = this.server.getSymmCrypto().encrypt(data, cryptoKey);
	    	}

			ComContainer output = new ComContainer(key, temp);
			oos.writeObject(output);
			oos.flush();
		} else {
			throw new InvalidStreamSizeException(this.size, data.length);
		}
    }

    /**
     * Reads a communication object from socket-stream
     * 
     * @return ComObject Returns a communication object.
     * @throws Exception 
     * @throws ClassNotFoundException 
     * @throws IOException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException
     */
	public final ComObject readObject() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException, Exception {
		ComObject comObject = (ComObject)GeneralHelper.getObject(readStream());
		Logger.getInstance().writeDebug(this.className + "::readObject", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
    	return comObject;
    }

    /**
     * Writes a communication object on a socket-stream
     * 
     * @param comObject The communication object to write on the socket-stream.
     * @throws InvalidStreamSizeException 
     * @throws IOException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public final void writeObject(ComObject comObject) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidStreamSizeException {
    	Logger.getInstance().writeDebug(this.className + "::writeObject", "comObject: " + comObject); //$NON-NLS-1$ //$NON-NLS-2$
    	writeStream(comObject.getUserKey(), GeneralHelper.getBytes(comObject));
    }
 
    
	/*
	 * Private methods
	 */
	private void init() {
		this.className = this.getClass().getName();
		this.server = (Server)Context.getInstance().getData(Server.ATT_INSTANCE);
		readProperties();
	}
	
	/**
	 * Reads the Properties.
	 */
	private void readProperties() {
		this.size = Context.getInstance().getProperties().getPropertyInt(PROPERTY_NETWORK_SIZE);
	}

	
	/*
	 * Implemented methods
	 */	

	public void connect(ComObject comObject) throws Exception {
		//TODO implement
	}

	public void disconnect(ComObject comObject) throws Exception {
		this.server.removeAllData(comObject.getUserKey());
	}

	public void sendAsymmKey(ComObject comObject) throws Exception {
		this.server.addCryptoKey(comObject.getUserKey(), (PublicKey)comObject.getData());
	}

	public void sendSymmKey(ComObject comObject) throws Exception {
		this.server.addCryptoKey(comObject.getUserKey(), (SecretKey)comObject.getData());
	}

    public void logon(ComObject comObject) throws Exception {
		this.server.addUser(comObject.getUserKey(), (User)comObject.getData());
	}
	
	public void logoff(ComObject comObject) throws Exception {
		this.server.removeUser(comObject.getUserKey());
	}
}
