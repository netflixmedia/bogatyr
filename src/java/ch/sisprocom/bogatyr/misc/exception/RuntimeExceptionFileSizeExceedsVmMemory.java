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

import java.io.File;

import ch.sisprocom.bogatyr.helper.HelperEnvironment;

/**
 * This runtime exception is thrown if an {@link File} exceeds the VM memory (e.g. read in a Byte array).
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100203)
 * @since 0.9.0
 */
public class RuntimeExceptionFileSizeExceedsVmMemory extends IllegalArgumentException {
	private static final long serialVersionUID = -2283187622612876223L;

	public RuntimeExceptionFileSizeExceedsVmMemory(final File file) {
		super(file.getName() + " (" + file.length() + ") exceeds the free VM memory (" + HelperEnvironment.getMemoryFree() + ')');  //$NON-NLS-1$//$NON-NLS-2$
	}
}
