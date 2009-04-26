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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

import ch.sisprocom.bogatyr.controller.ControllerAbstract;

/**
 * This is a timer which informs all added listeners about its state.
 * 
 * @author Stefan Laubenberger
 * @version 20090426
 */
public abstract class ControllerTimerAbstract extends ControllerAbstract implements IListener{ //TODO document in Wiki!
	private Collection<ListenerTimer> listListener = new ArrayList<ListenerTimer>();

	protected Timer timer;
	protected long time;
	protected long interval;
	

	protected ControllerTimerAbstract() {
        super();
        timer = new Timer();
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
		for (final ListenerTimer listener : listListener) {
			listener.timerStarted();
		}	
	}
	
	protected void fireTimerStopped() {
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
        listListener = new ArrayList<ListenerTimer>();
    }
}
