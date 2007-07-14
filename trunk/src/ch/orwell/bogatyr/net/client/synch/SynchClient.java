/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.net.client.synch;

import ch.orwell.bogatyr.net.client.Client;
import ch.orwell.bogatyr.net.common.ComInterface;
import ch.orwell.bogatyr.net.common.dto.ComObject;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is the skeleton for all synchron clients
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class SynchClient extends Client {

	/**
	 * Constructs an SynchClient.
	 * @param propertiesFileName The property file for the configuration.
	 */
	public SynchClient(String propertiesFileName) {
		super(propertiesFileName);
	}
	
	
	/*
	 * Implemented methods
	 */
	/**
	 * Send a asymm key
	 * @param comObject The {@link ComObject} with the asymm key
	 * @see ComObject
	 */
	public void sendAsymmKey(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::sendAsymmKey", "comObject: " + comObject);  //$NON-NLS-1$//$NON-NLS-2$
		comObject.setMethodName(ComInterface.METHOD_SEND_ASYMMKEY);
		execute(comObject);
	}

	/**
	 * Send a symm key
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(ComObject comObject) throws Exception {
		Logger.getInstance().writeDebug(className + "::sendSymmKey", "comObject: " + comObject); //$NON-NLS-1$ //$NON-NLS-2$
		comObject.setMethodName(ComInterface.METHOD_SEND_SYMMKEY);
		execute(comObject);
	}

	
	/*
	 * Overriden methods
	 */
	/**
	 * Send the ComObject to the Server.<p>
	 * The Server will execute the given Methodname.
	 *
     * @param  data ComObject
     * @return Result Data
	 * @throws Exception
     * @see ComObject
     */
    @Override
    protected Object execute(ComObject data) throws Exception {
    	Logger.getInstance().writeDebug(className + "::execute", "data: " + data); //$NON-NLS-1$ //$NON-NLS-2$
    	
    	Object result;
        ComObject comObject = data;
        
    	openStream();
    	
        // write the ComObject on the socket-stream
        writeObject(comObject);

        // read the ComObject from the socket-stream (waits until it get's an answer from the server)
        comObject = readObject();
        if (comObject.getException() != null) {
            throw comObject.getException();
        }
        result = comObject.getData();

    	closeStream();

        return result;
    }
}
