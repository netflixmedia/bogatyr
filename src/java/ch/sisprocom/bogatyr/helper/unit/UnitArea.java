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
 * Converts different units of area.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090610)
 * @since 0.7.0
 */
public abstract class UnitArea {
	private static final double FACTOR_MM2_TO_CM2 = 100.0D; //millimeters^2 to centimeters^2
	private static final double FACTOR_CM2_TO_M2 = 10000.0D; //centimeters^2 to meters^2
	private static final double FACTOR_M2_TO_AREA = 100.0D; //meters^2 to area
	private static final double FACTOR_AREA_TO_HECTARE = 100.0D; //area to hectare
	private static final double FACTOR_HECTARE_TO_KM2 = 100.0D; //hectare to kilometers^2
	private static final double FACTOR_FOOT2_TO_M2 = 0.09290304D; //square foot to meters^2
	private static final double FACTOR_YARD2_TO_M2 = 0.83612736D; //square yard to meters^2
	private static final double FACTOR_PERCH_TO_M2 = 25.2928526D; //square perch to meters^2
	private static final double FACTOR_ACRE_TO_M2 = 4046.8564224D; //acre to meters^2
	private static final double FACTOR_MILE2_TO_KM2 = 2.5899881103D; //square mile (terrestrial) to kilometers^2

	public enum Area {
		MM2(FACTOR_MM2_TO_CM2 * FACTOR_CM2_TO_M2),
		CM2(FACTOR_CM2_TO_M2),
		M2(1.0D),
		AREA(1/FACTOR_M2_TO_AREA),
		HECTARE(1/(FACTOR_M2_TO_AREA * FACTOR_AREA_TO_HECTARE)),
		KM2(1/(FACTOR_M2_TO_AREA * FACTOR_AREA_TO_HECTARE * FACTOR_HECTARE_TO_KM2)),
		FOOT2(FACTOR_FOOT2_TO_M2),
		YARD2(FACTOR_YARD2_TO_M2),
		PERCH(FACTOR_PERCH_TO_M2),
		ACRE(FACTOR_ACRE_TO_M2),
		MILE2(1/(FACTOR_M2_TO_AREA * FACTOR_AREA_TO_HECTARE * FACTOR_HECTARE_TO_KM2 * FACTOR_MILE2_TO_KM2));
		
		Area(final double factor) {
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
    public static double convert(final Area fromUnit, final Area toUnit, final double value) {
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}

    	return value / fromUnit.factor * toUnit.factor; 
    }
}
