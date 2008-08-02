/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.orwell.bogatyr.helper.logger;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.context.Context;
import ch.orwell.bogatyr.helper.localizer.Localizer;


/**
 * This is the logger for stdout
 *  
 * @author Stefan Laubenberger
 * @version 20080724
 */
public class LoggerNull implements ILogger {
	
	private boolean isDebug ;

	private final DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Localizer.getInstance().getLocale()); //$NON-NLS-1$
	private String formattedDate;

	
	public LoggerNull() {
        super();
        init();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
        isDebug = Context.getInstance().isApplicationDebug();
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */
	public void writeWarning(final Object object, final String method, final String logEntry) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.out.println(formattedDate + "|WARNING: " + Logger.getClassName(object) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
 	}
	
	public void writeDebug(final Object object, final String method, final String logEntry) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.out.println(formattedDate + "|DEBUG: " + Logger.getClassName(object) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
 	}

	public void writeException(final Object object, final String method, final String logEntry, final Exception ex) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.err.println(formattedDate + "|ERROR: " + Logger.getClassName(object) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			if (ex != null) {
                ex.printStackTrace();
            }
		}
	}

	public void writeLog(final Object object, final String method, final String logEntry) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.out.println(formattedDate + "|LOG: " + Logger.getClassName(object) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
	}
}
