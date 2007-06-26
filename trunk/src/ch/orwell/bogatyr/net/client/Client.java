package ch.orwell.bogatyr.net.client;

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

import ch.orwell.bogatyr.Application;
import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.crypto.AsymmCrypto;
import ch.orwell.bogatyr.crypto.SymmCrypto;
import ch.orwell.bogatyr.exception.ExceptionHelper;
import ch.orwell.bogatyr.exception.InvalidStreamSizeException;
import ch.orwell.bogatyr.net.common.dto.ComContainer;
import ch.orwell.bogatyr.net.common.dto.ComInterface;
import ch.orwell.bogatyr.net.common.dto.ComObject;
import ch.orwell.bogatyr.net.common.dto.User;
import ch.orwell.bogatyr.util.GeneralHelper;
import ch.orwell.bogatyr.util.Logger;

/**
 * This is the skeleton for all clients
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070625
 */
public abstract class Client extends Application implements ComInterface {
	// Properties
	private static final String PROPERTY_NETWORK_HOST    = "network.host"; //$NON-NLS-1$
	private static final String PROPERTY_NETWORK_PORT    = "network.port"; //$NON-NLS-1$
	private static final String PROPERTY_NETWORK_SIZE    = "network.size"; //$NON-NLS-1$
	private static final String PROPERTY_CLIENT_INTERVAL = "client.interval"; //$NON-NLS-1$
	
	protected Key cryptoKey;
	
	protected AsymmCrypto asymmCrypto; // asymmetric crypto engine
	protected SymmCrypto symmCrypto;   // symmetric crypto engine
	protected User user;
		
	private String host;
	private int port;
	private int size; // max. message size of a ComContainer
	private Socket socket;
	private int interval; //ms between connection attemps
	
	/**
	 * Constructs an Client.
	 * @param propertiesFileName The property file for the configuration.
	 */
	public Client(String propertiesFileName) {
		super(propertiesFileName);
		init();
	}
	
	public final User getUser() {
		return this.user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	/**
     * Open a stream
     * @throws IOException
     */
	protected final void openStream() throws IOException {
//		this.socket = new Socket((new URL(this.host)).getHost(), this.port);
		this.socket = new Socket(this.host, this.port);
	}
	
	/**
     * Close a stream
     * @throws IOException
     */
	protected final void closeStream() throws IOException {
		this.socket.close();
	}

	/**
     * Reads a socket-stream
     * @return Returns a byte-array
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
     */
	protected final byte[] readStream() throws IOException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    	ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());

		ComContainer input = (ComContainer)ois.readObject();

		byte[] data = input.getData();
		
    	if (this.cryptoKey instanceof PublicKey) {
    		data = this.asymmCrypto.decrypt(data);
    	} else if (this.cryptoKey instanceof SecretKey) {
    		data = this.symmCrypto.decrypt(data, this.cryptoKey);
    	}

    	return data;
    }

    /**
     * Writes on a socket-stream
     * @param key The key for authentication
     * @param data The data as a byte-array
     * @throws IOException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws InvalidStreamSizeException 
     * @throws Exception 
     */
    protected final void writeStream(String key, byte[] data) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidStreamSizeException {
		if (this.size == 0 || data.length < this.size) {
			
			if (this.interval > 0) { // prevent too fast connection attemps (DOS protection on the server side)
				GeneralHelper.suspendMilliseconds(this.interval);
			}
			
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
	
			byte[] temp = data;
			
			if (this.cryptoKey instanceof PublicKey) {
		   		temp = this.asymmCrypto.encrypt(data, (PublicKey)this.cryptoKey);
			} else if (this.cryptoKey instanceof SecretKey) {
				temp = this.symmCrypto.encrypt(data, this.cryptoKey);
	    	}

			ComContainer output = new ComContainer(key, temp);
			oos.writeObject(output);
			oos.flush();
		} else {
			throw new InvalidStreamSizeException(this.size, data.length);
		}
	}
    
    /**
     * Reads a ComObject
     * @return Returns the ComObject
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public final ComObject readObject() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
		ComObject comObject = (ComObject)GeneralHelper.getObject(readStream());
		Logger.getInstance().writeDebug(className + "::readObject", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		return comObject;
    }


	/**
	 * Writes a ComObject
	 * @param comObject The ComObject to write
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 * @throws InvalidStreamSizeException
	 * @see ComObject
	 */
    public final void writeObject(ComObject comObject) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidStreamSizeException {
		Logger.getInstance().writeDebug(className + "::writeObject", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
    	writeStream(comObject.getUserKey(), GeneralHelper.getBytes(comObject));
    }

	/**
	 * Send the ComObject to the Server.
	 * <p>
	 * The Server will execute the given Methodname.
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
	/**
	 * Intialize the SynchClient object.
	 * <p>
	 * Do some logging.
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
		try {
			this.symmCrypto.generateKey();
		} catch (NoSuchAlgorithmException ex) {
        	Logger.getInstance().writeException(className + "::init", ExceptionHelper.EX_SYMMCRYPTO, ex); //$NON-NLS-1$
            exit(700); 
		}
	}

	/**
	 * Reads the Properties.
	 * <p>
	 * Set host to network.host Set port to network.port
	 * 
	 */
	private void readProperties() {
		this.host     = Context.getInstance().getProperties().getProperty(PROPERTY_NETWORK_HOST);
		this.port     = Context.getInstance().getProperties().getPropertyInt(PROPERTY_NETWORK_PORT);
		this.size     = Context.getInstance().getProperties().getPropertyInt(PROPERTY_NETWORK_SIZE);
		this.interval = Context.getInstance().getProperties().getPropertyInt(PROPERTY_CLIENT_INTERVAL);
	}

	
	/*
	 * Implemented methods
	 */	
	public void connect(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::connect", "comObject: " + comObject); //$NON-NLS-1$ //$NON-NLS-2$
		comObject.setMethodName(ComInterface.METHOD_CONNECT);
		execute(comObject);
	}

	public void disconnect(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::disconnect", "comObject: " + comObject); //$NON-NLS-1$ //$NON-NLS-2$
		comObject.setMethodName(ComInterface.METHOD_DISCONNECT);
		execute(comObject);
	}

	public void logon(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::logon", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		comObject.setMethodName(ComInterface.METHOD_LOGON);
		execute(comObject);
	}

	public void logoff(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::logoff", "comObject: " + comObject); //$NON-NLS-1$ //$NON-NLS-2$
		comObject.setMethodName(ComInterface.METHOD_LOGOFF);
		execute(comObject);
	}
}