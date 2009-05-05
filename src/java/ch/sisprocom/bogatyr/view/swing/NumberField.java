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
 * @version 20090504
 */
public class NumberField<T extends Number> extends TextField {//TODO improve Document!
	private static final long serialVersionUID = 4469777330124040925L;

	T type; 
	
	
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
     * Get the value of the number field.
     * @return value of the number field
     */	
	@SuppressWarnings("unchecked")
	public T getValue() {
		if (type instanceof Integer) {
			return (T)Integer.valueOf(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof Float) {
			return (T)Float.valueOf(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof Double) {
			return (T)Double.valueOf(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof Byte) {
			return (T)Byte.valueOf(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof Long) {
			return (T)Long.valueOf(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof Short) {
			return (T)Short.valueOf(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof BigInteger) {
			return (T)new BigInteger(HelperGeneral.getValidNumericString(getText()));
		} else if (type instanceof BigDecimal) {
			return (T)new BigDecimal(HelperGeneral.getValidNumericString(getText()));
		}
		return null;
	}
	
//	/**
//     * Get the value of the number field as an Integer.
//     * @return Integer value of the number field
//     */	
//	public Integer getInteger() {
//		return Integer.valueOf(HelperGeneral.getValidNumericString(getText()));
//	}
//
//	/**
//     * Get the value of the number field as a BigDecimal.
//     * @return BigDecimal value of the number field
//     */	
//	public BigDecimal getBigDecimal() {
//		return new BigDecimal(HelperGeneral.getValidNumericString(getText()));
//	}
//	
//	/**
//     * Get the value of the number field as a BigInteger.
//     * @return BigInteger value of the number field
//     */	
//	public BigInteger getBigInteger() {
//		return new BigInteger(HelperGeneral.getValidNumericString(getText()));
//	}
	
	/**
     * Set the value of the number field
     * @param number value of the number field
     */	
	public void setValue(final T number) {
        setText(number.toString());
	}
	
	protected static boolean isStringNumeric(final CharSequence arg) {
		if (HelperGeneral.isValidString(arg)) {
			final Pattern pattern = Pattern.compile("[-%'0-9.]+"); //$NON-NLS-1$
			final Matcher matcher = pattern.matcher(arg);

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
