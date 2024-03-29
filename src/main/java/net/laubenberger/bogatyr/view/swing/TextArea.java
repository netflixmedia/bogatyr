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

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.JTextArea;
import javax.swing.text.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JTextArea.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.1.0
 */
public class TextArea extends JTextArea implements Activatable {
	private static final long serialVersionUID = 8509257459382968021L;

	private static final Logger log = LoggerFactory.getLogger(TextArea.class);

	private boolean isNotActive;

	{
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	/*
	 * Superclass constructors
	 */

	public TextArea() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public TextArea(final Document doc, final String text, final int rows, final int columns) {
		super(doc, text, rows, columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(doc, text, rows, columns));
	}

	public TextArea(final int rows, final int columns) {
		super(rows, columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(rows, columns));
	}

	public TextArea(final String text, final int rows, final int columns) {
		super(text, rows, columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, rows, columns));
	}

	public TextArea(final Document doc) {
		super(doc);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(doc));
	}

	/*
	 * Own constructors
	 */

	public TextArea(final String toolTip) {
		this();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(toolTip));

		setToolTipText(toolTip);
	}

	public TextArea(final String text, final String toolTip, final int rows, final int columns) {
		this(text, rows, columns);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, toolTip, rows, columns));

		setToolTipText(toolTip);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public void append(final String str) {
		super.append(str);
		setCaretPosition(getDocument().getLength());
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
