/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.model.misc.Platform;


/**
 * This class may be used to transmit client environment info to the server and to decide if an operating system corresponds to the Windows, Mac or a Unix operating system. 
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * It also provides informations about vm memory, temp/user directory and variables.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.1.0
 */
public abstract class HelperEnvironment {
	private static final Logger log = LoggerFactory.getLogger(HelperEnvironment.class);
	
	/**
	 * Returns the used VM memory in bytes.
     *
     * @return used VM memory
     * @since 0.9.0
	 */
	public static long getMemoryUsed() {
		log.debug(HelperLog.methodStart());
		
		final long result = getMemoryTotal() - Runtime.getRuntime().freeMemory();
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the maximal free VM memory in bytes.
     *
     * @return free VM memory
     * @since 0.9.0
	 */
	public static long getMemoryFree() {
		log.debug(HelperLog.methodStart());
		
		final long result = getMemoryMax() - getMemoryUsed();
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the current total VM memory in bytes.
     *
     * @return current total VM memory
     * @since 0.9.0
	 */
	public static long getMemoryTotal() {
		log.debug(HelperLog.methodStart());
		
		final long result = Runtime.getRuntime().totalMemory();
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the maximal reserved VM memory in bytes.
     *
     * @return max VM memory
     * @since 0.9.0
	 */
	public static long getMemoryMax() {
		log.debug(HelperLog.methodStart());
		
		final long result = Runtime.getRuntime().maxMemory();
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the current Java version.
     *
     * @return current Java runtime version
     * @since 0.7.0
	 */
	public static String getJavaVersion() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("java.version"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the current Java vendor.
     *
     * @return current Java runtime vendor
     * @since 0.7.0
	 */
	public static String getJavaVendor() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("java.vendor"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the current Java VM name.
     *
     * @return current Java VM name
     * @since 0.9.0
	 */
	public static String getJavaVmName() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("java.vm.name"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}	
	
	/**
	 * Returns the current Java VM version.
     *
     * @return current Java VM version
     * @since 0.9.0
	 */
	public static String getJavaVmVersion() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("java.vm.version"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}	
	
	/**
	 * Returns the current class path.
     *
     * @return current class path
     * @since 0.7.0
	 */
	public static String getClassPath() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("java.class.path"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the current library path.
     *
     * @return current library path
     * @since 0.7.0
	 */
	public static String getLibraryPath() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("java.library.path"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Adds a path to the Java library path.
	 * 
	 * @param path to add
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @see File
	 * @since 0.8.0
	 */
	public static void addPathToLibraryPath(final File path) throws SecurityException, NoSuchFieldException, IllegalAccessException {
		log.debug(HelperLog.methodStart(path));
		if (null == path) {
			throw new RuntimeExceptionIsNull("path"); //$NON-NLS-1$
		}
		
		final String location = path.getAbsolutePath();
		
		final Field field = ClassLoader.class.getDeclaredField("usr_paths"); //$NON-NLS-1$
		field.setAccessible(true);
		final String[] paths = (String[])field.get(null);

        for (final String path1 : paths) {
            if (location.equals(path1)) {
                return;
            }
        }
		
		final String[] tmp = new String[paths.length + 1];
		
		System.arraycopy(paths, 0, tmp, 0, paths.length);
		tmp[paths.length] = location;
		field.set(null,tmp);
		
		System.setProperty("java.library.path", System.getProperty("java.library.path") + HelperIO.PATH_SEPARATOR + location); //$NON-NLS-1$ //$NON-NLS-2$
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Returns all available processors for the VM.
     *
     * @return available processors for the VM
     * @since 0.6.0
	 */
	public static int getAvailableProcessors() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final int result = Runtime.getRuntime().availableProcessors();
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the architecture of the operating system.
     *
     * @return OS architecture
     * @since 0.1.0
	 */
	public static String getOsArch() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("os.arch"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the name of the operating system.
     *
     * @return OS name
     * @since 0.1.0
	 */
	public static String getOsName() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("os.name"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the version of the operating system.
     *
     * @return OS version
     * @since 0.1.0
	 */
	public static String getOsVersion() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperties().getProperty("os.version"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns a {@link Map} containing containing all Java system properties.
	 *
	 * @return {@link Map} of all Java system properties
	 * @see Map
	 * @since 0.1.0
	 */
	public static Map<Object, Object> getJavaProperties() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final Map<Object, Object> result = System.getProperties();
		
        log.debug(HelperLog.methodExit(result));
        return result;
    }
	
	/**
	 * Returns a {@link Map} containing all system environment variables.
	 *
	 * @return {@link Map} of system environment variables
	 * @see Map
	 * @since 0.1.0
	 */
	public static Map<String, String> getOsEnvironmentVariables() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final Map<String, String> result = System.getenv();
		
        log.debug(HelperLog.methodExit(result));
        return result;
    }

	/**
	 * Returns the value of a system environment variable.
	 *
	 * @param variable of the environment (e.g. PATH)
	 * @return value of the system environment variable
	 * @since 0.6.0
	 */
	public static String getOsEnvironmentVariable(final String variable) { //$JUnit$
		log.debug(HelperLog.methodStart(variable));
		
		final String result = System.getenv(variable);
		
        log.debug(HelperLog.methodExit(result));
        return result;
    }
	
	/**
	 * Returns the temporary directory of the current machine.
     *
     * @return OS temporary directory
     * @see File
     * @since 0.5.0
	 */
	public static File getOsTempDirectory() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final File result = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the user home directory of the current user.
     *
     * @return user home directory
     * @see File
     * @since 0.5.0
	 */
	public static File getUserHomeDirectory() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final File result = new File(System.getProperty("user.home")); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the current user directory.
	 *
	 * @return current user directory
	 * @see File
	 * @since 0.5.0
	 */
	public static File getUserDirectory() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final File result = new File(System.getProperty("user.dir")); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the name of the current user.
	 *
	 * @return name of the current user
	 * @since 0.7.0
	 */
	public static String getUserName() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperty("user.name"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the country of the current user.
	 *
	 * @return language of the current country
	 * @since 0.7.0
	 */
	public static String getUserCountry() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperty("user.country"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the language of the current user.
	 *
	 * @return language of the current user
	 * @since 0.7.0
	 */
	public static String getUserLanguage() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final String result = System.getProperty("user.language"); //$NON-NLS-1$
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}
	
	/**
	 * Returns the {@link TimeZone} of the current user.
	 *
	 * @return {@link TimeZone} of the current user
	 * @see TimeZone
	 * @since 0.7.0
	 */
	public static TimeZone getUserTimezone() { //$JUnit$
		log.debug(HelperLog.methodStart());
		
		final TimeZone result = TimeZone.getDefault();
		
        log.debug(HelperLog.methodExit(result));
        return result;
	}

	/**
	 * Returns the current platform.
	 *
	 * @return {@link Platform}
	 * @since 0.9.0
	 */
	public static Platform getPlatform() {
		log.debug(HelperLog.methodStart());
		
		final Platform result;
		if (isMacPlatform()) {
			result = Platform.MAC_OSX;
		} else if (isWindowsPlatform()) {
			result = Platform.WINDOWS;
		} else {
			return Platform.UNIX; //this is a bit dirty, because it could be another system than Unix, but its the best guess...
		}

        log.debug(HelperLog.methodExit(result));
        return result;
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Try to determine if this application is running under a Windows OS.
	 *
	 * @return true/false
	 * @since 0.1.0
	 */
	private static boolean isWindowsPlatform() {
		log.trace(HelperLog.methodStart());
		
		final boolean result = HelperString.contains(getOsName(), "Windows"); //$NON-NLS-1$
		
		log.trace(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Try to determine if this application is running under Mac OS.
	 *
	 * @return true/false
	 * @since 0.1.0
	 */
	private static boolean isMacPlatform() {
		log.trace(HelperLog.methodStart());
		
		final boolean result = HelperString.contains(getOsName(), "Mac"); //$NON-NLS-1$
		
		log.trace(HelperLog.methodExit(result));
		return result;
	}
}
