/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Creates a new process and reads standard output and standard error.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public abstract class ControlProcess {
	private static final int BUFFER = 1024;
	
	/**
	 * Creates a new process and reads the standard output and standard error.
	 *
	 * @param command to start the process
     * @param outputStream If null, output is discarded
	 * @param errorStream If null, error is discarded
	 * @return created process
     * @throws IOException
	 */
	public static Process createProcess(final String command, final OutputStream outputStream, final OutputStream errorStream) throws IOException {
		final Process process = createProcess(command);

		readStandardOutput(process, outputStream, errorStream);
		
		return process;
	}

	/**
	 * Creates a new process without reading the standard output and standard error ("fire and forget").
	 *
	 * @param command to start the process
	 * @return created process
     * @throws IOException
	 */
	public static Process createProcess(final String command) throws IOException {

//		ProcessBuilder pb = new ProcessBuilder(command);
//		final Process process = pb.start();
		return Runtime.getRuntime().exec(command);
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Starts two threads which read standard output and standard error from a process.
	 * <strong>Note:</strong> Standard output and standard error of the process returned by {@link Runtime#exec} must be read else the process might hang infinitly.
	 *
	 * @param process to observe
     * @param outputStream If null, output is discarded
	 * @param errorStream If null, error is discarded
	 */
	private static void readStandardOutput(final Process process, final OutputStream outputStream, final OutputStream errorStream) {
		new StreamReader(process.getErrorStream(), errorStream);
		new StreamReader(process.getInputStream(), outputStream);
	}

	
	/*
	 * Inner classes
	 */
	private static class StreamReader extends Thread {
		private final InputStream is;
		private final OutputStream os;

		/**
		 * Start new thread which reads from an {@link InputStream} until end-of-file is reached.
		 * The read data is written to an {@link OutputStream}.
		 * 
		 * @param source may not be null
		 * @param target may be null
		 */
		private StreamReader(final InputStream source, final OutputStream target) {
            super();
            is = source;
            os = target;
			start();
		}

		@Override
		public void run() {
			try {
                final byte[] buffer = new byte[BUFFER];
                int len;
				while (-1 != (len = is.read(buffer))) {
					if (os != null) {
                        os.write(buffer, 0, len);
					}
				}
			} catch (IOException ex) {
				throw new RuntimeException("Couldn't read the stream of the process: " + ex.getLocalizedMessage());
			}
		}
	}
}
