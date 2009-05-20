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

import ch.sisprocom.bogatyr.helper.converter.ConverterArea.ConversionArea;


/**
 * Converts different units of volume.
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public abstract class ConverterVolume {
	private static final double FACTOR_PINT_TO_CM3 = 473.176473D; //pint to centimeters^3
	private static final double FACTOR_QUART_TO_L = 0.946326D; //quart to liter
	private static final double FACTOR_GALLON_TO_L = 3.785411784D; //gallon to liter
	private static final double FACTOR_BARREL_TO_L = 158.987294928D; //barrel to liter
	private static final double FACTOR_MM3_TO_CM3 = 1000.0D; //millimeters^3 to centimeters^3
	private static final double FACTOR_CM3_TO_L = 1000.0D; //centimeters^3 to liter
	private static final double FACTOR_L_TO_M3 = 1000.0D; //liter to m^3
	
	public enum ConversionVolume {
		PINT_TO_CM3(FACTOR_PINT_TO_CM3),
		CM3_TO_PINT(1.0D/FACTOR_PINT_TO_CM3),
		QUART_TO_L(FACTOR_QUART_TO_L),
		L_TO_QUART(1.0D/FACTOR_QUART_TO_L),
		GALLON_TO_L(FACTOR_GALLON_TO_L),
		L_TO_GALLON(1.0D/FACTOR_GALLON_TO_L),
		BARREL_TO_L(FACTOR_BARREL_TO_L),
		L_TO_BARREL(1.0D/FACTOR_BARREL_TO_L),
		MM3_TO_CM3(FACTOR_MM3_TO_CM3),
		CM3_TO_MM3(1.0D/FACTOR_MM3_TO_CM3),
		CM3_TO_L(FACTOR_CM3_TO_L),
		L_TO_CM3(1.0D/FACTOR_CM3_TO_L),
		L_TO_M3(FACTOR_L_TO_M3),
		M3_TO_L(1.0D/FACTOR_L_TO_M3);
		
		ConversionVolume(final double factor) {
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
