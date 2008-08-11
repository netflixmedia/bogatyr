/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.view.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is a NumberField, similar to TextField, but only numeric characters are allowed
 * 
 * @author Stefan Laubenberger
 * @version 20080515
 */
public class NumberField extends TextField {
	private static final long serialVersionUID = 4469777330124040925L;

	
	public NumberField() {
		super();
	}
	
	public NumberField(final double number, final int columns, final String toolTip) {
		super(Double.toString(number), columns, toolTip);
	}
	
	/**
     * Get the number of the number field
     * @return int number of the number field
     */	
	public double getNumber() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getNumber");  //$NON-NLS-1$

		final double result = HelperGeneral.isValidString(getText()) ? Double.valueOf(getText()) : 0.0D;
		
		Logger.getInstance().writeMethodExit(this.getClass(), "getNumber", result);  //$NON-NLS-1$
		return result;
	}

	/**
     * Set the number of the number field
     * @param number Number of the number field
     */	
	public void setNumber(final double number) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setNumber", number);  //$NON-NLS-1$

        setText(Double.valueOf(number).toString());

		Logger.getInstance().writeMethodExit(this.getClass(), "setNumber");  //$NON-NLS-1$
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	protected Document createDefaultModel() {
 	      return new NumberDocument();
     }
 
	@Override
	public void setToolTipText(final String text) {
		if (text != null) {
            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}


    /*
     * Inner classes
     */
	protected class NumberDocument extends PlainDocument {
		private static final long serialVersionUID = 3766889554419497713L;

		@Override
		public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
			if (str != null && str.length() + offs <= getColumns() && HelperGeneral.isStringNumeric(str)) {
	 	          super.insertString(offs, str, a);
			}
		}
     }
 }
