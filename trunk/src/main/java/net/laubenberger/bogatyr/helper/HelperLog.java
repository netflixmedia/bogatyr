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

package net.laubenberger.bogatyr.helper;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import net.laubenberger.bogatyr.model.unit.Bit;

/**
 * This is a helper class for logging.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.9.1
 */
public abstract class HelperLog {
	private static final String ID_APPLICATION_START = "+++ started +++"; //$NON-NLS-1$
	private static final String ID_APPLICATION_EXIT = "---"; //$NON-NLS-1$
	private static final String ID_METHOD_START = ">>>"; //$NON-NLS-1$
	private static final String ID_METHOD_EXIT = "<<<"; //$NON-NLS-1$
	private static final String ID_CONSTRUCTOR = "***"; //$NON-NLS-1$

	private static final String NULL = " null"; //$NON-NLS-1$
	private static final String EMPTY = " empty"; //$NON-NLS-1$

	public static String applicationStart() {
		final StringBuilder sb = new StringBuilder();

		sb.append(ID_APPLICATION_START);
		sb.append(HelperString.NEW_LINE);

		sb.append("Java version: ");
		sb.append(HelperEnvironment.getJavaVersion());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java vendor: ");
		sb.append(HelperEnvironment.getJavaVendor());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java VM name: ");
		sb.append(HelperEnvironment.getJavaVmName());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java VM version: ");
		sb.append(HelperEnvironment.getJavaVmVersion());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java properties: ");
		sb.append(HelperMap.dump(HelperEnvironment.getJavaProperties()));
		sb.append(HelperString.NEW_LINE);

		sb.append("Class path: ");
		sb.append(HelperEnvironment.getClassPath());
		sb.append(HelperString.NEW_LINE);

		sb.append("Library path: ");
		sb.append(HelperEnvironment.getLibraryPath());
		sb.append(HelperString.NEW_LINE);

		sb.append("Platform: ");
		sb.append(HelperEnvironment.getPlatform());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS name: ");
		sb.append(HelperEnvironment.getOsName());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS version: ");
		sb.append(HelperEnvironment.getOsVersion());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS architecture: ");
		sb.append(HelperEnvironment.getOsArch());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS temorary directory: ");
		sb.append(HelperEnvironment.getOsTempDirectory());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS environment variables: ");
		sb.append(HelperMap.dump(HelperEnvironment.getOsEnvironmentVariables()));
		sb.append(HelperString.NEW_LINE);

		sb.append("User name: ");
		sb.append(HelperEnvironment.getUserName());
		sb.append(HelperString.NEW_LINE);

		sb.append("User home directory: ");
		sb.append(HelperEnvironment.getUserHomeDirectory());
		sb.append(HelperString.NEW_LINE);

		sb.append("User directory: ");
		sb.append(HelperEnvironment.getUserDirectory());
		sb.append(HelperString.NEW_LINE);

		sb.append("User country: ");
		sb.append(HelperEnvironment.getUserCountry());
		sb.append(HelperString.NEW_LINE);

		sb.append("User language: ");
		sb.append(HelperEnvironment.getUserLanguage());
		sb.append(HelperString.NEW_LINE);

		sb.append("User timezone: ");
		sb.append(HelperEnvironment.getUserTimezone());

		sb.append("Available processors (cores): ");
		sb.append(HelperEnvironment.getAvailableProcessors());
		sb.append(HelperString.NEW_LINE);

		sb.append("Memory max: ");
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryMax()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB");
		sb.append(HelperString.NEW_LINE);

		sb.append("Memory total: ");
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryTotal()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB");
		sb.append(HelperString.NEW_LINE);

		sb.append("Memory used: ");
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryUsed()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB");
		sb.append(HelperString.NEW_LINE);

		sb.append("Memory free: ");
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryFree()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB");
		sb.append(HelperString.NEW_LINE);

		List<File> files = HelperIO.getAvailableDrives();

		for (File root : files) {
			sb.append("File system root: ");
			sb.append(root.getAbsolutePath());
			sb.append(HelperString.NEW_LINE);
			sb.append("Total space: ");
			sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceTotal(root)).setScale(3, BigDecimal.ROUND_DOWN));
			sb.append(" GB");
			sb.append(HelperString.NEW_LINE);
			sb.append("Free space: ");
			sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceFree(root)).setScale(3, BigDecimal.ROUND_DOWN));
			sb.append(" GB");
			sb.append(HelperString.NEW_LINE);
			sb.append("Usable space: ");
			sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceUsable(root)).setScale(3, BigDecimal.ROUND_DOWN));
			sb.append(" GB");
			sb.append(HelperString.NEW_LINE);
			sb.append("Used space: ");
			sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceUsed(root)).setScale(3, BigDecimal.ROUND_DOWN));
			sb.append(" GB");
			sb.append(HelperString.NEW_LINE);
		}

		return sb.toString();
	}

	public static String applicationExit(final int returnCode) {
		return ID_APPLICATION_EXIT + HelperString.SPACE
				+ "ended: " + returnCode + HelperString.SPACE + ID_APPLICATION_EXIT; //$NON-NLS-1$
	}

	public static String methodStart() {
		return ID_METHOD_START;
	}

	public static String methodStart(final Object... args) {
		final StringBuilder sb = new StringBuilder();
		sb.append(ID_METHOD_START);

		if (null != args) {
			if (0 == args.length) {
				sb.append(EMPTY);
			} else {
				for (final Object obj : args) {
					if (sb.length() > ID_METHOD_START.length()) {
						sb.append(HelperString.COMMA);
					}
					sb.append(HelperString.SPACE);
					sb.append(String.valueOf(obj));
				}
			}
		} else {
			sb.append(NULL);
		}
		return sb.toString();
	}

	public static String methodExit() {
		return ID_METHOD_EXIT;
	}

	public static String methodExit(final Object arg) {
		return ID_METHOD_EXIT + HelperString.SPACE + String.valueOf(arg);
	}

	public static String constructor() {
		return ID_CONSTRUCTOR;
	}

	public static String constructor(final Object... args) {
		final StringBuilder sb = new StringBuilder();
		sb.append(ID_CONSTRUCTOR);

		if (null != args) {
			if (0 == args.length) {
				sb.append(EMPTY);
			} else {
				for (final Object obj : args) {
					if (sb.length() > ID_METHOD_START.length()) {
						sb.append(HelperString.COMMA);
					}
					sb.append(HelperString.SPACE);
					sb.append(String.valueOf(obj));
				}
			}
		} else {
			sb.append(NULL);
		}
		return sb.toString();
	}
}