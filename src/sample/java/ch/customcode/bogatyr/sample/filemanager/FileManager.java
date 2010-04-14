/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package ch.customcode.bogatyr.sample.filemanager;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import ch.customcode.bogatyr.controller.ApplicationAbstract;
import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.service.localizer.Localizer;
import ch.customcode.bogatyr.service.localizer.LocalizerFile;
import ch.customcode.bogatyr.service.property.Property;
import ch.customcode.bogatyr.service.property.PropertyImpl;


/**
 * Simple file manager using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @version 20100405
 */
public class FileManager extends ApplicationAbstract {
	// Fixed parameter - e.g. this could be an argument
	private static final String	ARG_PROPERTY_LOCATION = "src/sample/configuration/ch/customcode/bogatyr/sample/filemanager/standard.properties"; //$NON-NLS-1$

	// Properties
	private static final String	PROPERTY_LOCALIZER_BASE = "FileManager.localizerbase"; //$NON-NLS-1$
	private static final String	PROPERTY_PATH           = "FileManager.path"; //$NON-NLS-1$
	private static final String	PROPERTY_IDENTIFIER     = "FileManager.identifier"; //$NON-NLS-1$
	private static final String	PROPERTY_DELETE         = "FileManager.delete"; //$NON-NLS-1$

	// Resources
	private static final String	RES_FILES   = "FileManager.files"; //$NON-NLS-1$
	private static final String	RES_DELETED = "FileManager.deleted"; //$NON-NLS-1$
	private static final String	RES_FOUND   = "FileManager.found"; //$NON-NLS-1$

	Property property;
	private Localizer localizer;
	
	private File path; 
	
	
	public static void main(final String[] args) {
		PropertyConfigurator.configure("src/sample/configuration/log4j.properties");
		
		final FileManager fm = new FileManager();
		fm.run();
	}
	
	public FileManager() {
		super();
		
		init();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		try {
			property = new PropertyImpl(new File(ARG_PROPERTY_LOCATION));
		} catch (IOException ex) {
			System.err.println("Couldn't process the property file!"); //$NON-NLS-1$
			ex.printStackTrace();
			System.exit(1);
		}

 		final String value = property.getValue(PROPERTY_PATH);
		if (HelperString.isValid(value)) {
            path = new File(value);
		} else {
			System.err.println(PROPERTY_PATH + " == 'null'"); //$NON-NLS-1$
			System.exit(10);
		}
		
		localizer = new LocalizerFile(property.getValue(PROPERTY_LOCALIZER_BASE));
	}

	private void searchFiles() throws IOException {
		int ii = 0;
		final boolean isDelete = property.getBoolean(PROPERTY_DELETE);

	    final FileFilter filter = new FileFilter() { 
	    	@Override
            public boolean accept(final File file) {
	    		return HelperString.contains(file.getName(), property.getValue(PROPERTY_IDENTIFIER));
	    	} 
	    };

		for (final File file : HelperIO.getFiles(path, filter)) {
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
	@Override
    public void run() {
		try {
			searchFiles();
		} catch (IOException ex) {
			System.err.println("Couldn't process the file search!"); //$NON-NLS-1$
			ex.printStackTrace();
			System.exit(20);
		}
		System.exit(0);
	}
}
