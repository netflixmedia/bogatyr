package ch.orwell.bogatyr.gui;

import javax.swing.JTextArea;


/**
 * This is an extended TextArea
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070624
 */
public class TextArea extends JTextArea {
	private static final long serialVersionUID = -801724496277932349L;
    
	
	/*
	 * Overriden methods
	 */
	@Override
	public void append(String text) {
		super.append(text);
		this.setCaretPosition(this.getDocument().getLength());
	}
}