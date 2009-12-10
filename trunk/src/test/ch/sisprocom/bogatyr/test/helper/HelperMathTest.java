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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperMath;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091210
 */
public class HelperMathTest {
//	@Test
//	public void testGcd() {
//		assertEquals(2.0D, HelperMath.gcd(2.0D, 4.0D), 0.00001D);
//		assertEquals(2.0D, HelperMath.gcd(2.0D, 2.0D), 0.00001D);
//		assertEquals(2.0D, HelperMath.gcd(4.0D, 2.0D), 0.00001D);
//		assertEquals(2.0D, HelperMath.gcd(2.0D, 2.0D), 0.00001D);
//		assertEquals(2.5D, HelperMath.gcd(2.5D, 5.0D), 0.00001D);
//		assertEquals(0.0D, HelperMath.gcd(0.0D, 0.0D), 0.00001D);
//		assertEquals(Double.MAX_VALUE, HelperMath.gcd(Double.MAX_VALUE, Double.MAX_VALUE), 0.00001D);
//		
//		try {
//			HelperMath.gcd(-2.0D, 4.0D);
//			fail("a is negative!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			HelperMath.gcd(2.0D, -4.0D);
//			fail("b is negative!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//	}
	@Test
	public void testGcd() {
		assertTrue(0 == new BigDecimal("2").compareTo(HelperMath.gcd(new BigDecimal("2"), new BigDecimal("4"))));   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		assertTrue(0 == new BigDecimal("2").compareTo(HelperMath.gcd(new BigDecimal("2"), new BigDecimal("2")))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		assertTrue(0 == new BigDecimal("2").compareTo(HelperMath.gcd(new BigDecimal("4"), new BigDecimal("2"))));   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		assertTrue(0 == new BigDecimal("2.5").compareTo(HelperMath.gcd(new BigDecimal("2.5"), new BigDecimal("5"))));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		assertTrue(0 == BigDecimal.ZERO.compareTo(HelperMath.gcd(BigDecimal.ZERO, BigDecimal.ZERO)));
		
		try {
			HelperMath.gcd(new BigDecimal("-2"), BigDecimal.ZERO); //$NON-NLS-1$
			fail("a is negative!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperMath.gcd(BigDecimal.ZERO, new BigDecimal("-2")); //$NON-NLS-1$
			fail("b is negative!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
//	@Test
//	public void testLcm() {
//		assertEquals(10.0D, HelperMath.lcm(2.0D, 5.0D), 0.00001D);
//		assertEquals(10.0D, HelperMath.lcm(5.0D, 2.0D), 0.00001D);
//		assertEquals(Double.NaN, HelperMath.lcm(0.0D, 0.0D), 0.00001D);
//		assertEquals(Double.POSITIVE_INFINITY, HelperMath.lcm(Double.MAX_VALUE, Double.MAX_VALUE), 0.00001D);
//	
//		try {
//			HelperMath.lcm(-2.0D, 4.0D);
//			fail("a is negative!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			HelperMath.lcm(2.0D, -4.0D);
//			fail("b is negative!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//	}
	
	@Test
	public void testLcm() {
		assertTrue(0 == BigDecimal.TEN.compareTo(HelperMath.lcm(new BigDecimal("2"), new BigDecimal("5"))));  //$NON-NLS-1$//$NON-NLS-2$
		assertTrue(0 == BigDecimal.TEN.compareTo(HelperMath.lcm(new BigDecimal("5"), new BigDecimal("2")))); //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			HelperMath.lcm(new BigDecimal("-2"), BigDecimal.ZERO); //$NON-NLS-1$
			fail("a is negative!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperMath.lcm(BigDecimal.ZERO, new BigDecimal("-2")); //$NON-NLS-1$
			fail("b is negative!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
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
		
		try {
			HelperMath.calcPrimes(50, -10);
			fail("end value (-10) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperMath.calcPrimes(50, 10);
			fail("end value (10) must be greater than the start value (50)!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
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
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperMath.log(10.0, -100.0);
			fail("value (-100) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
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
		assertEquals(10055.710162720176D, HelperMath.calcAmount(10000.0D, 0.04D, 50), 0.00001D);
		assertEquals(9944.598480048968D, HelperMath.calcAmount(10000.0D, -0.04D, 50), 0.00001D);
		assertEquals(-9944.598480048968D, HelperMath.calcAmount(-10000.0D, -0.04D, 50), 0.00001D);
		assertEquals(-10055.710162720176D, HelperMath.calcAmount(-10000.0D, 0.04D, 50), 0.00001D);

		try {
			HelperMath.calcAmount(10000.0D, 0.04D, -50);
			fail("days (-50) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFactorial() {
        assertEquals(BigInteger.valueOf(24), HelperMath.factorial(4));
        
		try {
			HelperMath.factorial(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testSum() {
        assertEquals(10, HelperMath.sum(4));
        
		try {
			HelperMath.sum(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testSumSquare() {
        assertEquals(30, HelperMath.sumSquare(4));
        
		try {
			HelperMath.sumSquare(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testSumCubic() {
        assertEquals(100, HelperMath.sumCubic(4));
        
		try {
			HelperMath.sumCubic(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testSumOdd() {
        assertEquals(9, HelperMath.sumOdd(3));
        
		try {
			HelperMath.sumOdd(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testSumEven() {
        assertEquals(12, HelperMath.sumEven(3));
        
		try {
			HelperMath.sumEven(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testIsOdd() {
        assertFalse(HelperMath.isOdd(4));
        assertTrue(HelperMath.isOdd(3));
	}
	
	@Test
	public void testIsEven() {
        assertTrue(HelperMath.isEven(4));
        assertFalse(HelperMath.isEven(3));
	}
	
	@Test
	public void testCalcConnections() {
        assertEquals(6, HelperMath.calcConnections(4));
        
		try {
			HelperMath.calcConnections(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void tesBinomialCoefficient() {
		assertEquals(BigInteger.valueOf(35), HelperMath.binomialCoefficient(7, 3));

		try {
			HelperMath.binomialCoefficient(-1, 4);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperMath.binomialCoefficient(3, -1);
			fail("k (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperMath.binomialCoefficient(3, 4);
			fail("n (3) must be greater than k (4)!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
		
	@Test
	public void testCalcBirthdayProblem() {
        assertEquals(0.50729D, HelperMath.calcBirthdayProblem(23), 0.00001D);
        
		try {
			HelperMath.calcConnections(-1);
			fail("n (-1) must be positive!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
