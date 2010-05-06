/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.controller;

import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObject;
import net.laubenberger.bogatyr.model.application.ModelApplication;


/**
 * Defines the methods for all Bogatyr based applications.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100506)
 * @since 0.9.0
 */
public interface Application extends ExtendedObject, Runnable {
	/**
	 * Exit the application.
	 * 
	 * @param returnCode of the application
	 * @since 0.9.2
	 */	
	void exit(int returnCode);
	
	/**
     * Returns the model of the application.
     * 
     * @return model of the application
     * @since 0.9.0
     */	
	ModelApplication getModel();
//	
//	/**
//     * Returns the view of the controller.
//     * 
//     * @return View of the controller
//     * @since 0.9.0
//     */	
//	<T extends View> T getView();
}