/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.test.helper;

import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class HelperEnvironmentTest {
	@Test
	public void testGetMemoryHeapUsed() {
		assertNotNull(HelperEnvironment.getMemoryHeapUsed());
		assertEquals(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), HelperEnvironment.getMemoryHeapUsed());
		assertTrue(HelperEnvironment.getMemoryHeapTotal() > HelperEnvironment.getMemoryHeapUsed());
	}
	
	@Test
	public void testGetMemoryHeapFree() {
		assertNotNull(HelperEnvironment.getMemoryHeapFree());
		assertEquals(Runtime.getRuntime().freeMemory(), HelperEnvironment.getMemoryHeapFree());
		assertTrue(HelperEnvironment.getMemoryHeapTotal() >= HelperEnvironment.getMemoryHeapFree());
	}
	
	@Test
	public void testGetMemoryHeapTotal() {
		assertNotNull(HelperEnvironment.getMemoryHeapTotal());
		assertEquals(Runtime.getRuntime().totalMemory(), HelperEnvironment.getMemoryHeapTotal());
		assertTrue(HelperEnvironment.getMemoryTotal() >= HelperEnvironment.getMemoryHeapTotal());
	}

	@Test
	public void testGetMemoryStack() {
		assertNotNull(HelperEnvironment.getMemoryStack());
		assertEquals(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory(), HelperEnvironment.getMemoryStack());
		assertTrue(HelperEnvironment.getMemoryTotal() >= HelperEnvironment.getMemoryStack());
	}
	
	@Test
	public void testGetMemoryTotal() {
		assertNotNull(HelperEnvironment.getMemoryTotal());
		assertEquals(Runtime.getRuntime().maxMemory(), HelperEnvironment.getMemoryTotal());
		assertTrue(HelperEnvironment.getMemoryTotal() >= HelperEnvironment.getMemoryHeapTotal() + HelperEnvironment.getMemoryStack());
	}
	
	@Test
	public void testGetJavaVersion() {
		assertNotNull(HelperEnvironment.getJavaVersion());
		assertEquals(System.getProperties().getProperty("java.version"), HelperEnvironment.getJavaVersion()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetClassPath() {
		assertNotNull(HelperEnvironment.getClassPath());
		assertEquals(System.getProperties().getProperty("java.class.path"), HelperEnvironment.getClassPath()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetLibraryPath() {
		assertNotNull(HelperEnvironment.getLibraryPath());
		assertEquals(System.getProperties().getProperty("java.library.path"), HelperEnvironment.getLibraryPath()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetAvailableProcessors() {
		assertTrue(0 < HelperEnvironment.getAvailableProcessors());
		assertEquals(Runtime.getRuntime().availableProcessors(), HelperEnvironment.getAvailableProcessors());
	}

	@Test
	public void testGetOsArch() {
		assertNotNull(HelperEnvironment.getOsArch());
		assertEquals(System.getProperties().getProperty("os.arch"), HelperEnvironment.getOsArch()); //$NON-NLS-1$
	}

	@Test
	public void testGetOsName() {
		assertNotNull(HelperEnvironment.getOsName());
		assertEquals(System.getProperties().getProperty("os.name"), HelperEnvironment.getOsName()); //$NON-NLS-1$
	}

	@Test
	public void testGetOsVersion() {
		assertNotNull(HelperEnvironment.getOsVersion());
		assertEquals(System.getProperties().getProperty("os.version"), HelperEnvironment.getOsVersion()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetOsEnvironmentVariables() {
		assertNotNull(HelperEnvironment.getOsEnvironmentVariables());
		assertEquals(System.getenv(), HelperEnvironment.getOsEnvironmentVariables());

//		System.out.println(HelperGeneral.dumpList(HelperEnvInfo.getOsEnvironmentVariables()));
	}
	
	@Test
	public void testGetOsEnvironmentVariable() {
		final String variable = "PATH"; //$NON-NLS-1$
		
		assertNotNull(HelperEnvironment.getOsEnvironmentVariable(variable));
		assertEquals(System.getenv(variable), HelperEnvironment.getOsEnvironmentVariable(variable));
	}
	
	@Test
	public void testGetOsTempDirectory() {
		assertNotNull(HelperEnvironment.getOsTempDirectory());
		assertEquals(new File(System.getProperty("java.io.tmpdir")), HelperEnvironment.getOsTempDirectory()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetUserHomeDirectory() {
		assertNotNull(HelperEnvironment.getUserHomeDirectory());
		assertEquals(new File(System.getProperty("user.home")), HelperEnvironment.getUserHomeDirectory()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetUserDirectory() {
		assertNotNull(HelperEnvironment.getUserDirectory());
		assertEquals(new File(System.getProperty("user.dir")), HelperEnvironment.getUserDirectory()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetUserName() {
		assertNotNull(HelperEnvironment.getUserName());
		assertEquals(System.getProperty("user.name"), HelperEnvironment.getUserName()); //$NON-NLS-1$
	}
	
	@Test
	public void testIsWindowsPlatform() {
		assertEquals(HelperEnvironment.getOsName().contains("Windows"), HelperEnvironment.isWindowsPlatform()); //$NON-NLS-1$
	}
	
	@Test
	public void testIsMacPlatform() {
		assertEquals(HelperEnvironment.getOsName().contains("Mac"), HelperEnvironment.isMacPlatform()); //$NON-NLS-1$
	}
	
	@Test
	public void testIsUnixPlatform() {
		assertEquals(!HelperEnvironment.isWindowsPlatform() && !HelperEnvironment.isMacPlatform(), HelperEnvironment.isUnixPlatform()); 
	}
}


