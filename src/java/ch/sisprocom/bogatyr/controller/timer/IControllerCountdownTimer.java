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
 * Defines the methods for the implementation of the countdown timer.
 * 
 * @author Stefan Laubenberger
 * @version 20090522
 */
public interface IControllerCountdownTimer extends IListener {
	/**
	 * Starts immediately the countdown with a given runtime and standard interval of 1000ms.
	 * 
	 * @param runtime of the countdown
	 */
	void start(final long runtime);

	/**
	 * Start the countdown with a given delay, runtime and interval.
	 * 
	 * @param delay until the timer starts
	 * @param runtime of the countdown
	 * @param interval of the countown
	 */
	void start(final long delay, final long runtime, final long interval);
    
	/**
	 * Returns the current runtime in ms of the timer.
	 * 
	 * @return current time of the timer
	 */
    long getRuntime();
    
    /**
     * Sets the runtime in ms of the timer.
     * 
     * @param time
     */
    void setRuntime(final long time);

}   
