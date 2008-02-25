/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.orwell.bogatyr.Application;
import ch.orwell.bogatyr.Context;


/**
 * This is the logger for the hole framework
 *  
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080128
 */
public final class Logger {
	private static Logger instance = null;

	// Resources
	private static final String	RES_INVALID_PATH = "Logger.invalidPath"; //$NON-NLS-1$
	private static final String	RES_UNKNOWN      = "Logger.unknown"; //$NON-NLS-1$
	private static final String	RES_WRITE_FAILED = "Logger.writeFailed"; //$NON-NLS-1$
	
	// Properties
	private static final String PROPERTY_LOGGER_PATH = "logger.path"; //$NON-NLS-1$
	private static final String PROPERTY_LOGGER_FILE = "logger.file"; //$NON-NLS-1$
	
	private String logFileName;
	
	/**
	 * Constructs only one Logger.
	 */
	private Logger() {
		this.readProperties();
	}
	
    /**
     * If an instance of Logger exists it will give it back.
     * Else it will generate one instance of Logger.
     * 
     * @return The only one Logger.
     */
    public static synchronized Logger getInstance() {
    	if(instance == null) {
    		instance = new Logger();
		}
    	return instance;
	}
    
	/**
     * Writes to the WARNING-file
     * 
     * @param descent Caller Class::Method
     * @param logEntry Entry
     */
	public void writeWarning(String descent, String logEntry) {
		try {
			write(descent, logEntry, this.logFileName + "_Warning.log"); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println("Logger::writeWarning - " + Context.getInstance().getLocalizer().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			ex.printStackTrace();
			Application.exit(666);
		}
		if (Context.getInstance().isDebug()) System.out.println("#WARNING: " + checkDescent(descent) + " - " + checkLogEntry(logEntry));  //$NON-NLS-1$//$NON-NLS-2$
 	}
	
	/**
     * Writes to the DEBUG-file
     * 
     * @param descent Caller Class::Method
     * @param logEntry Entry
     */
	public void writeDebug(String descent, String logEntry) {
		if (Context.getInstance().isDebug())
			try {
				write(descent, logEntry, this.logFileName + "_Debug.log"); //$NON-NLS-1$
				System.out.println("#DEBUG: " + checkDescent(descent) + " - " + checkLogEntry(logEntry));  //$NON-NLS-1$//$NON-NLS-2$
			} catch (IOException ex) {
				System.err.println("Logger::writeDebug - " + Context.getInstance().getLocalizer().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
				ex.printStackTrace();
				Application.exit(666);
			}
 	}

	/**
     * Writes to the EXCEPTION-file
     * 
     * @param descent Caller Class::Method
     * @param logEntry Entry
     * @param ex Exception
     */
	public void writeException(String descent, String logEntry, Exception ex) {
		try {
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			write(descent, logEntry + "\n" + sw.toString(), this.logFileName + "_Exception.log"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			System.err.println("Logger::writeException - " + Context.getInstance().getLocalizer().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			e.printStackTrace();
			Application.exit(666);
		}
		if (Context.getInstance().isDebug()) {
			System.err.println("#ERROR: " + checkDescent(descent) + " - " + checkLogEntry(logEntry));  //$NON-NLS-1$//$NON-NLS-2$
			ex.printStackTrace();
		}
	}

	/**
     * Writes to the EXCEPTION-file
     * 
     * @param descent Caller Class::Method
     * @param logEntry Entry
     */
	public void writeException(String descent, String logEntry) {
		try {
			write(descent, logEntry, this.logFileName + "_Exception.log"); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println("Logger::writeException - " + Context.getInstance().getLocalizer().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			ex.printStackTrace();
			Application.exit(666);
		}
		if (Context.getInstance().isDebug()) System.err.println("#ERROR: " + checkDescent(descent) + " - " + checkLogEntry(logEntry));  //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
     * Writes to the Log-file
     * 
     * @param descent Caller Class::Method
     * @param logEntry Entry
     */
	public void writeLog(String descent, String logEntry) {
		try {
			write(descent, logEntry, this.logFileName + ".log"); //$NON-NLS-1$
		} catch (IOException ex) {
			System.err.println("Logger::writeLog - " + Context.getInstance().getLocalizer().getValue(RES_WRITE_FAILED)); //$NON-NLS-1$
			ex.printStackTrace();
			Application.exit(666);
		}
		if (Context.getInstance().isDebug()) System.out.println("#LOG: " + checkDescent(descent) + " - " + checkLogEntry(logEntry));  //$NON-NLS-1$//$NON-NLS-2$
	}
	
	
	/*
	 * Private methods
	 */
	/**
     * Checks the entry
     * 
     * @param logEntry Entry
     * @return validate/corrected entry
     */
	private String checkLogEntry(String logEntry) {
		if (logEntry == null) {
			return "???"; //$NON-NLS-1$
		}
		return logEntry;
	}

	/**
     * Checks the descent
     * 
     * @param descent Caller Class::Method
     * @return validate/corrected descent
     */
	private String checkDescent(String descent) {
		if (descent == null) {
			return Context.getInstance().getLocalizer().getValue(RES_UNKNOWN);
		}
		return descent;
	}

	/**
     * Writes physically into the log file
     * 
     * @param descent Caller Class::Method
     * @param logEntry Entry
     * @param fileName File name (with absolut path)
	 * @throws IOException 
     */
	private void write(String descent, String logEntry, String fileName) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); //$NON-NLS-1$
		FileManager.writeLine(fileName, null, new String(formatter.format(new Date()) + " - " + checkDescent(descent) + " - " + checkLogEntry(logEntry))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void readProperties() {
		String logPath = Context.getInstance().getProperties().getProperty(PROPERTY_LOGGER_PATH);
		if (!GeneralHelper.isValidString(logPath)) {
			System.err.println("Logger::readProperties - " + PROPERTY_LOGGER_PATH + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			Application.exit(200);
		}

		// be sure, that the path really exists
		if (!(new File(logPath)).exists()) {
			System.err.println("Logger::readProperties - " + PROPERTY_LOGGER_PATH + " " + Context.getInstance().getLocalizer().getValue(RES_INVALID_PATH)); //$NON-NLS-1$ //$NON-NLS-2$
			Application.exit(210);
		}

		String logFile = Context.getInstance().getProperties().getProperty(PROPERTY_LOGGER_FILE);
		if (!GeneralHelper.isValidString(logFile)) {
			System.err.println("Logger::readProperties - " + PROPERTY_LOGGER_FILE + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			Application.exit(220);
		}
		this.logFileName = (logPath + logFile);
	}
}
