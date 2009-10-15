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

import java.util.TimerTask;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.6.0
 */
public class TimerImpl extends TimerAbstract implements Timer {
	long time;
	
	public long getTime() {
		return time;
	}

	public void setTime(final long time) {
		this.time = time;
	}
	
	
	/*
	 * Implemented methods
	 */
    public synchronized void start(final long interval) {
        start(0L, interval);
    }

    public synchronized void start(final long delay, final long interval) {
    	getTimer().cancel();

    	setTimer(new java.util.Timer());
        setInterval(interval);
        getTimer().schedule(new Task(), delay, interval);
        fireTimerStarted();
    }

    public synchronized void stop() {
    	getTimer().cancel();
        fireTimerStopped();
    }
	
	
	/*
	 * Inner classes
	 */
	class Task extends TimerTask {
	    @Override
		public void run() {
	    	time += getInterval();
    		fireTimeChanged(time);
	    }
	}
}
