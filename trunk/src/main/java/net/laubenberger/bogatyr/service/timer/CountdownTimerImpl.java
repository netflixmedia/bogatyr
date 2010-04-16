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

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

/**
 * This is a countdown timer which informs all added listeners about its state.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.6.0
 */
public class CountdownTimerImpl extends TimerAbstract implements CountdownTimer {
	private static final Logger log = LoggerFactory.getLogger(CountdownTimerImpl.class);

	private static final long DEFAULT_DELAY = 1000L;

	long runtime;


	public CountdownTimerImpl() {
		super();
		log.trace(HelperLog.constructor());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public synchronized void start(final long runtime) {
		log.debug(HelperLog.methodStart(runtime));
		if (0L > runtime) {
			throw new RuntimeExceptionMustBeGreater("runtime", runtime, 0); //$NON-NLS-1$
		}

		start(0L, runtime, DEFAULT_DELAY);

		log.debug(HelperLog.methodExit());
	}

	@Override
	public synchronized void start(final long delay, final long runtime, final long interval) {
		log.debug(HelperLog.methodStart(delay, runtime, interval));
		if (0L > delay) {
			throw new RuntimeExceptionMustBeGreater("delay", delay, 0); //$NON-NLS-1$
		}
		if (0L > runtime) {
			throw new RuntimeExceptionMustBeGreater("runtime", runtime, 0); //$NON-NLS-1$
		}
		if (0L > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}

		getTimer().cancel();

		setTimer(new Timer());
		this.runtime = runtime;
		setInterval(interval);

		getTimer().schedule(new TaskCountdown(), delay, interval);
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

	@Override
	public long getTime() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(runtime));
		return runtime;
	}

	@Override
	public synchronized void setTime(final long runtime) {
		log.debug(HelperLog.methodStart(runtime));
		if (0L > runtime) {
			throw new RuntimeExceptionMustBeGreater("runtime", runtime, 0); //$NON-NLS-1$
		}

		this.runtime = runtime;

		log.debug(HelperLog.methodExit());
	}


	/*
	 * Inner classes
	 */

	class TaskCountdown extends TimerTask {
		@Override
		public void run() {
			if (0L < runtime) {
				runtime -= getInterval();
				fireTimeChanged();
			} else {
				fireTimerStopped();
				getTimer().cancel();
			}
		}
	}
}
