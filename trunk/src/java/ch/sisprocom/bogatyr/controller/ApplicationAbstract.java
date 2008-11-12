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

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import ch.sisprocom.bogatyr.controller.updater.Updater;
import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.context.Context;
import ch.sisprocom.bogatyr.helper.localizer.Localizer;


/**
 * This is the skeleton for applications
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class ApplicationAbstract implements Runnable {
	private static final Logger log = Logger.getLogger(ApplicationAbstract.class);
	
	// Resources
	private static final String	RES_LOG_START 	     = "Application.log.start"; //$NON-NLS-1$
	private static final String	RES_LOG_SUCCESSFUL 	 = "Application.log.successful"; //$NON-NLS-1$
	private static final String	RES_LOG_UNSUCCESSFUL = "Application.log.unsuccessful"; //$NON-NLS-1$
	private static final String	RES_LOG_OS 			 = "Application.log.os"; //$NON-NLS-1$
	private static final String	RES_LOG_VERSION 	 = "Application.log.version"; //$NON-NLS-1$
	private static final String	RES_LOG_ARCHITECTURE = "Application.log.architecture"; //$NON-NLS-1$

//	// Properties
//	private static final String PROPERTY_APPLICATION_NAME    		= "Application.name"; //$NON-NLS-1$
//	private static final String PROPERTY_APPLICATION_VERSION 		= "Application.version"; //$NON-NLS-1$
//	private static final String PROPERTY_APPLICATION_BUILD 		    = "Application.build"; //$NON-NLS-1$
//	private static final String PROPERTY_APPLICATION_DEBUG          = "Application.debug"; //$NON-NLS-1$
//	private static final String PROPERTY_APPLICATION_WORKDIRECTORY  = "Application.work_directory"; //$NON-NLS-1$
//	private static final String PROPERTY_APPLICATION_LOCALIZER      = "Application.localizer"; //$NON-NLS-1$
//	private static final String PROPERTY_APPLICATION_PROPERTIES     = "Application.properties"; //$NON-NLS-1$
	
	private final long startTime = System.currentTimeMillis();

	
	protected ApplicationAbstract() {
		super();
		init();
	}
	
	/**
     * Returns the start time of the application 
     * 
     * @return start time of the application
     */	
	public long getStartTime() {
		return startTime;
	}

	/**
     * Terminates the application in a proper way 
     * 
     * @param returnCode System-Return-Code
     */	
	public void exit(final int returnCode) {
		if (returnCode == 0) {
			log.info(Localizer.getInstance().getValue(RES_LOG_SUCCESSFUL));
		} else {
			log.warn(Localizer.getInstance().getValue(RES_LOG_UNSUCCESSFUL) + ' ' + returnCode);
		}
		System.exit(returnCode);
	}

	/**
     * Checks the updates for the application and update it if needed 
     */	
	protected void update() throws SAXException, IOException, ParserConfigurationException {
		Updater.update();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
//        // Properties and logger
//		final File file = new File(propertiesStreamName);
//		InputStream is = null;
//		try {
//            if (file.exists()) {
//                is = new FileInputStream(file);
//            } else {
//                is = new DataInputStream(ApplicationAbstract.class.getResourceAsStream(propertiesStreamName));
//            }
//            Property.setInstance(new PropertyStream(is));
////            PropertyConfigurator.configureAndWatch(logProperties, 60 * 1000);
//            PropertyConfigurator.configure(Property.getInstance().getProperties());
//            log.debug("Logging initialized.");
//            log.debug(Property.getInstance().toString());
//		} finally {
//			if (is != null) {
//				is.close();
//			}
//        }

//		readProperties();

		Application.setInstance(this);

		log.debug(toString());
		log.info(Localizer.getInstance().getValue(RES_LOG_START) + ' ' + Context.getInstance().getApplicationName() + ' ' + Context.getInstance().getApplicationVersion() + " (" + Context.getInstance().getApplicationBuild() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		log.info(Localizer.getInstance().getValue(RES_LOG_OS) + ' ' + HelperEnvInfo.getOsName() + " - " + Localizer.getInstance().getValue(RES_LOG_VERSION) + ' ' + HelperEnvInfo.getOsVersion()+ " - " + Localizer.getInstance().getValue(RES_LOG_ARCHITECTURE) + ' ' + HelperEnvInfo.getOsArch()); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
//	private void readProperties() {
//		File directory;
//		
//		final boolean isDebug = Property.getInstance().getPropertyBoolean(PROPERTY_APPLICATION_DEBUG);
//		Context.getInstance().setApplicationDebug(isDebug);
//		
//		String value = Property.getInstance().getProperty(PROPERTY_APPLICATION_NAME);
//		if (HelperGeneral.isValidString(value)) {
//			Context.getInstance().setApplicationName(value);
//		} else {
//			log.error(PROPERTY_APPLICATION_NAME + " == 'null'"); //$NON-NLS-1$
//			exit(10);
//		}
//		
//		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_VERSION);
//		if (HelperGeneral.isValidString(value)) {
//			Context.getInstance().setApplicationVersion(value);
//		} else {
//			log.error(PROPERTY_APPLICATION_VERSION + " == 'null'"); //$NON-NLS-1$
//			exit(20);
//		}
//
//		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_BUILD);
//		if (HelperGeneral.isValidString(value)) {
//			Context.getInstance().setApplicationBuild(value);
//		} else {
//			log.error(PROPERTY_APPLICATION_BUILD + " == 'null'"); //$NON-NLS-1$
//			exit(30);
//		}
//		
//		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_WORKDIRECTORY);
//		if (HelperGeneral.isValidString(value)) {
//			directory = new File(value);
//		} else {
//			directory = HelperEnvInfo.getOsTempDirectory();
//            log.warn(PROPERTY_APPLICATION_WORKDIRECTORY + " == 'null' => default: " + directory.getAbsolutePath()); //$NON-NLS-1$
//		}
//		Context.getInstance().setApplicationWorkDirectory(directory);
//		
//		// Localizer
//		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_LOCALIZER);
//		if (HelperGeneral.isValidString(value)) {
//			try {
//				Localizer.setInstance((ILocalizer)Class.forName(value).newInstance());
//			} catch (Exception ex) {
//				log.error("Construction of the localizer failed", ex); //$NON-NLS-1$
//				exit(40);
//			}
//		} else {
//			log.error(PROPERTY_APPLICATION_LOCALIZER + " == 'null'"); //$NON-NLS-1$
//			exit(41);
//		}
//		
//		// Properties
//		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_PROPERTIES);
//		if (HelperGeneral.isValidString(value)) {
//			try {
//				Property.setInstance((IProperty)Class.forName(value).newInstance());
//			} catch (Exception ex) {
//				log.warn("Construction of the property handler failed - using default", ex); //$NON-NLS-1$
//			}
//		}
//	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
