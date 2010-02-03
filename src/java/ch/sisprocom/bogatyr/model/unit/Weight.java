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
package ch.sisprocom.bogatyr.model.unit;

import java.math.BigDecimal;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;

/**
 * Weight units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100202)
 * @since 0.7.0
 */
public enum Weight {
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
	
	/**
     * Converts a weight value to another unit.
     * 
     * @param toUnit resulting weight unit
     * @param value in the given unit
     * @return value in the resulting unit
     * @since 0.9.0
     */
	public BigDecimal convertTo(final Weight toUnit, final BigDecimal value) { //$JUnit$
		if (null == toUnit) {
			throw new RuntimeExceptionArgumentIsNull("toUnit"); //$NON-NLS-1$
		}
		if (null == value) {
			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
		}

		return toUnit == this ? value : value.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.factor, Constants.DEFAULT_MATHCONTEXT);
    }	

	BigDecimal getFactor() {
		return factor;
	}
}

