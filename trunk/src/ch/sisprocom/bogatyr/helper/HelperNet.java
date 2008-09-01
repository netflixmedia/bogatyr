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
package ch.sisprocom.bogatyr.helper;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.PasswordAuthentication;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is a helper class for network operations
 *
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class HelperNet {
	private static final String PROPERTY_HTTP_USE_PROXY   = "http.useProxy"; //$NON-NLS-1$
	private static final String PROPERTY_HTTP_PROXY_HOST  = "http.proxyHost"; //$NON-NLS-1$
	private static final String PROPERTY_HTTP_PROXY_PORT  = "http.proxyPort"; //$NON-NLS-1$

	private static final String PROPERTY_HTTPS_USE_PROXY  = "https.useProxy"; //$NON-NLS-1$
	private static final String PROPERTY_HTTPS_PROXY_HOST = "https.proxyHost"; //$NON-NLS-1$
	private static final String PROPERTY_HTTPS_PROXY_PORT = "https.proxyPort"; //$NON-NLS-1$

	private static final String PROPERTY_FTP_USE_PROXY    = "ftp.useProxy"; //$NON-NLS-1$
	private static final String PROPERTY_FTP_PROXY_HOST   = "ftp.proxyHost"; //$NON-NLS-1$
	private static final String PROPERTY_FTP_PROXY_PORT   = "ftp.proxyPort"; //$NON-NLS-1$

	
	/**
     * Enable VM wide use of a proxy for HTTP
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyHttp(final String host, final int port, final String username, final String password) {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "enableProxyHttp", new Object[]{host, port, username, password});  //$NON-NLS-1$

    	System.setProperty(PROPERTY_HTTP_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_HTTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTP_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
          
		Logger.getInstance().writeMethodExit(HelperNet.class, "enableProxyHttp");  //$NON-NLS-1$
    }
    
    /**
     * Disable VM wide use of a proxy for HTTP
     *
     */
    public static void disableProxyHttp() {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "disableProxyHttp");  //$NON-NLS-1$

        System.clearProperty(PROPERTY_HTTP_USE_PROXY);
        System.clearProperty(PROPERTY_HTTP_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTP_PROXY_PORT);

		Logger.getInstance().writeMethodExit(HelperNet.class, "disableProxyHttp");  //$NON-NLS-1$
    }
    
	/**
     * Enable VM wide use of a proxy for HTTPS
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyHttps(final String host, final int port, final String username, final String password) {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "enableProxyHttps", new Object[]{host, port, username, password});  //$NON-NLS-1$

		System.setProperty(PROPERTY_HTTPS_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_HTTPS_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTPS_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
        
		Logger.getInstance().writeMethodExit(HelperNet.class, "enableProxyHttps");  //$NON-NLS-1$
    }
    
    /**
     * Disable VM wide use of a proxy for HTTPS
     *
     */
    public static void disableProxyHttps() {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "disableProxyHttps");  //$NON-NLS-1$

		System.clearProperty(PROPERTY_HTTPS_USE_PROXY);
        System.clearProperty(PROPERTY_HTTPS_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTPS_PROXY_PORT);

        Logger.getInstance().writeMethodExit(HelperNet.class, "disableProxyHttps");  //$NON-NLS-1$
    }
    
	/**
     * Enable VM wide use of a proxy for FTP
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyFtp(final String host, final int port, final String username, final String password) {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "enableProxyFtp", new Object[]{host, port, username, password});  //$NON-NLS-1$

		System.setProperty(PROPERTY_FTP_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_FTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_FTP_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);

        Logger.getInstance().writeMethodExit(HelperNet.class, "enableProxyFtp");  //$NON-NLS-1$
    }
    
    /**
     * Disable VM wide use of a proxy for FTP
     *
     */
    public static void disableProxyFtp() {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "disableProxyFtp");  //$NON-NLS-1$

        System.clearProperty(PROPERTY_FTP_USE_PROXY);
//        System.clearProperty(PROPERTY_FTP_PROXY_SET);
        System.clearProperty(PROPERTY_FTP_PROXY_HOST);
        System.clearProperty(PROPERTY_FTP_PROXY_PORT);

		Logger.getInstance().writeMethodExit(HelperNet.class, "disableProxyFtp");  //$NON-NLS-1$
    }

    /**
     * Tests if a host is pingable
     *
     * @param host for ping
     * @return true/false
     * @throws java.io.IOException
     */
    public static boolean isPingable(final String host) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "isPingable", host);  //$NON-NLS-1$

		final InetAddress address = InetAddress.getByName(host);

        // Try to reach the specified address within the timeout period.
        // If during this period the address cannot be
        // reached then the method returns false.
		final boolean result = address.isReachable(3000);
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "isPingable", result);  //$NON-NLS-1$
		return result;
    }

    /**
     * Returns the host name of an IP address
     *
     * @param ip for host name
     * @return host name
     * @throws java.net.UnknownHostException
     */
    public static String getHostname(final String ip) throws UnknownHostException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "getHostname", ip);  //$NON-NLS-1$

		final InetAddress address = InetAddress.getByName(ip);
		final String result = address.getHostName();
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "getHostname", result);  //$NON-NLS-1$
        return result;
    }

    /**
     * Returns the local host name of the machine
     *
     * @return host name
     * @throws java.net.UnknownHostException
     */
    public static String getLocalHostname() throws UnknownHostException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "getLocalHostname");  //$NON-NLS-1$

		final InetAddress address = InetAddress.getLocalHost();
		final String result = address.getHostName();

		Logger.getInstance().writeMethodExit(HelperNet.class, "getLocalHostname", result);  //$NON-NLS-1$
        return result;
    }

    /**
     * Returns the ip for an host name
     *
     * @param hostname for IP
     * @return IP
     * @throws java.net.UnknownHostException
     */
    public static String getIp(final String hostname) throws UnknownHostException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "getIp", hostname);  //$NON-NLS-1$

		final InetAddress address = InetAddress.getByName(hostname);
		final String result = address.getHostAddress();
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "getIp", result);  //$NON-NLS-1$
        return result;
    }

    /**
     * Returns the local IP address
     *
     * @return IP
     * @throws java.net.UnknownHostException
     */
    public static String getLocalIp() throws UnknownHostException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "getLocalIp");  //$NON-NLS-1$

		final InetAddress address = InetAddress.getLocalHost();
		final String result = address.getHostAddress();
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "getLocalIp", result);  //$NON-NLS-1$
        return result;
   }

    /**
     * Returns the all IP addresses of the machine
     *
     * @return List with IP addresses
     * @throws java.net.UnknownHostException
     */
    public static List<String> getLocalIps() throws UnknownHostException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "getLocalIps");  //$NON-NLS-1$

    	final List<String> list = new ArrayList<String>();
        final String localHost = InetAddress.getLocalHost().getHostName();

        for (InetAddress address : InetAddress.getAllByName(localHost)) {
            list.add(address.getHostAddress());
        }

		Logger.getInstance().writeMethodExit(HelperNet.class, "getLocalIps", list);  //$NON-NLS-1$
        return list;
    }

    /**
     * Returns the all network interfaces of the machine
     *
     * @return List with network interfaces
     * @throws java.net.SocketException
     */
    public static List<NetworkInterface> getNetworkInterfaces() throws SocketException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "getNetworkInterfaces");  //$NON-NLS-1$
		
		final List<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "getNetworkInterfaces", list);  //$NON-NLS-1$
		return list;
    }

    /**
     * Returns the content of an url
     *
     * @param url to read
     * @return byte array with the content
     * @throws IOException
     */
    public static byte[] readUrl(URL url) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "readUrl", url);  //$NON-NLS-1$

		final URLConnection connection = url.openConnection();
		final byte[] result = HelperIO.readStreamSecure(connection.getInputStream());
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "readUrl", result);  //$NON-NLS-1$
        return result;
     }

    /**
     * Returns the content of an url with HTTP authentication
     *
     * @param url to read
     * @param username for the HTTP authentication
     * @param password for the HTTP authentication
     * @return byte array with the content
     * @throws IOException
     */
    public static byte[] readUrl(URL url, String username, String password) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "readUrl", new Object[]{url, username, password});  //$NON-NLS-1$

		authenticate(username, password);
		final byte[] result = readUrl(url);
		
		Logger.getInstance().writeMethodExit(HelperNet.class, "readUrl", result);  //$NON-NLS-1$
        return result;
     }
    
//	public static String getMacAddress(NetworkInterface ni) throws SocketException { // Java 1.6 only
//		String result = "";
//
//		byte[] hardwareAddress = ni.getHardwareAddress();
//
//		if (hardwareAddress != null) {
//			for (int i = 0; i < hardwareAddress.length; i++) {
//				result += String.format((i == 0 ? "" : "-") + "%02X", hardwareAddress[i]);
//
//			}
//		}
//		return result;
//	}
    
    
    /*
     * Private methods
     */
    private static void authenticate(final String username, final String password) {
		Logger.getInstance().writeMethodEntry(HelperNet.class, "authenticate", new Object[]{username, password});  //$NON-NLS-1$

    	Authenticator.setDefault(new MyAuthenticator(username, password));

    	Logger.getInstance().writeMethodExit(HelperNet.class, "authenticate");  //$NON-NLS-1$
    }
    
    
    /*
     * Inner classes
     */
    private static class MyAuthenticator extends Authenticator {
        private String username, password;

        public MyAuthenticator(String username, String password) {
        	this.username = username;
        	this.password = password;
        }

    	@Override
		public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(username, password.toCharArray()));
        }
    }
}
