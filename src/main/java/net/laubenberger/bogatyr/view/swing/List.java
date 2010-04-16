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
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.JList;
import javax.swing.ListModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger log = LoggerFactory.getLogger(List.class);
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public List() {
		super();
		log.trace(HelperLog.constructor());
	}

	public List(final ListModel dataModel) {
		super(dataModel);
		log.trace(HelperLog.constructor(dataModel));
	}

	public List(final Object[] listData) {
		super(listData);
		log.trace(HelperLog.constructor(listData));
	}

	public List(final Vector<?> listData) {
		super(listData);
		log.trace(HelperLog.constructor(listData));
	}

	/*
	 * Own constructors
	 */

	public List(final ListModel dataModel, final String toolTip) {
		this(dataModel);
		log.trace(HelperLog.constructor(dataModel, toolTip));
		
		setToolTipText(toolTip);
	}

	public List(final Object[] listData, final String toolTip) {
		this(listData);
		log.trace(HelperLog.constructor(listData, toolTip));
		
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
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(!isNotActive));
		return !isNotActive;
	}

	@Override
	public void setActive(final boolean isActive) {
		log.debug(HelperLog.methodStart(isActive));
		
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
		
		log.debug(HelperLog.methodExit());
	}
}