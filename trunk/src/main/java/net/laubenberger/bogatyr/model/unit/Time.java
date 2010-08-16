/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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
 * Time units
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.8.0
 */
@XmlRootElement(name = "time")
public enum Time implements Unit<Time> {
	NANOSECOND(Constants.FACTOR_NANOSECOND_TO_SECOND),
	MICROSECOND(Constants.FACTOR_MICROSECOND_TO_SECOND),
	MILLISECOND(Constants.FACTOR_MILLISECOND_TO_SECOND),
	SECOND(BigDecimal.ONE),
	MINUTE(BigDecimal.ONE.divide(Constants.FACTOR_SECOND_TO_MINUTE, Constants.DEFAULT_MATHCONTEXT)),
	HOUR(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)),
	DAY(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY), Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)),
	WEEK(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY.multiply(Constants.FACTOR_DAY_TO_WEEK)), Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)),
	MONTH(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY.multiply(Constants.FACTOR_DAY_TO_MONTH), Constants.DEFAULT_MATHCONTEXT), Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)),
	YEAR(BigDecimal.ONE.divide((Constants.FACTOR_SECOND_TO_MINUTE.multiply(Constants.FACTOR_MINUTE_TO_HOUR.multiply(Constants.FACTOR_HOUR_TO_DAY.multiply(Constants.FACTOR_DAY_TO_YEAR), Constants.DEFAULT_MATHCONTEXT), Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT));

	final BigDecimal factor;

	Time(final BigDecimal factor) {
		this.factor = factor;
	}

	BigDecimal getFactor() {
		return factor;
	}


	/*
	 * Implemented methods
	 */

	@Override
	public BigDecimal convertTo(final Time toUnit, final Number value) {
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
