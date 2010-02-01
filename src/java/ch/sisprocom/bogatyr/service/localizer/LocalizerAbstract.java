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
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.localizer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import ch.sisprocom.bogatyr.helper.HelperNumber;
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
    public Boolean getBooleanValue(final String key) {
        return null == getValue(key) ? null : Boolean.valueOf(getValue(key));
    }

	@Override
    public BigDecimal getBigDecimalValue(final String key) {
		return HelperNumber.getBigDecimal(getValue(key));
	}

	@Override
    public BigInteger getBigIntegerValue(final String key) {
		return HelperNumber.getBigInteger(getValue(key));
	}

	@Override
    public Byte getByteValue(final String key) {
		return HelperNumber.getByte(getValue(key));
	}

	@Override
    public Double getDoubleValue(final String key) {
		return HelperNumber.getDouble(getValue(key));
	}

	@Override
    public Float getFloatValue(final String key) {
		return HelperNumber.getFloat(getValue(key));
	}

	@Override
    public Integer getIntegerValue(final String key) {
		return HelperNumber.getInteger(getValue(key));
	}

	@Override
    public Long getLongValue(final String key) {
		return HelperNumber.getLong(getValue(key));
	}

	@Override
    public Short getShortValue(final String key) {
		return HelperNumber.getShort(getValue(key));
	}

	@Override
    public Locale getLocale() {
		return locale;
	}
	
    @Override
    public void setLocale(final Locale locale) {
		if (null == locale) {
			throw new IllegalArgumentException("locale is null!"); //$NON-NLS-1$
		}

    	this.locale = locale;
        fireLocaleChanged();
    }
    
    @Override
    public synchronized void addListener(final ListenerLocale listener) {
		if (null == listener) {
			throw new IllegalArgumentException("listener is null!"); //$NON-NLS-1$
		}

		listListener.add(listener);
    }

	@Override
    public void deleteListener(final ListenerLocale listener) {
		if (null == listener) {
			throw new IllegalArgumentException("listener is null!"); //$NON-NLS-1$
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
