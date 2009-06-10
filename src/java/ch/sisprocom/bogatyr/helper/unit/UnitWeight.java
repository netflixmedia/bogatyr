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
 * Converts different units of weight.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090610)
 * @since 0.7.0
 */
public abstract class UnitWeight {
	private static final double FACTOR_MG_TO_G = 1000.0D; //milligram to gram
	private static final double FACTOR_G_TO_KG = 1000.0D; //gram to kilogram
	private static final double FACTOR_OUNCE_TO_G = 28.34952D; //ounce to gram
	private static final double FACTOR_POUND_TO_KG = 0.453592D; //pound to kilogram
	private static final double FACTOR_TON_TO_KG = 907.1847D; //ton to kilogram

	public enum Weight {
		MG(FACTOR_MG_TO_G * FACTOR_G_TO_KG),
		G(FACTOR_G_TO_KG),
		KG(1.0D),
		OUNCE(FACTOR_OUNCE_TO_G * FACTOR_G_TO_KG),
		POUND(FACTOR_POUND_TO_KG),
		TON(1/(FACTOR_TON_TO_KG));
		
		Weight(final double factor) {
			this.factor = factor;
		}
		
		public final double factor;
	}
    
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit length unit
     * @param toUnit length unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.7.0
     */
    public static double convert(final Weight fromUnit, final Weight toUnit, final double value) {
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}

    	return value / fromUnit.factor * toUnit.factor; 
    }
 }
