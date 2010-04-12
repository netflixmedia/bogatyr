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

import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperNumber;
import ch.customcode.bogatyr.misc.Event;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * Abstract localizer implementation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100413)
 * @since 0.6.0
 */
public abstract class LocalizerAbstract extends ServiceAbstract implements Localizer {
	private static final Logger log = LoggerFactory.getLogger(LocalizerAbstract.class);
	
	private Collection<ListenerLocale> listListener = new HashSet<ListenerLocale>();

	private final Event<Localizer> event = new Event<Localizer>(this);
	
	private Locale locale = Locale.getDefault();
	

	/*
	 * Private methods
	 */
	private void fireLocaleChanged() {
		log.trace(HelperLog.methodStart());
		
		for (final ListenerLocale listener : listListener) {
			listener.localeChanged(event);
		}	
		
		log.trace(HelperLog.methodExit());
	}

	
	/*
	 * Implemented methods
	 */
    @Override
    public Boolean getBoolean(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Boolean result = null == getValue(key) ? null : Boolean.valueOf(getValue(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

	@Override
    public BigDecimal getNumber(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final BigDecimal result = HelperNumber.getNumber(getValue(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public File getFile(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final String file = getValue(key);
		final File result = null == file ? null : new File(file);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public URL getURL(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		
		final String url = getValue(key);
		URL result = null;
		
		if (null != url) {
			try {
				result = new URL(url);
			} catch (MalformedURLException ex) {
				log.info("URL invalid", ex); //$NON-NLS-1$
			}
		}
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Locale getLocale() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(locale));
		return locale;
	}
	
    @Override
    public void setLocale(final Locale locale) {
		log.debug(HelperLog.methodStart(locale));
		if (null == locale) {
			throw new RuntimeExceptionIsNull("locale"); //$NON-NLS-1$
		}

    	this.locale = locale;
        fireLocaleChanged();

        log.debug(HelperLog.methodExit());
    }
    
    @Override
    public synchronized void addListener(final ListenerLocale listener) {
		log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.add(listener);

		log.debug(HelperLog.methodExit());
    }

	@Override
    public synchronized void deleteListener(final ListenerLocale listener) {
		log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.remove(listener);

		log.debug(HelperLog.methodExit());
    }

    @Override
    public synchronized void deleteListeners() {
		log.debug(HelperLog.methodStart());
		
		listListener = new HashSet<ListenerLocale>();

		log.debug(HelperLog.methodExit());
    }

    @Override
    public int countListeners() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(listListener.size()));
    	return listListener.size();
    }
}
