package ch.orwell.bogatyr.net.common.dto;



/**
 * This is the communication interface<br>
 * it will be implemented by all server threads and client applications
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070622
 */
public interface ComInterface {
	// Methods
	public static final String METHOD_CONNECT       = "connect"; //$NON-NLS-1$
	public static final String METHOD_DISCONNECT    = "disconnect"; //$NON-NLS-1$
	public static final String METHOD_SEND_ASYMMKEY = "sendAsymmKey"; //$NON-NLS-1$
	public static final String METHOD_SEND_SYMMKEY  = "sendSymmKey"; //$NON-NLS-1$
	public static final String METHOD_LOGON         = "logon"; //$NON-NLS-1$
	public static final String METHOD_LOGOFF        = "logoff"; //$NON-NLS-1$

	/**
     * Send a connect request
     * This is the first called method on the server
     * @param comObject
     * @throws Exception
     */
	public void connect(ComObject comObject) throws Exception;

	/**
     * Send a disconnect request
     * This is the last called method on the server
     * @param comObject
     * @throws Exception
     */
	public void disconnect(ComObject comObject) throws Exception;

	/**
     * Send the asymmetric key
     * @param comObject
     * @throws Exception
     */
	public void sendAsymmKey(ComObject comObject) throws Exception;
	
	/**
     * Send the symmetric key
     * @param comObject
     * @throws Exception
     */
	public void sendSymmKey(ComObject comObject) throws Exception;
    
	/**
     * Logon to server
     * @param comObject
     * @throws Exception
     */
	public void logon(ComObject comObject) throws Exception;
    
    /**
     * Logoff from server
     * @param comObject
     * @throws Exception
     */
	public void logoff(ComObject comObject) throws Exception;
}
