/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.test.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import junit.framework.TestCase;
import ch.orwell.bogatyr.helper.HelperEnvInfo;
import ch.orwell.bogatyr.helper.HelperImage;
import ch.orwell.bogatyr.view.swing.Button;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080803
 */
public class HelperImageTest extends TestCase { //TODO improve
	
	public void testCreateImage() {
		try {
			Component component = new Button("Hello world", "");
			component.setBackground(Color.YELLOW);
			component.setForeground(Color.BLACK);
			component.setFont(new Font("Arial", Font.PLAIN, 18));
			component.setSize(new Dimension(100, 100));
			
			HelperImage.createImage(component, HelperImage.TYPE_JPG, new File(HelperEnvInfo.getOsTempDirectory(), "test.jpg"));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}
