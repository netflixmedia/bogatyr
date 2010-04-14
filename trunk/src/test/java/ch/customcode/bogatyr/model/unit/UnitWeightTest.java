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
import ch.customcode.bogatyr.model.unit.Weight;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100212
 */
public class UnitWeightTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);

	@Test
	public void testConvert() {
		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.MILLIGRAM.convertTo(Weight.GRAM, HelperNumber.NUMBER_1000)));
		assertTrue(0 == HelperNumber.NUMBER_1000.compareTo(Weight.GRAM.convertTo(Weight.MILLIGRAM, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.GRAM.convertTo(Weight.KILOGRAM, HelperNumber.NUMBER_1000)));
		assertTrue(0 == HelperNumber.NUMBER_1000.compareTo(Weight.KILOGRAM.convertTo(Weight.GRAM, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.OUNCE.convertTo(Weight.GRAM, new BigDecimal("28.34952")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("28.34952").compareTo(Weight.GRAM.convertTo(Weight.OUNCE, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.POUND.convertTo(Weight.KILOGRAM, new BigDecimal("0.453592")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.453592").compareTo(Weight.KILOGRAM.convertTo(Weight.POUND, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("907.1847").compareTo(Weight.TON.convertTo(Weight.KILOGRAM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.KILOGRAM.convertTo(Weight.TON, new BigDecimal("907.1847")).round(MC))); //$NON-NLS-1$
		
//		try {
//			Weight.GRAM.convertTo(null, BigDecimal.ONE);
//			fail("toUnit is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//		
//		try {
//			Weight.GRAM.convertTo(Weight.KILOGRAM, null);
//			fail("value is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}	
	}
}
