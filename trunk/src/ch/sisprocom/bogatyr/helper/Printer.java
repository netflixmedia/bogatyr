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
package ch.sisprocom.bogatyr.helper;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is a printer class for print operations
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class Printer implements Printable {
	private Component componentToBePrinted;


	public Printer() {
        super();
        init();
    }

    /**
     * Print a component
     *
     * @param component for printing
     * @throws java.awt.print.PrinterException
     */
    public void print(final Component component) throws PrinterException {
		Logger.getInstance().writeMethodEntry(this.getClass(), "print", component);  //$NON-NLS-1$

		this.componentToBePrinted = component;
        print();
        
		Logger.getInstance().writeMethodExit(this.getClass(), "print");  //$NON-NLS-1$
	}


	/*
	 * Private methods
	 */
	private void print() throws PrinterException {
		Logger.getInstance().writeMethodEntry(this.getClass(), "print");  //$NON-NLS-1$

		final PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog()) {
			printJob.print();
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "print");  //$NON-NLS-1$
	}
	
	/**
	 * The speed and quality of printing suffers dramatically if any of the
	 * containers have double buffering turned on. So this turns if off
  	 * globally.
     *
     * @param component
	 */
	private void disableDoubleBuffering(final Component component) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "disableDoubleBuffering", component);  //$NON-NLS-1$

		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(false);

		Logger.getInstance().writeMethodExit(this.getClass(), "disableDoubleBuffering");  //$NON-NLS-1$
	}

	/** Re-enables double buffering globally.
     *
     * @param component
     */
	private void enableDoubleBuffering(final Component component) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "enableDoubleBuffering", component);  //$NON-NLS-1$

		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(true);
	
		Logger.getInstance().writeMethodExit(this.getClass(), "enableDoubleBuffering");  //$NON-NLS-1$
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init",  toString()); //$NON-NLS-1$
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}


    /*
     * Implemented methods
     */
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "print", new Object[]{graphics, pageFormat, pageIndex});  //$NON-NLS-1$

		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
//		componentToBePrinted.setSize((int)(componentToBePrinted.getWidth() * pageFormat.getWidth()/componentToBePrinted.getWidth()), (int)(componentToBePrinted.getHeight() * pageFormat.getHeight()/componentToBePrinted.getHeight()));

		final Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//		g2d.scale(pageFormat.getWidth()/componentToBePrinted.getWidth(), pageFormat.getHeight()/componentToBePrinted.getHeight());

        if (componentToBePrinted == null) {
            componentToBePrinted = new JPanel();
            //TODO set size of component?
        }

        disableDoubleBuffering(componentToBePrinted);
		componentToBePrinted.paint(g2d);
		enableDoubleBuffering(componentToBePrinted);
		
		final int result = PAGE_EXISTS;
		
		Logger.getInstance().writeMethodExit(this.getClass(), "print", result);  //$NON-NLS-1$
		return result;
	}
}