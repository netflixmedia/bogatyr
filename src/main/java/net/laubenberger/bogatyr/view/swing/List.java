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

import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.JList;
import javax.swing.ListModel;
import java.util.Vector;


/**
 * This is an extended JList.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public class List extends JList implements Activatable {
	private static final long serialVersionUID = 7354802735840177105L;

	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public List() {
		super();
	}

	public List(final ListModel dataModel) {
		super(dataModel);
	}

	public List(final Object[] listData) {
		super(listData);
	}

	public List(final Vector<?> listData) {
		super(listData);
	}

	/*
	 * Own constructors
	 */

	public List(final ListModel dataModel, final String toolTip) {
		this(dataModel);
		setToolTipText(toolTip);
	}

	public List(final Object[] listData, final String toolTip) {
		this(listData);
		setToolTipText(toolTip);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}

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
		return !isNotActive;
	}

	@Override
	public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}
}