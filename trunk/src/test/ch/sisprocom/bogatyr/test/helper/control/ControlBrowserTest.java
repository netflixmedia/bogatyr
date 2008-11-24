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
package ch.sisprocom.bogatyr.test.helper.control;

import static org.junit.Assert.fail;

import java.net.URL;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.control.ControlBrowser;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20081124
 */
public class ControlBrowserTest { //TODO improve
	@Test
	public void testDisplayUrl() {
		try {
			ControlBrowser.display(new URL("http://www.sisprocom.ch"));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}


