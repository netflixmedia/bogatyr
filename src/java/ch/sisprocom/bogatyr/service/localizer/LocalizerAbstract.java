/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.localizer;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * Abstract localizer implementation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.6.0
 */
public abstract class LocalizerAbstract extends ServiceAbstract implements Localizer {
	private Collection<ListenerLocale> listListener = new HashSet<ListenerLocale>();

	private Locale locale = Locale.getDefault();
	

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
    @Override
    public Boolean getBoolean(final String key) {
        return null == getValue(key) ? null : Boolean.valueOf(getValue(key));
    }

	@Override
    public BigDecimal getBigDecimal(final String key) {
		return HelperNumber.getBigDecimal(getValue(key));
	}

	@Override
    public BigInteger getBigInteger(final String key) {
		return HelperNumber.getBigInteger(getValue(key));
	}

	@Override
    public Byte getByte(final String key) {
		return HelperNumber.getByte(getValue(key));
	}

	@Override
    public Double getDouble(final String key) {
		return HelperNumber.getDouble(getValue(key));
	}

	@Override
    public Float getFloat(final String key) {
		return HelperNumber.getFloat(getValue(key));
	}

	@Override
    public Integer getInteger(final String key) {
		return HelperNumber.getInteger(getValue(key));
	}

	@Override
    public Long getLong(final String key) {
		return HelperNumber.getLong(getValue(key));
	}

	@Override
    public Short getShort(final String key) {
		return HelperNumber.getShort(getValue(key));
	}

	@Override
    public File getFile(final String key) {
		final String file = getValue(key);
		
		return null == file ? null : new File(file);
	}

	@Override
    public URL getURL(final String key) {
		final String url = getValue(key);
		
		if (null != url) {
			try {
				return new URL(url);
			} catch (MalformedURLException e) {
				// do nothing
			}
		}
		return null;
	}

	@Override
    public Locale getLocale() {
		return locale;
	}
	
    @Override
    public void setLocale(final Locale locale) {
		if (null == locale) {
			throw new RuntimeExceptionArgumentIsNull("locale"); //$NON-NLS-1$
		}

    	this.locale = locale;
        fireLocaleChanged();
    }
    
    @Override
    public synchronized void addListener(final ListenerLocale listener) {
		if (null == listener) {
			throw new RuntimeExceptionArgumentIsNull("listener"); //$NON-NLS-1$
		}

		listListener.add(listener);
    }

	@Override
    public void deleteListener(final ListenerLocale listener) {
		if (null == listener) {
			throw new RuntimeExceptionArgumentIsNull("listener"); //$NON-NLS-1$
		}

    	listListener.remove(listener);
    }

    @Override
    public synchronized void deleteListeners() {
        listListener = new HashSet<ListenerLocale>();
    }

    @Override
    public int countListeners() {
    	return listListener.size();
    }
}
