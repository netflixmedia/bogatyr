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

package net.laubenberger.bogatyr.helper.launcher;

import static org.junit.Assert.fail;

import java.net.URI;

import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperString;

import org.junit.Test;

/**
 * JUnit test for {@link LauncherMail}
 * 
 * @author Stefan Laubenberger
 * @version 20101103
 */
public class LauncherMailTest {
	@Test
	public void testPassMail() {
		try {
			LauncherMail.mail();
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherMail.mail(new URI("mailto:yourname@yourmail.com")); //$NON-NLS-1$
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherMail
					.mail(
							"Test", "This is a test:" + HelperString.NEW_LINE + AllBogatyrTests.DATA  + HelperString.NEW_LINE + "Yeah, all lines are here! :-)", "yourname@yourOtherMail.com", "anothername@anotherMail.com"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherMail
					.mail(
							"Test2", null, "yourname@yourOtherMail.com", "anothername@anotherMail.com"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testFailMail() {
		try {
			LauncherMail.mail(null);
			fail("uri is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherMail
					.mail(
							null, "This is a test:" + HelperString.NEW_LINE + AllBogatyrTests.DATA  + HelperString.NEW_LINE + "Yeah, all lines are here! :-)", "yourname@yourOtherMail.com", "anothername@anotherMail.com"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			fail("subject is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherMail
					.mail(
							"", "This is a test:" + HelperString.NEW_LINE + AllBogatyrTests.DATA  + HelperString.NEW_LINE + "Yeah, all lines are here! :-)", "yourname@yourOtherMail.com", "anothername@anotherMail.com"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			fail("subject is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherMail
					.mail(
							"Test", null, (String)null); //$NON-NLS-1$
			fail("emailAddresses is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherMail
					.mail(
							"Test", null, HelperArray.EMPTY_ARRAY_STRING); //$NON-NLS-1$
			fail("emailAddresses is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
