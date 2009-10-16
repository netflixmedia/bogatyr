/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperString;

import javax.swing.KeyStroke;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Localizer implementation for file access.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091016)
 * @since 0.1.0
 */
public class LocalizerFile extends LocalizerAbstract {
	public static final String POSTFIX_ACCELERATOR = ".accelerator"; //$NON-NLS-1$
	public static final String POSTFIX_MNEMONIC = ".mnemonic"; //$NON-NLS-1$
	public static final String POSTFIX_TOOLTIP = ".tooltip"; //$NON-NLS-1$
	
	private String localizerBase;
	private ResourceBundle bundle;
	
	
	public LocalizerFile(final String localizerBase) {
        super();
        this.localizerBase = localizerBase;

        init();
    }

	/**
	 * Returns the localize base of the resource file.
	 * 
	 * @return localize base of the resource file
	 * @since 0.1.0
	 */
	public String getLocalizerBase() {
		return localizerBase;
	}

	/**
	 * Sets the localize base of the resource file.
	 * 
	 * @param localizerBase of the resource file
	 * @since 0.1.0
	 */
    public synchronized void setLocalizerBase(final String localizerBase) {
        this.localizerBase = localizerBase;
        bundle = ResourceBundle.getBundle(localizerBase, getLocale());
    }
	
	
	/*
	 * Private methods
	 */
	private void init() {
        bundle = ResourceBundle.getBundle(localizerBase, getLocale());
	}
	

    /*
	 * Overridden methods
	 */
    @Override
	public synchronized void setLocale(final Locale locale) {
        bundle = ResourceBundle.getBundle(localizerBase, locale);

        super.setLocale(locale);
    }

    
	/*
	 * Implemented methods
	 */
	@Override
    public String getValue(final String key) {
		return bundle.getString(key);
	}
	
	@Override
    public KeyStroke getAccelerator(final String key) {
		try {
			return KeyStroke.getKeyStroke(bundle.getString(key + POSTFIX_ACCELERATOR));
		} catch (MissingResourceException ex) {
			return null;
		}
	}
	
	@Override
    public int getMnemonic(final String key) {
		try {
			final String mnemonic = bundle.getString(key + POSTFIX_MNEMONIC);
			return (int) mnemonic.charAt(0);
		} catch (Exception ex) {
			return 0;
		}
	}

	@Override
    public String getTooltip(final String key) {
		try {
			return bundle.getString(key + POSTFIX_TOOLTIP);
		} catch (MissingResourceException ex) {
			return HelperString.EMPTY_STRING;
		}
	}
}
