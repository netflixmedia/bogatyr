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

package net.laubenberger.bogatyr.helper;

import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import net.laubenberger.bogatyr.view.swing.Button;

import org.junit.Test;

import com.lowagie.text.PageSize;


/**
 * Junit test
 *
 * @author Stefan Laubenberger
 * @version 20100611
 */
public class HelperPdfTest {
	@Test
	public void testCreatePdf() {
		final JComponent component = new Button("Hello world", HelperString.EMPTY_STRING); //$NON-NLS-1$ 
		component.setBackground(Color.GREEN);
		component.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		component.setForeground(Color.RED);
		component.setFont(new Font("Arial", Font.PLAIN, 16)); //$NON-NLS-1$
		component.setSize(new Dimension(100, 100));

		final JComponent component2 = new Button("Hello again", HelperString.EMPTY_STRING); //$NON-NLS-1$ 
		component2.setBackground(Color.GREEN);
		component2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		component2.setForeground(Color.CYAN);
		component2.setFont(new Font("Arial", Font.BOLD, 20)); //$NON-NLS-1$
		component2.setSize(new Dimension(100, 100));

		try {
			File output = HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".pdf"); //$NON-NLS-1$ //$NON-NLS-2$
//			File output = new File("bogatyr.pdf");
			HelperPdf.writePdfFromImage(PageSize.A4, false, output, HelperImage.getImage(component), HelperImage.getImage(component2));
//			LauncherFile.open(output);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

//		try {
//			HelperPdf.writePdfFromImage(PageSize.A4, true, HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".pdf"), null); //$NON-NLS-1$ //$NON-NLS-2$
//			fail("components is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			HelperPdf.writePdfFromImage(PageSize.A4, true, null, component);
//			fail("file is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//	
//		try {
//			HelperPdf.writePdfFromImage(null, true, HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".pdf"), component);
//			fail("pageSize is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
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


