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
package ch.customcode.bogatyr.service.timer;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.6.0
 */
public class TimerImpl extends TimerAbstract implements Timer {
	private static final Logger log = LoggerFactory.getLogger(TimerImpl.class);
	
	long time;
	
	@Override
    public long getTime() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(time));
		return time;
	}

	@Override
    public void setTime(final long time) {
		log.debug(HelperLog.methodStart(time));
		if (0 > time) {
			throw new RuntimeExceptionMustBeGreater("time", time, 0); //$NON-NLS-1$
		}

		this.time = time;

		log.debug(HelperLog.methodExit());
	}
	
	
	/*
	 * Implemented methods
	 */
    @Override
    public synchronized void start(final long interval) {
		log.debug(HelperLog.methodStart(interval));
		if (0 > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}

		start(0L, interval);

		log.debug(HelperLog.methodExit());
    }

    @Override
    public synchronized void start(final long delay, final long interval) {
		log.debug(HelperLog.methodStart(delay, interval));
		if (0 > delay) {
			throw new RuntimeExceptionMustBeGreater("delay", delay, 0); //$NON-NLS-1$
		}
		if (0 > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}

    	getTimer().cancel();

    	setTimer(new java.util.Timer());
        setInterval(interval);
        getTimer().schedule(new Task(), delay, interval);
        fireTimerStarted();

        log.debug(HelperLog.methodExit());
    }

    @Override
    public synchronized void stop() {
		log.debug(HelperLog.methodStart());
		
		getTimer().cancel();
        fireTimerStopped();

        log.debug(HelperLog.methodExit());
    }
	
	
	/*
	 * Inner classes
	 */
	class Task extends TimerTask {
	    @Override
		public void run() {
	    	time += getInterval();
    		fireTimeChanged();
	    }
	}
}
