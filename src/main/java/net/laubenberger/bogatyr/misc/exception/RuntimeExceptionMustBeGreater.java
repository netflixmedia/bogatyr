/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.misc.exception;

/**
 * This runtime exception is thrown if an argument value is smaller than a given minimum value.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public class RuntimeExceptionMustBeGreater extends IllegalArgumentException {
	private static final long serialVersionUID = -8251735601253549510L;

	public RuntimeExceptionMustBeGreater(final String argument, final Number currentValue, final Number minValue) {
		super(argument + " (" + currentValue + ") must be greater than " + minValue); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
