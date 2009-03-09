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

import java.io.File;



/**
 * Interface for the Context for all Bogatyr applications.
 * 
 * @author Stefan Laubenberger
 * @version 20090306
 */
public interface IContext {
	void addData(Object key, Object value);
	void removeData(Object key);
	Object getData(Object key);
	String getDataString(Object key);
	Boolean getDataBoolean(Object key);
    
//    /*
//     * Bogatyr specific
//     */
//	void setApplicationName(String name);
//	void setApplicationId(String id);
//	void setApplicationVersion(int version);
//	void setApplicationMinorVersion(int minorversion);
//	void setApplicationBuild(int build);
//	void setApplicationDebug(boolean isDebug);
//	void setApplicationWorkDirectory(File directory);
//	void setApplicationUpdateLocation(String updateLocation);
//
//	String getApplicationName();
//	String getApplicationId();
//	Integer getApplicationVersion();
//	Integer getApplicationMinorVersion();
//	Integer getApplicationBuild();
//	Boolean isApplicationDebug();
//	File getApplicationWorkDirectory();
//	String getApplicationUpdateLocation();
}
