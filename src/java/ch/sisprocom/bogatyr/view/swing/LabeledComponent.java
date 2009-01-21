/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;


/**
 * This is an combined Label with a JComponent.
 * 
 * @author Stefan Laubenberger
 * @version 20090119
 */
public class LabeledComponent extends Panel {  //TODO document in Wiki!
	private static final long serialVersionUID = 2215341067138215010L;

	private Label label;
	private JComponent component;

	
	public LabeledComponent(final String labelText, final String toolTip, JComponent component) {
		super();
		
		this.component = component;
		
		createLayout(labelText);
		setToolTipText(toolTip);
	}

	public LabeledComponent(final String title, final String labelText, final String toolTip, JComponent component) {
		super(title);
		createLayout(labelText);
		setToolTipText(toolTip);
	}

	public Label getLabel() {
		return label;
	}

	public JComponent getComponent() {
		return component;
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(final String labelText) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(0, 0, 0, 5);

		add(component, gbc);

		gbc.weightx = 1.0D;
		gbc.gridx = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
        label = new Label(labelText);
		add(label, gbc);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public void setBackground(final Color bg) {
		super.setBackground(bg);
		
		if (label != null) {
            label.setBackground(bg);
        }
		if (component != null) {
            component.setBackground(bg);
        }
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (label != null) {
            label.setForeground(fg);
        }
		if (component != null) {
			component.setForeground(fg);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
		if (component != null) {
			component.setFont(font);
        }
	}
}