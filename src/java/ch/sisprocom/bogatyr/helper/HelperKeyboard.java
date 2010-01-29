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
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;



/**
 * This is a helper class for keyboards.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100127)
 * @since 0.9.0
 */
public abstract class HelperKeyboard {

	public static boolean isNonPrintableKey(final int keyCode) {
		return (keyCode == 8 || // backspace
			    (keyCode >= 16 && keyCode <= 18) || // shift, ctrl, alt
			    keyCode == 20 || // Caps lock
			    keyCode == 27 || // escape
			    (keyCode >= 33 && keyCode <= 40) || // PgUp, PgDown, Home, End, Arrows
			    (keyCode >= 112 && keyCode <= 123) || // F1-F12
			    keyCode == 127 || // Delete
			    keyCode == 144 || // Num lock
			    keyCode == 145 || // Scroll lock
			    keyCode == 154 || // Print
			    keyCode == 155 || // Insert
			    keyCode == 157 || // Mac cmd
			    keyCode == 524 || // Windows cmd
			    keyCode == 525); // Windows context menu
	}
}