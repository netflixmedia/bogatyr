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
 * @version 0.8.0 (20090612)
 * @since 0.8.0
 */
public abstract class UnitTime {
	public static final double FACTOR_MILLISECOND_TO_SECOND = 1000.0D; //milliseconds to seconds
	public static final double FACTOR_SECOND_TO_MINUTE = 60.0D; //seconds to minutes
	public static final double FACTOR_MINUTE_TO_HOUR = 60.0D; //minutes to hours
	public static final double FACTOR_HOUR_TO_DAY = 24.0D; //hours to days
	public static final double FACTOR_DAY_TO_WEEK = 7.0D; //days to weeks
	public static final double FACTOR_DAY_TO_MONTH = 30.0D; //days to months
	public static final double FACTOR_DAY_TO_YEAR = 365.0D; //days to years
    
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit time unit
     * @param toUnit time unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.8.0
     */
    public static double convert(final Time fromUnit, final Time toUnit, final double value) {
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}

    	return value / fromUnit.getFactor() * toUnit.getFactor(); 
    }
 }
