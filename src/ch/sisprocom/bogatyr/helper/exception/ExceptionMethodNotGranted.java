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

import ch.sisprocom.bogatyr.helper.localizer.Localizer;

/**
 * This is an exception if the method is not granted
 * 
 * @author Stefan Laubenberger
 * @version 20080515
 */
public final class ExceptionMethodNotGranted extends ExceptionTemplate {
	private static final long serialVersionUID = -5511296760913333465L;

	// Resources
    private static final String	RES_TEXT = "ExceptionMethodNotGranted.text"; //$NON-NLS-1$

//	/**
//	 * Exception when the method in the comObject is not granted.
//	 * 
//	 * @param comObject Communication Object
//	 */ 
//	public ExceptionMethodNotGranted(ComObject comObject) {
//        super(comObject.getMethodName() + " " + Localizer.getInstance().getValue(RES_TEXT)); //$NON-NLS-1$
//    }

	/**
	 * Exception when the method in the comObject is not granted.
	 * 
	 * @param methodName Method name
	 */ 
	public ExceptionMethodNotGranted(final String methodName) {
        super(methodName + ' ' + Localizer.getInstance().getValue(RES_TEXT));
    }

//	/**
//	 * Exception when the method is not granted with a individual message.
//	 * 
//	 * @param msg Message as String
//	 */
//	public ExceptionMethodNotGranted(final String msg) {
//        super(msg);
//    }
} 
