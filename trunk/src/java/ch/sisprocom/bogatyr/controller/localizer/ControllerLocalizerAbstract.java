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

import java.util.ArrayList;
import java.util.Collection;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * Localizer implementation for file access.
 * 
 * @author Stefan Laubenberger
 * @version 20090403
 */
public abstract class ControllerLocalizerAbstract implements IControllerLocalizer { //TODO document in Wiki!
	private Collection<ListenerLocale> listListener = new ArrayList<ListenerLocale>();

	
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
	protected void fireLocaleChanged() {
		for (final ListenerLocale listener : listListener) {
			listener.localeChanged();
		}	
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
