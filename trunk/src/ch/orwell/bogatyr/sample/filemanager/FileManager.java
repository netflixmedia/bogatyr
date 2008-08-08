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
package ch.orwell.bogatyr.sample.filemanager;

import java.io.File;
import java.io.IOException;

import ch.orwell.bogatyr.controller.ApplicationTemplate;
import ch.orwell.bogatyr.helper.HelperIO;
import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.exception.HelperException;
import ch.orwell.bogatyr.helper.logger.Logger;
import ch.orwell.bogatyr.helper.property.Property;

/**
 * Simple file manager using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @version 20080803
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
			Logger.getInstance().writeDebug(this.getClass(), "searchFiles", file.getAbsolutePath());
			ii++;
		}
		Logger.getInstance().writeLog(this.getClass(), "searchFiles", ii + " file(s) " + (isDelete ? "deleted" : "found"));
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
