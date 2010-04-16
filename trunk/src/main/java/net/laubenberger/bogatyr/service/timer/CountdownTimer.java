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
package net.laubenberger.bogatyr.service.timer;


/**
 * Defines the methods for the implementation of the countdown timer.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.6.0
 */
public interface CountdownTimer extends TimeMachine {
	/**
	 * Starts immediately the countdown with a given runtime and standard interval of 1000ms.
	 *
	 * @param runtime of the countdown
	 * @since 0.6.0
	 */
	void start(long runtime);

	/**
	 * Start the countdown with a given delay, runtime and interval.
	 *
	 * @param delay	 until the timer starts
	 * @param runtime  of the countdown
	 * @param interval of the countdown
	 * @since 0.6.0
	 */
	void start(long delay, long runtime, long interval);
}

