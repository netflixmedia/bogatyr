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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
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
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.Activatable;


/**
 * This is an extended JFormattedTextField.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public class FormattedTextField extends JFormattedTextField implements Activatable {
	private static final long serialVersionUID = -507647543464629867L;

	private boolean isNotActive;


	/*
	 * Superclass constructors
	 */

	public FormattedTextField() {
		super();
		init();
	}

	public FormattedTextField(final AbstractFormatter formatter) {
		super(formatter);
		init();
	}

	public FormattedTextField(final AbstractFormatterFactory factory, final Object currentValue) {
		super(factory, currentValue);
		init();
	}

	public FormattedTextField(final AbstractFormatterFactory factory) {
		super(factory);
		init();
	}

	public FormattedTextField(final Format format) {
		super(format);
		init();
	}

	public FormattedTextField(final Object value) {
		super(value);
		init();
	}


	/*
	 * Own constructors
	 */

	public FormattedTextField(final Object value, final String toolTip, final AbstractFormatterFactory formatterFactory, final boolean isNumeric) {
		this(toolTip, formatterFactory, isNumeric);
		setValue(value);
	}

	public FormattedTextField(final String toolTip, final AbstractFormatterFactory formatterFactory, final boolean isNumeric) {
		this(formatterFactory);
		setToolTipText(toolTip);

		if (isNumeric) {
			allowOnlyNumericInput();
		}
	}

	public FormattedTextField(final String toolTip, final Format format, final boolean isNumeric) {
		this(format);
		setToolTipText(toolTip);

		if (isNumeric) {
			allowOnlyNumericInput();
		}
	}

	public FormattedTextField(final String toolTip, final AbstractFormatter formatter, final boolean isNumeric) {
		this(formatter);
		setToolTipText(toolTip);

		if (isNumeric) {
			allowOnlyNumericInput();
		}
	}


	/*
	 * Private methods
	 */

	private void init() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent arg0) {
				try {
					commitEdit();
				} catch (ParseException e) {
					//e.printStackTrace();
				}
			}
		});
	}

	private void allowOnlyNumericInput() {
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
