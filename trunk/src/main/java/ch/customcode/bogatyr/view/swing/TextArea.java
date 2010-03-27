/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.view.swing;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.Activatable;

import javax.swing.JTextArea;
import javax.swing.text.Document;


/**
 * This is an extended JTextArea.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.1.0
 */
public class TextArea extends JTextArea implements Activatable {
	private static final long serialVersionUID = 8509257459382968021L;
	
	private boolean isNotActive;

    {
		setLineWrap(true);
		setWrapStyleWord(true);
	}
	
	/*
	 * Superclass constructors
	 */
    public TextArea() {
        super();
    }
    
	public TextArea(final Document doc, final String text, final int rows, final int columns) {
		super(doc, text, rows, columns);
	}

	public TextArea(final int rows, final int columns) {
		super(rows, columns);
	}

	public TextArea(final String text, final int rows, final int columns) {
		super(text, rows, columns);
	}

	public TextArea(final Document doc) {
		super(doc);
	}
	
	/*
	 * Own constructors
	 */
	public TextArea(final String toolTip) {
        this();
        setToolTipText(toolTip);
    }

    public TextArea(final String text, final String toolTip, final int rows, final int columns) {
        this(text, rows, columns);
        setToolTipText(toolTip);
    }
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
	@Override
	public void append(final String str) {
		super.append(str);
        setCaretPosition(getDocument().getLength());
	}

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
}
