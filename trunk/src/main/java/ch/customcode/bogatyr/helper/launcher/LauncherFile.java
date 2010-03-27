/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper.launcher;

import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;

import ch.customcode.bogatyr.helper.HelperEnvironment;
import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;

/**
 * This launcher opens, edits and prints files with the default system application.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.7.0
 */
public abstract class LauncherFile {
	private static final File PATH = HelperEnvironment.getOsTempDirectory();
	static final String IDENTIFIER = LauncherFile.class.getSimpleName();
	
	static {
		try {
			deleteTemporaryFiles();
		} catch (IOException ex) {
			throw new RuntimeException("temporary files couldn't be deleted: " + ex.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	
	/**
	 * Open a byte-array data with the default system application.
	 * 
	 * @param data as byte array
	 * @param extension of the file (e.g. ".pdf")
	 * @throws IOException 
	 * @since 0.7.0
	 */
	public static void open(final byte[] data, final String extension) throws IOException {
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}	
		
		open(createTemporaryFile(data, extension));
	}

	/**
	 * Open a {@link File} with the default system application.
	 * 
	 * @param file data as file
	 * @throws IOException
	 * @see File
	 * @since 0.7.0
	 */
	public static void open(final File file) throws IOException { //$JUnit$
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
			}

			Desktop.getDesktop().open(file);
		} else {
			throw new RuntimeException("Default system viewer application not supported by your machine!"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Open an {@link InputStream} with the default system application.
	 * 
	 * @param is data as stream
	 * @param extension of the file (e.g. ".pdf")
	 * @throws IOException
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static void open(final InputStream is, final String extension) throws IOException { //$JUnit$
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}
		
		open(HelperIO.readStream(is), extension);
	}
	
	/**
	 * Edit a byte-array data with the default system application.
	 * 
	 * @param data as byte array
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void edit(final byte[] data, final String extension) throws IOException {
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}	
		
		edit(createTemporaryFile(data, extension));
	}

	/**
	 * Edit a {@link File} with the default system application.
	 * 
	 * @param file data as file
	 * @throws IOException
	 * @see File
	 * @since 0.7.0
	 */
	public static void edit(final File file) throws IOException { //$JUnit$
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
			}

			Desktop.getDesktop().edit(file);
		} else {
			throw new RuntimeException("Default system editor application not supported by your machine!"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Edit an {@link InputStream} with the default system application.
	 * 
	 * @param is data as stream
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static void edit(final InputStream is, final String extension) throws IOException { //$JUnit$
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}
		
		edit(HelperIO.readStream(is), extension);
	}
	
	/**
	 * Print a byte-array data with the default system application.
	 * 
	 * @param data as byte array
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void print(final byte[] data, final String extension) throws IOException {
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}	
		
		print(createTemporaryFile(data, extension));
	}

	/**
	 * Print a {@link File} with the default system application.
	 * 
	 * @param file data as file
	 * @throws IOException
	 * @see File
	 * @since 0.7.0
	 */
	public static void print(final File file) throws IOException { //$JUnit$
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
			}

			Desktop.getDesktop().print(file);
		} else {
			throw new RuntimeException("Default system print application not supported by your machine!"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Print an {@link InputStream} with the default system application.
	 * 
	 * @param is data as stream
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static void print(final InputStream is, final String extension) throws IOException { //$JUnit$
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}
		
		print(HelperIO.readStream(is), extension);
	}
	
	/**
	 * Deletes the created temporary files.
	 * 
	 * @throws IOException
	 * @since 0.8.0
	 */
	public static void deleteTemporaryFiles() throws IOException {
	    final FileFilter filter = new FileFilter() { 
	    	@Override
            public boolean accept(final File file) {
	    		return file.getName().contains(IDENTIFIER);
	    	} 
	    }; 
		
		for (final File file : HelperIO.getFiles(PATH, filter)) {
			HelperIO.delete(file);
		}
	}
	
	
	/*
	 * Private methods
	 */
	private static File createTemporaryFile(final byte[] data, final String extension) throws IOException {
		final File file = extension.startsWith(HelperString.PERIOD) ? new File(PATH, IDENTIFIER + System.currentTimeMillis() + extension) : new File(PATH, IDENTIFIER + System.currentTimeMillis() + HelperString.PERIOD + extension);

		HelperIO.writeFile(file, data, false);
		
		return file;
	}
}
