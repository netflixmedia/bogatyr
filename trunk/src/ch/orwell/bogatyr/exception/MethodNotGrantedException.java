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
package ch.orwell.bogatyr.exception;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.net.common.dto.ComObject;

/**
 * This is an exception if the method is not granted
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
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
