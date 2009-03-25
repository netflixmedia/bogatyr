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

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090313
 */
public class HelperEnvInfoTest {
	@Test
	public void testGetMemoryUsed() {
		assertNotNull(HelperEnvInfo.getMemoryUsed());
	}
	
	@Test
	public void testGetMemoryFree() {
		assertNotNull(HelperEnvInfo.getMemoryFree());
	}
	
	@Test
	public void testGetMemoryMax() {
		assertNotNull(HelperEnvInfo.getMemoryMax());
	}
	
	@Test
	public void testGetAvailableProcessors() {
		assertNotNull(HelperEnvInfo.getAvailableProcessors());
	}

	@Test
	public void testGetOsArch() {
		assertNotNull(HelperEnvInfo.getOsArch());
	}

	@Test
	public void testGetOsName() {
		assertNotNull(HelperEnvInfo.getOsArch());
	}

	@Test
	public void testGetOsVersion() {
		assertNotNull(HelperEnvInfo.getOsArch());
	}
	
	@Test
	public void testGetOsEnvironmentVariables() {
		assertNotNull(HelperEnvInfo.getOsEnvironmentVariables());
//		System.out.println(HelperGeneral.dumpList(HelperEnvInfo.getOsEnvironmentVariables()));
	}

	@Test
	public void testGetOsTempDirectory() {
		assertNotNull(HelperEnvInfo.getOsTempDirectory());
	}
	
	@Test
	public void testGetUserHomeDirectory() {
		assertNotNull(HelperEnvInfo.getUserHomeDirectory());
	}
}


