/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ch.orwell.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JButton
 * 
 * @author Stefan Laubenberger
 * @version 20080606
 */
public class Button extends JButton {
	private static final long serialVersionUID = -7231487009931166084L;
	

	public Button() {
		super();
		init();
	}

	public Button(final String title, final String toolTip) {
		super(title);
		init();
		setToolTipText(toolTip);
	}
	
	public Button(final String title, final String toolTip, final ActionListener listener) {
		this(title, toolTip);
		addActionListener(listener);
	}	
	
	public Button(final String title, final ImageIcon icon, final String toolTip, final ActionListener listener) {
		super(title, icon);
		init();
		setToolTipText(toolTip);
		addActionListener(listener);
	}	
	

	/*
	 * Private methods
	 */
	private void init() {
		setContentAreaFilled(false);
		setOpaque(true);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void setText(final String text) {super.setText("<html>" + text + "</html>");} //$NON-NLS-1$ //$NON-NLS-2$
	
	@Override
	public void setToolTipText(final String text) {
		if (text != null) {
            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
	} 
}
