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
package ch.orwell.bogatyr.controller.net.client.synch;

import ch.orwell.bogatyr.controller.net.client.ClientTemplate;
import ch.orwell.bogatyr.controller.net.common.dto.ComObject;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the skeleton for synchron clients
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public abstract class ClientSynchTemplate extends ClientTemplate {

	
	protected ClientSynchTemplate(final String propertiesStreamName) throws Exception {
		super(propertiesStreamName);
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "execute", data); //$NON-NLS-1$

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

		Logger.getInstance().writeMethodExit(this.getClass(), "execute", result); //$NON-NLS-1$
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "sendAsymmKey", comObject); //$NON-NLS-1$

		comObject.setMethodName(METHOD_SEND_ASYMMKEY);
		execute(comObject);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "sendAsymmKey"); //$NON-NLS-1$
	}

	/**
	 * Send a symm key
	 * @param comObject The {@link ComObject} with the symm key
	 * @see ComObject
	 */
	public void sendSymmKey(final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "sendSymmKey", comObject); //$NON-NLS-1$

		comObject.setMethodName(METHOD_SEND_SYMMKEY);
		execute(comObject);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "sendSymmKey"); //$NON-NLS-1$
	}
}
