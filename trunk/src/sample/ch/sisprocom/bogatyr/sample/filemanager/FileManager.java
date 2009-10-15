/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.controller.ApplicationAbstract;
import ch.sisprocom.bogatyr.controller.localizer.LocalizerFile;
import ch.sisprocom.bogatyr.controller.localizer.Localizer;
import ch.sisprocom.bogatyr.controller.property.PropertyStream;
import ch.sisprocom.bogatyr.controller.property.Property;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperString;

import java.io.File;
import java.io.IOException;


/**
 * Simple file manager using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @version 20091015
 */
public class FileManager extends ApplicationAbstract {
	// Fixed parameter - e.g. this could be an argument
	private static final String	ARG_PROPERTY_LOCATION = "cfg/ch/sisprocom/bogatyr/sample/filemanager/standard.properties"; //$NON-NLS-1$

	// Properties
	private static final String	PROPERTY_LOCALIZER_BASE = "FileManager.localizerbase"; //$NON-NLS-1$
	private static final String	PROPERTY_PATH           = "FileManager.path"; //$NON-NLS-1$
	private static final String	PROPERTY_IDENTIFIER     = "FileManager.identifier"; //$NON-NLS-1$
	private static final String	PROPERTY_DELETE         = "FileManager.delete"; //$NON-NLS-1$

	// Resources
	private static final String	RES_FILES   = "FileManager.files"; //$NON-NLS-1$
	private static final String	RES_DELETED = "FileManager.deleted"; //$NON-NLS-1$
	private static final String	RES_FOUND   = "FileManager.found"; //$NON-NLS-1$

	private Property property;
	private Localizer localizer;
	
	private File path; 
	
	
	public static void main(final String[] args) {
		new FileManager();
	}
	
	public FileManager() {
		super();
		
		init();
		run();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		try {
			property = new PropertyStream(new File(ARG_PROPERTY_LOCATION));
		} catch (IOException ex) {
			System.err.println("Couldn't process the property file!"); //$NON-NLS-1$
			ex.printStackTrace();
			exit(1);
		}

 		final String value = property.getValue(PROPERTY_PATH);
		if (HelperString.isValid(value)) {
            path = new File(value);
		} else {
			System.err.println(PROPERTY_PATH + " == 'null'"); //$NON-NLS-1$
			exit(10);
		}
		
		localizer = new LocalizerFile(property.getValue(PROPERTY_LOCALIZER_BASE));
	}

	private void searchFiles() throws IOException {
		int ii = 0;
		final boolean isDelete = property.getBooleanValue(PROPERTY_DELETE);

		for (final File file : HelperIO.getFiles(path, false, property.getValue(PROPERTY_IDENTIFIER))) {
			if (isDelete) {
				HelperIO.delete(file);
			}
			System.out.println(file);
			ii++;
		}
		System.out.println(localizer.getValue(RES_FILES) + HelperString.SPACE + (isDelete ? localizer.getValue(RES_DELETED) : localizer.getValue(RES_FOUND)) + ':' + ii);
	}
	
	
	/*
	 * Implemented methods
	 */
	public void run() {
		try {
			searchFiles();
		} catch (IOException ex) {
			System.err.println("Couldn't process the file search!"); //$NON-NLS-1$
			ex.printStackTrace();
			exit(20);
		}
		exit(0);
	}

	@Override
	public void exit(final int returnCode) {
		System.exit(returnCode);
	}
}
