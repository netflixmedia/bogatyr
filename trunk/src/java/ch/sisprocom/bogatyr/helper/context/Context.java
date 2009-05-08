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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * Implementation of the context for applications.
 * 
 * @author Stefan Laubenberger
 * @version 20090507
 */
public class Context implements IContext { //TODO document in Wiki!
	private static final IContext instance = new Context();

	private final Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();
	

    private Context() {
        super();
    }
	
    public static IContext getInstance() {
	    return instance;
    }

	
    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(instance);
	}
	
	
	/*
	 * Implemented methods
	 */
	public void addValue(final Object key, final Object value) { //$JUnit
		if (value != null) {
            contextData.put(key, value);
		} else {
			removeValue(key);
		}
	}

	public void removeValue(final Object key) { //$JUnit
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		contextData.remove(key);
	}

	public Object getValue(final Object key) { //$JUnit
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		return contextData.get(key);
	}

	public String getStringValue(final Object key) { //$JUnit
		return (String)getValue(key);
    }

	public Boolean getBooleanValue(final Object key) { //$JUnit
		return (Boolean)getValue(key);
    }
	
	public Double getDoubleValue(final Object key) { //$JUnit
		return (Double)getValue(key);
	}
	
	public Integer getIntegerValue(final Object key) { //$JUnit
		return (Integer)getValue(key);
	}
	
	public Float getFloatValue(final Object key) { //$JUnit
		return (Float)getValue(key);
	}
	
	public Byte getByteValue(final Object key) { //$JUnit
		return (Byte)getValue(key);
	}
	
	public Long getLongValue(final Object key) { //$JUnit
		return (Long)getValue(key);
	}

	public Short getShortValue(final Object key) { //$JUnit
		return (Short)getValue(key);
	}
	
	public BigInteger getBigIntegerValue(final Object key) { //$JUnit
		return (BigInteger)getValue(key);
	}
	
	public BigDecimal getBigDecimalValue(final Object key) { //$JUnit
		return (BigDecimal)getValue(key);
	}

	public Date getDateValue(final Object key) { //$JUnit
		return (Date)getValue(key);
	}
}
