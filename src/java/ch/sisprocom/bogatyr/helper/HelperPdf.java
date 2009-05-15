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

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * This is a helper class for PDF operations.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class HelperPdf {

    /**
     * Saves a PDF from a {@link Component} to a {@link File}.
     *
     * @param component for the PDF
     * @param file output
     * @throws DocumentException
     * @throws IOException
     */
    public static void savePdfFromComponent(final Component component, final File file) throws IOException, DocumentException { //$JUnit
		if (null == component) {
			throw new IllegalArgumentException("component is null!"); //$NON-NLS-1$
		}
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		
    	final Dimension size = component.getSize();
		final Document document = new Document(new Rectangle((float) size.width, (float) size.height));
	    FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
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
            if (fos != null) {
                fos.close();
            }
        }
	}

    /**
     * Saves a PDF from multiple (X)HTML files to a {@link File}.
     *
     * @param input array with (X)HTML files for the PDF
     * @param file output
     * @throws DocumentException
     * @throws IOException
     */
	public static void savePdfFromHTML(final File[] input, final File file) throws IOException, DocumentException { //$JUnit
		if (!HelperGeneral.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		
		OutputStream os = null;

		try {
		   os = new FileOutputStream(file);
	
		   final ITextRenderer renderer = new ITextRenderer();
	
		   renderer.setDocument(input[0]);
		   renderer.layout();
		   renderer.createPDF(os, false);
	
		   for (int ii = 1; ii < input.length; ii++) {
		       renderer.setDocument(input[ii]);
		       renderer.layout();
		       renderer.writeNextDocument();
		   }
	
		   renderer.finishPDF();

		} finally {
		   if (os != null) {
	    	   os.close();
		   }
		}
	}
}