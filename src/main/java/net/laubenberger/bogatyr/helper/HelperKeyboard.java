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

package net.laubenberger.bogatyr.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a helper class for keyboards.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101108)
 * @since 0.9.0
 */
public abstract class HelperKeyboard {
	private static final Logger log = LoggerFactory.getLogger(HelperKeyboard.class);

	/**
	 * Checks if a key code is printable.
	 *
	 * @param keyCode to check
	 * @return true/false
	 * @since 0.9.1
	 */
	public static boolean isKeyPrintable(final int keyCode) { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(keyCode));

		final boolean result = !(0 > keyCode || //keyCode couldn't be negative
				8 == keyCode || // backspace
				16 == keyCode || // shift
				17 == keyCode || // ctrl
				18 == keyCode || // alt
				20 == keyCode || // Caps lock
				27 == keyCode || // escape
				(33 <= keyCode && 40 >= keyCode) || // PgUp, PgDown, Home, End, Arrows
				(112 <= keyCode && 123 >= keyCode) || // F1-F12
				127 == keyCode || // delete
				144 == keyCode || // Num lock
				145 == keyCode || // Scroll lock
				154 == keyCode || // print
				155 == keyCode || // insert
				157 == keyCode || // Mac cmd
				524 == keyCode || // Windows cmd
				525 == keyCode); // Windows context menu

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}