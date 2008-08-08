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
package ch.orwell.bogatyr.controller.net.client;

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

import ch.orwell.bogatyr.controller.ApplicationTemplate;
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
 * This is the skeleton for clients
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public abstract class ClientTemplate extends ApplicationTemplate implements ICom {
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
	

	protected ClientTemplate(final String propertiesStreamName) throws Exception {
		super(propertiesStreamName);
		init();
	}
	
	public User getUser() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUser"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getUser"); //$NON-NLS-1$

		return user;
	}

	protected void setUser(final User user) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setUser", user); //$NON-NLS-1$

		this.user = user;
	
		Logger.getInstance().writeMethodExit(this.getClass(), "setUser"); //$NON-NLS-1$
	}

	protected KeyPair getAsymmKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getAsymmKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getAsymmKey", asymmKey); //$NON-NLS-1$

		return asymmKey;
	}

	protected SecretKey getSymmKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getSymmKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getSymmKey", symmKey); //$NON-NLS-1$

		return symmKey;
	}

	protected Key getCryptoKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getCryptoKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getCryptoKey", cryptoKey); //$NON-NLS-1$

		return cryptoKey;
	}
	
	protected void setCryptoKey(final Key cryptoKey) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setCryptoKey", cryptoKey); //$NON-NLS-1$
		
		this.cryptoKey = cryptoKey;
		Logger.getInstance().writeMethodExit(this.getClass(), "setCryptoKey"); //$NON-NLS-1$
	}

	/**
     * Open a stream
     * 
     * @throws IOException
     */
	protected void openStream() throws IOException {
		Logger.getInstance().writeMethodEntry(this.getClass(), "openStream"); //$NON-NLS-1$

		socket = new Socket(host, port);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "openStream"); //$NON-NLS-1$
	}
	
	/**
     * Close a stream
     * 
     * @throws IOException
     */
	protected void closeStream() throws IOException {
		Logger.getInstance().writeMethodEntry(this.getClass(), "closeStream"); //$NON-NLS-1$

		socket.close();
		
		Logger.getInstance().writeMethodExit(this.getClass(), "closeStream"); //$NON-NLS-1$
	}

	/**
     * Reads a socket-stream
     * 
     * @return Returns a byte-array
	 * @throws Exception 
     */
	protected byte[] readStream() throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "readStream"); //$NON-NLS-1$

		final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        final Object obj = ois.readObject();
        final ComContainer input = (ComContainer)obj;
	
		byte[] data = input.getData();
			
	    if (cryptoKey instanceof PublicKey) {
	    	data = CryptoAsymm.decrypt(data, asymmKey.getPrivate(), 1024);
	    } else {
	    	data = CryptoSymm.decrypt(data, cryptoKey);

	    }
	    
	    Logger.getInstance().writeMethodExit(this.getClass(), "readStream", data); //$NON-NLS-1$
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
     * Writes on a socket-stream
     * 
     * @param key The key for authentication
     * @param data The data as a byte-array
     * @throws Exception 
     */
    protected void writeStream(final String key, final byte[] data) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "writeStream", new Object[]{key, data}); //$NON-NLS-1$

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
		Logger.getInstance().writeMethodExit(this.getClass(), "writeStream"); //$NON-NLS-1$
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
     * Reads a ComObject
     * 
     * @return Returns the ComObject
     * @throws Exception 
     */
	protected ComObject readObject() throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "readObject"); //$NON-NLS-1$

		final ComObject comObject = (ComObject)HelperGeneral.getObjectFromBytes(readStream());

		Logger.getInstance().writeMethodExit(this.getClass(), "readObject", comObject); //$NON-NLS-1$
		return comObject;
    }


	/**
	 * Writes a ComObject
	 * 
	 * @param comObject The ComObject to write
	 * @throws Exception 
	 * @see ComObject
	 */
    public void writeObject(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "writeObject", comObject); //$NON-NLS-1$

		writeStream(comObject.getUserKey(), HelperGeneral.getBytesFromObject(comObject));
		
		Logger.getInstance().writeMethodExit(this.getClass(), "writeObject"); //$NON-NLS-1$
    }

	/**
	 * Send the ComObject to the Server.
	 * The server will execute the given method name.
	 * 
	 * @param comObject for the execute
	 * @return Result Data
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
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_NETWORK_HOST + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(50);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_NETWORK_PORT);
		if (HelperGeneral.isValidString(value)) {
            port = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_PORT);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_NETWORK_PORT + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(51);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_CLIENT_INTERVAL);
		if (HelperGeneral.isValidString(value)) {
            interval = Property.getInstance().getPropertyInt(PROPERTY_CLIENT_INTERVAL);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_CLIENT_INTERVAL + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(52);
		}

		value = Property.getInstance().getProperty(PROPERTY_NETWORK_SIZE);
		if (HelperGeneral.isValidString(value)) {
            size = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_SIZE);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_NETWORK_SIZE + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(53);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public void connect(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "connect", comObject); //$NON-NLS-1$

		comObject.setMethodName(METHOD_CONNECT);
		execute(comObject);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "connect"); //$NON-NLS-1$
	}

	public void disconnect(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "disconnect", comObject); //$NON-NLS-1$

		comObject.setMethodName(METHOD_DISCONNECT);
		execute(comObject);

		Logger.getInstance().writeMethodExit(this.getClass(), "disconnect"); //$NON-NLS-1$
	}

	public void logon(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "logon", comObject); //$NON-NLS-1$

		comObject.setMethodName(METHOD_LOGON);
		execute(comObject);

		Logger.getInstance().writeMethodExit(this.getClass(), "logon"); //$NON-NLS-1$
	}

	public void logoff(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "logoff", comObject); //$NON-NLS-1$

		comObject.setMethodName(METHOD_LOGOFF);
		execute(comObject);

		Logger.getInstance().writeMethodExit(this.getClass(), "logoff"); //$NON-NLS-1$
	}
}
