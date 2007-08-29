/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;


/**
 * This is a button row
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070829
 */
public class ButtonRow extends Panel {
	private static final long serialVersionUID = 1482738844411568420L;

	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	
//	private JButton button;
	
	public ButtonRow(String[] data, int orientation) {
		super();

		createLayout(data, orientation);
	}

	public ButtonRow(String title, String[] data, int orientation) {
		super(title);

		createLayout(data, orientation);
	}

	public void addActionListener(ActionListener listener) {
		Component[] components = this.getComponents();
	    for (int ii = 0; ii < components.length; ii++) {
	    	if (components[ii] instanceof JButton) {
	    		((JButton)components[ii]).addActionListener(listener);
				
			}
	    }
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(String[] data, int orientation) {
		if (orientation == VERTICAL) {
			createLayoutVertical(data);
		} else {
			createLayoutHorizontal(data);
		}
	}
	
	private void createLayoutVertical(String[] data) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//	    c.weightx = 0.5;
	    c.insets = new Insets(5,5,5,5);
	   
	    if (data != null) {
		    for (int ii = 0; ii < data.length; ii++) {
				c.gridy = ii + 1;
		    	JButton button = new JButton(data[ii]);
		    	add(button, c);
		    }
	    }
	}
	
	private void createLayoutHorizontal(String[] data) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//	    c.weightx = 0.5;
	    c.insets = new Insets(5,5,5,5);

	    if (data != null) {
		    for (int ii = 0; ii < data.length; ii++) {
				c.gridx = ii + 1;
		    	JButton button = new JButton(data[ii]);
		    	add(button, c);
		    }
	    }
	}
}