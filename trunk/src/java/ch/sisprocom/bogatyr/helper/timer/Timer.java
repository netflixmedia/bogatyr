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

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 20081202
 */
public class Timer { //TODO document in Wiki!
	private List<ListenerTimer> listListenerTimer = new ArrayList<ListenerTimer>();

	protected java.util.Timer timer;
	protected long time = 0;
	protected long interval;
	

	public Timer(){
		timer = new java.util.Timer();
	}
	
	/**
	 * Starts immediately the timer with a given interval.
	 * 
	 * @param interval of the timer
	 */
	public void start(final long interval) {
		this.start(0, interval);
	}

	/**
	 * Starts the timer with a given delay and interval.
	 * 
	 * @param delay until the timer starts
	 * @param interval of the timer
	 */
	public void start(final long delay, final long interval) {
    	timer.cancel();
    	
    	timer = new java.util.Timer();
    	this.interval = interval;
        timer.schedule(new Task(), delay, interval);
        fireTimerStarted();
    }

	/**
	 * Stops immediately the timer/countdown.
	 */
    public void stop() {
        timer.cancel();
        fireTimerStopped();
    }	
	
	/**
	 * Adds a listener for this timer/countdown.
	 * 
	 * @param listener to add
	 */
	public void addListenerTimer(final ListenerTimer listener) {
		listListenerTimer.add(listener);
	}
	
	/**
	 * Remove a listener for this timer/countdown.
	 * 
	 * @param listener to remove
	 */
	public void removeListenerTimer(final ListenerTimer listener) {
		listListenerTimer.remove(listener);
	}
	
	/**
	 * Removes all listeners for this timer/countdown. 
	 */
	public void removeAllListenerTimer() {
		listListenerTimer = new ArrayList<ListenerTimer>();
	}
	
	
	/*
	 * Private methods
	 */
	protected void fireTimeChanged(final long time) {
		for (final ListenerTimer listener : listListenerTimer) {
			listener.timeChanged(time);
		}	
	}
	
	protected void fireTimerStarted() {
		for (final ListenerTimer listener : listListenerTimer) {
			listener.timerStarted();
		}	
	}
	
	protected void fireTimerStopped() {
		for (final ListenerTimer listener : listListenerTimer) {
			listener.timerStopped();
		}	
	}
	
	
	/*
	 * Inner classes
	 */
	protected class Task extends TimerTask {
	    @Override
		public void run() {
	    	time += interval;
    		fireTimeChanged(time);
	    }
	}
}
