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
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.unit.UnitWeight;
import ch.sisprocom.bogatyr.model.unit.Weight;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091202
 */
public class UnitWeightTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);

	@Test
	public void testConvert() {
		assertTrue(BigDecimal.ONE.compareTo(UnitWeight.convert(Weight.MILLIGRAM, Weight.GRAM, HelperNumber.BIGDECIMAL_1000)) == 0);
		assertTrue(HelperNumber.BIGDECIMAL_1000.compareTo(UnitWeight.convert(Weight.GRAM, Weight.MILLIGRAM, BigDecimal.ONE)) == 0);

		assertTrue(BigDecimal.ONE.compareTo(UnitWeight.convert(Weight.GRAM, Weight.KILOGRAM, HelperNumber.BIGDECIMAL_1000)) == 0);
		assertTrue(HelperNumber.BIGDECIMAL_1000.compareTo(UnitWeight.convert(Weight.KILOGRAM, Weight.GRAM, BigDecimal.ONE)) == 0);

		assertTrue(BigDecimal.ONE.compareTo(UnitWeight.convert(Weight.OUNCE, Weight.GRAM, new BigDecimal("28.34952"))) == 0); //$NON-NLS-1$
		assertTrue(new BigDecimal("28.34952").compareTo(UnitWeight.convert(Weight.GRAM, Weight.OUNCE, BigDecimal.ONE)) == 0); //$NON-NLS-1$

		assertTrue(BigDecimal.ONE.compareTo(UnitWeight.convert(Weight.POUND, Weight.KILOGRAM, new BigDecimal("0.453592"))) == 0); //$NON-NLS-1$
		assertTrue(new BigDecimal("0.453592").compareTo(UnitWeight.convert(Weight.KILOGRAM, Weight.POUND, BigDecimal.ONE)) == 0); //$NON-NLS-1$

		assertTrue(new BigDecimal("907.1847").compareTo(UnitWeight.convert(Weight.TON, Weight.KILOGRAM, BigDecimal.ONE)) == 0); //$NON-NLS-1$
		assertTrue(BigDecimal.ONE.compareTo(UnitWeight.convert(Weight.KILOGRAM, Weight.TON, new BigDecimal("907.1847")).round(MC)) == 0); //$NON-NLS-1$

		try {
			UnitWeight.convert(null, null, BigDecimal.ONE);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitWeight.convert(Weight.GRAM, null, BigDecimal.ONE);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitWeight.convert(Weight.GRAM, Weight.KILOGRAM, null);
			fail("value is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}	
	}
}
