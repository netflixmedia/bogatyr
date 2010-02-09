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
package ch.sisprocom.bogatyr.model.context;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.model.ModelAbstract;


/**
 * Implementation of the context for applications.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100202)
 * @since 0.1.0
 */
public class ContextImpl extends ModelAbstract implements Context {
	private static final long serialVersionUID = 5570878557994873482L;

	private static final Context INSTANCE = new ContextImpl();

	private transient Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();


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
            notifyObservers(MEMBER_DATA);
        }

    }

    @Override
    public void addValue(final Object key, final Object value) { //$JUnit$
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}

        setChanged();
		if (null != value) {
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
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		contextData.remove(key);
        setChanged();
        notifyObservers(METHOD_REMOVE_VALUE);
}

	@Override
    public Object getValue(final Object key) { //$JUnit$
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		return contextData.get(key);
	}

	@Override
    public String getString(final Object key) { //$JUnit$
		final Object obj = getValue(key);
		
		if (null == obj) {
			return null;
		}
		if (obj instanceof String) {
			return (String)obj;
		}
		return obj.toString();
    }

	@Override
    public Boolean getBoolean(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Boolean ? (Boolean)obj : null;
    }
	
	@Override
    public Double getDouble(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Double ? (Double)obj : null;
	}
	
	@Override
    public Integer getInteger(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Integer ? (Integer)obj : null;
	}
	
	@Override
    public Float getFloat(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Float ? (Float)obj : null;
	}
	
	@Override
    public Byte getByte(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Byte ? (Byte)obj : null;
	}
	
	@Override
    public Long getLong(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Long ? (Long)obj : null;
	}

	@Override
    public Short getShort(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Short ? (Short)obj : null;
	}
	
	@Override
    public BigInteger getBigInteger(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof BigInteger ? (BigInteger)obj : null;
	}
	
	@Override
    public BigDecimal getBigDecimal(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof BigDecimal ? (BigDecimal)obj : null;
	}

	@Override
    public Date getDate(final Object key) { //$JUnit$
		final Object obj = getValue(key);

		return null != obj && obj instanceof Date ? (Date)obj : null;
	}

	@Override
    public File getFile(final Object key) {
		final Object obj = getValue(key);

		return null != obj && obj instanceof File ? (File)obj : null;
	}
	
	@Override
    public URL getURL(final Object key) {
		final Object obj = getValue(key);

		return null != obj && obj instanceof URL ? (URL)obj : null;
	}
}
