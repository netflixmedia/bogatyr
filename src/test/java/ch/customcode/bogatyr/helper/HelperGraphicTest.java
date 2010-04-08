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

import ch.customcode.bogatyr.helper.HelperGraphic;
import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100408
 */
public class HelperGraphicTest {
	@Test
	public void testGetScale() {
//		System.out.println(HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(0, 20))));
//		System.out.println(HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(0, 40))));
//		System.out.println(HelperGraphic.getScaledSize(new Dimension(100, 100), 0.4));
		assertEquals(0.5D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(50, 50))), 0.001D);
		
//		try {
//			HelperGraphic.getCenter(null);
//			fail("size is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}

	@Test
	public void testGetCenter() {
		assertEquals(new Dimension(50, 50), HelperGraphic.getCenter(new Dimension(100, 100)));
		assertEquals(new Dimension(-50, -50), HelperGraphic.getCenter(new Dimension(-100, -100)));
		
//		try {
//			HelperGraphic.getCenter(null);
//			fail("size is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
	
	@Test
	public void testGetFonts() {
		assertNotNull(HelperGraphic.getAvailableFonts());
//		System.out.println(HelperGeneral.dumpList(HelperGraphic.getFonts()));
	}
	
	@Test
	public void testGetColorHex() {
		assertNotNull(HelperGraphic.getColorHex(Color.RED));
		
		try {
			HelperGraphic.getColorHex(null);
			fail("color is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

//		System.out.println(HelperGraphic.getColorHex(Color.RED));
	}
}


