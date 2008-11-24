/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.control;

import java.lang.reflect.Method;
import java.net.URL;

import org.apache.log4j.Logger;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;


/**
 * This controls displays an URL in the system browser.
 *
 * @author Stefan Laubenberger
 * @version 20081124
 */
public abstract class ControlBrowser {
	protected static final Logger log = Logger.getLogger(ControlBrowser.class);
	
	// Default browser under windows
	private static final String WINDOWS_PATH = "rundll32 url.dll,FileProtocolHandler"; //$NON-NLS-1$

	// Default browser under unix
	private static final String UNIX_PATH = "netscape"; //$NON-NLS-1$
	private static final String UNIX_FLAG = "-remote openURL"; //$NON-NLS-1$

	
	/**
	 * Display an url in the browser.
	 *
	 * @param url for the browser
     * @throws Exception
	 */
	public static void display(final URL url) throws Exception {
		final String urlString = url.toExternalForm();
		String cmd;
				
		if (HelperEnvInfo.isWindowsPlatform()) {
			cmd = WINDOWS_PATH + ' ' + url.toExternalForm();

			// Test for http or https protocol is necessary or file-urls will not work
			if ((urlString.toLowerCase().startsWith("http://") || urlString.toLowerCase().startsWith("https://")) && !urlString.contains("?") && !urlString.contains("#")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                cmd += "#"; //$NON-NLS-1$
			}
			ControlProcess.createProcess(cmd);
		} else if (HelperEnvInfo.isMacPlatform()) {
			displayOnMac(urlString);
		} else {
			// Under Unix, Netscape has to be running for the "-remote" command to work. So, we try sending the command and check for an exit value. If the exit command is 0, it worked, otherwise we need to start the browser.
			// cmd = 'netscape -remote openURL(http://code.google.com/p/bogatyr)'
			cmd = UNIX_PATH + ' ' + UNIX_FLAG + '(' + url + ')';
			final Process process = ControlProcess.createProcess(cmd);

			// wait for exit code -- if it's 0, command worked, otherwise we need to start the browser
			final int exitCode = process.waitFor();

			if (exitCode != 0) {
				// Command failed, start the browser
				// cmd = 'netscape http://code.google.com/p/bogatyr'
				cmd = UNIX_PATH + ' ' + url;
				ControlProcess.createProcess(cmd);
			}
		}
	}

	
	/*
	 * Private methods
	 */
	private static void displayOnMac(final String url) {
		final Runnable runnable = new Runnable() {

			public void run() {
				try {
					// Call the mac specific method com.apple.eio.FileManager.openURL(url) dynamically
					final Class<?> fileManager = Class.forName("com.apple.eio.FileManager"); //$NON-NLS-1$
					final Method method = fileManager.getDeclaredMethod("openURL", String.class); //$NON-NLS-1$
					method.invoke(null, url);
				} catch (Exception ex) {
					log.error("Couldn't start the web browser", ex);
				}
			}
		};
		final Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		
		// must be called in separate thread because the call of openURL sometimes hangs
		thread.start();
	}
}
