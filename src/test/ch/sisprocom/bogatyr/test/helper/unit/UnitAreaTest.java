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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.unit.UnitArea;
import ch.sisprocom.bogatyr.model.unit.Area;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091210
 */
public class UnitAreaTest {
	private static final MathContext MC = new MathContext(5, RoundingMode.HALF_EVEN);
	
	@Test
	public void testConvert() {
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.MM2, Area.CM2, HelperNumber.BIGDECIMAL_100)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_100.compareTo(UnitArea.convert(Area.CM2, Area.MM2, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.CM2, Area.M2, HelperNumber.BIGDECIMAL_10000)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_10000.compareTo(UnitArea.convert(Area.M2, Area.CM2, BigDecimal.ONE)));
		
		assertTrue(0 == HelperNumber.BIGDECIMAL_100.compareTo(UnitArea.convert(Area.AREA, Area.M2, BigDecimal.ONE)));
		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.M2, Area.AREA, HelperNumber.BIGDECIMAL_100)));

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.AREA, Area.HECTARE, HelperNumber.BIGDECIMAL_100)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_100.compareTo(UnitArea.convert(Area.HECTARE, Area.AREA, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.HECTARE, Area.KM2, HelperNumber.BIGDECIMAL_100)));
		assertTrue(0 == HelperNumber.BIGDECIMAL_100.compareTo(UnitArea.convert(Area.KM2, Area.HECTARE, BigDecimal.ONE)));

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.FOOT2, Area.M2, new BigDecimal("0.09290304")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.09290304").compareTo(UnitArea.convert(Area.M2, Area.FOOT2, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.YARD2, Area.M2, new BigDecimal("0.83612736")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("0.83612736").compareTo(UnitArea.convert(Area.M2, Area.YARD2, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.PERCH, Area.M2, new BigDecimal("25.2928526")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("25.2928526").compareTo(UnitArea.convert(Area.M2, Area.PERCH, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.ACRE, Area.M2, new BigDecimal("4046.8564224")))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("4046.8564224").compareTo(UnitArea.convert(Area.M2, Area.ACRE, BigDecimal.ONE))); //$NON-NLS-1$

		assertTrue(0 == BigDecimal.ONE.compareTo(UnitArea.convert(Area.KM2, Area.MILE2, new BigDecimal("2.5899881103")).round(MC))); //$NON-NLS-1$
		assertTrue(0 == new BigDecimal("2.5899881103").compareTo(UnitArea.convert(Area.MILE2, Area.KM2, BigDecimal.ONE))); //$NON-NLS-1$

		try {
			UnitArea.convert(null, null, BigDecimal.ONE);
			fail("fromUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitArea.convert(Area.KM2, null, BigDecimal.ONE);
			fail("toUnit is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			UnitArea.convert(Area.KM2, Area.M2, null);
			fail("value is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
