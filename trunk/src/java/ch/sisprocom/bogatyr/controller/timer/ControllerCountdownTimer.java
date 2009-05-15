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
package ch.sisprocom.bogatyr.controller.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This is a countdown timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public class ControllerCountdownTimer extends ControllerTimerAbstract implements IControllerCountdownTimer{
	long runtime;

    /*
      * Implemented methods
      */
    public synchronized void start(final long runtime) {
        start(0L, runtime, 1000L);
    }

    public synchronized void start(final long delay, final long runtime, final long interval) {
        getTimer().cancel();

        setTimer(new Timer());
        this.runtime = runtime;
        setInterval(interval);

        getTimer().schedule(new TaskCountdown(), delay, interval);
        fireTimerStarted();
    }

    public synchronized void stop() {
    	getTimer().cancel();
        fireTimerStopped();
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
