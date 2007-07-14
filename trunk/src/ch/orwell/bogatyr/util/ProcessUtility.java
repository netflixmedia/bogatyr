/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import ch.orwell.bogatyr.exception.ExceptionHelper;


/**
 * Reads standard output and standard error of a process.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class ProcessUtility {
	/**
	 * Starts two threads which read standard output and standard error from a subprocess.
	 * <strong>Note:</strong> Standard output and standard error of the process returned by
	 * {@link Runtime#exec} must be read else the process might hang infinitly.
	 * <p>
	 * See this <a href = http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps_p.html>javaworld article</a>.
	 * <p>
	 * Usage example: <code><pre>
	final Process process = Runtime.getRuntime().exec("some command");
	ProcessUtil.readStandardOutputs(process, null, null);
	 * </pre></code>
	 *
	 * @param outStream If null, output is discarded.
	 * @param errStream If null, error is discarded.
	 */
	public static void readStandardOutputs(Process process, OutputStream outStream, OutputStream errStream)	{
		new StreamReader(process.getErrorStream(), errStream);
		new StreamReader(process.getInputStream(), outStream);
	}

	
	/*
	 * Private methods
	 */
	private static class StreamReader extends Thread {
		final private InputStream fSource;
		final private OutputStream fTarget;

		/**
		 * Start new thread which reads from an {@link InputStream} until end-of-file is reached.
		 * The read data is written to an {@link OutputStream}.
		 * 
		 * @param source may not be null
		 * @param target may be null
		 */
		public StreamReader(InputStream source, OutputStream target) {
			this.fSource = source;
			this.fTarget = target;
			start();
		}

		@Override
		public void run() {
			try {
				final byte[] buffer = new byte[1000];
				int len;
				while ((len = this.fSource.read(buffer)) != -1) {
					if (this.fTarget != null) {
						this.fTarget.write(buffer, 0, len);
					}
				}
			}
			catch (IOException ex) {
				Logger.getInstance().writeException("ProcessUtil::stop", ExceptionHelper.EX_COMMUNICATION, ex); //$NON-NLS-1$
			}
		}
	}
}
