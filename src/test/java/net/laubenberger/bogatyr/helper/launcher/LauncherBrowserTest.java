/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.helper.launcher;

import static org.junit.Assert.fail;

import java.net.URI;

import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.junit.Test;

/**
 * JUnit test for {@link LauncherBrowser}
 * 
 * @author Stefan Laubenberger
 * @version 20101119
 */
public class LauncherBrowserTest {
	@Test
	public void testPassBrowse() {
		try {
			LauncherBrowser.browse(Constants.BOGATYR.getUrl().toURI());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherBrowser.browse("code.google.com/p/bogatyr/"); //$NON-NLS-1$
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testFailBrowse() {
		try {
			LauncherBrowser.browse((URI)null);
			fail("uri is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

	try {
			LauncherBrowser.browse((String)null);
			fail("url is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherBrowser.browse(HelperString.EMPTY_STRING);
			fail("url is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
