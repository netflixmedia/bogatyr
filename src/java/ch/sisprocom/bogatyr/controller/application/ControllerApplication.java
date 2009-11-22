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
package ch.sisprocom.bogatyr.controller.application;

import ch.sisprocom.bogatyr.controller.Controller;
import ch.sisprocom.bogatyr.controller.worker.ControllerWorker;


/**
 * The interface for the application controller.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091121)
 * @since 0.9.0
 */
public interface ControllerApplication extends Controller {
	
	void run();
	void exit(final int returnCode);

	ch.sisprocom.bogatyr.model.application.ModelApplication getModel();
	
    ControllerWorker getControllerWorker();
    
    //TODO add public actions (e.g. save)
}
