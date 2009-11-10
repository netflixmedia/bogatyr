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

import ch.sisprocom.bogatyr.controller.ApplicationAbstract;
import ch.sisprocom.bogatyr.service.localizer.LocalizerFile;
import ch.sisprocom.bogatyr.service.localizer.Localizer;
import ch.sisprocom.bogatyr.service.property.PropertyStream;
import ch.sisprocom.bogatyr.service.property.Property;
import ch.sisprocom.bogatyr.helper.HelperString;

import java.io.File;
import java.io.IOException;
import java.util.Locale;


/**
 * Simple HelloWorld example using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20091016
 */
public class HelloWorld extends ApplicationAbstract { 
	// Fixed parameter - e.g. this could be an argument
	private static final String	ARG_PROPERTY_LOCATION = "cfg/ch/sisprocom/bogatyr/sample/helloworld/standard.properties"; //$NON-NLS-1$
	
	// Properties
	private static final String	PROPERTY_LOCALIZER_BASE = "HelloWorld.localizerbase"; //$NON-NLS-1$
	private static final String	PROPERTY_USERNAME       = "HelloWorld.username"; //$NON-NLS-1$

	// Resources
	private static final String	RES_WELCOME = "HelloWorld.welcome"; //$NON-NLS-1$
	private static final String	RES_BYE     = "HelloWorld.bye"; //$NON-NLS-1$

	private Property property;
	private Localizer localizer;
	
	
	public static void main(final String[] args) {
		new HelloWorld();
	}
	
	public HelloWorld() {
		super();

		init();
		
		run();
	}

	/*
	 * Private methods
	 */
	private void init() {
		try {
			property = new PropertyStream(new File(ARG_PROPERTY_LOCATION));
		} catch (IOException ex) {
			System.err.println("Couldn't process the property file!"); //$NON-NLS-1$
			ex.printStackTrace();
			System.exit(1);
		}
		
		localizer = new LocalizerFile(property.getValue(PROPERTY_LOCALIZER_BASE));
	}

	/*
	 * Implemented methods
	 */
	@Override
    public void run() {
		final String username = property.getValue(PROPERTY_USERNAME);
		
		localizer.setLocale(Locale.GERMAN);
		System.out.println(localizer.getValue(RES_WELCOME) + HelperString.SPACE + username + '!');
		
		localizer.setLocale(Locale.ROOT);
		System.out.println(localizer.getValue(RES_WELCOME) + HelperString.SPACE + username + '!');

		localizer.setLocale(Locale.GERMAN);
		System.out.println(localizer.getValue(RES_BYE));
		
		localizer.setLocale(Locale.ROOT);
		System.out.println(localizer.getValue(RES_BYE));
		
		System.exit(0);
	}
}
