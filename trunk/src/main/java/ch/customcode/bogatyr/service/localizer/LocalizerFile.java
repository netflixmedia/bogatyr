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
package ch.customcode.bogatyr.service.localizer;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.KeyStroke;

import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * Localizer implementation for file access.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
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

        if (null == localizerBase) {
			throw new RuntimeExceptionIsNull("localizerBase"); //$NON-NLS-1$
		}
		
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
    public void setLocalizerBase(final String localizerBase) {
		if (null == localizerBase) {
			throw new RuntimeExceptionIsNull("localizerBase"); //$NON-NLS-1$
		}
		
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
	public void setLocale(final Locale locale) {
		if (null == locale) {
			throw new RuntimeExceptionIsNull("locale"); //$NON-NLS-1$
		}

		bundle = ResourceBundle.getBundle(localizerBase, locale);

        super.setLocale(locale);
    }

    
	/*
	 * Implemented methods
	 */
	@Override
    public String getValue(final String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException ex) {
			return null;
		}
	}
	
	@Override
    public KeyStroke getAccelerator(final String key) {
		final String keystroke = bundle.getString(key + POSTFIX_ACCELERATOR);
		
		return null == keystroke ? null : KeyStroke.getKeyStroke(keystroke);
	}
	
	@Override
    public int getMnemonic(final String key) {
		final String mnemonic = bundle.getString(key + POSTFIX_MNEMONIC);
		
		return null == mnemonic ? 0 : (int) mnemonic.charAt(0);
	}

	@Override
    public String getTooltip(final String key) {
		return bundle.getString(key + POSTFIX_TOOLTIP);
	}
}
