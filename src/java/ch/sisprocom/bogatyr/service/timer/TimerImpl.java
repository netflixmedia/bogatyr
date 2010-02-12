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

import java.util.TimerTask;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.6.0
 */
public class TimerImpl extends TimerAbstract implements Timer {
	long time;
	
	@Override
    public long getTime() {
		return time;
	}

	@Override
    public void setTime(final long time) {
		if (0 > time) {
			throw new RuntimeExceptionMustBeGreater("time", time, 0); //$NON-NLS-1$
		}

		this.time = time;
	}
	
	
	/*
	 * Implemented methods
	 */
    @Override
    public synchronized void start(final long interval) {
		if (0 > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}

		start(HelperNumber.NUMBER_0.longValue(), interval);
    }

    @Override
    public synchronized void start(final long delay, final long interval) {
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
    }

    @Override
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
    		fireTimeChanged();
	    }
	}
}
