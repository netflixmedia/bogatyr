/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.encoder.EncoderBase64;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This is a helper class for network operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.5.0
 */
public abstract class HelperNet {
	private static final Logger log = LoggerFactory.getLogger(HelperNet.class);
	
	public static final String PROPERTY_HTTP_PROXY_HOST  = "http.proxyHost"; //$NON-NLS-1$
	public static final String PROPERTY_HTTP_PROXY_PORT  = "http.proxyPort"; //$NON-NLS-1$

	public static final String PROPERTY_HTTPS_PROXY_HOST = "https.proxyHost"; //$NON-NLS-1$
	public static final String PROPERTY_HTTPS_PROXY_PORT = "https.proxyPort"; //$NON-NLS-1$

	public static final String PROPERTY_FTP_PROXY_HOST   = "ftp.proxyHost"; //$NON-NLS-1$
	public static final String PROPERTY_FTP_PROXY_PORT   = "ftp.proxyPort"; //$NON-NLS-1$

	private static final int TIMEOUT = 2000;
	
	/**
     * Enable VM wide use of a proxy for HTTP.
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     * @since 0.5.0
     */
    public static void enableProxyHttp(final String host, final int port, final String username, final String password) { //$JUnit$
    	log.debug(HelperLog.methodStart(host, port, username, password));
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsNullOrEmpty("host"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(username)) {
			throw new RuntimeExceptionIsNullOrEmpty("username"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}
		
//    	System.setProperty(PROPERTY_HTTP_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_HTTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTP_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
        
        log.debug(HelperLog.methodExit());
    }
    
    /**
     * Disable VM wide use of a proxy for HTTP.
     *
     * @since 0.5.0
     */
    public static void disableProxyHttp() { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
//        System.clearProperty(PROPERTY_HTTP_USE_PROXY);
        System.clearProperty(PROPERTY_HTTP_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTP_PROXY_PORT);
        
        log.debug(HelperLog.methodExit());
    }
    
	/**
     * Enable VM wide use of a proxy for HTTPS.
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     * @since 0.5.0
     */
    public static void enableProxyHttps(final String host, final int port, final String username, final String password) { //$JUnit$
    	log.debug(HelperLog.methodStart(host, port, username, password));
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsNullOrEmpty("host"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(username)) {
			throw new RuntimeExceptionIsNullOrEmpty("username"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}
		
//		System.setProperty(PROPERTY_HTTPS_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_HTTPS_PROXY_HOST, host);
        System.setProperty(PROPERTY_HTTPS_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
        
        log.debug(HelperLog.methodExit());
    }
    
    /**
     * Disable VM wide use of a proxy for HTTPS.
     * 
     * @since 0.5.0
     */
    public static void disableProxyHttps() { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
//		System.clearProperty(PROPERTY_HTTPS_USE_PROXY);
        System.clearProperty(PROPERTY_HTTPS_PROXY_HOST);
        System.clearProperty(PROPERTY_HTTPS_PROXY_PORT);
        
        log.debug(HelperLog.methodExit());
    }
    
	/**
     * Enable VM wide use of a proxy for FTP.
     *
     * @param host of the proxy
     * @param port of the proxy
     * @param username for authentication
     * @param password for authentication
     * @since 0.5.0
     */
    public static void enableProxyFtp(final String host, final int port, final String username, final String password) { //$JUnit$
    	log.debug(HelperLog.methodStart(host, port, username, password));
    	
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsNullOrEmpty("host"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(username)) {
			throw new RuntimeExceptionIsNullOrEmpty("username"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}
		
//		System.setProperty(PROPERTY_FTP_USE_PROXY, "true"); //$NON-NLS-1$
        System.setProperty(PROPERTY_FTP_PROXY_HOST, host);
        System.setProperty(PROPERTY_FTP_PROXY_PORT, Integer.toString(port));

        authenticate(username, password);
        
        log.debug(HelperLog.methodExit());
    }
    
    /**
     * Disable VM wide use of a proxy for FTP.
     * 
     * @since 0.5.0
     */
    public static void disableProxyFtp() { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
//        System.clearProperty(PROPERTY_FTP_USE_PROXY);
        System.clearProperty(PROPERTY_FTP_PROXY_HOST);
        System.clearProperty(PROPERTY_FTP_PROXY_PORT);
        
        log.debug(HelperLog.methodExit());
    }

    /**
     * Tests if a host is pingable.
     *
     * @param host for the ping command
     * @return true/false
     * @throws IOException
     * @since 0.5.0
     */
    public static boolean isPingable(final String host) throws IOException { //$JUnit$
    	log.debug(HelperLog.methodStart(host));
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsNullOrEmpty("host"); //$NON-NLS-1$
		}
		
    	final InetAddress address = InetAddress.getByName(host);

        // Try to reach the specified address within the timeout period in ms.
        // If during this period the address cannot be
        // reached then the method returns false.
		final boolean result = address.isReachable(TIMEOUT);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

    /**
     * Returns the host name of an IP address.
     *
     * @param ip for host name
     * @return host name
     * @throws UnknownHostException
     * @since 0.5.0
     */
    public static String getHostname(final String ip) throws UnknownHostException { //$JUnit$
    	log.debug(HelperLog.methodStart(ip));
		if (!HelperString.isValid(ip)) {
			throw new RuntimeExceptionIsNullOrEmpty("ip"); //$NON-NLS-1$
		}
		
		final InetAddress address = InetAddress.getByName(ip);

		final String result = address.getHostName();
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

    /**
     * Returns the local host name of the current machine.
     *
     * @return host name of the current machine
     * @throws UnknownHostException
     * @since 0.5.0
     */
    public static String getLocalHostname() throws UnknownHostException { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
		final InetAddress address = InetAddress.getLocalHost();
		
		final String result = address.getHostName();
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

    /**
     * Returns the IP for an host name.
     *
     * @param host for IP
     * @return IP of the host name
     * @throws UnknownHostException
     * @since 0.5.0
     */
    public static String getIp(final String host) throws UnknownHostException { //$JUnit$
    	log.debug(HelperLog.methodStart(host));
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsNullOrEmpty("host"); //$NON-NLS-1$
		}
		
		final InetAddress address = InetAddress.getByName(host);
		
		final String result = address.getHostAddress();
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

    /**
     * Returns the local IP address of the current machine.
     *
     * @return IP of the current machine 
     * @throws UnknownHostException
     * @since 0.5.0
     */
    public static String getLocalIp() throws UnknownHostException { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
		final InetAddress address = InetAddress.getLocalHost();
		
		final String result = address.getHostAddress();
		
		log.debug(HelperLog.methodExit(result));
		return result;
   }

    /**
     * Returns all IP addresses of the current machine.
     *
     * @return {@link Collection} with IP addresses of the current machine
     * @throws UnknownHostException
     * @since 0.5.0
     */
    public static Collection<String> getLocalIps() throws UnknownHostException { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
    	final Collection<String> result = new ArrayList<String>();
        final String localHost = InetAddress.getLocalHost().getHostName();

        for (final InetAddress address : InetAddress.getAllByName(localHost)) {
            result.add(address.getHostAddress());
        }
        log.debug(HelperLog.methodExit(result));
        return result;
    }

    /**
     * Returns all network interfaces of the current machine.
     *
     * @return {@link List} with network interfaces of the current machine
     * @throws SocketException
     * @see NetworkInterface
     * @since 0.5.0
     */
    public static List<NetworkInterface> getNetworkInterfaces() throws SocketException { //$JUnit$
    	log.debug(HelperLog.methodStart());
    	
		final List<NetworkInterface> result = Collections.list(NetworkInterface.getNetworkInterfaces());
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
    
    /**
     * Returns the MAC address of a given network interface.
     *
     * @param ni {@link NetworkInterface} to determine the MAC address
     * @return MAC address of a given {@link NetworkInterface}
     * @throws SocketException
     * @see NetworkInterface
     * @since 0.7.0
     */
    public static String getMacAddress(final NetworkInterface ni) throws SocketException { //$JUnit$
    	log.debug(HelperLog.methodStart(ni));
		if (null == ni) {
			throw new RuntimeExceptionIsNull("ni"); //$NON-NLS-1$
		}
		
		final StringBuilder sb = new StringBuilder();

		final byte[] hardwareAddress = ni.getHardwareAddress();

		if (null != hardwareAddress) {
			for (int ii = 0; ii < hardwareAddress.length; ii++) {
				sb.append(String.format((0 == ii ? HelperString.EMPTY_STRING : '-') + "%02X", hardwareAddress[ii])); //$NON-NLS-1$
			}
		}
		
		final String result = sb.toString();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

    /**
     * Returns an {@link InputStream} linked to an {@link URL}.
     *
     * @param url to read
     * @return {@link InputStream} for the content
     * @throws IOException
     * @see URL
     * @see InputStream
     * @since 0.5.0
     */
    public static InputStream readUrl(final URL url) throws IOException { //$JUnit$
    	log.debug(HelperLog.methodStart(url));
		if (null == url) {
			throw new RuntimeExceptionIsNull("url"); //$NON-NLS-1$
		}
		
		final URLConnection con = url.openConnection();
		con.setConnectTimeout(TIMEOUT);
		con.connect();
	
		final InputStream result = con.getInputStream();
		
		log.debug(HelperLog.methodExit(result));
		return result;
     }

    /**
     * Returns an {@link InputStream} linked to an {@link URL} with HTTP basic authentication.
     *
     * @param url to read
     * @param username for the HTTP authentication
     * @param password for the HTTP authentication
     * @return {@link InputStream} for the content
     * @throws IOException
     * @see URL
     * @see InputStream
     * @since 0.5.0
     */
    public static InputStream readUrl(final URL url, final String username, final String password) throws IOException {
    	log.debug(HelperLog.methodStart(url, username, password));
    	if (null == url) {
			throw new RuntimeExceptionIsNull("url"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(username)) {
			throw new RuntimeExceptionIsNullOrEmpty("username"); //$NON-NLS-1$
		}
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}
		
    	final URLConnection con = url.openConnection();
		con.setRequestProperty("Authorization", "Basic " + EncoderBase64.encode(username + ':' + password)); //$NON-NLS-1$ //$NON-NLS-2$
		con.setConnectTimeout(TIMEOUT);
		con.connect();
		
		final InputStream result = con.getInputStream();
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
    	
    
    /*
     * Private methods
     */
    private static void authenticate(final String username, final String password) {
    	log.trace(HelperLog.methodStart(username, password));
    	
    	Authenticator.setDefault(new WebAuthenticator(username, password));
    	
    	log.trace(HelperLog.methodExit());
    }
    
    
    /*
     * Inner classes
     */
    private static class WebAuthenticator extends Authenticator {
        private final String username;
        private final String password;

        WebAuthenticator(final String username, final String password) {
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
