/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.service.ServiceAbstract;

import java.util.HashMap;
import java.util.Map;


/**
 * The implementation of a profiler.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100203)
 * @since 0.9.0
 */
public class ProfilerImpl extends ServiceAbstract implements Profiler {

	private final Map<String, Long> profiles =  new HashMap<String, Long>();
	private long meanTime;
	private long elapsedTime;
	
	public ProfilerImpl() {
		super();
		
		start();
	}
	
	
	/*
	 * Implemented methods
	 */
	@Override
	public long getElapsedTime() {
		return elapsedTime;
	}

	@Override
	public Map<String, Long> getProfiles() {
		return profiles;
	}

	@Override
	public long profile(final String id) {

		final long currentTime = System.nanoTime();
		final long elapsed = currentTime - meanTime;
		profiles.put(id, elapsed);

		elapsedTime += elapsed;
		meanTime = currentTime;
		
		return elapsed;
	}

	@Override
	public void start() {
		profiles.clear();
		meanTime = System.nanoTime();
	}
}
