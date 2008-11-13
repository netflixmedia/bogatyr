/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.localizer;

import java.util.Locale;


/**
 * Defines the methods for the implementation of the localizer for different languages (i18n standard).
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public interface ILocalizer {
	/**
	 * Returns the current locale of the localizer.
	 * 
	 * @return current locale of the localizer
	 */
	Locale getLocale();
	
	/**
	 * Sets the current locale of the localizer.
	 * 
	 * @param locale of the localizer
	 */
	void setLocale(Locale locale);
	
	/**
	 * Returns the value for a key.
	 * 
	 * @return value for a key
	 */
	String getValue(String key);

	/**
	 * Returns the mnemonic for a key.
	 * This is very useful for GUI components.
	 * 
	 * @return value for a key
	 */
	int getMnemonic(String key);
	
	/**
	 * Returns the tooltip for a key.
	 * This is very useful for GUI components.
	 * 
	 * @return value for a key
	 */
	String getTooltip(String key);
}
