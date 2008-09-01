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
package ch.sisprocom.bogatyr.helper.exception;

import ch.sisprocom.bogatyr.model.dao.DataObject;


/**
 * This is an exception if the validation of a {@link DataObject} is failed
 * 
 * @author Stefan Laubenberger
 * @version 20080515
 */
public final class ExceptionValidation extends ExceptionTemplate {
	private static final long serialVersionUID = 2262162323753693225L;

	/**
	 * Exception when the the validation of a {@link DataObject} is failed.
	 * 
	 * @param msg Message as String
	 */
	public ExceptionValidation(final String msg) {
        super(msg);
    }
} 
