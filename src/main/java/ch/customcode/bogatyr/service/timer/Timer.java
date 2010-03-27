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
package ch.customcode.bogatyr.service.timer;



/**
 * Defines the methods for the implementation of the timer.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.6.0
 */
public interface Timer extends TimeMachine {
	/**
	 * Starts immediately the timer with a given interval.
	 * 
	 * @param interval of the timer
	 * @since 0.6.0
	 */
	void start(long interval);

	/**
	 * Starts the timer with a given delay and interval.
	 * 
	 * @param delay until the timer starts
	 * @param interval of the timer
	 * @since 0.6.0
	 */
	void start(long delay, long interval);
	
//	/**
//	 * Returns the current time in ms of the timer.
//	 * 
//	 * @return current time of the timer
//	 * @since 0.6.0
//	 */
//    long getTime();
//    
//    /**
//     * Sets the time in ms of the timer.
//     * 
//     * @param time
//     * @since 0.6.0
//     */
//    void setTime(long time);
}   

