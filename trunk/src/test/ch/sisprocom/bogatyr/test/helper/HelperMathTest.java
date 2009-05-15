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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperMath;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public class HelperMathTest {
	@Test
	public void testGcd() {
		assertEquals(2.0D, HelperMath.gcd(2.0D, 4.0D), 0.00001D);
		assertEquals(2.0D, HelperMath.gcd(2.0D, 2.0D), 0.00001D);
		assertEquals(2.0D, HelperMath.gcd(4.0D, 2.0D), 0.00001D);
		assertEquals(2.0D, HelperMath.gcd(2.0D, 2.0D), 0.00001D);
		assertEquals(2.5D, HelperMath.gcd(2.5D, 5.0D), 0.00001D);
		assertEquals(0.0D, HelperMath.gcd(0.0D, 0.0D), 0.00001D);
		assertEquals(Double.MAX_VALUE, HelperMath.gcd(Double.MAX_VALUE, Double.MAX_VALUE), 0.00001D);
		
		try {
			HelperMath.gcd(-2.0D, 4.0D);
			fail("a is negative!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			HelperMath.gcd(2.0D, -4.0D);
			fail("b is negative!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testLcm() {
		assertEquals(10.0D, HelperMath.lcm(2.0D, 5.0D), 0.00001D);
		assertEquals(10.0D, HelperMath.lcm(5.0D, 2.0D), 0.00001D);
		assertEquals(Double.NaN, HelperMath.lcm(0.0D, 0.0D), 0.00001D);
		assertEquals(Double.POSITIVE_INFINITY, HelperMath.lcm(Double.MAX_VALUE, Double.MAX_VALUE), 0.00001D);
	
		try {
			HelperMath.lcm(-2.0D, 4.0D);
			fail("a is negative!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			HelperMath.lcm(2.0D, -4.0D);
			fail("b is negative!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}

	@Test
	public void testIsPrime() {
        assertFalse(HelperMath.isPrime(-1));
        assertFalse(HelperMath.isPrime(0));
        assertFalse(HelperMath.isPrime(1));
        assertTrue(HelperMath.isPrime(2));
        assertFalse(HelperMath.isPrime(21));
        assertTrue(HelperMath.isPrime(23));
        assertTrue(HelperMath.isPrime(997));
	}
	
	@Test
	public void testCalcNearestPrime() {
		assertEquals(23, HelperMath.calcNearestPrime(21));
		assertEquals(43, HelperMath.calcNearestPrime(42));
		assertEquals(2, HelperMath.calcNearestPrime(-23));
		assertEquals(2, HelperMath.calcNearestPrime(0));
	}
	
	@Test
	public void testCalcPrimes() {
		assertEquals(168, HelperMath.calcPrimes(0, 1000).size());
		assertEquals(168, HelperMath.calcPrimes(-1000, 1000).size());
		assertEquals(0, HelperMath.calcPrimes(0, 0).size());
		assertEquals(2, HelperMath.calcPrimes(0, 3).size());
		
//		try {
//			HelperMath.calcPrimes(-50, 10);
//			fail("start value (-50) must be positive"); //$NON-NLS-1$
//		} catch (Exception e) {/*nothing to do*/}
		try {
			HelperMath.calcPrimes(50, -10);
			fail("end value (-10) must be positive!"); //$NON-NLS-1$
		} catch (Exception e) {/*nothing to do*/}		
		try {
			HelperMath.calcPrimes(50, 10);
			fail("end value (10) must be greater than the start value (50)!"); //$NON-NLS-1$
		} catch (Exception e) {/*nothing to do*/}
	}

	@Test
	public void testConvertDoubleToInt() {
		assertEquals(-2, HelperMath.convertDoubleToInt(-2.499D));
		assertEquals(-2, HelperMath.convertDoubleToInt(-2.5D));
		assertEquals(-3, HelperMath.convertDoubleToInt(-2.51D));
		assertEquals(2, HelperMath.convertDoubleToInt(2.499D));
		assertEquals(3, HelperMath.convertDoubleToInt(2.5D));
		assertEquals(0, HelperMath.convertDoubleToInt(0.0D));
	}
	
	@Test
	public void testLog() {
		assertEquals(2.0D, HelperMath.log(10.0D, 100.0D), 0.00001D);
		assertEquals(0.0D, HelperMath.log(2.0D, 1.0D), 0.00001D);
		assertEquals(1.0D, HelperMath.log(2.0D, 2.0D), 0.00001D);
		assertEquals(4.19180654857877D, HelperMath.log(3.0, 100.0), 0.00001D);
		
		try {
			HelperMath.log(1.0, 100.0);
			fail("base must be greater than 1!"); //$NON-NLS-1$
		} catch (Exception e) {/*nothing to do*/}
		try {
			HelperMath.log(10.0, -100.0);
			fail("value (-100) must be positive!"); //$NON-NLS-1$
		} catch (Exception e) {/*nothing to do*/}		
	}
	
	@Test
	public void testRound() {
		assertEquals(0.0D, HelperMath.round(0.0D, 4), 0.00001D);
		assertEquals(-2.0D, HelperMath.round(-2.499D, 0), 0.00001D);
		assertEquals(-2.0D, HelperMath.round(-2.5D, 0), 0.00001D);
		assertEquals(-3.0D, HelperMath.round(-2.51D, 0), 0.00001D);
		assertEquals(-2.55D, HelperMath.round(-2.554D, 2), 0.00001D);
		assertEquals(-2.55D, HelperMath.round(-2.546D, 2), 0.00001D);
		assertEquals(2.0D, HelperMath.round(2.499D, 0), 0.00001D);
		assertEquals(3.0D, HelperMath.round(2.5D, 0), 0.00001D);
		assertEquals(2.55D, HelperMath.round(2.554D, 2), 0.00001D);
		assertEquals(2.55D, HelperMath.round(2.545D, 2), 0.00001D);
		assertEquals(3.0D, HelperMath.round(2.545D, -2), 0.00001D);
	}
	
	@Test
	public void testRandom() {
		//positive numbers
		int range = Integer.MAX_VALUE;
		for (int ii = 0; 1000 > ii; ii++) {
            final int number = HelperMath.getRandom(range);

			assertTrue(0 <= number && range >= number);
		}

		//negative numbers
		range = Integer.MIN_VALUE;
		for (int ii = 0; 1000 > ii; ii++) {
            final int number = HelperMath.getRandom(range);

            assertTrue(0 >= number && range <= number);
		}
	}
	
	@Test
	public void testCalcAmount() {
		assertEquals(10055.710162720176D, HelperMath.calcAmount(10000D, 0.04D, 50D), 0.00001D);
		assertEquals(9944.598480048968D, HelperMath.calcAmount(10000D, -0.04D, 50D), 0.00001D);
		assertEquals(-9944.598480048968D, HelperMath.calcAmount(-10000D, -0.04D, 50D), 0.00001D);
		assertEquals(-10055.710162720176D, HelperMath.calcAmount(-10000D, 0.04D, 50D), 0.00001D);

		try {
			HelperMath.calcAmount(10000D, 0.04D, -50D);
			fail("days (-50) must be positive!"); //$NON-NLS-1$
		} catch (Exception e) {/*nothing to do*/}		

	}
}
