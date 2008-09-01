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
package ch.sisprocom.bogatyr.controller.net.server;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.sisprocom.bogatyr.controller.net.common.ICom;
import ch.sisprocom.bogatyr.controller.net.common.dto.ComObject;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is the super class for all Security-children
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class Security {	
	private static final Map<SocketAddress, Long> contactTable = new ConcurrentHashMap<SocketAddress, Long>(); //#LS new

	/**
	 * Validates a {@link ComObject} and the requested method on the server
	 * 
	 * @param server
     * @param comObject Communication object
	 * @throws Exception
     * @return true/false
	 */
	public static boolean isMethodGranted(final ServerTemplate server, final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(Security.class, "isMethodGranted", new Object[]{server, comObject}); //$NON-NLS-1$

		boolean flag = false;
		
		if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_CONNECT)) {
			flag = true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_DISCONNECT)) {
			flag = true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_SEND_ASYMMKEY)) {
			flag = true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_SEND_SYMMKEY)) {
			flag = true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_LOGON)) {
			flag = true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_LOGOFF)) {
			flag = isUserLoggedOn(server, comObject);
		}
		
		Logger.getInstance().writeMethodExit(Security.class, "isMethodGranted", flag); //$NON-NLS-1$
		return flag;
	}

	/**
	 * Validates a socket (simple DOS and overload protection)
	 * 
	 * Ensures that no client connects faster than {@link ServerTemplate#getInterval()}.
	 * Ensures that the total number of client connects within {@link ServerTemplate#getInterval()} dosen't reach {@link ServerTemplate#getRequests()}
	 * 
	 * @param server The server to protect
     * @param socket The socket to protect
	 * @see ServerTemplate#getInterval()
	 * @see ServerTemplate#getRequests()
     * @return true/false
	 */
	public static boolean isValidContact(final ServerTemplate server, final Socket socket) {
		Logger.getInstance().writeMethodEntry(Security.class, "isValidContact", new Object[]{server, socket}); //$NON-NLS-1$

		boolean isValid = true;

		if (server.getInterval() != 0) { // not disabled

			final SocketAddress socketAddress = socket.getRemoteSocketAddress();
			final Long timeOld = contactTable.get(socketAddress);
			final long time = System.currentTimeMillis();
			final long timeDifference = time - (long) server.getInterval();

            if (timeOld != null && time - timeOld < (long) server.getInterval()) {
				isValid = false;
			}

            int counter = 0;
            for (Iterator<SocketAddress> iter = contactTable.keySet().iterator(); iter.hasNext() && isValid;) {
				final long element = contactTable.get(iter.next());
							
				if (element < timeDifference) {
					counter++;
				}
				
				if (counter > server.getRequests()) {
					isValid = false;
				}
			}
			
			contactTable.put(socketAddress, time);
		}

		Logger.getInstance().writeMethodExit(Security.class, "isValidContact", isValid); //$NON-NLS-1$
		return isValid;
	}
	
	/**
	 * Checks if a user is logged on.
	 * 
	 * @param server
     * @param comObject Communication Object
     * @return true/false
	 */
	public static boolean isUserLoggedOn(final ServerTemplate server, final ComObject comObject) {
		Logger.getInstance().writeMethodEntry(Security.class, "isUserLoggedOn", new Object[]{server, comObject}); //$NON-NLS-1$

		final boolean isLoggedOn = server.getUser(comObject.getUserKey()) != null;

		Logger.getInstance().writeMethodExit(Security.class, "isUserLoggedOn", isLoggedOn); //$NON-NLS-1$
        return isLoggedOn;
    }
}
