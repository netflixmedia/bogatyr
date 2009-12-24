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

import java.math.BigDecimal;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.unit.UnitBit;

/**
 * Bit units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091224)
 * @since 0.9.0
 */
public enum Bit implements Unit {
	BIT(BigDecimal.ONE),
	BYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_BYTE, Constants.DEFAULT_MATHCONTEXT)),
	KILOBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_KILOBIT, Constants.DEFAULT_MATHCONTEXT)),
	MEGABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_MEGABIT, Constants.DEFAULT_MATHCONTEXT)),
	GIGABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_GIGABIT, Constants.DEFAULT_MATHCONTEXT)),
	TERABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_TERABIT, Constants.DEFAULT_MATHCONTEXT)),
	PETABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_PETABIT, Constants.DEFAULT_MATHCONTEXT)),
	EXABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_EXABIT, Constants.DEFAULT_MATHCONTEXT)),
	ZETTABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_ZETTABIT, Constants.DEFAULT_MATHCONTEXT)),
	YOTTABIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_YOTTABIT, Constants.DEFAULT_MATHCONTEXT)),
	KILOBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_KILOBYTE, Constants.DEFAULT_MATHCONTEXT)),
	MEGABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_MEGABYTE, Constants.DEFAULT_MATHCONTEXT)),
	GIGABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_GIGABYTE, Constants.DEFAULT_MATHCONTEXT)),
	TERABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_TERABYTE, Constants.DEFAULT_MATHCONTEXT)),
	PETABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_PETABYTE, Constants.DEFAULT_MATHCONTEXT)),
	EXABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_EXABYTE, Constants.DEFAULT_MATHCONTEXT)),
	ZETTABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_ZETTABYTE, Constants.DEFAULT_MATHCONTEXT)),
	YOTTABYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_YOTTABYTE, Constants.DEFAULT_MATHCONTEXT)),
	KIBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_KIBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	MEBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_MEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	GIBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_GIBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	TEBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_TEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	PEBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_PEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	EXBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_EXBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	ZEBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_ZEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	YOBIBIT(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_YOBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	KIBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_KIBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	MEBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_MEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	GIBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_GIBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	TEBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_TEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	PEBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_PEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	EXBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_EXBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	ZEBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_ZEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	YOBIBYTE(BigDecimal.ONE.divide(UnitBit.FACTOR_BIT_TO_YOBIBYTE, Constants.DEFAULT_MATHCONTEXT));
	

	private final BigDecimal factor;
	
	Bit(final BigDecimal factor) {
		this.factor = factor;
	}
	
	
	/*
	 * Implemented methods
	 */
	@Override
	public BigDecimal getFactor() {
		return factor;
	}
}	

