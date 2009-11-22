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
package ch.sisprocom.bogatyr.model.unit;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.unit.UnitBit;

/**
 * Bit units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091115)
 * @since 0.9.0
 */
public enum Bit {
	BIT(1.0D),
	BYTE(1.0D/ UnitBit.FACTOR_BIT_TO_BYTE),
	KILOBIT(1.0D/UnitBit.FACTOR_BIT_TO_KILOBIT),
	MEGABIT(1.0D/UnitBit.FACTOR_BIT_TO_MEGABIT),
	GIGABIT(1.0D/UnitBit.FACTOR_BIT_TO_GIGABIT),
	TERABIT(1.0D/UnitBit.FACTOR_BIT_TO_TERABIT),
	PETABIT(1.0D/UnitBit.FACTOR_BIT_TO_PETABIT),
	EXABIT(1.0D/UnitBit.FACTOR_BIT_TO_EXABIT),
	ZETTABIT(1.0D/UnitBit.FACTOR_BIT_TO_ZETTABIT),
	YOTTABIT(1.0D/UnitBit.FACTOR_BIT_TO_YOTTABIT),
	KILOBYTE(1.0D/UnitBit.FACTOR_BIT_TO_KILOBYTE),
	MEGABYTE(1.0D/UnitBit.FACTOR_BIT_TO_MEGABYTE),
	GIGABYTE(1.0D/UnitBit.FACTOR_BIT_TO_GIGABYTE),
	TERABYTE(1.0D/UnitBit.FACTOR_BIT_TO_TERABYTE),
	PETABYTE(1.0D/UnitBit.FACTOR_BIT_TO_PETABYTE),
	EXABYTE(1.0D/UnitBit.FACTOR_BIT_TO_EXABYTE),
	ZETTABYTE(1.0D/UnitBit.FACTOR_BIT_TO_ZETTABYTE),
	YOTTABYTE(1.0D/UnitBit.FACTOR_BIT_TO_YOTTABYTE),
	KIBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_KIBIBIT),
	MEBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_MEBIBIT),
	GIBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_GIBIBIT),
	TEBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_TEBIBIT),
	PEBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_PEBIBIT),
	EXBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_EXBIBIT),
	ZEBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_ZEBIBIT),
	YOBIBIT(1.0D/UnitBit.FACTOR_BIT_TO_YOBIBIT),
	KIBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_KIBIBYTE),
	MEBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_MEBIBYTE),
	GIBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_GIBIBYTE),
	TEBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_TEBIBYTE),
	PEBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_PEBIBYTE),
	EXBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_EXBIBYTE),
	ZEBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_ZEBIBYTE),
	YOBIBYTE(1.0D/UnitBit.FACTOR_BIT_TO_YOBIBYTE);
	

	private final double factor;
	
	Bit(final double factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (bit)
     * 
     * @return factor to the base value
     * @since 0.9.0
     */
	public double getFactor() {
		return factor;
	}


    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}	

