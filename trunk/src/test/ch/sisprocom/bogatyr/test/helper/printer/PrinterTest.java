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
package ch.sisprocom.bogatyr.test.helper.printer;

import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.helper.printer.Printer;
import ch.sisprocom.bogatyr.view.swing.Button;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090522
 */
public class PrinterTest {
	@Test
	public void testPrint() {
		try {
			final Component component = new Button("Hello world", HelperString.EMPTY_STRING); //$NON-NLS-1$ 
			component.setBackground(Color.YELLOW);
			component.setForeground(Color.BLACK);
            component.setFont(new Font("Arial", Font.PLAIN, HelperNumber.VALUE_16)); //$NON-NLS-1$;
			component.setSize(new Dimension(100, 100));
			
			Printer printer = new Printer();
			printer.print(component, true);
			printer.print(component, false);
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}


