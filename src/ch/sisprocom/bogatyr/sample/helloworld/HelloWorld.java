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
package ch.sisprocom.bogatyr.sample.helloworld;

import java.io.IOException;

import org.apache.log4j.Logger;

import ch.sisprocom.bogatyr.controller.ApplicationAbstract;
import ch.sisprocom.bogatyr.helper.localizer.Localizer;


/**
 * Simple HelloWorld example using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20081026
 */
public class HelloWorld extends ApplicationAbstract {
	private static final Logger log = Logger.getLogger(HelloWorld.class);
	
	// Resources
	private static final String	RES_TEXT  = "HelloWorld.text"; //$NON-NLS-1$

	
	public HelloWorld(String propertiesFileName) throws IOException {
		super(propertiesFileName);
		
		run();
	}

	public void run() {
		String text = Localizer.getInstance().getValue(RES_TEXT);
		
		log.info(text);

		exit(0);
	}
}
