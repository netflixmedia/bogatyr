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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.laubenberger.bogatyr.HelperResource;
import net.laubenberger.bogatyr.view.swing.Button;

import org.junit.Test;

/**
 * JUnit test for {@link HelperImage}
 * 
 * @author Stefan Laubenberger
 * @version 20101106
 */
public class HelperImageTest {
	@Test
	public void testReadImage() {
		try {
			assertEquals(32, HelperImage.readImage(getClass().getResourceAsStream(HelperResource.RES_FILE_PNG)).getWidth());
		} catch (IOException ex) {
			fail(ex.getMessage());
		}

		try {
			HelperImage.readImage((File) null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperImage.readImage((InputStream) null);
			fail("is is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testWriteImage() {
		File file = null;
		try {
			file = HelperIO.getTemporaryFile(getClass().getSimpleName(), HelperImage.TYPE_JPG);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}

		final Component component = new Button("Hello world", HelperString.EMPTY_STRING); //$NON-NLS-1$ 
		component.setBackground(Color.YELLOW);
		component.setForeground(Color.BLACK);
		component.setFont(new Font("Arial", Font.PLAIN, 16)); //$NON-NLS-1$
		component.setSize(new Dimension(100, 100));
		final BufferedImage image = HelperImage.getImage(component);
		
		try {
			HelperImage.writeImage(file, HelperImage.TYPE_JPG, image);
			assertEquals(100, HelperImage.readImage(file).getWidth());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperImage	.writeImage((File)null, HelperImage.TYPE_JPG, image);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperImage	.writeImage((OutputStream)null, HelperImage.TYPE_JPG, image);
			fail("os is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperImage	.writeImage(file, null, image);
			fail("type is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperImage	.writeImage(file, HelperString.EMPTY_STRING, image);
			fail("type is invalid"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperImage	.writeImage(file, HelperString.EMPTY_STRING, null);
			fail("image is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetScaledImage() {
		BufferedImage image = null;
		try {
			image = HelperImage.readImage(getClass().getResourceAsStream(HelperResource.RES_FILE_PNG));
		} catch (IOException ex) {
			fail(ex.getMessage());
		}
		
		assertEquals(64, HelperImage.getScaledImage(image, 2.0D).getWidth());
		assertEquals(32, HelperImage.getScaledImage(image, 0.0D).getWidth());

		assertEquals(40, HelperImage.getScaledImage(image, new Dimension(40, 30)).getWidth());
		assertEquals(30, HelperImage.getScaledImage(image, new Dimension(0, 30)).getWidth());
		assertEquals(40, HelperImage.getScaledImage(image, new Dimension(40, 0)).getWidth());
		
		try {
			HelperImage.getScaledImage(null, 2.0D);
			fail("image is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperImage.getScaledImage(null, new Dimension(40, 30));
			fail("image is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperImage.getScaledImage(image, -2.0D);
			fail("scale is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperImage.getScaledImage(image, null);
			fail("size is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}	
	}
	
	@Test
	public void testGetImageReadFormats() {
		assertTrue(HelperImage.getAvailableImageReadFormats().contains(HelperImage.TYPE_PNG));
		assertTrue(HelperImage.getAvailableImageReadFormats().contains(HelperImage.TYPE_GIF));
		assertTrue(HelperImage.getAvailableImageReadFormats().contains(HelperImage.TYPE_JPG));
		assertTrue(HelperImage.getAvailableImageReadFormats().contains(HelperImage.TYPE_BMP));
	}

	@Test
	public void testGetImageWriteFormats() {
		assertTrue(HelperImage.getAvailableImageWriteFormats().contains(HelperImage.TYPE_PNG));
		assertTrue(HelperImage.getAvailableImageWriteFormats().contains(HelperImage.TYPE_GIF));
		assertTrue(HelperImage.getAvailableImageWriteFormats().contains(HelperImage.TYPE_JPG));
		assertTrue(HelperImage.getAvailableImageWriteFormats().contains(HelperImage.TYPE_BMP));
	}

	@Test
	public void testGetImageReadMIMETypes() {
		assertTrue(HelperImage.getAvailableImageReadMIMETypes().contains("image/png")); //$NON-NLS-1$
		assertTrue(HelperImage.getAvailableImageReadMIMETypes().contains("image/jpeg")); //$NON-NLS-1$
	}

	@Test
	public void testGetImageWriteMIMETypes() {
		assertTrue(HelperImage.getAvailableImageWriteMIMETypes().contains("image/png")); //$NON-NLS-1$
		assertTrue(HelperImage.getAvailableImageWriteMIMETypes().contains("image/jpeg")); //$NON-NLS-1$
	}

	@Test
	public void testGetHQRenderingHints() {
		final RenderingHints rh = HelperImage.getHQRenderingHints();
		
		assertTrue(rh.get(RenderingHints.KEY_ANTIALIASING).equals(RenderingHints.VALUE_ANTIALIAS_ON));
		assertTrue(rh.get(RenderingHints.KEY_TEXT_ANTIALIASING).equals(RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		assertTrue(rh.get(RenderingHints.KEY_INTERPOLATION).equals(RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		assertTrue(rh.get(RenderingHints.KEY_ALPHA_INTERPOLATION).equals(RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
		assertTrue(rh.get(RenderingHints.KEY_RENDERING).equals(RenderingHints.VALUE_RENDER_QUALITY));
		assertTrue(rh.get(RenderingHints.KEY_COLOR_RENDERING).equals(RenderingHints.VALUE_COLOR_RENDER_QUALITY));
	}

	@Test
	public void testGetHQRenderingHintsForUpscale() {
		final RenderingHints rh = HelperImage.getHQRenderingHintsForUpscale();
		
		assertTrue(rh.get(RenderingHints.KEY_ANTIALIASING).equals(RenderingHints.VALUE_ANTIALIAS_ON));
		assertTrue(rh.get(RenderingHints.KEY_TEXT_ANTIALIASING).equals(RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		assertTrue(rh.get(RenderingHints.KEY_INTERPOLATION).equals(RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		assertTrue(rh.get(RenderingHints.KEY_ALPHA_INTERPOLATION).equals(RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
		assertTrue(rh.get(RenderingHints.KEY_RENDERING).equals(RenderingHints.VALUE_RENDER_QUALITY));
		assertTrue(rh.get(RenderingHints.KEY_COLOR_RENDERING).equals(RenderingHints.VALUE_COLOR_RENDER_QUALITY));
	}
	
	@Test
	public void testGetHQRenderingHintsForDownscale() {
		final RenderingHints rh = HelperImage.getHQRenderingHintsForDownscale();
		
		assertTrue(rh.get(RenderingHints.KEY_ANTIALIASING).equals(RenderingHints.VALUE_ANTIALIAS_ON));
		assertTrue(rh.get(RenderingHints.KEY_TEXT_ANTIALIASING).equals(RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		assertTrue(rh.get(RenderingHints.KEY_INTERPOLATION).equals(RenderingHints.VALUE_INTERPOLATION_BILINEAR));
		assertTrue(rh.get(RenderingHints.KEY_ALPHA_INTERPOLATION).equals(RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
		assertTrue(rh.get(RenderingHints.KEY_RENDERING).equals(RenderingHints.VALUE_RENDER_QUALITY));
		assertTrue(rh.get(RenderingHints.KEY_COLOR_RENDERING).equals(RenderingHints.VALUE_COLOR_RENDER_QUALITY));
	}
}
