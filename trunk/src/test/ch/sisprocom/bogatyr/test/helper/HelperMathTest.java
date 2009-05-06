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
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperMath;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090504
 */
public class HelperMathTest { //TODO improve
	@Test
	public void testGcd() {
		assertEquals(2.0, HelperMath.gcd(2.0D, 4.0D));
	}
	
	@Test
	public void testLcm() {
		assertEquals(10.0D, HelperMath.lcm(2.0D, 5.0D));
	}

	@Test
	public void testIsPrime() {
        assertFalse(HelperMath.isPrime(-1));
        assertFalse(HelperMath.isPrime(0));
        assertFalse(HelperMath.isPrime(1));
        assertTrue(HelperMath.isPrime(2));
        assertFalse(HelperMath.isPrime(21));
        assertTrue(HelperMath.isPrime(23));
	}
	
	@Test
	public void testGetPrime() {
		assertEquals(23, HelperMath.getPrime(21));
	}
	
	@Test
	public void testGetPrimes() {
		assertNotNull(HelperMath.getPrimes(-5, 100));
		
		try {
			HelperMath.getPrimes(50, 10);
			fail("end value (10) must be greater than the start value (50)");
		} catch (Exception e) {}
		
		assertNotNull(HelperMath.getPrimes(21, 23));
//		System.out.println(HelperGeneral.dump(HelperMath.getPrimes(-5, 100)));
//		System.out.println(HelperGeneral.dump(HelperMath.getPrimes(21, 23)));
	}

	@Test
	public void testConvertDoubleToInt() {
		assertEquals(-2, HelperMath.convertDoubleToInt(-2.499D));
		assertEquals(-2, HelperMath.convertDoubleToInt(-2.5D));
		assertEquals(-3, HelperMath.convertDoubleToInt(-2.51D));
		assertEquals(2, HelperMath.convertDoubleToInt(2.499D));
		assertEquals(3, HelperMath.convertDoubleToInt(2.5D));
	}
	
	@Test
	public void testLog() {
		assertEquals(2.0D, HelperMath.log(10.0, 100.0));
		assertEquals(4.19180654857877D, HelperMath.log(3.0, 100.0));
	}
	
	@Test
	public void testRound() {
		assertEquals(0.0D, HelperMath.round(0.0D, 4));
		assertEquals(-2.0D, HelperMath.round(-2.499D, 0));
		assertEquals(-2.0D, HelperMath.round(-2.5D, 0));
		assertEquals(-3.0D, HelperMath.round(-2.51D, 0));
		assertEquals(-2.55D, HelperMath.round(-2.554D, 2));
		assertEquals(-2.55D, HelperMath.round(-2.546D, 2));
		assertEquals(2.0D, HelperMath.round(2.499D, 0));
		assertEquals(3.0D, HelperMath.round(2.5D, 0));
		assertEquals(2.55D, HelperMath.round(2.554D, 2));
		assertEquals(2.55D, HelperMath.round(2.545D, 2));
	}
	
//	@Test
//	public void testErf() {
//		assertEquals(0.0D, HelperMath.erf(0.0D));
//		assertEquals(-0.8427007877600067D, HelperMath.erf(-1.0D));
//		assertEquals(-0.995322265010666D, HelperMath.erf(-2.0D));
//		assertEquals(0.8427007877600067D, HelperMath.erf(1.0D));
//		assertEquals(0.995322265010666D, HelperMath.erf(2.0D));
//	}
//	
//	@Test
//	public void testErf2() {
//		assertEquals(0.0D, HelperMath.erf2(0.0D));
//		assertEquals(-0.842716825727904D, HelperMath.erf2(-1.0D));
//		assertEquals(-0.9953087428644348D, HelperMath.erf2(-2.0D));
//		assertEquals(0.842716825727904D, HelperMath.erf2(1.0D));
//		assertEquals(0.9953087428644348D, HelperMath.erf2(2.0D));
//	}
//
//	@Test
//	public void testPhi() {
////		System.out.println(HelperMath.phi(8.0D));
//		assertEquals(0.5D, HelperMath.phi(0.0D));
//		assertEquals(0.15865526139567465D, HelperMath.phi(-1.0D));
//		assertEquals(0.022750129890124482D, HelperMath.phi(-2.0D));
//		assertEquals(0.8413447386043253D, HelperMath.phi(1.0D));
//		assertEquals(0.9772498701098755D, HelperMath.phi(2.0D));
//	}
	
	@Test
	public void testRandom() {
		final int range = 2000000000;
		
		for (int ii = 0; 100 > ii; ii++) {
            int number = HelperMath.random(range);

			if (0 <= number && range >= number) {
				assertTrue(true);
			} else {
                fail();
			}
		}
	}
}
