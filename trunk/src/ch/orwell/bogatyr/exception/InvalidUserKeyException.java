/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.exception;

import ch.orwell.bogatyr.Context;


/**
 * This is an exception if the user-key is invalid
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public final class InvalidUserKeyException extends Exception {
	private static final long serialVersionUID = -5681289983282835248L;

	// Resources
	private final static String	RES_TEXT = "InvalidUserKeyException.text"; //$NON-NLS-1$

	/**
	 * Exception when the user key is invalid.
	 * 
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
