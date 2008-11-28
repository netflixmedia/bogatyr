/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.timer;

import java.util.TimerTask;

/**
 * CountdownTimer
 * 
 * @author Stefan Laubenberger
 * @version 20081126
 */
public class CountdownTimer extends Timer { //TODO document in Wiki!
	protected long runtime;
	

	public CountdownTimer(){
		super();
	}
	
	/**
	 * Starts immediately the countdown with a given runtime and standard interval of 1000ms.
	 * 
	 * @param runtime of the countdown
	 */
	@Override
	public void start(final long runtime) {
		this.start(0, runtime, 1000);
	}

	/**
	 * Start the countdown with a given delay, runtime and interval.
	 * 
	 * @param delay
	 * @param runtime
	 * @param interval
	 */
	public void start(final long delay, final long runtime, final long interval) {
    	timer.cancel();
    	
    	timer = new java.util.Timer();
    	this.runtime = runtime;
    	this.interval = interval;
    	
        timer.schedule(new TaskCountdown(), delay, interval);
        fireTimerStarted();
    }
	
	
	/*
	 * Inner classes
	 */
	protected class TaskCountdown extends TimerTask {
		@Override
		public void run() {
			if (runtime > 0) {
				runtime -= interval;
				fireTimeChanged(runtime);
			} else {
				fireTimerStopped();
				timer.cancel();
			}
		}
	}
}
