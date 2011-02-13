/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.model.unit;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

/**
 * Bit units
 *
 * @author Stefan Laubenberger
 * @version 0.9.5 (20110213)
 * @since 0.9.0
 */
@XmlRootElement(name = "bit")
public enum Bit implements Unit<Bit> {
	BIT(BigDecimal.ONE),
	BYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_BYTE, Constants.DEFAULT_MATHCONTEXT)),
	KILOBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_KILOBIT, Constants.DEFAULT_MATHCONTEXT)),
	MEGABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_MEGABIT, Constants.DEFAULT_MATHCONTEXT)),
	GIGABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_GIGABIT, Constants.DEFAULT_MATHCONTEXT)),
	TERABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_TERABIT, Constants.DEFAULT_MATHCONTEXT)),
	PETABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_PETABIT, Constants.DEFAULT_MATHCONTEXT)),
	EXABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_EXABIT, Constants.DEFAULT_MATHCONTEXT)),
	ZETTABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_ZETTABIT, Constants.DEFAULT_MATHCONTEXT)),
	YOTTABIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_YOTTABIT, Constants.DEFAULT_MATHCONTEXT)),
	KILOBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_KILOBYTE, Constants.DEFAULT_MATHCONTEXT)),
	MEGABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_MEGABYTE, Constants.DEFAULT_MATHCONTEXT)),
	GIGABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_GIGABYTE, Constants.DEFAULT_MATHCONTEXT)),
	TERABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_TERABYTE, Constants.DEFAULT_MATHCONTEXT)),
	PETABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_PETABYTE, Constants.DEFAULT_MATHCONTEXT)),
	EXABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_EXABYTE, Constants.DEFAULT_MATHCONTEXT)),
	ZETTABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_ZETTABYTE, Constants.DEFAULT_MATHCONTEXT)),
	YOTTABYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_YOTTABYTE, Constants.DEFAULT_MATHCONTEXT)),
	KIBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_KIBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	MEBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_MEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	GIBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_GIBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	TEBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_TEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	PEBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_PEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	EXBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_EXBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	ZEBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_ZEBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	YOBIBIT(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_YOBIBIT, Constants.DEFAULT_MATHCONTEXT)),
	KIBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_KIBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	MEBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_MEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	GIBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_GIBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	TEBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_TEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	PEBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_PEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	EXBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_EXBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	ZEBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_ZEBIBYTE, Constants.DEFAULT_MATHCONTEXT)),
	YOBIBYTE(BigDecimal.ONE.divide(Constants.FACTOR_BIT_TO_YOBIBYTE, Constants.DEFAULT_MATHCONTEXT));


	final BigDecimal factor;

	Bit(final BigDecimal factor) {
		this.factor = factor;
	}

	BigDecimal getFactor() {
		return factor;
	}


	/*
	 * Implemented methods
	 */

	@Override
	public BigDecimal convertTo(final Bit toUnit, final Number value) { //$JUnit$
		if (null == toUnit) {
			throw new RuntimeExceptionIsNull("toUnit"); //$NON-NLS-1$
		}
		if (null == value) {
			throw new RuntimeExceptionIsNull("value"); //$NON-NLS-1$
		}

		final BigDecimal internalValue = new BigDecimal(value.toString());

		return toUnit == this ? internalValue : internalValue.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.factor, Constants.DEFAULT_MATHCONTEXT);
	}
}	

