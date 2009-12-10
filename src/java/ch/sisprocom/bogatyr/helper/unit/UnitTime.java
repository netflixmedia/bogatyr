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
import ch.sisprocom.bogatyr.model.unit.Time;


/**
 * Converts different units of weight.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.8.0
 */
public abstract class UnitTime extends UnitConverter {
	public static final BigDecimal FACTOR_NANOSECOND_TO_SECOND 	= new BigDecimal("1000000000"); //nanoseconds to seconds //$NON-NLS-1$
	public static final BigDecimal FACTOR_MICROSECOND_TO_SECOND = HelperNumber.BIGDECIMAL_1000000; //microseconds to seconds
	public static final BigDecimal FACTOR_MILLISECOND_TO_SECOND = HelperNumber.BIGDECIMAL_1000; //milliseconds to seconds
	public static final BigDecimal FACTOR_SECOND_TO_MINUTE 		= new BigDecimal("60"); //seconds to minutes //$NON-NLS-1$
	public static final BigDecimal FACTOR_MINUTE_TO_HOUR 		= new BigDecimal("60"); //minutes to hours //$NON-NLS-1$
	public static final BigDecimal FACTOR_HOUR_TO_DAY 			= new BigDecimal("24"); //hours to days //$NON-NLS-1$
	public static final BigDecimal FACTOR_DAY_TO_WEEK 			= new BigDecimal("7"); //days to weeks //$NON-NLS-1$
	public static final BigDecimal FACTOR_DAY_TO_MONTH 			= new BigDecimal("30"); //days to months //$NON-NLS-1$
	public static final BigDecimal FACTOR_DAY_TO_YEAR 			= new BigDecimal("365"); //days to years //$NON-NLS-1$
    
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit time unit
     * @param toUnit time unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.8.0
     */
    public static BigDecimal convert(final Time fromUnit, final Time toUnit, final BigDecimal value) {
    	return UnitConverter.convert(fromUnit, toUnit, value); 
    }
 }
