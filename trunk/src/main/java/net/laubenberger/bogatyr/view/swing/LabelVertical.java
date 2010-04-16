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

import javax.swing.Icon;

import net.laubenberger.bogatyr.helper.HelperLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 * This is a vertical Label.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.2.0
 */
public class LabelVertical extends Panel implements Icon {
	private static final long serialVersionUID = -6664528555390753370L;

	private static final Logger log = LoggerFactory.getLogger(LabelVertical.class);
	
	private static final double NINETY_DEGREES = 1.5707963267948966;

	private Label label;


	public LabelVertical() {
		super();
		log.trace(HelperLog.constructor());
	}

	public LabelVertical(final Icon icon, final int horizontalAlignment) {
		this();
		log.trace(HelperLog.constructor(icon, horizontalAlignment));
		
		label = new Label(icon, horizontalAlignment);
	}

	public LabelVertical(final String text, final Icon icon, final int horizontalAlignment) {
		this();
		log.trace(HelperLog.constructor(text, icon, horizontalAlignment));
		
		label = new Label(text, icon, horizontalAlignment);

	}

	public LabelVertical(final Icon icon) {
		this();
		log.trace(HelperLog.constructor(icon));
		
		label = new Label(icon);
	}

	public LabelVertical(final String text) {
		this();
		log.trace(HelperLog.constructor(text));
		
		label = new Label(text);
	}

	public LabelVertical(final String text, final int horizontalAlignment) {
		this();
		log.trace(HelperLog.constructor(text, horizontalAlignment));
		
		label = new Label(text, horizontalAlignment);
	}

	// Delegate these methods to the Label...

	public void setText(final String text) {
		label.setText(text);
	}

// 	public void setToolTipText(final String text) {
//		label.setToolTipText(text);
//    }

	public String getText() {
		return label.getText();
	}

	public void setIcon(final Icon icon) {
		label.setIcon(icon);
	}

	public Icon getIcon() {
		return label.getIcon();
	}

	public void setHorizontalAlignment(final int alignment) {
		label.setHorizontalAlignment(alignment);
	}

	public void setVerticalAlignment(final int alignment) {
		label.setVerticalAlignment(alignment);
	}


	/*
	 * Private methods
	 */

	private void paintVertical(final Object g, final int x, final int y, final int width, final int height) {
		log.trace(HelperLog.methodStart(g, x, y, width, height));
		
		if (!(0 >= height || 0 >= width)) {
	//		this.label.updateUI();
	//		this.label.revalidate();
	
			final BufferedImage buffer = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB_PRE); // switch of width and height
			final Graphics2D g2 = buffer.createGraphics();
			label.setSize(new Dimension(height, width)); // switch of width and height
			label.paint(g2);
	
			final AffineTransform af = AffineTransform.getTranslateInstance((double) x, (double) (y + height));
			final AffineTransform af2 = AffineTransform.getRotateInstance(-NINETY_DEGREES);
			af.concatenate(af2);
	
			((Graphics2D) g).drawImage(buffer, af, this);
		}		

		log.trace(HelperLog.methodExit());
	}


	/*
	 * Overridden methods
	 */

	@Override
	public Dimension getPreferredSize() {
		final Dimension dimension = label.getPreferredSize();
		return new Dimension(dimension.height, dimension.width); // switch of width and height
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Dimension dimension = getSize();
		paintVertical(g, 0, 0, dimension.width, dimension.height);
	}

	@Override
	public void setBackground(final Color bg) {
		super.setBackground(bg);

		if (null != label) {
			label.setBackground(bg);
		}
	}

	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);

		if (null != label) {
			label.setForeground(fg);
		}
	}

	@Override
	public void setFont(final Font font) {
		super.setFont(font);

		if (null != label) {
			label.setFont(font);
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	public int getIconHeight() {
		return getPreferredSize().height;
	}

	@Override
	public int getIconWidth() {
		return getPreferredSize().width;
	}

	@Override
	public void paintIcon(final Component component, final Graphics g, final int x, final int y) {
		final Dimension dimension = getPreferredSize();
		paintVertical(g, x, y, dimension.width, dimension.height);
	}
}