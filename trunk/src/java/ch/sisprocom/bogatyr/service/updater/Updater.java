/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.service.updater;

import ch.sisprocom.bogatyr.service.Service;



/**
 * Defines the methods for the implementation of the updater.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.6.0
 */
public interface Updater extends Service {
	/**
	 * Checks the update information for new versions an update the application if needed.
	 * 
	 * @param name Name of the application
	 * @param id Unique id of the application
	 * @param version Version of the application
	 * @param minorversion Minor version of the application
	 * @param build Build of the application
	 * @param updateLocation Location for the update information
     * @throws Exception
     * @since 0.6.0
	 */
	void update(String name, String id, int version, int minorversion, int build, String updateLocation) throws Exception;

	/**
	 * Adds a listener for this updater.
	 * 
	 * @param listener to add
	 * @since 0.6.0
	 */
	void addListener(ListenerUpdater listener);
	
	/**
	 * Remove a listener for this updater.
	 * 
	 * @param listener to remove
	 * @since 0.6.0
	 */
	void removeListener(ListenerUpdater listener);

	/**
	 * Remove all listeners for this updater.
	 * 
	 * @since 0.6.0 
	 */
	void removeAllListener();
}   

