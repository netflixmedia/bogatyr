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

import javax.swing.AbstractButton;
import javax.swing.JComponent;

import net.laubenberger.bogatyr.helper.HelperLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;


/**
 * This is a group to add 1-n components (e.g. useful with JButtons).
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public class Group extends Panel {
	private static final long serialVersionUID = -3557759501854611930L;

	private static final Logger log = LoggerFactory.getLogger(Frame.class);
	

	public Group(final Insets insets, final JComponent... data) {
		super();
		log.trace(HelperLog.constructor(insets, data));
		
		createLayout(data, insets, false);
	}

	public Group(final Insets insets, final JComponent[] data, final boolean isVertical) {
		super();
		log.trace(HelperLog.constructor(insets, data, isVertical));
		
		createLayout(data, insets, isVertical);
	}

	public void addActionListener(final ActionListener listener) {
		log.debug(HelperLog.methodStart(listener));
		
		final Component[] components = getComponents();
		for (final Component component : components) {
			if (component instanceof AbstractButton) {
				((AbstractButton) component).addActionListener(listener);
			}
		}

		log.debug(HelperLog.methodExit());
	}

	public void removeActionListener(final ActionListener listener) {
		log.debug(HelperLog.methodStart(listener));
		
		final Component[] components = getComponents();
		for (final Component component : components) {
			if (component instanceof AbstractButton) {
				((AbstractButton) component).removeActionListener(listener);
			}
		}

		log.debug(HelperLog.methodExit());
	}


	/*
	 * Private methods
	 */

	private void createLayout(final JComponent[] data, final Insets insets, final boolean isVertical) {
		log.trace(HelperLog.methodStart());
		
		if (isVertical) {
			createLayoutVertical(data, insets);
		} else {
			createLayoutHorizontal(data, insets);
		}
		
		log.trace(HelperLog.methodExit());
	}

	private void createLayoutVertical(final JComponent[] data, final Insets insets) {
		log.trace(HelperLog.methodStart());
		
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;

		if (null != data) {
			for (final JComponent button : data) {
				gbc.gridy++;
				add(button, gbc);
			}
		}
		
		log.trace(HelperLog.methodExit());
	}

	private void createLayoutHorizontal(final JComponent[] data, final Insets insets) {
		log.trace(HelperLog.methodStart());
		
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;

		if (null != data) {
			for (final JComponent button : data) {
				gbc.gridx++;
				add(button, gbc);
			}
		}
		
		log.trace(HelperLog.methodExit());
	}


	/*
	 * Overridden methods
	 */
	@Override
	public void setFont(final Font font) {
		super.setFont(font);

		for (final Component component : getComponents()) {
			component.setFont(font);
		}
	}
}