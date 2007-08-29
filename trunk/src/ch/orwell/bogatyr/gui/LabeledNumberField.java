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

import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * This is an combined JLabel and NumberField
 * 
 * @author Stefan Laubenberger
 * @version 20070829
 */
public class LabeledNumberField extends Panel {
	private static final long serialVersionUID = 7708772103626571218L;

	private int paddingX = 0;
	
	private JLabel label;
	private NumberField numberField;
	
	public LabeledNumberField(String labelText, int number, int columns) {
		super();

		createLayout(labelText, number, columns);
	}

	public LabeledNumberField(String title, String labelText, int number, int columns) {
		super(title);

		createLayout(labelText, number, columns);
	}

	public LabeledNumberField(String labelText, int number, int columns, int paddingX) {
		super();
		this.paddingX = paddingX;
		createLayout(labelText, number, columns);
	}

	public LabeledNumberField(String title, String labelText, int number, int columns, int paddingX) {
		super(title);
		this.paddingX = paddingX;
		createLayout(labelText, number, columns);
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
     * Get the number of the number field
     * @return int number of the number field
     */	
	public int getNumber() {
		return (GeneralHelper.isValidString(this.numberField.getText()) ? new Integer(this.numberField.getText()).intValue() : 0);
	}

	/**
     * Set the number of the number field
     * @param number Number of the number field
     */	
	public void setNumber(int number) {
		this.numberField.setText((new Integer(number)).toString());
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(String labelText, int number, int columns) {
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
		this.numberField = new NumberField(number, columns);
		add(this.numberField, c);
	}
}