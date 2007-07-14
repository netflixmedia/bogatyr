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
package ch.orwell.bogatyr.net.server;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.net.common.ComInterface;
import ch.orwell.bogatyr.net.common.dto.ComObject;


/**
 * This is the super class for all Security-children
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class Security {	
	private static Map<SocketAddress, Long> contactTable = new ConcurrentHashMap<SocketAddress, Long>(); //#LS new

	/**
	 * Validates a {@link ComObject} and the requested method on the server
	 * 
	 * @param comObject Communication object
	 * @throws Exception
	 */
	public static boolean isMethodGranted(ComObject comObject) throws Exception {
		if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_CONNECT)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_DISCONNECT)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_SEND_ASYMMKEY)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_SEND_SYMMKEY)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_LOGON)) {
			return true;
		} else if (comObject.getMethodName().equalsIgnoreCase(ComInterface.METHOD_LOGOFF)) {
			return isUserLoggedOn(comObject);
		}
		return false;
	}

	/**
	 * Validates a socket (simple DOS and overload protection)
	 * 
	 * Ensures that no client connects faster than {@link Server#getInterval()}.
	 * Ensures that the total number of client connects within {@link Server#getInterval()} dosen't reach {@link Server#getRequests()}
	 * 
	 * @param socket The socket to protect
	 * @see Server#getInterval()
	 * @see Server#getRequests()
	 */
	public static synchronized boolean isValidContact(Socket socket) {
		Server server = (Server)Context.getInstance().getData(Server.ATT_INSTANCE);
		boolean flag = true;

		if (server.getInterval() != 0) { // not disabled

			SocketAddress socketAddress = socket.getRemoteSocketAddress();
			Long timeOld = contactTable.get(socketAddress);
			long time = System.currentTimeMillis();
			long timeDifference = time - server.getInterval();
			int counter = 0;
			
			if (timeOld != null && time - timeOld.longValue() < server.getInterval()) {
				flag = false;
			}
	
			for (Iterator<SocketAddress> iter = contactTable.keySet().iterator(); iter.hasNext() && flag;) {
			
				long element = contactTable.get(iter.next()).longValue();
							
				if (element < timeDifference) {
					counter++;
				}
				
				if (counter > server.getRequests()) {
					flag = false;
				}
			}
			
			contactTable.put(socketAddress, new Long(time));
		}
		return flag;
	}
	
	/**
	 * Checkts if a user is logged on.
	 * 
	 * @param comObject The communication Object
	 */
	public static boolean isUserLoggedOn(ComObject comObject) {
		if (((Server)Context.getInstance().getData(Server.ATT_INSTANCE)).getUser(comObject.getUserKey()) != null) {
			return true;
		}
		return false;
	}
}
