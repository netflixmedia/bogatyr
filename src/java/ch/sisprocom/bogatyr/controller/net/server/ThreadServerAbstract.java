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
package ch.sisprocom.bogatyr.controller.net.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.security.PublicKey;

import ch.sisprocom.bogatyr.controller.net.common.ICom;
import ch.sisprocom.bogatyr.controller.net.common.dto.ComContainer;
import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.crypto.CryptoAsymm;
import ch.sisprocom.bogatyr.helper.crypto.CryptoSymm;
import ch.sisprocom.bogatyr.helper.exception.ExceptionInvalidStreamSize;
import ch.sisprocom.bogatyr.helper.property.Property;
import ch.sisprocom.bogatyr.model.dao.User;


/**
 * This is a skeleton for server threads.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class ThreadServerAbstract implements Runnable, ICom {
	private final long startTime = System.currentTimeMillis();

	// Properties
	private static final String PROPERTY_NETWORK_SIZE = "network.size"; //$NON-NLS-1$
	
	private final Socket socket;

	private int size; // max. message size of a ComContainer
	private final ServerAbstract server;
	

	protected ThreadServerAbstract(final ServerAbstract server, final Socket socket) {
        super();
        this.server = server;
		this.socket = socket;
		init();
	}
	
	/**
	 * Get the start time of the thread.
     *
     * @return start time of the thread
	 */
	public long getStartTime() {
		return startTime;
	}
	
	/**
     * Reads a communication object from socket-stream.
     * 
     * @return communication object
     * @throws Exception 
     */
	protected ComObject readObject() throws Exception  {
		return (ComObject)HelperGeneral.getObjectFromBytes(readStream());
    }

    /**
     * Writes a communication object on a socket-stream.
     * 
     * @param comObject communication object to write on the socket-stream
     * @throws Exception 
     */
    public void writeObject(final ComObject comObject) throws Exception {
		writeStream(comObject.getUserKey(), HelperGeneral.getBytesFromObject(comObject));
    }

    protected Socket getSocket() {
		return socket;
    }

    
	/*
	 * Private methods
	 */
	/**
     * Reads a socket-stream.
     * 
	 * @throws Exception
     * @return byte array from stream
     */
	private byte[] readStream() throws Exception {
        ObjectInputStream ois = null;
        Object obj;

//        try {
            ois = new ObjectInputStream(socket.getInputStream());
            obj = ois.readObject();
//        } finally {
//            if (ois != null) {
//                ois.close();
//           }
//        }

        final ComContainer input = (ComContainer)obj;
        byte[] data = input.getData();
        final Key cryptoKey = server.getKey(input.getKey());

        if (cryptoKey instanceof PublicKey) {
            data = CryptoAsymm.decrypt(data, server.getAsymmKey().getPrivate(), 1024);
        } else {
            data = CryptoSymm.decrypt(data, cryptoKey);
        }
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
     * Writes on a socket-stream.
     * 
     * @param key
     * @param data
     * @throws Exception
     */
    private void writeStream(final String key, final byte[] data) throws Exception {
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
		server.removeAllData(comObject.getUserKey());
	}

	public void sendAsymmKey(final ComObject comObject) throws Exception {
		server.addCryptoKey(comObject.getUserKey(), (Key) comObject.getData());
	}

	public void sendSymmKey(final ComObject comObject) throws Exception {
		server.addCryptoKey(comObject.getUserKey(), (Key) comObject.getData());
	}

    public void logon(final ComObject comObject) throws Exception {
		server.addUser(comObject.getUserKey(), (User)comObject.getData());
	}
	
	public void logoff(final ComObject comObject) throws Exception {
		server.removeUser(comObject.getUserKey());
	}
}
