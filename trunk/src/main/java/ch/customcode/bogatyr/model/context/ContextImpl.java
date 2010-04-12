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
package ch.customcode.bogatyr.model.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.model.ModelAbstract;


/**
 * Implementation of the context for applications.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100413)
 * @since 0.1.0
 */
public class ContextImpl extends ModelAbstract implements Context {
	private static final long serialVersionUID = 5570878557994873482L;

	private static final Logger log = LoggerFactory.getLogger(ContextImpl.class);
	
	private static final Context INSTANCE = new ContextImpl();

	private transient Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();


    private ContextImpl() {
        super();
        log.trace(HelperLog.constructor());
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
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(contextData));
		return contextData;
    }

    @Override
    public void setData(final Map<Object, Object> data) {
    	log.debug(HelperLog.methodStart(data));
    	
        if (!HelperObject.isEquals(data, contextData)) {
            contextData = data;
            setChanged();
            notifyObservers(MEMBER_DATA);
        }

        log.debug(HelperLog.methodExit());
    }

    @Override
    public void addValue(final Object key, final Object value) { //$JUnit$
    	log.debug(HelperLog.methodStart(key, value));
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
//		if (null == value) {
//			throw new RuntimeExceptionIsNull("value"); //$NON-NLS-1$
//		}

		contextData.put(key, value);
        setChanged();
        notifyObservers(METHOD_ADD_VALUE);
        
        log.debug(HelperLog.methodExit());
	}

	@Override
    public void removeValue(final Object key) { //$JUnit$
		log.debug(HelperLog.methodStart(key));
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		contextData.remove(key);
        setChanged();
        notifyObservers(METHOD_REMOVE_VALUE);
        
        log.debug(HelperLog.methodExit());
	}

	@Override
    public Object getValue(final Object key) { //$JUnit$
		log.debug(HelperLog.methodStart(key));
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		
		final Object result = contextData.get(key);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
    public <T> T getValue(final Object key, final Class<T> clazz) {
		log.debug(HelperLog.methodStart(key, clazz));
		final Object obj = getValue(key);

		T result = null;
		
		if (null != obj) {
			if (HelperObject.isEquals(obj.getClass(), clazz)) {
				result = (T)obj;
			}
		}
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
}
