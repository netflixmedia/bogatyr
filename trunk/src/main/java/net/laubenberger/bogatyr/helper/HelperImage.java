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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

/**
 * This is a helper class for image operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.4.0
 */
public abstract class HelperImage {
	private static final Logger log = LoggerFactory
			.getLogger(HelperImage.class);

	public static final String TYPE_JPG = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF = "gif"; //$NON-NLS-1$
	public static final String TYPE_BMP = "bmp"; //$NON-NLS-1$

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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(file));
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		final BufferedImage result = ImageIO.read(file);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
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
	public static BufferedImage readImage(final InputStream is)
			throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(is));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}

		final BufferedImage result = ImageIO.read(is);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Writes an image from a {@link RenderedImage} to a {@link File}.
	 *
	 * @param file  for the image
	 * @param type  of the image (e.g. "jpg")
	 * @param image {@link RenderedImage} for the image
	 * @throws IOException
	 * @see File
	 * @see RenderedImage
	 * @since 0.4.0
	 */
	public static void writeImage(final File file, final String type,
											final RenderedImage image) throws IOException { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(file, type, image));
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == image) {
			throw new RuntimeExceptionIsNull("image"); //$NON-NLS-1$
		}
		if (null == type || !getAvailableImageWriteFormats().contains(type)) {
			throw new IllegalArgumentException(
					"type is null or invalid: " + type); //$NON-NLS-1$
		}

		ImageIO.write(image, type, file);
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Writes an image from a {@link RenderedImage} to an {@link OutputStream}.
	 *
	 * @param os	 output stream for the image
	 * @param type  of the image (e.g. "jpg")
	 * @param image {@link RenderedImage} for the image
	 * @throws IOException
	 * @see OutputStream
	 * @see RenderedImage
	 * @since 0.9.0
	 */
	public static void writeImage(final OutputStream os, final String type,
											final RenderedImage image) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(os, type, image));
		if (null == image) {
			throw new RuntimeExceptionIsNull("image"); //$NON-NLS-1$
		}
		if (null == type || !getAvailableImageWriteFormats().contains(type)) {
			throw new IllegalArgumentException(
					"type is null or invalid: " + type); //$NON-NLS-1$
		}
		if (null == os) {
			throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
		}

		ImageIO.write(image, type, os);
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Gets a {@link BufferedImage} from a {@link Component}.
	 *
	 * @param component for the image
	 * @return {@link Component} as {@link BufferedImage}
	 * @see BufferedImage
	 * @see Component
	 * @since 0.4.0
	 */
	public static BufferedImage getImage(final Component component) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(component));
		if (null == component) {
			throw new RuntimeExceptionIsNull("component"); //$NON-NLS-1$
		}

		final Dimension size = component.getSize();
		final BufferedImage result = new BufferedImage(size.width, size.height,
				BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2 = result.createGraphics();

		component.paint(g2);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
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
	public static Image getScaledImage(final BufferedImage image,
												  final double scale) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(image, scale));
		if (null == image) {
			throw new RuntimeExceptionIsNull("image"); //$NON-NLS-1$
		}

		final Dimension size = HelperGraphic.getScaledSize(new Dimension(image.getWidth(), image.getHeight()), scale);
		final Image result = image.getScaledInstance(size.width, size.height,
				Image.SCALE_SMOOTH);

//		final double width = (double) image.getWidth() * scale;
//		final double height = (double) image.getHeight() * scale;

//		final Image result = image.getScaledInstance(HelperMath
//				.convertDoubleToInt(width), HelperMath
//				.convertDoubleToInt(height), Image.SCALE_SMOOTH);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Scales a {@link BufferedImage} to an {@link Image} with the given
	 * {@link Dimension}. If the width or height is 0, the image will be scaled to fit the given parameter.
	 *
	 * @param image to scale
	 * @param size  of the new image
	 * @return scaled {@link Image}
	 * @see BufferedImage
	 * @see Dimension
	 * @see Image
	 * @since 0.9.0
	 */
	public static Image getScaledImage(final BufferedImage image, final Dimension size) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(image, size));
		if (null == image) {
			throw new RuntimeExceptionIsNull("image"); //$NON-NLS-1$
		}
		if (0 > size.width) {
			throw new RuntimeExceptionMustBeGreater("size.width", size.width, 0); //$NON-NLS-1$
		}
		if (0 > size.height) {
			throw new RuntimeExceptionMustBeGreater("size.height", size.height, 0); //$NON-NLS-1$
		}

		final Image result;

		if (0 == size.width || 0 == size.height) {
			final Dimension sizeNew = HelperGraphic.getScaledSize(new Dimension(image.getWidth(), image.getHeight()), size);
			result = image.getScaledInstance(sizeNew.width, sizeNew.height, Image.SCALE_SMOOTH);
		} else {
			result = image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available read formats (e.g. "png",
	 * "jpg").
	 *
	 * @return {@link Collection} containing all available read formats
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageReadFormats() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Collection<String> result = unique(ImageIO.getReaderFormatNames());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available write formats (e.g. "png",
	 * "jpeg").
	 *
	 * @return {@link Collection} containing all available write formats
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageWriteFormats() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Collection<String> result = unique(ImageIO.getWriterFormatNames());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available MIME types that can be read
	 * (e.g. "image/png", "image/jpeg").
	 *
	 * @return {@link Collection} containing all available MIME types that can
	 *         be read
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageReadMIMETypes() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Collection<String> result = unique(ImageIO.getReaderMIMETypes());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available MIME types that can be
	 * written (e.g. "image/png", "image/jpg").
	 *
	 * @return {@link Collection} containing all available MIME types that can
	 *         be written
	 * @since 0.4.0
	 */
	public static Collection<String> getAvailableImageWriteMIMETypes() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Collection<String> result = unique(ImageIO.getWriterMIMETypes());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Gets a {@link BufferedImage} from a given {@link Image}.
	 *
	 * @param image to convert
	 * @return {@link BufferedImage} from the given {@link Image}
	 * @see BufferedImage
	 * @see Image
	 * @since 0.9.1
	 */
	public static BufferedImage getBufferedImage(final Image image) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(image));

		final BufferedImage result;
		if (image instanceof BufferedImage) {
			result = (BufferedImage) image;
		} else if (image instanceof VolatileImage) {
			result = ((VolatileImage) image).getSnapshot();
		} else {
			loadImage(image);

			result = new BufferedImage(image.getWidth(null),
					image.getHeight(null), BufferedImage.TYPE_INT_RGB);

			final Graphics2D g2 = result.createGraphics();
			g2.drawImage(image, null, null);
			g2.dispose();
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}


	/*
	 * Private methods
	 */

	/**
	 * Converts all {@link String} to lower case and returns a
	 * {@link Collection} containing the unique values.
	 *
	 * @param strings as array
	 * @return {@link Collection} containing the unique values
	 * @since 0.4.0
	 */
	private static Collection<String> unique(final String... strings) {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart(strings));
		final Collection<String> result = new HashSet<String>(strings.length);

		for (final String str : strings) {
			result.add(str.toLowerCase(Locale.getDefault()));
		}

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit(result));
		return result;
	}

	private static void loadImage(final Image image) {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart(image));

		class StatusObserver implements ImageObserver {
			boolean imageLoaded;

			@Override
			public boolean imageUpdate(final Image img, final int infoflags,
												final int x, final int y, final int width, final int height) {
				if (ALLBITS == infoflags) {
					synchronized (this) {
						imageLoaded = true;
						notifyAll();
					}
					return true;
				}
				return false;
			}
		}

		final StatusObserver imageStatus = new StatusObserver();
		synchronized (imageStatus) {
			if (-1 == image.getWidth(imageStatus)
					|| -1 == image.getHeight(imageStatus)) {
				while (!imageStatus.imageLoaded) {
					try {
						imageStatus.wait();
					} catch (InterruptedException ex) {
						//do nothing
					}
				}
			}
		}

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}
}