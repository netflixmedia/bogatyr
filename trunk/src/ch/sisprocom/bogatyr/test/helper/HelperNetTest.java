/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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

import java.net.URL;

import junit.framework.TestCase;
import ch.sisprocom.bogatyr.helper.HelperNet;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class HelperNetTest extends TestCase { //TODO improve
	public void testReadUrl() {
		try {
//			System.out.println(HelperNet.readUrl(new URL("http://www.kaywa.com/files/qr_chip_de.pdf")).length);
			assertNotNull(HelperNet.readUrl(new URL("http://www.sisprocom.ch")));

//			System.out.println(HelperNet.readUrl(new URL("http://www.immopepper.ch:3000/"), "demo", "pepper").length);
//			assertNotNull(HelperNet.readUrl(new URL("http://www.immopepper.ch:3000/")));
			assertNotNull(HelperNet.readUrl(new URL("http://www.immopepper.ch:3000/"), "demo", "pepper"));
		} catch (Exception ex) {/*fail(ex.getMessage());*/ ex.printStackTrace();}
	}
	
	public void testIsPingable() {
		try {
			assertTrue(HelperNet.isPingable("sisprocom.ch"));
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
			assertNotNull(HelperNet.getIp("www.sisprocom.ch"));
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

