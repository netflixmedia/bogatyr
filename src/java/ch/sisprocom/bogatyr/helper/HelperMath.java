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
package ch.sisprocom.bogatyr.helper;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This is a math helper class for math problems
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 20090520
 */
public abstract class HelperMath {
	
	/**
	 * Returns the greatest common divisor of two given numbers.
	 * 
	 * @param a first number
	 * @param b second number
	 * @return calculated gcd
	 */
	 public static double gcd(final double a, final double b) { //$JUnit
		 if (0.0D > a) {
			 throw new IllegalArgumentException("a value must be positive: " + a); //$NON-NLS-1$
		 }
		 if (0.0D > b) {
			 throw new IllegalArgumentException("b value must be positive: " + b); //$NON-NLS-1$
		 }
		 
		 if (a == b) {
			 return a;
		 }
		 
		 return 0.0D == b ? a : gcd(b, a % b);
	 } 

	 /**
	 * Returns the least common multiple of two given numbers.
	 * 
	 * @param a first number
	 * @param b second number
	 * @return calculated lcm
	 */
	 public static double lcm(final double a, final double b) { //$JUnit
		 if (0.0D > a) {
			 throw new IllegalArgumentException("a value must be positive: " + a); //$NON-NLS-1$
		 }
		 if (0.0D > b) {
			 throw new IllegalArgumentException("b value must be positive: " + b); //$NON-NLS-1$
		 }
		 return a * b / gcd(a, b);
	 }
	 
	/**
     * Checks if a number is a prime.
     * 
     * @param n number for the check
     * @return true/false
     */	
	public static boolean isPrime(final int n) { //$JUnit
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
     */	
	public static int calcNearestPrime(final int n) { //$JUnit
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
     */	
	public static Collection<Integer> calcPrimes(final int start, final int end) { //$JUnit
//        if (0 > start) {
//            throw new IllegalArgumentException("start value must be positive: " + start); //$NON-NLS-1$
//        }
        if (0 > end) {
            throw new IllegalArgumentException("end value must be positive: " + end); //$NON-NLS-1$
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
     */	
	public static int convertDoubleToInt(final double value) { //$JUnit
		return (int)StrictMath.round(value);
	}
	
    /**
     * Calculate the logarithm with a given base.
     *
     * @param base of the logarithm
     * @param value double-value
     * @return double-value
     */	
	public static double log(final double base, final double value) { //$JUnit
        if (1.0D >= base) {
            throw new IllegalArgumentException("base must be greater than 1: " + base); //$NON-NLS-1$
        }
        if (0.0D >= value) {
            throw new IllegalArgumentException("value must be positive: " + value); //$NON-NLS-1$
        }

		return StrictMath.log(value) / StrictMath.log(base);
	}

	/**
     * Round a double with decimal place.
     *
     * @param value double-value
     * @param decimalPlace for round
     * @return double-value
     */
    public static double round(final double value, final int decimalPlace) { //$JUnit
		int dp = decimalPlace;
		double powerOfTen = 1.0D;
		
	    while (0 < dp) {
            dp--;
            powerOfTen *= 10.0D;
	    }
        return (double) StrictMath.round(value * powerOfTen) / powerOfTen;
	}

    /**
     * Returns a random integer between 0 and n-1.
     *
     * @param n int-Value
     * @return random int-value between 0 and n-1
     */
    public static int getRandom(final int n) { //$JUnit
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
     */
    public static double calcAmount(final double amount, final double interest, final int days) { //$JUnit
		 if (0 >= days) {
			 throw new IllegalArgumentException("days value must be positive: " + days); //$NON-NLS-1$
		 }
		 
		 return amount * StrictMath.pow(StrictMath.E, (double)days / 360.0 * interest);
    }
    
	/**
     * Calculates the factorial (n!) from 0 to n.
     * 
     * @param n number to calculate
     * @return factorial (n!) from 1 to n
     */	
	public static int factorial(final int n) {
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        if (0 == n) {
        	return 1;
        }

        return n * (n + 1) / 2;
    }
	
	/**
     * Calculates the square factorial from 0 to n^2.
     * 
     * @param n number to calculate
     * @return square factorial from 1 to n^2
     */	
	public static int factorialSquare(final int n) {
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        if (0 == n) {
        	return 1;
        }

        return n * (n + 1) * (2* n + 1) / 6;
    }
	
	/**
     * Calculates the cubic factorial from 0 to n^3.
     * 
     * @param n number to calculate
     * @return square factorial from 1 to n^3
     */	
	public static int factorialCubic(final int n) {
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        if (0 == n) {
        	return 1;
        }

        return (int) StrictMath.pow(factorial(n), 2.0D);
    }
	
	/**
     * Calculates the number of connections from n.
     * 
     * @param n number to calculate
     * @return number of connections from n
     */	
	public static int calcConnections(final int n) {
        if (0 >= n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        return n * (n - 1) / 2;
    }

	/**
     * Calculates for the given number of persons the probability that two people will share a birthday.
     * 
     * @param n number of persons
     * @return probability that two people will share a birthday
     */	
	public static double calcBirthdayProblem(final int n) { //TODO does it also work for other parameters than days p.a.?
        if (0 >= n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        return factorial(HelperTime.DAYS_YEAR) / (factorial(HelperTime.DAYS_YEAR - n) * StrictMath.pow(HelperTime.DAYS_YEAR, n));
    }

	/**
     * Calculates the binomial coefficient ("n choose k").
     * 
     * @param n range
     * @param k subset of n ("n choose k")
     * @return binomial coefficient
     */	
	public static int binomialCoefficient(final int n, final int k) {
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        if (0 > k) {
        	throw new IllegalArgumentException("k value must be positive: " + k); //$NON-NLS-1$
        }
		if (k > n) {
            throw new IllegalArgumentException("n value (" + n + ") must be greater than the k value (" + k + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
  
        return factorial(n) / (factorial(n - k) * factorial(k));
    }

	/**
     * Calculates the sum between the range m - n.
     * 
     * @param m start number for the range
     * @param n end number for the range
     * @return sum between the range m - n
     */	
	public static int sumRange(final int m, final int n) {
        if (0 > m) {
        	throw new IllegalArgumentException("m value must be positive: " + m); //$NON-NLS-1$
        }
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
		if (m > n) {
            throw new IllegalArgumentException("n value (" + n + ") must be greater than the m value (" + m + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        return (m + n) * (n - m + 1) / 2;
    }

	/**
     * Calculates the sum of odd numbers between 0 - n.
     * 
     * @param n end number for the range
     * @return sum of odd numbers between 0 - n
     */	
	public static int sumOdd(final int n) {
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        return (int)StrictMath.pow(n, 2.0D);
    }

	/**
     * Calculates the sum of even numbers between 0 - n.
     * 
     * @param n number
     * @return sum of even numbers between 0 - n
     */	
	public static int sumEven(final int n) {
        if (0 > n) {
        	throw new IllegalArgumentException("n value must be positive: " + n); //$NON-NLS-1$
        }
        
        return factorial(n) - sumOdd(n);
    }
	
	/**
     * Checks if the given number is odd.
     * 
     * @param n number to check
     * @return true/false
     */	
	public static boolean isOdd(final int n) {
        return 0 != n % 2;
    }

	/**
     * Checks if the given number is even.
     * 
     * @param n number to check
     * @return true/false
     */	
	public static boolean isEven(final int n) {
        return !isOdd(n);
    }
}
