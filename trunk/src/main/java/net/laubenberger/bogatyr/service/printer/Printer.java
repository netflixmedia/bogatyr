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

package net.laubenberger.bogatyr.service.printer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.RepaintManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.service.ServiceAbstract;


/**
 * This is a printer class for print operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.5.0
 */
public class Printer extends ServiceAbstract implements Printable {
	private static final Logger log = LoggerFactory.getLogger(Printer.class);

	private Component componentToBePrinted;
	private boolean isScaled;


	public Printer() {
		super();
		log.trace(HelperLog.constructor());
	}

	/**
	 * Print a component {@link Component}.
	 *
	 * @param component for printing
	 * @param isScaled  true/false to fit the print page
	 * @throws PrinterException
	 * @see Component
	 * @since 0.5.0
	 */
	public synchronized void print(final Component component, final boolean isScaled) throws PrinterException {
		log.debug(HelperLog.methodStart(component, isScaled));
		if (null == component) {
			throw new RuntimeExceptionIsNull("component"); //$NON-NLS-1$
		}

		componentToBePrinted = component;
		this.isScaled = isScaled;

		print();

		log.debug(HelperLog.methodExit());
	}


	/*
	 * Private methods
	 */

	private void print() throws PrinterException {
		log.trace(HelperLog.methodStart());

		final PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog()) {
			printJob.print();
		}

		log.trace(HelperLog.methodExit());
	}

	/**
	 * The speed and quality of printing suffers dramatically if any of the containers have double buffering turned on.
	 * So this turns if off globally.
	 *
	 * @param component to disable double buffering
	 * @since 0.5.0
	 */
	private static void disableDoubleBuffering(final Component component) {
		log.trace(HelperLog.methodStart(component));

		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(false);

		log.trace(HelperLog.methodExit());
	}

	/**
	 * Re-enables double buffering globally.
	 *
	 * @param component to enable double buffering
	 * @since 0.5.0
	 */
	private static void enableDoubleBuffering(final Component component) {
		log.trace(HelperLog.methodStart(component));

		final RepaintManager currentManager = RepaintManager.currentManager(component);
		currentManager.setDoubleBufferingEnabled(true);

		log.trace(HelperLog.methodExit());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public synchronized int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) {
		log.debug(HelperLog.methodStart(graphics, pageFormat, pageIndex));
		if (null == graphics) {
			throw new RuntimeExceptionIsNull("graphics"); //$NON-NLS-1$
		}
		if (null == pageFormat) {
			throw new RuntimeExceptionIsNull("pageFormat"); //$NON-NLS-1$
		}

		int result = NO_SUCH_PAGE;
		if (0 < pageIndex) {
			final Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

			if (isScaled) {
				g2d.scale(pageFormat.getImageableWidth() / (componentToBePrinted.getWidth() + 1), pageFormat.getImageableHeight() / (componentToBePrinted.getHeight() + 1)); //divide by 0 save
			}


			disableDoubleBuffering(componentToBePrinted);
			componentToBePrinted.paint(g2d);
			enableDoubleBuffering(componentToBePrinted);

			result = PAGE_EXISTS;
		}

		log.debug(HelperLog.methodExit(result));
		return result;
	}
}