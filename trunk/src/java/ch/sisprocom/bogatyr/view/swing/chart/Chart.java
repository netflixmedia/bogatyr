/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import ch.sisprocom.bogatyr.view.swing.Label;
import ch.sisprocom.bogatyr.view.swing.LabelVertical;
import ch.sisprocom.bogatyr.view.swing.Panel;


/**
 * This is a chart class with labled x- and y-axes.
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 20090325
 */
public class Chart extends Panel { //TODO document in Wiki!
	private static final long serialVersionUID = -4618658256880807781L;

	public static final int X_AXIS_NORTH = 1;
	public static final int X_AXIS_SOUTH = 2;
	public static final int Y_AXIS_WEST  = 3;
	public static final int Y_AXIS_EAST  = 4;
		
	private Color colorGrid = Color.GRAY;
	private Color colorBackground = Color.WHITE;
	private Color colorForeground = Color.BLACK;
	private Font font;
	
	private final String[] xAxes, yAxes;
	private final int maxX, maxY, gridIntervalX, gridIntervalY, positionXAxis, positionYAxis;
	private final List<ChartEntry> entries;

	
	public Chart(final int maxX, final int maxY, final String[] xAxes, final String[] yAxes) {
		this(maxX, maxY, xAxes, yAxes, X_AXIS_SOUTH, Y_AXIS_WEST);
	}

	public Chart(final int maxX, final int maxY, final String[] xAxes, final String[] yAxes, final int positionXAxis, final int positionYAxis) {
		super();
		
		this.xAxes         = xAxes;
		this.yAxes         = yAxes;
		this.maxX          = maxX;
		this.maxY          = maxY;
		this.positionXAxis = positionXAxis;
		this.positionYAxis = positionYAxis;
        gridIntervalX = maxX / xAxes.length;
        gridIntervalY = maxY / yAxes.length;
        entries = new ArrayList<ChartEntry>();
        
		createLayout();
	}

	
	/**
	 * Adds an entry to the product selection chart.
	 * 
     * @param entry for the chart
     * @throws Exception
     */
	public void addEntry(final ChartEntry entry) {
		if (entry.getX() + entry.getSizeX() > maxX || entry.getY() + entry.getSizeY() > maxY) {
			throw new RuntimeException("Component exceeds the defined chart area!");
		}
        entries.add(entry);
		createLayout();
	}
	
	
	public Color getColorGrid() {
		return colorGrid;
	}

	public void setColorGrid(final Color colorGrid) {
		this.colorGrid = colorGrid;
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
		if (positionXAxis == X_AXIS_NORTH) {

			// Border in the south
			Panel panelXAxis = new Panel(colorBackground);
			panelXAxis.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorGrid));
			add(panelXAxis, gbc);
			
			gbc.gridy = 0;
		}
    	add(getXAxis(), gbc);

    	// Paint yAxis
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
    	gbc.weightx = 0.0D;
		gbc.weighty = 1.0D;
		if (positionYAxis == Y_AXIS_WEST) {
			if (positionXAxis == X_AXIS_SOUTH) {
				gbc.gridy = 0;
			} else {
				gbc.gridy = 1;
			}
			gbc.gridheight 	= maxY + 1;
		} else {
			gbc.gridy 		= 1;
			gbc.gridheight 	= maxY;

			// Border in the west
			Panel panelYAxis = new Panel(colorBackground);
			panelYAxis.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorGrid));
			add(panelYAxis, gbc);
			
			gbc.gridx = maxX + 1;
		}
		add(getYAxis(), gbc);

    	// Paint components
    	for (final ChartEntry entry : entries) {
			// GridBag
    		gbc.fill 		= GridBagConstraints.BOTH;
			gbc.gridx 		= entry.getX() + 1; 								// +1 Because of yAxis
	    	gbc.gridy 		= maxY - entry.getY() - (entry.getSizeY() - 1); 	// - Because of switched axis counting
//	    	gbc.gridy 		= entry.y + 1;
	    	gbc.gridwidth 	= entry.getSizeX(); 
	    	gbc.gridheight 	= entry.getSizeY();
//	    	gbc.weightx 	= (double) entry.getSizeX();
//			gbc.weighty		= (double) entry.getSizeY();
			gbc.weightx 	= 1.0/maxX;
			gbc.weighty		= 1.0/maxY;
			
			// Save used coordinates in map
			for (int x = entry.getX(); x < entry.getX() + entry.getSizeX(); x++) {
				for (int y = entry.getY(); y < entry.getY() + entry.getSizeY(); y++) {
					map[x][y] = true;
				}				
			}
	
			// Create component
			final JComponent component = entry.getComponent();
			
			// Create container with maybe a border
			final Panel panelContainer = new Panel(colorBackground);
			panelContainer.setLayout(new GridLayout(1, 1));
			
			// Create borders
			if ((entry.getX() + 1) % gridIntervalX == 0) {
				panelContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorGrid));
				component.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorBackground));
			}
			
			if ((entry.getY() + 1) % gridIntervalY == 0) {
				panelContainer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorGrid));
				component.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorBackground));
			}
			
			if ((entry.getX() + 1) % gridIntervalX == 0 && (entry.getY() + 1) % gridIntervalY == 0) {
				panelContainer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, colorGrid));
				component.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, colorBackground));
			}
			if (component.getBorder() == null) {
				component.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, colorBackground));
			} 
			
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
//    		    	gbc.gridy 		= y + 1;
    		    	gbc.gridwidth 	= 1;
    		    	gbc.gridheight 	= 1;
//    		    	gbc.weightx 	= 1.0D;
//    				gbc.weighty		= 1.0D;
    				
    				final Panel spacer = new Panel(colorBackground);
    				
    				// Create borders
    				if ((x + 1) % gridIntervalX == 0) {
    					spacer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorGrid));
    				}
    				
    				if ((y + 1) % gridIntervalY == 0) {
    					spacer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorGrid));
    				}
    				
    				if ((x + 1) % gridIntervalX == 0 && (y + 1) % gridIntervalY == 0) {
    					spacer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, colorGrid));
    				}
    				
    				add(spacer, gbc);
    			}
    		}
		}
	}
	
	private Panel getXAxis() {
		final Panel panelXAxis = new Panel(colorBackground);
		panelXAxis.setLayout(new GridLayout(0, xAxes.length));
		
		if (positionXAxis == X_AXIS_SOUTH) {
			panelXAxis.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorGrid));
		} else {
			panelXAxis.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorGrid));
		}
		
		// Paint x axis
		for (final String text : xAxes) {
			Label label = new Label(text, SwingConstants.CENTER);
			label.setBackground(colorBackground);
			label.setForeground(colorForeground);
			if (font != null) {
				label.setFont(font);
			}
			panelXAxis.add(label);
		}
		return panelXAxis;	
	}
	
	private Panel getYAxis() {
		final Panel panelYAxis = new Panel(colorBackground);
		panelYAxis.setLayout(new GridLayout(yAxes.length, 0));
		
		if (positionYAxis == Y_AXIS_WEST) {
			panelYAxis.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorGrid));
			
		} else {
			panelYAxis.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, colorGrid));
		}

		// Paint y axis
		for (final String text : yAxes) {
			LabelVertical label = new LabelVertical(text, SwingConstants.CENTER);
			label.setBackground(colorBackground);
			label.setForeground(colorForeground);
			if (font != null) {
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
//		createLayout();
	}
	
	@Override
	public void setForeground(final Color fg) {
		super.setForeground(fg);
		colorForeground = fg;
//		createLayout();
	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		this.font = font;
//		createLayout();
	}
}
