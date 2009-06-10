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

import ch.sisprocom.bogatyr.helper.launcher.LauncherMail;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090610
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
			LauncherMail.mail("yourname@yourOtherMail.com"); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}
