/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JScrollPane;


/**
 * This is an combined JLabel and JTextfield
 * 
 * @author Stefan Laubenberger
 * @version 20070828
 */
public class LabeledTextArea extends Panel {
	private static final long serialVersionUID = 1985953572684854791L;

	private int paddingX = 0;
	
	private JLabel label;
	private TextArea textArea;
	
	public LabeledTextArea(String labelText, String text, int rows, int columns) {
		super();

		createLayout(labelText, text, rows, columns);
	}

	public LabeledTextArea(String title, String labelText, String text, int rows, int columns) {
		super(title);

		createLayout(labelText, text, rows, columns);
	}

	public LabeledTextArea(String labelText, String text, int rows, int columns, int paddingX) {
		super();
		this.paddingX = paddingX;
		createLayout(labelText, text, rows, columns);
	}

	public LabeledTextArea(String title, String labelText, String text, int rows, int columns, int paddingX) {
		super(title);
		this.paddingX = paddingX;
		createLayout(labelText, text, rows, columns);
	}

	/**
     * Get the text of the label
     * @return String text of the label
     */	
	public String getLabelText() {
		return this.label.getText();
	}

	/**
     * Set the text of the label
     * @param labelText Text of the label
     */	
	public void setLabelText(String labelText) {
		this.label.setText(labelText);
	}

	/**
     * Get the text of the text field
     * @return String text of the text field
     */	
	public String getText() {
		return this.textArea.getText();
	}

	/**
     * Set the text of the text field
     * @param text Text of the text field
     */	
	public void setText(String text) {
		this.textArea.setText(text);
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(String labelText, String text, int rows, int columns) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//	    c.weightx = 0.5;
	    c.insets = new Insets(5,5,5,5);
	
	    this.label = new JLabel(labelText);
	    c.ipadx = this.paddingX > 0 ? this.paddingX - this.label.getPreferredSize().width : 0;
		add(this.label, c);

	    c.ipadx = 0;
		c.weightx = 1.0;
		c.gridx = 1;
		this.textArea = new TextArea(text, rows, columns);
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(this.textArea);
		add(scrollPane, c);
	}
}