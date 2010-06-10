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

package helloworld;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import net.laubenberger.bogatyr.controller.ApplicationAbstract;
import net.laubenberger.bogatyr.helper.HelperCollection;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.helper.HelperTime;
import net.laubenberger.bogatyr.model.application.ModelApplication;
import net.laubenberger.bogatyr.model.application.ModelApplicationImpl;
import net.laubenberger.bogatyr.model.misc.Country;
import net.laubenberger.bogatyr.model.misc.Gender;
import net.laubenberger.bogatyr.model.misc.PersonImpl;
import net.laubenberger.bogatyr.model.misc.Role;
import net.laubenberger.bogatyr.service.localizer.LocalizerFile;
import net.laubenberger.bogatyr.service.property.PropertyImpl;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple HelloWorld example using the Bogatyr framework
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20100611
 */
public class HelloWorld extends ApplicationAbstract {
	private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

	// Fixed parameter - e.g. this could be an argument
	private static final String ARG_PROPERTY_LOCATION = "src/sample/configuration/helloworld/standard.properties"; //$NON-NLS-1$

	private static final ModelApplication MODEL;
	
	// Properties
	private static final String PROPERTY_LOCALIZER_BASE = "HelloWorld.localizerbase"; //$NON-NLS-1$
	private static final String PROPERTY_USERNAME = "HelloWorld.username"; //$NON-NLS-1$

	// Resources
	private static final String RES_WELCOME = "HelloWorld.welcome"; //$NON-NLS-1$
	private static final String RES_BYE = "HelloWorld.bye"; //$NON-NLS-1$

	static {
		PropertyConfigurator.configure("src/sample/configuration/log4j.properties"); //$NON-NLS-1$

		MODEL = new ModelApplicationImpl(
				"HelloWorld", new BigDecimal("0.92"), 266, HelperTime.getDate(2010, 5, 10, 0, 28, 0), null, null, null, null, null, false, null, null, null); //$NON-NLS-1$ //$NON-NLS-2$

		try {
			MODEL.setUrl(new URL("http://dev.laubenberger.net/bogatyr/")); //$NON-NLS-1$
			MODEL.addPerson(new PersonImpl("Laubenberger", "Stefan", HelperTime.getDate(1976, 12, 30), Gender.MALE, "Bullingerstrasse 53", "8004", "Zürich", Country.SWITZERLAND, "+41 1 401 27 43", null, "laubenberger@gmail.com", new URL("http://www.laubenberger.net"), null, HelperCollection.getList(Role.ADMINISTRATOR, Role.ARCHITECT, Role.DESIGNER, Role.DEVELOPER, Role.MANUFACTURER, Role.OWNER, Role.PROJECT_MANAGER, Role.PUBLISHER, Role.REVIEWER, Role.TESTER, Role.WRITER), null));   //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
		} catch (MalformedURLException ex) {
			// should never happen!
			if (log.isErrorEnabled()) log.error("URL invalid", ex); //$NON-NLS-1$
		}
	}

	public static void main(final String[] args) {
		final HelloWorld hw = new HelloWorld();
		hw.run();
	}

	public HelloWorld() {
		super(MODEL);

		init();
	}

	/*
	 * Private methods
	 */

	private void init() {
		try {
			MODEL.setProperty(new PropertyImpl(new File(ARG_PROPERTY_LOCATION)));
		} catch (IOException ex) {
			if (log.isErrorEnabled()) log.error("Could not process the property file", ex); //$NON-NLS-1$
			exit(1);
		}

		MODEL.setLocalizer(new LocalizerFile(MODEL.getProperty().getValue(PROPERTY_LOCALIZER_BASE)));
	}

	/*
	 * Implemented methods
	 */

	@Override
	public void run() {
		super.run();
		
		final String username = MODEL.getProperty().getValue(PROPERTY_USERNAME);

		MODEL.getLocalizer().setLocale(Locale.GERMAN);
		System.out.println(MODEL.getLocalizer().getValue(RES_WELCOME) + HelperString.SPACE + username + '!');

		MODEL.getLocalizer().setLocale(Locale.ROOT);
		System.out.println(MODEL.getLocalizer().getValue(RES_WELCOME) + HelperString.SPACE + username + '!');

		MODEL.getLocalizer().setLocale(Locale.GERMAN);
		System.out.println(MODEL.getLocalizer().getValue(RES_BYE));

		MODEL.getLocalizer().setLocale(Locale.ROOT);
		System.out.println(MODEL.getLocalizer().getValue(RES_BYE));

		exit(0);
	}
}
