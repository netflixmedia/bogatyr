/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

import javax.swing.JPasswordField;
import javax.swing.text.Document;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Activatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JPasswordField.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.1.0
 */
public class PasswordField extends JPasswordField implements Activatable {
	private static final long serialVersionUID = 4337982428755317915L;

	private static final Logger log = LoggerFactory.getLogger(PasswordField.class);

	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public PasswordField() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public PasswordField(final Document doc, final String text, final int columns) {
		super(doc, text, columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(doc, text, columns));
	}

	public PasswordField(final int columns) {
		super(columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(columns));
	}

	public PasswordField(final String text, final int columns) {
		super(text, columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, columns));
	}

	/*
	 * Own constructors
	 */

	public PasswordField(final String toolTip) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(toolTip));

		setToolTipText(toolTip);
	}

//	/**
//	 * Checks if the entered password is correct.
//	 *
//	 * @param correctPassword from the user (e.g. from the db).
//	 * @return true/false
//	 */
//	public boolean isPasswordCorrect(final char[] correctPassword) {
//		return getPassword().length == correctPassword.length && Arrays.equals(getPassword(), correctPassword);
//	}


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
