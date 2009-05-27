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
package ch.sisprocom.bogatyr.helper;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * This is a helper class for numbers.
 *
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.7.0
 */
public abstract class HelperNumber {
    public static final int VALUE_8 = 8;
    public static final int VALUE_16 = 16;
    public static final int VALUE_32 = 32;
    public static final int VALUE_64 = 64;
    public static final int VALUE_128 = 128;
    public static final int VALUE_256 = 256;
    public static final int VALUE_512 = 512;
    public static final int VALUE_1024 = 1024;
    public static final int VALUE_2048 = 2048;
    public static final int VALUE_4096 = 4096;
    public static final int VALUE_8192 = 8192;
    public static final int VALUE_16384 = 16384;
    public static final int VALUE_32768 = 32768;
    public static final int VALUE_65536 = 65536;


    /**
     * Multiply 1-n {@link BigDecimal} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static BigDecimal multiply(final BigDecimal... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        BigDecimal result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = multiply(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Multiply 2 {@link BigDecimal} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static BigDecimal multiply(final BigDecimal a, final BigDecimal b) {
        if (a == null || b == null) {
            return null;
        }
        return a.multiply(b);
    }

    /**
     * Multiply 1-n {@link BigInteger} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static BigInteger multiply(final BigInteger... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        BigInteger result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = multiply(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Multiply 2 {@link BigInteger} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static BigInteger multiply(final BigInteger a, final BigInteger b) {
        if (a == null || b == null) {
            return null;
        }
        return a.multiply(b);
    }

    /**
     * Multiply 1-n {@link Integer} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static Integer multiply(final Integer... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Integer result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = multiply(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Multiply 2 {@link Integer} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static Integer multiply(final Integer a, final Integer b) {
        if (a == null || b == null) {
            return null;
        }
        return a * b;
    }

    /**
     * Multiply 1-n {@link Double} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static Double multiply(final Double... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Double result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = multiply(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Multiply 2 {@link Double} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static Double multiply(final Double a, final Double b) {
        if (a == null || b == null) {
            return null;
        }
        return a * b;
    }

    /**
     * Multiply 1-n {@link Float} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static Float multiply(final Float... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Float result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = multiply(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Multiply 2 {@link Float} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static Float multiply(final Float a, final Float b) {
        if (a == null || b == null) {
            return null;
        }
        return a * b;
    }

    /**
     * Multiply 1-n {@link Long} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.7.0
     */
    public static Long multiply(final Long... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Long result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = multiply(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Multiply 2 {@link Long} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.7.0
     */
    public static Long multiply(final Long a, final Long b) {
        if (a == null || b == null) {
            return null;
        }
        return a * b;
    }
    
    /**
     * Add 1-n {@link BigDecimal} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static BigDecimal add(final BigDecimal... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        BigDecimal result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = add(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Add 2 {@link BigDecimal} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static BigDecimal add(final BigDecimal a, final BigDecimal b) {
        if (a == null || b == null) {
            return null;
        }
        return a.add(b);
    }
    
    /**
     * Add 1-n {@link BigInteger} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static BigInteger add(final BigInteger... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        BigInteger result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = add(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Add 2 {@link BigInteger} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static BigInteger add(final BigInteger a, final BigInteger b) {
        if (a == null || b == null) {
            return null;
        }
        return a.add(b);
    }   
    
    /**
     * Add 1-n {@link Integer} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static Integer add(final Integer... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Integer result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = add(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Add 2 {@link Integer} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static Integer add(final Integer a, final Integer b) {
        if (a == null || b == null) {
            return null;
        }
        return a + b;
    }  
    
    /**
     * Add 1-n {@link Double} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static Double add(final Double... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Double result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = add(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Add 2 {@link Double} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static Double add(final Double a, final Double b) {
        if (a == null || b == null) {
            return null;
        }
        return a + b;
    }
    
    /**
     * Add 1-n {@link Float} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static Float add(final Float... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Float result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = add(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Add 2 {@link Float} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static Float add(final Float a, final Float b) {
        if (a == null || b == null) {
            return null;
        }
        return a + b;
    }
    
    /**
     * Add 1-n {@link Long} together.
     *
     * @param values 1-n values
     * @return calculated value
     * @since 0.8.0
     */
    public static Long add(final Long... values) {
        if (null == values) {
            throw new IllegalArgumentException("values is null!"); //$NON-NLS-1$
        }

        Long result = null;

        for (int ii = 0; ii < values.length; ii++) {
            if (0 == ii) {
                result = values[0];
            } else {
                result = add(result, values[ii]);
            }
        }
        return result;
    }

    /**
     * Add 2 {@link Long} together.
     *
     * @param a first value
     * @param b second value
     * @return calculated value
     * @since 0.8.0
     */
    public static Long add(final Long a, final Long b) {
        if (a == null || b == null) {
            return null;
        }
        return a + b;
    }
}
