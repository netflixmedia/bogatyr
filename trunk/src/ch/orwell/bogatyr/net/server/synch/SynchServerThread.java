package ch.orwell.bogatyr.net.server.synch;

import java.net.Socket;

import ch.orwell.bogatyr.net.server.ServerThread;


/**
 * This is the Skeleton for all synchron server threads
 *  
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070618
 */
public abstract class SynchServerThread extends ServerThread {

	/**
	 * Constructs a synchronized server.<br>
	 * @param socket The Socket to start the server thread.
	 */
	public SynchServerThread(Socket socket) {
		super(socket);
	}
}
