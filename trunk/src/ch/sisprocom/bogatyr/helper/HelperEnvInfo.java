/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class may be used to transmit client environment info to the server and to decide if
 * an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * It also provides informations about vm memory, temp/user directory and variables.
 * 
 * @author Stefan Laubenberger
 * @version 20081029
 */
public abstract class HelperEnvInfo {
	/**
	 * Used vm memory
	 * Returns the same as Runtime.getRuntime().totalMemory()
     *
     * @return used vm memory
	 */
	public static long getMemoryUsed() {
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Free vm memory
	 * Returns the same as Runtime.getRuntime().freeMemory()
     *
     * @return free vm memory
	 */
	public static long getMemoryFree() {
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Max vm memory
	 * Returns the same as Runtime.getRuntime().maxMemory()
     *
     * @return max vm memory
	 */
	public static long getMemoryMax() {
		return Runtime.getRuntime().maxMemory();
	}
	
	/**
	 * Available processors for the vm
	 * Returns the same as Runtime.getRuntime().availableProcessors()
     *
     * @return available processors for the vm
	 */
	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Returns the same as system property "os.arch"
     *
     * @return OS architecture
	 */
	public static String getOsArch() {
		return System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
	}

	/**
	 * Returns the same as system property "os.name"
     *
     * @return OS name
	 */
	public static String getOsName() {
		return System.getProperties().getProperty("os.name"); //$NON-NLS-1$
	}

	/**
	 * Returns the same as system property "os.version"
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
		final List<String> list = new ArrayList<String>();
		final Map<String, String> map = System.getenv();
		
		for (final Map.Entry<String, String> pair : map.entrySet()) {
            list.add(pair.getKey() + '=' + pair.getValue());
        }
        return list;
    }
	
	/**
	 * Returns the same as system property "java.io.tmpdir"
     *
     * @return OS temporary directory
	 */
	public static File getOsTempDirectory() {
		return new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
	}
	
	/**
	 * Returns the same as system property "user.home"
     *
     * @return user home directory
	 */
	public static File getUserHomeDirectory() {
		return new File(System.getProperty("user.home")); //$NON-NLS-1$
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
		return getOsName().contains("Windows"); //$NON-NLS-1$
	}

	/**
	 * Try to determine whether this application is running under Mac OS
	 * or some other platform by examing the "os.name" property.
	 *
	 * @return true if this application is running under Mac OS
	 */
	public static boolean isMacPlatform() {
		return getOsName().contains("Mac"); //$NON-NLS-1$
	}
}
