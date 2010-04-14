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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package ch.customcode.bogatyr.model.unit;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperNumber;
import ch.customcode.bogatyr.model.unit.Area;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100212
 */
public class UnitAreaTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);
	
	@Test
	public void testConvert() {
		assertTrue(0 == BigDecimal.ONE.compareTo(Area.MM2.convertTo(Area.CM2, HelperNumber.NUMBER_100)));
		assertTrue(0 == HelperNumber.NUMBER_100.compareTo(Area.CM2.convertTo(Area.MM2, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.CM2.convertTo(Area.M2, HelperNumber.NUMBER_10000)));
		assertTrue(0 == HelperNumber.NUMBER_10000.compareTo(Area.M2.convertTo(Area.CM2, BigDecimal.ONE)));
		
		assertTrue(0 == HelperNumber.NUMBER_100.compareTo(Area.AREA.convertTo(Area.M2, BigDecimal.ONE)));
		assertTrue(0 == BigDecimal.ONE.compareTo(Area.M2.convertTo(Area.AREA, HelperNumber.NUMBER_100)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.AREA.convertTo(Area.HECTARE, HelperNumber.NUMBER_100)));
		assertTrue(0 == HelperNumber.NUMBER_100.compareTo(Area.HECTARE.convertTo(Area.AREA, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.HECTARE.convertTo(Area.KM2, HelperNumber.NUMBER_100)));
		assertTrue(0 == HelperNumber.NUMBER_100.compareTo(Area.KM2.convertTo(Area.HECTARE, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.FOOT2.convertTo(Area.M2, new BigDecimal("0.09290304")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.09290304").compareTo(Area.M2.convertTo(Area.FOOT2, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.YARD2.convertTo(Area.M2, new BigDecimal("0.83612736")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.83612736").compareTo(Area.M2.convertTo(Area.YARD2, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.PERCH.convertTo(Area.M2, new BigDecimal("25.2928526")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("25.2928526").compareTo(Area.M2.convertTo(Area.PERCH, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.ACRE.convertTo(Area.M2, new BigDecimal("4046.8564224")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("4046.8564224").compareTo(Area.M2.convertTo(Area.ACRE, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Area.KM2.convertTo(Area.MILE2, new BigDecimal("2.5899881103")).round(MC))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("2.5899881103").compareTo(Area.MILE2.convertTo(Area.KM2, BigDecimal.ONE))); //$NON-NLS-1$
		
//		try {
//			Area.KM2.convertTo(null, BigDecimal.ONE);
//			fail("toUnit is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//		
//		try {
//			Area.KM2.convertTo(Area.M2, null);
//			fail("value is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
}
