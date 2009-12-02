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

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.model.unit.Weight;


/**
 * Converts different units of weight.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091203)
 * @since 0.7.0
 */
public abstract class UnitWeight {
	public static final BigDecimal FACTOR_MILLIGRAM_TO_GRAM = HelperNumber.BIGDECIMAL_1000; //milligram to gram
	public static final BigDecimal FACTOR_GRAM_TO_KILOGRAM  = HelperNumber.BIGDECIMAL_1000; //gram to kilogram
	public static final BigDecimal FACTOR_OUNCE_TO_GRAM 	= new BigDecimal("28.34952"); //ounce to gram //$NON-NLS-1$
	public static final BigDecimal FACTOR_POUND_TO_KILOGRAM = new BigDecimal("0.453592"); //pound to kilogram //$NON-NLS-1$
	public static final BigDecimal FACTOR_TON_TO_KILOGRAM 	= new BigDecimal("907.1847"); //ton to kilogram //$NON-NLS-1$
    
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit weight unit
     * @param toUnit weight unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.7.0
     */
    public static BigDecimal convert(final Weight fromUnit, final Weight toUnit, final BigDecimal value) { //$JUnit$
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}
		if (null == value) {
			throw new IllegalArgumentException("value is null!"); //$NON-NLS-1$
		}

    	return value.divide(fromUnit.getFactor(), Constants.DEFAULT_MATHCONTEXT).multiply(toUnit.getFactor(), Constants.DEFAULT_MATHCONTEXT); 
   }
 }
