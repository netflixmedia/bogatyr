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
package ch.sisprocom.bogatyr.helper;

import ch.sisprocom.bogatyr.helper.converter.ConverterBase64;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.PasswordAuthentication;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * This is a helper class for network operations.
 *
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class HelperNet {
	public static final String PROPERTY_HTTP_PROXY_HOST  = "http.proxyHost"; //$NON-NLS-1$
	public static final String PROPERTY_HTTP_PROXY_PORT  = "http.proxyPort"; //$NON-NLS-1$

	public static final String PROPERTY_HTTPS_PROXY_HOST = "https.proxyHost"; //$NON-NLS-1$
	public static final String PROPERTY_HTTPS_PROXY_PORT = "https.proxyPort"; //$NON-NLS-1$

	public static final String PROPERTY_FTP_PROXY_HOST   = "ftp.proxyHost"; //$NON-NLS-1$
	public static final String PROPERTY_FTP_PROXY_PORT   = "ftp.proxyPort"; //$NON-NLS-1$

	
	/**
     * Enable VM wide use of a proxy for HTTP.
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyHttp(final String host, final int port, final String username, final String password) { //$JUnit
		if (!HelperGeneral.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperGeneral.isValid(username)) {
			throw new IllegalArgumentException("username is null or empty!"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new IllegalArgumentException("password is null!"); //$NON-NLS-1$
		}
		
//    	System.setProperty(PROPERTY_HTTP_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_HTTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTP_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
    }
    
    /**
     * Disable VM wide use of a proxy for HTTP.
     *
     */
    public static void disableProxyHttp() { //$JUnit
//        System.clearProperty(PROPERTY_HTTP_USE_PROXY);
        System.clearProperty(PROPERTY_HTTP_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTP_PROXY_PORT);
    }
    
	/**
     * Enable VM wide use of a proxy for HTTPS.
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyHttps(final String host, final int port, final String username, final String password) { //$JUnit
		if (!HelperGeneral.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperGeneral.isValid(username)) {
			throw new IllegalArgumentException("username is null or empty!"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new IllegalArgumentException("password is null!"); //$NON-NLS-1$
		}
		
//		System.setProperty(PROPERTY_HTTPS_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_HTTPS_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTPS_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
    }
    
    /**
     * Disable VM wide use of a proxy for HTTPS.
     *
     */
    public static void disableProxyHttps() { //$JUnit
//		System.clearProperty(PROPERTY_HTTPS_USE_PROXY);
        System.clearProperty(PROPERTY_HTTPS_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTPS_PROXY_PORT);
    }
    
	/**
     * Enable VM wide use of a proxy for FTP.
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyFtp(final String host, final int port, final String username, final String password) { //$JUnit
		if (!HelperGeneral.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperGeneral.isValid(username)) {
			throw new IllegalArgumentException("username is null or empty!"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new IllegalArgumentException("password is null!"); //$NON-NLS-1$
		}
		
//		System.setProperty(PROPERTY_FTP_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_FTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_FTP_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
    }
    
    /**
     * Disable VM wide use of a proxy for FTP.
     *
     */
    public static void disableProxyFtp() { //$JUnit
//        System.clearProperty(PROPERTY_FTP_USE_PROXY);
        System.clearProperty(PROPERTY_FTP_PROXY_HOST);
        System.clearProperty(PROPERTY_FTP_PROXY_PORT);
    }

    /**
     * Tests if a host is pingable.
     *
     * @param host for the ping command
     * @return true/false
     * @throws IOException
     */
    public static boolean isPingable(final String host) throws IOException { //$JUnit
		if (!HelperGeneral.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		
    	final InetAddress address = InetAddress.getByName(host);

        // Try to reach the specified address within the timeout period.
        // If during this period the address cannot be
        // reached then the method returns false.
		return address.isReachable(Const.VALUE_4096);
    }

    /**
     * Returns the host name of an IP address.
     *
     * @param ip for host name
     * @return host name
     * @throws UnknownHostException
     */
    public static String getHostname(final String ip) throws UnknownHostException { //$JUnit
		if (!HelperGeneral.isValid(ip)) {
			throw new IllegalArgumentException("ip is null or empty!"); //$NON-NLS-1$
		}
		
		final InetAddress address = InetAddress.getByName(ip);

		return address.getHostName();
    }

    /**
     * Returns the local host name of the current machine.
     *
     * @return host name of the current machine
     * @throws UnknownHostException
     */
    public static String getLocalHostname() throws UnknownHostException { //$JUnit
		final InetAddress address = InetAddress.getLocalHost();
		
		return address.getHostName();
    }

    /**
     * Returns the IP for an host name.
     *
     * @param host for IP
     * @return IP of the host name
     * @throws UnknownHostException
     */
    public static String getIp(final String host) throws UnknownHostException { //$JUnit
		if (!HelperGeneral.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		
		final InetAddress address = InetAddress.getByName(host);
		
		return address.getHostAddress();
    }

    /**
     * Returns the local IP address of the current machine.
     *
     * @return IP of the current machine 
     * @throws UnknownHostException
     */
    public static String getLocalIp() throws UnknownHostException { //$JUnit
		final InetAddress address = InetAddress.getLocalHost();
		
		return address.getHostAddress();
   }

    /**
     * Returns all IP addresses of the current machine.
     *
     * @return List with IP addresses of the current machine
     * @throws UnknownHostException
     */
    public static Collection<String> getLocalIps() throws UnknownHostException { //$JUnit
    	final Collection<String> list = new ArrayList<String>();
        final String localHost = InetAddress.getLocalHost().getHostName();

        for (final InetAddress address : InetAddress.getAllByName(localHost)) {
            list.add(address.getHostAddress());
        }
        return list;
    }

    /**
     * Returns all network interfaces of the current machine.
     *
     * @return List with network interfaces of the current machine
     * @throws SocketException
     */
    public static List<NetworkInterface> getNetworkInterfaces() throws SocketException {
    	return Collections.list(NetworkInterface.getNetworkInterfaces());
    }
    
    /**
     * Returns the MAC address of a given network interface.
     *
     * @param ni network interface to determine the MAC address
     * @return MAC address of a given network interface
     * @throws SocketException
     */
    public static String getMacAddress(final NetworkInterface ni) throws SocketException {
		if (null == ni) {
			throw new IllegalArgumentException("ni is null!"); //$NON-NLS-1$
		}
		
		final StringBuilder sb = new StringBuilder();

		final byte[] hardwareAddress = ni.getHardwareAddress();

		if (hardwareAddress != null) {
			for (int ii = 0; ii < hardwareAddress.length; ii++) {
				sb.append(String.format((0 == ii ? Const.EMPTY_STRING : '-') + "%02X", hardwareAddress[ii])); //$NON-NLS-1$
			}
		}
		return sb.toString();
	}

    /**
     * Returns an {@link InputStream} linked to an {@link URL}.
     *
     * @param url to read
     * @return input stream for the content
     * @throws IOException
     */
    public static InputStream readUrl(final URL url) throws IOException { //$JUnit
		if (null == url) {
			throw new IllegalArgumentException("url is null!"); //$NON-NLS-1$
		}
		
		final URLConnection con = url.openConnection();
		con.setConnectTimeout(Const.VALUE_2048);
		con.connect();
		
		return con.getInputStream();
     }

    /**
     * Returns an {@link InputStream} linked to an {@link URL} with HTTP authentication.
     *
     * @param url to read
     * @param username for the HTTP authentication
     * @param password for the HTTP authentication
     * @return input stream for the content
     * @throws IOException
     */
    public static InputStream readUrl(final URL url, final String username, final String password) throws IOException {
		if (null == url) {
			throw new IllegalArgumentException("url is null!"); //$NON-NLS-1$
		}
		if (!HelperGeneral.isValid(username)) {
			throw new IllegalArgumentException("username is null or empty!"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new IllegalArgumentException("password is null!"); //$NON-NLS-1$
		}
		
    	final URLConnection con = url.openConnection();
		con.setRequestProperty("Authorization", "Basic " + ConverterBase64.encode(username + ':' + password)); //$NON-NLS-1$ //$NON-NLS-2$
		con.setConnectTimeout(Const.VALUE_2048);
		con.connect();
		
		return con.getInputStream();
    }
    	
    
    /*
     * Private methods
     */
    private static void authenticate(final String username, final String password) {
    	Authenticator.setDefault(new MyAuthenticator(username, password));
    }
    
    
    /*
     * Inner classes
     */
    static class MyAuthenticator extends Authenticator {
        private final String username, password;

        MyAuthenticator(final String username, final String password) {
            super();
            this.username = username;
            this.password = password;
        }

    	@Override
		public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password.toCharArray());
        }
    }
}
