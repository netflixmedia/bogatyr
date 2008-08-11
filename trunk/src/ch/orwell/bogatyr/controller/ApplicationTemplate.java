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
package ch.orwell.bogatyr.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ch.orwell.bogatyr.helper.HelperEnvInfo;
import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.context.Context;
import ch.orwell.bogatyr.helper.localizer.ILocalizer;
import ch.orwell.bogatyr.helper.localizer.Localizer;
import ch.orwell.bogatyr.helper.logger.ILogger;
import ch.orwell.bogatyr.helper.logger.Logger;
import ch.orwell.bogatyr.helper.logger.LoggerNull;
import ch.orwell.bogatyr.helper.property.IProperty;
import ch.orwell.bogatyr.helper.property.Property;
import ch.orwell.bogatyr.helper.property.PropertyStream;


/**
 * This is the skeleton for applications
 * 
 * @author Stefan Laubenberger
 * @version 20080808
 */
public abstract class ApplicationTemplate implements Runnable {
	// Resources
	private static final String	RES_LOG_START 	     = "Application.log.start"; //$NON-NLS-1$
	private static final String	RES_LOG_SUCCESSFUL 	 = "Application.log.successful"; //$NON-NLS-1$
	private static final String	RES_LOG_UNSUCCESSFUL = "Application.log.unsuccessful"; //$NON-NLS-1$
	private static final String	RES_LOG_OS 			 = "Application.log.os"; //$NON-NLS-1$
	private static final String	RES_LOG_VERSION 	 = "Application.log.version"; //$NON-NLS-1$
	private static final String	RES_LOG_ARCHITECTURE = "Application.log.architecture"; //$NON-NLS-1$

	// Properties
	private static final String PROPERTY_APPLICATION_NAME    		= "Application.name"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_VERSION 		= "Application.version"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_BUILD 		    = "Application.build"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_DEBUG          = "Application.debug"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_WORKDIRECTORY  = "Application.work_directory"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_LOCALIZER      = "Application.localizer"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_LOGGER         = "Application.logger"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_PROPERTIES     = "Application.properties"; //$NON-NLS-1$
	
//	private String name, version, build; //, workdirectory;
//	private File workdirectory;
//	private boolean isDebug;
	private final long startTime = System.currentTimeMillis();
	

	protected ApplicationTemplate(final String propertiesStreamName) throws IOException {
		super();
		
        // Properties
		final File file = new File(propertiesStreamName);
		InputStream is = null;
		try {
            if (file.exists()) {
                is = new FileInputStream(file);
            } else {
                is = new DataInputStream(ApplicationTemplate.class.getResourceAsStream(propertiesStreamName));
            }
            Property.setInstance(new PropertyStream(is));
		} finally {
			if (is != null) {
				is.close();
			}
        }

		init();
	}
	
	public long getStartTime() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getStartTime"); //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getStartTime", startTime); //$NON-NLS-1$

		return startTime;
	}

//	public String getName() {
//		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
//		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$
//
//		return name;
//	}
//
//	public String getVersion() {
//		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
//		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$
//
//		return version;
//	}
//
//	public String getBuild() {
//		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
//		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$
//
//		return build;
//	}
//
////	public String getWorkdirectory() {
////		return workdirectory;
////	}
//	
//	public File getWorkdirectory() {
//		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
//		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$
//		return workdirectory;
//	}
//	
//	public boolean isDebug() {
//		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
//		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$
//		return isDebug;
//	}
//	
//	public void setWorkdirectory(final File workdirectory) {
//		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword"); //$NON-NLS-1$
//		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password); //$NON-NLS-1$
//		this.workdirectory = workdirectory;
//		Logger.getInstance().writeDebug(this.getClass(), "setWorkdirectory", workdirectory);  //$NON-NLS-1$
//	}
//	
////	public void setWorkdirectory(final String workdirectory) {
////		this.workdirectory = workdirectory;
////	}

	/**
     * Terminates the application in a proper way 
     * 
     * @param returnCode System-Return-Code
     */	
	protected void exit(final int returnCode) {
		if (returnCode == 0) {
			Logger.getInstance().writeLog(this.getClass(), "exit", "##  " + Localizer.getInstance().getValue(RES_LOG_SUCCESSFUL) + "  ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			Logger.getInstance().writeLog(this.getClass(), "exit", "##  " + Localizer.getInstance().getValue(RES_LOG_UNSUCCESSFUL) + ' ' + returnCode + "  ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		System.exit(returnCode);
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		readProperties();

		Application.setInstance(this);

		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
		Logger.getInstance().writeLog(this.getClass(), "init", "##  " + Localizer.getInstance().getValue(RES_LOG_START) + ' ' + Context.getInstance().getApplicationName() + ' ' + Context.getInstance().getApplicationVersion() + " (" + Context.getInstance().getApplicationBuild() + ")  ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		Logger.getInstance().writeLog(this.getClass(), "init", Localizer.getInstance().getValue(RES_LOG_OS) + ' ' + HelperEnvInfo.getOsName() + " - " + Localizer.getInstance().getValue(RES_LOG_VERSION) + ' ' + HelperEnvInfo.getOsVersion()+ " - " + Localizer.getInstance().getValue(RES_LOG_ARCHITECTURE) + ' ' + HelperEnvInfo.getOsArch()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	private void readProperties() {
		File workdirectory;
		
		final boolean isDebug = Property.getInstance().getPropertyBoolean(PROPERTY_APPLICATION_DEBUG);
		Context.getInstance().addData(Context.KEY_APPLICATION_DEBUG, Boolean.valueOf(isDebug));
		
		String value = Property.getInstance().getProperty(PROPERTY_APPLICATION_NAME);
		if (HelperGeneral.isValidString(value)) {
//            name = value;
			Context.getInstance().addData(Context.KEY_APPLICATION_NAME, value);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_NAME + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(10);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_VERSION);
		if (HelperGeneral.isValidString(value)) {
//            version = value;
			Context.getInstance().addData(Context.KEY_APPLICATION_VERSION, value);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_VERSION + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(20);
		}

		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_BUILD);
		if (HelperGeneral.isValidString(value)) {
//            build = value;
			Context.getInstance().addData(Context.KEY_APPLICATION_BUILD, value);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_BUILD + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(30);
		}
		
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_WORKDIRECTORY);
		if (HelperGeneral.isValidString(value)) {
            workdirectory = new File(value);
		} else {
            workdirectory = HelperEnvInfo.getOsTempDirectory();
            if (isDebug) {
            	System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_WORKDIRECTORY + " == 'null' => default: " + workdirectory); //$NON-NLS-1$ //$NON-NLS-2$
            }
		}
		Context.getInstance().addData(Context.KEY_APPLICATION_WORKDIRECTORY, workdirectory);
		
		// Localizer
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_LOCALIZER);
		if (HelperGeneral.isValidString(value)) {
			try {
				Localizer.setInstance((ILocalizer)Class.forName(value).newInstance());
			} catch (Exception ex) {
				ex.printStackTrace();
				exit(40);
			}
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_LOCALIZER + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(41);
		}
		
		// Logger
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_LOGGER);
		if (HelperGeneral.isValidString(value)) {
			try {
				Logger.setInstance((ILogger)Class.forName(value).newInstance());
			} catch (Exception ex) {
				ex.printStackTrace();
				Logger.setInstance(new LoggerNull());
				System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_LOGGER + " == 'null' => default"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} else {
			Logger.setInstance(new LoggerNull());
			if (isDebug) {
				System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_LOGGER + " == 'null' => default"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		// Properties
		value = Property.getInstance().getProperty(PROPERTY_APPLICATION_PROPERTIES);
		if (HelperGeneral.isValidString(value)) {
			try {
				Property.setInstance((IProperty)Class.forName(value).newInstance());
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_APPLICATION_PROPERTIES + " == 'null' => default"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
