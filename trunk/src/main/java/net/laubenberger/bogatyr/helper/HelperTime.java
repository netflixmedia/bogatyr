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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package net.laubenberger.bogatyr.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;


/**
 * This is a helper class for time operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public abstract class HelperTime {
	private static final Logger log = LoggerFactory.getLogger(HelperTime.class);

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
		log.debug(HelperLog.methodStart());

		final Date result = getAtomicTime(DEFAULT_TIME_SERVER);

		log.debug(HelperLog.methodExit(result));
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
		log.debug(HelperLog.methodStart(host));
		if (!HelperString.isValid(host)) {
			throw new RuntimeExceptionIsNullOrEmpty("host"); //$NON-NLS-1$
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

			log.debug(HelperLog.methodExit(result));
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
	 * Create a {@link Date} with day, month and year as parameters.
	 *
	 * @param year
	 * @param month range between 1-12
	 * @param date  range between 1-31
	 * @return created date
	 * @see Date
	 * @since 0.7.0
	 */
	public static Date getDate(final int year, final int month, final int date) {
		log.debug(HelperLog.methodStart(year, month, date));
		if (0 > year) {
			throw new RuntimeExceptionMustBeGreater("year", year, 0); //$NON-NLS-1$
		}
		if (9999 < year) {
			throw new RuntimeExceptionMustBeSmaller("year", year, 9999); //$NON-NLS-1$
		}
		if (0 > month) {
			throw new RuntimeExceptionMustBeGreater("month", month, 0); //$NON-NLS-1$
		}
		if (12 < month) {
			throw new RuntimeExceptionMustBeSmaller("month", month, 12); //$NON-NLS-1$
		}
		if (0 > date) {
			throw new RuntimeExceptionMustBeGreater("date", date, 0); //$NON-NLS-1$
		}
		if (31 < date) {
			throw new RuntimeExceptionMustBeSmaller("date", date, 31); //$NON-NLS-1$
		}

		final Calendar cal = new GregorianCalendar();

		cal.set(year, month - 1, date);


		if (cal.get(Calendar.DAY_OF_MONTH) != date) {
			throw new IllegalArgumentException("date value invalid: " + date); //$NON-NLS-1$
		}

		if (cal.get(Calendar.MONTH) != month - 1) {
			throw new IllegalArgumentException("month value invalid: " + month); //$NON-NLS-1$
		}

		final Date result = cal.getTime();

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Create a {@link Date} with day, month and year as parameters.
	 *
	 * @param year	range between 0-9999
	 * @param month  range between 1-12
	 * @param date	range between 1-31
	 * @param hour	range between 0-23
	 * @param minute range between 0-59
	 * @param second range between 0-59
	 * @return created date
	 * @see Date
	 * @since 0.9.1
	 */
	public static Date getDate(final int year, final int month, final int date, final int hour, final int minute, final int second) {
		log.debug(HelperLog.methodStart(year, month, date));
		if (0 > year) {
			throw new RuntimeExceptionMustBeGreater("year", year, 0); //$NON-NLS-1$
		}
		if (9999 < year) {
			throw new RuntimeExceptionMustBeSmaller("year", year, 9999); //$NON-NLS-1$
		}
		if (0 > month) {
			throw new RuntimeExceptionMustBeGreater("month", month, 0); //$NON-NLS-1$
		}
		if (12 < month) {
			throw new RuntimeExceptionMustBeSmaller("month", month, 12); //$NON-NLS-1$
		}
		if (0 > date) {
			throw new RuntimeExceptionMustBeGreater("date", date, 0); //$NON-NLS-1$
		}
		if (31 < date) {
			throw new RuntimeExceptionMustBeSmaller("date", date, 31); //$NON-NLS-1$
		}
		if (0 > hour) {
			throw new RuntimeExceptionMustBeGreater("hour", hour, 0); //$NON-NLS-1$
		}
		if (23 < hour) {
			throw new RuntimeExceptionMustBeSmaller("hour", hour, 23); //$NON-NLS-1$
		}
		if (0 > minute) {
			throw new RuntimeExceptionMustBeGreater("minute", minute, 0); //$NON-NLS-1$
		}
		if (59 < minute) {
			throw new RuntimeExceptionMustBeSmaller("minute", minute, 59); //$NON-NLS-1$
		}
		if (0 > second) {
			throw new RuntimeExceptionMustBeGreater("second", second, 0); //$NON-NLS-1$
		}
		if (59 < second) {
			throw new RuntimeExceptionMustBeSmaller("second", second, 59); //$NON-NLS-1$
		}

		final Calendar cal = new GregorianCalendar();

		cal.set(year, month - 1, date, hour, minute, second);


		if (cal.get(Calendar.DAY_OF_MONTH) != date) {
			throw new IllegalArgumentException("date value invalid: " + date); //$NON-NLS-1$
		}

		if (cal.get(Calendar.MONTH) != month - 1) {
			throw new IllegalArgumentException("month value invalid: " + month); //$NON-NLS-1$
		}

		final Date result = cal.getTime();

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Create a {@link Date} with a given offset in ms from the current time.
	 *
	 * @param offSet in ms from the current time
	 * @return created date
	 * @see Date
	 * @since 0.7.0
	 */
	public static Date getDate(final long offSet) {
		log.debug(HelperLog.methodStart(offSet));

		final Date result = new Date(System.currentTimeMillis() + offSet);

		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
