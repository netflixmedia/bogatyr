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

import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is a helper class for preferences
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class HelperPreferences {
	public static void loadFromFile(final File file) throws IOException, InvalidPreferencesFormatException {
		Logger.getInstance().writeMethodEntry(HelperPreferences.class, "loadFromFile", file);  //$NON-NLS-1$

        BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            Preferences.importPreferences(bis);
        } finally {
            if (bis != null) {
                bis.close();
            }
        }


		Logger.getInstance().writeMethodExit(HelperPreferences.class, "loadFromFile");  //$NON-NLS-1$
	}
	 
	public static void save(final Preferences prefs, final File file) throws IOException, BackingStoreException {
		Logger.getInstance().writeMethodEntry(HelperPreferences.class, "save", new Object[]{prefs, file});  //$NON-NLS-1$

        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            prefs.exportNode(bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
		
		Logger.getInstance().writeMethodExit(HelperPreferences.class, "save");  //$NON-NLS-1$
	}	
	  
	public static void saveUserNode(final Class<?> clazz, final File file) throws IOException, BackingStoreException {
		Logger.getInstance().writeMethodEntry(HelperPreferences.class, "saveUserNode", new Object[]{clazz, file});  //$NON-NLS-1$

        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            Preferences.userNodeForPackage(clazz).exportNode(bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
	
		Logger.getInstance().writeMethodExit(HelperPreferences.class, "saveUserNode");  //$NON-NLS-1$
	}	
	  
	public static void saveSystemNode(final Class<?> clazz, final File file) throws IOException, BackingStoreException {
		Logger.getInstance().writeMethodEntry(HelperPreferences.class, "saveSystemNode", new Object[]{clazz, file});  //$NON-NLS-1$

        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            Preferences.systemNodeForPackage(clazz).exportNode(bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }

 		Logger.getInstance().writeMethodExit(HelperPreferences.class, "saveSystemNode");  //$NON-NLS-1$
	}	
}