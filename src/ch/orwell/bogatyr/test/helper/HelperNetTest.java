/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.orwell.bogatyr.test.helper;

import java.net.URL;

import junit.framework.TestCase;
import ch.orwell.bogatyr.helper.HelperNet;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080802
 */
public class HelperNetTest extends TestCase {
	public void testReadUrl() {
		try {
//			System.out.println(HelperNet.readUrl(new URL("http://www.kaywa.com/files/qr_chip_de.pdf")).length);
			assertNotNull(HelperNet.readUrl(new URL("http://www.orwell.ch")));
		} catch (Exception ex) {/*fail(ex.getMessage());*/ ex.printStackTrace();}
	}
	
	public void testIsPingable() {
		try {
			assertTrue(HelperNet.isPingable("orwell.ch"));
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			assertFalse(HelperNet.isPingable("192.168.1.12"));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperNet.isPingable("987.654.321.000");
			fail("host invalid");
		} catch (Exception ex) {}

	}
	
	public void testGetHostname() {
		try {
			assertNotNull(HelperNet.getHostname("209.85.129.99"));
//			System.out.println(HelperNet.getHostname("209.85.129.99"));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testGetIp() {
		try {
			assertNotNull(HelperNet.getIp("www.orwell.ch"));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testGetLocalHostname() {
		try {
			assertNotNull(HelperNet.getLocalHostname());
//			System.out.println(HelperNet.getLocalHostname());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
//	public void testGetLocalHostnames() {
//		try {
//			assertNotNull(HelperNet.getLocalHostnames());
//			System.out.println(HelperGeneral.dumpList(HelperNet.getLocalHostnames()));
//		} catch (Exception ex) {fail(ex.getMessage());}
//	}
	
	public void testGetLocalIp() {
		try {
			assertNotNull(HelperNet.getLocalIp());
//			System.out.println(HelperNet.getLocalIp());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testGetLocalIps() {
		try {
			assertNotNull(HelperNet.getLocalIps());
//			System.out.println(HelperGeneral.dumpList(HelperNet.getLocalIps()));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}


