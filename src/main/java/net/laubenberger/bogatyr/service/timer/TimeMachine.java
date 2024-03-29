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

package net.laubenberger.bogatyr.service.timer;

import net.laubenberger.bogatyr.misc.HolderListener;
import net.laubenberger.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of the timer.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101007)
 * @since 0.6.0
 */
public interface TimeMachine extends Service, HolderListener<ListenerTimer> {
	/**
	 * Returns the running state of the timer.
	 *
	 * @return true/false
	 * @since 0.6.0
	 */
	boolean isRunning();

	/**
	 * Returns the current interval in ms of the timer.
	 *
	 * @return current time of the timer
	 * @since 0.6.0
	 */
	long getInterval();

	/**
	 * Stops immediately the timer.
	 * 
	 * @since 0.6.0
	 */
	void stop();

	/**
	 * Returns the current time in ms of the timer.
	 *
	 * @return current time of the timer
	 * @since 0.6.0
	 */
	long getTime();

	/**
	 * Sets the time in ms of the timer.
	 *
	 * @param time
	 * @since 0.6.0
	 */
	void setTime(long time);
}   

