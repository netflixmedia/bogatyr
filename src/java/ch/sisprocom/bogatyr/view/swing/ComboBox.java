/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperGeneral;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * This is an extended JComboBox.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public class ComboBox extends JComboBox implements IComponentActivate {
	private static final long serialVersionUID = -3870596701286078140L;
	
	private boolean isNotActive;

	
	public ComboBox() {
		super();
		
		createLayout();
	}
	
	public ComboBox(final ComboBoxModel model) {
		super(model);
		createLayout();
	}

	public ComboBox(final Object[] data, final String toolTip) {
		super(data);
		
		if (data != null) {
//			setSelectedIndex(0);
			setSelectedIndex(-1);
		}

		setToolTipText(toolTip);
		createLayout();
	}
	
	
	/*
	 * Private methods
	 */
	private void createLayout() {
		setEditable(true);

		// get the combo boxes editor component
        final JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();

        // change the editor's document
        editor.setDocument(new ComboBoxPopup(this));
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
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
	public boolean isActive() {
		return !isNotActive;
	}

	public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}

//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//	}	
	
	
	/*
	 * Inner classes
	 */
	private static class ComboBoxPopup extends PlainDocument {
		private static final long serialVersionUID = -5374025097785761556L;

		private final JComboBox comboBox;
		private final transient ComboBoxModel model;
		private final JTextComponent myEditor;
		private boolean selecting;

		private ComboBoxPopup(final JComboBox comboBox) {
            super();
            this.comboBox = comboBox;
            model = comboBox.getModel();
            myEditor = (JTextComponent) comboBox.getEditor().getEditorComponent();

            comboBox.addActionListener(new ActionListener() {
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
		
		protected void highlightCompletedText(final int start) {
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
		    if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
		        return selectedItem;
		    } 
	        
		    // iterate over all items
	        for (int i=0, n=model.getSize(); i < n; i++) {

	            final Object currentItem = model.getElementAt(i);

	            // current item starts with the pattern?
	            if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
	                return currentItem;
	            }
	        }

	        // no item starts with the pattern => return null
		    return null;
		}

		// checks if str1 starts with str2 - ignores case
		private boolean startsWithIgnoreCase(final String str1, final String str2) {

		    return str1.toUpperCase().startsWith(str2.toUpperCase());

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

		    if (item != null) {
		        setSelectedItem(item);
		    } else {

		        // keep old item selected if there is no match
		        item = comboBox.getSelectedItem();

		        // imitate no insert (later on offs will be incremented by str.length(): selection won't move forward)
                offs -= str.length();

		        // provide feedback to the user that his input has been received but can not be accepted
		        comboBox.getToolkit().beep(); // when available use: UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
		    }
		    setText(item != null ? item.toString() : ""); //$NON-NLS-1$

		    // select the completed part
		    highlightCompletedText(offs + str.length());
		}
	}
}