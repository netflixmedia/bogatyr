/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;


/**
 * A tool to display an URL in a browser.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070708
 */
public class BrowserControl {
	// The default system browser under windows.
	private static final String WINDOWS_PATH = "rundll32"; //$NON-NLS-1$
	private static final String WINDOWS_FLAG = "url.dll,FileProtocolHandler"; //$NON-NLS-1$

	// The default browser under unix.
	private static final String UNIX_PATH = "netscape"; //$NON-NLS-1$
	private static final String UNIX_FLAG = "-remote openURL"; //$NON-NLS-1$

	private static OutputStream outputStream = null;
	private static OutputStream errorStream = null;

	
	/**
	 * Display a file in the browser. If you want to display a
	 * file, you must include the absolute path name.
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
	 */
	public static void displayURL(String url) throws Exception {
		String cmd;
		
		if (EnvironmentInfo.isWindowsPlatform()) {
			cmd = WINDOWS_PATH + " " + WINDOWS_FLAG + " " + url;  //$NON-NLS-1$//$NON-NLS-2$

			// If the URL starts with "http://" or "https://" and
			// doesn't contain a "?" or "#" we have problems
			// opening the browser under certain circumstances:
			//  - under Windows XP or 2000 with Internet Explorer
			//  - with URL's ending with  .html
			//  - with long URL's containing /../
			//  - maybe more
			//
			// Fixed by adding a "#" at the end of the URL.
			// Why? We don't know... :-)
			//
			// Test for http or https protocol is necessary else file-urls
			// will not work.
			if ((url.startsWith("http://") || url.startsWith("https://")) && (url.indexOf("?") == -1) && (url.indexOf("#") == -1)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				cmd = cmd + "#"; //$NON-NLS-1$
			}
			createSubprocess(cmd);
		} else if (EnvironmentInfo.isMacPlatform()) {
			openUrlOnMacPlatform(url);
		} else {
			// Under Unix, Netscape has to be running for the "-remote"
			// command to work. So, we try sending the command and
			// check for an exit value. If the exit command is 0,
			// it worked, otherwise we need to start the browser.

			// cmd = 'netscape -remote openURL(http://www.javaworld.com)'
			cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Process p = createSubprocess(cmd);

			// wait for exit code -- if it's 0, command worked,
			// otherwise we need to start the browser up.
			int exitCode = p.waitFor();

			if (exitCode != 0) {
				// Command failed, start up the browser

				// cmd = 'netscape http://www.javaworld.com'
				cmd = UNIX_PATH + " " + url; //$NON-NLS-1$
				createSubprocess(cmd);
			}
		}
	}

	public static void openUrlOnMacPlatform(final String url) {
		Runnable runnable = new Runnable() {
			
			public void run() {
				try {
					// Call the mac specific method com.apple.eio.FileManager.openURL(url)
					// dynamically because it should be compilable under windows
					final Class fileManager = Class.forName("com.apple.eio.FileManager"); //$NON-NLS-1$
					final Method method = fileManager.getDeclaredMethod("openURL", new Class[]{String.class}); //$NON-NLS-1$
					method.invoke(null, new Object[]{url});
				} catch (Exception ex) {
					ex.printStackTrace(); //TODO replace by logger?
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);

		// must be called in separat thread because the call to openURL sometimes hangs (probably a bug)
		thread.start();
	}

	
	/*
	 * Private methods
	 */
	private static Process createSubprocess(String command) throws IOException {
		final Process process = Runtime.getRuntime().exec(command);
		ProcessUtility.readStandardOutputs(process, outputStream, errorStream);
		return process;
	}
}

