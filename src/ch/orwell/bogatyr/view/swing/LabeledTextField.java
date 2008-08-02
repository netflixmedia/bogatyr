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


/**
 * This is an combined Label and TextField
 * 
 * @author Stefan Laubenberger
 * @version 20080604
 */
public class LabeledTextField extends Panel {
	private static final long serialVersionUID = 1310593497620798003L;
	
	private Label label;
	private TextField textField;
	
	
	public LabeledTextField(final String labelText, final String text, final int columns, final String toolTip) {
		super();
		createLayout(labelText, text, columns);
		setToolTipText(toolTip);
	}

	public LabeledTextField(final String title, final String labelText, final String text, final int columns, final String toolTip) {
		super(title);
		createLayout(labelText, text, columns);
		setToolTipText(toolTip);
	}

	public Label getLabel() {
		return label;
	}

	public TextField getTextField() {
		return textField;
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText, final String text, final int columns) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5,5,5,5);

        label = new Label(labelText);
		add(label, gbc);
		
		gbc.weightx = 1.0D;
		gbc.gridx = 1;
        textField = new TextField(text, columns, null);
		add(textField, gbc);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public void setBackground(final Color color) {
		super.setBackground(color);
		
		if (label != null) {
            label.setBackground(color);
        }
		if (textField != null) {
            textField.setBackground(color);
        }
	}
	
	@Override
	public void setForeground(final Color color) {
		super.setForeground(color);

		if (label != null) {
            label.setForeground(color);
        }
		if (textField != null) {
            textField.setForeground(color);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
		if (textField != null) {
            textField.setFont(font);
        }
	}
}