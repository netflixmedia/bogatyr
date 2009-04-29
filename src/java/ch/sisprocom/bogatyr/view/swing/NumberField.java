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

import ch.sisprocom.bogatyr.helper.HelperGeneral;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This is a NumberField, similar to TextField, but only numeric characters are allowed.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public class NumberField extends TextField {//TODO improve Document!
	private static final long serialVersionUID = 4469777330124040925L;

	
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
     * Get the value of the number field as Double.
     * @return Double value of the number field
     */	
	public Double getDouble() {
		return Double.valueOf(HelperGeneral.getValidNumericString(getText()));
	}
	
	/**
     * Get the value of the number field as Integer.
     * @return Integer value of the number field
     */	
	public Integer getInteger() {
		return Integer.valueOf(HelperGeneral.getValidNumericString(getText()));
	}
	
	/**
     * Set the number of the number field
     * @param number Number of the number field
     */	
	public void setNumber(final Number number) {
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
