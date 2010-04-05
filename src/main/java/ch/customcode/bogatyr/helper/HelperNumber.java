/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This is a helper class for numbers.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.7.0
 */
public abstract class HelperNumber {
	private static final Logger log = LoggerFactory.getLogger(HelperNumber.class);
	
//    public static final BigDecimal NUMBER_0 	  = BigDecimal.ZERO;
//    public static final BigDecimal NUMBER_1 	  = BigDecimal.ONE;
//    public static final BigDecimal NUMBER_2 	  = new BigDecimal("2"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_3 	  = new BigDecimal("3"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_4 	  = new BigDecimal("4"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_5 	  = new BigDecimal("5"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_6 	  = new BigDecimal("6"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_7 	  = new BigDecimal("7"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_8 	  = new BigDecimal("8"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_9 	  = new BigDecimal("9"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_10 	  = BigDecimal.TEN;
    public static final BigDecimal NUMBER_100 	  = new BigDecimal("100"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1000 	  = new BigDecimal("1000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_10000   = new BigDecimal("10000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_100000  = new BigDecimal("100000"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1000000 = new BigDecimal("1000000"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_15 	  = new BigDecimal("15"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16 	  = new BigDecimal("16"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_31 	  = new BigDecimal("31"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_32 	  = new BigDecimal("32"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_63 	  = new BigDecimal("63"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_64 	  = new BigDecimal("64"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_127 	  = new BigDecimal("127"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_128 	  = new BigDecimal("128"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_255 	  = new BigDecimal("255"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_256 	  = new BigDecimal("256"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_511 	  = new BigDecimal("511"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_512 	  = new BigDecimal("512"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_1023 	  = new BigDecimal("1023"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_1024 	  = new BigDecimal("1024"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_2047 	  = new BigDecimal("2047"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_2048 	  = new BigDecimal("2048"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_4095 	  = new BigDecimal("4095"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_4096 	  = new BigDecimal("4096"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_8191 	  = new BigDecimal("8191"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_8192 	  = new BigDecimal("8192"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_16383   = new BigDecimal("16383"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16384   = new BigDecimal("16384"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_32767   = new BigDecimal("32767"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_32768   = new BigDecimal("32768"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_65535   = new BigDecimal("65535"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_65536   = new BigDecimal("65536"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_131071   = new BigDecimal("131071"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_131072   = new BigDecimal("131072"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_262143   = new BigDecimal("262143"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_262144   = new BigDecimal("262144"); //$NON-NLS-1$
//    public static final BigDecimal NUMBER_16777215 = new BigDecimal("16777215"); //$NON-NLS-1$
    public static final BigDecimal NUMBER_16777216 = new BigDecimal("16777216"); //$NON-NLS-1$
    
 
    /**
     * Multiply 1-n values together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static BigDecimal multiply(final Number... values) {
    	log.debug(HelperLog.methodStart(values));
    	
		if (!HelperArray.isValid(values)) {
			throw new RuntimeExceptionIsNullOrEmpty("values"); //$NON-NLS-1$
		}

        BigDecimal result = null;

        for (int ii = 0; ii < values.length; ii++) {
            result = new BigDecimal((0 == ii ? values[0] : multiply(result, values[ii])).toString());
        }
        log.debug(HelperLog.methodExit(result));
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
    public static BigDecimal multiply(final Number a, final Number b) {
    	log.debug(HelperLog.methodStart(a, b));
		if (null == a) {
			throw new RuntimeExceptionIsNull("a"); //$NON-NLS-1$
		}
		if (null == b ) {
			throw new RuntimeExceptionIsNull("b"); //$NON-NLS-1$
		}
       
		final BigDecimal numberA = new BigDecimal(a.toString());
		final BigDecimal numberB = new BigDecimal(b.toString());
		
		final BigDecimal result = numberA.multiply(numberB, Constants.DEFAULT_MATHCONTEXT);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

    /**
     * Add 1-n values together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static BigDecimal add(final Number... values) {
    	log.debug(HelperLog.methodStart(values));
		if (!HelperArray.isValid(values)) {
			throw new RuntimeExceptionIsNullOrEmpty("values"); //$NON-NLS-1$
		}

        BigDecimal result = null;

        for (int ii = 0; ii < values.length; ii++) {
            result = new BigDecimal((0 == ii ? values[0] : add(result, values[ii])).toString());
        }
        log.debug(HelperLog.methodExit(result));
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
    public static BigDecimal add(final Number a, final Number b) {
    	log.debug(HelperLog.methodStart(a, b));
		if (null == a) {
			throw new RuntimeExceptionIsNull("a"); //$NON-NLS-1$
		}
		if (null == b ) {
			throw new RuntimeExceptionIsNull("b"); //$NON-NLS-1$
		}
		
		final BigDecimal numberA = new BigDecimal(a.toString());
		final BigDecimal numberB = new BigDecimal(b.toString());
		
		final BigDecimal result = numberA.add(numberB);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
    
	/**
     * Returns a {@link Double} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Double} from the {@link String} 
     */	
	public static Double getDouble(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final Double result = null == value ? null : new BigDecimal(value).doubleValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link Integer} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Integer} from the {@link String} 
     */	
	public static Integer getInteger(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final Integer result = null == value ? null : new BigDecimal(value).intValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link Float} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Float} from the {@link String}
     */	
	public static Float getFloat(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final Float result = null == value ? null : new BigDecimal(value).floatValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link Byte} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Byte} from the {@link String}
     */	
	public static Byte getByte(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final Byte result = null == value ? null : new BigDecimal(value).byteValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link Long} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Long} from the {@link String}
     */	
	public static Long getLong(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final Long result = null == value ? null : new BigDecimal(value).longValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link Short} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link Short} from the {@link String} 
     */	
	public static Short getShort(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final Short result = null == value ? null : new BigDecimal(value).shortValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link BigInteger} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link BigInteger} from the {@link String} 
     */	
	public static BigInteger getBigInteger(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final BigInteger result = null == value ? null : new BigInteger(value);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link BigDecimal} value from a {@link String}.
     * 
     * @param text value to convert
     * @return {@link BigDecimal} from the {@link String}
     */	
	public static BigDecimal getBigDecimal(final String text) {
		log.debug(HelperLog.methodStart(text));
		
		final String value = HelperString.getValidNumericString(text);
		final BigDecimal result = null == value ? null : new BigDecimal(value);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
     * Returns a {@link String} value from a {@link Number}.
     * 
     * @param number value to convert
     * @return {@link String} from the {@link Number}
     */	
	public static String getString(final Number number) {
		log.debug(HelperLog.methodStart(number));
		
		final String result = null == number ? HelperString.EMPTY_STRING : number.toString();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
