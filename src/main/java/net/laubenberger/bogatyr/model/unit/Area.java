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
 * Area units
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.7.0
 */
@XmlRootElement(name = "area")
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

	final BigDecimal factor;

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

		final BigDecimal internalValue = new BigDecimal(value.toString());

		return toUnit == this ? internalValue : internalValue.divide(factor, Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.factor, Constants.DEFAULT_MATHCONTEXT);
	}
}	

