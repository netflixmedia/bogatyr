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
package ch.sisprocom.bogatyr.controller.timer;



/**
 * Defines the methods for the implementation of the timer.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.6.0
 */
public interface TimeMachine {
	/**
	 * Adds a listener for this timer.
	 * 
	 * @param listener to add
	 * @since 0.6.0
	 */
	void addListener(ListenerTimer listener);
	
	/**
	 * Remove a listener for this timer.
	 * 
	 * @param listener to remove
	 * @since 0.6.0
	 */
	void removeListener(ListenerTimer listener);

	/**
	 * Remove all listeners for this timer.
	 * 
	 * @since 0.6.0
	 */
	void removeAllListener();
	
	
	/**
	 * Returns the running state of the timer.
     *
     * @return true/false
     */
	boolean isRunning();

	/**
	 * Returns the current interval in ms of the timer.
	 * 
	 * @return current time of the timer
	 */
    long getInterval();
//
//    /**
//     * Sets the time in ms of the timer.
//     * 
//     * @param interval
//     */
//    void setInterval(long interval);

	/**
	 * Stops immediately the timer.
	 */
	void stop();
}   

