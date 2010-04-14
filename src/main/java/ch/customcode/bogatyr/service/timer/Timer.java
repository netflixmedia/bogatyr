/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
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

