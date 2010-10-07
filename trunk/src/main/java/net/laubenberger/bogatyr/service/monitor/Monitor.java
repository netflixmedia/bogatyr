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

package net.laubenberger.bogatyr.service.monitor;

import net.laubenberger.bogatyr.misc.HolderListener;
import net.laubenberger.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of a monitor.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101007)
 * @since 0.9.4
 */
public interface Monitor extends Service, HolderListener<ListenerFileChanged> {
	/**
	 * Starts immediately the monitor with a given interval.
	 *
	 * @param interval of the monitor
	 * @since 0.9.4
	 */
	void start(long interval);
	
	/**
	 * Stops immediately the monitor.
	 *
	 * @since 0.9.4
	 */
	void stop();
}
