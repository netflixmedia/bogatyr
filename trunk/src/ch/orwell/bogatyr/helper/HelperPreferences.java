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
package ch.orwell.bogatyr.helper;

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
 * This is a helper class for preferences
 * 
 * @author Stefan Laubenberger
 * @version 20080725
 */
public abstract class HelperPreferences {
	public static void loadFromFile(File file) throws IOException, InvalidPreferencesFormatException {
	  java.util.prefs.Preferences.importPreferences(new BufferedInputStream(new FileInputStream(file)));
	}
	 
	public static void save(Preferences prefs, File file) throws IOException, BackingStoreException {
	  prefs.exportNode(new BufferedOutputStream(new FileOutputStream(file)));
	}	
	  
	public static void saveUserNode(Class<?> clazz, File file) throws IOException, BackingStoreException {
		java.util.prefs.Preferences.userNodeForPackage(clazz).exportNode(new BufferedOutputStream(new FileOutputStream(file)));
	}	
	  
	public static void saveSystemNode(Class<?> clazz, File file) throws IOException, BackingStoreException {
		java.util.prefs.Preferences.systemNodeForPackage(clazz).exportNode(new BufferedOutputStream(new FileOutputStream(file)));
	}	
}
