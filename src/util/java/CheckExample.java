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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */


import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import net.laubenberger.bogatyr.helper.HelperEnvironment;
import net.laubenberger.bogatyr.helper.HelperIO;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.helper.launcher.LauncherFile;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a class to parse Java files and check if an example documentation is available.
 * To find such classes and methods, it must be marked with $Example$.
 *
 * @author Stefan Laubenberger
 * @version 20100813
 */
public class CheckExample {
	private static final Logger log = LoggerFactory.getLogger(CheckExample.class);

	private static final String MARKER = "$Example$"; //$NON-NLS-1$
	private static final String EXTENSION_CSV = ".csv"; //$NON-NLS-1$
	private static final String EXTENSION_JAVA = ".java"; //$NON-NLS-1$

	private static final String QUALIFIER_PUBLIC = "public"; //$NON-NLS-1$
	private static final String QUALIFIER_PROTECTED = "protected"; //$NON-NLS-1$
	private static final String QUALIFIER_CLASS = "class"; //$NON-NLS-1$
	private static final String QUALIFIER_INTERFACE = "interface"; //$NON-NLS-1$

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		PropertyConfigurator.configure("src/util/configuration/log4j.properties"); //$NON-NLS-1$

//		list(true);
		list(false);
	}

	/*
	 * Lists all classes (and methods) with or without example documentation tests.
	 */

	private static void list(final boolean isExampleAvailable) {
		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new FileFilterCsv());

		if (isExampleAvailable) {
			fc.setSelectedFile(new File(HelperEnvironment.getUserHomeDirectory(), "ExampleAvailable" + EXTENSION_CSV)); //$NON-NLS-1$
		} else {
			fc.setSelectedFile(new File(HelperEnvironment.getUserHomeDirectory(), "ExampleNotAvailable" + EXTENSION_CSV)); //$NON-NLS-1$
		}

		if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(null)) {
			final File output = fc.getSelectedFile();
			output.delete();

			try {
				final java.io.FileFilter filter = new java.io.FileFilter() {
					@Override
					public boolean accept(final File file) {
						return HelperString.endsWith(file.getName(), EXTENSION_JAVA) && !HelperString.contains(file.getName(), "svn"); //$NON-NLS-1$
					}
				};

				final Collection<File> listJava = HelperIO.getFiles(HelperEnvironment.getUserDirectory(), filter);

				HelperIO.writeLine(output, HelperString.concatenate(HelperString.SEMICOLON, "Class", "Method/Variable")); //$NON-NLS-1$ //$NON-NLS-2$

				for (final File file : listJava) {
					if (file.getAbsolutePath().contains("/main/java/")) { //ignore all sources except the java package //$NON-NLS-1$
						final Scanner scanner = new Scanner(file);

						while (scanner.hasNextLine()) {
							final String line = scanner.nextLine();

							if (isExampleAvailable) {
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
				log.error("Could not process files", ex); //$NON-NLS-1$
			}

			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Open file with the default application?", "Open file", JOptionPane.YES_NO_OPTION)) {  //$NON-NLS-1$//$NON-NLS-2$
				try {
					LauncherFile.open(output);
				} catch (IOException ex) {
					log.error("Could not open output file", ex); //$NON-NLS-1$
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
		public boolean accept(final File file) {
			return HelperString.endsWith(file.getName(), EXTENSION_CSV);
		}

		@Override
		public String getDescription() {
			return "*.csv"; //$NON-NLS-1$
		}
	}
}
