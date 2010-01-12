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
 * Area units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100112)
 * @since 0.7.0
 */
public enum Area {
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
    
	/**
     * Converts an area value to another unit.
     * 
     * @param toUnit resulting area unit
     * @param value in the given unit
     * @return value in the resulting unit
     * @since 0.9.0
     */
	public BigDecimal convertTo(final Area toUnit, final BigDecimal value) { //$JUnit$
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

