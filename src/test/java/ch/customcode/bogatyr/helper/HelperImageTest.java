/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperImage;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.view.swing.Button;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100215
 */
public class HelperImageTest {
	@Test
	public void testWriteImage() {
		final Component component = new Button("Hello world", HelperString.EMPTY_STRING); //$NON-NLS-1$ 
		component.setBackground(Color.YELLOW);
		component.setForeground(Color.BLACK);
		component.setFont(new Font("Arial", Font.PLAIN, 16)); //$NON-NLS-1$
		component.setSize(new Dimension(100, 100));

		try {
			HelperImage.writeImage(HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), HelperImage.TYPE_JPG), HelperImage.TYPE_JPG, HelperImage.getImage(component)); //$NON-NLS-1$
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
		
		try {
			HelperImage.writeImage(HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), HelperImage.TYPE_JPG), "blabla", HelperImage.getImage(component)); //$NON-NLS-1$ //$NON-NLS-2$
			fail("type is invalid!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

//		try {
//			HelperImage.writeImage(null, HelperImage.TYPE_JPG, HelperImage.getImage(component));
//			fail("file is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
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
}