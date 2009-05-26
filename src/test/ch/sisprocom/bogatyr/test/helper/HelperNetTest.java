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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;
import java.net.UnknownHostException;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNet;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090527
 */
public class HelperNetTest {
	@Test
	public void testEnableDisableProxyHttp() {
		final String host = "192.168.1.1"; //$NON-NLS-1$
		final int port = 8080;
		final String user = "Bogatyr"; //$NON-NLS-1$
		final String pw = "1234"; //$NON-NLS-1$
		
		HelperNet.enableProxyHttp(host, port, user, pw);
		
		assertEquals(host, System.getProperty(HelperNet.PROPERTY_HTTP_PROXY_HOST));
		assertEquals(Integer.toString(port), System.getProperty(HelperNet.PROPERTY_HTTP_PROXY_PORT));
		
		HelperNet.disableProxyHttp();
		
		assertNull(System.getProperty(HelperNet.PROPERTY_HTTP_PROXY_HOST));
		assertNull(System.getProperty(HelperNet.PROPERTY_HTTP_PROXY_PORT));

		try {
			HelperNet.enableProxyHttp(null, port, user, pw);
			fail("host is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.enableProxyHttp(host, port, null, pw);
			fail("username is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.enableProxyHttp(host, port, user, null);
			fail("password is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testEnableDisableProxyHttps() {
		final String host = "192.168.1.1"; //$NON-NLS-1$
		final int port = 8080;
		final String user = "Bogatyr"; //$NON-NLS-1$
		final String pw = "1234"; //$NON-NLS-1$
		
		HelperNet.enableProxyHttps(host, port, user, pw);
		
		assertEquals(host, System.getProperty(HelperNet.PROPERTY_HTTPS_PROXY_HOST));
		assertEquals(Integer.toString(port), System.getProperty(HelperNet.PROPERTY_HTTPS_PROXY_PORT));
		
		HelperNet.disableProxyHttps();
		
		assertNull(System.getProperty(HelperNet.PROPERTY_HTTPS_PROXY_HOST));
		assertNull(System.getProperty(HelperNet.PROPERTY_HTTPS_PROXY_PORT));

		try {
			HelperNet.enableProxyHttps(null, port, user, pw);
			fail("host is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.enableProxyHttps(host, port, null, pw);
			fail("username is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.enableProxyHttps(host, port, user, null);
			fail("password is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testEnableDisableProxyFtp() {
		final String host = "192.168.1.1"; //$NON-NLS-1$
		final int port = 8080;
		final String user = "Bogatyr"; //$NON-NLS-1$
		final String pw = "1234"; //$NON-NLS-1$
		
		HelperNet.enableProxyFtp(host, port, user, pw);
		
		assertEquals(host, System.getProperty(HelperNet.PROPERTY_FTP_PROXY_HOST));
		assertEquals(Integer.toString(port), System.getProperty(HelperNet.PROPERTY_FTP_PROXY_PORT));
		
		HelperNet.disableProxyFtp();
		
		assertNull(System.getProperty(HelperNet.PROPERTY_FTP_PROXY_HOST));
		assertNull(System.getProperty(HelperNet.PROPERTY_FTP_PROXY_PORT));

		try {
			HelperNet.enableProxyFtp(null, port, user, pw);
			fail("host is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.enableProxyFtp(host, port, null, pw);
			fail("username is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.enableProxyFtp(host, port, user, null);
			fail("password is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testReadUrl() {
		try {
			assertNotNull(HelperNet.readUrl(new URL("http://www.sisprocom.ch"))); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperNet.readUrl(null);
			fail("url is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testIsPingable() {
		try {
			assertTrue(HelperNet.isPingable("sisprocom.ch")); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			assertFalse(HelperNet.isPingable("192.168.1.12")); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperNet.isPingable("987.654.321.000"); //$NON-NLS-1$
			fail("host is invalid!"); //$NON-NLS-1$
		} catch (UnknownHostException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperNet.isPingable(null);
			fail("host is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetHostname() {
		try {
			assertEquals("orwell.ch", HelperNet.getHostname("78.46.88.137")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperNet.getHostname(null);
			fail("ip is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetIp() {
		try {
			assertEquals("78.46.88.137", HelperNet.getIp("www.sisprocom.ch")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperNet.getIp(null);
			fail("host is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetLocalHostname() { //TODO improve... but how?
		try {
			assertNotNull(HelperNet.getLocalHostname());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testGetLocalIp() { //TODO improve... but how?
		try {
			assertNotNull(HelperNet.getLocalIp());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testGetLocalIps() { //TODO improve... but how?
		try {
			assertNotNull(HelperNet.getLocalIps());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testGetNetworkInterfaces() { //TODO improve... but how?
		try {
			assertNotNull(HelperNet.getNetworkInterfaces());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	
	@Test
	public void testGetMacAddress() {
		try {
			assertNotNull(HelperNet.getMacAddress(HelperNet.getNetworkInterfaces().get(0)));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperNet.getMacAddress(null);
			fail("ni is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
