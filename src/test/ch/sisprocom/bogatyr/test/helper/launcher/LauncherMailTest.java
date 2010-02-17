/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
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

import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.helper.launcher.LauncherMail;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100215
 */
public class LauncherMailTest {
	@Test
	public void testMail() {
		try {
			LauncherMail.mail();
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			LauncherMail.mail(new URI("mailto:yourname@yourmail.com")); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			LauncherMail.mail("Test", "This is a testäöäü:" + HelperString.NEW_LINE + "Yeah, the new line is here!", "yourname@yourOtherMail.com", "anothername@anotherMail.com"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}
