/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.sisprocom.bogatyr.helper.context;

import java.io.File;



/**
 * Interface for the Application-Context
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public interface IContext {

	void addData(Object key, Object value);

	void removeData(Object key);

	Object getData(Object key);

	String getDataString(Object key);

//    double getDataDouble(Object key);
//
//    int getDataInt(Object key);
//
//    long getDataLong(Object key);
//
//    boolean getDataBoolean(Object key);
    
    /*
     * Bogatyr specific
     */
    String getApplicationName();

	String getApplicationVersion();

	String getApplicationBuild();
	
	boolean isApplicationDebug();

	File getApplicationWorkDirectory();
}
