/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.net.common;

import ch.orwell.bogatyr.net.common.dto.ComObject;


/**
 * This is the super class for all MethodParser-children
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class MethodParser {	
	
	/**
	 * Execute a method in the server thread or client.
	 * 
	 * @param caller The server thread or the client
	 * @param comObject Communication object between the server thread an the client
	 * @throws Exception
	 */
	public static Object execute(ComInterface caller, ComObject comObject) throws Exception {
		Object result = null;

		if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_CONNECT)) {
			caller.connect(comObject);
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_DISCONNECT)) {
			caller.disconnect(comObject);
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_SEND_ASYMMKEY)) {
			caller.sendAsymmKey(comObject);
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_SEND_SYMMKEY)) {
			caller.sendSymmKey(comObject);
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_LOGON)) {
			caller.logon(comObject);
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_LOGOFF)) {
			caller.logoff(comObject);
		}
		return result;
	}
}
