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
 * Converts different units of volume.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090612)
 * @since 0.7.0
 */
public abstract class UnitVolume {
	public static final double FACTOR_MM3_TO_CM3 = 1000.0D; //millimeters^3 to centimeters^3
	public static final double FACTOR_CM3_TO_L = 1000.0D; //centimeters^3 to liter
	public static final double FACTOR_L_TO_M3 = 1000.0D; //liter to m^3
	public static final double FACTOR_PINT_TO_CM3 = 473.176473D; //pint to centimeters^3
	public static final double FACTOR_QUART_TO_L = 0.946326D; //quart to liter
	public static final double FACTOR_GALLON_TO_L = 3.785411784D; //gallon to liter
	public static final double FACTOR_BARREL_TO_L = 158.987294928D; //barrel to liter
	
	
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit volume unit
     * @param toUnit volume unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.7.0
     */
    public static double convert(final Volume fromUnit, final Volume toUnit, final double value) { //$JUnit$
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}

    	return value / fromUnit.getFactor() * toUnit.getFactor(); 
    }
}
