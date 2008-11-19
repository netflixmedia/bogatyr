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
package ch.sisprocom.bogatyr.controller.net.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

import ch.sisprocom.bogatyr.controller.ApplicationAbstract;
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
 * This is the skeleton for clients.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class ClientAbstract extends ApplicationAbstract implements ICom {
	private static final Logger log = Logger.getLogger(ClientAbstract.class);
	
	// Properties
	private static final String PROPERTY_NETWORK_HOST    = "network.host"; //$NON-NLS-1$
	private static final String PROPERTY_NETWORK_PORT    = "network.port"; //$NON-NLS-1$
	private static final String PROPERTY_NETWORK_SIZE    = "network.size"; //$NON-NLS-1$
	private static final String PROPERTY_CLIENT_INTERVAL = "client.interval"; //$NON-NLS-1$
	
	private Key cryptoKey;
	
	private KeyPair asymmKey;
	private SecretKey symmKey;
	
	private User user;
		
	private String host;
    private Socket socket;
	private int port, size, interval;
	

	protected ClientAbstract() throws Exception {
		super();
		init();
	}
	
	public User getUser() {
		return user;
	}

	protected void setUser(final User user) {
		this.user = user;
	}

	protected KeyPair getAsymmKey() {
		return asymmKey;
	}

	protected SecretKey getSymmKey() {
		return symmKey;
	}

	protected Key getCryptoKey() {
		return cryptoKey;
	}
	
	protected void setCryptoKey(final Key cryptoKey) {
		this.cryptoKey = cryptoKey;
	}

	/**
     * Open a stream.
     * 
     * @throws IOException
     */
	protected void openStream() throws IOException {
		socket = new Socket(host, port);
	}
	
	/**
     * Close a stream.
     * 
     * @throws IOException
     */
	protected void closeStream() throws IOException {
		socket.close();
	}

	/**
     * Reads a socket-stream.
     * 
     * @return byte-array
	 * @throws Exception 
     */
	protected byte[] readStream() throws Exception {
		final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        final Object obj = ois.readObject();
        final ComContainer input = (ComContainer)obj;
	
		byte[] data = input.getData();
			
	    if (cryptoKey instanceof PublicKey) {
	    	data = CryptoAsymm.decrypt(data, asymmKey.getPrivate(), 1024);
	    } else {
	    	data = CryptoSymm.decrypt(data, cryptoKey);

	    }
	    return data;
    }

//	protected final byte[] readStream() throws Exception {
//		ComContainer input = (ComContainer)HelperGeneral.getObjectFromBytes(HelperFile.readStream(this.socket.getInputStream()));
//		byte[] data = input.getData();
//
//System.out.println("******************************************************");
//System.out.println(data.length);
//System.out.println(input.toString());
//System.out.println("******************************************************");
//
//		if (this.cryptoKey instanceof PublicKey) {
//    		data = CryptoAsymm.decrypt(data, this.asymmKey.getPrivate(), 1024);
//    	} else if (this.cryptoKey instanceof SecretKey) {
//    		data = CryptoSymm.decrypt(data, this.cryptoKey);
//    	}
//    	return data;
//    }

    /**
     * Writes on a socket-stream.
     * 
     * @param key for authentication
     * @param data a byte-array
     * @throws Exception 
     */
    protected void writeStream(final String key, final byte[] data) throws Exception {
		if (size == 0 || data.length < size) {
			
			if (interval > 0) { // prevent too fast connection attemps (DOS protection on the server side)
//				Thread.sleep(this.interval);
//				HelperGeneral.suspendMilliseconds(this.interval);
			}
			
//			try {
            final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		
				byte[] temp;
				
				if (cryptoKey instanceof PublicKey) {
			   		temp = CryptoAsymm.encrypt(data, (PublicKey) cryptoKey, 1024);
				} else {
					temp = CryptoSymm.encrypt(data, cryptoKey);
		    	}
	
				final ComContainer output = new ComContainer(key, temp);
				oos.writeObject(output);
				oos.flush();
//			} finally {
//				if (oos != null) oos.close();
//			}
		} else {
			throw new ExceptionInvalidStreamSize(size, data.length);
		}
	}
//    protected final void writeStream(String key, byte[] data) throws Exception {
//		if (this.size == 0 || data.length < this.size) {
//			if (this.interval > 0) { // prevent too fast connection attemps (DOS protection on the server side)
////				Thread.sleep(this.interval);
////				HelperGeneral.suspendMilliseconds(this.interval);
//			}
//			
//			OutputStream os = this.socket.getOutputStream();
//		
//			byte[] temp = data;
//			
//			if (this.cryptoKey instanceof PublicKey) {
//		   		temp = CryptoAsymm.encrypt(data, (PublicKey)this.cryptoKey, 1024);
//			} else if (this.cryptoKey instanceof SecretKey) {
//				temp = CryptoSymm.encrypt(data, this.cryptoKey);
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
    
    /**
     * Reads a ComObject.
     * 
     * @return ComObject
     * @throws Exception 
     */
	protected ComObject readObject() throws Exception {
		return (ComObject)HelperGeneral.getObjectFromBytes(readStream());
    }


	/**
	 * Writes a ComObject.
	 * 
	 * @param comObject to write
	 * @throws Exception 
	 * @see ComObject
	 */
    public void writeObject(final ComObject comObject) throws Exception {
		writeStream(comObject.getUserKey(), HelperGeneral.getBytesFromObject(comObject));
    }

	/**
	 * Send the ComObject to the Server.
	 * The server will execute the given method name.
	 * 
	 * @param comObject to execute
	 * @return result data
	 * @throws Exception
	 * @see ComObject
	 */
	protected abstract Object execute(ComObject comObject) throws Exception;

	
	/*
	 * Private methods
	 */
	private void init() throws NoSuchAlgorithmException, NoSuchProviderException {
		readProperties();
	
		// Generate crypto keys
        asymmKey = CryptoAsymm.generateKeys(1024);

        symmKey = CryptoSymm.generateKey(128); //TODO still needed?
	}

	private void readProperties() {
		String value = Property.getInstance().getProperty(PROPERTY_NETWORK_HOST);
		if (HelperGeneral.isValidString(value)) {
            host = value;
		} else {
			log.error(PROPERTY_NETWORK_HOST + " == 'null'"); //$NON-NLS-1$
			exit(10);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_NETWORK_PORT);
		if (HelperGeneral.isValidString(value)) {
            port = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_PORT);
		} else {
			log.error(PROPERTY_NETWORK_PORT + " == 'null'"); //$NON-NLS-1$
			exit(11);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_CLIENT_INTERVAL);
		if (HelperGeneral.isValidString(value)) {
            interval = Property.getInstance().getPropertyInt(PROPERTY_CLIENT_INTERVAL);
		} else {
			log.error(PROPERTY_CLIENT_INTERVAL + " == 'null'"); //$NON-NLS-1$
			exit(12);
		}

		value = Property.getInstance().getProperty(PROPERTY_NETWORK_SIZE);
		if (HelperGeneral.isValidString(value)) {
            size = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_SIZE);
		} else {
			log.error(PROPERTY_NETWORK_SIZE + " == 'null'"); //$NON-NLS-1$
			exit(13);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public void connect(final ComObject comObject) throws Exception {
		comObject.setMethodName(METHOD_CONNECT);
		execute(comObject);
	}

	public void disconnect(final ComObject comObject) throws Exception {
		comObject.setMethodName(METHOD_DISCONNECT);
		execute(comObject);
	}

	public void logon(final ComObject comObject) throws Exception {
		comObject.setMethodName(METHOD_LOGON);
		execute(comObject);
	}

	public void logoff(final ComObject comObject) throws Exception {
		comObject.setMethodName(METHOD_LOGOFF);
		execute(comObject);
	}
}
