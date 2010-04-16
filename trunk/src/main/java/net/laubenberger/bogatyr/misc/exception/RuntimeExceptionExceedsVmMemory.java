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

import net.laubenberger.bogatyr.helper.HelperEnvironment;

/**
 * This runtime exception is thrown if an argument exceeds the VM memory (e.g. large Byte arrays).
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public class RuntimeExceptionExceedsVmMemory extends IllegalArgumentException {
	private static final long serialVersionUID = 1150311302870054754L;

	public RuntimeExceptionExceedsVmMemory(final String argument, final long size) {
		super(argument + " (" + size + ") exceeds the free VM memory (" + HelperEnvironment.getMemoryFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
