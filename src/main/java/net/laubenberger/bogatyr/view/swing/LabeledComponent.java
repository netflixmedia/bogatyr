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

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import net.laubenberger.bogatyr.helper.HelperLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a Label combined with a JComponent.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.6.0
 */
public class LabeledComponent<T extends JComponent> extends Panel {
	private static final long serialVersionUID = 2215341067138215010L;

	private static final Logger log = LoggerFactory.getLogger(LabeledComponent.class);

	private final JLabel label = new Label();
	private final T component;

	{
		createLayout();
	}

	public LabeledComponent(final String labelText, final String toolTip, final T component) {
		super();
		log.trace(HelperLog.constructor(labelText, toolTip, component));

		this.component = component;
		label.setText(labelText);
		setToolTipText(toolTip);
	}

	public LabeledComponent(final String title, final String labelText, final String toolTip, final T component) {
		super(title);
		log.trace(HelperLog.constructor(title, labelText, toolTip, component));

		this.component = component;
		label.setText(labelText);
		setToolTipText(toolTip);
	}

	public JLabel getLabel() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(label));
		return label;
	}

	public T getComponent() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(component));
		return component;
	}


	/*
	 * Private methods
	 */

	private void createLayout() {
		log.trace(HelperLog.methodStart());

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

		log.trace(HelperLog.methodExit());
	}


	/*
	 * Overridden methods
	 */

	@Override
	public void setBackground(final Color bg) {
		super.setBackground(bg);

		if (null != label) {
			label.setBackground(bg);
		}

		if (null != component) {
			component.setBackground(bg);
		}
	}

	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (null != label) {
			label.setForeground(fg);
		}

		if (null != component) {
			component.setForeground(fg);
		}
	}

	@Override
	public void setFont(final Font font) {
		super.setFont(font);

		if (null != label) {
			label.setFont(font);
		}

		if (null != component) {
			component.setFont(font);
		}
	}
}