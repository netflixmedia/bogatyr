/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.context.Context;


/**
 * This is the logger for stdout
 *  
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class LoggerNull implements ILogger {
	
	private boolean isDebug;

	private final DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$
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
	public void writeWarning(final Class<?> clazz, final String method, final Object logEntry) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.out.println(formattedDate + "|WARNING: " + Logger.getClassName(clazz) + "::" + method + " - " + HelperGeneral.toString(logEntry));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
 	}
	
	public void writeDebug(final Class<?> clazz, final String method, final Object logEntry) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.out.println(formattedDate + "|DEBUG: " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
 	}

	public void writeException(final Class<?> clazz, final String method, final Object logEntry, final Exception ex) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.err.println(formattedDate + "|ERROR: " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			if (ex != null) {
                ex.printStackTrace();
            }
		}
	}

	public void writeLog(final Class<?> clazz, final String method, final Object logEntry) {
		if (isDebug) {
            formattedDate = formatter.format(new Date());
			System.out.println(formattedDate + "|LOG: " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	public void writeMethodEntry(Class<?> clazz, String method, Object[] methodInput) {
		if (isDebug) {
			StringBuilder sb = new StringBuilder();
			sb.append("ENTRY:"); //$NON-NLS-1$
			
			for (Object obj : methodInput) {
				sb.append(obj);
				sb.append('|');
			}
			writeDebug(clazz, method, sb.toString());
		}		
	}
	
	public void writeMethodEntry(Class<?> clazz, String method, Object methodInput) {
		if (isDebug) {
			writeDebug(clazz, method, "ENTRY:" + methodInput); //$NON-NLS-1$
		}		
	}

	public void writeMethodEntry(Class<?> clazz, String method) {
		if (isDebug) {
			writeDebug(clazz, method, "ENTRY"); //$NON-NLS-1$
		}		
	}

	public void writeMethodExit(Class<?> clazz, String method, Object methodOutput) {
		if (isDebug) {
			writeDebug(clazz, method, "EXIT:" + methodOutput); //$NON-NLS-1$
		}		
	}
	
	public void writeMethodExit(Class<?> clazz, String method) {
		if (isDebug) {
			writeDebug(clazz, method, "EXIT"); //$NON-NLS-1$
		}		
	}
}
