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

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is an combined Label and CheckBox
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public class LabeledCheckBox extends Panel {
	private static final long serialVersionUID = 2215341067138215010L;

	private Label label;
	private CheckBox checkBox;

	
	public LabeledCheckBox(final String labelText, final boolean isSelected, final String toolTip) {
		super();
		createLayout(labelText, isSelected);
		setToolTipText(toolTip);
	}

	public LabeledCheckBox(final String title, final String labelText, final boolean isSelected, final String toolTip) {
		super(title);
		createLayout(labelText, isSelected);
		setToolTipText(toolTip);
	}

	public Label getLabel() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getLabel");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getLabel", label);  //$NON-NLS-1$
		
		return label;
	}

	public CheckBox getCheckBox() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getCheckBox");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getCheckBox", checkBox);  //$NON-NLS-1$

		return checkBox;
	}
	
	public boolean isSelected() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "isSelected");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "isSelected", checkBox.isSelected());  //$NON-NLS-1$

		return checkBox.isSelected();
	}
	
	public void addActionListener(final ActionListener listener) {
        checkBox.addActionListener(listener);
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText, final boolean isSelected) {
		Logger.getInstance().writeMethodEntry(getClass(), "createLayout", new Object[]{labelText, isSelected});  //$NON-NLS-1$

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5, 5, 5, 5);

        checkBox = new CheckBox(isSelected, null);
		add(checkBox, gbc);

		gbc.weightx = 1.0D;
		gbc.gridx = 1;
        label = new Label(labelText);
		add(label, gbc);
		
		Logger.getInstance().writeMethodExit(getClass(), "createLayout");  //$NON-NLS-1$
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public void setBackground(final Color bg) {
		super.setBackground(bg);
		
		if (label != null) {
            label.setBackground(bg);
        }
		if (checkBox != null) {
            checkBox.setBackground(bg);
        }
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (label != null) {
            label.setForeground(fg);
        }
		if (checkBox != null) {
            checkBox.setForeground(fg);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
		if (checkBox != null) {
            checkBox.setFont(font);
        }
	}
}