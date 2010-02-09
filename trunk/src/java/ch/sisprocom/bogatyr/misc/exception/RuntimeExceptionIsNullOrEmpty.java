/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.misc.exception;

/**
 * This runtime exception is thrown if an argument is null or empty.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100203)
 * @since 0.9.0
 */
public class RuntimeExceptionIsNullOrEmpty extends IllegalArgumentException {
	private static final long serialVersionUID = 6974645627720904297L;

	public RuntimeExceptionIsNullOrEmpty(final String argument) {
		super(argument + " is null or empty!"); //$NON-NLS-1$
	}
}
