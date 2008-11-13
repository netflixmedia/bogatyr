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

import ch.sisprocom.bogatyr.helper.localizer.Localizer;


/**
 * This is an exception if the user-key is invalid.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public final class ExceptionInvalidUserKey extends ExceptionAbstract {
	private static final long serialVersionUID = -5681289983282835248L;

	// Resources
    private static final String	RES_TEXT = "ExceptionInvalidUserKey.text"; //$NON-NLS-1$

	/**
	 * Exception when the user key is invalid.
	 */
	public ExceptionInvalidUserKey() {
        super(Localizer.getInstance().getValue(RES_TEXT));
    }

	/**
	 * Exception when the user key is invalid with a individual message.
	 * 
	 * @param msg Message as String
	 */
	public ExceptionInvalidUserKey(final String msg) {
        super(msg);
    }
} 
