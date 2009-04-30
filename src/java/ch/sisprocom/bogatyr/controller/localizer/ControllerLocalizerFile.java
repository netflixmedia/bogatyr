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

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Localizer implementation for file access.
 * 
 * @author Stefan Laubenberger
 * @version 20090430
 */
public class ControllerLocalizerFile extends ControllerLocalizerAbstract { //TODO document in Wiki!
	public static final String POSTFIX_ACCELERATOR = ".accelerator"; //$NON-NLS-1$
	public static final String POSTFIX_MNEMONIC = ".mnemonic"; //$NON-NLS-1$
	public static final String POSTFIX_TOOLTIP = ".tooltip"; //$NON-NLS-1$
	
	private Locale locale;
	private String localizerBase;
	private ResourceBundle bundle;
	
	
	public ControllerLocalizerFile(final String localizerBase) {
        super();
        this.localizerBase = localizerBase;

        init();
    }

	public String getLocalizerBase() {
		return localizerBase;
	}

    public synchronized void setLocalizerBase(final String localizerBase) {
        this.localizerBase = localizerBase;
        bundle = ResourceBundle.getBundle(localizerBase, locale);
    }
	
	
	/*
	 * Implemented methods
	 */
	public Locale getLocale() {
		return locale;
	}

    public synchronized void setLocale(final Locale locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle(localizerBase, locale);
        fireLocaleChanged();
    }
	
	public String getValue(final String key) {
		return bundle.getString(key);
	}
	
	public String getAccelerator(final String key) {
		try {
			return bundle.getString(key + POSTFIX_ACCELERATOR);
		} catch (Exception ex) {
			return ""; //$NON-NLS-1$
		}
	}
	
	public int getMnemonic(final String key) {
		try {
			return Integer.valueOf(bundle.getString(key + POSTFIX_MNEMONIC));
		} catch (Exception ex) {
			return 0;
		}
	}

	public String getTooltip(final String key) {
		try {
			return bundle.getString(key + POSTFIX_TOOLTIP);
		} catch (Exception ex) {
			return ""; //$NON-NLS-1$
		}
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle(localizerBase, locale);
	}
}
