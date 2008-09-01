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
package ch.sisprocom.bogatyr.helper.logger;


/**
 * Logger holder and helper
 *  
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class Logger {
	private static ILogger instance;
	   

    public static ILogger getInstance() {
    	if (instance == null) {
    		instance = new LoggerNull();
    	}
    	return instance;
	}

    public static void setInstance(final ILogger instance) {
    	Logger.instance = instance;
	}
    

	/**
     * Returns the class name
     * 
     * @param clazz caller class
     * @return validate/corrected descent
     */
	public static String getClassName(final Class<?> clazz) {
		if (clazz == null) {
			return "null"; //$NON-NLS-1$
		}
		return clazz.getName();
	}
}
