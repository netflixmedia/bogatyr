/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperPdf;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.view.swing.Button;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100215
 */
public class HelperPdfTest {
	@Test
	public void testCreatePdf() {
		final Component component = new Button("Hello world", HelperString.EMPTY_STRING); //$NON-NLS-1$ 
		component.setBackground(Color.YELLOW);
		component.setForeground(Color.BLACK);
		component.setFont(new Font("Arial", Font.PLAIN, 16)); //$NON-NLS-1$
		component.setSize(new Dimension(100, 100));

		try {
			HelperPdf.writePdfFromComponent(HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".pdf"), component); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperPdf.writePdfFromComponent(HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".pdf"), null); //$NON-NLS-1$ //$NON-NLS-2$
			fail("component is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperPdf.writePdfFromComponent(null, component);
			fail("file is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testWritePdfFromHTML() { //TODO improve
		try {
			HelperPdf.writePdfFromHTML(HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".pdf"), null); //$NON-NLS-1$ //$NON-NLS-2$
			fail("input is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperPdf.writePdfFromHTML(null, HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".html")); //$NON-NLS-1$ //$NON-NLS-2$
			fail("file is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}


