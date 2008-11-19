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

import java.net.ServerSocket;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

import ch.sisprocom.bogatyr.controller.ApplicationAbstract;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.crypto.CryptoAsymm;
import ch.sisprocom.bogatyr.helper.crypto.CryptoSymm;
import ch.sisprocom.bogatyr.helper.exception.ExceptionInvalidUserKey;
import ch.sisprocom.bogatyr.helper.property.Property;
import ch.sisprocom.bogatyr.model.dao.User;


/**
 * This is the skeleton for servers.
 *
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class ServerAbstract extends ApplicationAbstract {
	private static final Logger log = Logger.getLogger(ServerAbstract.class);
	
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


	protected ServerAbstract() throws Exception {
		super();
		init();

        serverSocket = new ServerSocket(port);
		if (timeout > 0) {
            serverSocket.setSoTimeout(timeout);
        }
	}
	
	protected int getPort() {
		return port;
	}

	protected ServerSocket getServerSocket() {
		return serverSocket;
	}

	public KeyPair getAsymmKey() {
		return asymmKey;
	}

	public SecretKey getSymmKey() {
		return symmKey;
	}

	public int getInterval() {
		return interval;
	}

	public int getRequests() {
		return requests;
	}
	
	public User getUser(final String key) {
		User user = mapUser.get(key);

		return user;
	}

	protected Map<String, User> getUsers() {
		return Collections.unmodifiableMap(mapUser);
	}
	
	public Key getKey(final String key) {
		Key cryptoKey = mapCryptoKey.get(key);
		return cryptoKey;
	}
	
	/**
	 * This adds a user to the {@link #mapUser}.
	 * 
	 * @param uniqueKey unique key for the {@link #mapUser}.
	 * @param user {@link User} to add.
	 * @throws ExceptionInvalidUserKey 
	 * @see Map
	 */
	public void addUser(final String uniqueKey, final User user) throws ExceptionInvalidUserKey {
		if (HelperGeneral.isValidString(uniqueKey) && HelperGeneral.isValidObject(user)) {
           mapUser.put(uniqueKey, user);
		} else {
			throw new ExceptionInvalidUserKey();
		}
	}

	/**
	 * Removes a user from the server.
	 * 
	 * @param uniqueKey unique key to find the user in the {@link #mapUser}.
	 * @see Map
	 */
	public void removeUser(final String uniqueKey) {
		mapUser.remove(uniqueKey);
	}

	/**
	 * Adds a crypto key to the {@link #mapCryptoKey}.
	 * 
	 * @param uniqueKey unique key for the {@link #mapCryptoKey}.
	 * @param key {@link Key} to add.
	 * @throws ExceptionInvalidUserKey 
	 * @see Map
	 */
	public void addCryptoKey(final String uniqueKey, final Key key) throws ExceptionInvalidUserKey {
		if (HelperGeneral.isValidString(uniqueKey) && HelperGeneral.isValidObject(key)) {
            mapCryptoKey.put(uniqueKey, key);
		} else {
			throw new ExceptionInvalidUserKey();
		}
	}

	/**
	 * Removes a crypto key from the server.
	 * 
	 * @param uniqueKey unique key to find the key in the {@link #mapCryptoKey}.
	 * @see Map
	 */
	public void removeCryptoKey(final String uniqueKey) {
		mapCryptoKey.remove(uniqueKey);
	}

	/**
	 * Removes the user and the crypto key from the Server.
	 * 
	 * @param uniqueKey The unique key to find the key in the {@link #mapCryptoKey}.
	 *                  the unique key to find the user in the {@link #mapUser}.
	 */
	public void removeAllData(final String uniqueKey) {
		removeUser(uniqueKey);
		removeCryptoKey(uniqueKey);
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
			log.error(PROPERTY_NETWORK_PORT + " == 'null'"); //$NON-NLS-1$
			exit(10);
		}
	
		value = Property.getInstance().getProperty(PROPERTY_NETWORK_TIMEOUT);
		if (HelperGeneral.isValidString(value)) {
            timeout = Property.getInstance().getPropertyInt(PROPERTY_NETWORK_TIMEOUT);
		} else {
			log.error(PROPERTY_NETWORK_TIMEOUT + " == 'null'"); //$NON-NLS-1$
			exit(11);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_SERVER_INTERVAL);
		if (HelperGeneral.isValidString(value)) {
            interval = Property.getInstance().getPropertyInt(PROPERTY_SERVER_INTERVAL);
		} else {
			log.error(PROPERTY_SERVER_INTERVAL + " == 'null'"); //$NON-NLS-1$
			exit(12);
		}

		value = Property.getInstance().getProperty(PROPERTY_SERVER_REQUESTS);
		if (HelperGeneral.isValidString(value)) {
            requests = Property.getInstance().getPropertyInt(PROPERTY_SERVER_REQUESTS);
		} else {
			log.error(PROPERTY_SERVER_REQUESTS + " == 'null'"); //$NON-NLS-1$
			exit(13);
		}
	}
}
