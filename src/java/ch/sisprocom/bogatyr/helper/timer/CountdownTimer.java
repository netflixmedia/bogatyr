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
import java.util.Timer;
import java.util.TimerTask;

/**
 * CountdownTimer
 * 
 * @author Stefan Laubenberger
 * @version 20081119
 */
public class CountdownTimer  { //TODO improve JavaDoc
	private List<ListenerTimer> listListenerTimer = new ArrayList<ListenerTimer>();

	protected Timer timer;
	protected long runtime;
	protected long interval;
	

	public CountdownTimer(){
		timer = new Timer();
	}
	
	public void start(final long runtime) {
		this.start(500, runtime, 1000);
	}

	public void start(final long delay, final long runtime, final long interval) {
    	timer.cancel();
    	
    	timer = new Timer();
    	this.runtime = runtime;
    	this.interval = interval;
    	
        timer.schedule(new Task(), delay, interval);
        fireTimerStarted();
    }

    public void stop() {
        timer.cancel();
        fireTimerStopped();
    }	
	
	public void addListenerTimer(final ListenerTimer listener) {
		listListenerTimer.add(listener);
	}
	
	public void removeListenerTimer() {
		listListenerTimer = new ArrayList<ListenerTimer>();
	}
	
	
	/*
	 * Private methods
	 */
	private void fireTimeChanged(long time) {
		for (final ListenerTimer listener : listListenerTimer) {
			listener.timeChanged(time);
		}	
	}
	
	private void fireTimerStarted() {
		for (final ListenerTimer listener : listListenerTimer) {
			listener.timerStarted();
		}	
	}
	
	private void fireTimerStopped() {
		for (final ListenerTimer listener : listListenerTimer) {
			listener.timerStopped();
		}	
	}
	
	
	/*
	 * Inner classes
	 */
	class Task extends TimerTask {
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
