/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.service.timer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;

import ch.customcode.bogatyr.misc.Event;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.service.ServiceAbstract;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.6.0
 */
public abstract class TimerAbstract extends ServiceAbstract implements TimeMachine {
	private Collection<ListenerTimer> listListener = new HashSet<ListenerTimer>();

    private final Event<TimeMachine> event = new Event<TimeMachine>(this);

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
    		throw new RuntimeExceptionIsNull("timer"); //$NON-NLS-1$
    	}

        this.timer = timer;
    }

	
	/*
	 * Private methods
	 */
	protected void fireTimeChanged() {
		for (final ListenerTimer listener : listListener) {
			listener.timeChanged(event);
		}	
	}
	
	protected void fireTimerStarted() {
		isRunning = true;
		
		for (final ListenerTimer listener : listListener) {
			listener.timerStarted(event);
		}	
	}
	
	protected void fireTimerStopped() {
		isRunning = false;
		
		for (final ListenerTimer listener : listListener) {
			listener.timerStopped(event);
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
    		throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
    	}

    	listListener.add(listener);
    }

    @Override
    public synchronized void deleteListener(final ListenerTimer listener) {
    	if (null == listener) {
    		throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
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
