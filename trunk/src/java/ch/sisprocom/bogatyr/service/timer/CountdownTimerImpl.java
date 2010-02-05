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
package ch.sisprocom.bogatyr.service.timer;

import java.util.Timer;
import java.util.TimerTask;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentMustBePositive;

/**
 * This is a countdown timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100205)
 * @since 0.6.0
 */
public class CountdownTimerImpl extends TimerAbstract implements CountdownTimer {
	long runtime;

	
	/*
     * Implemented methods
     */
    @Override
    public synchronized void start(final long runtime) {
		if (0 > runtime) {
			throw new RuntimeExceptionArgumentMustBePositive("runtime", runtime); //$NON-NLS-1$
		}

		start(0L, runtime, 1000L);
    }

    @Override
    public synchronized void start(final long delay, final long runtime, final long interval) {
		if (0 > delay) {
			throw new RuntimeExceptionArgumentMustBePositive("delay", delay); //$NON-NLS-1$
		}
		if (0 > runtime) {
			throw new RuntimeExceptionArgumentMustBePositive("runtime", runtime); //$NON-NLS-1$
		}
		if (0 > interval) {
			throw new RuntimeExceptionArgumentMustBePositive("interval", interval); //$NON-NLS-1$
		}

    	getTimer().cancel();

        setTimer(new Timer());
        this.runtime = runtime;
        setInterval(interval);

        getTimer().schedule(new TaskCountdown(), delay, interval);
        fireTimerStarted();
    }

    @Override
    public synchronized void stop() {
    	getTimer().cancel();
        fireTimerStopped();
    }

    @Override
    public long getTime() {
		return runtime;
	}

	@Override
    public synchronized void setTime(final long runtime) {
		if (0 > runtime) {
			throw new IllegalArgumentException("runtime must be positive: " + runtime); //$NON-NLS-1$
		}

		this.runtime = runtime;
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
