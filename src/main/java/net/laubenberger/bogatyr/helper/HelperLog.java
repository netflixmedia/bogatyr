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

import net.laubenberger.bogatyr.model.application.ModelApplication;
import net.laubenberger.bogatyr.model.unit.Bit;

/**
 * This is a helper class for logging.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100512)
 * @since 0.9.1
 */
public abstract class HelperLog {
	private static final String ID_APPLICATION_START = "+++ started +++"; //$NON-NLS-1$
	private static final String ID_APPLICATION_EXIT = "---"; //$NON-NLS-1$
	private static final String ID_METHOD_START = ">>>"; //$NON-NLS-1$
	private static final String ID_METHOD_EXIT = "<<<"; //$NON-NLS-1$
	private static final String ID_CONSTRUCTOR = "***"; //$NON-NLS-1$
	
	private static final int LINE_LENGTH = 80;
	private static final String FILLER = HelperString.fill('-', LINE_LENGTH);
	
	private static final String NULL = " null"; //$NON-NLS-1$
	private static final String EMPTY = " empty"; //$NON-NLS-1$

	public static String applicationStart(final ModelApplication model) {
		final StringBuilder sb = new StringBuilder();

		sb.append(ID_APPLICATION_START);
		sb.append(HelperString.NEW_LINE);
		
		// Application
		sb.append(FILLER); 
		sb.append(HelperString.NEW_LINE);
		
		sb.append("Name: "); //$NON-NLS-1$
		sb.append(model.getName());
		sb.append(HelperString.NEW_LINE);
		
		sb.append("Version: "); //$NON-NLS-1$
		sb.append(model.getVersion());
		sb.append(HelperString.NEW_LINE);
		
		sb.append("Build: "); //$NON-NLS-1$
		sb.append(model.getBuild());
		sb.append(HelperString.NEW_LINE);
		
		sb.append("Created: "); //$NON-NLS-1$
		sb.append(model.getCreated());
		sb.append(HelperString.NEW_LINE);
		
		sb.append("UUID: "); //$NON-NLS-1$
		sb.append(model.getUUID());
		sb.append(HelperString.NEW_LINE);
		
		sb.append("URL: "); //$NON-NLS-1$
		sb.append(model.getUrl());
		sb.append(HelperString.NEW_LINE);
		
		if (null != model.getOrganizations()) {
			sb.append("Organizations: "); //$NON-NLS-1$
			sb.append(HelperCollection.dump(model.getOrganizations()));
			sb.append(HelperString.NEW_LINE);		
		}
		
		if (null != model.getPersons()) {
			sb.append("Persons: "); //$NON-NLS-1$
			sb.append(HelperCollection.dump(model.getPersons()));
			sb.append(HelperString.NEW_LINE);	
		}
		
		sb.append("isDebug: "); //$NON-NLS-1$
		sb.append(model.isDebug());
		sb.append(HelperString.NEW_LINE);
		
		if (null != model.getTags()) {
			sb.append("Tags: "); //$NON-NLS-1$
			sb.append(HelperMap.dump(model.getTags()));
			sb.append(HelperString.NEW_LINE);	
		}
		
		// Java
		sb.append(FILLER); 
		sb.append(HelperString.NEW_LINE);
		
		sb.append("Java version: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getJavaVersion());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java vendor: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getJavaVendor());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java VM name: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getJavaVmName());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java VM version: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getJavaVmVersion());
		sb.append(HelperString.NEW_LINE);

		sb.append("Java properties: "); //$NON-NLS-1$
		sb.append(HelperMap.dump(HelperEnvironment.getJavaProperties()));
		sb.append(HelperString.NEW_LINE);

		sb.append("Class path: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getClassPath());
		sb.append(HelperString.NEW_LINE);

		sb.append("Library path: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getLibraryPath());
		sb.append(HelperString.NEW_LINE);

		// OS
		sb.append(FILLER); 
		sb.append(HelperString.NEW_LINE);

		sb.append("Platform: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getPlatform());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS name: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getOsName());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS version: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getOsVersion());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS architecture: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getOsArch());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS temorary directory: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getOsTempDirectory());
		sb.append(HelperString.NEW_LINE);

		sb.append("OS environment variables: "); //$NON-NLS-1$
		sb.append(HelperMap.dump(HelperEnvironment.getOsEnvironmentVariables()));
		sb.append(HelperString.NEW_LINE);

		// User
		sb.append(FILLER); 
		sb.append(HelperString.NEW_LINE);

		sb.append("User name: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getUserName());
		sb.append(HelperString.NEW_LINE);

		sb.append("User home directory: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getUserHomeDirectory());
		sb.append(HelperString.NEW_LINE);

		sb.append("User directory: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getUserDirectory());
		sb.append(HelperString.NEW_LINE);

		sb.append("User country: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getUserCountry());
		sb.append(HelperString.NEW_LINE);

		sb.append("User language: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getUserLanguage());
		sb.append(HelperString.NEW_LINE);

		sb.append("User timezone: "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getUserTimezone());
		sb.append(HelperString.NEW_LINE);
		
		// System
		sb.append(FILLER); 
		sb.append(HelperString.NEW_LINE);

		sb.append("Available processors (cores): "); //$NON-NLS-1$
		sb.append(HelperEnvironment.getAvailableProcessors());
		sb.append(HelperString.NEW_LINE);

		sb.append("Maximum memory: "); //$NON-NLS-1$
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryMax()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB"); //$NON-NLS-1$
		sb.append(HelperString.NEW_LINE);

		sb.append("Total memory: "); //$NON-NLS-1$
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryTotal()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB"); //$NON-NLS-1$
		sb.append(HelperString.NEW_LINE);

		sb.append("Free memory: "); //$NON-NLS-1$
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryFree()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB"); //$NON-NLS-1$
		sb.append(HelperString.NEW_LINE);
		
		sb.append("Used memory: "); //$NON-NLS-1$
		sb.append(Bit.BYTE.convertTo(Bit.MEGABYTE, HelperEnvironment.getMemoryUsed()).setScale(3, BigDecimal.ROUND_DOWN));
		sb.append(" MB"); //$NON-NLS-1$
		sb.append(HelperString.NEW_LINE);

		List<File> files = HelperIO.getAvailableDrives();

		for (File root : files) {
//			if (HelperIO.isDrive(root)) {
				sb.append("Drive: "); //$NON-NLS-1$
				sb.append(root.getAbsolutePath());
				sb.append(HelperString.NEW_LINE);
				sb.append("Total space: "); //$NON-NLS-1$
				sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceTotal(root)).setScale(3, BigDecimal.ROUND_DOWN));
				sb.append(" GB"); //$NON-NLS-1$
				sb.append(HelperString.NEW_LINE);
				sb.append("Free space: "); //$NON-NLS-1$
				sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceFree(root)).setScale(3, BigDecimal.ROUND_DOWN));
				sb.append(" GB"); //$NON-NLS-1$
				sb.append(HelperString.NEW_LINE);
				sb.append("Usable space: "); //$NON-NLS-1$
				sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceUsable(root)).setScale(3, BigDecimal.ROUND_DOWN));
				sb.append(" GB"); //$NON-NLS-1$
				sb.append(HelperString.NEW_LINE);
				sb.append("Used space: "); //$NON-NLS-1$
				sb.append(Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceUsed(root)).setScale(3, BigDecimal.ROUND_DOWN));
				sb.append(" GB"); //$NON-NLS-1$
				sb.append(HelperString.NEW_LINE);
//			}
		}

		sb.append(FILLER); 

		return sb.toString();
	}

	public static String applicationExit(final int returnCode) {
		return ID_APPLICATION_EXIT + HelperString.SPACE + returnCode + HelperString.SPACE + ID_APPLICATION_EXIT;
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