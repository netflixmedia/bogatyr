/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.sample.helloworld;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.log4j.PropertyConfigurator;

import ch.customcode.bogatyr.controller.ApplicationAbstract;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.service.localizer.Localizer;
import ch.customcode.bogatyr.service.localizer.LocalizerFile;
import ch.customcode.bogatyr.service.property.Property;
import ch.customcode.bogatyr.service.property.PropertyImpl;


/**
 * Simple HelloWorld example using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20100405
 */
public class HelloWorld extends ApplicationAbstract { 
	// Fixed parameter - e.g. this could be an argument
	private static final String	ARG_PROPERTY_LOCATION = "src/sample/configuration/ch/customcode/bogatyr/sample/helloworld/standard.properties"; //$NON-NLS-1$
	
	// Properties
	private static final String	PROPERTY_LOCALIZER_BASE = "HelloWorld.localizerbase"; //$NON-NLS-1$
	private static final String	PROPERTY_USERNAME       = "HelloWorld.username"; //$NON-NLS-1$

	// Resources
	private static final String	RES_WELCOME = "HelloWorld.welcome"; //$NON-NLS-1$
	private static final String	RES_BYE     = "HelloWorld.bye"; //$NON-NLS-1$

	private Property property;
	private Localizer localizer;
	
	
	public static void main(final String[] args) {
		PropertyConfigurator.configure("src/sample/configuration/ch/customcode/bogatyr/sample/helloworld/standard.properties");
		
		final HelloWorld hw = new HelloWorld();
		hw.run();
	}
	
	public HelloWorld() {
		super();

		init();
	}

	/*
	 * Private methods
	 */
	private void init() {
		try {
			property = new PropertyImpl(new File(ARG_PROPERTY_LOCATION));
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