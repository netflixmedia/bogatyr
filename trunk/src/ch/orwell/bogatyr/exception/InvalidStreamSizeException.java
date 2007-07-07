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
 * This is an exception if the stream size is invalid
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public final class InvalidStreamSizeException extends Exception {
	private static final long serialVersionUID = 6400247262258600862L;

	// Resources
	private final static String	RES_TEXT = "InvalidStreamSizeException.text"; //$NON-NLS-1$

	/**
	 * Exception when the Stream size is invalid.
	 * 
	 * @param allowedStreamSize
	 * @param dataStreamSize
	 */
	public InvalidStreamSizeException(int allowedStreamSize, int dataStreamSize) {
        super("ComContainer " + Context.getInstance().getLocalizer().getValue(RES_TEXT) + " - allowedStreamSize: " + allowedStreamSize + " dataStreamSize: " + dataStreamSize);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    
	/**
	 * Exception when the Stream size is invalid with a individual Message.
	 * 
	 * @param msg
	 *            Message as String
	 */
	public InvalidStreamSizeException(final String msg) {
        super(msg);
    }
} 
