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


/**
 * Time units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100112)
 * @since 0.8.0
 */
public enum Time {
	NANOSECOND(Constants.FACTOR_NANOSECOND_TO_SECOND),
	MICROSECOND(Constants.FACTOR_MICROSECOND_TO_SECOND),
	MILLISECOND(Constants.FACTOR_MILLISECOND_TO_SECOND),
	SECOND(BigDecimal.ONE),
	MINUTE(BigDecimal.ONE.divide(Constants.FACTOR_SECOND_TO_MINUTE)),
	HOUR(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR)))),
	DAY(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY))))),
	WEEK(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY.multiply(Constants.FACTOR_DAY_TO_WEEK)))))),
	MONTH(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY.multiply(Constants.FACTOR_DAY_TO_MONTH)))))),
	YEAR(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY.multiply(Constants.FACTOR_DAY_TO_YEAR))))));

	private final BigDecimal factor;
	
	Time(final BigDecimal factor) {
		this.factor = factor;
	}
	
	/**
     * Converts a time value to another unit.
     * 
     * @param toUnit resulting time unit
     * @param value in the given unit
     * @return value in the resulting unit
     * @since 0.9.0
     */
	public BigDecimal convertTo(final Time toUnit, final BigDecimal value) { //$JUnit$
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}
		if (null == value) {
			throw new IllegalArgumentException("value is null!"); //$NON-NLS-1$
		}

		return toUnit == this ? value : value.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.getFactor(), Constants.DEFAULT_MATHCONTEXT); 
    }	

	BigDecimal getFactor() {
		return factor;
	}
}
