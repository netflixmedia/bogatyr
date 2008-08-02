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

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import ch.orwell.bogatyr.helper.exception.HelperException;
import ch.orwell.bogatyr.helper.localizer.Localizer;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is a panel for tables
 * 
 * @author Stefan Laubenberger
 * @version 20080515
 */
public abstract class PanelTableEditTemplate extends Panel implements ActionListener {
	private static final long serialVersionUID = 4152134766882997391L;
	
	private static final String RES_BUTTON_ADD    = "PanelEditTable.button.add"; //$NON-NLS-1$
	private static final String RES_BUTTON_EDIT   = "PanelEditTable.button.edit"; //$NON-NLS-1$
	private static final String RES_BUTTON_DELETE = "PanelEditTable.button.delete"; //$NON-NLS-1$

	private JTable table;
	private Button buttonAdd;
	private Button buttonEdit;
	private Button buttonDelete;

	private String[] columnNames;
	private Object[][] data;
	
	
	protected PanelTableEditTemplate(final String[] columnNames, final Object[][] data) {
		super();
		
		this.columnNames = columnNames;
		this.data = data;
		createLayout();
    }

	protected PanelTableEditTemplate(final String[] columnNames, final Object[][] data, final String title) {
		this(columnNames, data);

		setTitle(title);
		createLayout();
    }
	
	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(final String[] columnNames) {
		this.columnNames = columnNames;
		createLayout();
		doLayout();
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(final Object[][] data) {
		this.data = data;
		createLayout();
		doLayout();
	}
	
	protected JTable getTable() {
		return table;
	}

	public Button getButtonAdd() {
		return buttonAdd;
	}

	public Button getButtonDelete() {
		return buttonDelete;
	}

	public Button getButtonEdit() {
		return buttonEdit;
	}

	protected void createLayout() {
		removeAll();

        table = createTable(new TableModel(columnNames, data));

//        this.table.setFillsViewportHeight(true); // Java 1.6
//        this.table.setAutoCreateRowSorter(true); // Java 1.6

        // Create the scroll pane and add the table to it
        final ScrollPane scrollPane = new ScrollPane(table);
        
        // Buttons
        buttonAdd = new Button(Localizer.getInstance().getValue(RES_BUTTON_ADD), null, this);

//        this.buttonEdit = new JButton("<html><b><u>T</u>wo</b><br>lines</html>"); //COOL!
        buttonEdit = new Button(Localizer.getInstance().getValue(RES_BUTTON_EDIT), null, this);

        buttonDelete = new Button(Localizer.getInstance().getValue(RES_BUTTON_DELETE), null, this);

        // Add components to this panel
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0D;
        gbc.weighty = 1.0D;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
//        c.gridwidth = 1;
        gbc.gridheight = 3;
	    gbc.insets = new Insets(5,5,5,5);
		add(scrollPane, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.0D;
	    gbc.gridheight = 1;
	    gbc.gridx = 1;
        add(buttonAdd, gbc);
        
        gbc.gridy = 1;
        add(buttonEdit, gbc);
        
        gbc.gridy = 2;
        add(buttonDelete, gbc);
	}
	
	protected static JTable createTable(final TableModel tableModel) {
		return new JTable(tableModel);
	}
	
	protected abstract void executeActionAdd();

	protected abstract void executeActionEdit();
	
	protected abstract void executeActionDelete();

	
	/*
	 * Implemented methods
	 */
	public void actionPerformed(final ActionEvent event) {
		if (event.getActionCommand().equals(Localizer.getInstance().getValue(RES_BUTTON_ADD))) {
			executeActionAdd();
		} else if (event.getActionCommand().equals(Localizer.getInstance().getValue(RES_BUTTON_EDIT))) {
			executeActionEdit();
		} else if (event.getActionCommand().equals(Localizer.getInstance().getValue(RES_BUTTON_DELETE))) {
			executeActionDelete();
		} else {
			Logger.getInstance().writeException(this, "actionPerformed", HelperException.EX_UNKNOWN_EVENT + ": " + event, null); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}