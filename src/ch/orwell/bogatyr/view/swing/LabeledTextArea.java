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

import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is an combined Label and TextArea
 * 
 * @author Stefan Laubenberger
 * @version 20080604
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "getLabel");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getLabel", label);  //$NON-NLS-1$

		return label;
	}

	public TextArea getTextArea() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getTextArea");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getTextArea", textArea);  //$NON-NLS-1$

		return textArea;
	}
	
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText, final String text, final int rows, final int columns) {
		Logger.getInstance().writeMethodEntry(getClass(), "createLayout", new Object[]{labelText, text, rows, columns});  //$NON-NLS-1$

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
		
		Logger.getInstance().writeMethodExit(this.getClass(), "createLayout");  //$NON-NLS-1$
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
		if (textArea != null) {
            textArea.setBackground(color);
        }
	}
	
	@Override
	public void setForeground(final Color color) {
		super.setForeground(color);

		if (label != null) {
            label.setForeground(color);
        }
		if (textArea != null) {
            textArea.setForeground(color);
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