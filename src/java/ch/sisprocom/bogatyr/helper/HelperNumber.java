/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * This is a helper class for numbers.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100209)
 * @since 0.7.0
 */
public abstract class HelperNumber {
    public static final BigDecimal BIGDECIMAL_100 	  = new BigDecimal("100"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_1000 	  = new BigDecimal("1000"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_10000   = new BigDecimal("10000"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_100000  = new BigDecimal("100000"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_1000000 = new BigDecimal("1000000"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_8 	  = new BigDecimal("8"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_16 	  = new BigDecimal("16"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_32 	  = new BigDecimal("32"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_64 	  = new BigDecimal("64"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_128 	  = new BigDecimal("128"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_256 	  = new BigDecimal("256"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_512 	  = new BigDecimal("512"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_1024 	  = new BigDecimal("1024"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_2048 	  = new BigDecimal("2048"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_4096 	  = new BigDecimal("4096"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_8192 	  = new BigDecimal("8192"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_16384   = new BigDecimal("16384"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_32768   = new BigDecimal("32768"); //$NON-NLS-1$
    public static final BigDecimal BIGDECIMAL_65536   = new BigDecimal("65536"); //$NON-NLS-1$

    public static final int INT_10 		= 10;
    public static final int INT_100 	= 100;
    public static final int INT_1000 	= 1000;
    public static final int INT_10000 	= 10000;
    public static final int INT_100000 	= 100000;
    public static final int INT_1000000 = 1000000;
    public static final int INT_8 		= 8;
    public static final int INT_16 		= 16;
    public static final int INT_32 		= 32;
    public static final int INT_64 		= 64;
    public static final int INT_128 	= 128;
    public static final int INT_256 	= 256;
    public static final int INT_512 	= 512;
    public static final int INT_1024 	= 1024;
    public static final int INT_2048 	= 2048;
    public static final int INT_4096 	= 4096;
    public static final int INT_8192 	= 8192;
    public static final int INT_16384 	= 16384;
    public static final int INT_32768 	= 32768;
    public static final int INT_65536 	= 65536;


    /**
     * Multiply 1-n values together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static <T extends Number> BigDecimal multiply(final T... values) {
        if (null == values) {
            throw new RuntimeExceptionIsNull("values"); //$NON-NLS-1$
        }

        BigDecimal result = null;

        for (int ii = 0; ii < values.length; ii++) {
            result = new BigDecimal((0 == ii ? values[0] : multiply(result, values[ii])).toString());
        }
        return result;
    }

    /**
     * Multiply two values together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static <T extends Number> BigDecimal multiply(final T a, final T b) {
        if (null == a || null == b) {
            return null;
        }
        
		final BigDecimal numberA = new BigDecimal(a.toString());
		final BigDecimal numberB = new BigDecimal(b.toString());

        return numberA.multiply(numberB, Constants.DEFAULT_MATHCONTEXT);
    }

//    /**
//     * Multiply 1-n {@link BigInteger} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static BigInteger multiply(final BigInteger... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        BigInteger result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : multiply(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Multiply 2 {@link BigInteger} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static BigInteger multiply(final BigInteger a, final BigInteger b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a.multiply(b);
//    }
//
//    /**
//     * Multiply 1-n {@link Integer} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Integer multiply(final Integer... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Integer result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : multiply(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Multiply 2 {@link Integer} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Integer multiply(final Integer a, final Integer b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a * b;
//    }
//
//    /**
//     * Multiply 1-n {@link Double} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Double multiply(final Double... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Double result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : multiply(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Multiply 2 {@link Double} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Double multiply(final Double a, final Double b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a * b;
//    }
//
//    /**
//     * Multiply 1-n {@link Float} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Float multiply(final Float... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Float result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : multiply(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Multiply 2 {@link Float} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Float multiply(final Float a, final Float b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a * b;
//    }
//
//    /**
//     * Multiply 1-n {@link Long} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Long multiply(final Long... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Long result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : multiply(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Multiply 2 {@link Long} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.7.0
//     */
//    public static Long multiply(final Long a, final Long b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a * b;
//    }
    
    /**
     * Add 1-n values together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static <T extends Number> BigDecimal add(final T... values) {
        if (null == values) {
            throw new RuntimeExceptionIsNull("values"); //$NON-NLS-1$
        }

        BigDecimal result = null;

        for (int ii = 0; ii < values.length; ii++) {
            result = new BigDecimal((0 == ii ? values[0] : add(result, values[ii])).toString());
        }
        return result;
    }

    /**
     * Add two values together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static <T extends Number> BigDecimal add(final T a, final T b) {
        if (null == a || null == b) {
            return null;
        }
        
		final BigDecimal numberA = new BigDecimal(a.toString());
		final BigDecimal numberB = new BigDecimal(a.toString());

        return numberA.add(numberB);
    }
    
//    /**
//     * Add 1-n {@link BigInteger} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static BigInteger add(final BigInteger... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        BigInteger result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : add(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Add 2 {@link BigInteger} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static BigInteger add(final BigInteger a, final BigInteger b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a.add(b);
//    }   
//    
//    /**
//     * Add 1-n {@link Integer} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Integer add(final Integer... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Integer result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : add(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Add 2 {@link Integer} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Integer add(final Integer a, final Integer b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a + b;
//    }  
//    
//    /**
//     * Add 1-n {@link Double} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Double add(final Double... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Double result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : add(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Add 2 {@link Double} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Double add(final Double a, final Double b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a + b;
//    }
//    
//    /**
//     * Add 1-n {@link Float} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Float add(final Float... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Float result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : add(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Add 2 {@link Float} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Float add(final Float a, final Float b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a + b;
//    }
//    
//    /**
//     * Add 1-n {@link Long} together.
//     *
//     * @param values 1-n values
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Long add(final Long... values) {
//        if (null == values) {
//            throw new RuntimeExceptionArgumentIsNull("values"); //$NON-NLS-1$
//        }
//
//        Long result = null;
//
//        for (int ii = 0; ii < values.length; ii++) {
//            result = 0 == ii ? values[0] : add(result, values[ii]);
//        }
//        return result;
//    }
//
//    /**
//     * Add 2 {@link Long} together.
//     *
//     * @param a first value
//     * @param b second value
//     * @return calculated value
//     * @since 0.8.0
//     */
//    public static Long add(final Long a, final Long b) {
//        if (null == a || null == b) {
//            return null;
//        }
//        return a + b;
//    }
    
	/**
     * Returns a {@link Double} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Double} from the {@link String} 
     */	
	public static Double getDouble(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value).doubleValue();
	}
	
	/**
     * Returns a {@link Integer} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Integer} from the {@link String} 
     */	
	public static Integer getInteger(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value).intValue();
	}
	
	/**
     * Returns a {@link Float} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Float} from the {@link String}
     */	
	public static Float getFloat(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value).floatValue();
	}
	
	/**
     * Returns a {@link Byte} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Byte} from the {@link String}
     */	
	public static Byte getByte(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value).byteValue();
	}
	
	/**
     * Returns a {@link Long} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Long} from the {@link String}
     */	
	public static Long getLong(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value).longValue();
	}
	
	/**
     * Returns a {@link Short} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Short} from the {@link String} 
     */	
	public static Short getShort(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value).shortValue();
	}
	
	/**
     * Returns a {@link BigInteger} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link BigInteger} from the {@link String} 
     */	
	public static BigInteger getBigInteger(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigInteger(value);
	}
	
	/**
     * Returns a {@link BigDecimal} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link BigDecimal} from the {@link String}
     */	
	public static BigDecimal getBigDecimal(final String text) {
		final String value = HelperString.getValidNumericString(text);
		
		return null == value ? null : new BigDecimal(value);
	}
	
	/**
     * Returns a {@link String} value from a {@link Number}.
     * 
     * @param number value to convert
     * @return {@link String} from the {@link Number}
     */	
	public static String getString(final Number number) {
		return null == number ? HelperString.EMPTY_STRING : number.toString();
	}
}
