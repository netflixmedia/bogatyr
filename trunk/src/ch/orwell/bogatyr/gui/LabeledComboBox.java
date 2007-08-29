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
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;


/**
 * This is an combined JLabel and JComboBox
 * 
 * @author Stefan Laubenberger
 * @version 20070823
 */
public class LabeledComboBox extends Panel {
	private static final long serialVersionUID = 1525102676692389022L;

	private int paddingX = 0;

	private JLabel label;
	private JComboBox comboBox;
	
	public LabeledComboBox(String labelText, Object[] data) {
		super();
		createLayout(labelText, data);
	}

	public LabeledComboBox(String title, String labelText, Object[] data) {
		super(title);

		createLayout(labelText, data);
	}

	public LabeledComboBox(String labelText, Object[] data, int paddingX) {
		super();
		this.paddingX = paddingX;
		createLayout(labelText, data);
	}

	public LabeledComboBox(String title, String labelText, Object[] data, int paddingX) {
		super(title);
		this.paddingX = paddingX;
		createLayout(labelText, data);
	}

	public void addActionListener(ActionListener listener) {
		this.comboBox.addActionListener(listener);
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
     * Get the selected item of the combo box
     * @return Object selected item
     */	
	public Object getSelectedItem() {
		return this.comboBox.getSelectedItem();
	}
  
	/**
     * Set the selected item of the combo box
     * @param obj Object to select an item
     */	
	public void setSelectedItem(Object obj) {
		this.comboBox.setSelectedItem(obj);
	}

	
	
	/*
	 * Private methods
	 */
	private void createLayout(String labelText, Object[] data) {
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
		if (data != null) {
			this.comboBox = new JComboBox(data);
			this.comboBox.setSelectedIndex(0);
		} else {
			this.comboBox = new JComboBox();
		}
		add(this.comboBox, c);
	}
}