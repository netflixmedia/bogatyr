package ch.orwell.bogatyr.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


/**
 * This is an extended JTextField
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public class TextField extends JTextField {
	private static final long serialVersionUID = -2651301302591855508L;

//	protected int cols = 0;

	public TextField(String text, int columns) {
        super(text, columns);
//        this.cols = columns;
    }
	
//	@Override
//	public void setColumns(int columns) {
//		super.setColumns(columns);
//        this.cols = columns;
//	}
	
    @Override
	protected Document createDefaultModel() {
    	return new TextFieldDocument();
    }

    class TextFieldDocument extends PlainDocument {
    	private static final long serialVersionUID = -9060548395031856257L;

		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	
			if (str != null && (str.length() + offs) <= getColumns()) {
				super.insertString(offs, str, a);
			}
		}
    }
}
