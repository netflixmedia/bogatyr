/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.model.misc;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;

import net.laubenberger.bogatyr.helper.HelperString;

/**
 * Possible weekdays.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101202)
 * @since 0.9.1
 */
@XmlRootElement(name = "weekday")
public enum Weekday {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

	public String getName() {
		return getName(Locale.getDefault());
	}

	public String getName(final Locale locale) {
		return new SimpleDateFormat(HelperString.EMPTY_STRING, locale).getDateFormatSymbols().getWeekdays()[ordinal() + 1];
	}
}
