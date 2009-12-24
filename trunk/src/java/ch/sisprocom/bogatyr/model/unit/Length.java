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
import ch.sisprocom.bogatyr.helper.unit.UnitLength;

/**
 * Length units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091224)
 * @since 0.7.0
 */
public enum Length implements Unit {
	MM(UnitLength.FACTOR_MM_TO_CM.multiply(UnitLength.FACTOR_CM_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	CM(UnitLength.FACTOR_CM_TO_M), //$JUnit$
	M(BigDecimal.ONE),
	KM(BigDecimal.ONE.divide(UnitLength.FACTOR_M_TO_KM, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	INCH(UnitLength.FACTOR_CM_TO_M.divide(UnitLength.FACTOR_INCH_TO_CM, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	FOOT(BigDecimal.ONE.divide(UnitLength.FACTOR_FOOT_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	YARD(BigDecimal.ONE.divide(UnitLength.FACTOR_YARD_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	MILE(BigDecimal.ONE.divide(UnitLength.FACTOR_MILE_TO_M, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	NAUTICAL_MILE(BigDecimal.ONE.divide(UnitLength.FACTOR_NAUTICAL_MILE_TO_M, Constants.DEFAULT_MATHCONTEXT)); //$JUnit$

	private final BigDecimal factor;
	
	Length(final BigDecimal factor) {
		this.factor = factor;
	}

	
	/*
	 * Implemented methods
	 */
	@Override
	public BigDecimal getFactor() {
		return factor;
	}
}

