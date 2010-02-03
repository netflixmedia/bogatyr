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
package ch.sisprocom.bogatyr.test.helper;

import ch.sisprocom.bogatyr.helper.HelperGraphic;
import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090527
 */
public class HelperGraphicTest {
	@Test
	public void testGetCenter() {
		assertEquals(new Dimension(50, 50), HelperGraphic.getCenter(new Dimension(100, 100)));
		assertEquals(new Dimension(-50, -50), HelperGraphic.getCenter(new Dimension(-100, -100)));
		
		try {
			HelperGraphic.getCenter(null);
			fail("size is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
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


