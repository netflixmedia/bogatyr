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

import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;

import ch.sisprocom.bogatyr.controller.ControllerAbstract;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.6.0
 */
public abstract class ControllerTimerAbstract extends ControllerAbstract implements IListener {
	private Collection<ListenerTimer> listListener = new HashSet<ListenerTimer>();

	private Timer timer = new Timer();
	private long interval;
	
	private boolean isRunning;
	

	protected ControllerTimerAbstract() {
        super();
    }

	/**
	 * Returns the current {@link Timer}.
	 * 
	 * @return current timer
	 * @since 0.6.0
	 */
	public Timer getTimer() {
        return timer;
    }

	/**
	 * Sets the current {@link Timer}.
	 * 
	 * @param timer for the implementation
	 * @since 0.6.0
	 */
    public void setTimer(final Timer timer) {
        this.timer = timer;
    }

	
	/*
	 * Private methods
	 */
	protected void fireTimeChanged(final long time) {
		for (final ListenerTimer listener : listListener) {
			listener.timeChanged(time);
		}	
	}
	
	protected void fireTimerStarted() {
		isRunning = true;
		
		for (final ListenerTimer listener : listListener) {
			listener.timerStarted();
		}	
	}
	
	protected void fireTimerStopped() {
		isRunning = false;
		
		for (final ListenerTimer listener : listListener) {
			listener.timerStopped();
		}	
	}

	
    /*
     * Implemented methods
     */
    public synchronized void addListener(final ListenerTimer listener) {
        listListener.add(listener);
    }

    public synchronized void removeListener(final ListenerTimer listener) {
        listListener.remove(listener);
    }

    public synchronized void removeAllListener() {
        listListener = new HashSet<ListenerTimer>();
    }
    
    public boolean isRunning() {
		return isRunning;
	}
    
    public long getInterval() {
        return interval;
    }

    public void setInterval(final long interval) {
        this.interval = interval;
    }   
}
