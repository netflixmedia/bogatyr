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
package ch.customcode.bogatyr.model.unit;

import java.math.BigDecimal;

import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;

/**
 * Weight units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.7.0
 */
public enum Weight implements Unit<Weight> {
	MILLIGRAM(Constants.FACTOR_MILLIGRAM_TO_GRAM.multiply(Constants.FACTOR_GRAM_TO_KILOGRAM, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	GRAM(Constants.FACTOR_GRAM_TO_KILOGRAM), //$JUnit$
	KILOGRAM(BigDecimal.ONE), //$JUnit$
	OUNCE(Constants.FACTOR_OUNCE_TO_GRAM.multiply(Constants.FACTOR_GRAM_TO_KILOGRAM, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	POUND(Constants.FACTOR_POUND_TO_KILOGRAM), //$JUnit$
	TON(BigDecimal.ONE.divide(Constants.FACTOR_TON_TO_KILOGRAM, Constants.DEFAULT_MATHCONTEXT)); //$JUnit$

	private final BigDecimal factor;
	
	Weight(final BigDecimal factor) {
		this.factor = factor;
	}
	
	BigDecimal getFactor() {
		return factor;
	}

	
	/*
	 * Implemented methods
	 */
	@Override
	public BigDecimal convertTo(final Weight toUnit, final Number value) {
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
