/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.model.misc.Country;
import net.laubenberger.bogatyr.model.misc.Language;
import net.laubenberger.bogatyr.model.misc.Platform;
import net.laubenberger.bogatyr.model.unit.Bit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class may be used to transmit client environment info to the server and to decide if an operating system corresponds to the Windows, Mac or a Unix operating system.
 * It encapsulates name, architecture and version of an operating system, based on system properties.
 * It also provides informations about vm memory, temp/user directory and variables.
 *
 * @author Stefan Laubenberger
 * @version 0.9.6 (20110517)
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
	public static long getMemoryUsed() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final long result = getMemoryTotal() - Runtime.getRuntime().freeMemory();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the maximal free VM memory in bytes.
	 *
	 * @return free VM memory
	 * @since 0.9.0
	 */
	public static long getMemoryFree() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final long result = getMemoryMax() - getMemoryUsed();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current total VM memory in bytes.
	 *
	 * @return current total VM memory
	 * @since 0.9.0
	 */
	public static long getMemoryTotal() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final long result = Runtime.getRuntime().totalMemory();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the maximal reserved VM memory in bytes.
	 *
	 * @return max VM memory
	 * @since 0.9.0
	 */
	public static long getMemoryMax() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final long result = Runtime.getRuntime().maxMemory();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current Java version.
	 *
	 * @return current Java runtime version
	 * @since 0.7.0
	 */
	public static String getJavaVersion() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("java.version"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current Java vendor.
	 *
	 * @return current Java runtime vendor
	 * @since 0.7.0
	 */
	public static String getJavaVendor() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("java.vendor"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current Java VM name.
	 *
	 * @return current Java VM name
	 * @since 0.9.0
	 */
	public static String getJavaVmName() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("java.vm.name"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current Java VM version.
	 *
	 * @return current Java VM version
	 * @since 0.9.0
	 */
	public static String getJavaVmVersion() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("java.vm.version"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Map<Object, Object> result = System.getProperties();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
	 * Returns the current class path.
	 *
	 * @return current class path
	 * @since 0.7.0
	 */
	public static String getClassPath() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("java.class.path"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current library path.
	 *
	 * @return current library path
	 * @since 0.7.0
	 */
	public static String getLibraryPath() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("java.library.path"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(path));
		if (null == path) {
			throw new RuntimeExceptionIsNull("path"); //$NON-NLS-1$
		}

		final String location = path.getAbsolutePath();

		final Field field = ClassLoader.class.getDeclaredField("usr_paths"); //$NON-NLS-1$
		field.setAccessible(true);
		final String[] paths = (String[]) field.get(null);

		if (!HelperArray.contains(paths, location)) {
			final String[] tmp = new String[paths.length + 1];

			System.arraycopy(paths, 0, tmp, 0, paths.length);
			tmp[paths.length] = location;
			field.set(null, tmp);

			System.setProperty("java.library.path", System.getProperty("java.library.path") + HelperIO.PATH_SEPARATOR + location); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Returns all available processors (cores) for the VM.
	 *
	 * @return available processors (cores) for the VM
	 * @since 0.6.0
	 */
	public static int getAvailableProcessors() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final int result = Runtime.getRuntime().availableProcessors();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the architecture of the operating system.
	 *
	 * @return OS architecture
	 * @since 0.1.0
	 */
	public static String getOsArch() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("os.arch"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the name of the operating system.
	 *
	 * @return OS name
	 * @since 0.1.0
	 */
	public static String getOsName() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("os.name"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the version of the operating system.
	 *
	 * @return OS version
	 * @since 0.1.0
	 */
	public static String getOsVersion() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperties().getProperty("os.version"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Map<String, String> result = System.getenv();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(variable));
		if (null == variable) {
			throw new RuntimeExceptionIsNull("variable"); //$NON-NLS-1$
		}
		
		final String result = System.getenv(variable);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final File result = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final File result = new File(System.getProperty("user.home")); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final File result = new File(System.getProperty("user.dir")); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the name of the current user.
	 *
	 * @return name of the current user
	 * @since 0.7.0
	 */
	public static String getUserName() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String result = System.getProperty("user.name"); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the country of the current user.
	 *
	 * @return language of the current country
	 * @since 0.7.0
	 */
	public static Country getUserCountry() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String countryString = System.getProperty("user.country"); //$NON-NLS-1$
		Country result = null;
		
		for (final Country country : Country.values()) {
	     if (HelperObject.isEquals(countryString, country.getCode())) {
	   	  result = country;
	   	  break;
	     }
	   }  

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the {@link Language} of the current user.
	 *
	 * @return language of the current user
	 * @since 0.7.0
	 */
	public static Language getUserLanguage() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final String languageString = System.getProperty("user.language"); //$NON-NLS-1$
		Language result = null;
		
		for (final Language language : Language.values()) {
	     if (HelperObject.isEquals(languageString, language.getCode())) {
	   	  result = language;
	   	  break;
	     }
	   }  
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final TimeZone result = TimeZone.getDefault();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current platform.
	 *
	 * @return {@link Platform}
	 * @since 0.9.0
	 */
	public static Platform getPlatform() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Platform result;
		if (isMacPlatform()) {
			result = Platform.MAC_OSX;
		} else if (isWindowsPlatform()) {
			result = Platform.WINDOWS;
		} else {
			return Platform.UNIX; //this is a bit dirty, because it could be another system than Unix, but its the best guess...
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a report about the current Java environment.
	 *
	 * @return report about the current Java environment
	 * @since 0.9.3
	 */
	public static Map<String, Object> getReportJava() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("version", getJavaVersion()); //$NON-NLS-1$
		result.put("vendor", getJavaVendor()); //$NON-NLS-1$
		result.put("VM name", getJavaVmName()); //$NON-NLS-1$
		result.put("VM version", getJavaVmVersion()); //$NON-NLS-1$
		result.put("Java properties", HelperMap.dump(getJavaProperties())); //$NON-NLS-1$
		result.put("class path", getClassPath()); //$NON-NLS-1$
		result.put("library path", getLibraryPath()); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a report about the current operating system.
	 *
	 * @return report about the current operating system
	 * @since 0.9.3
	 */
	public static Map<String, Object> getReportOS() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("platform", getPlatform()); //$NON-NLS-1$
		result.put("name", getOsName()); //$NON-NLS-1$
		result.put("version", getOsVersion()); //$NON-NLS-1$
		result.put("architecture", getOsArch()); //$NON-NLS-1$
		result.put("temporary directory", getOsTempDirectory()); //$NON-NLS-1$
		result.put("environment variables", HelperMap.dump(getOsEnvironmentVariables())); //$NON-NLS-1$
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a report about the current user.
	 *
	 * @return report about the current user
	 * @since 0.9.3
	 */
	public static Map<String, Object> getReportUser() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("name", getUserName()); //$NON-NLS-1$
		result.put("home directory", getUserHomeDirectory()); //$NON-NLS-1$
		result.put("directory", getUserDirectory()); //$NON-NLS-1$
		result.put("country", getUserCountry()); //$NON-NLS-1$
		result.put("language", getUserLanguage()); //$NON-NLS-1$
		result.put("timezone", getUserTimezone()); //$NON-NLS-1$
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a report about the current system environment.
	 *
	 * @return report about the current system environment
	 * @since 0.9.3
	 */
	public static Map<String, Object> getReportSystem() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		final Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("processors/cores", getAvailableProcessors()); //$NON-NLS-1$
		if (null != HelperScreen.getCurrentScreenSize()) result.put("current screen size", HelperScreen.getCurrentScreenSize()); //$NON-NLS-1$
		if (null != HelperScreen.getCurrentColorModel()) result.put("current color model", HelperScreen.getCurrentColorModel()); //$NON-NLS-1$
		if (0 < HelperScreen.getCurrentScreenResolution()) result.put("current screen resolution", HelperScreen.getCurrentScreenResolution() + " DPI"); //$NON-NLS-1$ //$NON-NLS-2$
		result.put("memory maximum", Bit.BYTE.convertTo(Bit.MEGABYTE, getMemoryMax()).setScale(3, BigDecimal.ROUND_DOWN) + " MB"); //$NON-NLS-1$ //$NON-NLS-2$
		result.put("memory total", Bit.BYTE.convertTo(Bit.MEGABYTE, getMemoryTotal()).setScale(3, BigDecimal.ROUND_DOWN) + " MB"); //$NON-NLS-1$ //$NON-NLS-2$
		result.put("memory free", Bit.BYTE.convertTo(Bit.MEGABYTE, getMemoryFree()).setScale(3, BigDecimal.ROUND_DOWN) + " MB"); //$NON-NLS-1$ //$NON-NLS-2$
		result.put("memory used", Bit.BYTE.convertTo(Bit.MEGABYTE, getMemoryUsed()).setScale(3, BigDecimal.ROUND_DOWN) + " MB"); //$NON-NLS-1$ //$NON-NLS-2$

		final List<File> files = HelperIO.getAvailableDrives();

		for (final File root : files) {

			result.put("drive" + root.getName() + " path", root.getAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$
			result.put("drive" + root.getName() + " space total", Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceTotal(root)).setScale(3, BigDecimal.ROUND_DOWN) + " GB"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			result.put("drive" + root.getName() + " space free", Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceFree(root)).setScale(3, BigDecimal.ROUND_DOWN) + " GB"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			result.put("drive" + root.getName() + " space used", Bit.BYTE.convertTo(Bit.GIGABYTE, HelperIO.getSpaceUsed(root)).setScale(3, BigDecimal.ROUND_DOWN) + " GB"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		final boolean result = HelperString.contains(getOsName(), "Windows"); //$NON-NLS-1$

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Try to determine if this application is running under Mac OS.
	 *
	 * @return true/false
	 * @since 0.1.0
	 */
	private static boolean isMacPlatform() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		final boolean result = HelperString.contains(getOsName(), "Mac"); //$NON-NLS-1$

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit(result));
		return result;
	}
}
