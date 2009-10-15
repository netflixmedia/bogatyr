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
package ch.sisprocom.bogatyr.helper.context;

import ch.sisprocom.bogatyr.helper.HelperObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Implementation of the context for applications.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.1.0
 */
public class ContextImpl implements Context {
	private static final Context INSTANCE = new ContextImpl();

	private final Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();


    private ContextImpl() {
        super();
    }

    public static Context getInstance() {
	    return INSTANCE;
    }

	
    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(INSTANCE);
	}
	
	
	/*
	 * Implemented methods
	 */
	public void addValue(final Object key, final Object value) { //$JUnit$
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}

		if (value != null) {
            contextData.put(key, value);
		} else {
			removeValue(key);
		}
	}

	public void removeValue(final Object key) { //$JUnit$
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		contextData.remove(key);
	}

	public Object getValue(final Object key) { //$JUnit$
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		return contextData.get(key);
	}

	public String getStringValue(final Object key) { //$JUnit$
		return (String)getValue(key);
    }

	public Boolean getBooleanValue(final Object key) { //$JUnit$
		return (Boolean)getValue(key);
    }
	
	public Double getDoubleValue(final Object key) { //$JUnit$
		return (Double)getValue(key);
	}
	
	public Integer getIntegerValue(final Object key) { //$JUnit$
		return (Integer)getValue(key);
	}
	
	public Float getFloatValue(final Object key) { //$JUnit$
		return (Float)getValue(key);
	}
	
	public Byte getByteValue(final Object key) { //$JUnit$
		return (Byte)getValue(key);
	}
	
	public Long getLongValue(final Object key) { //$JUnit$
		return (Long)getValue(key);
	}

	public Short getShortValue(final Object key) { //$JUnit$
		return (Short)getValue(key);
	}
	
	public BigInteger getBigIntegerValue(final Object key) { //$JUnit$
		return (BigInteger)getValue(key);
	}
	
	public BigDecimal getBigDecimalValue(final Object key) { //$JUnit$
		return (BigDecimal)getValue(key);
	}

	public Date getDateValue(final Object key) { //$JUnit$
		return (Date)getValue(key);
	}
}
