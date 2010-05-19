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

package net.laubenberger.bogatyr.view.swing.worker;

import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.Listener;

/**
 * This listener informs the listeners about the current work state.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100520)
 * @since 0.9.0
 */
public interface ListenerWorker extends Listener {

	/**
	 * Informs the listener that a worker had been started.
	 *
	 * @param event for the listener
	 * @since 0.9.0
	 */
	void start(Event<Worker> event);

	/**
	 * Informs the listener that a worker had been stopped.
	 *
	 * @param event for the listener
	 * @since 0.9.0
	 */
	void done(Event<Worker> event);
}