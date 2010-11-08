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
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

/**
 * This is a helper class for logging.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101108)
 * @since 0.9.1
 */
public abstract class HelperLog {
	private static final String ID_APPLICATION_START = "+++"; //$NON-NLS-1$
	private static final String ID_APPLICATION_EXIT = "---"; //$NON-NLS-1$
	private static final String ID_METHOD_START = ">>>"; //$NON-NLS-1$
	private static final String ID_METHOD_EXIT = "<<<"; //$NON-NLS-1$
	private static final String ID_CONSTRUCTOR = "***"; //$NON-NLS-1$
	
	private static final int LINE_LENGTH = 80;
	
	private static final String NULL = " null"; //$NON-NLS-1$
	private static final String EMPTY = " empty"; //$NON-NLS-1$

	public static String applicationStart() { // $JUnit$
		final StringBuilder sb = new StringBuilder();

		sb.append(ID_APPLICATION_START);
		sb.append(HelperString.NEW_LINE);
		
		sb.append(getReport());

		sb.append(HelperString.fill('-', LINE_LENGTH)); 

		return sb.toString();
	}
	
	public static String applicationStart(final ControllerApplication<?, ?> controller) { // $JUnit$
		if (null == controller) {
			throw new RuntimeExceptionIsNull("controller"); //$NON-NLS-1$
		}
		
		final StringBuilder sb = new StringBuilder();

		sb.append(ID_APPLICATION_START);
		sb.append(HelperString.NEW_LINE);
		
		// Application
		final String application = "### Application: "; //$NON-NLS-1$
		sb.append(application);
		sb.append(HelperString.fill('#', LINE_LENGTH - application.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(controller.getReport());
		sb.append(HelperString.NEW_LINE);
		
		sb.append(getReport());

		sb.append(HelperString.fill('-', LINE_LENGTH)); 

		return sb.toString();
	}

	public static String applicationExit(final int returnCode) { // $JUnit$
		return ID_APPLICATION_EXIT + HelperString.SPACE + returnCode;
	}

	public static String methodStart() {
		return ID_METHOD_START;
	}

	public static String methodStart(final Object... args) { // $JUnit$
		final StringBuilder sb = new StringBuilder();
		sb.append(ID_METHOD_START);

		sb.append(getArgsAsString(args));
		
		return sb.toString();
	}

	public static String methodExit() { // $JUnit$
		return ID_METHOD_EXIT;
	}

	public static String methodExit(final Object arg) { // $JUnit$
		return ID_METHOD_EXIT + HelperString.SPACE + String.valueOf(arg);
	}

	public static String constructor() {
		return ID_CONSTRUCTOR;
	}

	public static String constructor(final Object... args) { // $JUnit$
		final StringBuilder sb = new StringBuilder();
		sb.append(ID_CONSTRUCTOR);

		sb.append(getArgsAsString(args));
		
		return sb.toString();
	}
	
	
	/*
	 * Private methods
	 */
	
	private static String getArgsAsString(final Object... args) {
		if (null != args) {
			final StringBuilder sb = new StringBuilder();
			if (0 == args.length) {
				sb.append(EMPTY);
			} else {
				for (final Object obj : args) {
					if (sb.length() > 0) {
						sb.append(HelperString.COMMA);
					}
					sb.append(HelperString.SPACE);
					sb.append(String.valueOf(obj));
				}
			}
			return sb.toString();
		}
		
		return NULL;
	}

	private static String getReport() {
		final StringBuilder sb = new StringBuilder();

		// Java
		final String java = "### Java: "; //$NON-NLS-1$
		sb.append(java);
		sb.append(HelperString.fill('#', LINE_LENGTH - java.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportJava());
		sb.append(HelperString.NEW_LINE);

		// OS
		final String os = "### Operating system: "; //$NON-NLS-1$
		sb.append(os);
		sb.append(HelperString.fill('#', LINE_LENGTH - os.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportOS());
		sb.append(HelperString.NEW_LINE);

		// User
		final String user = "### User: "; //$NON-NLS-1$
		sb.append(user);
		sb.append(HelperString.fill('#', LINE_LENGTH - user.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportUser());
		sb.append(HelperString.NEW_LINE);
		
		// System
		final String system = "### System: "; //$NON-NLS-1$
		sb.append(system);
		sb.append(HelperString.fill('#', LINE_LENGTH - system.length())); 
		sb.append(HelperString.NEW_LINE);
		sb.append(HelperEnvironment.getReportSystem());

		return sb.toString();
	}
}