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
import ch.sisprocom.bogatyr.helper.control.ControlFile;

/**
 * This is a class to parse Java files and check if a Wiki documentation is available.
 * To find such classes and methods, it must be marked with $Wiki$.
 *
 * @author Stefan Laubenberger
 * @version 20090528
 */
public class CheckWiki {
	private static final String MARKER = "$Wiki$"; //$NON-NLS-1$
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		listJUnit();
		listNoJUnit();
	}

	/*
	 * Lists all classes (and methods) with JUnit tests
	 */
	private static void listJUnit() {
		final JFileChooser fc = new JFileChooser();

		fc.addChoosableFileFilter(new FileFilterCsv());
		fc.setSelectedFile(new File(HelperEnvironment.getUserHomeDirectory(), "JUnitTested.csv"));

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	final File output = fc.getSelectedFile();
        	output.delete();
        	
        	try {
				final Collection<File>listJava = HelperIO.getFiles(HelperEnvironment.getUserDirectory(), ".java");
				final Collection<File>listSvn = HelperIO.getFiles(HelperEnvironment.getUserDirectory(), "svn");
				listJava.removeAll(listSvn);
	
				HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, "Class", "Method/Variable"));
				
				for (final File file : listJava) {
					if (file.getAbsolutePath().contains("/java/")) { //ignore all sources except the java package
						Scanner scanner = new Scanner(file);
						
				    	while (scanner.hasNextLine()) {
				    		final String line = scanner.nextLine();
				    		
				    		if (null != line && line.contains(MARKER)) {
				    			HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, file.getAbsolutePath(), line));
				    		}
				    		
			            }
		
						scanner.close();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	if (JOptionPane.showConfirmDialog(null, "Open file with the default application?", "Open file", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        		try {
					ControlFile.open(output);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
        	}
        }
	}
	
	/*
	 * Lists all classes (and methods) without JUnit tests
	 */
	private static void listNoJUnit() {
		final JFileChooser fc = new JFileChooser();

		fc.addChoosableFileFilter(new FileFilterCsv());
		fc.setSelectedFile(new File(HelperEnvironment.getUserHomeDirectory(), "JUnitUntested.csv"));

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	final File output = fc.getSelectedFile();
        	output.delete();
        	
        	try {
				final Collection<File>listJava = HelperIO.getFiles(HelperEnvironment.getUserDirectory(), ".java");
				final Collection<File>listSvn = HelperIO.getFiles(HelperEnvironment.getUserDirectory(), "svn");
				listJava.removeAll(listSvn);
	
				HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, "Class", "Method/Variable"));

				for (final File file : listJava) {
					if (file.getAbsolutePath().contains("/java/")) { //ignore all sources except the java package
						Scanner scanner = new Scanner(file);
						
				    	while (scanner.hasNextLine()) {
				    		final String line = scanner.nextLine();
				    		
				    		if (null != line && (line.contains("public") || line.contains("protected")) && 
				    				!line.contains("class") && !line.contains("interface") && !line.contains(MARKER)) {
				    			HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, file.getAbsolutePath(), line));
				    		}
			            }
						scanner.close();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	       	if (JOptionPane.showConfirmDialog(null, "Open file with the default application?", "Open file", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	    		try {
					ControlFile.open(output);
				} catch (IOException ex) {
					ex.printStackTrace();
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
            return filename.endsWith(".csv"); //$NON-NLS-1$
        }

        @Override
		public String getDescription() {
            return "*.csv"; //$NON-NLS-1$
        }
    }
}
