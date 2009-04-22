/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JTable.
 * 
 * @author Stefan Laubenberger
 * @version 20090422
 */
public class Table extends JTable {
	private static final long serialVersionUID = -1201835867524275584L;
	

	public Table() {
		super();
	}
	
	public Table(int numRows, int numColumns) {
		super(numRows, numColumns);
	}

	public Table(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}

	public Table(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
	}

	public Table(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
	}

	public Table(TableModel dm) {
		super(dm);
	}

	public Table(Vector<?> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}