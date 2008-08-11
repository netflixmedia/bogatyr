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
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * Application-Context
 * Get access for general content in the context.
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public class Context implements IContext {
	private static Context instance;

	public static final String KEY_APPLICATION_NAME          = "Application.name"; //$NON-NLS-1$
	public static final String KEY_APPLICATION_VERSION       = "Application.version"; //$NON-NLS-1$
	public static final String KEY_APPLICATION_BUILD         = "Application.build"; //$NON-NLS-1$
	public static final String KEY_APPLICATION_DEBUG         = "Application.debug"; //$NON-NLS-1$
	public static final String KEY_APPLICATION_WORKDIRECTORY = "Application.work_directory"; //$NON-NLS-1$

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
        Logger.getInstance().writeMethodEntry(Context.class, "removeData", key);  //$NON-NLS-1$
	}

	public Object getData(final Object key) {
		Logger.getInstance().writeMethodEntry(Context.class, "getData", key);  //$NON-NLS-1$

		final Object obj = contextData.get(key);
		
		Logger.getInstance().writeMethodExit(Context.class, "getData", obj);  //$NON-NLS-1$
		return obj;
	}

	public String getDataString(final Object key) {
		Logger.getInstance().writeMethodEntry(Context.class, "getDataString", key);  //$NON-NLS-1$

		final String str = (String) contextData.get(key);
		
		Logger.getInstance().writeMethodExit(Context.class, "getDataString", str); //$NON-NLS-1$
		return str;
    }
//
//    public double getDataDouble(final Object key) {
//    	double value = (Double) contextData.get(key);
//    	
//    	Logger.getInstance().writeMethodEntry(Context.class, "getDataDouble", key);  //$NON-NLS-1$
//    	Logger.getInstance().writeMethodExit(Context.class, "getDataDouble", value);  //$NON-NLS-1$
//        
//    	return value;
//    }
//
//    public int getDataInt(final Object key) {
//    	int value = (Integer) contextData.get(key);
//    	
//    	Logger.getInstance().writeMethodEntry(Context.class, "getDataInt", key);  //$NON-NLS-1$
//    	Logger.getInstance().writeMethodExit(Context.class, "getDataInt", value);  //$NON-NLS-1$
//    	
//    	return value;
//    }
//
//    public long getDataLong(final Object key) {
//    	long value = (Long) contextData.get(key);
//    	
//    	Logger.getInstance().writeMethodEntry(Context.class, "getDataLong", key);  //$NON-NLS-1$
//    	Logger.getInstance().writeMethodExit(Context.class, "getDataLong", value);  //$NON-NLS-1$
//    	
//    	return value;
//    }
//
//    public boolean getDataBoolean(final Object key) {
//    	Boolean value = (Boolean) contextData.get(key);
//    	boolean flag = value != null ? value : false;
////    	Logger.getInstance().writeDebug(Context.class, "getDataBoolean", key);  //$NON-NLS-1$
//    	return flag;
//    }

	public String getApplicationName() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getApplicationName"); //$NON-NLS-1$

		String str = getDataString(KEY_APPLICATION_NAME);

		if (str == null) {
			str = "Bogatyr"; //$NON-NLS-1$
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "getApplicationName", str); //$NON-NLS-1$		
		return str;
	}

	public String getApplicationVersion() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getApplicationVersion"); //$NON-NLS-1$

		String str = getDataString(KEY_APPLICATION_VERSION);

		if (str == null) {
			str = "0.36";  //$NON-NLS-1$ //TODO change every release!
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "getApplicationVersion", str); //$NON-NLS-1$
		return str;
	}

	public String getApplicationBuild() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getApplicationBuild"); //$NON-NLS-1$

		String str = getDataString(KEY_APPLICATION_BUILD);
		
		if (str == null) {
			str = "20080808";  //$NON-NLS-1$ //TODO change every release!
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "getApplicationBuild", str); //$NON-NLS-1$
		return str;
	}
	
	public boolean isApplicationDebug() {
    	Boolean value = (Boolean) contextData.get(KEY_APPLICATION_DEBUG);
    	final boolean flag = value != null ? value : false;

    	return flag;
 	}

	public File getApplicationWorkDirectory() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getApplicationWorkDirectory"); //$NON-NLS-1$

		File file = (File)getData(KEY_APPLICATION_WORKDIRECTORY);

		if (file == null) {
			file = HelperEnvInfo.getOsTempDirectory();
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "getApplicationWorkDirectory", file); //$NON-NLS-1$
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
