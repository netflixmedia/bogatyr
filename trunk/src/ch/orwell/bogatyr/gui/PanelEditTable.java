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
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.orwell.bogatyr.Context;



/**
 * This is a panel for tables
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public abstract class PanelEditTable extends Panel implements ActionListener {
	private static final long serialVersionUID = -1516031824098742290L;
	
	protected final static String	RES_BUTTON_ADD    = "PanelEditTable.button.add"; //$NON-NLS-1$
	protected final static String	RES_BUTTON_EDIT   = "PanelEditTable.button.edit"; //$NON-NLS-1$
	protected final static String	RES_BUTTON_DELETE = "PanelEditTable.button.delete"; //$NON-NLS-1$
		
	private String title;
	private String[] columnNames;
	private Object[][] data;
	
	public PanelEditTable(String[] columnNames, Object[][] data, String title) {
		super();
		
		this.columnNames = columnNames;
		this.data = data;
		this.title = title;
		
		setTitle(this.title);
		createLayout();
    }
	
	public String[] getColumnNames() {
		return this.columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
		createLayout();
		doLayout();
	}

	public Object[][] getData() {
		return this.data;
	}

	public void setData(Object[][] data) {
		this.data = data;
		createLayout();
		doLayout();
	}
	
	
	/*
	 * Private methods
	 */
	private void createLayout() {
		removeAll();
		
		setLayout(new GridBagLayout());

        JTable table = new JTable(new TableModel(this.columnNames, this.data));

        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Buttons
		JButton buttonAdd = new JButton(Context.getInstance().getLocalizer().getValue(RES_BUTTON_ADD));
		buttonAdd.addActionListener(this);

		JButton buttonEdit = new JButton(Context.getInstance().getLocalizer().getValue(RES_BUTTON_EDIT));
		buttonEdit.addActionListener(this);
		
		JButton buttonDelete = new JButton(Context.getInstance().getLocalizer().getValue(RES_BUTTON_DELETE));
		buttonDelete.addActionListener(this);

        // Add components to this panel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
	    c.gridx = 0;
	    c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
	    c.insets = new Insets(5,5,5,5);
		add(scrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
	    c.gridheight = 1;
	    c.gridx = 11;
        add(buttonAdd, c);
        
        c.gridy = 1;
        add(buttonEdit, c);
        
        c.gridy = 2;
        add(buttonDelete, c);
	}
}
