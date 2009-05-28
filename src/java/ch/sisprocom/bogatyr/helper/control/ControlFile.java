/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.control;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperString;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * This control opens, edits and prints data with the default system application.
 *
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.7.0
 */
public abstract class ControlFile {
	private static final File PATH = HelperEnvironment.getOsTempDirectory();
	private static final String IDENTIFIER = ControlFile.class.getSimpleName();
	
	static {
		try {
			final Collection<File> list = HelperIO.getFiles(PATH, IDENTIFIER);
			
			for (final File file : list) {
				HelperIO.delete(file);
			}
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
		if (!HelperArray.isValid(data)) {
			throw new IllegalArgumentException("data is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
		}	
		
		open(createTemporaryFile(data, extension));
	}

	/**
	 * Open a {@link File} with the default system application.
	 * 
	 * @param file data as file
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void open(final File file) throws IOException { //$JUnit
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
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
	 * @since 0.7.0
	 */
	public static void open(final InputStream is, final String extension) throws IOException { //$JUnit
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
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
		if (!HelperArray.isValid(data)) {
			throw new IllegalArgumentException("data is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
		}	
		
		edit(createTemporaryFile(data, extension));
	}

	/**
	 * Edit a {@link File} with the default system application.
	 * 
	 * @param file data as file
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void edit(final File file) throws IOException { //$JUnit
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
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
	 * @since 0.7.0
	 */
	public static void edit(final InputStream is, final String extension) throws IOException { //$JUnit
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
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
		if (!HelperArray.isValid(data)) {
			throw new IllegalArgumentException("data is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
		}	
		
		print(createTemporaryFile(data, extension));
	}

	/**
	 * Print a {@link File} with the default system application.
	 * 
	 * @param file data as file
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void print(final File file) throws IOException { //$JUnit
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
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
	 * @since 0.7.0
	 */
	public static void print(final InputStream is, final String extension) throws IOException { //$JUnit
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
		}
		
		print(HelperIO.readStream(is), extension);
	}
	
	
	/*
	 * Private methods
	 */
	private static File createTemporaryFile(final byte[] data, final String extension) throws IOException {
		final File file;
		if (extension.startsWith(HelperString.PERIOD)) {
			file = new File(PATH, IDENTIFIER + System.currentTimeMillis() + extension);
		} else {
			file = new File(PATH, IDENTIFIER + System.currentTimeMillis() + HelperString.PERIOD + extension);
		}

		HelperIO.writeFile(file, data, false);
		
		return file;
	}
}
