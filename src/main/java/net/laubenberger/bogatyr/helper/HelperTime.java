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

package net.laubenberger.bogatyr.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a helper class for time operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101119)
 * @since 0.7.0
 */
public abstract class HelperTime {
	private static final Logger log = LoggerFactory.getLogger(HelperTime.class);

	public static final int MAX_SECOND_VALUE = 59;
	public static final int MAX_MINUTE_VALUE = 59;
	public static final int MAX_HOUR_VALUE = 23;
	public static final int MAX_DAY_VALUE = 31;
	public static final int MAX_MONTH_VALUE = 12;
	public static final int MAX_YEAR_VALUE = 9999;

	public static final int HOURS_PER_DAY = 24;
	public static final int DAYS_PER_WEEK = 7;
	public static final int DAYS_PER_YEAR = 365;

	public static final long MILLISECONDS_PER_MINUTE = 60L * HelperNumber.NUMBER_1000.longValue();
	public static final long MILLISECONDS_PER_HOUR = 60L * MILLISECONDS_PER_MINUTE;
	public static final long MILLISECONDS_PER_DAY = HOURS_PER_DAY * MILLISECONDS_PER_HOUR;
	public static final long MILLISECONDS_PER_WEEK = DAYS_PER_WEEK * MILLISECONDS_PER_DAY;
	public static final long MILLISECONDS_PER_YEAR = DAYS_PER_YEAR * MILLISECONDS_PER_DAY;
	public static final long SECONDS_BETWEEN_1900_AND_1970 = 2208988800L;

	public static final int TIME_SERVER_PORT = 37;
	public static final String DEFAULT_TIME_SERVER = "ptbtime1.ptb.de"; //$NON-NLS-1$


	/**
	 * Returns the current atomic time of the default time server.
	 *
	 * @return atomic time of the default time server
	 * @throws IOException
	 * @see Date
	 * @since 0.7.0
	 */
	public static Date getAtomicTime() throws IOException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Date result = getAtomicTime(DEFAULT_TIME_SERVER);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current atomic time of the given time server.
	 *
	 * @param host of the time server
	 * @return atomic time of the given time server
	 * @throws IOException
	 * @see Date
	 * @since 0.7.0
	 */
	public static Date getAtomicTime(final String host) throws IOException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(host));
		if (null == host) {
			throw new RuntimeExceptionIsNull("host"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsEmpty("host"); //$NON-NLS-1$
		}

		Socket socket = null;
		InputStream is = null;

		try {
			socket = new Socket(host, TIME_SERVER_PORT);
			is = socket.getInputStream();

			long time = 0L;

			for (int ii = 3; 0 <= ii; ii--) {
				time ^= (long) is.read() << ii * 8;
			}

			final Date result = new Date((time - SECONDS_BETWEEN_1900_AND_1970) * HelperNumber.NUMBER_1000.longValue());

			if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
			return result;
		} finally {
			if (null != is) {
				is.close();
			}

			if (null != socket) {
				socket.close();
			}
		}
	}

	/**
	 * Create a {@link Date} with day, month and year as parameters and the current user {@link TimeZone}.
	 *
	 * @param year
	 * @param month range between 1-12
	 * @param date  range between 1-31
	 * @return created {@link Date}
	 * @see Date
	 * @see TimeZone
	 * @since 0.7.0
	 */
	public static Date getDate(final int year, final int month, final int date) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(year, month, date));
		
		final Date result = getDate(year, month, date, HelperEnvironment.getUserTimezone());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;	
	}
	
	/**
	 * Create a {@link Date} with day, month, year and {@link TimeZone} as parameters.
	 *
	 * @param year
	 * @param month range between 1-12
	 * @param date  range between 1-31
	 * @param timeZone for the {@link Date}
	 * @return created {@link Date}
	 * @see Date
	 * @see TimeZone
	 * @since 0.9.2
	 */
	public static Date getDate(final int year, final int month, final int date, final TimeZone timeZone) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(year, month, date, timeZone));
		if (0 > year) {
			throw new RuntimeExceptionMustBeGreater("year", year, 0); //$NON-NLS-1$
		}
		if (MAX_YEAR_VALUE < year) {
			throw new RuntimeExceptionMustBeSmaller("year", year, MAX_YEAR_VALUE); //$NON-NLS-1$
		}
		if (0 > month) {
			throw new RuntimeExceptionMustBeGreater("month", month, 0); //$NON-NLS-1$
		}
		if (MAX_MONTH_VALUE < month) {
			throw new RuntimeExceptionMustBeSmaller("month", month, MAX_MONTH_VALUE); //$NON-NLS-1$
		}
		if (0 > date) {
			throw new RuntimeExceptionMustBeGreater("date", date, 0); //$NON-NLS-1$
		}
		if (MAX_DAY_VALUE < date) {
			throw new RuntimeExceptionMustBeSmaller("date", date, MAX_DAY_VALUE); //$NON-NLS-1$
		}

		final Calendar cal = Calendar.getInstance(timeZone);
		
		cal.set(year, month - 1, date, 0, 0, 0);


		if (cal.get(Calendar.DAY_OF_MONTH) != date) {
			throw new IllegalArgumentException("date value invalid: " + date); //$NON-NLS-1$
		}

		if (cal.get(Calendar.MONTH) != month - 1) {
			throw new IllegalArgumentException("month value invalid: " + month); //$NON-NLS-1$
		}

		final Date result = cal.getTime();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Create a {@link Date} with day, month and year as parameters and the current user {@link TimeZone}.
	 *
	 * @param year	range between 0-9999
	 * @param month  range between 1-12
	 * @param date	range between 1-31
	 * @param hour	range between 0-23
	 * @param minute range between 0-59
	 * @param second range between 0-59
	 * @return created {@link Date}
	 * @see Date
	 * @see TimeZone
	 * @since 0.9.1
	 */
	public static Date getDate(final int year, final int month, final int date, final int hour, final int minute, final int second) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(year, month, date, hour, minute, second));
		
		final Date result = getDate(year, month, date, hour, minute, second, HelperEnvironment.getUserTimezone());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;	
	}
	
	/**
	 * Create a {@link Date} with day, month, year and {@link TimeZone} as parameters.
	 * 
	 * @param year	range between 0-9999
	 * @param month  range between 1-12
	 * @param date	range between 1-31
	 * @param hour	range between 0-23
	 * @param minute range between 0-59
	 * @param second range between 0-59
	 * @param timeZone for the {@link Date}
	 * @return created {@link Date}
	 * @see Date
	 * @see TimeZone
	 * @since 0.9.2
	 */
	public static Date getDate(final int year, final int month, final int date, final int hour, final int minute, final int second, final TimeZone timeZone) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(year, month, date, hour, minute, second, timeZone));
		if (0 > year) {
			throw new RuntimeExceptionMustBeGreater("year", year, 0); //$NON-NLS-1$
		}
		if (MAX_YEAR_VALUE < year) {
			throw new RuntimeExceptionMustBeSmaller("year", year, MAX_YEAR_VALUE); //$NON-NLS-1$
		}
		if (0 > month) {
			throw new RuntimeExceptionMustBeGreater("month", month, 0); //$NON-NLS-1$
		}
		if (MAX_MONTH_VALUE < month) {
			throw new RuntimeExceptionMustBeSmaller("month", month, MAX_MONTH_VALUE); //$NON-NLS-1$
		}
		if (0 > date) {
			throw new RuntimeExceptionMustBeGreater("date", date, 0); //$NON-NLS-1$
		}
		if (MAX_DAY_VALUE < date) {
			throw new RuntimeExceptionMustBeSmaller("date", date, MAX_DAY_VALUE); //$NON-NLS-1$
		}
		if (0 > hour) {
			throw new RuntimeExceptionMustBeGreater("hour", hour, 0); //$NON-NLS-1$
		}
		if (MAX_HOUR_VALUE < hour) {
			throw new RuntimeExceptionMustBeSmaller("hour", hour, MAX_HOUR_VALUE); //$NON-NLS-1$
		}
		if (0 > minute) {
			throw new RuntimeExceptionMustBeGreater("minute", minute, 0); //$NON-NLS-1$
		}
		if (MAX_MINUTE_VALUE < minute) {
			throw new RuntimeExceptionMustBeSmaller("minute", minute, MAX_MINUTE_VALUE); //$NON-NLS-1$
		}
		if (0 > second) {
			throw new RuntimeExceptionMustBeGreater("second", second, 0); //$NON-NLS-1$
		}
		if (MAX_SECOND_VALUE < second) {
			throw new RuntimeExceptionMustBeSmaller("second", second, MAX_SECOND_VALUE); //$NON-NLS-1$
		}
		if (null == timeZone) {
			throw new RuntimeExceptionIsNull("timeZone"); //$NON-NLS-1$
		}
		
		final Calendar cal = Calendar.getInstance(timeZone);

		cal.set(year, month - 1, date, hour, minute, second);


		if (cal.get(Calendar.DAY_OF_MONTH) != date) {
			throw new IllegalArgumentException("date value invalid: " + date); //$NON-NLS-1$
		}

		if (cal.get(Calendar.MONTH) != month - 1) {
			throw new IllegalArgumentException("month value invalid: " + month); //$NON-NLS-1$
		}

		final Date result = cal.getTime();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
	 * Returns a {@link Date} containing only the date from a given {@link Date}. The values of hours, minutes, seconds and milliseconds is set to 0.
	 *
	 * @param date for the absolute date
	 * @return created {@link Date}
	 * @see Date
	 * @since 0.9.2
	 */
	public static Date getDateAsAbsoluteDate(final Date date) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(date));

		final Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		final Date result = cal.getTime();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
	
	/**
	 * Returns a {@link Date} containing only the time from a given {@link Date}. The values of years is set to 0 and months/days set to 1.
	 *
	 * @param date for the absolute time
	 * @return created {@link Date}
	 * @see Date
	 * @since 0.9.2
	 */
	public static Date getDateAsAbsoluteTime(final Date date) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(date));

		final Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		cal.set(Calendar.YEAR, 0);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		final Date result = cal.getTime();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
	
//	/**
//	 * Create a {@link Date} with a given offset in ms from the current time.
//	 *
//	 * @param offSet in ms from the current time
//	 * @return created date
//	 * @see Date
//	 * @since 0.7.0
//	 */
//	public static Date getDate(final long offSet) {
//		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(offSet));
//
//		final Date result = new Date(System.currentTimeMillis() + offSet);
//
//		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
//		return result;
//	}
}
