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
package ch.sisprocom.bogatyr.service.printer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.RepaintManager;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is a printer class for print operations.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091027)
 * @since 0.5.0
 */
public class Printer extends ServiceAbstract implements Printable {
	private Component componentToBePrinted;
	private boolean isScaled;

    /**
     * Print a component {@link Component}.
     *
     * @param component for printing
     * @param isScaled true/false to fit the print page
     * @throws PrinterException
     * @see Component
     * @since 0.5.0
     */
    public synchronized void print(final Component component, final boolean isScaled) throws PrinterException {
		if (null == component) {
			throw new RuntimeExceptionArgumentIsNull("component"); //$NON-NLS-1$
		}

    	componentToBePrinted = component;
    	this.isScaled = isScaled;
    	
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
	 * The speed and quality of printing suffers dramatically if any of the containers have double buffering turned on.
	 * So this turns if off globally.
     *
     * @param component to disable double buffering
     * @since 0.5.0
	 */
	private static void disableDoubleBuffering(final Component component) {
		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(false);
	}

	/** Re-enables double buffering globally.
     *
     * @param component to enable double buffering
     * @since 0.5.0
     */
	private static void enableDoubleBuffering(final Component component) {
		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(true);
	}


    /*
     * Implemented methods
     */
    @Override
    public synchronized int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) {
		if (null == graphics) {
			throw new RuntimeExceptionArgumentIsNull("graphics"); //$NON-NLS-1$
		}
		if (null == pageFormat) {
			throw new RuntimeExceptionArgumentIsNull("pageFormat"); //$NON-NLS-1$
		}

    	if (0 < pageIndex) {
			return NO_SUCH_PAGE;
		}
//		componentToBePrinted.setSize((int)(componentToBePrinted.getWidth() * pageFormat.getWidth()/componentToBePrinted.getWidth()), (int)(componentToBePrinted.getHeight() * pageFormat.getHeight()/componentToBePrinted.getHeight()));

		final Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		if (isScaled) {
			g2d.scale(pageFormat.getImageableWidth()/(componentToBePrinted.getWidth() + 1), pageFormat.getImageableHeight()/(componentToBePrinted.getHeight() + 1)); //divide by 0 save
		}


        disableDoubleBuffering(componentToBePrinted);
		componentToBePrinted.paint(g2d);
		enableDoubleBuffering(componentToBePrinted);
		
		return PAGE_EXISTS;
	}
}