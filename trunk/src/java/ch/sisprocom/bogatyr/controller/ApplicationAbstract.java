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

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.context.Context;
import ch.sisprocom.bogatyr.helper.localizer.Localizer;
import ch.sisprocom.bogatyr.helper.updater.Updater;


/**
 * This is the skeleton for all Bogatyr applications.
 * 
 * @author Stefan Laubenberger
 * @version 20081113
 */
public abstract class ApplicationAbstract implements Runnable { //TODO document in Wiki!
	private static final Logger log = Logger.getLogger(ApplicationAbstract.class);
	
	// Resources
	private static final String	RES_LOG_START 	     = "Application.log.start"; //$NON-NLS-1$
	private static final String	RES_LOG_SUCCESSFUL 	 = "Application.log.successful"; //$NON-NLS-1$
	private static final String	RES_LOG_UNSUCCESSFUL = "Application.log.unsuccessful"; //$NON-NLS-1$
	private static final String	RES_LOG_OS 			 = "Application.log.os"; //$NON-NLS-1$
	private static final String	RES_LOG_VERSION 	 = "Application.log.version"; //$NON-NLS-1$
	private static final String	RES_LOG_ARCHITECTURE = "Application.log.architecture"; //$NON-NLS-1$
	
	private final long startTime = System.currentTimeMillis();

	
	protected ApplicationAbstract() {
		super();
		init();
	}
	
	/**
     * Returns the start time of the application.
     * 
     * @return start time of the application
     */	
	public long getStartTime() {
		return startTime;
	}

	/**
     * Terminates the application in a proper way with a return code (between 30 and 90).
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
     * Checks the update XML file for the application and update it if needed.
     */	
	protected void update() throws SAXException, IOException, ParserConfigurationException {
		Updater.update();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		Application.setInstance(this);

		log.debug(toString());
		log.info(Localizer.getInstance().getValue(RES_LOG_START) + ' ' + Context.getInstance().getApplicationName() + ' ' + Context.getInstance().getApplicationVersion() + " (" + Context.getInstance().getApplicationBuild() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		log.info(Localizer.getInstance().getValue(RES_LOG_OS) + ' ' + HelperEnvInfo.getOsName() + " - " + Localizer.getInstance().getValue(RES_LOG_VERSION) + ' ' + HelperEnvInfo.getOsVersion()+ " - " + Localizer.getInstance().getValue(RES_LOG_ARCHITECTURE) + ' ' + HelperEnvInfo.getOsArch()); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
