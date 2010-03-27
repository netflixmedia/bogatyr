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
package ch.customcode.bogatyr.misc;




/**
 * Defines the methods for the implementation of activations.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public interface Activatable {
	/**
     * Sets the activation state of the component.
     * 
     * @param isActive activation state
     * @since 0.7.0
     */
	void setActive(boolean isActive);
	
	/**
     * Returns the activation state of the component.
     * 
     * @return true/false
     * @since 0.7.0
     */
	boolean isActive();
}   

