/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.updater;

import java.io.IOException;

/**
 * ListenerUpdater
 * 
 * @author Stefan Laubenberger
 * @version 20090301
 */
public interface ListenerUpdater {
	/**
	 * The update was successful.
	 */
	void updateSuccessful();
	
	/**
	 * The update was cancelled.
	 */
	void updateCancelled();
	
	/**
	 * The update was failed.
	 * 
	 * @param ex Exception containing details of the failure
	 */
	void updateFailed(IOException ex);
	
	/**
	 * The downgrade was successful.
	 */
	void downgradeSuccessful();
	
	/**
	 * The downgrade was cancelled.
	 */
	void downgradeCancelled();
	
	/**
	 * The downgrade was failed.
	 * 
	 * @param ex Exception containing details of the failure
	 */
	void downgradeFailed(IOException ex);
	
	/**
	 * The download was cancelled.
	 */
	void downloadCancelled();
	
	/**
	 * The network was not available.
	 */
	void networkNotAvailable();
}
