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
 * Length units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.7.0
 */
public enum Length implements Unit<Length> {
	MM(Constants.FACTOR_MM_TO_CM.multiply(Constants.FACTOR_CM_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	CM(Constants.FACTOR_CM_TO_M), //$JUnit$
	M(BigDecimal.ONE),
	KM(BigDecimal.ONE.divide(Constants.FACTOR_M_TO_KM, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	INCH(Constants.FACTOR_CM_TO_M.divide(Constants.FACTOR_INCH_TO_CM, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	FOOT(BigDecimal.ONE.divide(Constants.FACTOR_FOOT_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	YARD(BigDecimal.ONE.divide(Constants.FACTOR_YARD_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	MILE(BigDecimal.ONE.divide(Constants.FACTOR_MILE_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	NAUTICAL_MILE(BigDecimal.ONE.divide(Constants.FACTOR_NAUTICAL_MILE_TO_M, Constants.DEFAULT_MATHCONTEXT)); //$JUnit$

	private final BigDecimal factor;
	
	Length(final BigDecimal factor) {
		this.factor = factor;
	}
	
	BigDecimal getFactor() {
		return factor;
	}

	
	/*
	 * Implemented methods
	 */
	@Override
	public BigDecimal convertTo(final Length toUnit, final Number value) {
		if (null == toUnit) {
			throw new RuntimeExceptionIsNull("toUnit"); //$NON-NLS-1$
		}
		if (null == value) {
			throw new RuntimeExceptionIsNull("value"); //$NON-NLS-1$
		}
		
		final BigDecimal intValue = new BigDecimal(value.toString());
		
		return toUnit == this ? intValue : intValue.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.factor, Constants.DEFAULT_MATHCONTEXT);
	}
//	
//	@Override
//	public BigDecimal convertTo(final Length toUnit, final BigDecimal value) { //$JUnit$
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
//	public BigInteger convertTo(final Length toUnit, final BigInteger value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).toBigInteger();
//	}
//
//	@Override
//	public Byte convertTo(final Length toUnit, final Byte value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).byteValue();
//	}
//
//	@Override
//	public Double convertTo(final Length toUnit, final Double value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).doubleValue();
//	}
//
//	@Override
//	public Float convertTo(final Length toUnit, final Float value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).floatValue();
//	}
//
//	@Override
//	public Integer convertTo(final Length toUnit, final Integer value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).intValue();
//	}
//
//	@Override
//	public Long convertTo(final Length toUnit, final Long value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).longValue();
//	}
//
//	@Override
//	public Short convertTo(final Length toUnit, final Short value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).shortValue();
//	}	
}

