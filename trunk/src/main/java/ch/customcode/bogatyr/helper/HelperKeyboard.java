/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;



/**
 * This is a helper class for keyboards.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.0
 */
public abstract class HelperKeyboard {

	public static boolean isNonPrintableKey(final int keyCode) {
		return (0 > keyCode || //keyCode couldn't be negative
				8 == keyCode || // backspace
			    (16 <= keyCode && 18 >= keyCode) || // shift, ctrl, alt
                20 == keyCode || // Caps lock
                27 == keyCode || // escape
			    (33 <= keyCode && 40 >= keyCode) || // PgUp, PgDown, Home, End, Arrows
			    (112 <= keyCode && 123 >= keyCode) || // F1-F12
                127 == keyCode || // Delete
                144 == keyCode || // Num lock
                145 == keyCode || // Scroll lock
                154 == keyCode || // Print
                155 == keyCode || // Insert
                157 == keyCode || // Mac cmd
                524 == keyCode || // Windows cmd
                525 == keyCode); // Windows context menu
	}
}