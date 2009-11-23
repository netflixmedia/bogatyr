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
package ch.sisprocom.bogatyr.model.context;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Implementation of the context for applications.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091022)
 * @since 0.1.0
 */
public class ContextImpl extends ModelAbstract implements Context {
	private static final long serialVersionUID = 5570878557994873482L;

	private static final Context INSTANCE = new ContextImpl();

	private Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();


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
    @Override
    public Map<Object, Object> getData() {
        return contextData;
    }

    @Override
    public void setData(final Map<Object, Object> data) {
        if (!HelperObject.isEquals(data, contextData)) {
            contextData = data;
            setChanged();
            notifyObservers(METHOD_SET_DATA);
        }

    }

    @Override
    public void addValue(final Object key, final Object value) { //$JUnit$
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}

        setChanged();
		if (value != null) {
            contextData.put(key, value);
            notifyObservers(METHOD_ADD_VALUE);
		} else {
			removeValue(key);
            notifyObservers(METHOD_REMOVE_VALUE);
		}
	}

	@Override
    public void removeValue(final Object key) { //$JUnit$
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		contextData.remove(key);
        setChanged();
        notifyObservers(METHOD_REMOVE_VALUE);
}

	@Override
    public Object getValue(final Object key) { //$JUnit$
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		return contextData.get(key);
	}

	@Override
    public String getStringValue(final Object key) { //$JUnit$
		return (String)getValue(key);
    }

	@Override
    public Boolean getBooleanValue(final Object key) { //$JUnit$
		return (Boolean)getValue(key);
    }
	
	@Override
    public Double getDoubleValue(final Object key) { //$JUnit$
		return (Double)getValue(key);
	}
	
	@Override
    public Integer getIntegerValue(final Object key) { //$JUnit$
		return (Integer)getValue(key);
	}
	
	@Override
    public Float getFloatValue(final Object key) { //$JUnit$
		return (Float)getValue(key);
	}
	
	@Override
    public Byte getByteValue(final Object key) { //$JUnit$
		return (Byte)getValue(key);
	}
	
	@Override
    public Long getLongValue(final Object key) { //$JUnit$
		return (Long)getValue(key);
	}

	@Override
    public Short getShortValue(final Object key) { //$JUnit$
		return (Short)getValue(key);
	}
	
	@Override
    public BigInteger getBigIntegerValue(final Object key) { //$JUnit$
		return (BigInteger)getValue(key);
	}
	
	@Override
    public BigDecimal getBigDecimalValue(final Object key) { //$JUnit$
		return (BigDecimal)getValue(key);
	}

	@Override
    public Date getDateValue(final Object key) { //$JUnit$
		return (Date)getValue(key);
	}
}
