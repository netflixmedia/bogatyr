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

import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.service.ServiceAbstract;

/**
 * This is a timer which informs all added listeners about its state.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.6.0
 */
public abstract class TimerAbstract extends ServiceAbstract implements TimeMachine {
	private static final Logger log = LoggerFactory.getLogger(TimerAbstract.class);

	private Collection<ListenerTimer> listListener = new HashSet<ListenerTimer>();

	private final Event<TimeMachine> event = new Event<TimeMachine>(this);

	private Timer timer = new Timer();
	private long interval;

	private boolean isRunning;


//	protected TimerAbstract() {
//        super();
//    }

	/**
	 * Returns the current {@link Timer}.
	 *
	 * @return current timer
	 * @see Timer
	 * @since 0.6.0
	 */
	public Timer getTimer() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(timer));
		return timer;
	}

	/**
	 * Sets the current {@link Timer}.
	 *
	 * @param timer for the implementation
	 * @see Timer
	 * @since 0.6.0
	 */
	public void setTimer(final Timer timer) {
		log.debug(HelperLog.methodStart(timer));
		if (null == timer) {
			throw new RuntimeExceptionIsNull("timer"); //$NON-NLS-1$
		}

		this.timer = timer;

		log.debug(HelperLog.methodExit());
	}


	/*
		 * Private methods
		 */

	protected void fireTimeChanged() {
		log.trace(HelperLog.methodStart());

		for (final ListenerTimer listener : listListener) {
			listener.timeChanged(event);
		}

		log.trace(HelperLog.methodExit());
	}

	protected void fireTimerStarted() {
		log.trace(HelperLog.methodStart());

		isRunning = true;

		for (final ListenerTimer listener : listListener) {
			listener.timerStarted(event);
		}

		log.trace(HelperLog.methodExit());
	}

	protected void fireTimerStopped() {
		log.trace(HelperLog.methodStart());

		isRunning = false;

		for (final ListenerTimer listener : listListener) {
			listener.timerStopped(event);
		}

		log.trace(HelperLog.methodExit());
	}

	protected void setInterval(final long interval) {
		log.trace(HelperLog.methodStart(interval));

		this.interval = interval;

		log.trace(HelperLog.methodExit());
	}


	/*
		  * Implemented methods
		  */

	@Override
	public boolean isRunning() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(isRunning));
		return isRunning;
	}

	@Override
	public long getInterval() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(interval));
		return interval;
	}

	@Override
	public synchronized void addListener(final ListenerTimer listener) {
		log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.add(listener);

		log.debug(HelperLog.methodExit());
	}

	@Override
	public synchronized void deleteListener(final ListenerTimer listener) {
		log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.remove(listener);

		log.debug(HelperLog.methodExit());
	}

	@Override
	public synchronized void deleteListeners() {
		log.debug(HelperLog.methodStart());

		listListener = new HashSet<ListenerTimer>();

		log.debug(HelperLog.methodExit());
	}

	@Override
	public int countListeners() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(listListener.size()));
		return listListener.size();
	}
}
