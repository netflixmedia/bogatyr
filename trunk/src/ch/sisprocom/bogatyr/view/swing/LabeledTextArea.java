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


/**
 * This is an combined Label and TextArea
 * 
 * @author Stefan Laubenberger
 * @version 20081026
 */
public class LabeledTextArea extends Panel {
	private static final long serialVersionUID = -3385104817739873049L;

	private Label label;
	private TextArea textArea;
	
	
	public LabeledTextArea(final String labelText, final String text, final int rows, final int columns, final String toolTip) {
		super();
		createLayout(labelText, text, rows, columns);
		setToolTipText(toolTip);
	}

	public LabeledTextArea(final String title, final String labelText, final String text, final int rows, final int columns, final String toolTip) {
		super(title);
		createLayout(labelText, text, rows, columns);
		setToolTipText(toolTip);
	}
	
	public Label getLabel() {
		return label;
	}

	public TextArea getTextArea() {
		return textArea;
	}
	
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText, final String text, final int rows, final int columns) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(5, 5, 5, 5);

        label = new Label(labelText);
		add(label, gbc);

		gbc.weightx = 1.0D;
		gbc.gridx = 1;
        textArea = new TextArea(text, rows, columns, null);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		final ScrollPane scrollPane = new ScrollPane(textArea);
		add(scrollPane, gbc);
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
		if (textArea != null) {
            textArea.setBackground(bg);
        }
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (label != null) {
            label.setForeground(fg);
        }
		if (textArea != null) {
            textArea.setForeground(fg);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
		if (textArea != null) {
            textArea.setFont(font);
        }
	}
}