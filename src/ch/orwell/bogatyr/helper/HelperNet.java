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
package ch.orwell.bogatyr.helper;

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

/**
 * This is a helper class for network operations
 *
 * @author Stefan Laubenberger
 * @version 20080729
 */
public abstract class HelperNet {
	private static final String PROPERTY_HTTP_USE_PROXY   = "http.useProxy";
//	private static final String PROPERTY_HTTP_PROXY_SET   = "http.proxySet";
	private static final String PROPERTY_HTTP_PROXY_HOST  = "http.proxyHost";
	private static final String PROPERTY_HTTP_PROXY_PORT  = "http.proxyPort";

	private static final String PROPERTY_HTTPS_USE_PROXY  = "https.useProxy";
//	private static final String PROPERTY_HTTPS_PROXY_SET  = "https.proxySet";
	private static final String PROPERTY_HTTPS_PROXY_HOST = "https.proxyHost";
	private static final String PROPERTY_HTTPS_PROXY_PORT = "https.proxyPort";

	private static final String PROPERTY_FTP_USE_PROXY    = "ftp.useProxy";
//	private static final String PROPERTY_FTP_PROXY_SET    = "ftp.proxySet";
	private static final String PROPERTY_FTP_PROXY_HOST   = "ftp.proxyHost";
	private static final String PROPERTY_FTP_PROXY_PORT   = "ftp.proxyPort";

	
	/**
     * Enable VM wide use of a proxy for HTTP
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     */
    public static void enableProxyHttp(final String host, final int port, final String username, final String password) {
        System.setProperty(PROPERTY_HTTP_USE_PROXY, "true");
//        System.setProperty(PROPERTY_HTTP_PROXY_SET, "true");
        System.setProperty(PROPERTY_HTTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTP_PROXY_PORT, Integer.toString(port));

        Authenticator.setDefault(new Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password.toCharArray());
          }});
    }
    
    /**
     * Disable VM wide use of a proxy for HTTP
     *
     */
    public static void disableProxyHttp() {
        System.clearProperty(PROPERTY_HTTP_USE_PROXY);
//        System.clearProperty(PROPERTY_HTTP_PROXY_SET);
        System.clearProperty(PROPERTY_HTTP_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTP_PROXY_PORT);
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
        System.setProperty(PROPERTY_HTTPS_USE_PROXY, "true");
//        System.setProperty(PROPERTY_HTTPS_PROXY_SET, "true");
        System.setProperty(PROPERTY_HTTPS_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTPS_PROXY_PORT, Integer.toString(port));

        Authenticator.setDefault(new Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password.toCharArray());
          }});
    }
    
    /**
     * Disable VM wide use of a proxy for HTTPS
     *
     */
    public static void disableProxyHttps() {
        System.clearProperty(PROPERTY_HTTPS_USE_PROXY);
//        System.clearProperty(PROPERTY_HTTPS_PROXY_SET);
        System.clearProperty(PROPERTY_HTTPS_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTPS_PROXY_PORT);
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
        System.setProperty(PROPERTY_FTP_USE_PROXY, "true");
//        System.setProperty(PROPERTY_FTP_PROXY_SET, "true");
        System.setProperty(PROPERTY_FTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_FTP_PROXY_PORT, Integer.toString(port));

        Authenticator.setDefault(new Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password.toCharArray());
          }});
    }
    
    /**
     * Disable VM wide use of a proxy for FTP
     *
     */
    public static void disableProxyFtp() {
        System.clearProperty(PROPERTY_FTP_USE_PROXY);
//        System.clearProperty(PROPERTY_FTP_PROXY_SET);
        System.clearProperty(PROPERTY_FTP_PROXY_HOST);
        System.clearProperty(PROPERTY_FTP_PROXY_PORT);
    }

    /**
     * Tests if a host is pingable
     *
     * @param host for ping
     * @return true/false
     * @throws java.io.IOException
     */
    public static boolean isPingable(String host) throws IOException {
        InetAddress address = InetAddress.getByName(host);

        // Try to reach the specified address within the timeout period.
        // If during this period the address cannot be
        // reached then the method returns false.
        return address.isReachable(3000);
    }

    /**
     * Returns the host name of an IP address
     *
     * @param ip for host name
     * @return host name
     * @throws java.net.UnknownHostException
     */
    public static String getHostname(String ip) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(ip);

        return address.getHostName();
    }

    /**
     * Returns the local host name of the machine
     *
     * @return host name
     * @throws java.net.UnknownHostException
     */
    public static String getLocalHostname() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        return address.getHostName();
    }

    /**
     * Returns the ip for an host name
     *
     * @param hostname for IP
     * @return IP
     * @throws java.net.UnknownHostException
     */
    public static String getIp(String hostname) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(hostname);

        return address.getHostAddress();
    }

    /**
     * Returns the local IP address
     *
     * @return IP
 * @throws java.net.UnknownHostException
     */
    public static String getLocalIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        return address.getHostAddress();
    }

    /**
     * Returns the all IP addresses of the machine
     *
     * @return List with IP addresses
     * @throws java.net.UnknownHostException
     */
    public static List<String> getLocalIps() throws UnknownHostException {
        List<String> list = new ArrayList<String>();

        String localHost = InetAddress.getLocalHost().getHostName();

        for (InetAddress address : InetAddress.getAllByName(localHost)) {
            list.add(address.getHostAddress());
        }

        return list;
    }

    /**
     * Returns the all network interfaces of the machine
     *
     * @return List with network interfaces
     * @throws java.net.SocketException
     */
    public static List<NetworkInterface> getNetworkInterfaces() throws SocketException {
        return Collections.list(NetworkInterface.getNetworkInterfaces());
    }

    /**
     * Returns the content of an url
     *
     * @return byte array with the content
     * @throws IOException
     */
    public static byte[] readUrl(URL url) throws IOException {
        URLConnection connection = url.openConnection();
 
        return HelperFile.readStreamSecure(connection.getInputStream());
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
}
