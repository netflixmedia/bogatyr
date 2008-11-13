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


/**
 * This is the super class for all Security-children.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class Security {	
	private static final Map<SocketAddress, Long> contactTable = new ConcurrentHashMap<SocketAddress, Long>(); //#LS new

	/**
	 * Validates a {@link ComObject} and the requested method on the server.
	 * 
	 * @param server
     * @param comObject communication object
	 * @throws Exception
     * @return true/false
	 */
	public static boolean isMethodGranted(final ServerAbstract server, final ComObject comObject) throws Exception {
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
		return flag;
	}

	/**
	 * Validates a socket (simple DOS and overload protection).
	 * 
	 * Ensures that no client connects faster than {@link ServerAbstract#getInterval()}.
	 * Ensures that the total number of client connects within {@link ServerAbstract#getInterval()} dosen't reach {@link ServerAbstract#getRequests()}
	 * 
	 * @param server to protect
     * @param socket to protect
	 * @see ServerAbstract#getInterval()
	 * @see ServerAbstract#getRequests()
     * @return true/false
	 */
	public static boolean isValidContact(final ServerAbstract server, final Socket socket) {
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
		return isValid;
	}
	
	/**
	 * Checks if a user is logged on.
	 * 
	 * @param server
     * @param comObject communication Object
     * @return true/false
	 */
	public static boolean isUserLoggedOn(final ServerAbstract server, final ComObject comObject) {
		return server.getUser(comObject.getUserKey()) != null;
    }
}
