package ch.orwell.bogatyr.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.Application;
import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.crypto.AsymmCrypto;
import ch.orwell.bogatyr.crypto.SymmCrypto;
import ch.orwell.bogatyr.exception.ExceptionHelper;
import ch.orwell.bogatyr.exception.InvalidUserKeyException;
import ch.orwell.bogatyr.net.common.dto.User;
import ch.orwell.bogatyr.util.GeneralHelper;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the skeleton for all servers
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @author Roman Wuersch
 * @version 20070624
 */
public abstract class Server extends Application {
	// Attributes
	public static String ATT_INSTANCE = "ch.orwell.bogatyr.net.server.Server"; //$NON-NLS-1$

	// Properties
	private static final String PROPERTY_NETWORK_PORT    = "network.port"; //$NON-NLS-1$
	private static final String PROPERTY_NETWORK_TIMEOUT = "network.timeout"; //$NON-NLS-1$
	private static final String PROPERTY_SERVER_INTERVAL = "server.interval"; //$NON-NLS-1$
	private static final String PROPERTY_SERVER_REQUESTS = "server.requests"; //$NON-NLS-1$
	
	// Lists
	protected Map<String, User> userList     = new ConcurrentHashMap<String, User>();
	protected Map<String, Key> cryptoKeyList = new ConcurrentHashMap<String, Key>();

	protected int port;
	protected ServerSocket serverSocket;

	private AsymmCrypto asymmCrypto; // asymmetric crypto engine
	private SymmCrypto symmCrypto;   // symmetric crypto engine
	private int timeout;             // ServerSocketSocket timeout in seconds
	private int interval;            //ms between connection attemps
	private int requests;            //requests per interval

	/**
	 * Constructs a server.
	 * @param propertiesFileName Properties file for the server configuration.
	 */
	public Server(String propertiesFileName) {
		super(propertiesFileName);
		init();
		
		try {
			this.serverSocket = new ServerSocket(this.port);
			if (this.timeout > 0) this.serverSocket.setSoTimeout(this.timeout); 
        } catch (IOException ex) {
        	Logger.getInstance().writeException(className + "::<constructor>", ExceptionHelper.EX_COMMUNICATION, ex); //$NON-NLS-1$
            exit(800); 
        }
	}

	public final AsymmCrypto getAsymmCrypto() {
		return this.asymmCrypto;
	}

	public final SymmCrypto getSymmCrypto() {
		return this.symmCrypto;
	}

	public final int getInterval() {
		return this.interval;
	}

	public final int getRequests() {
		return this.requests;
	}

	public final User getUser(String key) {
	    return this.userList.get(key);
	}

	public final Key getKey(String key) {
	    return this.cryptoKeyList.get(key);
	}
	
	/**
	 * This adds a user to the {@link #userList}.
	 * 
	 * @param uniqueKey The unique key for the {@link #userList}.
	 * @param user The user to add.
	 * @throws InvalidUserKeyException
	 * @see Map
	 */
	public final void addUser(String uniqueKey, User user) throws InvalidUserKeyException {
		if (GeneralHelper.isValidString(uniqueKey) && GeneralHelper.isValidObject(user)) {
			Logger.getInstance().writeDebug(className + "::addUser", "user: " + user); //$NON-NLS-1$ //$NON-NLS-2$
			this.userList.put(uniqueKey, user);
		} else {
			throw new InvalidUserKeyException();
		}
	}

	/**
	 * Removes a user from the server.
	 * 
	 * @param uniqueKey The unique key to find the user in the {@link #userList}.
	 * @see Map
	 */
	public final void removeUser(String uniqueKey) {
		Logger.getInstance().writeDebug(className + "::removeUser", "uniqueKey: " + uniqueKey); //$NON-NLS-1$ //$NON-NLS-2$
		this.userList.remove(uniqueKey);
	}

	/**
	 * Adds a crypto key to the {@link #cryptoKeyList}.
	 * @param uniqueKey The unique key for the {@link #cryptoKeyList}.
	 * @param key The key to add.
	 * @throws InvalidUserKeyException
	 * @see Map
	 */
	public final void addCryptoKey(String uniqueKey, Key key) throws InvalidUserKeyException {
		if (GeneralHelper.isValidString(uniqueKey) && GeneralHelper.isValidObject(key)) {
			Logger.getInstance().writeDebug(className + "::addCryptoKey", "uniqueKey: " + uniqueKey + " key: " + key);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			this.cryptoKeyList.put(uniqueKey, key);
		} else {
			throw new InvalidUserKeyException();
		}
	}

	/**
	 * Removes a key from the server.
	 * 
	 * @param uniqueKey The unique key to find the key in the {@link #cryptoKeyList}.
	 * @see Map
	 */
	public final void removeCryptoKey(String uniqueKey) {
		Logger.getInstance().writeDebug(className + "::removeCryptoKey", "uniqueKey: " + uniqueKey); //$NON-NLS-1$ //$NON-NLS-2$
		this.cryptoKeyList.remove(uniqueKey);
	}

	/**
	 * Removes both the user and the key from the Server
	 * @param uniqueKey The unique key to find the key in the {@link #cryptoKeyList}.<br>
	 *                  the unique key to find the user in the {@link #userList}.
	 */
	public void removeAllData(String uniqueKey) {
		removeUser(uniqueKey);
		removeCryptoKey(uniqueKey);
	}


	/*
	 * Private methods
	 */
	private void init() {
		readProperties();
		
		// Initialize crypto engines
		this.asymmCrypto = new AsymmCrypto();
		try {
			this.asymmCrypto.generateKeys();
		} catch (NoSuchAlgorithmException ex) {
        	Logger.getInstance().writeException(className + "::init", ExceptionHelper.EX_ASYMMCRYPTO, ex); //$NON-NLS-1$
            exit(600); 
		}
		
		this.symmCrypto = new SymmCrypto();
		
		Context.getInstance().addData(ATT_INSTANCE, this);
	}
	
	private void readProperties() {
		this.port     = Context.getInstance().getProperties().getPropertyInt(PROPERTY_NETWORK_PORT);
		this.timeout  = Context.getInstance().getProperties().getPropertyInt(PROPERTY_NETWORK_TIMEOUT) * 1000;
		this.interval = Context.getInstance().getProperties().getPropertyInt(PROPERTY_SERVER_INTERVAL);
		this.requests = Context.getInstance().getProperties().getPropertyInt(PROPERTY_SERVER_REQUESTS);
	}
}
