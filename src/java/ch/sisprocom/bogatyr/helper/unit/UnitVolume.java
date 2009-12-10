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
import ch.sisprocom.bogatyr.model.unit.Volume;


/**
 * Converts different units of volume.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.7.0
 */
public abstract class UnitVolume extends UnitConverter {
	public static final BigDecimal FACTOR_MM3_TO_CM3 	 = HelperNumber.BIGDECIMAL_1000; //millimeters^3 to centimeters^3
	public static final BigDecimal FACTOR_CM3_TO_L 		 = HelperNumber.BIGDECIMAL_1000; //centimeters^3 to liter
	public static final BigDecimal FACTOR_L_TO_M3 		 = HelperNumber.BIGDECIMAL_1000; //liter to m^3
	public static final BigDecimal FACTOR_PINT_TO_CM3	 = new BigDecimal("473.176473"); //pint to centimeters^3 //$NON-NLS-1$
	public static final BigDecimal FACTOR_QUART_TO_L 	 = new BigDecimal("0.946326"); //quart to liter //$NON-NLS-1$
	public static final BigDecimal FACTOR_GALLON_US_TO_L = new BigDecimal("3.785411784"); //gallon to liter //$NON-NLS-1$
	public static final BigDecimal FACTOR_BARREL_TO_L 	 = new BigDecimal("158.987294928"); //barrel to liter //$NON-NLS-1$
	
	
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit volume unit
     * @param toUnit volume unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.7.0
     */
    public static BigDecimal convert(final Volume fromUnit, final Volume toUnit, final BigDecimal value) { //$JUnit$
    	return UnitConverter.convert(fromUnit, toUnit, value); 
    }
}
