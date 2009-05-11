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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090508
 */
public class HelperEnvInfoTest {
	@Test
	public void testGetMemoryUsed() {
//		System.out.println("Max:" + (HelperEnvInfo.getMemoryMax() / Const.DIVIDER_MEGABYTE));
//		System.out.println("Used:" + (HelperEnvInfo.getMemoryUsed() / Const.DIVIDER_MEGABYTE));
//		System.out.println("Free:" + (HelperEnvInfo.getMemoryFree() / Const.DIVIDER_MEGABYTE));
		assertNotNull(HelperEnvInfo.getMemoryUsed());
		assertEquals(Runtime.getRuntime().totalMemory(), HelperEnvInfo.getMemoryUsed());
		assertTrue(HelperEnvInfo.getMemoryMax() > HelperEnvInfo.getMemoryUsed());
	}
	
	@Test
	public void testGetMemoryFree() {
		assertNotNull(HelperEnvInfo.getMemoryFree());
		assertEquals(Runtime.getRuntime().freeMemory(), HelperEnvInfo.getMemoryFree());
		assertTrue(HelperEnvInfo.getMemoryMax() > HelperEnvInfo.getMemoryFree());
	}
	
	@Test
	public void testGetMemoryMax() {
		assertNotNull(HelperEnvInfo.getMemoryMax());
		assertEquals(Runtime.getRuntime().maxMemory(), HelperEnvInfo.getMemoryMax());
		assertTrue(HelperEnvInfo.getMemoryMax() > HelperEnvInfo.getMemoryUsed() + HelperEnvInfo.getMemoryFree());
	}
	
	@Test
	public void testGetJavaVersion() {
		assertNotNull(HelperEnvInfo.getJavaVersion());
		assertEquals(System.getProperties().getProperty("java.version"), HelperEnvInfo.getJavaVersion()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetClassPath() {
		assertNotNull(HelperEnvInfo.getClassPath());
		assertEquals(System.getProperties().getProperty("java.class.path"), HelperEnvInfo.getClassPath()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetLibraryPath() {
		assertNotNull(HelperEnvInfo.getLibraryPath());
		assertEquals(System.getProperties().getProperty("java.library.path"), HelperEnvInfo.getLibraryPath()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetAvailableProcessors() {
		assertTrue(0 < HelperEnvInfo.getAvailableProcessors());
		assertEquals(Runtime.getRuntime().availableProcessors(), HelperEnvInfo.getAvailableProcessors());
	}

	@Test
	public void testGetOsArch() {
		assertNotNull(HelperEnvInfo.getOsArch());
		assertEquals(System.getProperties().getProperty("os.arch"), HelperEnvInfo.getOsArch()); //$NON-NLS-1$
	}

	@Test
	public void testGetOsName() {
		assertNotNull(HelperEnvInfo.getOsName());
		assertEquals(System.getProperties().getProperty("os.name"), HelperEnvInfo.getOsName()); //$NON-NLS-1$
	}

	@Test
	public void testGetOsVersion() {
		assertNotNull(HelperEnvInfo.getOsVersion());
		assertEquals(System.getProperties().getProperty("os.version"), HelperEnvInfo.getOsVersion()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetOsEnvironmentVariables() {
		assertNotNull(HelperEnvInfo.getOsEnvironmentVariables());
		assertEquals(System.getenv(), HelperEnvInfo.getOsEnvironmentVariables());

//		System.out.println(HelperGeneral.dumpList(HelperEnvInfo.getOsEnvironmentVariables()));
	}
	
	@Test
	public void testGetOsEnvironmentVariable() {
		final String variable = "PATH"; //$NON-NLS-1$
		
		assertNotNull(HelperEnvInfo.getOsEnvironmentVariable(variable));
		assertEquals(System.getenv(variable), HelperEnvInfo.getOsEnvironmentVariable(variable));
	}
	
	@Test
	public void testGetOsTempDirectory() {
		assertNotNull(HelperEnvInfo.getOsTempDirectory());
		assertEquals(new File(System.getProperty("java.io.tmpdir")), HelperEnvInfo.getOsTempDirectory()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetUserHomeDirectory() {
		assertNotNull(HelperEnvInfo.getUserHomeDirectory());
		assertEquals(new File(System.getProperty("user.home")), HelperEnvInfo.getUserHomeDirectory()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetUserDirectory() {
		assertNotNull(HelperEnvInfo.getUserDirectory());
		assertEquals(new File(System.getProperty("user.dir")), HelperEnvInfo.getUserDirectory()); //$NON-NLS-1$
	}
	
	@Test
	public void testGetUserName() {
		assertNotNull(HelperEnvInfo.getUserName());
		assertEquals(System.getProperty("user.name"), HelperEnvInfo.getUserName()); //$NON-NLS-1$
	}
	
	@Test
	public void testIsWindowsPlatform() {
		assertEquals(HelperEnvInfo.getOsName().contains("Windows"), HelperEnvInfo.isWindowsPlatform()); //$NON-NLS-1$
	}
	
	@Test
	public void testIsMacPlatform() {
		assertEquals(HelperEnvInfo.getOsName().contains("Mac"), HelperEnvInfo.isMacPlatform()); //$NON-NLS-1$
	}
	
	@Test
	public void testIsUnixPlatform() {
		assertEquals(!HelperEnvInfo.isWindowsPlatform() && !HelperEnvInfo.isMacPlatform(), HelperEnvInfo.isUnixPlatform()); 
	}
}


