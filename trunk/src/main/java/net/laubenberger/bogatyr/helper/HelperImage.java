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
import java.awt.RenderingHints;
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

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a helper class for image operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100525)
 * @since 0.4.0
 */
public abstract class HelperImage {
	private static final Logger log = LoggerFactory
			.getLogger(HelperImage.class);

	public static final String TYPE_JPG = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF = "gif"; //$NON-NLS-1$
	public static final String TYPE_BMP = "bmp"; //$NON-NLS-1$

//	   1. public static BufferedImage rotate(BufferedImage img, int angle) {
//   2.         int w = img.getWidth();
//   3.         int h = img.getHeight();
//   4.         BufferedImage dimg = dimg = new BufferedImage(w, h, img.getType());
//   5.         Graphics2D g = dimg.createGraphics();
//   6.         g.rotate(Math.toRadians(angle), w/2, h/2);
//   7.         g.drawImage(img, null, 0, 0);
//   8.         return dimg;
//   9.     }
//
//	   1. public static BufferedImage verticalflip(BufferedImage img) {
//   2.         int w = img.getWidth();
//   3.         int h = img.getHeight();
//   4.         BufferedImage dimg = dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
//   5.         Graphics2D g = dimg.createGraphics();
//   6.         g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
//   7.         g.dispose();
//   8.         return dimg;
//   9.     }
//
//	   1. public static BufferedImage horizontalflip(BufferedImage img) {
//   2.         int w = img.getWidth();
//   3.         int h = img.getHeight();
//   4.         BufferedImage dimg = new BufferedImage(w, h, img.getType());
//   5.         Graphics2D g = dimg.createGraphics();
//   6.         g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
//   7.         g.dispose();
//   8.         return dimg;
//   9.     }
//
//
//
//	   1. public static BufferedImage makeColorTransparent(String ref, Color color) {
//   2.         BufferedImage image = loadImage(ref);
//   3.         BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
//
//   1.     Graphics2D g = dimg.createGraphics();
//   2.     g.setComposite(AlphaComposite.Src);
//   3.     g.drawImage(image, null, 0, 0);
//   4.     g.dispose();
//   5.     for(int i = 0; i < dimg.getHeight(); i++) {
//   6.         for(int j = 0; j < dimg.getWidth(); j++) {
//   7.             if(dimg.getRGB(j, i) == color.getRGB()) {
//   8.             dimg.setRGB(j, i, 0x8F1C1C);
//   9.             }
//  10.         }
//  11.     }
//  12.     return dimg;
//  13. }
//
//
//
//	   1. public static BufferedImage loadTranslucentImage(String url, float transperancy) {
//   2.         // Load the image
//   3.         BufferedImage loaded = loadImage(url);
//   4.         // Create the image using the
//   5.         BufferedImage aimg = new BufferedImage(loaded.getWidth(), loaded.getHeight(), BufferedImage.TRANSLUCENT);
//   6.         // Get the images graphics
//   7.         Graphics2D g = aimg.createGraphics();
//   8.         // Set the Graphics composite to Alpha
//   9.         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transperancy));
//  10.         // Draw the LOADED img into the prepared reciver image
//  11.         g.drawImage(loaded, null, 0, 0);
//  12.         // let go of all system resources in this Graphics
//  13.         g.dispose();
//  14.         // Return the image
//  15.         return aimg;
//  16.     }


	
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
		final Graphics2D g2d = result.createGraphics();

		component.paint(g2d);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Scales a {@link BufferedImage} to an {@link Image}.
	 *
	 * @param image to scale
	 * @param scale for the new image
	 * @return scaled {@link BufferedImage}
	 * @see BufferedImage
	 * @since 0.9.0
	 */
	public static BufferedImage getScaledImage(final BufferedImage image,
												  final double scale) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(image, scale));
		if (null == image) {
			throw new RuntimeExceptionIsNull("image"); //$NON-NLS-1$
		}

		final Dimension size = HelperGraphic.getScaledSize(new Dimension(image.getWidth(), image.getHeight()), scale);
		final BufferedImage result = getScaledImage(image, size);
		
		
		//		final Image result = image.getScaledInstance(size.width, size.height,
//				Image.SCALE_SMOOTH);

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
	 * @return scaled {@link BufferedImage}
	 * @see BufferedImage
	 * @see Dimension
	 * @since 0.9.0
	 */
	public static BufferedImage getScaledImage(final BufferedImage image, final Dimension size) {
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

		BufferedImage result; 
		if (size.equals(new Dimension(image.getWidth(), image.getHeight()))) {
			result = image;
		} else {
	      if (0 == size.width || 0 == size.height) {
	      	final Dimension sizeNew = HelperGraphic.getScaledSize(new Dimension(image.getWidth(), image.getHeight()), size);
	      	
	      	result = new BufferedImage(sizeNew.width, sizeNew.height, image.getType());  
	      	
	      	final Graphics2D g2d = result.createGraphics();  
	      	g2d.setRenderingHints(getHQRenderingHints());
	      	g2d.drawImage(image, 0, 0, sizeNew.width, sizeNew.height, 0, 0, image.getWidth(), image.getHeight(), null);  
	      	g2d.dispose();  		         
	      } else {
	      	result = new BufferedImage(size.width, size.height, image.getType());  
	      	
	      	final Graphics2D g2d = result.createGraphics();
	      	g2d.setRenderingHints(getHQRenderingHints());
	      	g2d.drawImage(image, 0, 0, size.width, size.height, 0, 0, image.getWidth(), image.getHeight(), null);  
	      	g2d.dispose();  
	      	
	      }
		} 
		
//		final Image result;
//
//		if (0 == size.width || 0 == size.height) {
//			final Dimension sizeNew = HelperGraphic.getScaledSize(new Dimension(image.getWidth(), image.getHeight()), size);
//			result = image.getScaledInstance(sizeNew.width, sizeNew.height, Image.SCALE_SMOOTH);
//		} else {
//			result = image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
//		}
//
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
	 * Returns a {@link BufferedImage} from a given {@link Image}.
	 *
	 * @param image to convert
	 * @param type BufferedImage.TYPE_xxx (e.g. BufferedImage.TYPE_INT_RGB, BufferedImage.TYPE_INT_ARGB)
	 * @return {@link BufferedImage} from the given {@link Image}
	 * @see BufferedImage
	 * @see Image
	 * @since 0.9.1
	 */
	public static BufferedImage getBufferedImage(final Image image, int type) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(image));

		final BufferedImage result;
		if (image instanceof BufferedImage) {
			result = (BufferedImage) image;
		} else if (image instanceof VolatileImage) {
			result = ((VolatileImage) image).getSnapshot();
		} else {
			loadImage(image);

			result = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);

			final Graphics2D g2 = result.createGraphics();
			g2.drawImage(image, null, null);
			g2.dispose();
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
	 * Returns a set of high quality {@link RenderingHints}.
	 *
	 * @return set of high quality {@link RenderingHints}
	 * @see RenderingHints
	 * @since 0.9.2
	 */
	public static RenderingHints getHQRenderingHints() {
	    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    return hints;
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