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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Format;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

import net.laubenberger.bogatyr.helper.HelperKeyboard;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Activatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JFormattedTextField.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100618)
 * @since 0.9.0
 */
public class FormattedTextField extends JFormattedTextField implements Activatable {
	private static final long serialVersionUID = -507647543464629867L;

	static final Logger log = LoggerFactory.getLogger(FormattedTextField.class);

	private boolean isNotActive;

	{
		init();
	}

	/*
	 * Superclass constructors
	 */

	public FormattedTextField() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public FormattedTextField(final AbstractFormatter formatter) {
		super(formatter);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(formatter));
	}

	public FormattedTextField(final AbstractFormatterFactory factory, final Object currentValue) {
		super(factory, currentValue);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(factory, currentValue));
	}

	public FormattedTextField(final AbstractFormatterFactory factory) {
		super(factory);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(factory));
	}

	public FormattedTextField(final Format format) {
		super(format);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(format));
	}

	public FormattedTextField(final Object value) {
		super(value);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(value));
	}


	/*
	 * Own constructors
	 */

	public FormattedTextField(final Object value, final String toolTip, final AbstractFormatterFactory formatterFactory, final boolean isNumeric) {
		this(toolTip, formatterFactory, isNumeric);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(value, toolTip, formatterFactory, isNumeric));

		setValue(value);
	}

	public FormattedTextField(final String toolTip, final AbstractFormatterFactory formatterFactory, final boolean isNumeric) {
		this(formatterFactory);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(toolTip, formatterFactory, isNumeric));

		setToolTipText(toolTip);

		if (isNumeric) {
			allowOnlyNumericInput();
		}
	}

	public FormattedTextField(final String toolTip, final Format format, final boolean isNumeric) {
		this(format);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(toolTip, format, isNumeric));

		setToolTipText(toolTip);

		if (isNumeric) {
			allowOnlyNumericInput();
		}
	}

	public FormattedTextField(final String toolTip, final AbstractFormatter formatter, final boolean isNumeric) {
		this(formatter);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(toolTip, formatter, isNumeric));

		setToolTipText(toolTip);

		if (isNumeric) {
			allowOnlyNumericInput();
		}
	}


	/*
	 * Private methods
	 */

	private void init() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent e) {
				try {
					commitEdit();
				} catch (ParseException ex) {
					if (log.isDebugEnabled()) log.debug("Could not parse data", ex); //$NON-NLS-1$
				}
			}
		});

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	private void allowOnlyNumericInput() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(final KeyEvent e) {
				final char key = e.getKeyChar();

				if (!Character.isDigit(key) &&
						'.' != key &&
						'-' != key &&
						'+' != key &&
						HelperKeyboard.isKeyPrintable(e.getKeyCode())) {

					e.consume();
				}
			}
		});

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
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
