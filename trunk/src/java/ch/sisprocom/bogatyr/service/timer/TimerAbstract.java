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

import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.6.0
 */
public abstract class TimerAbstract extends ServiceAbstract implements TimeMachine {
	private Collection<ListenerTimer> listListener = new HashSet<ListenerTimer>();

	private Timer timer = new Timer();
	private long interval;
	
	private boolean isRunning;
	

	protected TimerAbstract() {
        super();
    }

	/**
	 * Returns the current {@link Timer}.
	 * 
	 * @return current timer
	 * @see Timer
	 * @since 0.6.0
	 */
	public Timer getTimer() {
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
    	if (null == timer) {
    		throw new RuntimeExceptionArgumentIsNull("timer"); //$NON-NLS-1$
    	}

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
	
    protected void setInterval(final long interval) {
        this.interval = interval;
    } 

	
    /*
     * Implemented methods
     */
    @Override
    public boolean isRunning() {
    	return isRunning;
    }
    
    @Override
    public long getInterval() {
    	return interval;
    }  

    @Override
    public synchronized void addListener(final ListenerTimer listener) {
    	if (null == listener) {
    		throw new RuntimeExceptionArgumentIsNull("listener"); //$NON-NLS-1$
    	}

    	listListener.add(listener);
    }

    @Override
    public synchronized void deleteListener(final ListenerTimer listener) {
    	if (null == listener) {
    		throw new RuntimeExceptionArgumentIsNull("listener"); //$NON-NLS-1$
    	}

    	listListener.remove(listener);
    }

    @Override
    public synchronized void deleteListeners() {
        listListener = new HashSet<ListenerTimer>();
    }

    @Override
    public int countListeners() {
    	return listListener.size();
    }
}
