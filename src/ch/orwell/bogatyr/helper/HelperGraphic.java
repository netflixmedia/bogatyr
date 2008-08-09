/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

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

import ch.orwell.bogatyr.helper.logger.Logger;



/**
 * This is a helper class for graphic operations
 * 
 * @author Stefan Laubenberger
 * @version 20080809
 */
public abstract class HelperGraphic {
	
	/**
     * Enable antialiasing on a graphic container
     * 
     * @param g Graphics2D container
     */	
	public static void enableAntialiasing(Graphics2D g) {
		Logger.getInstance().writeMethodEntry(HelperGraphic.class, "enableAntialiasing", g);  //$NON-NLS-1$

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Logger.getInstance().writeMethodExit(HelperGraphic.class, "enableAntialiasing");  //$NON-NLS-1$
	}

    /**
     * Calculates center of a dimension
     *
     * @param size Dimension
     * @return Dimension with center coordinates
     */
    public static Dimension getCenter(Dimension size) {
		Logger.getInstance().writeMethodEntry(HelperGraphic.class, "getCenter", size);  //$NON-NLS-1$

		Dimension dim = new Dimension(size.width / 2, size.height / 2);
		
		Logger.getInstance().writeMethodExit(HelperGraphic.class, "getCenter", dim);  //$NON-NLS-1$
		return dim;
	}

    /**
     * Get size of a text in a graphic container
     *
     * @param text Text
     * @param g Graphic container
     * @return Dimension with text size
     */
	public static Dimension getTextSize(String text, Graphics g) {
		Logger.getInstance().writeMethodEntry(HelperGraphic.class, "getTextSize", new Object[]{text, g});  //$NON-NLS-1$

		FontMetrics fm = g.getFontMetrics(g.getFont());
		Rectangle2D rect = fm.getStringBounds(text, g);

		Dimension dim = new Dimension((int)rect.getWidth(), (int)rect.getHeight()); 
		
		Logger.getInstance().writeMethodExit(HelperGraphic.class, "getTextSize", dim);  //$NON-NLS-1$
		return dim;
	}
	
//	public static List<String> getFonts() {
//	    // Get all available font family names from GraphicsEnvironment
//	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    return Arrays.asList(ge.getAvailableFontFamilyNames());
//	}

    /**
     * Returns all system fonts
     *
     * @return List containing all fonts
     */
    public static List<Font> getFonts() {
		Logger.getInstance().writeMethodEntry(HelperGraphic.class, "getFonts");  //$NON-NLS-1$

		// Get all available fonts from GraphicsEnvironment
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		List<Font> result = Arrays.asList(ge.getAllFonts());
		
		Logger.getInstance().writeMethodExit(HelperGraphic.class, "getFonts", result);  //$NON-NLS-1$
		return result;
	}
}
