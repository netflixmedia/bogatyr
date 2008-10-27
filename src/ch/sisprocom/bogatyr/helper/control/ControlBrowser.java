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

import org.apache.log4j.Logger;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;


/**
 * This controls displays an URL in a browser.
 *
 * @author Stefan Laubenberger
 * @version 20081027
 */
public abstract class ControlBrowser {
	protected static final Logger log = Logger.getLogger(ControlBrowser.class);
	
	// The default system browser under windows.
	private static final String WINDOWS_PATH = "rundll32 url.dll,FileProtocolHandler"; //$NON-NLS-1$

	// The default browser under unix.
	private static final String UNIX_PATH = "netscape"; //$NON-NLS-1$
	private static final String UNIX_FLAG = "-remote openURL"; //$NON-NLS-1$

	
	/**
	 * Display a file in the browser. If you want to display a file, you must include the absolute path name.
	 * <p>
	 * <em>Possible security issue:</em> if the url is received from a third party company such as Paynet, the url
	 * must be verified by the application before calling <code>displayURL</code>. Else it is
	 * possible for the third party company to send an url like "notepad.exe" which directly starts
	 * the exe without further asking the user of the application. Another attack could be the direct execution
	 * of javascript with a javascript url such as <code>javascript:alert("demo")</code>.
	 * The verification could just check if the url starts with "https://" (or "http://" if that is allowed).
	 *
	 * @param url the file's url. The url should start with either "http://", "https://" or
	 * "file://" but this is not enforced by Bogatyr. On windows the length of the url is limited to about 260 characters.
     * @throws Exception
	 */
	public static void displayURL(final String url) throws Exception {
		String cmd;
				
		if (HelperEnvInfo.isWindowsPlatform()) {
			cmd = WINDOWS_PATH + ' ' + url;

			// If the URL starts with "http://" or "https://" and doesn't contain a "?" or "#" we have problems opening the browser under certain circumstances:
			//  - under Windows XP or 2000 with Internet Explorer
			//  - with URL's ending with .html
			//  - with long URL's containing /../
			//  - maybe more
			//
			// Fixed by adding a "#" at the end of the URL.
			//
			// Test for http or https protocol is necessary else file-urls will not work.
			if ((url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) && !url.contains("?") && !url.contains("#")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                cmd += "#"; //$NON-NLS-1$
			}
			ControlProcess.createProcess(cmd);
		} else if (HelperEnvInfo.isMacPlatform()) {
			displayURLonMac(url);
		} else {
			// Under Unix, Netscape has to be running for the "-remote"
			// command to work. So, we try sending the command and
			// check for an exit value. If the exit command is 0,
			// it worked, otherwise we need to start the browser.

			// cmd = 'netscape -remote openURL(http://code.google.com/p/bogatyr)'
			cmd = UNIX_PATH + ' ' + UNIX_FLAG + '(' + url + ')';
			final Process process = ControlProcess.createProcess(cmd);

			// wait for exit code -- if it's 0, command worked,
			// otherwise we need to start the browser up.
			final int exitCode = process.waitFor();

			if (exitCode != 0) {
				// Command failed, start up the browser

				// cmd = 'netscape http://code.google.com/p/bogatyr'
				cmd = UNIX_PATH + ' ' + url;
				ControlProcess.createProcess(cmd);
			}
		}
	}

	
	/*
	 * Private methods
	 */
	private static void displayURLonMac(final String url) {
		final Runnable runnable = new Runnable() {

			public void run() {
				try {
					// Call the mac specific method com.apple.eio.FileManager.openURL(url)
					// dynamically because it should be compilable under windows
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
		
		// must be called in separate thread because the call to openURL sometimes hangs (probably a bug)
		thread.start();
	}
}
