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
 * Defines the methods for the listener holder (observable).
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.9.0
 */
public interface HolderListener<L extends Listener> {
	/**
	 * Add a listener.
	 * 
	 * @param listener to add
	 * @since 0.9.0
	 */
	void addListener(L listener);
	
	/**
	 * Remove and delete a listener.
	 * <strong>Note:</strong> This method could be type-safe via covariance in the implementation
	 * 
	 * @param listener to remove
	 * @since 0.9.0
	 */
	void deleteListener(L listener);
//	<T extends Listener> void deleteListener(T listener);

	/**
	 * Removes and deletes all registered listeners.
	 * 
	 * @since 0.9.0
	 */
	void deleteListeners();
	
	/**
	 * Counts all registered listeners.
	 * 
	 * @since 0.9.0
	 */	
	int countListeners();
}   