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
package ch.sisprocom.bogatyr.helper.exception;


/**
 * This is an exception if the stream size is invalid
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public final class ExceptionInvalidStreamSize extends ExceptionTemplate {
	private static final long serialVersionUID = 6400247262258600862L;


	/**
	 * Exception when the Stream size is invalid.
	 * 
	 * @param allowedStreamSize
	 * @param dataStreamSize
	 */
	public ExceptionInvalidStreamSize(final int allowedStreamSize, final int dataStreamSize) {
        super("allowedStreamSize: " + allowedStreamSize + " < dataStreamSize: " + dataStreamSize);  //$NON-NLS-1$ //$NON-NLS-2$
    }
    
	/**
	 * Exception when the Stream size is invalid with a individual Message.
	 * 
	 * @param msg Message as String
	 */
	public ExceptionInvalidStreamSize(final String msg) {
        super(msg);
    }
} 