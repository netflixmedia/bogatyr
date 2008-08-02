/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class may be used to transmit client environment info to the server and to decide if
 * an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * 
 * @author Stefan Laubenberger
 * @version 20080729
 */
public abstract class HelperEnvInfo {
	/**
	 * Returns same as system property "os.arch"
     *
     * @return OS architecture
	 */
	public static String getOsArch() {
		return System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
	}

	/**
	 * Returns same as system property "os.name"
     *
     * @return OS name
	 */
	public static String getOsName() {
		return System.getProperties().getProperty("os.name"); //$NON-NLS-1$
	}

	/**
	 * Returns same as system property "os.version"
     *
     * @return OS version
	 */
	public static String getOsVersion() {
		return System.getProperties().getProperty("os.version"); //$NON-NLS-1$
	}

	/**
	 * Returns all system environment variables
	 *
	 * @return list of system environment variables
	 */
	public static List<String> getOsEnvironmentVariables() {
		List<String> list = new ArrayList<String>();
		
		Map<String, String> map = System.getenv();
		
		for (final Map.Entry<String, String> pair : map.entrySet()) {
            list.add(pair.getKey() + '=' + pair.getValue());
        }
        return list;
    }
	
	/**
	 * Returns same as system property "java.io.tmpdir"
     *
     * @return OS temporary directory
	 */
	public static File getOsTempDirectory() {
		return new File(System.getProperty("java.io.tmpdir"));
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
//		return osNameContainsString("Windows"); //$NON-NLS-1$
		return getOsName().contains("Windows"); //$NON-NLS-1$
	}

	/**
	 * Try to determine whether this application is running under Mac OS
	 * or some other platform by examing the "os.name" property.
	 *
	 * @return true if this application is running under Mac OS
	 */
	public static boolean isMacPlatform() {
//		return osNameContainsString("Mac"); //$NON-NLS-1$
		return getOsName().contains("Mac"); //$NON-NLS-1$
	}
}
