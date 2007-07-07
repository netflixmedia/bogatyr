/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import javax.swing.table.AbstractTableModel;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is an extended AbstractTableModel
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public class TableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3971895479011825010L;
	private String className;
	
	private String[] columnNames;
    private Object[][] data;
    
    /**
     * Constructs the TableModel
     * 
     * @param columnNames String array with the column names.
     * @param data 2-dimensional Object array with the data.
     */
	public TableModel(String[] columnNames, Object[][] data) {
		super();
		init();
		
		this.columnNames = columnNames;
		this.data = data;
	}
	
	/**
	 * Counts the number of columns
	 * @return Number of columns
	 */
	
	public int getColumnCount() {
		return this.columnNames.length;
	}
	
	/**
	 * Counts the number of rows
	 * @return Number of rows
	 */
	
	public int getRowCount() {
		if (this.data != null) {
			return this.data.length;
		}
		return 0;
	}
	
	/**
	 * Get the Object at a specific place in the data array
	 * @param row row number
	 * @param col column number
	 * @return Returns the Object at specific place in the data array 
	 */
	
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}
	
	
	/*
	 * Private methods
	 */
	/**
	 * Initialize the TableModel object<p>
	 * Do some logging.
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED)); //$NON-NLS-1$
	}
	
	/**
	 * Prints out all the data objects with the index of the row.
	 *
	 */
	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();
		
		for (int ii = 0; ii < numRows; ii++) {
			System.out.print("    row " + ii + ":");  //$NON-NLS-1$//$NON-NLS-2$
			for (int zz = 0; zz < numCols; zz++) {
				System.out.print("  " + this.data[ii][zz]); //$NON-NLS-1$
			}
			System.out.println();
		}
		System.out.println("--------------------------"); //$NON-NLS-1$
	}

	
	/*
	 * Overriden methods
	 */
	@Override
	public String getColumnName(int col) {
		return this.columnNames[col];
	}
	
	/*
	* JTable uses this method to determine the default renderer/
	* editor for each cell.  If we didn't implement this method,
	* then the last column would contain text ("true"/"false"),
	* rather than a check box
	*/
	@Override
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	/*
	* Don't need to implement this method unless your table's
	* editable.
	*/
	@Override
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen
		if (col < 2) {
			return false;
		}
		return true;
	}
	
	/*
	* Don't need to implement this method unless your table's
	* data can change
	*/
	@Override
	public void setValueAt(Object value, int row, int col) {
		if (Context.getInstance().isDebug()) {
			System.out.println("Setting value at " + row + "," + col  //$NON-NLS-1$//$NON-NLS-2$
			       + " to " + value //$NON-NLS-1$
			       + " (an instance of " //$NON-NLS-1$
			       + value.getClass() + ")"); //$NON-NLS-1$
		}
		
		this.data[row][col] = value;
		fireTableCellUpdated(row, col);
		
		if (Context.getInstance().isDebug()) {
			System.out.println("New value of data:"); //$NON-NLS-1$
			printDebugData();
		}
	}
}
