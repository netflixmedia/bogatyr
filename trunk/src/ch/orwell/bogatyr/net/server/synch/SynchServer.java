package ch.orwell.bogatyr.net.server.synch;

import ch.orwell.bogatyr.net.server.Server;


/**
 * This is the skeleton for all synchron servers
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070618
 */
public abstract class SynchServer extends Server {

	/**
	 * Constructs a synchron server.<br>
	 * @param propertiesFileName Properties file for the server configuration.
	 */
    public SynchServer(String propertiesFileName) {
		super(propertiesFileName);
 	}
}
