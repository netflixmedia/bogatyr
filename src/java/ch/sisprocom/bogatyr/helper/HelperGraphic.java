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


/**
 * This is a helper class for graphic operations
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091101)
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
			throw new IllegalArgumentException("graphics is null!"); //$NON-NLS-1$
		}
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

    /**
     * Calculates the center of a {@link Dimension}.
     *
     * @param size {@link Dimension} for the calculation
     * @return {@link Dimension} with center coordinates
     * @see Dimension
     * @since 0.4.0
     */
    public static Dimension getCenter(final Dimension size) { //$JUnit$
		if (null == size) {
			throw new IllegalArgumentException("size is null!"); //$NON-NLS-1$
		}

    	return new Dimension(size.width / 2, size.height / 2);
	}

    /**
     * Calculates the scale to fit an input {@link Dimension} to an output {@link Dimension}.
     *
     * @param input {@link Dimension} for the calculation
     * @param output {@link Dimension} for the calculation
     * @return scale to fit the input dimension
     * @see Dimension
     * @since 0.9.0
     */
    public static double getScale(final Dimension input, final Dimension output) { //$JUnit$
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}
		if (null == output) {
			throw new IllegalArgumentException("output is null!"); //$NON-NLS-1$
		}
		
		final double scaleHeight =  input.getHeight() / output.getHeight();
		final double scaleWidth =  input.getWidth() / output.getWidth();
		double scale;

        scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
		
    	return scale;
	}
    
    /**
     * Calculates the scaled size to fit an input {@link Dimension} to an output {@link Dimension}.
     *
     * @param input {@link Dimension} for the calculation
     * @param output {@link Dimension} for the calculation
     * @return scaled {@link Dimension} to fit the input {@link Dimension}
     * @see Dimension
     * @since 0.9.0
     */
    public static Dimension getScaledSize(final Dimension input, final Dimension output) { //$JUnit$
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}
		if (null == output) {
			throw new IllegalArgumentException("output is null!"); //$NON-NLS-1$
		}
		
		final double scaleHeight = input.getHeight() / output.getHeight();
		final double scaleWidth = input.getWidth() / output.getWidth();
		double scale;

        scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
		
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
			throw new IllegalArgumentException("text is null!"); //$NON-NLS-1$
		}
		if (null == graphics) {
			throw new IllegalArgumentException("graphics is null!"); //$NON-NLS-1$
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
			throw new IllegalArgumentException("color is null!"); //$NON-NLS-1$
		}
		
    	return Integer.toHexString(color.getRGB() & 0x00ffffff); 
	}
}
