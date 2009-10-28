/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.helper.launcher.LauncherFile;

/**
 * This is a class to parse Java files and check if JUnit tests are available.
 * To find such classes and methods, it must be marked with $JUnit$.
 *
 * @author Stefan Laubenberger
 * @version 20091028
 */
public class CheckJUnit {
	private static final String MARKER = "$JUnit$"; //$NON-NLS-1$
	private static final String EXTENSION_CSV = ".csv"; //$NON-NLS-1$
	private static final String EXTENSION_JAVA = ".java"; //$NON-NLS-1$
	
	private static final String QUALIFIER_PUBLIC = "public"; //$NON-NLS-1$
	private static final String QUALIFIER_PROTECTED = "protected"; //$NON-NLS-1$
	private static final String QUALIFIER_CLASS = "class"; //$NON-NLS-1$
	private static final String QUALIFIER_INTERFACE = "interface"; //$NON-NLS-1$

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		list(true);
		list(false);
	}

	/*
	 * Lists all classes (and methods) with or without JUnit tests
	 */
	private static void list(final boolean isTested) {
		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileFilterCsv());
		
		if (isTested) {
			fc.setSelectedFile(new File(HelperEnvironment.getUserHomeDirectory(), "JUnitTested" + EXTENSION_CSV)); //$NON-NLS-1$
		} else {
			fc.setSelectedFile(new File(HelperEnvironment.getUserHomeDirectory(), "JUnitUntested" + EXTENSION_CSV)); //$NON-NLS-1$
		}
		
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	final File output = fc.getSelectedFile();
        	output.delete();
        	
        	try {
           	    final java.io.FileFilter filter = new java.io.FileFilter() { 
        	    	public boolean accept(File file) { 
        	    		return HelperString.endsWith(file.getName(), EXTENSION_JAVA) && !HelperString.contains(file.getName(), "svn");
        	    	} 
        	    };
        	    
        	    final Collection<File>listJava = HelperIO.getFiles(HelperEnvironment.getUserDirectory(), filter, -1);
		
				HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, "Class", "Method/Variable"));  //$NON-NLS-1$//$NON-NLS-2$
				
				for (final File file : listJava) {
					if (file.getAbsolutePath().contains("/java/")) { //ignore all sources except the java package //$NON-NLS-1$
						Scanner scanner = new Scanner(file);
						
				    	while (scanner.hasNextLine()) {
				    		final String line = scanner.nextLine();
				    		
				    		if (isTested) {
					    		if (null != line && line.contains(MARKER)) {
					    			HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, file.getAbsolutePath(), line));
					    		}
				    		} else {
					    		if (null != line && !line.contains(MARKER) && (line.contains(QUALIFIER_PUBLIC) || line.contains(QUALIFIER_PROTECTED)) && 
					    				!line.contains(QUALIFIER_CLASS) && !line.contains(QUALIFIER_INTERFACE) && !line.contains(HelperString.SEMICOLON)) {
					    			HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, file.getAbsolutePath(), line));
					    		}
				    		}
			            }
						scanner.close();
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
        	if (JOptionPane.showConfirmDialog(null, "Open file with the default application?", "Open file", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {  //$NON-NLS-1$//$NON-NLS-2$
        		try {
					LauncherFile.open(output);
					System.exit(0);
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(10);
				}
        	}
        }
	}

	
	/*
	 * Inner classes
	 */
	static class FileFilterCsv extends FileFilter {

        @Override
		public boolean accept(File file) {
            String filename = file.getName();
            return filename.endsWith(EXTENSION_CSV);
        }

        @Override
		public String getDescription() {
            return "*.csv"; //$NON-NLS-1$
        }
    }
}
