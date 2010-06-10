/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.view.swing;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;


/**
 * This is an extended JTable.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.7.0
 */
public class Table extends JTable implements Activatable {
	private static final long serialVersionUID = -1201835867524275584L;

	private static final Logger log = LoggerFactory.getLogger(Table.class);

	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public Table() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public Table(final int numRows, final int numColumns) {
		super(numRows, numColumns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(numRows, numColumns));
	}

	public Table(final Object[][] rowData, final Object[] columnNames) {
		super(rowData, columnNames);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(rowData, columnNames));
	}

	public Table(final TableModel dm, final TableColumnModel cm, final ListSelectionModel sm) {
		super(dm, cm, sm);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(dm, cm, sm));
	}

	public Table(final TableModel dm, final TableColumnModel cm) {
		super(dm, cm);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(dm, cm));
	}

	public Table(final TableModel dm) {
		super(dm);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(dm));
	}

	public Table(final Vector<?> rowData, final Vector<?> columnNames) {
		super(rowData, columnNames);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(rowData, columnNames));
	}


	/*
	 * Overridden methods
	 */

	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	public boolean isActive() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(!isNotActive));
		return !isNotActive;
	}

	@Override
	public void setActive(final boolean isActive) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(isActive));

		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}