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
package ch.orwell.bogatyr.helper.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ch.orwell.bogatyr.helper.HelperIO;
import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.context.Context;
import ch.orwell.bogatyr.helper.localizer.Localizer;
import ch.orwell.bogatyr.helper.property.Property;


/**
 * This is the logger for file access
 *  
 * @author Stefan Laubenberger
 * @version 20080811
 */
public class LoggerFile implements ILogger {
	// Resources
//	private static final String	RES_INVALID_PATH = "LoggerFile.invalidPath"; //$NON-NLS-1$
	private static final String	RES_WRITE_FAILED = "LoggerFile.writeFailed"; //$NON-NLS-1$
	
	// Properties
	private static final String PROPERTY_LOGGER_PATH = "LoggerFile.path"; //$NON-NLS-1$
	
	private boolean isDebug;
	
	private String logFileName;
	private final DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$
	private String formattedDate;
	

	public LoggerFile() {
        super();
        init();
	}


	/*
	 * Private methods
	 */
	private void init() {
        isDebug = Context.getInstance().isApplicationDebug();
		
		readProperties();
	}

	private void readProperties() {
		String logPath = Property.getInstance().getProperty(PROPERTY_LOGGER_PATH);
		if (!HelperGeneral.isValidString(logPath)) {
			logPath = Context.getInstance().getApplicationWorkDirectory().getAbsolutePath();
			Context.getInstance().addData(PROPERTY_LOGGER_PATH, logPath);
		}

		final String logFile = Context.getInstance().getApplicationName();
        logFileName = logPath + logFile;
	}

	/**
     * Writes physically into the log file
     * 
     * @param clazz caller object
     * @param method caller method
     * @param logEntry Entry
     * @param file
	 * @throws IOException
     */
	private void write(final Class<?> clazz, final String method, final Object logEntry, final File file) throws IOException {
        formattedDate = formatter.format(new Date());
		HelperIO.writeLine(file, null, formattedDate + " - " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		try {
			write(clazz, method, logEntry, new File(logFileName + "_Warning.log")); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println(getClass().getName() + "::writeWarning - " + Localizer.getInstance().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			ex.printStackTrace();
		}
		if (isDebug) {
            System.out.println(formattedDate + "|WARNING: " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        }
 	}
	
	public void writeDebug(final Class<?> clazz, final String method, final Object logEntry) {
		if (isDebug) {
			try {
				write(clazz, method, logEntry, new File(logFileName + "_Debug.log")); //$NON-NLS-1$
				System.out.println(formattedDate + "|DEBUG: " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			} catch (IOException ex) {
				System.err.println(getClass().getName() + "::writeDebug - " + Localizer.getInstance().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
				ex.printStackTrace();
			}
		}
 	}

	public void writeException(final Class<?> clazz, final String method, final Object logEntry, final Exception ex) {
		try {
			final StringWriter sw = new StringWriter();
			if (ex != null) {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                } finally {
                	if (pw != null) {
                		pw.close();
                	}
                }
            }
			write(clazz, method, logEntry != null ? logEntry : "" + HelperGeneral.getLineSeparator() + sw.toString(), new File(logFileName + "_Exception.log")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			System.err.println(getClass().getName() + "::writeException - " + Localizer.getInstance().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			e.printStackTrace();
		}
		if (isDebug) {
			System.err.println(formattedDate + "|ERROR: " + Logger.getClassName(clazz) + "::" + method + " - " + logEntry);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			if (ex != null) {
                ex.printStackTrace();
            }
		}
	}

	public void writeLog(final Class<?> clazz, final String method, final Object logEntry) {
		try {
			write(clazz, method, logEntry, new File(logFileName + ".log")); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println(getClass().getName() + "::writeLog - " + Localizer.getInstance().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			ex.printStackTrace();
		}
		if (isDebug) {
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
