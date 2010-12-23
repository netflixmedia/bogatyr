/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.service.localizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.KeyStroke;

import net.laubenberger.bogatyr.helper.HelperEnvironment;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.model.misc.Language;
import net.laubenberger.bogatyr.model.misc.Platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Localizer implementation for file access.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100526)
 * @since 0.1.0
 */
public class LocalizerFile extends LocalizerAbstract {
	private static final Logger log = LoggerFactory.getLogger(LocalizerFile.class);

	private static final Platform PLATFORM = HelperEnvironment.getPlatform();
	
	public static final String POSTFIX_ACCELERATOR = ".accelerator"; //$NON-NLS-1$
	public static final String POSTFIX_MNEMONIC = ".mnemonic"; //$NON-NLS-1$
	public static final String POSTFIX_TOOLTIP = ".tooltip"; //$NON-NLS-1$

	private String localizerBase;
	final ClassLoader classLoader;
	private ResourceBundle bundle;


	public LocalizerFile(final String localizerBase) {
		this(localizerBase, ClassLoader.getSystemClassLoader());
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(localizerBase));
	}

	public LocalizerFile(final String localizerBase, final ClassLoader classLoader) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(localizerBase, classLoader));

		if (null == localizerBase) {
			throw new RuntimeExceptionIsNull("localizerBase"); //$NON-NLS-1$
		}

		if (null == classLoader) {
			throw new RuntimeExceptionIsNull("classLoader"); //$NON-NLS-1$
		}

		this.localizerBase = localizerBase;
		this.classLoader = classLoader;

		init();
	}
	
	/**
	 * Returns the localize base of the resource file.
	 *
	 * @return localize base of the resource file
	 * @since 0.1.0
	 */
	public String getLocalizerBase() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(localizerBase));
		return localizerBase;
	}

	/**
	 * Sets the localize base of the resource file.
	 *
	 * @param localizerBase of the resource file
	 * @since 0.1.0
	 */
	public void setLocalizerBase(final String localizerBase) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(localizerBase));
		if (null == localizerBase) {
			throw new RuntimeExceptionIsNull("localizerBase"); //$NON-NLS-1$
		}

		this.localizerBase = localizerBase;
		bundle = ResourceBundle.getBundle(localizerBase, getLocale(), classLoader);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}


	/*
	 * Private methods
	 */

	private void init() {
		bundle = ResourceBundle.getBundle(localizerBase, getLocale(), classLoader);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public void setLocale(final Locale locale) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(locale));
		if (null == locale) {
			throw new RuntimeExceptionIsNull("locale"); //$NON-NLS-1$
		}

		bundle = ResourceBundle.getBundle(localizerBase, locale, classLoader);

		super.setLocale(locale);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public String getValue(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		String result = null;
		try {
			result = bundle.getString(key);
		} catch (MissingResourceException ex) {
			if (log.isWarnEnabled()) log.warn("Resource not found", ex); //$NON-NLS-1$
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public KeyStroke getAccelerator(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		String keystroke;
		KeyStroke result;
		try {
			keystroke = bundle.getString(key + POSTFIX_ACCELERATOR);
			result = null == keystroke ? null : KeyStroke.getKeyStroke(keystroke);
		} catch (MissingResourceException ex) {
			keystroke = getValue(key + POSTFIX_ACCELERATOR + '.' + PLATFORM.getCode());
			result = null == keystroke ? null : KeyStroke.getKeyStroke(keystroke);
		}
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public int getMnemonic(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final String mnemonic = getValue(key + POSTFIX_MNEMONIC);
		final int result = null == mnemonic ? 0 : (int) mnemonic.charAt(0);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public String getTooltip(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final String result = getValue(key + POSTFIX_TOOLTIP);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	@Override
	public List<Language> getAvailableLanguages() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		final List<Language> result = new ArrayList<Language>();
		
		for (final Language language : Language.values()) {
			final ResourceBundle bundle = ResourceBundle.getBundle(localizerBase, language.getLocale());
			
			if (HelperObject.isEquals(language.getLocale(), bundle.getLocale())) {
				result.add(language);
			}
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}