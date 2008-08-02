/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.context;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.helper.HelperEnvInfo;
import ch.orwell.bogatyr.helper.HelperGeneral;


/**
 * Application-Context
 * Get access for general content in the context.
 * 
 * @author Stefan Laubenberger
 * @version 20080729
 */
public class Context implements IContext {
	private static Context instance;

	public static final String ATT_APPLICATION_NAME          = "Application.name"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_VERSION       = "Application.version"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_BUILD         = "Application.build"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_DEBUG         = "Application.debug"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_WORKDIRECTORY = "Application.work_directory"; //$NON-NLS-1$

	private final Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();
	

    private Context() {
        super();
    }
	
    public static Context getInstance() {
    	if (instance == null) {
    		instance = new Context();
		}
    	return instance;
	}
    
    
	/*
	 * Implemented methods
	 */
	public void addData(final Object key, final Object value) {
		if (value != null) {
            contextData.put(key, value);
		} else {
			removeData(key);
		}
	}

	public void removeData(final Object key) {
        contextData.remove(key);
	}

	public Object getData(final Object key) {
	    return contextData.get(key);
	}

	public String getDataString(final Object key) {
        return (String) contextData.get(key);
    }

    public double getDataDouble(final Object key) {
        return (Double) contextData.get(key);
    }

    public int getDataInt(final Object key) {
       return (Integer) contextData.get(key);
    }

    public long getDataLong(final Object key) {
        return (Long) contextData.get(key);
    }

    public boolean getDataBoolean(final Object key) {
    	return (Boolean) contextData.get(key);
    }

	public String getApplicationName() {
		String str = getDataString(ATT_APPLICATION_NAME);
		if (str == null) return "Bogatyr";
		return str;
	}

	public String getApplicationVersion() {
		String str = getDataString(ATT_APPLICATION_VERSION);
		if (str == null) return "0.35"; //TODO change every release!
		return str;
	}

	public String getApplicationBuild() {
		String str = getDataString(ATT_APPLICATION_BUILD);
		if (str == null) return "20080729"; //TODO change every release!
		return str;
	}
	
	public boolean isApplicationDebug() {
		return getDataBoolean(ATT_APPLICATION_DEBUG);
	}

	public File getApplicationWorkDirectory() {
		File file = (File)getData(ATT_APPLICATION_WORKDIRECTORY);
		if (file == null) return HelperEnvInfo.getOsTempDirectory();
		return file;
	}


    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(instance);
	}
}
