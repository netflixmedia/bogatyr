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
package ch.sisprocom.bogatyr.helper.unit;

import ch.sisprocom.bogatyr.helper.HelperObject;

/**
 * Volume units
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091016)
 * @since 0.7.0
 */
public enum Volume {
	MM3(UnitVolume.FACTOR_MM3_TO_CM3 * UnitVolume.FACTOR_CM3_TO_L), //$JUnit$
	CM3(UnitVolume.FACTOR_CM3_TO_L), //$JUnit$
	L(1.0D),
	M3(1.0D/UnitVolume.FACTOR_L_TO_M3), //$JUnit$
	PINT(UnitVolume.FACTOR_PINT_TO_CM3 * UnitVolume.FACTOR_CM3_TO_L), //$JUnit$
	QUART(UnitVolume.FACTOR_QUART_TO_L), //$JUnit$
	GALLON_US(UnitVolume.FACTOR_GALLON_US_TO_L), //$JUnit$
	BARREL(UnitVolume.FACTOR_BARREL_TO_L); //$JUnit$

	private final double factor;
	
	Volume(final double factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (liter)
     * 
     * @return factor to the base value
     * @since 0.8.0
     */
	public double getFactor() {
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

