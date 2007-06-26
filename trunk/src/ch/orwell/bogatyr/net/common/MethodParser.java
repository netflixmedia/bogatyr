package ch.orwell.bogatyr.net.common;

import ch.orwell.bogatyr.net.common.dto.ComInterface;
import ch.orwell.bogatyr.net.common.dto.ComObject;


/**
 * This is the super class for all MethodParser-children
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070619
 */
public abstract class MethodParser {	
	
	public static synchronized Object execute(ComInterface caller, ComObject comObject) throws Exception {
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
