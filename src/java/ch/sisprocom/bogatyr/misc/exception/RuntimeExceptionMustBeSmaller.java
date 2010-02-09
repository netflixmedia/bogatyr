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
 * This runtime exception is thrown if an argument value is greater than a given maximum value.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100209)
 * @since 0.9.0
 */
public class RuntimeExceptionMustBeSmaller extends IllegalArgumentException {
	private static final long serialVersionUID = -8251735601253549510L;

	public RuntimeExceptionMustBeSmaller(final String argument, final Number currentValue, final Number maxValue) {
		super(argument + " (" + currentValue + ") must be smaller than " + maxValue); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
