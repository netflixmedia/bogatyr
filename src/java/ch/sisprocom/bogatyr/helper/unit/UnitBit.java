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

import ch.sisprocom.bogatyr.model.unit.Bit;


/**
 * Converts different units of bit.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091115)
 * @since 0.9.0
 */
public abstract class UnitBit {
	public static final double FACTOR_BIT_TO_BYTE = 8.0D; //bit to byte

	public static final double FACTOR_BIT_TO_KILOBIT  = 10E2;
	public static final double FACTOR_BIT_TO_MEGABIT  = 10E5;
	public static final double FACTOR_BIT_TO_GIGABIT  = 10E8;
	public static final double FACTOR_BIT_TO_TERABIT  = 10E11;
	public static final double FACTOR_BIT_TO_PETABIT  = 10E14;
	public static final double FACTOR_BIT_TO_EXABIT   = 10E17;
	public static final double FACTOR_BIT_TO_ZETTABIT = 10E20;
	public static final double FACTOR_BIT_TO_YOTTABIT = 10E23;
	
	public static final double FACTOR_BIT_TO_KILOBYTE  = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_KILOBIT;
	public static final double FACTOR_BIT_TO_MEGABYTE  = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_MEGABIT;
	public static final double FACTOR_BIT_TO_GIGABYTE  = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_GIGABIT;
	public static final double FACTOR_BIT_TO_TERABYTE  = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_TERABIT;
	public static final double FACTOR_BIT_TO_PETABYTE  = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_PETABIT;
	public static final double FACTOR_BIT_TO_EXABYTE   = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_EXABIT;
	public static final double FACTOR_BIT_TO_ZETTABYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_ZETTABIT;
	public static final double FACTOR_BIT_TO_YOTTABYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_YOTTABIT;

	public static final double FACTOR_BIT_TO_KIBIBIT = 1024.0D;
	public static final double FACTOR_BIT_TO_MEBIBIT = FACTOR_BIT_TO_KIBIBIT * 1024.0D;
	public static final double FACTOR_BIT_TO_GIBIBIT = FACTOR_BIT_TO_MEBIBIT * 1024.0D;
	public static final double FACTOR_BIT_TO_TEBIBIT = FACTOR_BIT_TO_GIBIBIT * 1024.0D;
	public static final double FACTOR_BIT_TO_PEBIBIT = FACTOR_BIT_TO_TEBIBIT * 1024.0D;
	public static final double FACTOR_BIT_TO_EXBIBIT = FACTOR_BIT_TO_PEBIBIT * 1024.0D;
	public static final double FACTOR_BIT_TO_ZEBIBIT = FACTOR_BIT_TO_EXBIBIT * 1024.0D;
	public static final double FACTOR_BIT_TO_YOBIBIT = FACTOR_BIT_TO_ZEBIBIT * 1024.0D;
	
	public static final double FACTOR_BIT_TO_KIBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_KIBIBIT;
	public static final double FACTOR_BIT_TO_MEBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_MEBIBIT;
	public static final double FACTOR_BIT_TO_GIBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_GIBIBIT;
	public static final double FACTOR_BIT_TO_TEBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_TEBIBIT;
	public static final double FACTOR_BIT_TO_PEBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_PEBIBIT;
	public static final double FACTOR_BIT_TO_EXBIBYTE  = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_EXBIBIT;
	public static final double FACTOR_BIT_TO_ZEBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_ZEBIBIT;
	public static final double FACTOR_BIT_TO_YOBIBYTE = FACTOR_BIT_TO_BYTE * FACTOR_BIT_TO_YOBIBIT;
	
	
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit bit unit
     * @param toUnit bit unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.9.0
     */
    public static double convert(final Bit fromUnit, final Bit toUnit, final double value) { //$JUnit$
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}

    	return value / fromUnit.getFactor() * toUnit.getFactor(); 
    }
}
