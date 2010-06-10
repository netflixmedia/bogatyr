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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Activatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JRadioButton.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.2.0
 */
public class RadioButton extends JRadioButton implements Activatable {
	private static final long serialVersionUID = 8676540667794440059L;

	private static final Logger log = LoggerFactory.getLogger(RadioButton.class);

	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public RadioButton() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public RadioButton(final Action action) {
		super(action);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(action));
	}

	public RadioButton(final Icon icon, final boolean selected) {
		super(icon, selected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(icon, selected));
	}

	public RadioButton(final Icon icon) {
		super(icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(icon));
	}

	public RadioButton(final String text, final boolean selected) {
		super(text, selected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, selected));
	}

	public RadioButton(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon, selected));
	}

	public RadioButton(final String text, final Icon icon) {
		super(text, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon));
	}

	public RadioButton(final String text) {
		super(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text));
	}

	/*
	 * Own constructors
	 */

	public RadioButton(final boolean isSelected) {
		this();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(isSelected));

		setSelected(isSelected);
	}

	public RadioButton(final boolean isSelected, final Action action) {
		this(action);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(isSelected, action));

		setSelected(isSelected);
	}

	public RadioButton(final String text, final boolean isSelected, final String toolTip) {
		this(text, isSelected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, isSelected, toolTip));

		setToolTipText(toolTip);
	}

	public RadioButton(final boolean isSelected, final String toolTip) {
		this();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(isSelected, toolTip));

		setSelected(isSelected);
		setToolTipText(toolTip);
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