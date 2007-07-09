/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.util;


/**
 * This class may be used to transmit client environment info to the server and to decide if
 * an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * The class is instanciated on the client by calling and transmitted to the server
 * where the client operating system properties may be read by the server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class EnvironmentInfo {

	/**
	 * Returns same as system property "os.arch".
	 */
	public static String getOsArch() {
		return System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
	}

	/**
	 * Returns same as system property "os.name".
	 */
	public static String getOsName() {
		return System.getProperties().getProperty("os.name"); //$NON-NLS-1$
	}

	/**
	 * Returns same as system property "os.version".
	 */
	public static String getOsVersion() {
		return System.getProperties().getProperty("os.version"); //$NON-NLS-1$
	}

	/**
	 * Try to determine whether this application is running under Windows
	 * or some other platform by examing the "os.name" property.
	 * A check for unix platform is done by checking that it is not the Windows and not the
	 * Mac platform.
	 *
	 * @return true if this application is running under a Windows OS
	 */
	public static boolean isWindowsPlatform() {
		return osNameContainsString("Windows"); //$NON-NLS-1$
	}

	/**
	 * Try to determine whether this application is running under Mac OS
	 * or some other platform by examing the "os.name" property.
	 *
	 * @return true if this application is running under Mac OS
	 */
	public static boolean isMacPlatform() {
		return osNameContainsString("Mac"); //$NON-NLS-1$
	}

	
	/*
	 * Private methods
	 */
	private static boolean osNameContainsString(String s) {
		String osName = getOsName().toUpperCase();
		return (osName != null && osName.indexOf(s.toUpperCase()) >= 0);
	}
}
