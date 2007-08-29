package ch.orwell.bogatyr.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * This is a NumberField, similar to TextField, but only numeric characters are allowed
 * 
 * @author Stefan Laubenberger
 * @version 20070829
 */
public class NumberField extends JTextField {
	private static final long serialVersionUID = 5310015371075554007L;

	public NumberField(int number, int columns) {
		super((new Integer(number)).toString(), columns);
	}
 
     @Override
	protected Document createDefaultModel() {
 	      return new NumberDocument();
     }
 
     class NumberDocument extends PlainDocument {
		private static final long serialVersionUID = 3766889554419497713L;

		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str != null && (str.length() + offs) <= getColumns() && GeneralHelper.isStringNumeric(str)) {
	 	          super.insertString(offs, str, a);
			}
		}
     }
 }
