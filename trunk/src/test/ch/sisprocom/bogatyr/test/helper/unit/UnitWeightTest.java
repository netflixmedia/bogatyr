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

import ch.sisprocom.bogatyr.helper.unit.UnitWeight;
import ch.sisprocom.bogatyr.helper.unit.Weight;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090612
 */
public class UnitWeightTest {
	@Test
	public void testConvert() {
		assertEquals(1.0D, UnitWeight.convert(Weight.MILLIGRAM, Weight.GRAM, 1000.0D), 0.00001D);
		assertEquals(1000.0D, UnitWeight.convert(Weight.GRAM, Weight.MILLIGRAM, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitWeight.convert(Weight.GRAM, Weight.KILOGRAM, 1000.0D), 0.00001D);
		assertEquals(1000.0D, UnitWeight.convert(Weight.KILOGRAM, Weight.GRAM, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitWeight.convert(Weight.OUNCE, Weight.GRAM, 28.34952D), 0.00001D);
		assertEquals(28.34952D, UnitWeight.convert(Weight.GRAM, Weight.OUNCE, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitWeight.convert(Weight.POUND, Weight.KILOGRAM, 0.453592D), 0.00001D);
		assertEquals(0.453592D, UnitWeight.convert(Weight.KILOGRAM, Weight.POUND, 1.0D), 0.00001D);

		assertEquals(907.1847D, UnitWeight.convert(Weight.TON, Weight.KILOGRAM, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitWeight.convert(Weight.KILOGRAM, Weight.TON, 907.1847D), 0.00001D);
		
		try {
			UnitWeight.convert(null, null, 1.0D);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitWeight.convert(Weight.GRAM, null, 1.0D);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
