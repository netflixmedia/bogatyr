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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.exception.ExceptionHelper;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is a panel for tables
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070829
 */
public abstract class PanelEditTable extends Panel implements ActionListener {
	private final static String RES_BUTTON_ADD    = "PanelEditTable.button.add"; //$NON-NLS-1$
	private final static String RES_BUTTON_EDIT   = "PanelEditTable.button.edit"; //$NON-NLS-1$
	private final static String RES_BUTTON_DELETE = "PanelEditTable.button.delete"; //$NON-NLS-1$

	private JTable table;
	private JButton buttonAdd;
	private JButton buttonEdit;
	private JButton buttonDelete;

	private String[] columnNames;
	private Object[][] data;
	
	public PanelEditTable(String[] columnNames, Object[][] data) {
		super();
		
		this.columnNames = columnNames;
		this.data = data;
		createLayout();
    }

	public PanelEditTable(String[] columnNames, Object[][] data, String title) {
		super();

		this.columnNames = columnNames;
		this.data = data;
		setTitle(title);
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
	
	protected JTable getTable() {
		return this.table;
	}

	public JButton getButtonAdd() {
		return this.buttonAdd;
	}

	public JButton getButtonDelete() {
		return this.buttonDelete;
	}

	public JButton getButtonEdit() {
		return this.buttonEdit;
	}

	protected void createLayout() {
		removeAll();
		
		setLayout(new GridBagLayout());

        this.table = createTable(new TableModel(this.columnNames, this.data));

        table.setFillsViewportHeight(true); // Java 1.6
        table.setAutoCreateRowSorter(true); // Java 1.6

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(this.table);
        
        // Buttons
        this.buttonAdd = new JButton(Context.getInstance().getLocalizer().getValue(RES_BUTTON_ADD));
        this.buttonAdd.addActionListener(this);

//        this.buttonEdit = new JButton("<html><b><u>T</u>wo</b><br>lines</html>"); //COOL!
        this.buttonEdit = new JButton(Context.getInstance().getLocalizer().getValue(RES_BUTTON_EDIT));
        this.buttonEdit.addActionListener(this);
		
        this.buttonDelete = new JButton(Context.getInstance().getLocalizer().getValue(RES_BUTTON_DELETE));
        this.buttonDelete.addActionListener(this);

        // Add components to this panel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
	    c.gridx = 0;
	    c.gridy = 0;
//        c.gridwidth = 1;
        c.gridheight = 3;
	    c.insets = new Insets(5,5,5,5);
		add(scrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
	    c.gridheight = 1;
	    c.gridx = 1;
        add(this.buttonAdd, c);
        
        c.gridy = 1;
        add(this.buttonEdit, c);
        
        c.gridy = 2;
        add(this.buttonDelete, c);
	}
	
	protected JTable createTable(TableModel tableModel) {
		return new JTable(tableModel);
	}
	
	protected abstract void executeActionAdd();

	protected abstract void executeActionEdit();
	
	protected abstract void executeActionDelete();

	
	/*
	 * Implemented methods
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(Context.getInstance().getLocalizer().getValue(RES_BUTTON_ADD))) {
			executeActionAdd();
		} else if (event.getActionCommand().equals(Context.getInstance().getLocalizer().getValue(RES_BUTTON_EDIT))) {
			executeActionEdit();
		} else if (event.getActionCommand().equals(Context.getInstance().getLocalizer().getValue(RES_BUTTON_DELETE))) {
			executeActionDelete();
		} else {
			Logger.getInstance().writeException(this.className + "::actionPerformed", ExceptionHelper.EX_UNKNOWN_EVENT + ": " + event); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}