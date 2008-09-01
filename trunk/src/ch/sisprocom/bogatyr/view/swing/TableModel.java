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
package ch.sisprocom.bogatyr.view.swing;

import javax.swing.table.AbstractTableModel;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is an extended AbstractTableModel
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public class TableModel extends AbstractTableModel {
	private static final long serialVersionUID = -472247252594891753L;
	
	private final String[] columnNames;
    private final Object[][] data;
    

	public TableModel(final String[] columnNames, final Object[][] data) {
		super();
		
		this.columnNames = columnNames;
		this.data = data;
		
		init();
	}
	
	/**
	 * Counts the number of columns
	 * 
	 * @return Number of columns
	 */
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/**
	 * Counts the number of rows
	 * 
	 * @return Number of rows
	 */
	public int getRowCount() {
		if (data != null) {
			return data.length;
		}
		return 0;
	}
	
	/**
	 * Get the Object at a specific place in the data array
	 * 
	 * @param rowIndex row number
	 * @param columnIndex column number
	 * @return Returns the Object at specific place in the data array 
	 */
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public String getColumnName(final int column) {
		return columnNames[column];
	}
	
	/*
	* JTable uses this method to determine the default renderer/editor for each cell.  If we didn't implement this method,
	* then the last column would contain text ("true"/"false"), rather than a check box
	*/
	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		final Object obj = getValueAt(0, columnIndex);
		
		if (obj != null) {
			return getValueAt(0, columnIndex).getClass();
		}
//		return null;
		return String.class; //FIXME not really nice implemented... But it works fine :-)
	}
	
	/*
	* Don't need to implement this method unless your table's editable.
	*/
	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		// Note that the data/cell address is constant, no matter where the cell appears onscreen
        return columnIndex >= 2;
    }
	
	/*
	* Don't need to implement this method unless your table's data can change
	*/
	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
