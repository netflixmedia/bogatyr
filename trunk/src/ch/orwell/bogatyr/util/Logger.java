package ch.orwell.bogatyr.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.orwell.bogatyr.Application;
import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.io.FileManager;


/**
 * This is the logger for the hole framework (Singleton)
 * <p>
 * This is a design pattern implementation for the singleton pattern.<br>
 * ~~~ one of the musthaves for this semester.<br>
 * Look here: <a href="http://edu.panter.ch/static/SingletonPattern.html">http://edu.panter.ch/static/SingletonPattern.html</a>
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070624
 */
public final class Logger {
	private static Logger instance = null;

	// Resources
	private final static String	RES_UNKNOWN      = "Logger.unknown"; //$NON-NLS-1$
	private final static String	RES_WRITE_FAILED = "Logger.writeFailed"; //$NON-NLS-1$
	
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
     * If an instance of Logger exists it will give it back.<br>
     * Else it will generate one instance of Logger.
     * @return The only one Logger.
     */
    public static Logger getInstance() {
    	if(instance == null) {
    		instance = new Logger();
		}
    	return instance;
	}
    
	/**
     * Writes to the WARNING-file
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
     * @param descent Caller Class::Method
     * @param logEntry Entry
     * @param ex Exception
     */
	public void writeException(String descent, String logEntry, Exception ex) {
		try {
			write(descent, logEntry + " - " + ex.getMessage(), this.logFileName + "_Exception.log"); //$NON-NLS-1$ //$NON-NLS-2$
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
     * @param descent Caller Class::Method
     * @param logEntry Entry
     * @param fileName File name (with absolut path)
	 * @throws IOException 
     */
	private void write(String descent, String logEntry, String fileName) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); //$NON-NLS-1$
		FileManager.writeLine(fileName, null, new String(formatter.format(new Date()) + " - " + checkDescent(descent) + " - " + checkLogEntry(logEntry))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void readProperties() {
		String logPath = Context.getInstance().getProperties().getProperty(PROPERTY_LOGGER_PATH);
		if (!GeneralHelper.isValidString(logPath)) {
			System.err.println("Logger::readProperties - " + PROPERTY_LOGGER_PATH + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			Application.exit(300);
		}

		String logFile = Context.getInstance().getProperties().getProperty(PROPERTY_LOGGER_FILE);
		if (!GeneralHelper.isValidString(logFile)) {
			System.err.println("Logger::readProperties - " + PROPERTY_LOGGER_FILE + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			Application.exit(400);
		}
		
		this.logFileName = (logPath + logFile);
	}
}
