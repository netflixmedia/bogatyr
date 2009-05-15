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
package ch.sisprocom.bogatyr.controller;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is the skeleton for all Bogatyr applications.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class ApplicationAbstract implements Runnable {
	private final long startTime = System.currentTimeMillis();
	
	
	/**
     * Returns the start time of the application.
     * 
     * @return start time of the application
     */	
	public long getStartTime() {
		return startTime;
	}

	/**
     * Terminates the application in a proper way with a return code.
     * 
     * @param returnCode System-Return-Code
     */	
	public abstract void exit(final int returnCode);


	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
