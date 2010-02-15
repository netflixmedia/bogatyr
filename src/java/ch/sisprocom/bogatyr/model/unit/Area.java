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
 * Area units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.7.0
 */
public enum Area implements Unit<Area> {
	MM2(Constants.FACTOR_MM2_TO_CM2.multiply(Constants.FACTOR_CM2_TO_M2, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	CM2(Constants.FACTOR_CM2_TO_M2), //$JUnit$
	M2(BigDecimal.ONE),
	AREA(BigDecimal.ONE.divide(Constants.FACTOR_M2_TO_AREA, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	HECTARE(BigDecimal.ONE.divide((Constants.FACTOR_M2_TO_AREA.multiply(Constants.FACTOR_AREA_TO_HECTARE, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	KM2(BigDecimal.ONE.divide((Constants.FACTOR_M2_TO_AREA.multiply(Constants.FACTOR_AREA_TO_HECTARE, Constants.DEFAULT_MATHCONTEXT).multiply(Constants.FACTOR_HECTARE_TO_KM2, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	FOOT2(Constants.FACTOR_FOOT2_TO_M2), //$JUnit$
	YARD2(Constants.FACTOR_YARD2_TO_M2), //$JUnit$
	PERCH(Constants.FACTOR_PERCH_TO_M2), //$JUnit$
	ACRE(Constants.FACTOR_ACRE_TO_M2), //$JUnit$
	MILE2(BigDecimal.ONE.divide((Constants.FACTOR_M2_TO_AREA.multiply(Constants.FACTOR_AREA_TO_HECTARE, Constants.DEFAULT_MATHCONTEXT).multiply(Constants.FACTOR_HECTARE_TO_KM2, Constants.DEFAULT_MATHCONTEXT).multiply(Constants.FACTOR_MILE2_TO_KM2, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)); //$JUnit$

	private final BigDecimal factor;
	
	Area(final BigDecimal factor) {
		this.factor = factor;
	}
    
	BigDecimal getFactor() {
		return factor;
	}
	
	
	/*
	 * Implemented methods
	 */
	@Override
	public BigDecimal convertTo(final Area toUnit, final Number value) {
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
//	public BigDecimal convertTo(final Area toUnit, final BigDecimal value) { //$JUnit$
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
//
//	@Override
//	public BigInteger convertTo(final Area toUnit, final BigInteger value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).toBigInteger();
//	}
//
//	@Override
//	public Byte convertTo(final Area toUnit, final Byte value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).byteValue();
//	}
//
//	@Override
//	public Double convertTo(final Area toUnit, final Double value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).doubleValue();
//	}
//
//	@Override
//	public Float convertTo(final Area toUnit, final Float value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).floatValue();
//	}
//
//	@Override
//	public Integer convertTo(final Area toUnit, final Integer value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).intValue();
//	}
//
//	@Override
//	public Long convertTo(final Area toUnit, final Long value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).longValue();
//	}
//
//	@Override
//	public Short convertTo(final Area toUnit, final Short value) {
//		if (null == value) {
//			throw new RuntimeExceptionArgumentIsNull("value"); //$NON-NLS-1$
//		}
//
//		return convertTo(toUnit, new BigDecimal(value)).shortValue();
//	}	
}	

