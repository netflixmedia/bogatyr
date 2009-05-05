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
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;


/**
 * This is a helper class for image operations.
 * 
 * @author Stefan Laubenberger
 * @version 20090504
 */
public abstract class HelperImage { //TODO document in Wiki!
	public static final String TYPE_JPG = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF = "gif"; //$NON-NLS-1$

    /**
     * Saves an image from a RenderImage to a file.
     *
     * @param image RenderImage for the image
     * @param type Image type (e.g. "jpg")
     * @param output Filename
     * @throws IOException
     */
    public static void saveImage(final RenderedImage image, final String type, final File output) throws IOException {
    	ImageIO.write(image, type, output);
    }

    /**
     * Saves an image from a Component to a file.
     *
     * @param component Component for the image
     * @param type Image type (e.g. "jpg")
     * @param output Filename
     * @throws IOException
     */
	public static void saveImage(final Component component, final String type, final File output) throws IOException {
		saveImage(getImage(component), type, output);
	} 
    
	/**
     * Gets an image from a Component.
     *
     * @param component Component for the image
     * @return component as BufferedImage
     * @throws IOException
     */
	public static RenderedImage getImage(final Component component) {
		final Dimension size = component.getSize();
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2 = image.createGraphics();
		
		component.paint(g2);
       
		return image;
	}
	
	/**
	 * Returns list of unique supported read formats (e.g. "png", "jpg").
	 * 
	 * @return list of unique supported read formats
	 */
	public static Collection<String> getImageReadFormats() {
	    final String[] formatNames = ImageIO.getReaderFormatNames();
	    
	    return unique(formatNames);
	}

	/**
	 * Returns list of unique supported write formats (e.g. "png", "jpg").
	 * 
	 * @return list of unique supported write formats
	 */
	public static Collection<String> getImageWriteFormats() {
	    final String[] formatNames = ImageIO.getWriterFormatNames();
	    
	    return unique(formatNames);
	}

	/**
	 * Returns list of unique MIME types that can be read (e.g. "image/png", "image/jpg").
	 * 
	 * @return list of unique MIME types that can be read
	 */
	public static Collection<String> getImageReadMIMETypes() {
	    final String[] formatNames = ImageIO.getReaderMIMETypes();
	    
	    return unique(formatNames);
	}

	/**
	 * Returns list of unique MIME types that can be written (e.g. "image/png", "image/jpg").
	 * 
	 * @return list of unique MIME types that can be written
	 */
	public static Collection<String> getImageWriteMIMETypes() {
	    final String[] formatNames = ImageIO.getWriterMIMETypes();
	    
	    return unique(formatNames);
	}

	/**
	 * Converts all strings to lowercase and returns a list containing the unique values.
	 * 
	 * @param strings as array
	 * @return list containing the unique values
	 */
    private static Collection<String> unique(final String[] strings) {
        final Set<String> set = new HashSet<String>(strings.length);
        
        for (final String str : strings) {
            set.add(str.toLowerCase());
        }

        return new ArrayList<String>(set);
    }
}