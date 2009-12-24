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

import java.math.BigDecimal;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.model.unit.Area;


/**
 * Converts different units of area.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091224)
 * @since 0.7.0
 */
public abstract class UnitArea {
	public static final BigDecimal FACTOR_MM2_TO_CM2 	  = HelperNumber.BIGDECIMAL_100; //millimeters^2 to centimeters^2
	public static final BigDecimal FACTOR_CM2_TO_M2 	  = HelperNumber.BIGDECIMAL_10000; //centimeters^2 to meters^2
	public static final BigDecimal FACTOR_M2_TO_AREA 	  = HelperNumber.BIGDECIMAL_100; //meters^2 to area
	public static final BigDecimal FACTOR_AREA_TO_HECTARE = HelperNumber.BIGDECIMAL_100; //area to hectare
	public static final BigDecimal FACTOR_HECTARE_TO_KM2  = HelperNumber.BIGDECIMAL_100; //hectare to kilometers^2
	public static final BigDecimal FACTOR_FOOT2_TO_M2 	  = new BigDecimal("0.09290304"); //square foot to meters^2 //$NON-NLS-1$
	public static final BigDecimal FACTOR_YARD2_TO_M2 	  = new BigDecimal("0.83612736"); //square yard to meters^2 //$NON-NLS-1$
	public static final BigDecimal FACTOR_PERCH_TO_M2 	  = new BigDecimal("25.2928526"); //square perch to meters^2 //$NON-NLS-1$
	public static final BigDecimal FACTOR_ACRE_TO_M2 	  = new BigDecimal("4046.8564224"); //acre to meters^2 //$NON-NLS-1$
	public static final BigDecimal FACTOR_MILE2_TO_KM2 	  = new BigDecimal("2.5899881103"); //square mile (terrestrial) to kilometers^2 //$NON-NLS-1$

	
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit area unit
     * @param toUnit area unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.7.0
     */
    public static BigDecimal convert(final Area fromUnit, final Area toUnit, final BigDecimal value) { //$JUnit$
    	return UnitConverter.convert(fromUnit, toUnit, value); 
    }
}
