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
package ch.orwell.bogatyr.helper.exception;



/**
 * This is an exception if the stream size is invalid
 * 
 * @author Stefan Laubenberger
 * @version 20080515
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