/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;
/**
 * This is a math helper class for math problems
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 20080725
 */
public abstract class HelperMath {
	/**
     * Convert a double to an int-Value.
     * Rounds the double value with {@link Math#round(double)}.
     * 
     * @param value double-value
     * @return int-value
     * @see Math
     */	
	public static int convertDoubleToInt(final double value) {
		return (int)Math.round(value);
	}
	
    /**
     * Logarithm
     *
     * @param base of the logarithm
     * @param value double-Value
     * @return double-value
     */	
	public static double log(final double base, final double value) {
		return StrictMath.log(value) / StrictMath.log(base);
	}

	/**
     * Round a double with decimal place
     *
     * @param value double-Value
     * @param decimalPlace for round
     * @return double-value
     */
    public static double round(final double value, final int decimalPlace) {
	    int dp = decimalPlace;
	    
		double power_of_ten = 1.0D;
	    while (dp-- > 0) {
	       power_of_ten *= 10.0D;
	    }
	    return (double) Math.round(value * power_of_ten) / power_of_ten;
	}
	
    /**
     * Fractional error in math formula less than 1.2 * 10 ^ -7.
     * Although subject to catastrophic cancellation when z is very close to 0
     *
     * @param z double-Value
     * @return double-value
     */
    public static double erf(final double z) {
        if (HelperGeneral.isValidDouble(z)) {
	    	final double t = 1.0D / (1.0D + 0.5D * Math.abs(z));
	
	        // use Horner's method
	        final double ans = 1.0D - t * StrictMath.exp( -z*z - 1.26551223D +
	        								t * ( 1.00002368D +
	                                        t * ( 0.37409196D + 
	                                        t * ( 0.09678418D + 
	                                        t * (-0.18628806D + 
	                                        t * ( 0.27886807D + 
	                                        t * (-1.13520398D + 
	                                        t * ( 1.48851587D + 
	                                        t * (-0.82215223D +
                                                    t * 0.17087277D)))))))));
	        if (z >= 0.0D) {
                return ans;
            }
			return -ans;
        }
        return 0.0D;
    }

    /**
     * Fractional error less than x.xx * 10 ^ -4.
     *
     * @param z double-Value
     * @return double-value
     */
    public static double erf2(final double z) {
        final double t = 1.0D / (1.0D + 0.47047D * Math.abs(z));
        final double poly = t * (0.3480242D + t * (-0.0958798D + t * 0.7478556D));
        final double ans = 1.0D - poly * StrictMath.exp(-z * z);
        
        if (z >= 0.0D) {
            return ans;
        }
		return -ans;
    }

    /**
     * Cumulative normal distribution
     *
     * @param z double-Value
     * @return double-value
     */
    public static double phi(final double z) {
        return 0.5 * (1.0D + erf(z / 1.4142135623730951));
    }

    /**
     * Random number with standard Gaussian distribution
     *
     * @return double-value
     */
    public static double gaussian() {
        final double U = Math.random();
        final double V = Math.random();
        return StrictMath.sin(2.0D * Math.PI * V) * Math.sqrt(-2.0D * StrictMath.log(1.0 - U));
    }

    /**
     * Random number with Gaussian distribution of mean mu and stddev sigma
     *
     * @param mu double-Value
     * @param sigma double-Value
     * @return double-value
     */
    public static double gaussian(final double mu, final double sigma) {
        return mu + sigma * gaussian();
    }

    /**
     * Random integer between 0 and n-1
     *
     * @param n int-Value
     * @return random int-value between 0 and n-1
     */
    public static int random(int n) {
    	return (int) (Math.random() * n);
    }
    
    
//	// absolute value
//  public static double abs(double x) {
//      Math.
//  	if      (x == 0.0) return  x;    // for -0 and +0
//      else if (x >  0.0) return  x;
//      else               return -x;
//  }
//
//  // exponentiation - special case for negative input improves accuracy
//  public static double exp(double x) {
//  	double term = 1.0;
//      double sum  = 1.0;
//      for (int N = 1; sum != sum + term; N++) {
//          term = term * Math.abs(x) / N;
//          sum  = sum + term;
//      }
//      if (x >= 0) return sum;
//		return 1.0 / sum;
//  }
//
//  // calculate square root using Newton's method
//  public static double sqrt(double c) {
//      if (c == 0) return c;
//      if (c <  0) return Double.NaN;
//      double EPSILON = 1E-15;
//      double t = c;
//      while (Math.abs(t - c/t) > EPSILON * t) {
//          t = (c/t + t) / 2.0;
//      }
//      return t;
//  }
//    public static double phi(double x) {
//        return Math.exp(-0.5 * x * x) / Math.sqrt(2 * Math.PI);
//    }
//
//    public static double phi(double x, double mu, double sigma) {
//        return phi((x - mu) / sigma) / sigma;
//    }
//
//    // accurate with absolute error less than 8 * 10^-16
//    // Reference: http://www.jstatsoft.org/v11/i04/v11i04.pdf
//    public static double Phi2(double z) {
//        if (z >  8.0) return 1.0;    // needed for large values of z
//        if (z < -8.0) return 0.0;    // probably not needed
//        double sum = 0.0, term = z;
//        for (int i = 3; sum + term != sum; i += 2) {
//            sum  = sum + term;
//            term = term * z * z / i;
//        }
//        return 0.5 + sum * phi(z);
//    }
//
//    // cumulative normal distribution with mean mu and std deviation sigma
//    public static double Phi(double z, double mu, double sigma) {
//        return Phi((z - mu) / sigma);
//    }
}

