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
package ch.orwell.bogatyr.net.common;

import ch.orwell.bogatyr.net.common.dto.ComObject;


/**
 * This is the communication interface
 * It will be implemented by all server threads and client applications
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
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
     * 
     * @param comObject
     * @throws Exception
     */
	public void connect(ComObject comObject) throws Exception;

	/**
     * Send a disconnect request
     * This is the last called method on the server
     * 
     * @param comObject
     * @throws Exception
     */
	public void disconnect(ComObject comObject) throws Exception;

	/**
     * Send the asymmetric key
     * 
     * @param comObject
     * @throws Exception
     */
	public void sendAsymmKey(ComObject comObject) throws Exception;
	
	/**
     * Send the symmetric key
     * 
     * @param comObject
     * @throws Exception
     */
	public void sendSymmKey(ComObject comObject) throws Exception;
    
	/**
     * Logon to server
     * 
     * @param comObject
     * @throws Exception
     */
	public void logon(ComObject comObject) throws Exception;
    
    /**
     * Logoff from server
     * 
     * @param comObject
     * @throws Exception
     */
	public void logoff(ComObject comObject) throws Exception;
}
