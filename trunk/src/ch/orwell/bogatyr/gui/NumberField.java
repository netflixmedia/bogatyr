/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * This is a NumberField, similar to TextField, but only numeric characters are allowed
 * 
 * @author Stefan Laubenberger
 * @version 20070829
 */
public class NumberField extends JTextField {
	private static final long serialVersionUID = 5310015371075554007L;

	public NumberField(int number, int columns) {
		super((new Integer(number)).toString(), columns);
	}
 
     @Override
	protected Document createDefaultModel() {
 	      return new NumberDocument();
     }
 
     class NumberDocument extends PlainDocument {
		private static final long serialVersionUID = 3766889554419497713L;

		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str != null && (str.length() + offs) <= getColumns() && GeneralHelper.isStringNumeric(str)) {
	 	          super.insertString(offs, str, a);
			}
		}
     }
 }
