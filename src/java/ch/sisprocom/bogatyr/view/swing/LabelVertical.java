/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Icon;


/**
 * This is a vertical Label.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public class LabelVertical extends Panel implements Icon { //TODO document in Wiki!
	private static final long serialVersionUID = -6664528555390753370L;
	
	private static final double NINETY_DEGREES = 1.5707963267948966;
	
	private Label label;
 
	
	public LabelVertical() {
		super();
	}
	
	public LabelVertical(final String title) {
		this();
        label = new Label(title);
	}
	
	public LabelVertical(final String title, final int alignment) {
		this();
        label = new Label(title, alignment);
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
		if (0 >= height || 0 >= width) {
            return;
        }
 
//		this.label.updateUI();
//		this.label.revalidate();
		
		// Paint the JLabel into an image buffer (switch width <=> height)...
		final BufferedImage buffer = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB_PRE); // switch of width and height
		final Graphics2D g2 = buffer.createGraphics();
        label.setSize(new Dimension(height, width)); // switch of width and height
        label.paint(g2);
 
		// ...then apply a transform while painting the buffer into the component
		final AffineTransform af = AffineTransform.getTranslateInstance((double) x, (double) (y + height));
		final AffineTransform af2 = AffineTransform.getRotateInstance(-NINETY_DEGREES);
		af.concatenate(af2);
 
		((Graphics2D)g).drawImage(buffer, af, this);
	}

 	
	/*
	 * Overridden methods
	 */
	@Override
	public Dimension getPreferredSize() {
		final Dimension d = label.getPreferredSize();
		return new Dimension(d.height, d.width); // switch of width and height
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Dimension d = getSize();
		paintVertical(g, 0, 0, d.width, d.height);
	}
	
	@Override
	public void setBackground(final Color bg) {
		super.setBackground(bg);
		
		if (label != null) {
            label.setBackground(bg);
        }
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);
		
		if (label != null) {
            label.setForeground(fg);
        }
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		if (label != null) {
            label.setFont(font);
        }
	}
	
	
	/*
	 * Implemented methods
	 */
	public int getIconHeight() {
		return getPreferredSize().height;
	}
 
	public int getIconWidth() {
		return getPreferredSize().width;
	}
 
	public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
		final Dimension d = getPreferredSize();
		paintVertical(g, x, y, d.width, d.height);
	}
}