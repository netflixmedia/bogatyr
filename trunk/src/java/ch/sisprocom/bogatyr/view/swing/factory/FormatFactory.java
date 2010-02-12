/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing.factory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This is a format factory.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.9.0
 */
public abstract class FormatFactory {
	public static final String PATTERN_DATE_DAY_MONTH_YEAR 								  = "dd.MM.yyyy"; //$NON-NLS-1$
	public static final String PATTERN_DATE_YEAR_MONTH_DAY 								  = "yyyy.MM.dd"; //$NON-NLS-1$
	public static final String PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE 					  = "yyyy.MM.dd HH:mm"; //$NON-NLS-1$
	public static final String PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND 			  = "yyyy.MM.dd HH:mm:ss"; //$NON-NLS-1$
	public static final String PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND = "yyyy.MM.dd HH:mm:ss.SSS"; //$NON-NLS-1$
	public static final String PATTERN_DATE_DAY_MONTH_YEAR_HOUR_MINUTE 					  = "dd.MM.yyyy HH:mm"; //$NON-NLS-1$
	public static final String PATTERN_DATE_DAY_MONTH_YEAR_HOUR_MINUTE_SECOND 			  = "dd.MM.yyyy HH:mm:ss"; //$NON-NLS-1$
	public static final String PATTERN_DATE_DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_MILLISECOND = "dd.MM.yyyy HH:mm:ss.SSS"; //$NON-NLS-1$
	public static final String PATTERN_DATE_HOUR_MINUTE 								  = "HH:mm"; //$NON-NLS-1$
	public static final String PATTERN_DATE_HOUR_MINUTE_SECOND 							  = "HH:mm:ss"; //$NON-NLS-1$
	public static final String PATTERN_DATE_HOUR_MINUTE_SECOND_MILLISECOND 				  = "HH:mm:ss.SSS"; //$NON-NLS-1$
	public static final String PATTERN_DATE_TEXT						 				  = "EEEEE, dd. MMMMM yyyy"; //$NON-NLS-1$

	/**
     * Creates a default {@link NumberFormat}.
     * 
     * @return created {@link NumberFormat}
     * @see NumberFormat
     * @since 0.9.0
     */	
	public static NumberFormat createNumberFormat() {
		return NumberFormat.getNumberInstance(Locale.getDefault());
	}

	/**
     * Creates a {@link NumberFormat} with a given pattern.
     * 
     * @param pattern for the format
     * @return created {@link NumberFormat}
     * @see NumberFormat
     * @since 0.9.0
     */	
	public static NumberFormat createNumberFormat(final String pattern) {
		if (!HelperString.isValid(pattern)) {
			throw new RuntimeExceptionIsNullOrEmpty("pattern"); //$NON-NLS-1$
		}

		return new DecimalFormat(pattern);
	}

	/**
     * Creates a default {@link NumberFormat} for percent values.
     * 
     * @return created {@link NumberFormat}
     * @see NumberFormat
     * @since 0.9.0
     */	
	public static NumberFormat createPercentFormat() {
		return NumberFormat.getPercentInstance(Locale.getDefault());
	}

	/**
     * Creates a default {@link NumberFormat} for currency values.
     * 
     * @return created {@link NumberFormat}
     * @see NumberFormat
     * @since 0.9.0
     */	
	public static NumberFormat createCurrencyFormat() {
		return NumberFormat.getCurrencyInstance(Locale.getDefault());
	}

	/**
     * Creates a {@link DateFormat} with a given pattern.
     * 
     * @param pattern for the format
     * @return created {@link DateFormat}
     * @see DateFormat
     * @since 0.9.0
     */	
	public static DateFormat createDateFormat(final String pattern) {
		if (!HelperString.isValid(pattern)) {
			throw new RuntimeExceptionIsNullOrEmpty("pattern"); //$NON-NLS-1$
		}

		return new SimpleDateFormat(pattern, Locale.getDefault());
	}

	/**
     * Creates a {@link NumberFormatter} for display-mode from a given {@link NumberFormat}.
     * 
     * @param format for the formatter
     * @return created {@link NumberFormatter}
     * @see NumberFormat
     * @see NumberFormatter
     * @since 0.9.0
     */	
	public static NumberFormatter createNumberFormatterDisplay(final NumberFormat format) {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new NumberFormatter(format) {
			private static final long serialVersionUID = -8279122736069976223L;

			{
				setCommitsOnValidEdit(true);
			}
		};
	}

	/**
     * Creates a {@link NumberFormatter} for edit-mode from a given {@link NumberFormat}.
     * 
     * @param format for the formatter
     * @return created {@link NumberFormatter}
     * @see NumberFormat
     * @see NumberFormatter
     * @since 0.9.0
     */	
	public static NumberFormatter createNumberFormatterEdit(final NumberFormat format) {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new NumberFormatter(format) {
			private static final long serialVersionUID = 1315478587371973936L;

			{
				setCommitsOnValidEdit(true);
				setValueClass(BigDecimal.class);
			}

	        @Override
			public Object stringToValue(final String string) throws ParseException {
	        	final String text = HelperString.getValidNumericString(string);
	        	
        		return null == text ? null : super.stringToValue(text);
	        }
	    };
	}

	/**
     * Creates a {@link NumberFormatter} (percent) for display-mode from a given {@link NumberFormat}.
     * 
     * @param format for the formatter
     * @return created {@link NumberFormatter}
     * @see NumberFormat
     * @see NumberFormatter
     * @since 0.9.0
     */	
	public static NumberFormatter createPercentFormatterDisplay(final NumberFormat format) {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new NumberFormatter(format) {
			private static final long serialVersionUID = 1104014354379282093L;

			{
				setCommitsOnValidEdit(true);
			}
		};
	}

	/**
     * Creates a {@link NumberFormatter} (percent) for edit-mode from a given {@link NumberFormat}.
     * 
     * @param format for the formatter
     * @return created {@link NumberFormatter}
     * @see NumberFormat
     * @see NumberFormatter
     * @since 0.9.0
     */	
	public static NumberFormatter createPercentFormatterEdit(final NumberFormat format) {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new NumberFormatter(format) {
			private static final long serialVersionUID = 1315478587371973936L;

			{
				setCommitsOnValidEdit(true);
				setValueClass(BigDecimal.class);
			}

			@Override
			public String valueToString(final Object o)
	              throws ParseException {
	            Number number = (Number)o;
	            if (null != number) {
	                final double d = number.doubleValue() * HelperNumber.NUMBER_100.doubleValue();
	                number = new Double(d);
	            }
	            return super.valueToString(number);
	        }
	        
	        @Override
			public Object stringToValue(final String s) throws ParseException {
	        	final String text = HelperString.getValidNumericString(s);
	        	
	        	if (null != text) {
	        		Number number = new BigDecimal(HelperString.getValidNumericString(s));

	                final double d = number.doubleValue() / HelperNumber.NUMBER_100.doubleValue();
	                number = new Double(d);
	                return number;
	        	}
	        	return null;
	        }
	    };
	}
	
	/**
     * Creates a {@link DateFormatter} for display-mode from a given {@link DateFormat}.
     * 
     * @param format for the formatter
     * @return created {@link DateFormatter}
     * @see DateFormat
     * @see DateFormatter
     * @since 0.9.0
     */	
	public static DateFormatter createDateFormatterDisplay(final DateFormat format) {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new DateFormatter(format) {
			private static final long serialVersionUID = 8705680761187261160L;

			{
				setCommitsOnValidEdit(true);
			}
		};
	}
	
	/**
     * Creates a {@link DateFormatter} for edit-mode from a given {@link DateFormat}.
     * 
     * @param format for the formatter
     * @return created {@link DateFormatter}
     * @see DateFormat
     * @see DateFormatter
     * @since 0.9.0
     */	
	public static DateFormatter createDateFormatterEdit(final DateFormat format) {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new DateFormatter(format) {
			private static final long serialVersionUID = 8705680761187261160L;

			{
				setCommitsOnValidEdit(true);
			}
		};
	}

	/**
     * Creates a {@link MaskFormatter} from a given pattern.
     * 
     * @param format for the formatter
     * @return created {@link MaskFormatter}
	 * @throws ParseException 
     * @see MaskFormatter
     * @since 0.9.0
     */	
	public static MaskFormatter createMaskFormatter(final String format) throws ParseException {
		if (null == format) {
			throw new RuntimeExceptionIsNull("format"); //$NON-NLS-1$
		}

		return new MaskFormatter(format) {
			private static final long serialVersionUID = 5212947957420446007L;

			{
				setCommitsOnValidEdit(true);
				setPlaceholderCharacter('_');
			}
		};
	}

	/**
     * Creates a {@link DefaultFormatter} from a given regex pattern.
     * 
     * @param regex for the formatter
     * @return created {@link DefaultFormatter}
     * @see Pattern
     * @see DefaultFormatter
     * @since 0.9.0
     */	
	public static DefaultFormatter createRegexFormatter(final Pattern regex) {
		if (null == regex) {
			throw new RuntimeExceptionIsNull("regex"); //$NON-NLS-1$
		}

		return new DefaultFormatter() {
			private static final long serialVersionUID = 2665033244806980400L;

			transient Matcher matcher;

			{
				setCommitsOnValidEdit(true);
				setOverwriteMode(false);
				matcher = regex.matcher(""); //$NON-NLS-1$
			}


			@Override
			public Object stringToValue(final String string) throws ParseException {
				if (null == string) {
			    	return null;
			    }
			    	
			    matcher.reset(string);
			
			    if (!matcher.matches()) {
			    	throw new ParseException("does not match regex", 0); //$NON-NLS-1$
			    }
			    return super.stringToValue(string);
			}
		};
	}
}
