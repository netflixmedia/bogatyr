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
package ch.sisprocom.bogatyr.test.helper.unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.model.unit.Weight;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100112
 */
public class UnitWeightTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);

	@Test
	public void testConvert() {
		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.MILLIGRAM.convertTo(Weight.GRAM, HelperNumber.BIGDECIMAL_1000)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_1000.compareTo(Weight.GRAM.convertTo(Weight.MILLIGRAM, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.GRAM.convertTo(Weight.KILOGRAM, HelperNumber.BIGDECIMAL_1000)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_1000.compareTo(Weight.KILOGRAM.convertTo(Weight.GRAM, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.OUNCE.convertTo(Weight.GRAM, new BigDecimal("28.34952")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("28.34952").compareTo(Weight.GRAM.convertTo(Weight.OUNCE, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.POUND.convertTo(Weight.KILOGRAM, new BigDecimal("0.453592")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.453592").compareTo(Weight.KILOGRAM.convertTo(Weight.POUND, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("907.1847").compareTo(Weight.TON.convertTo(Weight.KILOGRAM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Weight.KILOGRAM.convertTo(Weight.TON, new BigDecimal("907.1847")).round(MC))); //$NON-NLS-1$
		
		try {
			Weight.GRAM.convertTo(null, BigDecimal.ONE);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			Weight.GRAM.convertTo(Weight.KILOGRAM, null);
			fail("value is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}	
	}
}
