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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;


/**
 * This is a helper class for image operations.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091111)
 * @since 0.4.0
 */
public abstract class HelperImage {
	public static final String TYPE_JPG  = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG  = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF  = "gif"; //$NON-NLS-1$
	public static final String TYPE_BMP  = "bmp"; //$NON-NLS-1$

    /**
     * Reads an image from a {@link File} to a {@link BufferedImage}.
     *
     * @param file for the image
     * @return {@link BufferedImage}
     * @throws IOException
     * @see File
     * @see BufferedImage
     * @since 0.9.0
     */
    public static BufferedImage readImage(final File file) throws IOException {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (!file.exists()) {
			throw new IllegalArgumentException("file doesn't exists: " + file); //$NON-NLS-1$
		}

		return ImageIO.read(file);
    }
    
    /**
     * Reads an image from an {@link InputStream} to a {@link BufferedImage}.
     *
     * @param is input stream for the image
     * @return {@link BufferedImage}
     * @throws IOException
     * @see InputStream
     * @see BufferedImage
     * @since 0.9.0
     */
    public static BufferedImage readImage(final InputStream is) throws IOException {
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}

		return ImageIO.read(is);
    }  
    
	/**
     * Writes an image from a {@link RenderedImage} to a {@link File}.
     *
     * @param file for the image
     * @param type of the image (e.g. "jpg")
     * @param image {@link RenderedImage} for the image
     * @throws IOException
     * @see File
     * @see RenderedImage
     * @since 0.4.0
     */
    public static void writeImage(final File file, final String type, final RenderedImage image) throws IOException { //$JUnit$
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
     * Writes an image from a {@link RenderedImage} to an {@link OutputStream}.
     *
     * @param os output stream for the image
     * @param type of the image (e.g. "jpg")
     * @param image {@link RenderedImage} for the image
     * @throws IOException
     * @see OutputStream
     * @see RenderedImage
     * @since 0.9.0
     */
    public static void writeImage(final OutputStream os, final String type, final RenderedImage image) throws IOException {
		if (null == image) {
			throw new IllegalArgumentException("image is null!"); //$NON-NLS-1$
		}
		if (null == type || !getAvailableImageWriteFormats().contains(type)) {
			throw new IllegalArgumentException("type is null or invalid: " + type); //$NON-NLS-1$
		}
		if (null == os) {
			throw new IllegalArgumentException("os is null!"); //$NON-NLS-1$
		}
		
		ImageIO.write(image, type, os);
    }

    
	/**
     * Gets a {@link RenderedImage} from a {@link Component}.
     *
     * @param component for the image
     * @return {@link Component} as {@link RenderedImage}
     * @see RenderedImage
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
     * Scales a {@link BufferedImage} to an {@link Image}.
     *
     * @param image to scale
     * @param scale for the new image
     * @return scaled {@link Image}
     * @see BufferedImage
     * @see Image
     * @since 0.9.0
     */
	public static Image getScaledImage(final BufferedImage image, final double scale) {
		if (null == image) {
			throw new IllegalArgumentException("image is null!"); //$NON-NLS-1$
		}

		final double width = (double)image.getWidth() / scale;
		final double height = (double)image.getHeight() / scale;
		
		return image.getScaledInstance(HelperMath.convertDoubleToInt(width), HelperMath.convertDoubleToInt(height), Image.SCALE_SMOOTH);
	}
	
	/**
     * Scales a {@link Image} to an {@link Image} with the given {@link Dimension}.
     *
     * @param image to scale
     * @param size of the new image
     * @return scaled {@link Image}
     * @see BufferedImage
     * @see Dimension
     * @see Image
     * @since 0.9.0
     */
	public static Image getScaledImage(final Image image, final Dimension size) {
		if (null == image) {
			throw new IllegalArgumentException("image is null!"); //$NON-NLS-1$
		}

		return image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
	}
	
	/**
	 * Returns a {@link Collection} of all available read formats (e.g. "png", "jpg").
	 * 
	 * @return {@link Collection} containing all available read formats
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageReadFormats() { //$JUnit$
	    return unique(ImageIO.getReaderFormatNames());
	}

	/**
	 * Returns a {@link Collection} of all available write formats (e.g. "png", "jpeg").
	 * 
	 * @return {@link Collection} containing all available write formats
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageWriteFormats() { //$JUnit$
	    return unique(ImageIO.getWriterFormatNames());
	}

	/**
	 * Returns a {@link Collection} of all available MIME types that can be read (e.g. "image/png", "image/jpeg").
	 * 
	 * @return {@link Collection} containing all available MIME types that can be read
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageReadMIMETypes() { //$JUnit$
	    return unique(ImageIO.getReaderMIMETypes());
	}

	/**
	 * Returns a {@link Collection} of all available MIME types that can be written (e.g. "image/png", "image/jpg").
	 * 
	 * @return {@link Collection} containing all available MIME types that can be written
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
	 * @return {@link Collection} containing the unique values
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