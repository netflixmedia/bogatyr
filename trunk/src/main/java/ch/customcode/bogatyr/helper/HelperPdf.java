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
package ch.customcode.bogatyr.helper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.xhtmlrenderer.pdf.ITextRenderer;

import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfEncryptor;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


/**
 * This is a helper class for PDF operations.
 * <strong>Note:</strong> This class needs <a href="http://itextpdf.com/">iText</a> to work.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.5.0
 */
public abstract class HelperPdf {

    /**
     * Writes a PDF from a {@link Component} to a {@link File}.
     *
     * @param file output as PDF
     * @param component for the PDF
     * @throws DocumentException
     * @throws IOException
     * @see File
     * @see Component
     * @since 0.5.0
     */
    public static void writePdfFromComponent(final File file, final Component component) throws IOException, DocumentException { //$JUnit$
    	if (null == file) {
    		throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
    	}
		if (null == component) {
			throw new RuntimeExceptionIsNull("component"); //$NON-NLS-1$
		}
		
    	final Dimension size = component.getSize();
		final Document document = new Document(new Rectangle((float) size.width, (float) size.height));
		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

        try {
            final PdfWriter writer = PdfWriter.getInstance(document, fos);
			      
			document.open();
			final PdfContentByte cb = writer.getDirectContent();
			final PdfTemplate tp = cb.createTemplate((float) size.width, (float) size.height);
			      
			final Graphics2D g2d = tp.createGraphics((float) size.width, (float) size.height, new DefaultFontMapper());
			component.paint(g2d);
			g2d.dispose();
			cb.addTemplate(tp, 0.0F, 0.0F);
		} finally {
			document.close();
            fos.close();
        }
	}

    /**
     * Writes a PDF from multiple (X)HTML files to a {@link File}.
     * <strong>Note:</strong> This method needs <a href="https://xhtmlrenderer.dev.java.net/">XHTML</a> to work.
     *
     * @param file output as PDF
     * @param files in (X)HTML format for the PDF
     * @throws DocumentException
     * @throws IOException
     * @see File
     * @since 0.5.0
     */
	public static void writePdfFromHTML(final File file, final File... files) throws IOException, DocumentException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(files)) {
			throw new RuntimeExceptionIsNullOrEmpty("files"); //$NON-NLS-1$
		}
		
		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

		try {
		   final ITextRenderer renderer = new ITextRenderer();
	
		   renderer.setDocument(files[0]);
		   renderer.layout();
		   renderer.createPDF(fos, false);
	
		   for (int ii = 1; ii < files.length; ii++) {
		       renderer.setDocument(files[ii]);
		       renderer.layout();
		       renderer.writeNextDocument();
		   }
	
		   renderer.finishPDF();

		} finally {
		   fos.close();
		}
	}
	
    /**
     * Modifies the meta data of given PDF and stores it in a new {@link File}.
     * <strong>Meta data example:"</strong>
     * metadata.put("Title", "Example");
     * metadata.put("Author", "Stefan Laubenberger");
     * metadata.put("Subject", "Example PDF meta data");
     * metadata.put("Keywords", "Example, PDF");
     * metadata.put("Creator", "http://www.sisprocom.ch/");
     * metadata.put("Producer", "Silvan Spross");
     * 
     * @param source {@link File}
     * @param dest {@link File} for the modified PDF
     * @param metadata list with the new meta data informations
     * @throws DocumentException
     * @throws IOException
     * @since 0.7.0
     */
	@SuppressWarnings("unchecked")
	public static void setMetaData(final File source, final File dest, final Map<String, String> metadata) throws IOException, DocumentException {
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (!HelperMap.isValid(metadata)) {
			throw new RuntimeExceptionIsNullOrEmpty("metadata"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new RuntimeExceptionIsEquals("source", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		PdfReader reader = null;
		PdfStamper stamper = null;
		
		try {
			reader = new PdfReader(source.getAbsolutePath());
			stamper = new PdfStamper(reader, new FileOutputStream(dest));
			
			final HashMap<String, String> info = reader.getInfo();
			info.putAll(metadata);

			stamper.setMoreInfo(info);
		} finally {
			if (null != stamper) {
				stamper.close();
			}
			if (null != reader) {
				reader.close();	   
			}
		}
	} 
	
	/**
     * Removes all locks and restrictions from a PDF.
     * 
     * @param source {@link File}
     * @param dest {@link File} for the unlocked PDF
     * @param user of the source {@link File}
     * @param password of the source {@link File}
     * @throws DocumentException
     * @throws IOException
     * @since 0.9.0
     */
	public static void unlock(final File source, final File dest, final byte[] user, final byte[] password) throws IOException, DocumentException {
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new RuntimeExceptionIsEquals("source", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		PdfReader reader = null;
        OutputStream os = null;
		
		try {
			reader = new PdfReader(source.getAbsolutePath());
            os = new FileOutputStream(dest);

			PdfEncryptor.encrypt(reader, os, user, password,
					PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_COPY
			        | PdfWriter.ALLOW_DEGRADED_PRINTING | PdfWriter.ALLOW_FILL_IN
			        | PdfWriter.ALLOW_MODIFY_ANNOTATIONS | PdfWriter.ALLOW_MODIFY_CONTENTS
			        | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS, false);
		} finally {
			if (null != os) {
				os.close();
			}
            if (null != reader) {
				reader.close();	   
			}
		}
	} 
}