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
 * Volume units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.7.0
 */
public enum Volume implements Unit<Volume> {
	MM3(Constants.FACTOR_MM3_TO_CM3.multiply(Constants.FACTOR_CM3_TO_L, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	CM3(Constants.FACTOR_CM3_TO_L), //$JUnit$
	L(BigDecimal.ONE),
	M3(BigDecimal.ONE.divide(Constants.FACTOR_L_TO_M3, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	PINT(Constants.FACTOR_PINT_TO_CM3.multiply(Constants.FACTOR_CM3_TO_L, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	QUART(Constants.FACTOR_QUART_TO_L), //$JUnit$
	GALLON_US(Constants.FACTOR_GALLON_US_TO_L), //$JUnit$
	BARREL(Constants.FACTOR_BARREL_TO_L); //$JUnit$

	private final BigDecimal factor;
	
	Volume(final BigDecimal factor) {
		this.factor = factor;
	}

	BigDecimal getFactor() {
		return factor;
	}

	
	/*
	 * Implemented methods
	 */
	@Override
	public BigDecimal convertTo(final Volume toUnit, final Number value) {
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

