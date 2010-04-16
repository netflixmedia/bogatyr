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
package net.laubenberger.bogatyr.helper.launcher;

import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperEnvironment;
import net.laubenberger.bogatyr.helper.HelperIO;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;

/**
 * This launcher opens, edits and prints files with the default system application.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public abstract class LauncherFile {
	private static final Logger log = LoggerFactory.getLogger(LauncherFile.class);

	private static final File PATH = HelperEnvironment.getOsTempDirectory();
	static final String IDENTIFIER = LauncherFile.class.getSimpleName();

	static {
		try {
			deleteTemporaryFiles();
		} catch (IOException ex) {
			log.warn("Could not delete temporary files", ex); //$NON-NLS-1$
		}
	}


	/**
	 * Open a byte-array data with the default system application.
	 *
	 * @param data		as byte array
	 * @param extension of the file (e.g. ".pdf")
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void open(final byte[] data, final String extension) throws IOException {
		log.debug(HelperLog.methodStart(data, extension));
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		open(createTemporaryFile(data, extension));

		log.debug(HelperLog.methodExit());
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
		log.debug(HelperLog.methodStart(file));
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
			}

			Desktop.getDesktop().open(file);
		} else {
			throw new RuntimeException("Default system viewer application not supported by your machine!"); //$NON-NLS-1$
		}
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Open an {@link InputStream} with the default system application.
	 *
	 * @param is		  data as stream
	 * @param extension of the file (e.g. ".pdf")
	 * @throws IOException
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static void open(final InputStream is, final String extension) throws IOException { //$JUnit$
		log.debug(HelperLog.methodStart(is, extension));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		open(HelperIO.readStream(is), extension);

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Edit a byte-array data with the default system application.
	 *
	 * @param data		as byte array
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void edit(final byte[] data, final String extension) throws IOException {
		log.debug(HelperLog.methodStart(data, extension));
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		edit(createTemporaryFile(data, extension));

		log.debug(HelperLog.methodExit());
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
		log.debug(HelperLog.methodStart(file));
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
			}

			Desktop.getDesktop().edit(file);
		} else {
			throw new RuntimeException("Default system editor application not supported by your machine!"); //$NON-NLS-1$
		}
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Edit an {@link InputStream} with the default system application.
	 *
	 * @param is		  data as stream
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static void edit(final InputStream is, final String extension) throws IOException { //$JUnit$
		log.debug(HelperLog.methodStart(is, extension));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		edit(HelperIO.readStream(is), extension);

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Print a byte-array data with the default system application.
	 *
	 * @param data		as byte array
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void print(final byte[] data, final String extension) throws IOException {
		log.debug(HelperLog.methodStart(data, extension));
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		print(createTemporaryFile(data, extension));

		log.debug(HelperLog.methodExit());
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
		log.debug(HelperLog.methodStart(file));
		if (Desktop.isDesktopSupported()) {
			if (null == file) {
				throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
			}

			Desktop.getDesktop().print(file);
		} else {
			throw new RuntimeException("Default system print application not supported by your machine!"); //$NON-NLS-1$
		}
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Print an {@link InputStream} with the default system application.
	 *
	 * @param is		  data as stream
	 * @param extension of the file (e.g. ".html")
	 * @throws IOException
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static void print(final InputStream is, final String extension) throws IOException { //$JUnit$
		log.debug(HelperLog.methodStart(is, extension));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		print(HelperIO.readStream(is), extension);

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Deletes the created temporary files.
	 *
	 * @throws IOException
	 * @since 0.8.0
	 */
	public static void deleteTemporaryFiles() throws IOException {
		log.debug(HelperLog.methodStart());
		final FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(final File file) {
				return file.getName().contains(IDENTIFIER);
			}
		};

		for (final File file : HelperIO.getFiles(PATH, filter)) {
			HelperIO.delete(file);
		}
		log.debug(HelperLog.methodExit());
	}


	/*
	 * Private methods
	 */
	private static File createTemporaryFile(final byte[] data, final String extension) throws IOException {
		log.trace(HelperLog.methodStart(data, extension));

		final File result = extension.startsWith(HelperString.PERIOD) ? new File(PATH, IDENTIFIER + System.currentTimeMillis() + extension) : new File(PATH, IDENTIFIER + System.currentTimeMillis() + HelperString.PERIOD + extension);

		HelperIO.writeFile(result, data, false);

		log.trace(HelperLog.methodExit(result));
		return result;
	}
}
