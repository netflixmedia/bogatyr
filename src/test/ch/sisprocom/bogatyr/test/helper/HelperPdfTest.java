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
package ch.sisprocom.bogatyr.test.helper;

import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperPdf;
import ch.sisprocom.bogatyr.helper.Const;
import ch.sisprocom.bogatyr.view.swing.Button;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 2009510
 */
public class HelperPdfTest { //TODO improve
	@Test
	public void testCreatePdf() {
		try {
			final Component component = new Button("Hello world", ""); //$NON-NLS-1$ //$NON-NLS-2$
			component.setBackground(Color.YELLOW);
			component.setForeground(Color.BLACK);
			component.setFont(new Font("Arial", Font.PLAIN, Const.VALUE_16)); //$NON-NLS-1$
			component.setSize(new Dimension(100, 100));
			
			HelperPdf.savePdfFromComponent(component, HelperIO.getTemporaryFile("bogatyr_HelperPdfTest", "pdf")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}


