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
import ch.sisprocom.bogatyr.model.unit.Volume;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100112
 */
public class UnitVolumeTest {
	@Test
	public void testConvert() {
		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.MM3.convertTo(Volume.CM3, HelperNumber.BIGDECIMAL_1000)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_1000.compareTo(Volume.CM3.convertTo(Volume.MM3, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.CM3.convertTo(Volume.L, HelperNumber.BIGDECIMAL_1000)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_1000.compareTo(Volume.L.convertTo(Volume.CM3, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.L.convertTo(Volume.M3, HelperNumber.BIGDECIMAL_1000)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_1000.compareTo(Volume.M3.convertTo(Volume.L, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.PINT.convertTo(Volume.CM3, new BigDecimal("473.176473")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("473.176473").compareTo(Volume.CM3.convertTo(Volume.PINT, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.QUART.convertTo(Volume.L, new BigDecimal("0.946326")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.946326").compareTo(Volume.L.convertTo(Volume.QUART, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.GALLON_US.convertTo(Volume.L, new BigDecimal("3.785411784")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("3.785411784").compareTo(Volume.L.convertTo(Volume.GALLON_US, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Volume.BARREL.convertTo(Volume.L, new BigDecimal("158.987294928")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("158.987294928").compareTo(Volume.L.convertTo(Volume.BARREL, BigDecimal.ONE))); //$NON-NLS-1$
		
		try {
			Volume.M3.convertTo(null, BigDecimal.ONE);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			Volume.M3.convertTo(Volume.CM3, null);
			fail("value is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}	
	}
}
