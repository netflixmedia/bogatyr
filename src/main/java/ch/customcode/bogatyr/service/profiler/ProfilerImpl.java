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
package ch.customcode.bogatyr.service.profiler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * The implementation of a profiler.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.9.0
 */
public class ProfilerImpl<T> extends ServiceAbstract implements Profiler<T> {
	private static final Logger log = LoggerFactory.getLogger(ProfilerImpl.class);
	
	private final Map<T, Long> profiles =  new ConcurrentHashMap<T, Long>();
	private long meanTime;
	private long elapsedTime;
	
	public ProfilerImpl() {
		super();
		log.trace(HelperLog.constructor());
		
		start();
	}
	
	
	/*
	 * Implemented methods
	 */
	@Override
	public long getElapsedTime() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(elapsedTime));
		return elapsedTime;
	}

	@Override
	public Map<T, Long> getProfiles() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(profiles));
		return profiles;
	}

	@Override
	public long profile(final T event) {
		log.debug(HelperLog.methodStart(event));
		
		final long currentTime = System.nanoTime();
		final long result = currentTime - meanTime;
		profiles.put(event, result);

		elapsedTime += result;
		meanTime = currentTime;
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public void start() {
		log.debug(HelperLog.methodStart());
		
		profiles.clear();
		meanTime = System.nanoTime();

		log.debug(HelperLog.methodExit());
	}
}
