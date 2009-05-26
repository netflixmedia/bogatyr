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
package ch.sisprocom.bogatyr.view.swing.labeledcomponent;

import ch.sisprocom.bogatyr.view.swing.Label;
import ch.sisprocom.bogatyr.view.swing.Panel;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * This is a Label combined with a JComponent.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.60
 */
public class LabeledComponent extends Panel {
	private static final long serialVersionUID = 2215341067138215010L;

	private final Label label = new Label();
	private final JComponent component;

	
	public LabeledComponent(final String labelText, final String toolTip, final JComponent component) {
		super();
		
		this.component = component;
		label.setText(labelText);

		createLayout();
		setToolTipText(toolTip);
	}

	public LabeledComponent(final String title, final String labelText, final String toolTip, final JComponent component) {
		super(title);
		
		this.component = component;
		label.setText(labelText);

		createLayout();
		setToolTipText(toolTip);
	}

	public Label getLabel() {
		return label;
	}

	public Object getComponent() {
		return component;
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(0, 0, 0, 5);

	    if (component instanceof JCheckBox || component instanceof JRadioButton) {
			add(component, gbc);

			gbc.weightx = 1.0D;
			gbc.gridx = 1;
			gbc.insets = new Insets(0, 0, 0, 0);
			add(label, gbc);	    	
	    } else {
			add(label, gbc);
			
			gbc.weightx = 1.0D;
			gbc.gridx = 1;
			gbc.insets = new Insets(0, 0, 0, 0);
			add(component, gbc);

	    }
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