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

import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.Listener;

/**
 * ListenerTimer
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100520)
 * @since 0.6.0
 */
public interface ListenerTimer extends Listener {
	/**
	 * Sends the actual time of the timer/countdown to the listener.
	 *
	 * @param event for the listener
	 * @since 0.6.0
	 */
	void timeChanged(Event<TimeMachine> event);

	/**
	 * Informs the listener that the timer/countdown has started.
	 *
	 * @param event for the listener
	 * @since 0.6.0
	 */
	void timerStarted(Event<TimeMachine> event);

	/**
	 * Informs the listener that the timer/countdown has stopped.
	 *
	 * @param event for the listener
	 * @since 0.6.0
	 */
	void timerStopped(Event<TimeMachine> event);
}
