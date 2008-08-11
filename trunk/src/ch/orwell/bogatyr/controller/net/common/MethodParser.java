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
package ch.orwell.bogatyr.controller.net.common;

import ch.orwell.bogatyr.controller.net.common.dto.ComObject;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is the super class for all MethodParser-children
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public abstract class MethodParser {
    /**
     * Execute a method in the server thread or client.
     *
     * @param caller The server thread or the client
     * @param comObject Communication object between the server thread an the client
     * @throws Exception
     * @return result object
     */
    public static synchronized Object execute(final ICom caller, final ComObject comObject) throws Exception {
		Logger.getInstance().writeMethodEntry(MethodParser.class, "execute", new Object[]{caller, comObject}); //$NON-NLS-1$

    	if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_CONNECT)) {
            caller.connect(comObject);
        } else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_DISCONNECT)) {
            caller.disconnect(comObject);
        } else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_SEND_ASYMMKEY)) {
            caller.sendAsymmKey(comObject);
        } else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_SEND_SYMMKEY)) {
            caller.sendSymmKey(comObject);
        } else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_LOGON)) {
            caller.logon(comObject);
        } else if (comObject.getMethodName().equalsIgnoreCase(ICom.METHOD_LOGOFF)) {
            caller.logoff(comObject);
        }

		Logger.getInstance().writeMethodExit(MethodParser.class, "execute", null); //$NON-NLS-1$
    	return null;
    }
}