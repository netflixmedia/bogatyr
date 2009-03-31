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
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperImage;
import ch.sisprocom.bogatyr.view.swing.Button;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20081027
 */
public class HelperImageTest { //TODO improve
	@Test
	public void testCreateImage() {
		try {
			final Component component = new Button("Hello world", "");
			component.setBackground(Color.YELLOW);
			component.setForeground(Color.BLACK);
			component.setFont(new Font("Arial", Font.PLAIN, 18));
			component.setSize(new Dimension(100, 100));
			
			HelperImage.saveImage(component, HelperImage.TYPE_JPG, new File(HelperEnvInfo.getOsTempDirectory(), "test.jpg"));
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
}
