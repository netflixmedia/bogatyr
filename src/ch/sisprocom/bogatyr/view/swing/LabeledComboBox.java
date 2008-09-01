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
package ch.sisprocom.bogatyr.view.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is an combined Label and ComboBox
 * 
 * @author Stefan Laubenberger
 * @version 20080604
 */
public class LabeledComboBox extends Panel {
	private static final long serialVersionUID = -67296455436983811L;

	private Label label;
	private ComboBox comboBox;
	
	
	public LabeledComboBox(final String labelText, final Object[] data, final String toolTip) {
		super();
		createLayout(labelText, data);
		setToolTipText(toolTip);
	}

	public LabeledComboBox(final String title, final String labelText, final Object[] data, final String toolTip) {
		super(title);
		createLayout(labelText, data);
		setToolTipText(toolTip);
	}
	
	public ComboBox getComboBox() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getComboBox");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getComboBox", comboBox);  //$NON-NLS-1$

		return comboBox;
	}

	public Label getLabel() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getLabel");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getLabel", label);  //$NON-NLS-1$

		return label;
	}

	public void addActionListener(final ActionListener listener) {
        comboBox.addActionListener(listener);
	}
	
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText, final Object[] data) {
		Logger.getInstance().writeMethodEntry(getClass(), "createLayout", new Object[]{labelText, data});  //$NON-NLS-1$

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5,5,5,5);

        label = new Label(labelText);
		add(label, gbc);
		
		gbc.weightx = 1.0D;
		gbc.gridx = 1;
		if (data != null) {
            comboBox = new ComboBox(data, null);
            comboBox.setSelectedIndex(0);
		} else {
            comboBox = new ComboBox(null, null);
		}
		add(comboBox, gbc);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "createLayout");  //$NON-NLS-1$
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
		if (comboBox != null) {
            comboBox.setBackground(bg);
        }
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (label != null) {
            label.setForeground(fg);
        }
		if (comboBox != null) {
            comboBox.setForeground(fg);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
		if (comboBox != null) {
            comboBox.setFont(font);
        }
	}
}