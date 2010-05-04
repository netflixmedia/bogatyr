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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for graphic operations
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.4.0
 */
public abstract class HelperGraphic {
	private static final Logger log = LoggerFactory.getLogger(HelperGraphic.class);

	/**
	 * Enable anti-aliasing on a {@link Graphics2D} container.
	 *
	 * @param graphics {@link Graphics2D} container
	 * @see Graphics2D
	 * @since 0.4.0
	 */
	public static void enableAntialiasing(final Graphics2D graphics) {
		log.debug(HelperLog.methodStart(graphics));
		if (null == graphics) {
			throw new RuntimeExceptionIsNull("graphics"); //$NON-NLS-1$
		}

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Calculates the center of given a {@link Dimension}.
	 *
	 * @param size {@link Dimension} for the calculation
	 * @return {@link Dimension} with center coordinates
	 * @see Dimension
	 * @since 0.4.0
	 */
	public static Dimension getCenter(final Dimension size) { //$JUnit$
		log.debug(HelperLog.methodStart(size));
		if (null == size) {
			throw new RuntimeExceptionIsNull("size"); //$NON-NLS-1$
		}

		final Dimension result = new Dimension(size.width / 2, size.height / 2);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Calculates the scale to fit an input {@link Dimension} to an output {@link Dimension}.
	 *
	 * @param input  {@link Dimension} for the calculation
	 * @param output {@link Dimension} for the calculation
	 * @return scale to fit the input {@link Dimension}
	 * @see Dimension
	 * @since 0.9.0
	 */
	public static double getScale(final Dimension input, final Dimension output) { //$JUnit$
		log.debug(HelperLog.methodStart(input, output));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (0 > input.width) {
			throw new RuntimeExceptionMustBeGreater("input.width", input.width, 0); //$NON-NLS-1$
		}
		if (0 > input.height) {
			throw new RuntimeExceptionMustBeGreater("input.height", input.height, 0); //$NON-NLS-1$
		}
		if (null == output) {
			throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
		}
		if (0 > output.width) {
			throw new RuntimeExceptionMustBeGreater("output.width", output.width, 0); //$NON-NLS-1$
		}
		if (0 > output.height) {
			throw new RuntimeExceptionMustBeGreater("output.height", output.height, 0); //$NON-NLS-1$
		}

		double scaleWidth = 0.0D;
		double scaleHeight = 0.0D;

		if (0 == output.width) {
			if (0 != output.height) {
				scaleHeight = input.getHeight() / output.getHeight();
			}
		} else {
			scaleWidth = input.getWidth() / output.getWidth();
		}
		if (0 == output.height) {
			if (0 != output.width) {
				scaleWidth = input.getWidth() / output.getWidth();
			}
		} else {
			scaleHeight = input.getHeight() / output.getHeight();
		}

		final double result = 0.0D == scaleWidth && 0.0D == scaleHeight ? 1.0D : scaleWidth > scaleHeight ? 1.0D / scaleWidth : 1.0D / scaleHeight;

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Calculates the scaled size to fit an input {@link Dimension} to an output {@link Dimension}.
	 *
	 * @param input  {@link Dimension} for the calculation
	 * @param output {@link Dimension} for the calculation
	 * @return scaled {@link Dimension} to fit the input {@link Dimension}
	 * @see Dimension
	 * @since 0.9.0
	 */
	public static Dimension getScaledSize(final Dimension input, final Dimension output) {
		log.debug(HelperLog.methodStart(input, output));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (0 > input.width) {
			throw new RuntimeExceptionMustBeGreater("input.width", input.width, 0); //$NON-NLS-1$
		}
		if (0 > input.height) {
			throw new RuntimeExceptionMustBeGreater("input.height", input.height, 0); //$NON-NLS-1$
		}
		if (null == output) {
			throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
		}
		if (0 > output.width) {
			throw new RuntimeExceptionMustBeGreater("output.width", output.width, 0); //$NON-NLS-1$
		}
		if (0 > output.height) {
			throw new RuntimeExceptionMustBeGreater("output.height", output.height, 0); //$NON-NLS-1$
		}

//		final double scaleHeight = input.getHeight() / output.getHeight();
//		final double scaleWidth = input.getWidth() / output.getWidth();
//        final double scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
//
//        final double scale = getScale(input, output);

		final Dimension result = getScaledSize(input, getScale(input, output));

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Calculates the scaled size to for an input {@link Dimension} with a given scale.
	 *
	 * @param input {@link Dimension} for the calculation
	 * @param scale for the new {@link Dimension}
	 * @return scaled {@link Dimension} of the input {@link Dimension}
	 * @see Dimension
	 * @since 0.9.1
	 */
	public static Dimension getScaledSize(final Dimension input, final double scale) {
		log.debug(HelperLog.methodStart(input, scale));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
//    	if (input.width < 0) {
//			throw new RuntimeExceptionMustBeGreater("input.width", input.width, 0); //$NON-NLS-1$
//		}
//    	if (input.height < 0) {
//			throw new RuntimeExceptionMustBeGreater("input.height", input.height, 0); //$NON-NLS-1$
//		}

//		final double scaleHeight = input.getHeight() * scale;
//		final double scaleWidth = input.getWidth() * scale;
//        final double scaleNew = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
//		
//    	final Dimension result = new Dimension(HelperMath.convertDoubleToInt(input.getWidth() * scaleNew), HelperMath.convertDoubleToInt(input.getHeight() * scaleNew));

		final Dimension result = new Dimension((int) (input.getWidth() * scale), (int) (input.getHeight() * scale));

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Calculates the size of a text in a {@link Graphics} container with its current {@link Font}.
	 *
	 * @param text	  for the calculation
	 * @param graphics {@link Graphics} container for the calculation
	 * @return {@link Dimension} with text size
	 * @see Graphics
	 * @see Dimension
	 * @see Font
	 * @since 0.4.0
	 */
	public static Dimension getTextSize(final String text, final Graphics graphics) {
		log.debug(HelperLog.methodStart(text, graphics));
		if (null == text) {
			throw new RuntimeExceptionIsNull("text"); //$NON-NLS-1$
		}
		if (null == graphics) {
			throw new RuntimeExceptionIsNull("graphics"); //$NON-NLS-1$
		}

		final FontMetrics fm = graphics.getFontMetrics(graphics.getFont());
		final Rectangle2D rect = fm.getStringBounds(text, graphics);

		final Dimension result = new Dimension((int) rect.getWidth(), (int) rect.getHeight());

		log.debug(HelperLog.methodExit(result));
		return result;
	}


//	public static List<String> getFonts() {
//	    // Get all available font family names from GraphicsEnvironment
//	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    return Arrays.asList(ge.getAvailableFontFamilyNames());
//	}

	/**
	 * Returns a {@link Collection} containing all available system {@link Font}.
	 *
	 * @return {@link Collection} containing all fonts
	 * @see Font
	 * @since 0.4.0
	 */
	public static Collection<Font> getAvailableFonts() { //$JUnit$
		log.debug(HelperLog.methodStart());
		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		final Collection<Font> result = Arrays.asList(ge.getAllFonts());

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the hex value of a {@link Color} (RGB).
	 * Used e.g. for HTML.
	 *
	 * @param color for the hex value
	 * @return hex value of the color
	 * @since 0.4.0
	 */
	public static String getColorHex(final Color color) { //$JUnit$
		log.debug(HelperLog.methodStart(color));
		if (null == color) {
			throw new RuntimeExceptionIsNull("color"); //$NON-NLS-1$
		}

		final String result = Integer.toHexString(color.getRGB() & 0x00ffffff);

		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
