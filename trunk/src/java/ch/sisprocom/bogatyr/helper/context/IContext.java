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

import ch.sisprocom.bogatyr.model.dao.User;



/**
 * Interface for the Application-Context
 * 
 * @author Stefan Laubenberger
 * @version 20081112
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
	int getApplicationVersion();
	int getApplicationMinorVersion();
	int getApplicationBuild();
	boolean isApplicationDebug();
	File getApplicationWorkDirectory();
	User getApplicationUser();
	String getApplicationUpdateLocation();

	void setApplicationName(String name);
	void setApplicationVersion(int version);
	void setApplicationMinorVersion(int minorversion);
	void setApplicationBuild(int build);
	void setApplicationDebug(boolean isDebug);
	void setApplicationWorkDirectory(File directory);
	void setApplicationUser(User user);
	void setApplicationUpdateLocation(String updateLocation);
}
