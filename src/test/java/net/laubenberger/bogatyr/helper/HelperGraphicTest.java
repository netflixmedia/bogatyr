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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.view.swing.Panel;

import org.junit.Test;


/**
 * JUnit test for {@link HelperGraphic}
 *
 * @author Stefan Laubenberger
 * @version 20101108
 */
public class HelperGraphicTest {
	@Test
	public void testGetCenter() {
		assertEquals(new Dimension(50, 50), HelperGraphic.getCenter(new Dimension(100, 100)));
		assertEquals(new Dimension(-50, -50), HelperGraphic.getCenter(new Dimension(-100, -100)));
		assertEquals(new Dimension(0, 0), HelperGraphic.getCenter(new Dimension(0, 0)));
		assertEquals(new Dimension(50, 0), HelperGraphic.getCenter(new Dimension(100, 0)));
		assertEquals(new Dimension(0, 50), HelperGraphic.getCenter(new Dimension(0, 100)));
		
		try {
			HelperGraphic.getCenter(null);
			fail("size is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetScale() {
		// standard cases
		assertEquals(0.5D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(50, 50))), 0.001D);
		assertEquals(0.5D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(50, 75))), 0.001D);
		assertEquals(0.25D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(50, 25))), 0.001D);
		assertEquals(0.5D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(75, 50))), 0.001D);
		assertEquals(0.25D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(25, 50))), 0.001D);

		// special cases with 0 (= flexible)
		assertEquals(0.5D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(0, 50))), 0.001D);
		assertEquals(0.5D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(50, 0))), 0.001D);
		assertEquals(1.0D, HelperGraphic.getScale(new Dimension(100, 100), (new Dimension(0, 0))), 0.001D);

		try {
			HelperGraphic.getScale(null, new Dimension(50, 50));
			fail("input is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperGraphic.getScale(new Dimension(100, 100), null);
			fail("output is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperGraphic.getScale(new Dimension(-100, 100), new Dimension(50, 50));
			fail("input.width is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperGraphic.getScale(new Dimension(100, -100), new Dimension(50, 50));
			fail("input.height is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperGraphic.getScale(new Dimension(100, 100), new Dimension(-50, 50));
			fail("output.width is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperGraphic.getScale(new Dimension(100, 100), new Dimension(50, -50));
			fail("output.height is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetScaledSizeWithDimension() {
		// standard cases
		assertEquals(new Dimension(50, 50), HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(50, 50))));
		assertEquals(new Dimension(50, 50), HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(70, 50))));
		assertEquals(new Dimension(50, 50), HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(50, 70))));

		// special cases with 0 (= flexible)
		assertEquals(new Dimension(50, 50), HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(0, 50))));
		assertEquals(new Dimension(50, 50), HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(50, 0))));
		assertEquals(new Dimension(100, 100), HelperGraphic.getScaledSize(new Dimension(100, 100), (new Dimension(0, 0))));

		try {
			HelperGraphic.getScaledSize(null, new Dimension(50, 50));
			fail("input is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperGraphic.getScaledSize(new Dimension(100, 100), null);
			fail("output is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperGraphic.getScale(new Dimension(-100, 100), new Dimension(50, 50));
			fail("input.width is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperGraphic.getScale(new Dimension(100, -100), new Dimension(50, 50));
			fail("input.height is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperGraphic.getScale(new Dimension(100, 100), new Dimension(-50, 50));
			fail("output.width is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperGraphic.getScale(new Dimension(100, 100), new Dimension(50, -50));
			fail("output.height is negative"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetScaledSizeWithScale() {
		assertEquals(new Dimension(50, 50), HelperGraphic.getScaledSize(new Dimension(100, 100), 0.5D));
		assertEquals(new Dimension(-50, -50), HelperGraphic.getScaledSize(new Dimension(100, 100), -0.5D));
		assertEquals(new Dimension(50, -50), HelperGraphic.getScaledSize(new Dimension(-100, 100), -0.5D));
		assertEquals(new Dimension(-50, 50), HelperGraphic.getScaledSize(new Dimension(100, -100), -0.5D));
		assertEquals(new Dimension(0, 0), HelperGraphic.getScaledSize(new Dimension(100, -100), 0.0D));

		try {
			HelperGraphic.getScaledSize(null, 0.5D);
			fail("input is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetTextSize() {
		final Component component = new Panel();
		component.setSize(new Dimension(100, 30));

		final BufferedImage result = new BufferedImage(component.getSize().width, component.getSize().height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = result.createGraphics();

		component.paint(g2d);

		assertEquals(new Dimension(47, 15), HelperGraphic.getTextSize(Constants.BOGATYR.getName(), g2d));
		assertEquals(new Dimension(0, 15), HelperGraphic.getTextSize(HelperString.EMPTY_STRING, g2d));
	
		try {
			HelperGraphic.getTextSize(null, g2d);
			fail("text is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperGraphic.getTextSize(Constants.BOGATYR.getName(), null);
			fail("graphics is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

	}
	
	@Test
	public void testGetAvailableFonts() {
//		System.err.println(HelperGraphic.getAvailableFonts().size());
		assertTrue(HelperGraphic.getAvailableFonts().size() > 50);
	}

	@Test
	public void testGetColorHex() {
		assertEquals("ff0000", HelperGraphic.getColorHex(Color.RED)); //$NON-NLS-1$
		assertEquals("ffff00", HelperGraphic.getColorHex(Color.YELLOW)); //$NON-NLS-1$

		try {
			HelperGraphic.getColorHex(null);
			fail("color is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}


