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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.unit.UnitVolume;
import ch.sisprocom.bogatyr.model.unit.Volume;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091202
 */
public class UnitVolumeTest {
	@Test
	public void testConvert() {
		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.MM3, Volume.CM3, HelperNumber.BIGDECIMAL_1000)) == 0);
		assertTrue(HelperNumber.BIGDECIMAL_1000.compareTo(UnitVolume.convert(Volume.CM3, Volume.MM3, BigDecimal.ONE)) == 0);

		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.CM3, Volume.L, HelperNumber.BIGDECIMAL_1000)) == 0);
		assertTrue(HelperNumber.BIGDECIMAL_1000.compareTo(UnitVolume.convert(Volume.L, Volume.CM3, BigDecimal.ONE)) == 0);

		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.L, Volume.M3, HelperNumber.BIGDECIMAL_1000)) == 0);
		assertTrue(HelperNumber.BIGDECIMAL_1000.compareTo(UnitVolume.convert(Volume.M3, Volume.L, BigDecimal.ONE)) == 0);

		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.PINT, Volume.CM3, new BigDecimal("473.176473"))) == 0); //$NON-NLS-1$
		assertTrue(new BigDecimal("473.176473").compareTo(UnitVolume.convert(Volume.CM3, Volume.PINT, BigDecimal.ONE)) == 0); //$NON-NLS-1$

		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.QUART, Volume.L, new BigDecimal("0.946326"))) == 0); //$NON-NLS-1$
		assertTrue(new BigDecimal("0.946326").compareTo(UnitVolume.convert(Volume.L, Volume.QUART, BigDecimal.ONE)) == 0); //$NON-NLS-1$

		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.GALLON_US, Volume.L, new BigDecimal("3.785411784"))) == 0); //$NON-NLS-1$
		assertTrue(new BigDecimal("3.785411784").compareTo(UnitVolume.convert(Volume.L, Volume.GALLON_US, BigDecimal.ONE)) == 0); //$NON-NLS-1$

		assertTrue(BigDecimal.ONE.compareTo(UnitVolume.convert(Volume.BARREL, Volume.L, new BigDecimal("158.987294928"))) == 0); //$NON-NLS-1$
		assertTrue(new BigDecimal("158.987294928").compareTo(UnitVolume.convert(Volume.L, Volume.BARREL, BigDecimal.ONE)) == 0); //$NON-NLS-1$
		
		try {
			UnitVolume.convert(null, null, BigDecimal.ONE);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitVolume.convert(Volume.M3, null, BigDecimal.ONE);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitVolume.convert(Volume.M3, Volume.CM3, null);
			fail("value is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}	
	}
}
