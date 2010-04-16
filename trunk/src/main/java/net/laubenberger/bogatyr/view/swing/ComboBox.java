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
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;


/**
 * This is an extended JComboBox.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.2.0
 */
public class ComboBox extends JComboBox implements Activatable {
	private static final long serialVersionUID = -3870596701286078140L;

	private static final Logger log = LoggerFactory.getLogger(ComboBox.class);
	
	private boolean isNotActive;

	{
		createLayout();
	}
	
	/*
	 * Superclass constructors
	 */

	public ComboBox() {
		super();
		log.trace(HelperLog.constructor());
	}

	public ComboBox(final ComboBoxModel model) {
		super(model);
		log.trace(HelperLog.constructor(model));
	}

	public ComboBox(final Object[] data) {
		super(data);
		log.trace(HelperLog.constructor(data));
	}

	public ComboBox(final Vector<?> data) {
		super(data);
		log.trace(HelperLog.constructor(data));
	}

	/*
	 * Own constructors
	 */

	public ComboBox(final ComboBoxModel model, final String toolTip) {
		this(model);
		log.trace(HelperLog.constructor(model, toolTip));
		
		setToolTipText(toolTip);
	}

	public ComboBox(final Object[] data, final String toolTip) {
		this(data);
		log.trace(HelperLog.constructor(data, toolTip));
		
		if (null != data) {
			setSelectedIndex(-1);
		}

		setToolTipText(toolTip);
	}


	/*
	 * Private methods
	 */

	private void createLayout() {
		log.trace(HelperLog.methodStart());
		
		setEditable(true);

		// get the combo boxes editor component and change the editor's document
		((JTextComponent) getEditor().getEditorComponent()).setDocument(new ComboBoxPopup(this));
		
		log.trace(HelperLog.methodExit());
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


	/*
	 * Inner classes
	 */

	static class ComboBoxPopup extends PlainDocument {
		private static final long serialVersionUID = -5374025097785761556L;

		private final JComboBox comboBox;
		private final transient ComboBoxModel model;
		private final JTextComponent myEditor;
		boolean selecting;

		ComboBoxPopup(final JComboBox comboBox) {
			super();
			this.comboBox = comboBox;
			model = comboBox.getModel();
			myEditor = (JTextComponent) comboBox.getEditor().getEditorComponent();

			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					if (!selecting) {
						highlightCompletedText(0);
					}
				}
			});

			myEditor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(final KeyEvent e) {
					if (comboBox.isDisplayable()) {
						comboBox.setPopupVisible(true);
					}
				}
			});
		}

		/*
		 * Private methods
		 */

		private void setText(final String text) throws BadLocationException {
			// remove all text and insert the completed string
			super.remove(0, getLength());
			super.insertString(0, text, null);
		}

		void highlightCompletedText(final int start) {
			myEditor.setSelectionStart(start);
			myEditor.setSelectionEnd(getLength());
		}

		private void setSelectedItem(final Object item) {
			selecting = true;

			model.setSelectedItem(item);

			selecting = false;
		}

		private Object lookupItem(final String pattern) {
			final Object selectedItem = model.getSelectedItem();

			// only search for a different item if the currently selected does not match
			if (null != selectedItem && HelperString.startsWith(selectedItem.toString(), pattern)) {
				return selectedItem;
			}

			// iterate over all items
			for (int ii = 0, n = model.getSize(); ii < n; ii++) {

				final Object currentItem = model.getElementAt(ii);

				// current item starts with the pattern?
				if (HelperString.startsWith(currentItem.toString(), pattern)) {
					return currentItem;
				}
			}

			// no item starts with the pattern => return null
			return null;
		}


		/*
		 * Overridden methods
		 */

		@Override
		public void remove(final int offs, final int len) throws BadLocationException {
			// return immediately when selecting an item
			if (selecting) {
				return;
			}

			super.remove(offs, len);
		}

		@Override
		public void insertString(final int offset, final String str, final AttributeSet a) throws BadLocationException {
			int offs = offset;

			// return immediately when selecting an item
			if (selecting) {
				return;
			}

			// insert the string into the document
			super.insertString(offs, str, a);

			// lookup and select a matching item
			Object item = lookupItem(getText(0, getLength()));

			if (null != item) {
				setSelectedItem(item);
			} else {

				// keep old item selected if there is no match
				item = comboBox.getSelectedItem();

				// imitate no insert (later on offs will be incremented by str.length(): selection won't move forward)
				offs -= str.length();

				// provide feedback to the user that his input has been received but can not be accepted
				comboBox.getToolkit().beep(); // when available use: UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
			}
			setText(null == item ? HelperString.EMPTY_STRING : item.toString());

			// select the completed part
			highlightCompletedText(offs + str.length());
		}
	}
}