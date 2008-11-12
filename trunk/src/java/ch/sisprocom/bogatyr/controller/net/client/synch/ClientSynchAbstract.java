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
package ch.sisprocom.bogatyr.controller.net.client.synch;

import ch.sisprocom.bogatyr.controller.net.client.ClientAbstract;
import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;


/**
 * This is the skeleton for synchron clients
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class ClientSynchAbstract extends ClientAbstract {

	
	protected ClientSynchAbstract() throws Exception {
		super();
	}

	
	/*
	 * Overridden methods
	 */
	/**
	 * Send the ComObject to the Server.<p>
	 * The server will execute the given method name.
	 *
     * @param  data ComObject
     * @return Result object
	 * @throws Exception
     * @see ComObject
     */
    @Override
    protected Object execute(final ComObject data) throws Exception {
    	openStream();
    	
        // write the ComObject on the socket-stream
        writeObject(data);

        // read the ComObject from the socket-stream (waits until it get's an answer from the server)
        final ComObject comObject = readObject();
        if (comObject.getException() != null) {
            throw comObject.getException();
        }
        final Object result = comObject.getData();

    	closeStream();

    	return result;
    }
    
	
	
	/*
	 * Implemented methods
	 */
	/**
	 * Send a asymm key
	 * @param comObject The {@link ComObject} with the asymm key
	 * @see ComObject
	 */
	public void sendAsymmKey(final ComObject comObject) throws Exception {
		comObject.setMethodName(METHOD_SEND_ASYMMKEY);
		execute(comObject);
	}

	/**
	 * Send a symm key
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(final ComObject comObject) throws Exception {
		comObject.setMethodName(METHOD_SEND_SYMMKEY);
		execute(comObject);
	}
}
