/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
 * ListenerTimer
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.6.0
 */
public interface ListenerTimer {
	/**
	 * Sends the actual time of the timer/countdown to the listener.
     *
     * @param time actual time of the timer/countdown
     * @since 0.6.0
	 */
	void timeChanged(long time);
	
	/**
	 * Informs the listener that the timer/countdown has started.
	 * 
	 * @since 0.6.0
	 */
	void timerStarted();
	
	/**
	 * Informs the listener that the timer/countdown has stopped.
	 * 
	 * @since 0.6.0
	 */
	void timerStopped();
}
