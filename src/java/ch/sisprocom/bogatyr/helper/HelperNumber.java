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
 * @version 0.9.0 (20100212)
 * @since 0.7.0
 */
public abstract class HelperNumber {
    public static final BigDecimal NUMBER_0 	  = BigDecimal.ZERO;
    public static final BigDecimal NUMBER_1 	  = BigDecimal.ONE;
    public static final BigDecimal NUMBER_2 	  = new BigDecimal("2"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_3 	  = new BigDecimal("3"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_4 	  = new BigDecimal("4"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_5 	  = new BigDecimal("5"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_6 	  = new BigDecimal("6"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_7 	  = new BigDecimal("7"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_8 	  = new BigDecimal("8"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_9 	  = new BigDecimal("9"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_10 	  = BigDecimal.TEN;
    public static final BigDecimal NUMBER_100 	  = new BigDecimal("100"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1000 	  = new BigDecimal("1000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_10000   = new BigDecimal("10000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_100000  = new BigDecimal("100000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1000000 = new BigDecimal("1000000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_15 	  = new BigDecimal("15"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16 	  = new BigDecimal("16"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_31 	  = new BigDecimal("31"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_32 	  = new BigDecimal("32"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_63 	  = new BigDecimal("63"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_64 	  = new BigDecimal("64"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_127 	  = new BigDecimal("127"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_128 	  = new BigDecimal("128"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_255 	  = new BigDecimal("255"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_256 	  = new BigDecimal("256"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_511 	  = new BigDecimal("511"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_512 	  = new BigDecimal("512"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1023 	  = new BigDecimal("1023"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1024 	  = new BigDecimal("1024"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_2047 	  = new BigDecimal("2047"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_2048 	  = new BigDecimal("2048"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_4095 	  = new BigDecimal("4095"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_4096 	  = new BigDecimal("4096"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_8191 	  = new BigDecimal("8191"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_8192 	  = new BigDecimal("8192"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16383   = new BigDecimal("16383"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16384   = new BigDecimal("16384"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_32767   = new BigDecimal("32767"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_32768   = new BigDecimal("32768"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_65535   = new BigDecimal("65535"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_65536   = new BigDecimal("65536"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_131071   = new BigDecimal("131071"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_131072   = new BigDecimal("131072"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_262143   = new BigDecimal("262143"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_262144   = new BigDecimal("262144"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16777215 = new BigDecimal("16777215"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16777216 = new BigDecimal("16777216"); //$NON-NLS-1$
    
 
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
