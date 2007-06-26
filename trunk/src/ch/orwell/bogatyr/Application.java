package ch.orwell.bogatyr;

import java.util.Locale;

import ch.orwell.bogatyr.util.EnvInfo;
import ch.orwell.bogatyr.util.GeneralHelper;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;
import ch.orwell.bogatyr.util.PropertiesManager;


/**
 * This is the skeleton for all applications
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070624
 */
public abstract class Application implements Runnable {
	protected static String className = null;
	protected final long startTime = System.currentTimeMillis();
	
	// Attributes
	public static final String ATT_APPLICATION_NAME    		 = "Application.name"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_VERSION 		 = "Application.version"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_DEBUG         = "Application.debug"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_WORKDIRECTORY = "Application.work_directory"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_PROPERTIES    = "Application.properties"; //$NON-NLS-1$
	public static final String ATT_APPLICATION_LOCALIZER     = "Application.localizer"; //$NON-NLS-1$

	// Resources
	private final static String	RES_LOG_START 	     = "Application.log.start"; //$NON-NLS-1$
	private final static String	RES_LOG_SUCCESSFUL 	 = "Application.log.successful"; //$NON-NLS-1$
	private final static String	RES_LOG_UNSUCCESSFUL = "Application.log.unsuccessful"; //$NON-NLS-1$
	private final static String	RES_LOG_OS 			 = "Application.log.os"; //$NON-NLS-1$
	private final static String	RES_LOG_VERSION 	 = "Application.log.version"; //$NON-NLS-1$
	private final static String	RES_LOG_ARCHITECTURE = "Application.log.architecture"; //$NON-NLS-1$

	// Properties
	private static final String PROPERTY_APPLICATION_NAME    		= "application.name"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_VERSION 		= "application.version"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_DEBUG          = "application.debug"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_WORKDIRECTORY  = "application.work_directory"; //$NON-NLS-1$
	private static final String PROPERTY_APPLICATION_LOCALIZER_BASE = "application.localizer.base"; //$NON-NLS-1$

	private static Localizer localizer;

	private String localizerBase;
	private Logger logger;
	private Context context;
	
	/**
	 * Constructs an Application.
	 * @param propertiesFileName Properties file for the server configuration.
	 */
	public Application(String propertiesFileName) {
		this.context = Context.getInstance();
		
		// PropertiesManager
		this.context.addData(ATT_APPLICATION_PROPERTIES, new PropertiesManager(propertiesFileName)); 
		
		init();
	}
	
	/**
     * Terminates the application in a proper way 
     * @param returnCode System-Return-Code
     */	
	public final static void exit(int returnCode) {
		if (returnCode == 0) {
			Logger.getInstance().writeLog(className + "::exit", "## " + localizer.getValue(RES_LOG_SUCCESSFUL) + " ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			Logger.getInstance().writeLog(className + "::exit", "## " + localizer.getValue(RES_LOG_UNSUCCESSFUL) + " " + returnCode + " ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		System.exit(returnCode);
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		className = this.getClass().getName();
		
		readProperties();

		// Localizer
		localizer = new Localizer(this.localizerBase, Locale.getDefault());
		this.context.addData(ATT_APPLICATION_LOCALIZER, localizer);

		// Logger
		this.logger = Logger.getInstance();
		this.logger.writeLog(className + "::init", "#  " + localizer.getValue(RES_LOG_START) + " " + this.context.getName() + " " + this.context.getVersion()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		this.logger.writeLog(className + "::init", "#  " + localizer.getValue(RES_LOG_OS) + " " + EnvInfo.getOsName() + " - " + localizer.getValue(RES_LOG_VERSION) + " " + EnvInfo.getOsVersion()+ " - " + localizer.getValue(RES_LOG_ARCHITECTURE) + " " + EnvInfo.getOsArch()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	}
	
	private void readProperties() {
		this.context.addData(ATT_APPLICATION_NAME, this.context.getProperties().getProperty(PROPERTY_APPLICATION_NAME));
		if (!GeneralHelper.isValidString(this.context.getName())) {
			System.err.println(className + "::readProperties - " + PROPERTY_APPLICATION_NAME + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(100);
		}
		
		this.context.addData(ATT_APPLICATION_VERSION, this.context.getProperties().getProperty(PROPERTY_APPLICATION_VERSION));
		if (!GeneralHelper.isValidString(this.context.getVersion())) {
			System.err.println(className + "::readProperties - " + PROPERTY_APPLICATION_VERSION + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(200);
		}

		this.context.addData(ATT_APPLICATION_DEBUG, new Boolean(this.context.getProperties().getPropertyBoolean(PROPERTY_APPLICATION_DEBUG)));
		
		this.context.addData(ATT_APPLICATION_WORKDIRECTORY, this.context.getProperties().getProperty(PROPERTY_APPLICATION_WORKDIRECTORY));
		
		this.localizerBase = this.context.getProperties().getProperty(PROPERTY_APPLICATION_LOCALIZER_BASE);
		
		if (this.context.isDebug()) this.context.getProperties().diagProperties();
	}

	
	/*
	 * Implemented methods
	 */
	public abstract void run();
}
