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

import java.io.DataInputStream;
import java.io.File;
import java.net.URL;
import java.text.MessageFormat;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.context.Context;

/**
 * This control opens PDF data with an external program such as Acrobat Reader.
 *
 * @author Stefan Laubenberger
 * @version 20081124
 */
public abstract class ControlPdf { //TODO document in Wiki!
	private static final String PDF_EXTENSION = ".pdf"; //$NON-NLS-1$

	private static final String WINDOWS_PDF_VIEWER_PATH  = "rundll32 url.dll,FileProtocolHandler {0}"; //$NON-NLS-1$
	private static final String UNIX_PDF_VIEWER_PATH     = "acroread {0}"; //$NON-NLS-1$


	/**
	 * Show a PDF (provided as a byte[]) with PDF viewer. A temporary file is first created.
	 * 
	 * @param pdfContent PDF content as byte array
     * @throws Exception
	 */
	public static void open(final byte[] pdfContent)	throws Exception {
		// first store the pdfFileContents to a temporary file
		final File temporaryFile = HelperIO.getTemporaryFile(Context.getInstance().getApplicationName(), PDF_EXTENSION);
		HelperIO.writeFileFromBinary(temporaryFile, pdfContent, false);
		open(temporaryFile);
	}

	/**
	 * Show a PDF (provided as {@link File}) with PDF viewer.
	 * 
	 * @param pdfFile PDF content as file
     * @throws Exception
	 */
	public static void open(final File pdfFile) throws Exception {
		execute(pdfFile.getCanonicalPath());
	}
	
	/**
	 * Show a PDF (provided as {@link File}) with PDF viewer.
	 * 
	 * @param pdfStream PDF content as stream
     * @throws Exception
	 */
	public static void open(final DataInputStream pdfStream) throws Exception {
		open(HelperIO.readStream(pdfStream));
	}
	
	
	/*
	 * Private methods
	 */
	private static void execute(final String path) throws Exception {
		final String viewerPath;
		if (HelperEnvInfo.isWindowsPlatform()) {
			viewerPath = WINDOWS_PDF_VIEWER_PATH;
		} else if (HelperEnvInfo.isMacPlatform()) {
			ControlBrowser.display(new URL("file://" + path)); //$NON-NLS-1$
			return;
		} else {
			viewerPath = UNIX_PDF_VIEWER_PATH;
		}
		final String cmd = MessageFormat.format(viewerPath, path);
		ControlProcess.createProcess(cmd);
	}
}
