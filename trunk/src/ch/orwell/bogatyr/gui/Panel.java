package ch.orwell.bogatyr.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is an extended JPanel
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070625
 */
public class Panel extends JPanel {
	private static final long serialVersionUID = 3027031734134184715L;
	protected String className;
	
	/**
	 * Constructs a Panel without a title.
	 *
	 */
	public Panel() {
		super();
		init();
//		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
	}

	/**
	 * Constructs a Panel with a title.
	 * @param title Title of the panel
	 */
	public Panel(String title) {
		super();
		init();
		setTitle(title);
	}
	
	/**
     * Set the title of the panel
     * @param title Title of the panel
     */	
	public void setTitle(String title){
//		setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), title));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
	}
	

	/*
	 * Private methods
	 */
	/**
	 * Initialize the object<p>
	 * Do some logging.
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED) + toString()); //$NON-NLS-1$
	}
}