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
import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.unit.UnitVolume;

/**
 * Volume units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091026)
 * @since 0.7.0
 */
public enum Volume {
	MM3(UnitVolume.FACTOR_MM3_TO_CM3.multiply(UnitVolume.FACTOR_CM3_TO_L, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	CM3(UnitVolume.FACTOR_CM3_TO_L), //$JUnit$
	L(BigDecimal.ONE),
	M3(BigDecimal.ONE.divide(UnitVolume.FACTOR_L_TO_M3, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	PINT(UnitVolume.FACTOR_PINT_TO_CM3.multiply(UnitVolume.FACTOR_CM3_TO_L, Constants.DEFAULT_MATHCONTEXT)), //$JUnit$
	QUART(UnitVolume.FACTOR_QUART_TO_L), //$JUnit$
	GALLON_US(UnitVolume.FACTOR_GALLON_US_TO_L), //$JUnit$
	BARREL(UnitVolume.FACTOR_BARREL_TO_L); //$JUnit$

	private final BigDecimal factor;
	
	Volume(final BigDecimal factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (liter)
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

