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
package ch.sisprocom.bogatyr.test.helper.launcher;

import static org.junit.Assert.fail;

import java.net.URI;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.launcher.LauncherBrowser;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090610
 */
public class LauncherBrowserTest {
	@Test
	public void testBrowse() {
		try {
			LauncherBrowser.browse(new URI("http://www.sisprocom.ch/bogatyr/")); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			LauncherBrowser.browse("code.google.com/p/bogatyr/"); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}

	}
}
