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

package ch.customcode.bogatyr.service.profiler;

import java.util.Map;

import ch.customcode.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of a profiler.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.9.0
 */
public interface Profiler<T> extends Service {

	/**
	 * Starts the profiler.
	 * 
	 * @since 0.9.0
	 */
	void start();
	
	/**
	 * Profile an event with its elapsed time between start() or the last profile-call and return the elapsed time in ns.
	 * 
	 * @param event to profile
	 * @return elapsed time in ns between start() or the last profile-call
	 * @since 0.9.0
	 */
	long profile(T event);
	
	/**
	 * Returns a {@link Map} containing all profiled events and their elapsed time in ms.
	 * 
	 * @return {@link Map} containing all events and their elapsed time in ns
	 * @since 0.9.0
	 */
	Map<T, Long> getProfiles();

	/**
	 * Returns the elapsed time in ns for all profiled events.
	 * 
	 * @return elapsed time in ms for all profiled events
	 * @since 0.9.0
	 */
	long getElapsedTime();
}
