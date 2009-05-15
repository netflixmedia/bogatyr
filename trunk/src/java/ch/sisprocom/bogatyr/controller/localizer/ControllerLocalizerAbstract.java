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
package ch.sisprocom.bogatyr.controller.localizer;

import ch.sisprocom.bogatyr.controller.ControllerAbstract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;


/**
 * Localizer implementation for file access.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class ControllerLocalizerAbstract extends ControllerAbstract implements IControllerLocalizer {
	private Collection<ListenerLocale> listListener = new ArrayList<ListenerLocale>();

	private Locale locale = Locale.getDefault();
	
	
	/*
	 * Implemented methods
	 */
    public synchronized void addListener(final ListenerLocale listener) {
        listListener.add(listener);
    }

    public synchronized void removeListener(final ListenerLocale listener) {
        listListener.remove(listener);
    }

    public synchronized void removeAllListener() {
        listListener = new ArrayList<ListenerLocale>();
    }
	

	/*
	 * Private methods
	 */
	private void fireLocaleChanged() {
		for (final ListenerLocale listener : listListener) {
			listener.localeChanged();
		}	
	}

	
	/*
	 * Implemented methods
	 */
	public Locale getLocale() {
		return locale;
	}
	
    public void setLocale(final Locale locale) {
    	this.locale = locale;
        fireLocaleChanged();
    }
}
