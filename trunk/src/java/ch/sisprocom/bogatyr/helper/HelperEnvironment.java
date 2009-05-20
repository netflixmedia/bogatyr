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
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * This class may be used to transmit client environment info to the server and to decide if an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * It also provides informations about vm memory, temp/user directory and variables.
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public abstract class HelperEnvironment {
	/**
	 * Returns the used VM memory in bytes.
     *
     * @return used VM memory
	 */
	public static long getMemoryUsed() { //$JUnit
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Returns the free VM memory in bytes.
     *
     * @return free VM memory
	 */
	public static long getMemoryFree() { //$JUnit
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Returns the maximal memory reserved for the VM in bytes.
     *
     * @return max VM memory
	 */
	public static long getMemoryMax() { //$JUnit
		return Runtime.getRuntime().maxMemory();
	}

	/**
	 * Returns the current Java version.
     *
     * @return current Java runtime version
	 */
	public static String getJavaVersion() { //$JUnit
		return System.getProperties().getProperty("java.version"); //$NON-NLS-1$
	}

	/**
	 * Returns the current Java vendor.
     *
     * @return current Java runtime vendor
	 */
	public static String getJavaVendor() {
		return System.getProperties().getProperty("java.vendor"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current class path.
     *
     * @return current class path
	 */
	public static String getClassPath() { //$JUnit
		return System.getProperties().getProperty("java.class.path"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current class path.
     *
     * @return current class path
	 */
	public static String getLibraryPath() { //$JUnit
		return System.getProperties().getProperty("java.library.path"); //$NON-NLS-1$
	}
	
	/**
	 * Returns all available processors for the VM.
     *
     * @return available processors for the VM
	 */
	public static int getAvailableProcessors() { //$JUnit
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Returns the architecture of the operating system.
     *
     * @return OS architecture
	 */
	public static String getOsArch() { //$JUnit
		return System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
	}

	/**
	 * Returns the name of the operating system.
     *
     * @return OS name
	 */
	public static String getOsName() { //$JUnit
		return System.getProperties().getProperty("os.name"); //$NON-NLS-1$
	}

	/**
	 * Returns the version of the operating system.
     *
     * @return OS version
	 */
	public static String getOsVersion() { //$JUnit
		return System.getProperties().getProperty("os.version"); //$NON-NLS-1$
	}

	/**
	 * Returns a {@link Map} containing containing all Java system properties.
	 *
	 * @return map of all Java system properties
	 */
	public static Map<Object, Object> getJavaProperties() {
		return new HashMap<Object, Object>(System.getProperties());
    }
	
	/**
	 * Returns a {@link Map} containing all system environment variables.
	 *
	 * @return map of system environment variables
	 */
	public static Map<String, String> getOsEnvironmentVariables() { //$JUnit
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
	public static String getOsEnvironmentVariable(final String variable) { //$JUnit
		return System.getenv(variable);
    }
	
	/**
	 * Returns the temporary directory of the current machine.
     *
     * @return OS temporary directory
	 */
	public static File getOsTempDirectory() { //$JUnit
		return new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
	}
	
	/**
	 * Returns the user home directory of the current user.
     *
     * @return user home directory
	 */
	public static File getUserHomeDirectory() { //$JUnit
		return new File(System.getProperty("user.home")); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current user directory.
	 *
	 * @return current user directory
	 */
	public static File getUserDirectory() { //$JUnit
		return new File(System.getProperty("user.dir")); //$NON-NLS-1$
	}

	/**
	 * Returns the name of the current user.
	 *
	 * @return name of the current user
	 */
	public static String getUserName() { //$JUnit
		return System.getProperty("user.name"); //$NON-NLS-1$
	}

	/**
	 * Returns the country of the current user.
	 *
	 * @return language of the current country
	 */
	public static String getUserCountry() {
		return System.getProperty("user.country"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the language of the current user.
	 *
	 * @return language of the current user
	 */
	public static String getUserLanguage() {
		return System.getProperty("user.language"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the time zone of the current user.
	 *
	 * @return time zone of the current user
	 */
	public static TimeZone getUserTimezone() {
//		return System.getProperty("user.timezone"); //$NON-NLS-1$
		return TimeZone.getDefault();
		
	}
	
	/**
	 * Try to determine if this application is running under a Windows OS.
	 *
	 * @return true if this application is running under a Windows OS
	 */
	public static boolean isWindowsPlatform() { //$JUnit
		return getOsName().contains("Windows"); //$NON-NLS-1$
	}

	/**
	 * Try to determine if this application is running under Mac OS.
	 *
	 * @return true if this application is running under Mac OS
	 */
	public static boolean isMacPlatform() { //$JUnit
		return getOsName().contains("Mac"); //$NON-NLS-1$
	}
	
	/**
	 * Try to determine if this application is running under a UNIX OS.
	 *
	 * @return true if this application is running under UNIX
	 */
	public static boolean isUnixPlatform() { //$JUnit
		return !isWindowsPlatform() && !isMacPlatform(); //this method is a bit dirty, because it could be another system than Unix, but its the best guess...
	}
}
