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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is a NumberField, similar to TextField, but only numeric characters are allowed.
 * 
 * @author Stefan Laubenberger
 * @version 20090505
 */
public class NumberField extends TextField {//TODO improve Document!
	private static final long serialVersionUID = 4469777330124040925L;
	
	private static final Pattern PATTERN = Pattern.compile("[-%'0-9.]+"); //$NON-NLS-1$
	
	
	public NumberField() {
		this(0, "", Integer.MAX_VALUE); //$NON-NLS-1$
	}

	public NumberField(final String text, final String toolTip, final int columns) {
		super(text, toolTip, columns);
	}

	public NumberField(final Number number, final String toolTip, final int columns) {
		super(number.toString(), toolTip, columns);
	}
	
	/**
     * Get the Double value of the number field.
     * @return Double value of the number field
     */	
	public Double getDoubleValue() {
		String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return Double.valueOf(value);
		}
		return null;
	}
	
	/**
     * Get the Integer value of the number field.
     * @return Integer value of the number field
     */	
	public Integer getIntegerValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return Integer.valueOf(value);
		}
		return null;
	}
	
	/**
     * Get the Float value of the number field.
     * @return Float value of the number field
     */	
	public Float getFloatValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return Float.valueOf(value);
		}
		return null;
	}
	
	/**
     * Get the Byte value of the number field.
     * @return Byte value of the number field
     */	
	public Byte getByteValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return Byte.valueOf(value);
		}
		return null;
	}
	
	/**
     * Get the Long value of the number field.
     * @return Long value of the number field
     */	
	public Long getLongValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return Long.valueOf(value);
		}
		return null;
	}
	
	/**
     * Get the Short value of the number field.
     * @return Short value of the number field
     */	
	public Short getShortValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return Short.valueOf(value);
		}
		return null;
	}
	
	/**
     * Get the BigInteger value of the number field.
     * @return BigInteger value of the number field
     */	
	public BigInteger getBigIntegerValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return new BigInteger(value);
		}
		return null;
	}
	
	/**
     * Get the BigDecimal value of the number field.
     * @return BigDecimal value of the number field
     */	
	public BigDecimal getBigDecimalValue() {
		final String value = HelperGeneral.getValidNumericString(getText());
		
		if (null != value) {
			return new BigDecimal(value);
		}
		return null;
	}
	
	/**
     * Set the value of the number field
     * @param number value of the number field
     */	
	public void setValue(final Number number) {
        setText(number.toString());
	}
	
	protected static boolean isStringNumeric(final CharSequence arg) {
		if (HelperGeneral.isValidString(arg)) {
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
	private class NumberDocument extends PlainDocument {
		private static final long serialVersionUID = 3766889554419497713L;

		@Override
		public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
			if (str != null && str.length() + offs <= getColumns() && isStringNumeric(str)) {
	 	          super.insertString(offs, str, a);
			}
		}
     }
 }
