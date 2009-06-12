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

/**
 * Length units
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090612)
 * @since 0.7.0
 */
public enum Length {
	MM(UnitLength.FACTOR_MM_TO_CM * UnitLength.FACTOR_CM_TO_M), //$JUnit$
	CM(UnitLength.FACTOR_CM_TO_M), //$JUnit$
	M(1.0D),
	KM(1.0D/UnitLength.FACTOR_M_TO_KM), //$JUnit$
	INCH(UnitLength.FACTOR_CM_TO_M / UnitLength.FACTOR_INCH_TO_CM), //$JUnit$
	FOOT(1.0D/UnitLength.FACTOR_FOOT_TO_M), //$JUnit$
	YARD(1.0D/UnitLength.FACTOR_YARD_TO_M), //$JUnit$
	MILE(1.0D/UnitLength.FACTOR_MILE_TO_M); //$JUnit$

	private final double factor;
	
	Length(final double factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (meter)
     * 
     * @return factor to the base value
     * @since 0.8.0
     */
	public double getFactor() {
		return factor;
	}
}	

