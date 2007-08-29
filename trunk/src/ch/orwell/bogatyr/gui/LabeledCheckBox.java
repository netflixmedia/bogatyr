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

import javax.swing.JCheckBox;
import javax.swing.JLabel;


/**
 * This is an combined JLabel and JCheckBox
 * 
 * @author Stefan Laubenberger
 * @version 20070828
 */
public class LabeledCheckBox extends Panel {
	private static final long serialVersionUID = 2215341067138215010L;

	private int paddingX = 0;
	
	private JLabel label;
	private JCheckBox checkBox;
	
	public LabeledCheckBox(String labelText, boolean flag) {
		super();

		createLayout(labelText, flag);
	}

	public LabeledCheckBox(String title, String labelText, boolean flag) {
		super(title);

		createLayout(labelText, flag);
	}

	public LabeledCheckBox(String labelText, boolean flag, int paddingX) {
		super();
		this.paddingX = paddingX;
		createLayout(labelText, flag);
	}

	public LabeledCheckBox(String title, String labelText, boolean flag, int paddingX) {
		super(title);
		this.paddingX = paddingX;
		createLayout(labelText, flag);
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
     * Get the status of the check box
     * @return boolean status of the check box
     */	
	public boolean isSelected() {
		return this.checkBox.isSelected();
	}

	/**
     * Set the status of the check box
     * @param flag status of the check box
     */	
	public void setText(boolean flag) {
		this.checkBox.setSelected(flag);
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(String labelText, boolean flag) {
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
		this.checkBox = new JCheckBox("", flag);
		add(this.checkBox, c);
	}
}