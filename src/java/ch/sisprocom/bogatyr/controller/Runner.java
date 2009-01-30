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
package ch.sisprocom.bogatyr.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.context.Context;
import ch.sisprocom.bogatyr.helper.localizer.ILocalizer;
import ch.sisprocom.bogatyr.helper.localizer.Localizer;
import ch.sisprocom.bogatyr.helper.property.IProperty;
import ch.sisprocom.bogatyr.helper.property.Property;
import ch.sisprocom.bogatyr.helper.property.PropertyStream;


/**
 * This class starts every Bogatyr or "Runnable" application.
 * It handles the main functions like setup the logger, properties and localizer.
 *
 * @author Stefan Laubenberger
 * @version 20090127
 */
public abstract class Runner { //TODO document in Wiki!
	private static final Logger log = Logger.getRootLogger();
	
	// Properties
	private static final String PROPERTY_APPLICATION_NAME    		= "Application.name"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_ID             = "Application.id"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_VERSION 		= "Application.version"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_MINORVERSION   = "Application.minorversion"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_BUILD 		    = "Application.build"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_DEBUG          = "Application.debug"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_WORKDIRECTORY  = "Application.work_directory"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_LOCALIZER      = "Application.localizer"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_PROPERTIES     = "Application.properties"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_UPDATE         = "Application.update"; //$NON-NLS-1$

	
	/**
	 * The only main() method to run every runnable.
	 * 
	 * @param args The arguments given to start an application (at least the full qualified class name and the standard.properties)
	 */
	public static void main(final String[] args) {
		if (args.length == 2) { // checks the number of arguments - <Application> and <Properties> are needed
			try {
				init(args[1]);
				((Runnable) HelperGeneral.createObject(args[0], null)).run();
			} catch (NullPointerException ex) {
				System.err.println("Error: " + args[1] + " not found!");
				ex.printStackTrace();
				System.exit(97);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(98);
			}
		} else {
			System.err.println("Error: wrong number of arguments!"); //$NON-NLS-1$
			System.err.println("usage: Runner <Application> <Properties>"); //$NON-NLS-1$
			System.exit(99);
		}
	}

	private static void init(final String propertiesStreamName) throws IOException {
	    // Properties and logger
		final File file = new File(propertiesStreamName);
		InputStream is = null;
		try {
	        if (file.exists()) {
	            is = new FileInputStream(file);
	        } else {
	            is = new DataInputStream(ApplicationAbstract.class.getResourceAsStream(propertiesStreamName));
	        }
	        Property.setInstance(new PropertyStream(is));
	//        PropertyConfigurator.configureAndWatch(logProperties, 60 * 1000);
	        PropertyConfigurator.configure(Property.getInstance().getProperties());
		} finally {
			if (is != null) {
				is.close();
			}
	    }
		readProperties();
	}
	
	private static void readProperties() {
		File directory;
		
		final boolean isDebug = Property.getInstance().getPropertyBoolean(PROPERTY_APPLICATION_DEBUG);
		Context.getInstance().setApplicationDebug(isDebug);
		
		String value = Property.getInstance().getProperty(PROPERTY_APPLICATION_NAME);
		if (HelperGeneral.isValidString(value)) {
			Context.getInstance().setApplicationName(value);
		} else {
			log.error(PROPERTY_APPLICATION_NAME + " == 'null'"); //$NON-NLS-1$
			System.exit(1);
		}

		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_ID);
		if (HelperGeneral.isValidString(value)) {
			Context.getInstance().setApplicationId(value);
		} else {
			log.error(PROPERTY_APPLICATION_ID + " == 'null'"); //$NON-NLS-1$
			System.exit(2);
		}
		
		int number = Property.getInstance().getPropertyInt(PROPERTY_APPLICATION_VERSION);
		Context.getInstance().setApplicationVersion(number);

		number = Property.getInstance().getPropertyInt(PROPERTY_APPLICATION_MINORVERSION);
		Context.getInstance().setApplicationMinorVersion(number);

		number = Property.getInstance().getPropertyInt(PROPERTY_APPLICATION_BUILD);
		Context.getInstance().setApplicationBuild(number);
		
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_WORKDIRECTORY);
		if (HelperGeneral.isValidString(value)) {
			directory = new File(value);
		} else {
			directory = HelperEnvInfo.getOsTempDirectory();
            log.warn(PROPERTY_APPLICATION_WORKDIRECTORY + " == 'null' => default: " + directory.getAbsolutePath()); //$NON-NLS-1$
		}
		Context.getInstance().setApplicationWorkDirectory(directory);
		
		// Localizer
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_LOCALIZER);
		if (HelperGeneral.isValidString(value)) {
			try {
				Localizer.setInstance((ILocalizer)Class.forName(value).newInstance());
			} catch (Exception ex) {
				log.error("Construction of the localizer failed", ex); //$NON-NLS-1$
				System.exit(3);
			}
		} else {
			log.error(PROPERTY_APPLICATION_LOCALIZER + " == 'null'"); //$NON-NLS-1$
			System.exit(4);
		}
		
		// Properties
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_PROPERTIES);
		if (HelperGeneral.isValidString(value)) {
			try {
				Property.setInstance((IProperty)Class.forName(value).newInstance());
			} catch (Exception ex) {
				log.warn("Construction of the property handler failed - using default", ex); //$NON-NLS-1$
			}
		}
		
		// Update location
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_UPDATE);
		if (HelperGeneral.isValidString(value)) {
			Context.getInstance().setApplicationUpdateLocation(value);
		} else {
			log.warn(PROPERTY_APPLICATION_UPDATE + " == 'null' - update disabled"); //$NON-NLS-1$
		}
	}
}
