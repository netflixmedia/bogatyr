/*******************************************************************************
§ * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
import ch.sisprocom.bogatyr.helper.unit.UnitLength;
import ch.sisprocom.bogatyr.model.unit.Length;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091210
 */
public class UnitLengthTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);
	
	@Test
	public void testPixelToCm() {
		assertTrue(0 == new BigDecimal("28.222").compareTo(UnitLength.pixelToCm(new BigDecimal("800")).round(MC))); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	@Test
	public void testCmToPixel() {
		assertTrue(0 == new BigDecimal("799.99").compareTo(UnitLength.cmToPixel(new BigDecimal("28.222")).round(MC))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void testPixelToInch() {
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.pixelToInch(new BigDecimal("72")))); //$NON-NLS-1$
	}
	
	@Test
	public void testInchToPixel() {
		assertTrue(0 == new BigDecimal("72").compareTo(UnitLength.inchToPixel(BigDecimal.ONE))); //$NON-NLS-1$
	}
	
	@Test
	public void testConvert() {
		assertTrue(0 == new BigDecimal("0.1").compareTo(UnitLength.convert(Length.MM, Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.CM, Length.MM, new BigDecimal("0.1")))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.TEN.compareTo(UnitLength.convert(Length.CM, Length.MM, BigDecimal.ONE)));
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.MM, Length.CM, BigDecimal.TEN)));

		assertTrue(0 == new BigDecimal("0.01").compareTo(UnitLength.convert(Length.CM, Length.M, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == HelperNumber.BIGDECIMAL_100.compareTo(UnitLength.convert(Length.M, Length.CM, BigDecimal.ONE)));

		assertTrue(0 == HelperNumber.BIGDECIMAL_100000.compareTo(UnitLength.convert(Length.KM, Length.CM, BigDecimal.ONE)));
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.CM, Length.KM, HelperNumber.BIGDECIMAL_100000)));

		assertTrue(0 == new BigDecimal("2.54").compareTo(UnitLength.convert(Length.INCH, Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.CM, Length.INCH, new BigDecimal("2.54")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("30.48").compareTo(UnitLength.convert(Length.FOOT, Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.CM, Length.FOOT, new BigDecimal("30.48")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("91.44").compareTo(UnitLength.convert(Length.YARD, Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.CM, Length.YARD, new BigDecimal("91.44")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("1.609344").compareTo(UnitLength.convert(Length.MILE, Length.KM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.KM, Length.MILE, new BigDecimal("1.609344")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("1.852").compareTo(UnitLength.convert(Length.NAUTICAL_MILE, Length.KM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.convert(Length.KM, Length.NAUTICAL_MILE, new BigDecimal("1.852")).round(MC))); //$NON-NLS-1$

		try {
			UnitLength.convert(null, null, BigDecimal.ONE);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitLength.convert(Length.KM, null, BigDecimal.ONE);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitLength.convert(Length.KM, Length.M, null);
			fail("value is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
