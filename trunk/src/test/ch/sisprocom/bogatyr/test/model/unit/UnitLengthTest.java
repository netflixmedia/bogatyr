/*******************************************************************************
§ * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.test.model.unit;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.model.unit.Length;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100212
 */
public class UnitLengthTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);
	
//	@Test
//	public void testPixelToCm() {
//		assertTrue(0 == new BigDecimal("28.222").compareTo(UnitLength.pixelToCm(new BigDecimal("800")).round(MC))); //$NON-NLS-1$ //$NON-NLS-2$
//	}
//	
//	@Test
//	public void testCmToPixel() {
//		assertTrue(0 == new BigDecimal("799.99").compareTo(UnitLength.cmToPixel(new BigDecimal("28.222")).round(MC))); //$NON-NLS-1$ //$NON-NLS-2$
//	}
//
//	@Test
//	public void testPixelToInch() {
//		assertTrue(0 == BigDecimal.ONE.compareTo(UnitLength.pixelToInch(new BigDecimal("72")))); //$NON-NLS-1$
//	}
//	
//	@Test
//	public void testInchToPixel() {
//		assertTrue(0 == new BigDecimal("72").compareTo(UnitLength.inchToPixel(BigDecimal.ONE))); //$NON-NLS-1$
//	}
	
	@Test
	public void testConvert() {
		assertTrue(0 == new BigDecimal("0.1").compareTo(Length.MM.convertTo(Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.CM.convertTo(Length.MM, new BigDecimal("0.1")))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.TEN.compareTo(Length.CM.convertTo(Length.MM, BigDecimal.ONE)));
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.MM.convertTo(Length.CM, BigDecimal.TEN)));

		assertTrue(0 == new BigDecimal("0.01").compareTo(Length.CM.convertTo(Length.M, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == HelperNumber.NUMBER_100.compareTo(Length.M.convertTo(Length.CM, BigDecimal.ONE)));

		assertTrue(0 == HelperNumber.NUMBER_100000.compareTo(Length.KM.convertTo(Length.CM, BigDecimal.ONE)));
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.CM.convertTo(Length.KM, HelperNumber.NUMBER_100000)));

		assertTrue(0 == new BigDecimal("2.54").compareTo(Length.INCH.convertTo(Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.CM.convertTo(Length.INCH, new BigDecimal("2.54")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("30.48").compareTo(Length.FOOT.convertTo(Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.CM.convertTo(Length.FOOT, new BigDecimal("30.48")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("91.44").compareTo(Length.YARD.convertTo(Length.CM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.CM.convertTo(Length.YARD, new BigDecimal("91.44")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("1.609344").compareTo(Length.MILE.convertTo(Length.KM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.KM.convertTo(Length.MILE, new BigDecimal("1.609344")).round(MC))); //$NON-NLS-1$

		assertTrue(0 == new BigDecimal("1.852").compareTo(Length.NAUTICAL_MILE.convertTo(Length.KM, BigDecimal.ONE))); //$NON-NLS-1$
		assertTrue(0 == BigDecimal.ONE.compareTo(Length.KM.convertTo(Length.NAUTICAL_MILE, new BigDecimal("1.852")).round(MC))); //$NON-NLS-1$
		
//		try {
//			Length.KM.convertTo(null, BigDecimal.ONE);
//			fail("toUnit is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//		
//		try {
//			Length.KM.convertTo(Length.M, null);
//			fail("value is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
}
