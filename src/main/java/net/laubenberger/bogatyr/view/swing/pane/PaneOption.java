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

package net.laubenberger.bogatyr.view.swing.pane;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JOptionPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100516)
 * @since 0.7.0
 */
public class PaneOption extends JOptionPane {
	private static final long serialVersionUID = -4947729751470298861L;

	private static final Logger log = LoggerFactory.getLogger(PaneOption.class);

	/*
	 * Superclass constructors
	 */

	public PaneOption() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public PaneOption(final Object message, final int messageType, final int optionType,
							final Icon icon, final Object[] options, final Object initialValue) {
		super(message, messageType, optionType, icon, options, initialValue);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(message, messageType, optionType, icon, options, initialValue));
	}

	public PaneOption(final Object message, final int messageType, final int optionType,
							final Icon icon, final Object[] options) {
		super(message, messageType, optionType, icon, options);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(message, messageType, optionType, icon, options));
	}

	public PaneOption(final Object message, final int messageType, final int optionType, final Icon icon) {
		super(message, messageType, optionType, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(message, messageType, optionType, icon));
	}

	public PaneOption(final Object message, final int messageType, final int optionType) {
		super(message, messageType, optionType);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(message, messageType, optionType));
	}

	public PaneOption(final Object message, final int messageType) {
		super(message, messageType);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(message, messageType));
	}

	public PaneOption(final Object message) {
		super(message);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(message));
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
		super.setEnabled(isEnabled);

		for (final Component component : getComponents()) {
			component.setEnabled(isEnabled);
		}
	}
}