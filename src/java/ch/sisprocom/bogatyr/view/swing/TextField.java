/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JTextField.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public class TextField extends JTextField {//TODO improve Document!
	private static final long serialVersionUID = 866371447844640358L;

	
	public TextField() {
        super();
    }

	public TextField(final String text, final int columns, final String toolTip) {
        super(text, columns);
        setToolTipText(toolTip);
    }
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	protected Document createDefaultModel() {
    	return new TextFieldDocument();
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
    protected class TextFieldDocument extends PlainDocument {
    	private static final long serialVersionUID = -9060548395031856257L;

		@Override
		public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {

			if (str != null && str.length() + offs <= getColumns()) {
				super.insertString(offs, str, a);
			}
		}
    }
}
