package ch.orwell.bogatyr.exception;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.net.common.dto.ComObject;

/**
 * This is an exception if the method is not granted
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070625
 */
public final class MethodNotGrantedException extends Exception {
	private static final long serialVersionUID = -5511296760913333465L;

	// Resources
	private final static String	RES_TEXT = "MethodNotGrantedException.text"; //$NON-NLS-1$

	/**
	 * Exception when the Method in the comObject is not granted.
	 * 
	 * @param comObject Communication Object
	 */ 
	public MethodNotGrantedException(ComObject comObject) {
        super(comObject.getMethodName() + " " + Context.getInstance().getLocalizer().getValue(RES_TEXT)); //$NON-NLS-1$
    }

	/**
	 * Exception when the Mothod is not granted with a individual Message.
	 * 
	 * @param msg Message as String
	 */
	public MethodNotGrantedException(final String msg) {
        super(msg);
    }
} 
