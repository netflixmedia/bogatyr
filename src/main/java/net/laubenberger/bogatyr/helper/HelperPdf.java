/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfEncryptor;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;


/**
 * This is a helper class for PDF operations.
 * <strong>Note:</strong> This class needs <a href="http://itextpdf.com/">iText</a> to work.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100423)
 * @since 0.5.0
 */
public abstract class HelperPdf {
	private static final Logger log = LoggerFactory.getLogger(HelperPdf.class);

//	/**
//	 * Writes a PDF from multiple {@link Component} to a {@link File}.
//	 *
//	 * @param pageSize of the PDF
//	 * @param scale images to fit the page size
//	 * @param file		output as PDF
//	 * @param components for the PDF
//	 * @throws DocumentException
//	 * @throws IOException
//	 * @see File
//	 * @see Component
//	 * @since 0.5.0
//	 */
//	public static void writePdfFromComponent(Rectangle pageSize, boolean scale, final File file, final Component... components) throws IOException, DocumentException { //$JUnit$ //TODO enable multiple components
//		log.debug(HelperLog.methodStart(pageSize, scale, file, components));
//		if (null == pageSize) {
//			throw new RuntimeExceptionIsNull("pageSize"); //$NON-NLS-1$
//		}
//		if (null == file) {
//			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
//		}
//		if (null == components) {
//			throw new RuntimeExceptionIsNull("components"); //$NON-NLS-1$
//		}
//
//		final Document document = new Document(pageSize);
//		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
//
//		try {
//			final PdfWriter writer = PdfWriter.getInstance(document, fos);
//			document.open();
//
//			for (Component component : components) {
//				final PdfContentByte cb = writer.getDirectContent();
//				PdfTemplate tp;
//				Graphics2D g2d;
//
//				if (scale) {
//					Dimension dim = HelperGraphic.getScaledSize(component.getSize(), new Dimension((int)pageSize.getWidth(), (int)pageSize.getHeight()));
//					
//					System.out.println(dim);
//					System.out.println(pageSize);
//					tp = cb.createTemplate(pageSize.getWidth(), pageSize.getHeight());
//					g2d = tp.createGraphics((float)dim.getWidth(), (float)dim.getHeight(), new DefaultFontMapper());
//				} else {
//					tp = cb.createTemplate(pageSize.getWidth(), pageSize.getHeight());
//					g2d = tp.createGraphics(component.getWidth(), component.getHeight(), new DefaultFontMapper());
//				}
//	
//				component.paint(g2d);
//				g2d.dispose();
//				cb.addTemplate(tp, 0.0F, 0.0F);
//				document.newPage();
//			}
//		} finally {
//			document.close();
//			fos.close();
//		}
//		log.debug(HelperLog.methodExit());
//	}
 
	/**
	 * Writes a PDF from multiple (X)HTML files to a {@link File}.
	 * <strong>Note:</strong> This method needs <a href="https://xhtmlrenderer.dev.java.net/">XHTML</a> to work.
	 *
	 * @param file  output as PDF
	 * @param files in (X)HTML format for the PDF
	 * @throws DocumentException
	 * @throws IOException
	 * @see File
	 * @since 0.5.0
	 */
	public static void writePdfFromHTML(final File file, final File... files) throws IOException, DocumentException { //$JUnit$
		log.debug(HelperLog.methodStart(file, files));
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
		log.debug(HelperLog.methodExit());
	}
	
	/**
	 * Writes a PDF from multiple image files to a {@link File}.
	 *
	 * @param pageSize of the PDF
	 * @param scale images to fit the page size
	 * @param file  output as PDF
	 * @param files for the PDF
	 * @throws DocumentException 
	 * @throws IOException 
	 * @see File
	 * @see Rectangle
	 * @since 0.9.2
	 */
	public static void writePdfFromImage(Rectangle pageSize, boolean scale, final File file, final File... files) throws DocumentException, IOException {
		log.debug(HelperLog.methodStart(pageSize, scale, file, files));
		if (null == pageSize) {
			throw new RuntimeExceptionIsNull("pageSize"); //$NON-NLS-1$
		}
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(files)) {
			throw new RuntimeExceptionIsNullOrEmpty("files"); //$NON-NLS-1$
		}

		final Document document = new Document(pageSize);
		document.setMargins(0.0F, 0.0F, 0.0F, 0.0F);
		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

		try {
			PdfWriter.getInstance(document, fos);
			document.open();

			for (final File tempFile : files) {
				final Image image = Image.getInstance(tempFile.getAbsolutePath());
				
				if (scale) {
					image.scaleToFit(pageSize.getWidth(), pageSize.getHeight());
				}
				document.add(image);
				document.newPage();
			}
		} finally {
			document.close();
			fos.close();
		}
		log.debug(HelperLog.methodExit());
	}
	
	/**
	 * Writes a PDF from multiple {@link java.awt.Image} to a {@link File}.
	 *
	 * @param pageSize of the PDF
	 * @param scale images to fit the page size
	 * @param file  output as PDF
	 * @param images for the PDF
	 * @throws DocumentException 
	 * @throws IOException 
	 * @see File
	 * @see java.awt.Image
	 * @see Rectangle
	 * @since 0.9.2
	 */
	public static void writePdfFromImage(Rectangle pageSize, boolean scale, final File file, final java.awt.Image... images) throws DocumentException, IOException {
		log.debug(HelperLog.methodStart(pageSize, scale, file, images));
		if (null == pageSize) {
			throw new RuntimeExceptionIsNull("pageSize"); //$NON-NLS-1$
		}		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(images)) {
			throw new RuntimeExceptionIsNullOrEmpty("images"); //$NON-NLS-1$
		}
		
		final Document document = new Document(pageSize);
		document.setMargins(0.0F, 0.0F, 0.0F, 0.0F);
		
		final FilterOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));

		try {
			PdfWriter.getInstance(document, fos);
			document.open();
	
			for (final java.awt.Image tempImage : images) {
				final Image image = Image.getInstance(tempImage, null);
				
				if (scale) {
					image.scaleToFit(pageSize.getWidth(), pageSize.getHeight());
				}
				document.add(image);
				document.newPage();
			}
		} finally {
			document.close();
			fos.close();
		}
		log.debug(HelperLog.methodExit());
	}
	
	/**
	 * Modifies the meta data of given PDF and stores it in a new {@link File}.
	 * <strong>Meta data example:"</strong>
	 * metadata.put("Title", "Example");
	 * metadata.put("Author", "Stefan Laubenberger");
	 * metadata.put("Subject", "Example PDF meta data");
	 * metadata.put("Keywords", "Example, PDF");
	 * metadata.put("Creator", "http://www.laubenberger.net/");
	 * metadata.put("Producer", "Silvan Spross");
	 *
	 * @param source	{@link File}
	 * @param dest	  {@link File} for the modified PDF
	 * @param metadata list with the new meta data informations
	 * @throws DocumentException
	 * @throws IOException
	 * @since 0.7.0
	 */
	@SuppressWarnings("unchecked")
	public static void setMetaData(final File source, final File dest, final Map<String, String> metadata) throws IOException, DocumentException {
		log.debug(HelperLog.methodStart(source, dest, metadata));
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (!HelperMap.isValid(metadata)) {
			throw new RuntimeExceptionIsNullOrEmpty("metadata"); //$NON-NLS-1$
		}
		if (HelperObject.isEquals(source, dest)) {
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
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Removes all locks and restrictions from a PDF.
	 *
	 * @param source	{@link File}
	 * @param dest	  {@link File} for the unlocked PDF
	 * @param user	  of the source {@link File}
	 * @param password of the source {@link File}
	 * @throws DocumentException
	 * @throws IOException
	 * @since 0.9.0
	 */
	public static void unlock(final File source, final File dest, final byte[] user, final byte[] password) throws IOException, DocumentException {
		log.debug(HelperLog.methodStart(source, dest, user, password));
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (HelperObject.isEquals(source, dest)) {
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
		log.debug(HelperLog.methodExit());
	}
}