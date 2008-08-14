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

import java.io.File;
import java.text.MessageFormat;

import ch.orwell.bogatyr.helper.HelperEnvInfo;
import ch.orwell.bogatyr.helper.HelperIO;
import ch.orwell.bogatyr.helper.context.Context;
import ch.orwell.bogatyr.helper.logger.Logger;

/**
 * This control opens PDF data with an external program such as Acrobat Reader.
 *
 * @author Stefan Laubenberger
 * @version 20080809
 */
public abstract class ControlPdf {
	private static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$

	private static final String WINDOWS_PDF_VIEWER_PATH  = "rundll32 url.dll,FileProtocolHandler {0}"; //$NON-NLS-1$
	private static final String UNIX_PDF_VIEWER_PATH     = "acroread {0}"; //$NON-NLS-1$

	private static final String FILE_NAME_PREFIX = "temp"; //$NON-NLS-1$
	private static final String DIRECTORY_NAME   = "pdffiles"; //$NON-NLS-1$


	/**
	 * Show a PDF (provided as a byte[]) with PDF viewer. A temporary file is first created (see
	 * {@link #createFile} and {@link ControlPdf class description}).
	 * @param pdfFileContents PDF content as byte array
     * @throws Exception
	 */
	public static void open(final byte[] pdfFileContents)	throws Exception {
		Logger.getInstance().writeMethodEntry(ControlPdf.class, "open", pdfFileContents);  //$NON-NLS-1$

		// first store the pdfFileContents to a temporary file
		final File temporaryFile = createFile();
		HelperIO.writeFileFromBinary(temporaryFile, pdfFileContents, false);
		open(temporaryFile);
		
		Logger.getInstance().writeMethodExit(ControlPdf.class, "open");  //$NON-NLS-1$
	}

	/**
	 * Show a PDF (provided as {@link File}) with PDF viewer.
	 * @param pdfFile PDF content as file
     * @throws Exception
	 */
	public static void open(final File pdfFile) throws Exception {
		Logger.getInstance().writeMethodEntry(ControlPdf.class, "open", pdfFile);  //$NON-NLS-1$

		execute(pdfFile.getCanonicalPath());
		
		Logger.getInstance().writeMethodExit(ControlPdf.class, "open");  //$NON-NLS-1$
	}

	/**
	 * Deletes all PDF files in the PDF Directory.
	 * Intended to be called before the application is closed for security reasons (else the information within
	 * temporary PDF files could be read by anyone with access to the files).
	 * This will also delete the directory itself if it contains PDF files only. This is especially important on
	 * Unix systems where the directory could not be written to by another user of the application because of
	 * file permissions.
	 */
	public static void cleanup() {
		Logger.getInstance().writeMethodEntry(ControlPdf.class, "cleanup");  //$NON-NLS-1$

		final File directory = createDirectory();
		final File[] files = directory.listFiles();
		if (files != null) {
			for (final File file : files) {
				if (file.getName().endsWith(PDF_EXTENSION)) {
					file.delete();
//					int ii = 0; // Prevent hang up
//					while (!file.delete() && ii < 5) {
//						ii++;
//					}
				}
			}
		}

		// cleanup the directory
		directory.delete();
	
		Logger.getInstance().writeMethodExit(ControlPdf.class, "cleanup");  //$NON-NLS-1$
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Return next temporary non existing file according to the rules of this class (see {@link ControlPdf above}).
	 * 
	 * @return File object. File is not physically created.
	 */
	private static File createFile() {
		Logger.getInstance().writeMethodEntry(ControlPdf.class, "createFile");  //$NON-NLS-1$

		final File directory = createDirectory();
		int count = 0;
		File file;

		do {
			file = new File(directory, FILE_NAME_PREFIX + count++ + PDF_EXTENSION);
		} while (file.exists());
		
		Logger.getInstance().writeMethodExit(ControlPdf.class, "createFile", file);  //$NON-NLS-1$
		return file;
	}

	private static File createDirectory() {
		Logger.getInstance().writeMethodEntry(ControlPdf.class, "createDirectory");  //$NON-NLS-1$

		// this is the root for Pdfs
		final File directoryPath = Context.getInstance().getApplicationWorkDirectory();

		// get a subdirectory for the Pdf files
		final File directory = new File(directoryPath, DIRECTORY_NAME);

		if (!directory.exists()) {
			directory.mkdir();
		}
		
		Logger.getInstance().writeMethodExit(ControlPdf.class, "createDirectory", directory);  //$NON-NLS-1$
		return directory;
	}

	private static void execute(final String path) throws Exception {
		Logger.getInstance().writeMethodEntry(ControlPdf.class, "execute");  //$NON-NLS-1$

		final String viewerPath;
		if (HelperEnvInfo.isWindowsPlatform()) {
			viewerPath = WINDOWS_PDF_VIEWER_PATH;
		} else if (HelperEnvInfo.isMacPlatform()) {
			ControlBrowser.displayURL("file://" + path); //$NON-NLS-1$
			return;
		} else {
			viewerPath = UNIX_PDF_VIEWER_PATH;
		}
		final String cmd = MessageFormat.format(viewerPath, path);
		ControlProcess.createProcess(cmd);
		
		Logger.getInstance().writeMethodExit(ControlPdf.class, "execute");  //$NON-NLS-1$
	}
}
