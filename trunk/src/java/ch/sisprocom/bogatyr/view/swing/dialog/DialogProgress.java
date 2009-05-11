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
package ch.sisprocom.bogatyr.view.swing.dialog;

import ch.sisprocom.bogatyr.view.swing.Panel;
import ch.sisprocom.bogatyr.view.swing.ProgressBar;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;



/**
 * DialogProgress displays a indeterminate progress bar.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public class DialogProgress extends Dialog { //TODO document in Wiki!
	private static final long serialVersionUID = 3287183043789350515L;
	
	private final JProgressBar progressBar = new ProgressBar();

	
	public DialogProgress(final Frame frame) {
        super(frame);
        
        createLayout();
    }
    
    public DialogProgress() {
        this(null);
    }
    
    
	/*
	 * Private methods
	 */
    private void createLayout() {
		final JComponent panelMain = new Panel(Color.WHITE);
		panelMain.setLayout(new BorderLayout());
		panelMain.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,	Color.LIGHT_GRAY, Color.WHITE, Color.DARK_GRAY, Color.GRAY));

		progressBar.setIndeterminate(true);
		
		panelMain.add(progressBar, BorderLayout.CENTER);
		
		add(panelMain, BorderLayout.CENTER);
		
    	setUndecorated(true);
    	setSize(250, 40);
//    	setLocationRelativeTo(frame);
	}
 }