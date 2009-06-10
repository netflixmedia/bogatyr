/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.unit.UnitArea;
import ch.sisprocom.bogatyr.helper.unit.UnitArea.Area;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090610
 */
public class UnitAreaTest {
	@Test
	public void testConvert() {
		assertEquals(1.0D, UnitArea.convert(Area.MM2, Area.CM2, 100.0D), 0.00001D);
		assertEquals(100.0D, UnitArea.convert(Area.CM2, Area.MM2, 1.0D), 0.00001D);
		
		assertEquals(1.0D, UnitArea.convert(Area.CM2, Area.M2, 10000.0D), 0.00001D);
		assertEquals(10000.0D, UnitArea.convert(Area.M2, Area.CM2, 1.0D), 0.00001D);

		assertEquals(100.0D, UnitArea.convert(Area.AREA, Area.M2, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitArea.convert(Area.M2, Area.AREA, 100.0D), 0.00001D);

		assertEquals(1.0D, UnitArea.convert(Area.AREA, Area.HECTARE, 100.0D), 0.00001D);
		assertEquals(100.0D, UnitArea.convert(Area.HECTARE, Area.AREA, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitArea.convert(Area.HECTARE, Area.KM2, 100.0D), 0.00001D);
		assertEquals(100.0D, UnitArea.convert(Area.KM2, Area.HECTARE, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitArea.convert(Area.FOOT2, Area.M2, 0.09290304D), 0.00001D);
		assertEquals(0.09290304D, UnitArea.convert(Area.M2, Area.FOOT2, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitArea.convert(Area.YARD2, Area.M2, 0.83612736D), 0.00001D);
		assertEquals(0.83612736D, UnitArea.convert(Area.M2, Area.YARD2, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitArea.convert(Area.PERCH, Area.M2, 25.2928526D), 0.00001D);
		assertEquals(25.2928526D, UnitArea.convert(Area.M2, Area.PERCH, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitArea.convert(Area.ACRE, Area.M2, 4046.8564224D), 0.00001D);
		assertEquals(4046.8564224D, UnitArea.convert(Area.M2, Area.ACRE, 1.0D), 0.00001D);

		assertEquals(2.5899881103D, UnitArea.convert(Area.MILE2, Area.KM2, 1.0D), 0.00001D);
		assertEquals(1.0D, UnitArea.convert(Area.KM2, Area.MILE2, 2.5899881103D), 0.00001D);
		
		try {
			UnitArea.convert(null, null, 1.0D);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitArea.convert(Area.KM2, null, 1.0D);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
