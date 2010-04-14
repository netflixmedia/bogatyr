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
package ch.customcode.bogatyr.view.swing;

import javax.swing.JTextField;
import javax.swing.text.Document;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.Activatable;


/**
 * This is an extended JTextField.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.1.0
 */
public class TextField extends JTextField implements Activatable {
	private static final long serialVersionUID = 866371447844640358L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
	public TextField() {
		super();
	}

	public TextField(final Document doc, final String text, final int columns) {
		super(doc, text, columns);
	}

	public TextField(final int columns) {
		super(columns);
	}

	public TextField(final String text, final int columns) {
		super(text, columns);
	}

	public TextField(final String text) {
		super(text);
	}
	
	/*
	 * Own constructors
	 */
	public TextField(final String text, final String toolTip) {
        this(text);
        setToolTipText(toolTip);
    }

	public TextField(final String text, final String toolTip, final Document document, final int columns) {
        this(document, text, columns);
        setToolTipText(toolTip);
    }

	public TextField(final String toolTip, final Document document, final int columns) {
        this();
        setDocument(document);
        setColumns(columns);
        setToolTipText(toolTip);
    }
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
//	@Override
//	protected Document createDefaultModel() {
//    	return DocumentFactory.createNumberDocument(getColumns());
//    }

	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	@Override
    public boolean isActive() {
		return !isNotActive;
	}

	@Override
    public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}

//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//	}

//
//    /*
//     * Inner classes
//     */
//    class TextFieldDocument extends PlainDocument {
//    	private static final long serialVersionUID = -9060548395031856257L;
//
//		@Override
//		public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
//
//			if (null != str && str.length() + offs <= getColumns()) {
//				super.insertString(offs, str, a);
//			}
//		}
//    }
}
