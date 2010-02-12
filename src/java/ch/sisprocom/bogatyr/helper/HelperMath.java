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
package ch.sisprocom.bogatyr.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for math problems.
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.4.0
 */
public abstract class HelperMath { //TODO replace primitive types by BigDecimal/BigInteger
	/**
	 * Returns the greatest common divisor of two given numbers.
	 * 
	 * @param a first number
	 * @param b second number
	 * @return calculated gcd
	 * @since 0.4.0
	 */
	 public static <T extends Number> BigDecimal gcd(final T a, final T b) { //$JUnit$
		if (null == a) {
			throw new RuntimeExceptionIsNull("a"); //$NON-NLS-1$
		}
		if (null == b) {
			throw new RuntimeExceptionIsNull("b"); //$NON-NLS-1$
		}

		final BigDecimal numberA = new BigDecimal(a.toString());
		final BigDecimal numberB = new BigDecimal(b.toString());
		 
		if (0 < BigDecimal.ZERO.compareTo(numberA)) {
			throw new RuntimeExceptionMustBeGreater("a", a, 0); //$NON-NLS-1$
		}
		if (0 < BigDecimal.ZERO.compareTo(numberB)) {
			 throw new RuntimeExceptionMustBeGreater("b", b, 0); //$NON-NLS-1$
		}
		 
		if (0 == numberA.compareTo(numberB)) {
			return numberA;
		}
		 
		return 0 == BigDecimal.ZERO.compareTo(numberB) ? numberA : gcd(numberB, numberA.remainder(numberB));
	 } 

	 /**
	 * Returns the least common multiple of two given numbers.
	 * 
	 * @param a first number
	 * @param b second number
	 * @return calculated lcm
	 * @since 0.4.0
	 */
	 public static <T extends Number> BigDecimal lcm(final T a, final T b) { //$JUnit$
		if (null == a) {
			throw new RuntimeExceptionIsNull("a"); //$NON-NLS-1$
		}
		if (null == b) {
			throw new RuntimeExceptionIsNull("b"); //$NON-NLS-1$
		}
		
		final BigDecimal numberA = new BigDecimal(a.toString());
		final BigDecimal numberB = new BigDecimal(b.toString());

		if (0 <= BigDecimal.ZERO.compareTo(numberA)) {
			throw new RuntimeExceptionMustBeGreater("a", a, 0); //$NON-NLS-1$
		}
		if (0 <= BigDecimal.ZERO.compareTo(numberB)) {
			throw new RuntimeExceptionMustBeGreater("b", b, 0); //$NON-NLS-1$
		}
		 
		return numberA.multiply(numberB, Constants.DEFAULT_MATHCONTEXT).divide(gcd(a, b), Constants.DEFAULT_MATHCONTEXT);
	}
	 
	/**
     * Checks if a number is a prime.
     * 
     * @param n number for the check
     * @return true/false
     * @since 0.5.0
     */	
	public static boolean isPrime(final int n) { //$JUnit$
		// 2 is the smallest prime
        if (2 >= n) {
        	return 2 == n;
        }

        // even numbers other than 2 are not prime
        if (0 == n % 2) {
            return false;
        }

        // check odd divisors from 3 to the square root of n
        for (int i = 3, end = (int)StrictMath.sqrt((double) n); i <= end; i += 2) {
            if (0 == n % i) {
                return false;
            }
        }
        return true;
    }

	/**
     * Find the nearest prime >= n.
     * 
     * @param n number for the check
     * @return nearest prime number
     * @since 0.5.0
     */	
	public static int calcNearestPrime(final int n) { //$JUnit$
		// 2 is the smallest prime
        if (2 >= n) {
        	return 2;
        }
        
		int number = n;
        
		while (!isPrime(number)) {
            number++;
        }
        return number;
    }

	/**
     * Calculates a {@link Collection} containing all primes in a given range.
     * 
     * @param start number of the range
     * @param end number of the range
     * @return list with the calculated prime numbers
     * @since 0.5.0
     */	
	public static Collection<Integer> calcPrimes(final int start, final int end) { //$JUnit$
//        if (0 > start) {
//            throw new IllegalArgumentException("start value must be positive: " + start); //$NON-NLS-1$
//        }
        if (0 > end) {
            throw new RuntimeExceptionMustBeGreater("end", end, 0); //$NON-NLS-1$
        }
		if (start > end) {
            throw new IllegalArgumentException("end value (" + end + ") must be greater than the start value (" + start + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

        final Collection<Integer> list = new ArrayList<Integer>(end - start + 1);
		
		for (int ii = start; ii <= end; ii++) {
			if (isPrime(ii)) {
				list.add(ii);
			}
		}
		return list;
	}
	
	/**
     * Convert a double to an int-value.
     * Rounds the double value with {@link Math#round(double)}.
     * 
     * @param value double-value
     * @return int-value
     * @since 0.4.0
     */	
	public static int convertDoubleToInt(final double value) { //$JUnit$
		return (int)StrictMath.round(value);
	}
	
    /**
     * Calculate the logarithm with a given base.
     *
     * @param base of the logarithm
     * @param value double-value
     * @return double-value
     * @since 0.4.0
     */	
	public static double log(final double base, final double value) { //$JUnit$
        if (HelperNumber.NUMBER_1.doubleValue() >= base) {
            throw new RuntimeExceptionMustBeGreater("base", base, 1); //$NON-NLS-1$
        }
        if (HelperNumber.NUMBER_0.doubleValue() >= value) {
            throw new RuntimeExceptionMustBeGreater("value", value, 0); //$NON-NLS-1$
        }

		return StrictMath.log(value) / StrictMath.log(base);
	}

	/**
     * Round a double with decimal place.
     *
     * @param value double-value
     * @param decimalPlace for round
     * @return double-value
     * @since 0.4.0
     */
    public static double round(final double value, final int decimalPlace) { //$JUnit$
		int dp = decimalPlace;
		double powerOfTen = HelperNumber.NUMBER_1.doubleValue();
		
	    while (0 < dp) {
            dp--;
            powerOfTen *= HelperNumber.NUMBER_10.doubleValue();
	    }
        return (double) StrictMath.round(value * powerOfTen) / powerOfTen;
	}

    /**
     * Returns a random integer between 0 and n-1.
     *
     * @param n int-Value
     * @return random int-value between 0 and n-1
     * @since 0.4.0
     */
    public static int getRandom(final int n) { //$JUnit$
    	return (int) (StrictMath.random() * (double) n);
    }
    
    /**
     * Returns the amount with a given start amount, interest and elapsed days.
     * Note: the interest p.a. is based on 360 days
     *
     * @param amount start
     * @param interest in % p.a. (e.g. 0.04)
     * @param days elapsed
     * @return calculated amount with a given start amount, interest and elapsed days
     * @since 0.7.0
     */
    public static double calcAmount(final double amount, final double interest, final int days) { //$JUnit$
		 if (0 >= days) {
			 throw new RuntimeExceptionMustBeGreater("days", days, 0); //$NON-NLS-1$
		 }
		 
		 return amount * StrictMath.pow(StrictMath.E, (double)days / 360.0 * interest);
    }
	
    /**
     * Calculates the factorial (n!) from 0 to n.
     * 
     * @param n number to calculate
     * @return factorial (n!) from 1 to n
     * @since 0.9.0
     */	
	public static BigInteger factorial(final int n) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }

        BigInteger sum = BigInteger.ONE;

        for (int ii = n; 0 < ii; ii--) {
        	sum = sum.multiply(BigInteger.valueOf(ii));
        }
        return sum;
    }
	
	/**
     * Calculates the Gauss sum from 0 to n.
     * 
     * @param n number to calculate
     * @return Gauss sum from 1 to n
     * @since 0.7.0
     */	
	public static int sum(final int n) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
        if (0 == n) {
        	return 1;
        }

        return n * (n + 1) / 2;
    }
	
	/**
     * Calculates the Gauss square sum from 0 to n^2.
     * 
     * @param n number to calculate
     * @return Gauss square sum from 1 to n^2
     * @since 0.7.0
     */	
	public static int sumSquare(final int n) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
        if (0 == n) {
        	return 1;
        }

        return n * (n + 1) * (2* n + 1) / 6;
    }
	
	/**
     * Calculates the Gauss cubic sum from 0 to n^3.
     * 
     * @param n number to calculate
     * @return Gauss cubic sum from 1 to n^3
     * @since 0.7.0
     */	
	public static int sumCubic(final int n) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
        if (0 == n) {
        	return 1;
        }

        return (int) StrictMath.pow(sum(n), HelperNumber.NUMBER_2.doubleValue());
    }

	/**
     * Calculates the sum between the range m - n.
     * 
     * @param m start number for the range
     * @param n end number for the range
     * @return sum between the range m - n
     * @since 0.7.0
     */	
	public static int sumRange(final int m, final int n) {
        if (0 > m) {
        	throw new RuntimeExceptionMustBeGreater("m", m, 0); //$NON-NLS-1$
        }
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
		if (m > n) {
            throw new IllegalArgumentException("n value (" + n + ") must be greater than the m value (" + m + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        return (m + n) * (n - m + 1) / 2;
    }
	
	/**
     * Calculates the number of connections from n.
     * 
     * @param n number to calculate
     * @return number of connections from n
     * @since 0.7.0
     */	
	public static int calcConnections(final int n) { //$JUnit$
        if (0 >= n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
        return n * (n - 1) / 2;
    }

	/**
     * Calculates for the given number of persons the probability that two people will share a birthday.
     * 
     * @param n number of persons
     * @return probability that two people will share a birthday
     * @since 0.7.0
     */	
	public static double calcBirthdayProblem(final int n) { //$JUnit$
        if (0 >= n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
      //TODO does it also work for other parameters than days p.a.?
        
        final BigDecimal term01 = new BigDecimal(factorial(HelperTime.DAYS_PER_YEAR));
        final BigDecimal term02 = new BigDecimal(factorial(HelperTime.DAYS_PER_YEAR - n));
        final BigDecimal term03 = BigDecimal.valueOf(HelperTime.DAYS_PER_YEAR).pow(n);
        
        final BigDecimal result = BigDecimal.ONE.subtract(term01.divide(term02.multiply(term03, Constants.DEFAULT_MATHCONTEXT), Constants.DEFAULT_MATHCONTEXT));

        return result.doubleValue();
    }

	/**
     * Calculates the binomial coefficient ("n choose k").
     * 
     * @param n range
     * @param k subset of n ("n choose k")
     * @return binomial coefficient
     * @since 0.7.0
     */	
	public static BigInteger binomialCoefficient(final int n, final int k) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        if (0 > k) {
        	throw new RuntimeExceptionMustBeGreater("k", k, 0); //$NON-NLS-1$
        }
		if (k > n) {
            throw new IllegalArgumentException("n value (" + n + ") must be greater than the k value (" + k + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
  
        return factorial(n).divide(((factorial(n - k).multiply(factorial(k)))));
    }

	/**
     * Calculates the sum of odd numbers between 0 - n.
     * 
     * @param n end number for the range
     * @return sum of odd numbers between 0 - n
     * @since 0.7.0
     */	
	public static int sumOdd(final int n) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
        return (int)StrictMath.pow(n, HelperNumber.NUMBER_2.doubleValue());
    }

	/**
     * Calculates the sum of even numbers between 0 - n.
     * 
     * @param n number
     * @return sum of even numbers between 0 - n
     * @since 0.7.0
     */	
	public static int sumEven(final int n) { //$JUnit$
        if (0 > n) {
        	throw new RuntimeExceptionMustBeGreater("n", n, 0); //$NON-NLS-1$
        }
        
        int sum = 0;
        for (int ii = 2; ii <= n * 2; ii += 2 ) {
        	sum += ii;
        }

        return sum;
    }
	
	/**
     * Checks if the given number is odd.
     * 
     * @param n number to check
     * @return true/false
     * @since 0.7.0
     */	
	public static boolean isOdd(final int n) { //$JUnit$
        return 0 != n % 2;
    }

	/**
     * Checks if the given number is even.
     * 
     * @param n number to check
     * @return true/false
     * @since 0.7.0
     */	
	public static boolean isEven(final int n) { //$JUnit$
        return !isOdd(n);
    }
}

