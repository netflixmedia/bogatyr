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
package ch.sisprocom.bogatyr.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;


/**
 * This is a helper class for preferences.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091110)
 * @since 0.5.0
 */
public abstract class HelperPreferences { //TODO complete and test it!
	
	public static void readFromFile(final File file) throws IOException, InvalidPreferencesFormatException {
        final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        try {
            Preferences.importPreferences(bis);
        } finally {
            bis.close();
        }
	}
	 
	public static void write(final File file, final Preferences prefs) throws IOException, BackingStoreException {
        final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

        try {
            prefs.exportNode(bos);
        } finally {
            bos.close();
        }
	}	
	  
	public static void writeUserNode(final File file, final Class<?> clazz) throws IOException, BackingStoreException {
        final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

        try {
            Preferences.userNodeForPackage(clazz).exportNode(bos);
        } finally {
            bos.close();
        }
	}	
	  
	public static void writeSystemNode(final File file, final Class<?> clazz) throws IOException, BackingStoreException {
        final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

        try {
            Preferences.systemNodeForPackage(clazz).exportNode(bos);
        } finally {
            bos.close();
        }
	}	
}
