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

package net.laubenberger.bogatyr.misc;


/**
 * Defines the methods for the implementation of activations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
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

