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
package ch.sisprocom.bogatyr.view.swing;

import java.awt.Color;



/**
 * Defines the methods for the implementation of fading.
 * 
 * @author Stefan Laubenberger
 * @version 20090421
 */
public interface IComponentFader {
	/**
     * Sets the fading state of the component.
     * 
     * @param isEnabled anti-aliasing state
     */
	void setFading(final boolean isFading);
	
	/**
     * Sets the fading color.
     * 
     * @param colorFader color of the fader
     */
	void setFaderColor(final Color colorFader);
	
	/**
     * Returns the fading state of the component.
     * 
     * @return true/false
     */
	boolean isFading();
}   

