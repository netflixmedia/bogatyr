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

import javax.imageio.ImageIO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;


/**
 * This is a helper class for image operations.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.4.0
 */
public abstract class HelperImage {
	public static final String TYPE_JPG  = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG  = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF  = "gif"; //$NON-NLS-1$
	public static final String TYPE_BMP  = "bmp"; //$NON-NLS-1$

    /**
     * Saves an image from a {@link RenderedImage} to a {@link File}.
     *
     * @param file for the image
     * @param type of the image (e.g. "jpg")
     * @param image RenderImage for the image
     * @throws IOException
     * @see File
     * @see RenderedImage
     * @since 0.4.0
     */
    public static void saveImage(final File file, final String type, final RenderedImage image) throws IOException { //$JUnit$
		if (null == image) {
			throw new IllegalArgumentException("image is null!"); //$NON-NLS-1$
		}
		if (null == type || !getAvailableImageWriteFormats().contains(type)) {
			throw new IllegalArgumentException("type is null or invalid: " + type); //$NON-NLS-1$
		}
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		
		ImageIO.write(image, type, file);
    }

    /**
     * Saves an image from a {@link Component} to a {@link File}.
     * 
     * @param file for the image
     * @param type of the image (e.g. "jpg")
     * @param component Component for the image
     * @throws IOException
     * @see File
     * @see Component
     * @since 0.4.0
     */
	public static void saveImage(final File file, final String type, final Component component) throws IOException { //$JUnit$
		saveImage(file, type, getImage(component));
	} 
    
	/**
     * Gets a {@link RenderedImage} from a {@link Component}.
     *
     * @param component for the image
     * @return component as image
     * @see Component
     * @since 0.4.0
     */
	public static RenderedImage getImage(final Component component) {
		if (null == component) {
			throw new IllegalArgumentException("component is null!"); //$NON-NLS-1$
		}

		final Dimension size = component.getSize();
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2 = image.createGraphics();
		
		component.paint(g2);
       
		return image;
	}
	
	/**
	 * Returns a {@link Collection} of all available read formats (e.g. "png", "jpg").
	 * 
	 * @return list of all available read formats
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageReadFormats() { //$JUnit$
	    return unique(ImageIO.getReaderFormatNames());
	}

	/**
	 * Returns a {@link Collection} of all available write formats (e.g. "png", "jpeg").
	 * 
	 * @return list of all available write formats
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageWriteFormats() { //$JUnit$
	    return unique(ImageIO.getWriterFormatNames());
	}

	/**
	 * Returns a {@link Collection} of all available MIME types that can be read (e.g. "image/png", "image/jpeg").
	 * 
	 * @return list of all available MIME types that can be read
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageReadMIMETypes() { //$JUnit$
	    return unique(ImageIO.getReaderMIMETypes());
	}

	/**
	 * Returns a {@link Collection} of all available MIME types that can be written (e.g. "image/png", "image/jpg").
	 * 
	 * @return list of all available MIME types that can be written
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageWriteMIMETypes() { //$JUnit$
	    return unique(ImageIO.getWriterMIMETypes());
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Converts all {@link String} to lower case and returns a {@link Collection} containing the unique values.
	 * 
	 * @param strings as array
	 * @return list containing the unique values
	 * @since 0.4.0
	 */
    private static Collection<String> unique(final String... strings) {
        final Collection<String> set = new HashSet<String>(strings.length);
        
        for (final String str : strings) {
            set.add(str.toLowerCase(Locale.getDefault()));
        }

        return set;
    }
}