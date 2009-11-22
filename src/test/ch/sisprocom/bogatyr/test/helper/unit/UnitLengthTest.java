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
package ch.sisprocom.bogatyr.test.helper.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.model.unit.Length;
import ch.sisprocom.bogatyr.helper.unit.UnitLength;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090612
 */
public class UnitLengthTest {
	@Test
	public void testPixelToCm() {
		assertEquals(28.22222D, UnitLength.pixelToCm(800), 0.00001D);
	}
	
	@Test
	public void testCmToPixel() {
		assertEquals(800, UnitLength.cmToPixel(28.22222D));
	}
	
	@Test
	public void testConvert() {
		assertEquals(0.1D, UnitLength.convert(Length.MM, Length.CM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.CM, Length.MM, 0.1D), 0.00001D);
		
		assertEquals(10.0D, UnitLength.convert(Length.CM, Length.MM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.MM, Length.CM, 10.0D), 0.00001D);

		assertEquals(0.01D, UnitLength.convert(Length.CM, Length.M, 1.0D), 0.00001D);
		assertEquals(100.0D, UnitLength.convert(Length.M, Length.CM, 1.0D), 0.00001D);

		assertEquals(100000.0D, UnitLength.convert(Length.KM, Length.CM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.CM, Length.KM, 100000.0D), 0.00001D);
		
		assertEquals(2.54D, UnitLength.convert(Length.INCH, Length.CM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.CM, Length.INCH, 2.54D), 0.00001D);
		
		assertEquals(30.48D, UnitLength.convert(Length.FOOT, Length.CM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.CM, Length.FOOT, 30.48D), 0.00001D);
		
		assertEquals(91.44D, UnitLength.convert(Length.YARD, Length.CM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.CM, Length.YARD, 91.44D), 0.00001D);
		
		assertEquals(1.609344D, UnitLength.convert(Length.MILE, Length.KM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitLength.convert(Length.KM, Length.MILE, 1.609344D), 0.00001D);

		try {
			UnitLength.convert(null, null, 1.0D);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitLength.convert(Length.KM, null, 1.0D);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
