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

import javax.swing.JTextArea;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is an extended JTextArea
 * 
 * @author Stefan Laubenberger
 * @version 20080613
 */
public class TextArea extends JTextArea {
	private static final long serialVersionUID = 8509257459382968021L;


    public TextArea() {
        super();
        init();
    }
    
	public TextArea(final String toolTip) {
        this();
        setToolTipText(toolTip);
    }

    public TextArea(final String text, final int rows, final int columns, final String toolTip) {
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
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
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
