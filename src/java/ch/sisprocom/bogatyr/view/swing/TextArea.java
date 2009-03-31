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

import javax.swing.JTextArea;
import javax.swing.text.Document;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JTextArea.
 * 
 * @author Stefan Laubenberger
 * @version 20090309
 */
public class TextArea extends JTextArea {
	private static final long serialVersionUID = 8509257459382968021L;


    public TextArea() {
        super();
        init();
    }
    
	public TextArea(final Document doc) {
		super(doc);
	}

	public TextArea(final String toolTip) {
        this();
        setToolTipText(toolTip);
    }

    public TextArea(final String text, final String toolTip, final int rows, final int columns) {
        super(text, rows, columns);
        setToolTipText(toolTip);
        init();
    }
	
    
    /*
	 * Private methods
	 */
	private void init() {
		setLineWrap(true);
		setWrapStyleWord(true);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void append(final String str) {
		super.append(str);
        setCaretPosition(getDocument().getLength());
	}
	
	@Override
	public void setToolTipText(final String text) {
		if (text != null) {
            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}
}
