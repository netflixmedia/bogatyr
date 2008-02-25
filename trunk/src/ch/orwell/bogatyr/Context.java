/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.PropertiesManager;


/**
 * Application-Context
 * Get access for general content in the context.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public class Context {
	private static Context instance = null;
   
	private Map<String, Object> contextData;
	
	/**
	 * Constructs only one Context.
	 */
    private Context() {
    	this.contextData = new ConcurrentHashMap<String, Object>();
    }
	
    /**
     * If an instance of Context exists it will give it back.<br>
     * Else it will generate one instance of Context.
     * @return The only one Context.
     */
    public static synchronized Context getInstance() {
    	if(instance == null) {
    		instance = new Context();
		}
    	return instance;
	}

	public void addData(String key, Object value) {
		this.contextData.put(key, value);
	}

	public void removeData(String key) {
	    this.contextData.remove(key);
	}

	public Object getData(String key) {
	    return this.contextData.get(key);
	}

	public String getDataString(String key) {
        return (String)this.contextData.get(key);
    }

    public double getDataDouble(String key) {
        return ((Double)this.contextData.get(key)).doubleValue();
    }

    public int getDataInt(String key) {
       return ((Integer)this.contextData.get(key)).intValue();
    }

    public long getDataLong(String key) {
        return ((Long)this.contextData.get(key)).longValue();
    }

    public boolean getDataBoolean(String key) {
    	return ((Boolean)this.contextData.get(key)).booleanValue();
    }
    
    /*
     * Application-specific
     */
	public String getName() {
		return getDataString(Application.ATT_APPLICATION_NAME);
	}

	public String getVersion() {
		return getDataString(Application.ATT_APPLICATION_VERSION);
	}
	
	public boolean isDebug() {
		return getDataBoolean(Application.ATT_APPLICATION_DEBUG);
	}

	public PropertiesManager getProperties() {
		return (PropertiesManager)getData(Application.ATT_APPLICATION_PROPERTIES);
	}

	public Localizer getLocalizer() {
		return (Localizer)getData(Application.ATT_APPLICATION_LOCALIZER);
	}

	public String getWorkDirectory() {
		return getDataString(Application.ATT_APPLICATION_WORKDIRECTORY);
	}
}
