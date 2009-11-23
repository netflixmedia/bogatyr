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

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.unit.UnitArea;

import java.math.BigDecimal;

/**
 * Area units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091123)
 * @since 0.7.0
 */
public enum Area {
	MM2(UnitArea.FACTOR_MM2_TO_CM2.multiply(UnitArea.FACTOR_CM2_TO_M2, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	CM2(UnitArea.FACTOR_CM2_TO_M2), //$JUnit$
	M2(BigDecimal.ONE),
	AREA(BigDecimal.ONE.divide(UnitArea.FACTOR_M2_TO_AREA, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	HECTARE(BigDecimal.ONE.divide((UnitArea.FACTOR_M2_TO_AREA.multiply(UnitArea.FACTOR_AREA_TO_HECTARE, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	KM2(BigDecimal.ONE.divide((UnitArea.FACTOR_M2_TO_AREA.multiply(UnitArea.FACTOR_AREA_TO_HECTARE, Constants.DEFAULT_MATHCONTEXT).multiply(UnitArea.FACTOR_HECTARE_TO_KM2, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	FOOT2(UnitArea.FACTOR_FOOT2_TO_M2), //$JUnit$
	YARD2(UnitArea.FACTOR_YARD2_TO_M2), //$JUnit$
	PERCH(UnitArea.FACTOR_PERCH_TO_M2), //$JUnit$
	ACRE(UnitArea.FACTOR_ACRE_TO_M2), //$JUnit$
	MILE2(BigDecimal.ONE.divide((UnitArea.FACTOR_M2_TO_AREA.multiply(UnitArea.FACTOR_AREA_TO_HECTARE, Constants.DEFAULT_MATHCONTEXT).multiply(UnitArea.FACTOR_HECTARE_TO_KM2, Constants.DEFAULT_MATHCONTEXT).multiply(UnitArea.FACTOR_MILE2_TO_KM2, Constants.DEFAULT_MATHCONTEXT)), Constants.DEFAULT_MATHCONTEXT)); //$JUnit$

	private final BigDecimal factor;
	
	Area(final BigDecimal factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (meter^2)
     * 
     * @return factor to the base value
     * @since 0.8.0
     */
	public BigDecimal getFactor() {
		return factor;
	}


    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}	

