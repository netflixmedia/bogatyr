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
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * This class may be used to transmit client environment info to the server and to decide if an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * It also provides informations about vm memory, temp/user directory and variables.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.1.0
 */
public abstract class HelperEnvironment {
	/**
	 * Returns the used VM memory in bytes.
     *
     * @return used VM memory
     * @since 0.5.0
	 */
	public static long getMemoryUsed() { //$JUnit$
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Returns the free VM memory in bytes.
     *
     * @return free VM memory
     * @since 0.5.0
	 */
	public static long getMemoryFree() { //$JUnit$
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Returns the maximal memory reserved for the VM in bytes.
     *
     * @return max VM memory
     * @since 0.5.0
	 */
	public static long getMemoryMax() { //$JUnit$
		return Runtime.getRuntime().maxMemory();
	}

	/**
	 * Returns the current Java version.
     *
     * @return current Java runtime version
     * @since 0.7.0
	 */
	public static String getJavaVersion() { //$JUnit$
		return System.getProperties().getProperty("java.version"); //$NON-NLS-1$
	}

	/**
	 * Returns the current Java vendor.
     *
     * @return current Java runtime vendor
     * @since 0.7.0
	 */
	public static String getJavaVendor() {
		return System.getProperties().getProperty("java.vendor"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current class path.
     *
     * @return current class path
     * @since 0.7.0
	 */
	public static String getClassPath() { //$JUnit$
		return System.getProperties().getProperty("java.class.path"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current library path.
     *
     * @return current library path
     * @since 0.7.0
	 */
	public static String getLibraryPath() { //$JUnit$
		return System.getProperties().getProperty("java.library.path"); //$NON-NLS-1$
	}
	
	/**
	 * Adds a path to the Java library path.
	 * 
	 * @param path to add
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @since 0.8.0
	 */
	public void addPathToLibraryPath(final File path) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		final String location = path.getAbsolutePath();
		
		final Field field = ClassLoader.class.getDeclaredField("usr_paths"); //$NON-NLS-1$
		field.setAccessible(true);
		final String[] paths = (String[])field.get(null);
		
		for (int ii = 0; ii < paths.length; ii++) {
			if (location.equals(paths[ii])) {
				return;
			}
		}
		
		final String[] tmp = new String[paths.length+1];
		
		System.arraycopy(paths, 0, tmp, 0, paths.length);
		tmp[paths.length] = location;
		field.set(null,tmp);
		
		System.setProperty("java.library.path", System.getProperty("java.library.path") + HelperIO.PATH_SEPARATOR + location); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Returns all available processors for the VM.
     *
     * @return available processors for the VM
     * @since 0.6.0
	 */
	public static int getAvailableProcessors() { //$JUnit$
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Returns the architecture of the operating system.
     *
     * @return OS architecture
     * @since 0.1.0
	 */
	public static String getOsArch() { //$JUnit$
		return System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
	}

	/**
	 * Returns the name of the operating system.
     *
     * @return OS name
     * @since 0.1.0
	 */
	public static String getOsName() { //$JUnit$
		return System.getProperties().getProperty("os.name"); //$NON-NLS-1$
	}

	/**
	 * Returns the version of the operating system.
     *
     * @return OS version
     * @since 0.1.0
	 */
	public static String getOsVersion() { //$JUnit$
		return System.getProperties().getProperty("os.version"); //$NON-NLS-1$
	}

	/**
	 * Returns a {@link Map} containing containing all Java system properties.
	 *
	 * @return map of all Java system properties
	 * @since 0.1.0
	 */
	public static Map<Object, Object> getJavaProperties() {
		return new HashMap<Object, Object>(System.getProperties());
    }
	
	/**
	 * Returns a {@link Map} containing all system environment variables.
	 *
	 * @return map of system environment variables
	 * @since 0.1.0
	 */
	public static Map<String, String> getOsEnvironmentVariables() { //$JUnit$
		return System.getenv();
    }

	/**
	 * Returns the value of a system environment variable.
	 *
	 * @param variable of the environment (e.g. PATH)
	 * @return value of the system environment variable
	 * @since 0.6.0
	 */
	public static String getOsEnvironmentVariable(final String variable) { //$JUnit$
		return System.getenv(variable);
    }
	
	/**
	 * Returns the temporary directory of the current machine.
     *
     * @return OS temporary directory
     * @since 0.5.0
	 */
	public static File getOsTempDirectory() { //$JUnit$
		return new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
	}
	
	/**
	 * Returns the user home directory of the current user.
     *
     * @return user home directory
     * @since 0.5.0
	 */
	public static File getUserHomeDirectory() { //$JUnit$
		return new File(System.getProperty("user.home")); //$NON-NLS-1$
	}
	
	/**
	 * Returns the current user directory.
	 *
	 * @return current user directory
	 * @since 0.5.0
	 */
	public static File getUserDirectory() { //$JUnit$
		return new File(System.getProperty("user.dir")); //$NON-NLS-1$
	}

	/**
	 * Returns the name of the current user.
	 *
	 * @return name of the current user
	 * @since 0.7.0
	 */
	public static String getUserName() { //$JUnit$
		return System.getProperty("user.name"); //$NON-NLS-1$
	}

	/**
	 * Returns the country of the current user.
	 *
	 * @return language of the current country
	 * @since 0.7.0
	 */
	public static String getUserCountry() {
		return System.getProperty("user.country"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the language of the current user.
	 *
	 * @return language of the current user
	 * @since 0.7.0
	 */
	public static String getUserLanguage() {
		return System.getProperty("user.language"); //$NON-NLS-1$
	}
	
	/**
	 * Returns the time zone of the current user.
	 *
	 * @return time zone of the current user
	 * @since 0.7.0
	 */
	public static TimeZone getUserTimezone() {
//		return System.getProperty("user.timezone"); //$NON-NLS-1$
		return TimeZone.getDefault();
		
	}
	
	/**
	 * Try to determine if this application is running under a Windows OS.
	 *
	 * @return true if this application is running under a Windows OS
	 * @since 0.1.0
	 */
	public static boolean isWindowsPlatform() { //$JUnit$
		return getOsName().contains("Windows"); //$NON-NLS-1$
	}

	/**
	 * Try to determine if this application is running under Mac OS.
	 *
	 * @return true if this application is running under Mac OS
	 * @since 0.1.0
	 */
	public static boolean isMacPlatform() { //$JUnit$
		return getOsName().contains("Mac"); //$NON-NLS-1$
	}
	
	/**
	 * Try to determine if this application is running under a UNIX OS.
	 *
	 * @return true if this application is running under UNIX
	 * @since 0.1.0
	 */
	public static boolean isUnixPlatform() { //$JUnit$
		return !isWindowsPlatform() && !isMacPlatform(); //this method is a bit dirty, because it could be another system than Unix, but its the best guess...
	}
}