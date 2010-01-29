/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Format;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

import ch.sisprocom.bogatyr.helper.HelperKeyboard;
import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JFormattedTextField.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100128)
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

	public FormattedTextField(AbstractFormatter formatter) {
		super(formatter);
		init();
	}

	public FormattedTextField(AbstractFormatterFactory factory, Object currentValue) {
		super(factory, currentValue);
		init();
	}

	public FormattedTextField(AbstractFormatterFactory factory) {
		super(factory);
		init();
	}

	public FormattedTextField(Format format) {
		super(format);
		init();
	}

	public FormattedTextField(Object value) {
		super(value);
		init();
	}
	
	
	/*
	 * Own constructors
	 */
	public FormattedTextField(Object value, final String toolTip, AbstractFormatterFactory formatterFactory, final boolean isNumeric) {
		this(toolTip, formatterFactory, isNumeric);
		setValue(value);
	}

	public FormattedTextField(final String toolTip, AbstractFormatterFactory formatterFactory, final boolean isNumeric) {
		super(formatterFactory);
		setToolTipText(toolTip);
		init();
		
		if (isNumeric) {
			addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					final char key = e.getKeyChar();
					
					if (!Character.isDigit(key) &&
						'.' != key &&
						'-' != key &&
						'+' != key &&
						!HelperKeyboard.isNonPrintableKey(e.getKeyCode())) {
						
						e.consume();
					}
				}
			});
		}
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					commitEdit();
				} catch (ParseException e) {
					//e.printStackTrace();
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
