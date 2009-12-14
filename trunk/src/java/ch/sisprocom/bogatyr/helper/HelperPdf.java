/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xhtmlrenderer.pdf.ITextRenderer;

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
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091214)
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
		if (null == component) {
			throw new IllegalArgumentException("component is null!"); //$NON-NLS-1$
		}
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
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
     *
     * @param file output as PDF
     * @param files in (X)HTML format for the PDF
     * @throws DocumentException
     * @throws IOException
     * @see File
     * @since 0.5.0
     */
	public static void writePdfFromHTML(final File file, final File... files) throws IOException, DocumentException { //$JUnit$
		if (!HelperArray.isValid(files)) {
			throw new IllegalArgumentException("files is null or empty!"); //$NON-NLS-1$
		}
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
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
			throw new IllegalArgumentException("source is null!"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
		}
		if (!HelperMap.isValid(metadata)) {
			throw new IllegalArgumentException("metadata is null or empty!"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new IllegalArgumentException("source is equals to dest!"); //$NON-NLS-1$
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
			throw new IllegalArgumentException("source is null!"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new IllegalArgumentException("source is equals to dest!"); //$NON-NLS-1$
		}

		PdfReader reader = null;
		
		try {
			reader = new PdfReader(source.getAbsolutePath());
			PdfEncryptor.encrypt(reader, new FileOutputStream(dest), user, password,
					PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_COPY
			        | PdfWriter.ALLOW_DEGRADED_PRINTING | PdfWriter.ALLOW_FILL_IN
			        | PdfWriter.ALLOW_MODIFY_ANNOTATIONS | PdfWriter.ALLOW_MODIFY_CONTENTS
			        | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS, false);
		} finally {
			if (null != reader) {
				reader.close();	   
			}
		}
	} 
}