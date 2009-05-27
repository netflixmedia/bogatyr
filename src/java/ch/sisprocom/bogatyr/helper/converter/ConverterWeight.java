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
 * Converts different units of weight.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.7.0
 */
public abstract class ConverterWeight {
	private static final double FACTOR_OUNCE_TO_G = 28.34952D; //ounce to gram
	private static final double FACTOR_POUND_TO_KG = 0.453592D; //pound to kilogram
	private static final double FACTOR_TON_TO_KG = 907.1847D; //ton to kilogram
	private static final double FACTOR_MG_TO_G = 1000.0D; //milligram to gram
	private static final double FACTOR_G_TO_KG = 1000.0D; //gram to kilogram

	public enum ConversionWeight {
		OUNCE_TO_G(FACTOR_OUNCE_TO_G),
		G_TO_OUNCE(1.0D/FACTOR_OUNCE_TO_G),
		POUND_TO_KG(FACTOR_POUND_TO_KG),
		KG_TO_POUND(1.0D/FACTOR_POUND_TO_KG),
		TON_TO_KG(FACTOR_TON_TO_KG),
		KG_TO_TON(1.0D/FACTOR_TON_TO_KG),
		MG_TO_G(FACTOR_MG_TO_G),
		G_TO_MG(1.0D/FACTOR_MG_TO_G),
		G_TO_KG(FACTOR_G_TO_KG),
		KG_TO_G(1.0D/FACTOR_G_TO_KG);
		
		ConversionWeight(final double factor) {
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
     * @since 0.7.0
     */
    public static double convert(final ConversionWeight conversion, final double value) {
    	return value * conversion.factor; 
    }
 }
