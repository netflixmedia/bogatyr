/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import ch.sisprocom.bogatyr.model.misc.Document;



/**
 * This is a helper class for logging.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100321)
 * @since 0.9.1
 */
public abstract class HelperLog {
	private static final String ID_APPLICATION_START = "+++";
	private static final String ID_APPLICATION_EXIT = "---";
	private static final String ID_METHOD_START = ">>>";
	private static final String ID_METHOD_EXIT = "<<<";
	
//	public static void trace(final Logger logger, String message) {
//		if (logger.isTraceEnabled()) {
//			logger.trace('<' + getCaller().getMethodName() + ">: " + message);
//		}
//	}	
//
//	public static void trace(final Logger logger, String message, Object... args) {
//		if (logger.isTraceEnabled()) {
//			logger.trace('<' + getCaller().getMethodName() + ">: " + message, args);
//		}
//	}	
//
//	public static void trace(final Logger logger, String message, Throwable t) {
//		if (logger.isTraceEnabled()) {
//			logger.trace('<' + getCaller().getMethodName() + ">: " + message, t);
//		}
//	}	
//
//	public static void debug(final Logger logger, String message) {
//		if (logger.isDebugEnabled()) {
//			logger.debug('<' + getCaller().getMethodName() + ">: " + message);
//		}
//	}	
//
//	public static void debug(final Logger logger, String message, Object... args) {
//		if (logger.isDebugEnabled()) {
//			logger.debug('<' + getCaller().getMethodName() + ">: " + message, args);
//		}
//	}
//
//	public static void debug(final Logger logger, String message, Throwable t) {
//		if (logger.isDebugEnabled()) {
//			logger.debug('<' + getCaller().getMethodName() + ">: " + message, t);
//		}
//	}	
//
//	public static void info(final Logger logger, String message) {
//		if (logger.isInfoEnabled()) {
//			logger.info('<' + getCaller().getMethodName() + ">: " + message);
//		}
//	}	
//
//	public static void info(final Logger logger, String message, Object... args) {
//		if (logger.isInfoEnabled()) {
//			logger.info('<' + getCaller().getMethodName() + ">: " + message, args);
//		}
//	}	
//
//	public static void info(final Logger logger, String message, Throwable t) {
//		if (logger.isInfoEnabled()) {
//			logger.info('<' + getCaller().getMethodName() + ">: " + message, t);
//		}
//	}	
//	
//	public static void warn(final Logger logger, String message) {
//		if (logger.isWarnEnabled()) {
//			logger.warn('<' + getCaller().getMethodName() + ">: " + message);
//		}
//	}	
//
//	public static void warn(final Logger logger, String message, Object... args) {
//		if (logger.isWarnEnabled()) {
//			logger.warn('<' + getCaller().getMethodName() + ">: " + message, args);
//		}
//	}	
//
//	public static void warn(final Logger logger, String message, Throwable t) {
//		if (logger.isWarnEnabled()) {
//			logger.warn('<' + getCaller().getMethodName() + ">: " + message, t);
//		}
//	}	
//	
//	public static void error(final Logger logger, String message) {
//		if (logger.isErrorEnabled()) {
//			logger.error('<' + getCaller().getMethodName() + ">: " + message);
//		}
//	}	
//
//	public static void error(final Logger logger, String message, Object... args) {
//		if (logger.isErrorEnabled()) {
//			logger.error('<' + getCaller().getMethodName() + ">: " + message, args);
//		}
//	}	
//
//	public static void error(final Logger logger, String message, Throwable t) {
//		if (logger.isErrorEnabled()) {
//			logger.error('<' + getCaller().getMethodName() + ">: " + message, t);
//		}
//	}	
	
	public static String applicationStart(Document document) {
		return ID_APPLICATION_START + HelperString.SPACE + document.getName() + HelperString.SPACE + document.getVersion() + " (" + document.getBuild() + ") " + ID_APPLICATION_START;
	}

	public static String applicationExit(Document document, int returnCode) {
		return ID_APPLICATION_EXIT + HelperString.SPACE + document.getName() + ": " + returnCode + HelperString.SPACE + ID_APPLICATION_EXIT;
	}

	public static String methodStart() {
		return ID_METHOD_START;
	}

	public static String methodStart(Object[] args) {
		final StringBuilder sb = new StringBuilder();
		sb.append(ID_METHOD_START);
		
		for (Object obj : args) {
            if (sb.length() > ID_METHOD_START.length()) {
                sb.append(HelperString.COMMA);
            }
            sb.append(HelperString.SPACE + String.valueOf(obj));
//            sb.append(HelperString.SPACE + HelperObject.toString(obj));
		}
		
		return sb.toString();
	}

	public static String methodExit() {
		return ID_METHOD_EXIT;
 	}

	public static String methodExit(final Object arg) {
		return ID_METHOD_EXIT + HelperString.SPACE + String.valueOf(arg);
	}

//	private static StackTraceElement getCaller() {
//		final Throwable t = new Throwable();
//		final StackTraceElement[] elements = t.getStackTrace();
//		
//		return elements[3];
//	}
}