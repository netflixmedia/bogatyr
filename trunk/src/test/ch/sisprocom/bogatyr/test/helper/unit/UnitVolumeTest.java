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

import ch.sisprocom.bogatyr.helper.unit.UnitVolume;
import ch.sisprocom.bogatyr.helper.unit.Volume;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090612
 */
public class UnitVolumeTest {
	@Test
	public void testConvert() {
		assertEquals(1.0D, UnitVolume.convert(Volume.MM3, Volume.CM3, 1000.0D), 0.00001D);
		assertEquals(1000.0D, UnitVolume.convert(Volume.CM3, Volume.MM3, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitVolume.convert(Volume.CM3, Volume.L, 1000.0D), 0.00001D);
		assertEquals(1000.0D, UnitVolume.convert(Volume.L, Volume.CM3, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitVolume.convert(Volume.L, Volume.M3, 1000.0D), 0.00001D);
		assertEquals(1000.0D, UnitVolume.convert(Volume.M3, Volume.L, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitVolume.convert(Volume.PINT, Volume.CM3, 473.176473D), 0.00001D);
		assertEquals(473.176473D, UnitVolume.convert(Volume.CM3, Volume.PINT, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitVolume.convert(Volume.QUART, Volume.L, 0.946326D), 0.00001D);
		assertEquals(0.946326D, UnitVolume.convert(Volume.L, Volume.QUART, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitVolume.convert(Volume.GALLON_US, Volume.L, 3.785411784D), 0.00001D);
		assertEquals(3.785411784D, UnitVolume.convert(Volume.L, Volume.GALLON_US, 1.0D), 0.00001D);

		assertEquals(1.0D, UnitVolume.convert(Volume.BARREL, Volume.L, 158.987294928D), 0.00001D);
		assertEquals(158.987294928D, UnitVolume.convert(Volume.L, Volume.BARREL, 1.0D), 0.00001D);
		
		try {
			UnitVolume.convert(null, null, 1.0D);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitVolume.convert(Volume.M3, null, 1.0D);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
