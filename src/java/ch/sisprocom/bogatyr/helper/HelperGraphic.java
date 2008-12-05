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
import java.util.List;


/**
 * This is a helper class for graphic operations
 * 
 * @author Stefan Laubenberger
 * @version 20081205
 */
public abstract class HelperGraphic { //TODO document in Wiki!
	
	/**
     * Enable antialiasing on a graphic container.
     * 
     * @param g Graphics2D container
     */	
	public static void enableAntialiasing(final Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

    /**
     * Calculates center of a dimension.
     *
     * @param size Dimension
     * @return Dimension with center coordinates
     */
    public static Dimension getCenter(final Dimension size) {
    	return new Dimension(size.width / 2, size.height / 2);
	}

    /**
     * Get size of a text in a graphic container.
     *
     * @param text Text
     * @param g Graphic container
     * @return Dimension with text size
     */
	public static Dimension getTextSize(final String text, final Graphics g) {
		final FontMetrics fm = g.getFontMetrics(g.getFont());
		final Rectangle2D rect = fm.getStringBounds(text, g);
		
		return new Dimension((int)rect.getWidth(), (int)rect.getHeight()); 
	}
	
//	public static List<String> getFonts() {
//	    // Get all available font family names from GraphicsEnvironment
//	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    return Arrays.asList(ge.getAvailableFontFamilyNames());
//	}

    /**
     * Returns all system fonts.
     *
     * @return List containing all fonts
     */
    public static List<Font> getFonts() {
		// Get all available fonts from GraphicsEnvironment
		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		return Arrays.asList(ge.getAllFonts());
	}
    
    /**
     * Returns the hex value of a color (RGB).
     * Used e.g. for HTML.
     *
     * @param color for the hex value
     * @return hex value of the color
     */
    public static String getColorHex(final Color color) {
    	return Integer.toHexString(color.getRGB() & 0x00ffffff); 
	}
}
