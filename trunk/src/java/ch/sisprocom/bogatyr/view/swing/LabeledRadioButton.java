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

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;


/**
 * This is an combined Label and RadioButton
 * 
 * @author Stefan Laubenberger
 * @version 20081105
 */
public class LabeledRadioButton extends Panel {
	private static final long serialVersionUID = 3461718081893469685L;

	private Label label;
	private RadioButton radioButton;

	
	public LabeledRadioButton(final String labelText, final boolean isSelected, final String toolTip) {
		super();
		createLayout(labelText, isSelected);
		setToolTipText(toolTip);
	}

	public LabeledRadioButton(final String title, final String labelText, final boolean isSelected, final String toolTip) {
		super(title);
		createLayout(labelText, isSelected);
		setToolTipText(toolTip);
	}

	public Label getLabel() {
		return label;
	}

	public RadioButton getRadioButton() {
		return radioButton;
	}
	
	public boolean isSelected() {
		return radioButton.isSelected();
	}
	
	public void addActionListener(final ActionListener listener) {
        radioButton.addActionListener(listener);
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText, final boolean isSelected) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 5);

        radioButton = new RadioButton(isSelected, null);
		add(radioButton, gbc);

		gbc.weightx = 1.0D;
		gbc.gridx = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
        label = new Label(labelText);
		add(label, gbc);
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
		if (radioButton != null) {
            radioButton.setBackground(bg);
        }
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (label != null) {
            label.setForeground(fg);
        }
		if (radioButton != null) {
            radioButton.setForeground(fg);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
		if (radioButton != null) {
            radioButton.setFont(font);
        }
	}
}