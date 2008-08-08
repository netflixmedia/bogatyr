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

import java.net.ServerSocket;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.SecretKey;

import ch.orwell.bogatyr.controller.ApplicationTemplate;
import ch.orwell.bogatyr.controller.net.common.dto.User;
import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.crypto.CryptoAsymm;
import ch.orwell.bogatyr.helper.crypto.CryptoSymm;
import ch.orwell.bogatyr.helper.exception.ExceptionInvalidUserKey;
import ch.orwell.bogatyr.helper.logger.Logger;
import ch.orwell.bogatyr.helper.property.Property;


/**
 * This is the skeleton for servers
 *
 * @author Stefan Laubenberger
 * @version 20080808
 */
public abstract class ServerTemplate extends ApplicationTemplate {
	// Properties
	private static final String PROPERTY_NETWORK_PORT    = "network.port"; //$NON-NLS-1$
	private static final String PROPERTY_NETWORK_TIMEOUT = "network.timeout"; //$NON-NLS-1$
	private static final String PROPERTY_SERVER_INTERVAL = "server.interval"; //$NON-NLS-1$
	private static final String PROPERTY_SERVER_REQUESTS = "server.requests"; //$NON-NLS-1$
	
	// Lists
	private final Map<String, User> mapUser     = new ConcurrentHashMap<String, User>();
	private final Map<String, Key> mapCryptoKey = new ConcurrentHashMap<String, Key>();

	private int port;
	private ServerSocket serverSocket;

	private KeyPair asymmKey;
	private SecretKey symmKey;
//	private AsymmCrypto asymmCrypto; // asymmetric crypto engine
//	private SymmCrypto symmCrypto;   // symmetric crypto engine
	private int timeout;             // ServerSocketSocket timeout in seconds
	private int interval;            //ms between connection attemps
	private int requests;            //requests per interval


	protected ServerTemplate(final String propertiesStreamName) throws Exception {
		super(propertiesStreamName);
		init();

        serverSocket = new ServerSocket(port);
		if (timeout > 0) {
            serverSocket.setSoTimeout(timeout);
        }
	}
	
	protected int getPort() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getPort"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getPort", port); //$NON-NLS-1$

		return port;
	}

	protected ServerSocket getServerSocket() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getServerSocket"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getServerSocket", serverSocket); //$NON-NLS-1$
		
		return serverSocket;
	}

	public KeyPair getAsymmKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getAsymmKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getAsymmKey", asymmKey); //$NON-NLS-1$
		
		return asymmKey;
	}

	public SecretKey getSymmKey() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getSymmKey"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getSymmKey", symmKey); //$NON-NLS-1$
		
		return symmKey;
	}

	public int getInterval() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getInterval"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getInterval", interval); //$NON-NLS-1$
		
		return interval;
	}

	public int getRequests() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getRequests"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getRequests", requests); //$NON-NLS-1$
	
		return requests;
	}
	
	public User getUser(final String key) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUser", key); //$NON-NLS-1$
		User user = mapUser.get(key);

		Logger.getInstance().writeMethodExit(this.getClass(), "getUser", user); //$NON-NLS-1$
		return user;
	}

	protected Map<String, User> getUsers() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUsers"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getUsers", mapUser); //$NON-NLS-1$

		return Collections.unmodifiableMap(mapUser);
	}
	
	public Key getKey(final String key) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getKey", key); //$NON-NLS-1$

		Key cryptoKey = mapCryptoKey.get(key);
	    
		Logger.getInstance().writeMethodExit(this.getClass(), "getKey", cryptoKey); //$NON-NLS-1$
		return cryptoKey;
	}
	
	/**
	 * This adds a user to the {@link #mapUser}.
	 * 
	 * @param uniqueKey The unique key for the {@link #mapUser}.
	 * @param user The {@link User} to add.
	 * @throws ExceptionInvalidUserKey 
	 * @see Map
	 */
	public void addUser(final String uniqueKey, final User user) throws ExceptionInvalidUserKey {
		Logger.getInstance().writeMethodEntry(this.getClass(), "addUser", new Object[]{uniqueKey, user}); //$NON-NLS-1$
		
		if (HelperGeneral.isValidString(uniqueKey) && HelperGeneral.isValidObject(user)) {
           mapUser.put(uniqueKey, user);
		} else {
			throw new ExceptionInvalidUserKey();
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "addUser"); //$NON-NLS-1$
	}

	/**
	 * Removes a user from the server.
	 * 
	 * @param uniqueKey The unique key to find the user in the {@link #mapUser}.
	 * @see Map
	 */
	public void removeUser(final String uniqueKey) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "removeUser", uniqueKey); //$NON-NLS-1$

		mapUser.remove(uniqueKey);

		Logger.getInstance().writeMethodExit(this.getClass(), "removeUser"); //$NON-NLS-1$
	}

	/**
	 * Adds a crypto key to the {@link #mapCryptoKey}.
	 * 
	 * @param uniqueKey The unique key for the {@link #mapCryptoKey}.
	 * @param key The {@link Key} to add.
	 * @throws ExceptionInvalidUserKey 
	 * @see Map
	 */
	public void addCryptoKey(final String uniqueKey, final Key key) throws ExceptionInvalidUserKey {
		Logger.getInstance().writeMethodEntry(this.getClass(), "addCryptoKey", new Object[]{uniqueKey, key}); //$NON-NLS-1$

		if (HelperGeneral.isValidString(uniqueKey) && HelperGeneral.isValidObject(key)) {
            mapCryptoKey.put(uniqueKey, key);
		} else {
			throw new ExceptionInvalidUserKey();
		}

		Logger.getInstance().writeMethodExit(this.getClass(), "addCryptoKey"); //$NON-NLS-1$
	}

	/**
	 * Removes a crypto key from the server.
	 * 
	 * @param uniqueKey The unique key to find the key in the {@link #mapCryptoKey}.
	 * @see Map
	 */
	public void removeCryptoKey(final String uniqueKey) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "removeCryptoKey", uniqueKey); //$NON-NLS-1$

		mapCryptoKey.remove(uniqueKey);

		Logger.getInstance().writeMethodExit(this.getClass(), "removeCryptoKey"); //$NON-NLS-1$
	}

	/**
	 * Removes the user and the crypto key from the Server
	 * 
	 * @param uniqueKey The unique key to find the key in the {@link #mapCryptoKey}.
	 *                  the unique key to find the user in the {@link #mapUser}.
	 */
	public void removeAllData(final String uniqueKey) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "removeAllData", uniqueKey); //$NON-NLS-1$
		
		removeUser(uniqueKey);
		removeCryptoKey(uniqueKey);

		Logger.getInstance().writeMethodExit(this.getClass(), "removeAllData"); //$NON-NLS-1$
	}


	/*
	 * Private methods
	 */
	private void init() throws NoSuchAlgorithmException, NoSuchProviderException {
		readProperties();
		
		// Generate crypto keys
        asymmKey = CryptoAsymm.generateKeys(1024);

        symmKey = CryptoSymm.generateKey(128);
		
//		Context.getInstance().addData(ATT_INSTANCE, this);
	}
	
	private void readProperties() {
		String value = Property.getInstance().getProperty(PROPERTY_NETWORK_PORT);
		if (HelperGeneral.isValidString(value)) {
            port = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_PORT);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_NETWORK_PORT + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(50);
		}
	
		value = Property.getInstance().getProperty(PROPERTY_NETWORK_TIMEOUT);
		if (HelperGeneral.isValidString(value)) {
            timeout = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_TIMEOUT);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_NETWORK_TIMEOUT + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(51);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_SERVER_INTERVAL);
		if (HelperGeneral.isValidString(value)) {
            interval = Property.getInstance().getPropertyInt(PROPERTY_SERVER_INTERVAL);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_SERVER_INTERVAL + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(52);
		}

		value = Property.getInstance().getProperty(PROPERTY_SERVER_REQUESTS);
		if (HelperGeneral.isValidString(value)) {
            requests = Property.getInstance().getPropertyInt(PROPERTY_SERVER_REQUESTS);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_SERVER_REQUESTS + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(53);
		}
	}
}
