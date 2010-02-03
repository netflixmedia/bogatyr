/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.misc;

import java.awt.Color;



/**
 * Defines the methods for the implementation of fading.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.6.0
 */
public interface Fadeable {
	/**
     * Sets the fading state of the component.
     * 
     * @param isFading fading state
     * @since 0.6.0
     */
	void setFading(boolean isFading);
	
	/**
     * Sets the fading color.
     * 
     * @param colorFader color of the fader
     * @since 0.6.0
     */
	void setFaderColor(Color colorFader);
	
	/**
     * Returns the fading state of the component.
     * 
     * @return true/false
     * @since 0.6.0
     */
	boolean isFading();
}   

