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
package net.laubenberger.bogatyr.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FilterOutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * This is a helper class for preferences.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.5.0
 */
public abstract class HelperPreferences { //TODO complete and test it!

	public static void readFromFile(final File file) throws IOException, InvalidPreferencesFormatException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

		try {
			Preferences.importPreferences(bis);
		} finally {
			bis.close();
		}
	}

	public static void write(final File file, final Preferences prefs) throws IOException, BackingStoreException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == prefs) {
			throw new RuntimeExceptionIsNull("prefs"); //$NON-NLS-1$
		}

		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

		try {
			prefs.exportNode(fos);
		} finally {
			fos.close();
		}
	}

	public static void writeUserNode(final File file, final Class<?> clazz) throws IOException, BackingStoreException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
		}

		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

		try {
			Preferences.userNodeForPackage(clazz).exportNode(fos);
		} finally {
			fos.close();
		}
	}

	public static void writeSystemNode(final File file, final Class<?> clazz) throws IOException, BackingStoreException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
		}

		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

		try {
			Preferences.systemNodeForPackage(clazz).exportNode(fos);
		} finally {
			fos.close();
		}
	}
}
