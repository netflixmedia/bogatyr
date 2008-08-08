/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.orwell.bogatyr.sample.helloworld;

import java.io.IOException;

import ch.orwell.bogatyr.controller.ApplicationTemplate;
import ch.orwell.bogatyr.helper.localizer.Localizer;
import ch.orwell.bogatyr.helper.logger.Logger;

/**
 * Simple HelloWorld example using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080802
 */
public class HelloWorld extends ApplicationTemplate {
	// Resources
	private final static String	RES_TEXT  = "HelloWorld.text"; //$NON-NLS-1$

	
	public HelloWorld(String propertiesFileName) throws IOException {
		super(propertiesFileName);
		run();
	}

	public void run() {
		String text = Localizer.getInstance().getValue(RES_TEXT);
		
		System.out.println(text);
		
		Logger.getInstance().writeLog(this.getClass(), "run", "## " + text + " ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		exit(0);
	}
}
