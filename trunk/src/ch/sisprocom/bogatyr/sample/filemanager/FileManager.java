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
package ch.sisprocom.bogatyr.sample.filemanager;

import java.io.File;
import java.io.IOException;

import ch.sisprocom.bogatyr.controller.ApplicationTemplate;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.exception.HelperException;
import ch.sisprocom.bogatyr.helper.logger.Logger;
import ch.sisprocom.bogatyr.helper.property.Property;


/**
 * Simple file manager using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class FileManager extends ApplicationTemplate {
	// Resources
	private final static String	PROPERTY_PATH        = "FileManager.path"; //$NON-NLS-1$
	private final static String	PROPERTY_IDENTIFIER  = "FileManager.identifier"; //$NON-NLS-1$
	private final static String	PROPERTY_DELETE      = "FileManager.delete"; //$NON-NLS-1$

	private File path; 
	private String[] identifier;
	private boolean isDelete;
	
	
	public FileManager(String propertiesFileName) throws IOException {
		super(propertiesFileName);
		init();
		run();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		readProperties();
	}
	
	private void readProperties() {
 		String value = Property.getInstance().getProperty(PROPERTY_PATH);
		if (HelperGeneral.isValidString(value)) {
            path = new File(value);
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_PATH + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			exit(70);
		}
		
		identifier = new String[]{Property.getInstance().getProperty(PROPERTY_IDENTIFIER)};
	
		isDelete = Property.getInstance().getPropertyBoolean(PROPERTY_DELETE);
	}

	private void searchFiles() throws IOException {
		int ii = 0;
		
		for (File file : HelperIO.getFiles(path, identifier, false)) {
			if (isDelete) {
				HelperIO.delete(file);
			}
			Logger.getInstance().writeDebug(this.getClass(), "searchFiles", file.getAbsolutePath()); //$NON-NLS-1$
			ii++;
		}
		Logger.getInstance().writeLog(this.getClass(), "searchFiles", ii + " file(s) " + (isDelete ? "deleted" : "found")); //$NON-NLS-1$
	}
	
	
	/*
	 * Implemented methods
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			searchFiles();
		} catch (IOException ex) {
			Logger.getInstance().writeException(this.getClass(), "run", HelperException.EX_UNKNOWN_ERROR, ex);
			exit(79);
		}
		exit(0);
	}
}