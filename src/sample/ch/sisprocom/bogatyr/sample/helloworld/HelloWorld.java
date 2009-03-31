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
package ch.sisprocom.bogatyr.sample.helloworld;

import java.util.Locale;

import ch.sisprocom.bogatyr.controller.ApplicationAbstract;
import ch.sisprocom.bogatyr.controller.localizer.ControllerLocalizerFile;
import ch.sisprocom.bogatyr.controller.localizer.IControllerLocalizer;


/**
 * Simple HelloWorld example using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090305
 */
public class HelloWorld extends ApplicationAbstract { //TODO document in Wiki!
	// Resources
	private static final String	RES_TEXT  = "HelloWorld.text"; //$NON-NLS-1$

	private final IControllerLocalizer localizer;
	
	
	public static void main(final String[] args) {
		new HelloWorld();
	}
	
	public HelloWorld() {
		super();

		localizer = new ControllerLocalizerFile("res/ch/sisprocom/bogatyr/sample/helloworld/helloworld");
		run();
	}

	/*
	 * Implemented methods
	 */
	public void run() {
		localizer.setLocale(Locale.GERMAN);
		System.out.println(localizer.getValue(RES_TEXT));
		
		localizer.setLocale(Locale.ROOT);
		System.out.println(localizer.getValue(RES_TEXT));
		
		exit(0);
	}

	@Override
	public void exit(final int returnCode) {
		System.exit(returnCode);
	}
}
