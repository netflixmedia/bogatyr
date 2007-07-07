/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.io.FileManager;

/**
 * This is a tool to open PDF data with an external program such as Acrobat Reader.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public class PDFControl {
	private static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$

	private static final String WIN_PDF_VIEWER_PATH  = "rundll32 url.dll,FileProtocolHandler {0}"; //$NON-NLS-1$
	private static final String UNIX_PDF_VIEWER_PATH = "acroread {0}"; //$NON-NLS-1$

	private static final String fileNamePrefix = "temp"; //$NON-NLS-1$
	private static final String directoryName = "pdffiles"; //$NON-NLS-1$

	private static OutputStream outputStream = null;
	private static OutputStream errorStream = null;


	/**
	 * Show a PDF (provided as a byte[]) with PDF viewer. A temporary file is first created (see
	 * {@link #createFile} and {@link PDFControl class description}).
	 */
	public static void open(byte[] pdfFileContents)	throws Exception {
		try {
			// first store the pdfFileContents to a temporary file
			File temporaryFile = createFile();
			FileManager.writeBinaryFile(temporaryFile.getAbsolutePath(), pdfFileContents);
			open(temporaryFile);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Show a PDF (provided as {@link File}) with PDF viewer.
	 */
	public static void open(File pdfFile) throws Exception {
		try {
			PDFControl.exec(pdfFile.getCanonicalPath());
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Deletes all PDF files in the PDF Directory. Calls {@link ExceptionHandler#handleCleanupError} if
	 * a file could not be deleted, for example because the PDF viewer is still open and showing one of the
	 * files (see {@link #setExceptionHandler}).
	 * <p>
	 * Intended to be called before the application is closed for security reasons (Else the information within
	 * temporary PDF files could be read by anyone with access to the files).
	 * <p>
	 * This will also delete the directory itself if it contains PDF files only. This is especially important on
	 * Unix systems where the directory could not be written to by another user of the application because of
	 * file permissions.
	 */
	public static void cleanup() {
		File directory = createDirectory();
//		File[] files = directory.listFiles();
//		if (files != null) {
//			for (int i = 0; i < files.length; i++) {
//				if (files[i].getName().endsWith(PDF_EXTENSION)) {
//					while (!files[i].delete()) {
//						//...
//					}
//				}
//			}
//		}

		// cleanup the directory
		directory.delete();
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Return next temporary non existing file according to the rules of this class (see {@link PDFControl above}).
	 * Is not synchronized.
	 * @return File object. File is not physically created.
	 */
	private static File createFile() {
		final File directory = createDirectory();
		int count = 0;
		File file = null;

		do {
			file = new File(directory, fileNamePrefix + count++ + PDF_EXTENSION);
		} while (file.exists());
		return file;
	}

	private static File createDirectory() {
		// this is the root for Pdfs
		final File directoryPath = new File(Context.getInstance().getWorkDirectory());

		// get a subdirectory for the Pdf files
		final File directory = new File(directoryPath, directoryName);

		if (!directory.exists()) {
			directory.mkdir();
		}
		return directory;
	}

	private static void exec(String path) throws IOException {
		String viewerPath;
		if (EnvironmentInfo.isWindowsPlatform()) {
			viewerPath = WIN_PDF_VIEWER_PATH;
		} else if (EnvironmentInfo.isMacPlatform()) {
			BrowserControl.openUrlOnMacPlatform("file://" + path); //$NON-NLS-1$
			return;
		} else {
			viewerPath = UNIX_PDF_VIEWER_PATH;
		}
		String cmd = MessageFormat.format(viewerPath, new Object[]{path});
		createSubprocess(cmd);
	}

	private static Process createSubprocess(String command) throws IOException {
		final Process process = Runtime.getRuntime().exec(command);
		ProcessUtility.readStandardOutputs(process, outputStream, errorStream);
		return process;
	}
}
