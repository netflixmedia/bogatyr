/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collection;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * This is a helper class for graphic operations
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100209)
 * @since 0.4.0
 */
public abstract class HelperGraphic {
	
	/**
     * Enable anti-aliasing on a {@link Graphics2D} container.
     * 
     * @param graphics {@link Graphics2D} container
     * @see Graphics2D
     * @since 0.4.0
     */	
	public static void enableAntialiasing(final Graphics2D graphics) {
		if (null == graphics) {
			throw new RuntimeExceptionIsNull("graphics"); //$NON-NLS-1$
		}
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
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
//		if (null == size) {
//			throw new RuntimeExceptionArgumentIsNull("size"); //$NON-NLS-1$
//		}

		if (null != size) {
			return new Dimension(size.width / 2, size.height / 2);
		}
		return null;
	}

    /**
     * Calculates the scale to fit an input {@link Dimension2D} to an output {@link Dimension2D}.
     *
     * @param input {@link Dimension2D} for the calculation
     * @param output {@link Dimension2D} for the calculation
     * @return scale to fit the input {@link Dimension2D}
     * @see Dimension2D
     * @since 0.9.0
     */
    public static double getScale(final Dimension2D input, final Dimension2D output) { //$JUnit$
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (null == output) {
			throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
		}
		
		final double scaleHeight =  input.getHeight() / output.getHeight();
		final double scaleWidth =  input.getWidth() / output.getWidth();

        return scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
	}
    
    /**
     * Calculates the scaled size to fit an input {@link Dimension2D} to an output {@link Dimension2D}.
     *
     * @param input {@link Dimension2D} for the calculation
     * @param output {@link Dimension2D} for the calculation
     * @return scaled {@link Dimension2D} to fit the input {@link Dimension2D}
     * @see Dimension2D
     * @since 0.9.0
     */
    public static Dimension getScaledSize(final Dimension2D input, final Dimension2D output) { //$JUnit$
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (null == output) {
			throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
		}
		
		final double scaleHeight = input.getHeight() / output.getHeight();
		final double scaleWidth = input.getWidth() / output.getWidth();
        final double scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
		
		return new Dimension(HelperMath.convertDoubleToInt(input.getWidth() / scale), HelperMath.convertDoubleToInt(input.getHeight() / scale));
	}    
    
    /**
     * Calculates the size of a text in a {@link Graphics} container with its current {@link Font}.
     *
     * @param text for the calculation
     * @param graphics {@link Graphics} container for the calculation
     * @return {@link Dimension} with text size
     * @see Graphics
     * @see Dimension
     * @see Font
     * @since 0.4.0
     */
	public static Dimension getTextSize(final String text, final Graphics graphics) {
		if (null == text) {
			throw new RuntimeExceptionIsNull("text"); //$NON-NLS-1$
		}
		if (null == graphics) {
			throw new RuntimeExceptionIsNull("graphics"); //$NON-NLS-1$
		}
		
		final FontMetrics fm = graphics.getFontMetrics(graphics.getFont());
		final Rectangle2D rect = fm.getStringBounds(text, graphics);
		
		return new Dimension((int)rect.getWidth(), (int)rect.getHeight()); 
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
		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		return Arrays.asList(ge.getAllFonts());
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
		if (null == color) {
			throw new RuntimeExceptionIsNull("color"); //$NON-NLS-1$
		}
		
    	return Integer.toHexString(color.getRGB() & 0x00ffffff); 
	}
}
