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
package ch.orwell.bogatyr.controller.net.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.security.PublicKey;

import ch.orwell.bogatyr.controller.net.common.ICom;
import ch.orwell.bogatyr.controller.net.common.dto.ComContainer;
import ch.orwell.bogatyr.controller.net.common.dto.ComObject;
import ch.orwell.bogatyr.controller.net.common.dto.User;
import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.crypto.CryptoAsymm;
import ch.orwell.bogatyr.helper.crypto.CryptoSymm;
import ch.orwell.bogatyr.helper.exception.ExceptionInvalidStreamSize;
import ch.orwell.bogatyr.helper.logger.Logger;
import ch.orwell.bogatyr.helper.property.Property;


/**
 * This is a server thread
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public abstract class ThreadServerTemplate implements Runnable, ICom {
	private final long startTime = System.currentTimeMillis();

	// Properties
	private static final String PROPERTY_NETWORK_SIZE = "network.size"; //$NON-NLS-1$
	
	private final Socket socket;

	private int size; // max. message size of a ComContainer
	private final ServerTemplate server;
	

	protected ThreadServerTemplate(final ServerTemplate server, final Socket socket) {
        super();
        this.server = server;
		this.socket = socket;
		init();
	}
	
	/**
	 * Get the start time of the thread
     *
     * @return start time of the thread
	 */
	public long getStartTime() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getStartTime"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getStartTime", startTime); //$NON-NLS-1$

		return startTime;
	}
	
	/**
     * Reads a communication object from socket-stream
     * 
     * @return Returns a communication object.
     * @throws Exception 
     */
	protected ComObject readObject() throws Exception  {
		Logger.getInstance().writeMethodEntry(this.getClass(), "readObject"); //$NON-NLS-1$

		final ComObject comObject = (ComObject)HelperGeneral.getObjectFromBytes(readStream());

		Logger.getInstance().writeMethodExit(this.getClass(), "readObject", comObject); //$NON-NLS-1$
		return comObject;
    }

    /**
     * Writes a communication object on a socket-stream
     * 
     * @param comObject The communication object to write on the socket-stream.
     * @throws Exception 
     */
    public void writeObject(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "writeObject", comObject); //$NON-NLS-1$

		writeStream(comObject.getUserKey(), HelperGeneral.getBytesFromObject(comObject));

		Logger.getInstance().writeMethodExit(this.getClass(), "writeObject"); //$NON-NLS-1$
    }

    protected Socket getSocket() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getSocket"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getSocket", socket); //$NON-NLS-1$

		return socket;
    }

    
	/*
	 * Private methods
	 */
	/**
     * Reads a socket-stream
	 * @throws Exception
     * @return byte array from stream
     */
	private byte[] readStream() throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "readStream"); //$NON-NLS-1$

        final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        final Object obj = ois.readObject();
        final ComContainer input = (ComContainer)obj;
        byte[] data = input.getData();
        final Key cryptoKey = server.getKey(input.getKey());

        if (cryptoKey instanceof PublicKey) {
            data = CryptoAsymm.decrypt(data, server.getAsymmKey().getPrivate(), 1024);
        } else {
            data = CryptoSymm.decrypt(data, cryptoKey);
        }

        Logger.getInstance().writeMethodExit(this.getClass(), "readStream", data); //$NON-NLS-1$
        return data;
    }

//	private final byte[] readStream() throws Exception {
//System.out.println("WUFF");
//		ComContainer input = (ComContainer)HelperGeneral.getObjectFromBytes(HelperFile.readStream(this.socket.getInputStream()));
//		byte[] data = input.getData();
//		Key cryptoKey = this.server.getKey(input.getKey());
//
//System.out.println("******************************************************");
//System.out.println(data.length);
//System.out.println(input.toString());
//System.out.println("******************************************************");
//
//		if (cryptoKey instanceof PublicKey) {
//    		data = CryptoAsymm.decrypt(data, this.server.getAsymmKey().getPrivate(), 1024);
//    	} else if (cryptoKey instanceof SecretKey) {
//    		data = CryptoSymm.decrypt(data, cryptoKey);
//    	}
//    	return data;
//    }

    /**
     * Writes on a socket-stream
     * @param key
     * @param data
     * @throws Exception
     */
    private void writeStream(final String key, final byte[] data) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "writeStream", new Object[]{key, data}); //$NON-NLS-1$

		if (size == 0 || data.length < size) {

            final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

//    		try {
            	final byte[] temp;
				final Key cryptoKey = server.getKey(key);
				
				if (cryptoKey instanceof PublicKey) {
			   		temp = CryptoAsymm.encrypt(data, (PublicKey)cryptoKey, 1024);
				} else {
		    		temp = CryptoSymm.encrypt(data, cryptoKey);
		    	}
	
				final ComContainer output = new ComContainer(key, temp);
				oos.writeObject(output);
				oos.flush();
//    		} finally {
//    			if (oos != null) oos.close();
//    		}
		} else {
			throw new ExceptionInvalidStreamSize(size, data.length);
		}
		Logger.getInstance().writeMethodExit(this.getClass(), "writeStream"); //$NON-NLS-1$
    }
//    protected final void writeStream(String key, byte[] data) throws Exception {
//		if (this.size == 0 || data.length < this.size) {
//			
//			OutputStream os = this.socket.getOutputStream();
//		
//			byte[] temp = data;
//			Key cryptoKey = this.server.getKey(key);
//			
//			if (cryptoKey instanceof PublicKey) {
//		   		temp = CryptoAsymm.encrypt(data, (PublicKey)cryptoKey, 1024);
//			} else if (cryptoKey instanceof SecretKey) {
//				temp = CryptoSymm.encrypt(data, cryptoKey);
//	    	}
//
//			ComContainer output = new ComContainer(key, temp);
//			System.out.println("******************************************************");
//			System.out.println(temp.length);
//			System.out.println(output.toString());
//			System.out.println("******************************************************");
//			os.write(HelperGeneral.getBytesFromObject(output));
//			os.flush();
//		} else {
//			throw new ExceptionInvalidStreamSize(this.size, data.length);
//		}
//	}
    
	private void init() {
		readProperties();
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
	}
	
	private void readProperties() { //TODO improve! @see Application
        size = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_SIZE);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}

	
	/*
	 * Implemented methods
	 */	
	public void disconnect(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "disconnect", comObject); //$NON-NLS-1$

		server.removeAllData(comObject.getUserKey());

		Logger.getInstance().writeMethodExit(this.getClass(), "disconnect"); //$NON-NLS-1$
	}

	public void sendAsymmKey(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "sendAsymmKey", comObject); //$NON-NLS-1$

		server.addCryptoKey(comObject.getUserKey(), (Key) comObject.getData());

		Logger.getInstance().writeMethodExit(this.getClass(), "sendAsymmKey"); //$NON-NLS-1$
	}

	public void sendSymmKey(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "sendSymmKey", comObject); //$NON-NLS-1$

		server.addCryptoKey(comObject.getUserKey(), (Key) comObject.getData());

		Logger.getInstance().writeMethodExit(this.getClass(), "sendSymmKey"); //$NON-NLS-1$
	}

    public void logon(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "logon", comObject); //$NON-NLS-1$

		server.addUser(comObject.getUserKey(), (User)comObject.getData());

		Logger.getInstance().writeMethodExit(this.getClass(), "logon"); //$NON-NLS-1$
	}
	
	public void logoff(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "logoff", comObject); //$NON-NLS-1$

		server.removeUser(comObject.getUserKey());
		
		Logger.getInstance().writeMethodExit(this.getClass(), "logoff"); //$NON-NLS-1$
	}
}
