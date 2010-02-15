/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing.chart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.view.swing.Label;
import ch.sisprocom.bogatyr.view.swing.LabelVertical;
import ch.sisprocom.bogatyr.view.swing.Panel;


/**
 * This is a chart class with labeled x- and y-axes.
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.5.0
 */
public class Chart extends Panel {
	private static final long serialVersionUID = -4618658256880807781L;

    public enum X_Axis {NORTH, SOUTH}
    public enum Y_Axis {WEST, EAST}

	private Color colorGrid = Color.GRAY;
	private Color colorBackground = Color.WHITE;
	private Color colorForeground = Color.BLACK;
	private Font font;
	
	private final String[] xAxes;
    private final String[] yAxes;
	private final int maxX;
    private final int maxY;
    private final X_Axis positionXAxis;
    private final Y_Axis positionYAxis;
	private final List<ChartEntry> entries;

	private final boolean isConstructed;
	
	
	public Chart(final int maxX, final int maxY, final String[] xAxes, final String[] yAxes) {
		this(maxX, maxY, xAxes, yAxes, X_Axis.SOUTH, Y_Axis.WEST);
	}

	public Chart(final int maxX, final int maxY, final String[] xAxes, final String[] yAxes, final X_Axis positionXAxis, final Y_Axis positionYAxis) {
		super();
		
		this.xAxes         = xAxes.clone();
		this.yAxes         = yAxes.clone();
		this.maxX          = maxX;
		this.maxY          = maxY;
		this.positionXAxis = positionXAxis;
		this.positionYAxis = positionYAxis;
        entries = new ArrayList<ChartEntry>(maxX * maxY);
        
		createLayout();
		
		isConstructed = true;
	}

	
	/**
	 * Adds an entry to the product selection chart.
	 * 
     * @param entry for the chart
     * @since 0.5.0
     */
	public void addEntry(final ChartEntry entry) {
    	if (null == entry) {
    		throw new RuntimeExceptionIsNull("entry"); //$NON-NLS-1$
    	}
		if (entry.getX() + entry.getSizeX() > maxX || entry.getY() + entry.getSizeY() > maxY) {
			throw new IllegalArgumentException("Component (" + entry.getSizeX() + 'x' + entry.getSizeY() + ") is outside of the defined chart area (" + maxX + 'x' + maxY + ')'); //$NON-NLS-1$ //$NON-NLS-2$
		}
        entries.add(entry);
		createLayout();
	}
	
	/**
	 * Returns the color of the grid.
	 * 
	 * @return color of the grid
	 * @since 0.5.0
	 */
	public Color getColorGrid() {
		return colorGrid;
	}

	/**
	 * Sets the color of the grid.
	 * 
	 * @param color of the grid
	 * @since 0.5.0
	 */
	public void setColorGrid(final Color color) {
        colorGrid = color;
		createLayout();
	}

	
	/*
	 * Private methods
	 */
	private void createLayout() {
		// First remove all
		removeAll();
		
		// Control map
		final boolean[][] map = new boolean[maxX][maxY];
		
		// Create gbc
		final GridBagConstraints gbc = new GridBagConstraints();
		
		// Paint xAxis
    	gbc.fill 		= GridBagConstraints.HORIZONTAL;
		gbc.gridx 		= 1;
		gbc.gridy = maxY + 1;
    	gbc.gridwidth 	= maxX;
    	gbc.gridheight 	= 1;
    	gbc.weightx 	= 1.0D;
		gbc.weighty		= 0.0D;

    	add(getXAxis(), gbc);

    	// Paint yAxis
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
    	gbc.weightx = 0.0D;
		gbc.weighty = 1.0D;
		if (Y_Axis.WEST == positionYAxis) {
            gbc.gridy = X_Axis.SOUTH == positionXAxis ? 0 : 1;
			gbc.gridheight 	= maxY + 1;
		} else {
			gbc.gridy 		= 1;
			gbc.gridheight 	= maxY;
			
			gbc.gridx = maxX + 1;
		}
		add(getYAxis(), gbc);

    	// Paint components
    	for (final ChartEntry entry : entries) {
			// GridBag
    		gbc.fill 		= GridBagConstraints.BOTH;
			gbc.gridx 		= entry.getX() + 1; 								// +1 Because of yAxis
	    	gbc.gridy 		= maxY - entry.getY() - (entry.getSizeY() - 1); 	// - Because of switched axis counting
	    	gbc.gridwidth 	= entry.getSizeX(); 
	    	gbc.gridheight 	= entry.getSizeY();
			gbc.weightx 	= 1.0/ (double) maxX;
			gbc.weighty		= 1.0/ (double) maxY;
			
			// Save used coordinates in map
			for (int x = entry.getX(); x < entry.getX() + entry.getSizeX(); x++) {
				for (int y = entry.getY(); y < entry.getY() + entry.getSizeY(); y++) {
					map[x][y] = true;
				}				
			}
	
			// Create component
			final JComponent component = entry.getComponent();
			
			// Create container with maybe a border
			final JComponent panelContainer = new Panel(colorBackground);
			panelContainer.setLayout(new GridLayout(1, 1));
			
			panelContainer.add(component);
	    	add(panelContainer, gbc);
		}
    	
    	// Fill unused coordinates with blank spacers
    	for (int x = 0; x < maxX; x++) {
    		for (int y = 0; y < maxY; y++) {
    			if (!map[x][y]) {
    				gbc.fill 		= GridBagConstraints.BOTH;
    				gbc.gridx 		= x + 1; 					// +1 Because of yAxis
    		    	gbc.gridy 		= maxY - y; 			// - Because of switched axis counting
    		    	gbc.gridwidth 	= 1;
    		    	gbc.gridheight 	= 1;
    				
    				add(new Panel(colorBackground), gbc);
    			}
    		}
		}
	}
	
	private Component getXAxis() {
		final JComponent panelXAxis = new Panel(colorBackground);
		panelXAxis.setLayout(new GridLayout(0, xAxes.length));
		
		// Paint x axis
		for (final String text : xAxes) {
			final Component label = new Label(text, SwingConstants.CENTER);
			label.setBackground(colorBackground);
			label.setForeground(colorForeground);
			if (null != font) {
				label.setFont(font);
			}
			panelXAxis.add(label);
		}
		return panelXAxis;	
	}
	
	private Component getYAxis() {
		final JComponent panelYAxis = new Panel(colorBackground);
		panelYAxis.setLayout(new GridLayout(yAxes.length, 0));

		// Paint y axis
		for (final String text : yAxes) {
			final Component label = new LabelVertical(text, SwingConstants.CENTER);
			label.setBackground(colorBackground);
			label.setForeground(colorForeground);
			if (null != font) {
				label.setFont(font);
			}
			panelYAxis.add(label);
		}
		return panelYAxis;
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public void setBackground(final Color bg) {
		super.setBackground(bg);
		colorBackground = bg;
		
		if (isConstructed) {
			createLayout();
		}
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);
		colorForeground = fg;
		
		if (isConstructed) {
			createLayout();
		}
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		this.font = font;
		
		if (isConstructed) {
			createLayout();
		}
	}
}
