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
 * Area units
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090612)
 * @since 0.7.0
 */
public enum Area {
	MM2(UnitArea.FACTOR_MM2_TO_CM2 * UnitArea.FACTOR_CM2_TO_M2), //$JUnit$
	CM2(UnitArea.FACTOR_CM2_TO_M2), //$JUnit$
	M2(1.0D),
	AREA(1.0D/UnitArea.FACTOR_M2_TO_AREA), //$JUnit$
	HECTARE(1.0D/(UnitArea.FACTOR_M2_TO_AREA * UnitArea.FACTOR_AREA_TO_HECTARE)), //$JUnit$
	KM2(1/(UnitArea.FACTOR_M2_TO_AREA * UnitArea.FACTOR_AREA_TO_HECTARE * UnitArea.FACTOR_HECTARE_TO_KM2)), //$JUnit$
	FOOT2(UnitArea.FACTOR_FOOT2_TO_M2), //$JUnit$
	YARD2(UnitArea.FACTOR_YARD2_TO_M2), //$JUnit$
	PERCH(UnitArea.FACTOR_PERCH_TO_M2), //$JUnit$
	ACRE(UnitArea.FACTOR_ACRE_TO_M2), //$JUnit$
	MILE2(1.0D/(UnitArea.FACTOR_M2_TO_AREA * UnitArea.FACTOR_AREA_TO_HECTARE * UnitArea.FACTOR_HECTARE_TO_KM2 * UnitArea.FACTOR_MILE2_TO_KM2)); //$JUnit$

	private final double factor;
	
	Area(final double factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (meter^2)
     * 
     * @return factor to the base value
     * @since 0.8.0
     */
	public double getFactor() {
		return factor;
	}
}	

