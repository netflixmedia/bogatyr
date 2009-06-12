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
 * Weight units
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090612)
 * @since 0.7.0
 */
public enum Weight {
	MILLIGRAM(UnitWeight.FACTOR_MILLIGRAM_TO_GRAM * UnitWeight.FACTOR_GRAM_TO_KILOGRAM), //$JUnit$
	GRAM(UnitWeight.FACTOR_GRAM_TO_KILOGRAM), //$JUnit$
	KILOGRAM(1.0D), //$JUnit$
	OUNCE(UnitWeight.FACTOR_OUNCE_TO_GRAM * UnitWeight.FACTOR_GRAM_TO_KILOGRAM), //$JUnit$
	POUND(UnitWeight.FACTOR_POUND_TO_KILOGRAM), //$JUnit$
	TON(1.0D/(UnitWeight.FACTOR_TON_TO_KILOGRAM)); //$JUnit$

	private final double factor;
	
	Weight(final double factor) {
		this.factor = factor;
	}

    /**
     * Returns the factor from the unit to the base value (kilogram)
     * 
     * @return factor to the base value
     * @since 0.8.0
     */
	public double getFactor() {
		return factor;
	}
}	

