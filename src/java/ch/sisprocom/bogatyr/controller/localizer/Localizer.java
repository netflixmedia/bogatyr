/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.localizer;

import javax.swing.KeyStroke;
import java.util.Locale;


/**
 * Defines the methods for the implementation of the localizer for different languages (i18n standard).
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.6.0
 */
public interface Localizer {
	/**
	 * Returns the current locale of the localizer.
	 * 
	 * @return current locale of the localizer
	 * @since 0.6.0
	 */
	Locale getLocale();
	
	/**
	 * Sets the current locale of the localizer.
	 * 
	 * @param locale of the localizer
	 * @since 0.6.0
	 */
	void setLocale(Locale locale);
	
	/**
	 * Returns the value for a key.
	 *
     * @param key for the value
	 * @return value for the key
	 * @since 0.6.0
	 */
	String getValue(String key);

	/**
	 * Returns the accelerator ({@link KeyStroke}) for a key.
	 *
     * @param key for the accelerator
     * @return accelerator for the key
     * @since 0.6.0
	 */
	KeyStroke getAccelerator(String key);
	
	/**
	 * Returns the mnemonic for a key.
	 *
     * @param key for the mnemonic
	 * @return mnemonic for the key
	 * @since 0.6.0
	 */
	int getMnemonic(String key);
	
	/**
	 * Returns the tooltip for a key.
	 *
     * @param key for the tooltip
	 * @return tooltip for the key
	 * @since 0.6.0
	 */
	String getTooltip(String key);
	
	/**
	 * Adds a listener for this locale.
	 * 
	 * @param listener to add
	 * @since 0.6.0
	 */
	void addListener(ListenerLocale listener);
	
	/**
	 * Remove a listener for this locale.
	 * 
	 * @param listener to remove
	 * @since 0.6.0
	 */
	void removeListener(ListenerLocale listener);

	/**
	 * Remove all listeners for this locale.
	 * @since 0.6.0 
	 */
	void removeAllListener();
}
