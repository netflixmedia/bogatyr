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
package ch.sisprocom.bogatyr.model.unit;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.unit.UnitTime;

/**
 * Time units
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091116)
 * @since 0.8.0
 */
public enum Time {
	NANOSECOND(UnitTime.FACTOR_NANOSECOND_TO_SECOND),
	MICROSECOND(UnitTime.FACTOR_MICROSECOND_TO_SECOND),
	MILLISECOND(UnitTime.FACTOR_MILLISECOND_TO_SECOND),
	SECOND(1.0D),
	MINUTE(1.0D/UnitTime.FACTOR_SECOND_TO_MINUTE),
	HOUR(1.0D/(UnitTime.FACTOR_SECOND_TO_MINUTE * UnitTime.FACTOR_MINUTE_TO_HOUR)),
	DAY(1.0D/(UnitTime.FACTOR_SECOND_TO_MINUTE * UnitTime.FACTOR_MINUTE_TO_HOUR * UnitTime.FACTOR_HOUR_TO_DAY)),
	WEEK(1.0D/(UnitTime.FACTOR_SECOND_TO_MINUTE * UnitTime.FACTOR_MINUTE_TO_HOUR * UnitTime.FACTOR_HOUR_TO_DAY * UnitTime.FACTOR_DAY_TO_WEEK)),
	MONTH(1.0D/(UnitTime.FACTOR_SECOND_TO_MINUTE * UnitTime.FACTOR_MINUTE_TO_HOUR * UnitTime.FACTOR_HOUR_TO_DAY * UnitTime.FACTOR_DAY_TO_MONTH)),
	YEAR(1.0D/(UnitTime.FACTOR_SECOND_TO_MINUTE * UnitTime.FACTOR_MINUTE_TO_HOUR * UnitTime.FACTOR_HOUR_TO_DAY * UnitTime.FACTOR_DAY_TO_YEAR));

	private final double factor;
	
	Time(final double factor) {
		this.factor = factor;
	}
	
    /**
     * Returns the factor from the unit to the base value (second)
     * 
     * @return factor to the base value
     * @since 0.8.0
     */
	public double getFactor() {
		return factor;
	}


    /*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}
