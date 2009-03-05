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


/**
 * This is a printer class for print operations.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public class Printer implements Printable { //TODO document in Wiki!
	private Component componentToBePrinted;


	public Printer() {
        super();
    }

    /**
     * Print a component.
     *
     * @param component for printing
     * @throws java.awt.print.PrinterException
     */
    public synchronized void print(final Component component) throws PrinterException {
		this.componentToBePrinted = component;
        print();
	}


	/*
	 * Private methods
	 */
	private void print() throws PrinterException {
		final PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog()) {
			printJob.print();
		}
	}
	
	/**
	 * The speed and quality of printing suffers dramatically if any of thecontainers have double buffering turned on.
	 * So this turns if off globally.
     *
     * @param component
	 */
	private void disableDoubleBuffering(final Component component) {
		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(false);
	}

	/** Re-enables double buffering globally.
     *
     * @param component
     */
	private void enableDoubleBuffering(final Component component) {
		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(true);
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
		
		return PAGE_EXISTS;
	}
}