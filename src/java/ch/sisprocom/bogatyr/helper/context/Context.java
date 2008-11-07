/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * Application-Context
 * Get access for general content in the context.
 * 
 * @author Stefan Laubenberger
 * @version 20081028
 */
public class Context implements IContext {
	private static Context instance;

	private static final String KEY_APPLICATION_NAME          = "Application.name"; //$NON-NLS-1$
	private static final String KEY_APPLICATION_VERSION       = "Application.version"; //$NON-NLS-1$
	private static final String KEY_APPLICATION_BUILD         = "Application.build"; //$NON-NLS-1$
	private static final String KEY_APPLICATION_DEBUG         = "Application.debug"; //$NON-NLS-1$
	private static final String KEY_APPLICATION_WORKDIRECTORY = "Application.work_directory"; //$NON-NLS-1$

	private final Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();
	

    private Context() {
        super();
    }
	
    public static IContext getInstance() {
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


	public String getApplicationBuild() {
		String str = getDataString(KEY_APPLICATION_BUILD);
		
		if (!HelperGeneral.isValidString(str)) {
			str = "20080901";  //$NON-NLS-1$ //TODO change every release!
		}
		return str;
	}

	public boolean isApplicationDebug() {
    	Boolean value = (Boolean) contextData.get(KEY_APPLICATION_DEBUG);
    	return value != null ? value : false;
 	}

	public String getApplicationName() {
		String str = getDataString(KEY_APPLICATION_NAME);

		if (!HelperGeneral.isValidString(str)) {
			str = "Bogatyr"; //$NON-NLS-1$
		}
		return str;
	}

	public String getApplicationVersion() {
		String str = getDataString(KEY_APPLICATION_VERSION);

		if (!HelperGeneral.isValidString(str)) {
			str = "0.40";  //$NON-NLS-1$ //TODO change every release!
		}
		return str;
	}

	public File getApplicationWorkDirectory() {
		File file = (File)getData(KEY_APPLICATION_WORKDIRECTORY);

		if (file == null) {
			file = HelperEnvInfo.getOsTempDirectory();
		}
		return file;
	}

	public void setApplicationBuild(final String build) {
		addData(KEY_APPLICATION_BUILD, build);
	}

	public void setApplicationDebug(boolean isDebug) {
		addData(KEY_APPLICATION_BUILD, isDebug);
	}

	public void setApplicationName(String name) {
		addData(KEY_APPLICATION_NAME, name);
	}

	public void setApplicationVersion(String version) {
		addData(KEY_APPLICATION_VERSION, version);
	}

	public void setApplicationWorkDirectory(File directory) {
		addData(KEY_APPLICATION_WORKDIRECTORY, directory);
	}
	
	
    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(instance);
	}
}
