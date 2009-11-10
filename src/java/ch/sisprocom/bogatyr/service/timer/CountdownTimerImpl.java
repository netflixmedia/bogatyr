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
 * Badenerstrasse 47 
 * CH-8004 Zuerich
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

/**
 * This is a countdown timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091027)
 * @since 0.6.0
 */
public class CountdownTimerImpl extends TimerAbstract implements CountdownTimer {
	long runtime;

	
	/*
     * Implemented methods
     */
    @Override
    public synchronized void start(final long runtime) {
        start(0L, runtime, 1000L);
    }

    @Override
    public synchronized void start(final long delay, final long runtime, final long interval) {
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
    public long getRuntime() {
		return runtime;
	}

	@Override
    public synchronized void setRuntime(final long runtime) {
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
				fireTimeChanged(runtime);
			} else {
				fireTimerStopped();
				getTimer().cancel();
			}
		}
	}
}