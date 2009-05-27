/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
 * @version 0.8.0 (20090527)
 * @since 0.6.0
 */
public interface ListenerUpdater {
	/**
	 * The update was successful.
	 * 
	 * @since 0.6.0
	 */
	void updateSuccessful();
	
	/**
	 * The update was cancelled.
	 * 
	 * @since 0.6.0
	 */
	void updateCancelled();
	
	/**
	 * The update was failed.
	 * 
	 * @param ex Exception containing details of the failure
	 * @since 0.6.0
	 */
	void updateFailed(IOException ex);
	
	/**
	 * The downgrade was successful.
	 * 
	 * @since 0.6.0
	 */
	void downgradeSuccessful();
	
	/**
	 * The downgrade was cancelled.
	 * 
	 * @since 0.6.0
	 */
	void downgradeCancelled();
	
	/**
	 * The downgrade was failed.
	 * 
	 * @param ex Exception containing details of the failure
	 * @since 0.6.0
	 */
	void downgradeFailed(IOException ex);
	
	/**
	 * The download was cancelled.
	 * 
	 * @since 0.6.0
	 */
	void downloadCancelled();
	
	/**
	 * The network was not available.
	 * 
	 * @since 0.6.0
	 */
	void networkNotAvailable();
}
