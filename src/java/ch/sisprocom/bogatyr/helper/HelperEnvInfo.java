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
package ch.sisprocom.bogatyr.helper;

import java.io.File;
import java.util.Map;


/**
 * This class may be used to transmit client environment info to the server and to decide if an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * It also provides informations about vm memory, temp/user directory and variables.
 * 
 * @author Stefan Laubenberger
 * @version 20090508
 */
public abstract class HelperEnvInfo {
	/**
	 * Returns the used VM memory.
     *
     * @return used VM memory
	 */
	public static long getMemoryUsed() {
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Returns the free VM memory.
     *
     * @return free VM memory
	 */
	public static long getMemoryFree() {
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Returns the maximal memory reserved for the VM.
     *
     * @return max VM memory
	 */
	public static long getMemoryMax() {
		return Runtime.getRuntime().maxMemory();
	}

	/**
	 * Returns the current Java runtime version.
     *
     * @return current Java runtime version
	 */
	public static String getJavaVersion() {
		return System.getProperties().getProperty("java.version"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current class path.
     *
     * @return current class path
	 */
	public static String getClassPath() {
		return System.getProperties().getProperty("java.class.path"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current class path.
     *
     * @return current class path
	 */
	public static String getLibraryPath() {
		return System.getProperties().getProperty("java.library.path"); //$NON-NLS-1$
	}
	
	/**
	 * Returns all available processors for the VM.
     *
     * @return available processors for the VM
	 */
	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Returns the architecture of the operating system.
     *
     * @return OS architecture
	 */
	public static String getOsArch() {
		return System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
	}

	/**
	 * Returns the name of the operating system.
     *
     * @return OS name
	 */
	public static String getOsName() {
		return System.getProperties().getProperty("os.name"); //$NON-NLS-1$
	}

	/**
	 * Returns the version of the operating system.
     *
     * @return OS version
	 */
	public static String getOsVersion() {
		return System.getProperties().getProperty("os.version"); //$NON-NLS-1$
	}

	/**
	 * Returns all system environment variables.
	 *
	 * @return map of system environment variables
	 */
	public static Map<String, String> getOsEnvironmentVariables() {
		return System.getenv();
//		final Map<String, String> map = System.getenv();
//		final Collection<String> list = new ArrayList<String>(map.size());
//
//		for (final Map.Entry<String, String> pair : map.entrySet()) {
//            list.add(pair.getKey() + '=' + pair.getValue());
//        }
//        return list;
    }

	/**
	 * Returns the value of a system environment variable.
	 *
	 * @param variable of the environment (e.g. PATH)
	 * @return value of the system environment variable
	 */
	public static String getOsEnvironmentVariable(String variable) {
		return System.getenv(variable);
    }
	
	/**
	 * Returns the temporary directory of the current machine.
     *
     * @return OS temporary directory
	 */
	public static File getOsTempDirectory() {
		return new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
	}
	
	/**
	 * Returns the user home directory of the current user.
     *
     * @return user home directory
	 */
	public static File getUserHomeDirectory() {
		return new File(System.getProperty("user.home")); //$NON-NLS-1$
	}
	
	/**
	* Returns the current user directory.
	*
	* @return current user directory
	*/
	public static File getUserDirectory() {
		return new File(System.getProperty("user.dir")); //$NON-NLS-1$
	}

	/**
	* Returns the name of the current user.
	*
	* @return name of the current user
	*/
	public static String getUserName() {
		return System.getProperty("user.name"); //$NON-NLS-1$
	}
	
	/**
	 * Try to determine if this application is running under a Windows OS.
	 *
	 * @return true if this application is running under a Windows OS
	 */
	public static boolean isWindowsPlatform() {
		return getOsName().contains("Windows"); //$NON-NLS-1$
	}

	/**
	 * Try to determine if this application is running under Mac OS.
	 *
	 * @return true if this application is running under Mac OS
	 */
	public static boolean isMacPlatform() {
		return getOsName().contains("Mac"); //$NON-NLS-1$
	}
	
	/**
	 * Try to determine if this application is running under a UNIX OS.
	 *
	 * @return true if this application is running under UNIX
	 */
	public static boolean isUnixPlatform() {
		return !isWindowsPlatform() && !isMacPlatform(); //this method is a bit dirty, because it could be another system than Unix, but its the best guess...
	}
}
