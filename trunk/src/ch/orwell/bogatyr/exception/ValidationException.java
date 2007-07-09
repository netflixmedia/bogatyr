/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.exception;

import ch.orwell.bogatyr.dao.DataObject;


/**
 * This is an exception if the validation of a {@link DataObject} is failed
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class ValidationException extends Exception {
	private static final long serialVersionUID = 2262162323753693225L;

	/**
	 * Exception when the the validation of a {@link DataObject} is failed.
	 * 
	 * @param msg Message as String
	 */
	public ValidationException(final String msg) {
        super(msg);
    }
} 
