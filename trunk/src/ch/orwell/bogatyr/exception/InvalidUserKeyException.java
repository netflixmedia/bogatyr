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


/**
 * This is an exception if the user-key is invalid
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class InvalidUserKeyException extends Exception {
	private static final long serialVersionUID = -5681289983282835248L;

	// Resources
	private final static String	RES_TEXT = "InvalidUserKeyException.text"; //$NON-NLS-1$

	/**
	 * Exception when the user key is invalid.
	 */
	public InvalidUserKeyException() {
        super(Context.getInstance().getLocalizer().getValue(RES_TEXT));
    }

	/**
	 * Exception when the user key is invalid with a individual Message.
	 * 
	 * @param msg Message as String
	 */
	public InvalidUserKeyException(final String msg) {
        super(msg);
    }
} 
