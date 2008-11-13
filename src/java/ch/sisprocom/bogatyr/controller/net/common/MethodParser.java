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
 * This is the super class for all MethodParser-children.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class MethodParser {
    /**
     * Execute a method in the server thread or client.
     *
     * @param caller server thread or the client
     * @param comObject communication object between the server thread an the client
     * @throws Exception
     * @return result object
     */
    public static synchronized Object execute(final ICom caller, final ComObject comObject) throws Exception {
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
    	return null;
    }
}
