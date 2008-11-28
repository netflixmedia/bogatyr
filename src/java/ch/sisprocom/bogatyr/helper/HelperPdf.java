/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


/**
 * This is a helper class for PDF operations.
 * 
 * @author Stefan Laubenberger
 * @version 20081126
 */
public abstract class HelperPdf { //TODO document in Wiki!

    /**
     * Saves a pdf from a Component to a file.
     *
     * @param component Component for the pdf
     * @param output Filename
     * @throws com.lowagie.text.DocumentException
     * @throws java.io.IOException
     */
    public static void savePdfFromComponent(final Component component, final File output) throws IOException, DocumentException {
    	final Dimension size = component.getSize();
		final Document document = new Document(new Rectangle((float) size.width, (float) size.height));
	    FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(output);
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
     * Saves a pdf from multiple (X)HTML files to a file.
     *
     * @param input Array with (X)HTML files for the pdf
     * @param output Filename
     * @throws com.lowagie.text.DocumentException
     * @throws java.io.IOException
     */
	public static void savePdfFromHTML(final File[] input, final File output) throws IOException, DocumentException {
		OutputStream os = null;

		try {
		   os = new FileOutputStream(output);
	
		   ITextRenderer renderer = new ITextRenderer();
	
		   renderer.setDocument(input[0]);
		   renderer.layout();
		   renderer.createPDF(os, false);
	
		   for (int ii = 1; ii < input.length; ii++) {
		       renderer.setDocument(input[ii]);
		       renderer.layout();
		       renderer.writeNextDocument();
		   }
	
		   renderer.finishPDF();
	
//		   os.close();
//		   os = null;
		} finally {
		   if (os != null) {
	    	   os.close();
		   }
		}
	}

//	public static void createPdf(Component component, int width, int height, File file) throws FileNotFoundException, DocumentException {
////		component.setSize(width, height);
//		Document document = new Document(new Rectangle(width, height));
//	   
//	   try {
//	      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
//	      
//	      document.open();
//	      PdfContentByte cb = writer.getDirectContent();
//	      PdfTemplate tp = cb.createTemplate(width, height);
//	      
//	      Graphics2D g2d = tp.createGraphics(width, height, new DefaultFontMapper());
//	      component.paint(g2d);
//	      g2d.dispose();
//	      cb.addTemplate(tp, 0, 0);
//	   } finally {
//		   document.close();
//	   }
//	}
}