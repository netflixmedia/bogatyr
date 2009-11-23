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
package ch.sisprocom.bogatyr.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * This is a helper class for time operations.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091122)
 * @since 0.7.0
 */
public abstract class HelperTime {
	public static final int HOURS_PER_DAY = 24;
	public static final int DAYS_PER_WEEK = 7;
	public static final int DAYS_PER_YEAR = 365;
	  
	public static final long MILLISECONDS_PER_MINUTE = 60L * 1000L;
	public static final long MILLISECONDS_PER_HOUR = 60L * MILLISECONDS_PER_MINUTE;
	public static final long MILLISECONDS_PER_DAY = HOURS_PER_DAY * MILLISECONDS_PER_HOUR;
	public static final long MILLISECONDS_PER_WEEK = DAYS_PER_WEEK * MILLISECONDS_PER_DAY;
	public static final long MILLISECONDS_PER_YEAR = DAYS_PER_YEAR * MILLISECONDS_PER_DAY;
	public static final long SECONDS_BETWEEN_1900_AND_1970 = 2208988800L;
	
	public static final int TIME_SERVER_PORT = 37;
	public static final String DEFAULT_TIME_SERVER = "ptbtime1.ptb.de"; //$NON-NLS-1$

	public static final DateFormat FORMAT_DAY_MONTH_YEAR = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_YEAR_MONTH_DAY = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE_SECOND = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_HOUR_MINUTE = new SimpleDateFormat("HH:mm", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_HOUR_MINUTE_SECOND = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()); //$NON-NLS-1$
	public static final DateFormat FORMAT_HOUR_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$

	
    /**
     * Returns the current atomic time of the default time server.
     *
     * @return atomic time of the default time server
     * @throws IOException
     * @see Date
     * @since 0.7.0
     */
	public static Date getAtomicTime() throws IOException { //$JUnit$
		return getAtomicTime(DEFAULT_TIME_SERVER);
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
		if (!HelperString.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		
		Socket socket = null;
		InputStream is = null;

		try {
			socket = new Socket(host, TIME_SERVER_PORT);
			is = socket.getInputStream();

            long time = 0L;

            for (int ii = 3; 0 <= ii; ii-- ) {
				time ^= (long) is.read() << ii * 8;
			}

			return new Date((time - SECONDS_BETWEEN_1900_AND_1970) * 1000L);
		} finally {
			if (is != null) {
				is.close();
			}
			
			if (socket != null) {
                socket.close();
            }
        }
    }
	
    /**
     * Create a {@link Date} with day, month and year as parameters.
     *
     * @param day range between 1-31
     * @param month range between 1-12
     * @param year 
     * @return created date
     * @see Date
     * @since 0.7.0
     */
    public static Date getDate(final int day, final int month, final int year) {
    	if (0 > day) {
    		throw new IllegalArgumentException("day value must be positive: " + day); //$NON-NLS-1$
    	}
    	if (0 > month) {
    		throw new IllegalArgumentException("month value must be positive: " + month); //$NON-NLS-1$
    	}
    	if (0 > year) {
    		throw new IllegalArgumentException("year value must be positive: " + year); //$NON-NLS-1$
    	}
    	
    	final Calendar cal = new GregorianCalendar();

        cal.set(year, month - 1, day);


        if (cal.get(Calendar.DAY_OF_MONTH) != day) {
            throw new IllegalArgumentException("day value invalid: " + day); //$NON-NLS-1$
        }

        if (cal.get(Calendar.MONTH) != month - 1) {
            throw new IllegalArgumentException("month value invalid: " + month); //$NON-NLS-1$
        }

        return cal.getTime();
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
         return new Date(System.currentTimeMillis() + offSet);
    }
    
    /**
     * Create a date with a given input {@link String} and a {@link DateFormat}.
     *
     * @param date as {@link String}
     * @param format {@link DateFormat} for the date
     * @return parsed date
     * @throws ParseException 
     * @see DateFormat
     * @since 0.9.0
     */
    public static Date getDate(final String date, final DateFormat format) throws ParseException {
    	return format.parse(date);
    }

    /**
     * Create an formatted date with a given time in ms and a {@link DateFormat}.
     *
     * @param time in ms to format
     * @param format {@link DateFormat} for the date
     * @return formatted date
     * @see DateFormat
     * @since 0.9.0
     */
    public static String getFormattedDate(final long time, final DateFormat format) {
    	return format.format(new Date(time));
    }
    
    /**
     * Create an formatted date with a given {@link Date} and a {@link DateFormat}.
     *
     * @param date to format
     * @param format {@link DateFormat} for the date
     * @return formatted date
     * @see Date
     * @see DateFormat
     * @since 0.9.0
     */
    public static String getFormattedDate(final Date date, final DateFormat format) {
    	return format.format(date);
    }
}
