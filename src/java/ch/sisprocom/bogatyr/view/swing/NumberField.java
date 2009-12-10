/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing;

import ch.sisprocom.bogatyr.helper.HelperString;

import javax.swing.Action;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This is a NumberField, similar to TextField, but only numeric characters are allowed.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.1.0
 */
public class NumberField extends TextField { //$JUnit$
	private static final long serialVersionUID = 4469777330124040925L;
	
	private static final Pattern PATTERN = Pattern.compile("[-%'0-9.]+"); //$NON-NLS-1$
	
	
	public NumberField() {
		this(0, HelperString.EMPTY_STRING, 255);
	}

	public NumberField(final Action action) {
		super(action);
	}

	public NumberField(final String text, final String toolTip) {
		this(text, toolTip, 255);
	}
	
	public NumberField(final String text, final String toolTip, final int columns) {
		super(text, toolTip, columns);
	}

	public NumberField(final Number number, final String toolTip, final int columns) {
		super(number.toString(), toolTip, columns);
	}
	
	/**
     * Returns a {@link Double} value from the number field.
     * 
     * @return Double from the number field 
     */	
	public Double getDoubleValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return Double.valueOf(value);
		}
		return null;
	}
	
	/**
     * Returns a {@link Integer} value from the number field.
     * 
     * @return Integer from the number field 
     */	
	public Integer getIntegerValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value).intValue();
		}
		return null;
	}
	
	/**
     * Returns a {@link Float} value from the number field.
     * 
     * @return Float from the number field 
     */	
	public Float getFloatValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return Float.valueOf(value);
		}
		return null;
	}
	
	/**
     * Returns a {@link Byte} value from the number field.
     * 
     * @return Byte from the number field 
     */	
	public Byte getByteValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value).byteValue();
		}
		return null;
	}
	
	/**
     * Returns a {@link Long} value from the number field.
     * 
     * @return Long from the number field 
     */	
	public Long getLongValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value).longValue();
		}
		return null;
	}
	
	/**
     * Returns a {@link Short} value from the number field.
     * 
     * @return Short from the number field 
     */	
	public Short getShortValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value).shortValue();
		}
		return null;
	}
	
	/**
     * Returns a {@link BigInteger} value from the number field.
     * 
     * @return BigInteger from the number field 
     */	
	public BigInteger getBigIntegerValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value).toBigInteger();
		}
		return null;
	}
	
	/**
     * Returns a {@link BigDecimal} value from the number field.
     * 
     * @return BigDecimal from the number field 
     */	
	public BigDecimal getBigDecimalValue() {
		final String value = HelperString.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value);
		}
		return null;
	}
	
	/**
     * Set the {@link Number} value of the number field.
     * 
     * @param number value for the number field 
     */	
	public void setValue(final Number number) {
		if (null == number) {
			setText(HelperString.EMPTY_STRING);
		} else {
			setText(number.toString());
		}
	}
	
	
	/*
	 * Private methods
	 */	
	static boolean isStringNumeric(final CharSequence arg) {
		if (HelperString.isValid(arg)) {
			final Matcher matcher = PATTERN.matcher(arg);

			if (matcher.matches()) {
                return true;
            }
		}
		return false;
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	protected Document createDefaultModel() {
 	      return new NumberDocument();
     }
 
//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//	}


    /*
     * Inner classes
     */
	class NumberDocument extends PlainDocument {
		private static final long serialVersionUID = 3766889554419497713L;

		@Override
		public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
			if (null != str && str.length() + offs <= getColumns() && isStringNumeric(str)) {
	 	          super.insertString(offs, str, a);
			}
		}
     }
 }
