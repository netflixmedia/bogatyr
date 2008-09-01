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
package ch.sisprocom.bogatyr.helper.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ch.sisprocom.bogatyr.helper.exception.HelperException;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * Reads standard output and standard error of a process.
 * 
 * @author Stefan Laubenberger
 * @version 20080826
 */
public abstract class ControlProcess {
	private static final int BUFFER = 1024;
	
	/**
	 * Creates a new process and reads the standard output and standard error.
	 *
	 * @param command to start the process
     * @param outputStream If null, output is discarded.
	 * @param errorStream If null, error is discarded.
	 * @return created process
     * @throws java.io.IOException
	 */
	public static Process createProcess(final String command, final OutputStream outputStream, final OutputStream errorStream) throws IOException {
		Logger.getInstance().writeMethodEntry(ControlProcess.class, "createSubprocess", command);  //$NON-NLS-1$

		final Process process = new ProcessBuilder(command).start();
//		final Process process = Runtime.getRuntime().exec(command);
		ControlProcess.readStandardOutput(process, outputStream, errorStream);
		
		Logger.getInstance().writeMethodExit(ControlProcess.class, "createSubprocess", process);  //$NON-NLS-1$
		return process;
	}

	/**
	 * Creates a new process without reading the standard output and standard error ("fire and forget").
	 *
	 * @param command to start the process
	 * @return created process
     * @throws java.io.IOException
	 */
	public static Process createProcess(final String command) throws IOException {
		Logger.getInstance().writeMethodEntry(ControlProcess.class, "createSubprocess", command);  //$NON-NLS-1$

		final Process process = new ProcessBuilder(command).start();
//		final Process process = Runtime.getRuntime().exec(command);
		
		Logger.getInstance().writeMethodExit(ControlProcess.class, "createSubprocess", process);  //$NON-NLS-1$
		return process;
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Starts two threads which read standard output and standard error from a process.
	 * <strong>Note:</strong> Standard output and standard error of the process returned by
	 * {@link Runtime#exec} must be read else the process might hang infinitly.
	 *
	 * @param process to observe
     * @param outputStream If null, output is discarded.
	 * @param errorStream If null, error is discarded.
	 */
	private static void readStandardOutput(final Process process, final OutputStream outputStream, final OutputStream errorStream) {
		Logger.getInstance().writeMethodEntry(ControlProcess.class, "readStandardOutput", new Object[]{process, outputStream, errorStream});  //$NON-NLS-1$

		new StreamReader(process.getErrorStream(), errorStream);
		new StreamReader(process.getInputStream(), outputStream);

		Logger.getInstance().writeMethodExit(ControlProcess.class, "readStandardOutput");  //$NON-NLS-1$
	}

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
                final byte[] buffer = new byte[BUFFER];
                int len;
				while ((len = fSource.read(buffer)) != -1) {
					if (fTarget != null) {
                        fTarget.write(buffer, 0, len);
					}
				}
			} catch (IOException ex) {
				Logger.getInstance().writeException(this.getClass(), "run", HelperException.EX_COMMUNICATION, ex); //$NON-NLS-1$
			}
		}
	}
}
