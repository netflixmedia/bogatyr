/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.orwell.bogatyr.helper.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ch.orwell.bogatyr.helper.exception.HelperException;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * Reads standard output and standard error of a process.
 * 
 * @author Stefan Laubenberger
 * @version 20080515
 */
public abstract class ControlProcess {
	/**
	 * Starts two threads which read standard output and standard error from a subprocess.
	 * <strong>Note:</strong> Standard output and standard error of the process returned by
	 * {@link Runtime#exec} must be read else the process might hang infinitly.
	 * <p>
	 * Usage example: <code><pre>
	final Process process = Runtime.getRuntime().exec("some command");
	ProcessUtil.readStandardOutputs(process, null, null);
	 * </pre></code>
	 *
	 * @param process
     * @param outStream If null, output is discarded.
	 * @param errStream If null, error is discarded.
	 */
	public static void readStandardOutput(final Process process, final OutputStream outStream, final OutputStream errStream) {
		new StreamReader(process.getErrorStream(), errStream);
		new StreamReader(process.getInputStream(), outStream);
	}

	
	/*
	 * Private methods
	 */
	protected static class StreamReader extends Thread {
		private final InputStream fSource;
		private final OutputStream fTarget;

		/**
		 * Start new thread which reads from an {@link InputStream} until end-of-file is reached.
		 * The read data is written to an {@link OutputStream}.
		 * 
		 * @param source may not be null
		 * @param target may be null
		 */
		StreamReader(final InputStream source, final OutputStream target) {
            super();
            fSource = source;
            fTarget = target;
			start();
		}

		@Override
		public void run() {
			try {
//				final byte[] buffer = new byte[1000];
                final byte[] buffer = new byte[1024];
                int len;
				while ((len = fSource.read(buffer)) != -1) {
					if (fTarget != null) {
                        fTarget.write(buffer, 0, len);
					}
				}
			} catch (IOException ex) {
				Logger.getInstance().writeException(this, "run", HelperException.EX_COMMUNICATION, ex); //$NON-NLS-1$
			}
		}
	}
}
