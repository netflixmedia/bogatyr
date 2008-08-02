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
package ch.orwell.bogatyr.controller.net.server;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.controller.net.common.ICom;
import ch.orwell.bogatyr.controller.net.common.dto.ComObject;


/**
 * This is the super class for all Security-children
 * 
 * @author Stefan Laubenberger
 * @version 20080515
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
		if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_CONNECT)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_DISCONNECT)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_SEND_ASYMMKEY)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_SEND_SYMMKEY)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_LOGON)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_LOGOFF)) {
			return isUserLoggedOn(server, comObject);
		}
		return false;
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
	 */
	public static boolean isValidContact(final ServerTemplate server, final Socket socket) {
		boolean flag = true;

		if (server.getInterval() != 0) { // not disabled

			final SocketAddress socketAddress = socket.getRemoteSocketAddress();
			final Long timeOld = contactTable.get(socketAddress);
			final long time = System.currentTimeMillis();
			final long timeDifference = time - (long) server.getInterval();

            if (timeOld != null && time - timeOld < (long) server.getInterval()) {
				flag = false;
			}

            int counter = 0;
            for (Iterator<SocketAddress> iter = contactTable.keySet().iterator(); iter.hasNext() && flag;) {
				final long element = contactTable.get(iter.next());
							
				if (element < timeDifference) {
					counter++;
				}
				
				if (counter > server.getRequests()) {
					flag = false;
				}
			}
			
			contactTable.put(socketAddress, time);
		}
		return flag;
	}
	
	/**
	 * Checks if a user is logged on.
	 * 
	 * @param server
     * @param comObject Communication Object
     * @return true/false
	 */
	public static boolean isUserLoggedOn(final ServerTemplate server, final ComObject comObject) {
        return server.getUser(comObject.getUserKey()) != null;
    }
}
