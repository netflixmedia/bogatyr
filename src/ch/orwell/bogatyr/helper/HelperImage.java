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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.orwell.bogatyr.helper.logger.Logger;

/**
 * This is a helper class for image operations
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public abstract class HelperImage {
	public static final String TYPE_JPG = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF = "gif"; //$NON-NLS-1$

    /**
     * Create an image from a RenderImage
     *
     * @param image RenderImage for the image
     * @param type Image type (e.g. "jpg")
     * @param output Filename
     * @throws java.io.IOException
     */
    public static void createImage(final RenderedImage image, final String type, final File output) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "createImage", new Object[]{image, type, output});  //$NON-NLS-1$

    	ImageIO.write(image, type, output);

		Logger.getInstance().writeMethodExit(HelperImage.class, "createImage");  //$NON-NLS-1$
    }

    /**
     * Create an image from a Component
     *
     * @param component Component for the image
     * @param type Image type (e.g. "jpg")
     * @param output Filename
     * @throws java.io.IOException
     */
	public static void createImage(final Component component, final String type, final File output) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "createImage", new Object[]{component, type, output});  //$NON-NLS-1$

		final Dimension size = component.getSize();
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2 = image.createGraphics();
		
		component.paint(g2);
       
		createImage(image, type, output);

		Logger.getInstance().writeMethodExit(HelperImage.class, "createImage");  //$NON-NLS-1$
	}  
}