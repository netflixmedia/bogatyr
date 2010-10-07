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

package net.laubenberger.bogatyr.service.timer;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

/**
 * This is a timer which informs all added listeners about its state.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101007)
 * @since 0.6.0
 */
public class TimerImpl extends TimerAbstract implements Timer {
	private static final Logger log = LoggerFactory.getLogger(TimerImpl.class);

	long time;

	public TimerImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public long getTime() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(time));
		return time;
	}

	@Override
	public void setTime(final long time) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(time));
		if (0L > time) {
			throw new RuntimeExceptionMustBeGreater("time", time, 0); //$NON-NLS-1$
		}

		this.time = time;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void start(final long interval) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(interval));
		if (0L > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}

		start(0L, interval);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void start(final long delay, final long interval) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(delay, interval));
		if (0L > delay) {
			throw new RuntimeExceptionMustBeGreater("delay", delay, 0); //$NON-NLS-1$
		}
		if (0L > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}

		if (isRunning()) {
			getTimer().cancel();
		}
		
		setTimer(new java.util.Timer());
		setInterval(interval);
		getTimer().schedule(new Task(), delay, interval);
		fireStarted();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void stop() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (isRunning()) {
			getTimer().cancel();
		}
		fireStopped();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
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
