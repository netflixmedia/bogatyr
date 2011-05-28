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

package net.laubenberger.bogatyr.service.monitor;

import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.Listener;

/**
 * ListenerFileChanged
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101007)
 * @since 0.9.4
 */
public interface ListenerFileChanged extends Listener {
	/**
	 * Informs the listener that the monitor has started.
	 *
	 * @param event for the listener
	 * @since 0.9.4
	 */
	void monitorStarted(Event<MonitorFile> event);

	/**
	 * Informs the listener that the monitor has stopped.
	 *
	 * @param event for the listener
	 * @since 0.9.4
	 */
	void monitorStopped(Event<MonitorFile> event);
	
	/**
	 * Informs the listener that the file has changed.
	 *
	 * @param event for the listener
	 * @since 0.9.4
	 */
	void fileChanged(Event<MonitorFile> event);

	/**
	 * Informs the listener that the file has not been found.
	 *
	 * @param event for the listener
	 * @since 0.9.4
	 */
	void fileNotFound(Event<MonitorFile> event);
}
