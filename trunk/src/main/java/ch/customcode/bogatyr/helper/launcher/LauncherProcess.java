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
package ch.customcode.bogatyr.helper.launcher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperArray;
import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This launcher creates a new process and reads standard output and standard error.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.2.0
 */
public abstract class LauncherProcess {
	private static final Logger log = LoggerFactory.getLogger(LauncherProcess.class);
	
	/**
	 * Creates a new process and reads the standard output and standard error.
	 *
     * @param outputStream for the standard output of the process
	 * @param errorStream for the standard error output of the process
	 * @param commands arguments to start the process
	 * @return created process
     * @throws IOException
     * @see OutputStream
     * @since 0.2.0
	 */
	public static Process createProcess(final String[] commands, final OutputStream outputStream, final OutputStream errorStream) throws IOException {
		log.debug(HelperLog.methodStart(commands, outputStream, errorStream));
		if (null == outputStream) {
			throw new RuntimeExceptionIsNull("outputStream"); //$NON-NLS-1$
		}
		if (null == errorStream) {
			throw new RuntimeExceptionIsNull("errorStream"); //$NON-NLS-1$
		}
		
		final Process result = createProcess(commands);

		readStandardOutput(result, outputStream, errorStream);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Creates a new process without reading the standard output and standard error ("fire and forget").
	 *
	 * @param commands arguments to start the process
	 * @return created process
     * @throws IOException
     * @since 0.2.0
	 */
	public static Process createProcess(final String... commands) throws IOException {
		log.debug(HelperLog.methodStart(commands));
		if (!HelperArray.isValid(commands)) {
			throw new RuntimeExceptionIsNullOrEmpty("commands"); //$NON-NLS-1$
		}
		
		final ProcessBuilder pb = new ProcessBuilder(commands);
//		return Runtime.getRuntime().exec(command);
		final Process result = pb.start();
		
		log.debug(HelperLog.methodExit(result));
		return result;
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
	 * @see Process
	 * @see OutputStream
	 * @since 0.2.0
	 */
	private static void readStandardOutput(final Process process, final OutputStream outputStream, final OutputStream errorStream) {
		log.trace(HelperLog.methodStart(process, outputStream, errorStream));
		
		new Thread(new StreamReader(process.getErrorStream(), errorStream)).start();
		new Thread(new StreamReader(process.getInputStream(), outputStream)).start();
		
		log.debug(HelperLog.methodExit());
	}

	
	/*
	 * Inner classes
	 */
	private static class StreamReader implements Runnable {
		private final InputStream is;
		private final OutputStream os;

		/**
		 * Start new thread which reads from an {@link InputStream} until end-of-file is reached.
		 * The read data is written to an {@link OutputStream}.
		 * 
		 * @param source may not be null
		 * @param target may be null
		 */
		StreamReader(final InputStream source, final OutputStream target) {
            super();
            is = source;
            os = target;
//			start();
		}

		@Override
		public void run() {
			try {
                final byte[] buffer = new byte[Constants.DEFAULT_FILE_BUFFER_SIZE];
                int offset;
				while (-1 != (offset = is.read(buffer))) {
					if (null != os) {
                        os.write(buffer, 0, offset);
					}
				}
			} catch (IOException ex) {
				throw new RuntimeException("Couldn't read the stream of the process: " + ex.getLocalizedMessage()); //$NON-NLS-1$
			}
		}
	}
}
