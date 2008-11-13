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
package ch.sisprocom.bogatyr.controller.net.common;

import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;


/**
 * This is the communication interface.
 * It will be implemented by all server threads and client applications.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public interface ICom {
	// Methods
    String METHOD_CONNECT       = "connect"; //$NON-NLS-1$
	String METHOD_DISCONNECT    = "disconnect"; //$NON-NLS-1$
	String METHOD_SEND_ASYMMKEY = "sendAsymmKey"; //$NON-NLS-1$
	String METHOD_SEND_SYMMKEY  = "sendSymmKey"; //$NON-NLS-1$
	String METHOD_LOGON         = "logon"; //$NON-NLS-1$
	String METHOD_LOGOFF        = "logoff"; //$NON-NLS-1$

	/**
     * Send a connect request.
     * This is the first called method on the server.
     * 
     * @param comObject communication object
     * @throws Exception
     */
    void connect(ComObject comObject) throws Exception;

	/**
     * Send a disconnect request.
     * This is the last called method on the server.
     * 
     * @param comObject communication object
     * @throws Exception
     */
    void disconnect(ComObject comObject) throws Exception;

	/**
     * Send the asymmetric key.
     * 
     * @param comObject communication object
     * @throws Exception
     */
    void sendAsymmKey(ComObject comObject) throws Exception;
	
	/**
     * Send the symmetric key.
     * 
     * @param comObject communication object
     * @throws Exception
     */
    void sendSymmKey(ComObject comObject) throws Exception;
    
	/**
     * Logon to server.
     * 
     * @param comObject communication object
     * @throws Exception
     */
    void logon(ComObject comObject) throws Exception;
    
    /**
     * Logoff from server.
     * 
     * @param comObject communication object
     * @throws Exception
     */
    void logoff(ComObject comObject) throws Exception;
}
