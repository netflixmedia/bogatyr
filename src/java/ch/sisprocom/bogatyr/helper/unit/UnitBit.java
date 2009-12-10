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
package ch.sisprocom.bogatyr.helper.unit;

import java.math.BigDecimal;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.model.unit.Bit;


/**
 * Converts different units of bit.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.9.0
 */
public abstract class UnitBit extends UnitConverter {
	public static final BigDecimal FACTOR_BIT_TO_BYTE = HelperNumber.BIGDECIMAL_8; //bit to byte

	public static final BigDecimal FACTOR_BIT_TO_KILOBIT  = new BigDecimal("10E2"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_MEGABIT  = new BigDecimal("10E5"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_GIGABIT  = new BigDecimal("10E8"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_TERABIT  = new BigDecimal("10E11"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_PETABIT  = new BigDecimal("10E14"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_EXABIT   = new BigDecimal("10E17"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_ZETTABIT = new BigDecimal("10E20"); //$NON-NLS-1$
	public static final BigDecimal FACTOR_BIT_TO_YOTTABIT = new BigDecimal("10E23"); //$NON-NLS-1$
	
	public static final BigDecimal FACTOR_BIT_TO_KILOBYTE  = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_KILOBIT);
	public static final BigDecimal FACTOR_BIT_TO_MEGABYTE  = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_MEGABIT);
	public static final BigDecimal FACTOR_BIT_TO_GIGABYTE  = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_GIGABIT);
	public static final BigDecimal FACTOR_BIT_TO_TERABYTE  = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_TERABIT);
	public static final BigDecimal FACTOR_BIT_TO_PETABYTE  = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_PETABIT);
	public static final BigDecimal FACTOR_BIT_TO_EXABYTE   = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_EXABIT);
	public static final BigDecimal FACTOR_BIT_TO_ZETTABYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_ZETTABIT);
	public static final BigDecimal FACTOR_BIT_TO_YOTTABYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_YOTTABIT);

	public static final BigDecimal FACTOR_BIT_TO_KIBIBIT = HelperNumber.BIGDECIMAL_1024;
	public static final BigDecimal FACTOR_BIT_TO_MEBIBIT = FACTOR_BIT_TO_KIBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	public static final BigDecimal FACTOR_BIT_TO_GIBIBIT = FACTOR_BIT_TO_MEBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	public static final BigDecimal FACTOR_BIT_TO_TEBIBIT = FACTOR_BIT_TO_GIBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	public static final BigDecimal FACTOR_BIT_TO_PEBIBIT = FACTOR_BIT_TO_TEBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	public static final BigDecimal FACTOR_BIT_TO_EXBIBIT = FACTOR_BIT_TO_PEBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	public static final BigDecimal FACTOR_BIT_TO_ZEBIBIT = FACTOR_BIT_TO_EXBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	public static final BigDecimal FACTOR_BIT_TO_YOBIBIT = FACTOR_BIT_TO_ZEBIBIT.multiply(HelperNumber.BIGDECIMAL_1024);
	
	public static final BigDecimal FACTOR_BIT_TO_KIBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_KIBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_MEBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_MEBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_GIBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_GIBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_TEBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_TEBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_PEBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_PEBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_EXBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_EXBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_ZEBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_ZEBIBIT);
	public static final BigDecimal FACTOR_BIT_TO_YOBIBYTE = FACTOR_BIT_TO_BYTE.multiply(FACTOR_BIT_TO_YOBIBIT);
	
	
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit bit unit
     * @param toUnit bit unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.9.0
     */
    public static BigDecimal convert(final Bit fromUnit, final Bit toUnit, final BigDecimal value) {
    	return UnitConverter.convert(fromUnit, toUnit, value); 
    }
}
