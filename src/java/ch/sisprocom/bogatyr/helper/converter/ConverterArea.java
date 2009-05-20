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
package ch.sisprocom.bogatyr.helper.converter;



/**
 * Converts different units of area.
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public abstract class ConverterArea {
	private static final double FACTOR_MM2_TO_CM2 = 100.0D; //millimeters^2 to centimeters^2
	private static final double FACTOR_CM2_TO_M2 = 100.0D; //centimeters^2 to meters^2
	private static final double FACTOR_M2_TO_AREA = 100.0D; //meters^2 to area
	private static final double FACTOR_AREA_TO_HECTARE = 100.0D; //area to hectare
	private static final double FACTOR_HECTARE_TO_KM2 = 100.0D; //hectare to kilometers^2
	private static final double FACTOR_FOOT2_TO_M2 = 0.09290304D; //square foot to meters^2
	private static final double FACTOR_YARD2_TO_M2 = 0.83612736D; //square yard to meters^2
	private static final double FACTOR_PERCH_TO_M2 = 25.2928526D; //square perch to meters^2
	private static final double FACTOR_ACRE_TO_M2 = 4046.8564224D; //acre to meters^2
	private static final double FACTOR_MILE2_TO_KM2 = 2.5899881103D; //square mile (terrestrial) to kilometers^2
	
	public enum ConversionArea {
		MM2_TO_CM2(FACTOR_MM2_TO_CM2),
		CM2_TO_MM2(1.0D/FACTOR_MM2_TO_CM2),
		CM2_TO_M2(FACTOR_CM2_TO_M2),
		M2_TO_CM2(1.0D/FACTOR_CM2_TO_M2),
		M2_TO_AREA(FACTOR_M2_TO_AREA),
		AREA_TO_M2(1.0D/FACTOR_M2_TO_AREA),
		AREA_TO_HECTARE(FACTOR_AREA_TO_HECTARE),
		HECTARE_TO_AREA(1.0D/FACTOR_AREA_TO_HECTARE),
		HECTARE_TO_KM2(FACTOR_HECTARE_TO_KM2),
		KM2_TO_HECTARE(1.0D/FACTOR_HECTARE_TO_KM2),
		FOOT2_TO_M2(FACTOR_FOOT2_TO_M2),
		M2_TO_FOOT2(1.0D/FACTOR_FOOT2_TO_M2),
		YARD2_TO_M2(FACTOR_YARD2_TO_M2),
		M2_TO_YARD2(1.0D/FACTOR_YARD2_TO_M2),
		PERCH_TO_M2(FACTOR_PERCH_TO_M2),
		M2_TO_PERCH(1.0D/FACTOR_PERCH_TO_M2),
		ACRE_TO_M2(FACTOR_ACRE_TO_M2),
		M2_TO_ACRE(1.0D/FACTOR_ACRE_TO_M2),
		MILE2_TO_KM2(FACTOR_MILE2_TO_KM2),
		KM2_TO_MILE2(1.0D/FACTOR_MILE2_TO_KM2);
		
		ConversionArea(final double factor) {
			this.factor = factor;
		}
		
		public final double factor;
	}
	
	/**
     * Converts a value with the given conversion to a new unit.
     * 
     * @param conversion factor
     * @param value
     * @return value in the new unit
     */
    public static double convert(final ConversionArea conversion, final double value) {
    	return value * conversion.factor; 
    }
}
