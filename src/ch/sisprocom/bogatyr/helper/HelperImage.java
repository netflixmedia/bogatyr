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
package ch.sisprocom.bogatyr.helper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import ch.sisprocom.bogatyr.helper.logger.Logger;

/**
 * This is a helper class for image operations
 * 
 * @author Stefan Laubenberger
 * @version 20080829
 */
public abstract class HelperImage {
	public static final String TYPE_JPG = "jpg"; //$NON-NLS-1$
	public static final String TYPE_PNG = "png"; //$NON-NLS-1$
	public static final String TYPE_GIF = "gif"; //$NON-NLS-1$

    /**
     * Saves an image from a RenderImage to a file
     *
     * @param image RenderImage for the image
     * @param type Image type (e.g. "jpg")
     * @param output Filename
     * @throws java.io.IOException
     */
    public static void saveImage(final RenderedImage image, final String type, final File output) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "saveImage", new Object[]{image, type, output});  //$NON-NLS-1$

    	ImageIO.write(image, type, output);

		Logger.getInstance().writeMethodExit(HelperImage.class, "saveImage");  //$NON-NLS-1$
    }

    /**
     * Saves an image from a Component to a file
     *
     * @param component Component for the image
     * @param type Image type (e.g. "jpg")
     * @param output Filename
     * @throws java.io.IOException
     */
	public static void saveImage(final Component component, final String type, final File output) throws IOException {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "saveImage", new Object[]{component, type, output});  //$NON-NLS-1$

		final Dimension size = component.getSize();
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2 = image.createGraphics();
		
		component.paint(g2);
       
		saveImage(image, type, output);

		Logger.getInstance().writeMethodExit(HelperImage.class, "saveImage");  //$NON-NLS-1$
	} 
	
	/**
	 * Returns list of unique supported read formats (e.g. "png", "jpg")
	 * @return list of unique supported read formats
	 */
	public static List<String> getImageReadFormats() {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "getImageReadFormats");  //$NON-NLS-1$

	    String[] formatNames = ImageIO.getReaderFormatNames();
	    List<String> list = unique(formatNames);
	    
		Logger.getInstance().writeMethodExit(HelperImage.class, "getImageReadFormats", list);  //$NON-NLS-1$
	    return list;
	}

	/**
	 * Returns list of unique supported write formats (e.g. "png", "jpg")
	 * @return list of unique supported write formats
	 */
	public static List<String> getImageWriteFormats() {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "getImageWriteFormats");  //$NON-NLS-1$

	    String[] formatNames = ImageIO.getWriterFormatNames();
	    List<String> list = unique(formatNames);

	    Logger.getInstance().writeMethodExit(HelperImage.class, "getImageWriteFormats", list);  //$NON-NLS-1$
	    return list;
	}

	/**
	 * Returns list of unique MIME types that can be read (e.g. "image/png", "image/jpg")
	 * @return list of unique MIME types that can be read
	 */
	public static List<String> getImageReadMIMETypes() {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "getImageReadMIMETypes");  //$NON-NLS-1$

	    String[] formatNames = ImageIO.getReaderMIMETypes();
	    List<String> list = unique(formatNames);

	    Logger.getInstance().writeMethodExit(HelperImage.class, "getImageReadMIMETypes", list);  //$NON-NLS-1$
		return list;
	}

	/**
	 * Returns list of unique MIME types that can be written (e.g. "image/png", "image/jpg")
	 * @return list of unique MIME types that can be written
	 */
	public static List<String> getImageWriteMIMETypes() {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "getImageWriteMIMETypes");  //$NON-NLS-1$

	    String[] formatNames = ImageIO.getWriterMIMETypes();
	    List<String> list = unique(formatNames);

	    Logger.getInstance().writeMethodExit(HelperImage.class, "getImageWriteMIMETypes", list);  //$NON-NLS-1$
	    return list;
	}

	/**
	 * Converts all strings to lowercase and returns a list containing the unique values.
	 * 
	 * @param strings as array
	 * @return list containing the unique values
	 */
    private static List<String> unique(String[] strings) {
		Logger.getInstance().writeMethodEntry(HelperImage.class, "unique", strings);  //$NON-NLS-1$

        Set<String> set = new HashSet<String>();
        
        for (String str : strings) {
            set.add(str.toLowerCase());
        }

		Logger.getInstance().writeMethodExit(HelperImage.class, "unique", set);  //$NON-NLS-1$
        return new ArrayList<String>(set);
    }
}