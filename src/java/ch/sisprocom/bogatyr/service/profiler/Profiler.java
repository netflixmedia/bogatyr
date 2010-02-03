/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.profiler;

import java.util.Map;

import ch.sisprocom.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of a profiler.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091116)
 * @since 0.9.0
 */
public interface Profiler extends Service {

	/**
	 * Starts the profiler.
	 * 
	 * @since 0.9.0
	 */
	void start();
	
	/**
	 * Profile an event with its elapsed time between start() or the last profile-call and return the elapsed time in ns.
	 * 
	 * @param id of the event
	 * @return elapsed time in ns between start() or the last profile-call
	 * @since 0.9.0
	 */
	long profile(String id);
	
	/**
	 * Returns a {@link Map} containing all profiled events and their elapsed time in ms.
	 * 
	 * @return {@link Map} containing all events and their elapsed time in ns
	 * @since 0.9.0
	 */
	Map<String, Long> getProfiles();

	/**
	 * Returns the elapsed time in ns for all profiled events.
	 * 
	 * @return elapsed time in ms for all profiled events
	 * @since 0.9.0
	 */
	long getElapsedTime();
}
