/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;


/**
 * This is an combined JLabel and JTextfield
 * 
 * @author Stefan Laubenberger
 * @version 20070828
 */
public class LabeledTextField extends Panel {
	private static final long serialVersionUID = 1525102676692389022L;

	private int paddingX = 0;
	
	private JLabel label;
	private TextField textField;
	
	public LabeledTextField(String labelText, String text, int columns) {
		super();

		createLayout(labelText, text, columns);
	}

	public LabeledTextField(String title, String labelText, String text, int columns) {
		super(title);

		createLayout(labelText, text, columns);
	}

	public LabeledTextField(String labelText, String text, int columns, int paddingX) {
		super();
		this.paddingX = paddingX;
		createLayout(labelText, text, columns);
	}

	public LabeledTextField(String title, String labelText, String text, int columns, int paddingX) {
		super(title);
		this.paddingX = paddingX;
		createLayout(labelText, text, columns);
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
		return this.textField.getText();
	}

	/**
     * Set the text of the text field
     * @param text Text of the text field
     */	
	public void setText(String text) {
		this.textField.setText(text);
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(String labelText, String text, int columns) {
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
		this.textField = new TextField(text, columns);
		add(this.textField, c);
	}
}