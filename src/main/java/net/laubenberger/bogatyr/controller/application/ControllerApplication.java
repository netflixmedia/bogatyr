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

package net.laubenberger.bogatyr.controller.application;

import java.util.Map;

import net.laubenberger.bogatyr.controller.Controller;
import net.laubenberger.bogatyr.model.application.ModelApplication;
import net.laubenberger.bogatyr.view.View;


/**
 * The interface for the application controller.
 *
 * @author Stefan Laubenberger
 * @version 0.9.5 (20110124)
 * @since 0.9.0
 */
public interface ControllerApplication<M extends ModelApplication, V extends View> extends Controller, Runnable {
	/**
	 * Exit the application.
	 * 
	 * @param returnCode of the application
	 * @since 0.9.0
	 */	
	void exit(final int returnCode);

	/**
    * Returns the model of the application.
    * 
    * @return model of the application
    * @since 0.9.0
    */	
	M getModel();
	
	/**
    * Returns the view of the application.
    * 
    * @return View of the application
    * @since 0.9.0
    */	
	V getView();
	
	/**
    * Sets the model of the application.
    * 
    * @param model for the application
    * @since 0.9.3
    */	
	void setModel(M model);
	
	/**
    * Sets the view of the application.
    * 
    * @param view for the application
    * @since 0.9.3
    */	
	void setView(V view);
	
	/**
	 * Returns a report about the current application.
	 *
	 * @return report about the current application
	 * @since 0.9.3
	 */
	Map<String, Object> getReport();
	
	//TODO add public actions (e.g. save)
}
