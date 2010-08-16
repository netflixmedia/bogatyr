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

import net.laubenberger.bogatyr.controller.application.ControllerApplication;

/**
 * This is a helper class for logging.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.9.1
 */
public abstract class HelperLog {
	private static final String ID_APPLICATION_START = "+++ started +++"; //$NON-NLS-1$
	private static final String ID_APPLICATION_EXIT = "---"; //$NON-NLS-1$
	private static final String ID_METHOD_START = ">>>"; //$NON-NLS-1$
	private static final String ID_METHOD_EXIT = "<<<"; //$NON-NLS-1$
	private static final String ID_CONSTRUCTOR = "***"; //$NON-NLS-1$
	
	private static final int LINE_LENGTH = 80;
	
	private static final String NULL = " null"; //$NON-NLS-1$
	private static final String EMPTY = " empty"; //$NON-NLS-1$

	public static String applicationStart(final ControllerApplication<?, ?> controller) {
		final StringBuilder sb = new StringBuilder();

		sb.append(ID_APPLICATION_START);
		sb.append(HelperString.NEW_LINE);
		
		// Application
		final String application = "--- Application: ";
		sb.append(application);
		sb.append(HelperString.fill('-', LINE_LENGTH - application.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(controller.getReport());
		sb.append(HelperString.NEW_LINE);
		
		// Java
		final String java = "--- Java: ";
		sb.append(java);
		sb.append(HelperString.fill('-', LINE_LENGTH - java.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportJava());
		sb.append(HelperString.NEW_LINE);

		// OS
		final String os = "--- Operating system: ";
		sb.append(os);
		sb.append(HelperString.fill('-', LINE_LENGTH - os.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportOS());
		sb.append(HelperString.NEW_LINE);

		// User
		final String user = "--- User: ";
		sb.append(user);
		sb.append(HelperString.fill('-', LINE_LENGTH - user.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportUser());
		sb.append(HelperString.NEW_LINE);
		
		// System
		final String system = "--- System: ";
		sb.append(system);
		sb.append(HelperString.fill('-', LINE_LENGTH - system.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportSystem());

		sb.append(HelperString.fill('-', LINE_LENGTH)); 

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