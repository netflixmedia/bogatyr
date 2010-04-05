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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * Localizer implementation for file access.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.1.0
 */
public class LocalizerFile extends LocalizerAbstract {
	private static final Logger log = LoggerFactory.getLogger(LocalizerFile.class);
	
	public static final String POSTFIX_ACCELERATOR = ".accelerator"; //$NON-NLS-1$
	public static final String POSTFIX_MNEMONIC = ".mnemonic"; //$NON-NLS-1$
	public static final String POSTFIX_TOOLTIP = ".tooltip"; //$NON-NLS-1$
	
	private String localizerBase;
	private ResourceBundle bundle;
	
	
	public LocalizerFile(final String localizerBase) {
        super();
        log.trace(HelperLog.constructor(localizerBase));
        
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
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(localizerBase));
		return localizerBase;
	}

	/**
	 * Sets the localize base of the resource file.
	 * 
	 * @param localizerBase of the resource file
	 * @since 0.1.0
	 */
    public void setLocalizerBase(final String localizerBase) {
    	log.debug(HelperLog.methodStart(localizerBase));
    	if (null == localizerBase) {
			throw new RuntimeExceptionIsNull("localizerBase"); //$NON-NLS-1$
		}
		
		this.localizerBase = localizerBase;
        bundle = ResourceBundle.getBundle(localizerBase, getLocale());
        
        log.debug(HelperLog.methodExit());
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
    	log.debug(HelperLog.methodStart(locale));
		if (null == locale) {
			throw new RuntimeExceptionIsNull("locale"); //$NON-NLS-1$
		}

		bundle = ResourceBundle.getBundle(localizerBase, locale);

        super.setLocale(locale);
        
        log.debug(HelperLog.methodExit());
    }

    
	/*
	 * Implemented methods
	 */
	@Override
    public String getValue(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		String result = null;
		try {
			result = bundle.getString(key);
		} catch (MissingResourceException ex) {
			log.warn("Resource not found", ex); //$NON-NLS-1$
		}
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	@Override
    public KeyStroke getAccelerator(final String key) {
		log.debug(HelperLog.methodStart(key));

		final String keystroke = bundle.getString(key + POSTFIX_ACCELERATOR);
		final KeyStroke result = null == keystroke ? null : KeyStroke.getKeyStroke(keystroke);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	@Override
    public int getMnemonic(final String key) {
		log.debug(HelperLog.methodStart(key));

		final String mnemonic = bundle.getString(key + POSTFIX_MNEMONIC);
		final int result = null == mnemonic ? 0 : (int) mnemonic.charAt(0);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public String getTooltip(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final String result = bundle.getString(key + POSTFIX_TOOLTIP);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
