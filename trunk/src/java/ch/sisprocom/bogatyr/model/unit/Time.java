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

import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * Time units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.8.0
 */
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

	private final BigDecimal factor;
	
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
	public <T extends Number> BigDecimal convertTo(final Time toUnit, final T value) {
		if (null == toUnit) {
			throw new RuntimeExceptionIsNull("toUnit"); //$NON-NLS-1$
		}
		if (null == value) {
			throw new RuntimeExceptionIsNull("value"); //$NON-NLS-1$
		}
		
		final BigDecimal intValue = new BigDecimal(value.toString());
		
		return toUnit == this ? intValue : intValue.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.factor, Constants.DEFAULT_MATHCONTEXT);
	}
//	@Override
//	public BigDecimal convertTo(final Time toUnit, final BigDecimal value) { //$JUnit$
//		if (null == toUnit) {
//			throw new RuntimeExceptionArgumentIsNull("toUnit"); //$NON-NLS-1$
//		}
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return toUnit == this ? value : value.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.factor, Constants.DEFAULT_MATHCONTEXT);
//    }	
//
//
//	@Override
//	public BigInteger convertTo(final Time toUnit, final BigInteger value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).toBigInteger();
//	}
//
//	@Override
//	public Byte convertTo(final Time toUnit, final Byte value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).byteValue();
//	}
//
//	@Override
//	public Double convertTo(final Time toUnit, final Double value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).doubleValue();
//	}
//
//	@Override
//	public Float convertTo(final Time toUnit, final Float value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).floatValue();
//	}
//
//	@Override
//	public Integer convertTo(final Time toUnit, final Integer value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).intValue();
//	}
//
//	@Override
//	public Long convertTo(final Time toUnit, final Long value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).longValue();
//	}
//
//	@Override
//	public Short convertTo(final Time toUnit, final Short value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).shortValue();
//	}	
}
