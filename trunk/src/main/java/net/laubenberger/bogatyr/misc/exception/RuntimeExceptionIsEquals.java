/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package net.laubenberger.bogatyr.misc.exception;

/**
 * This runtime exception is thrown if an input argument is equals to an output argument.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public class RuntimeExceptionIsEquals extends IllegalArgumentException {
	private static final long serialVersionUID = -3055488683655986508L;

	public RuntimeExceptionIsEquals(final String input, final String output) {
		super(input + " is equals to " + output); //$NON-NLS-1$
	}
}
