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
 * @version 20081026
 */
public abstract class HelperPreferences {
	public static void loadFromFile(final File file) throws IOException, InvalidPreferencesFormatException {
        BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            Preferences.importPreferences(bis);
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
	}
	 
	public static void save(final Preferences prefs, final File file) throws IOException, BackingStoreException {
        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            prefs.exportNode(bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
	}	
	  
	public static void saveUserNode(final Class<?> clazz, final File file) throws IOException, BackingStoreException {
        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            Preferences.userNodeForPackage(clazz).exportNode(bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
	}	
	  
	public static void saveSystemNode(final Class<?> clazz, final File file) throws IOException, BackingStoreException {
        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            Preferences.systemNodeForPackage(clazz).exportNode(bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
	}	
}
