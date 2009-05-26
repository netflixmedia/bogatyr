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
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperTime;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090527
 */
public class HelperTimeTest {
	private final DateFormat formatter = new SimpleDateFormat("dd.mm.yyyy", Locale.getDefault()); //$NON-NLS-1$
	
	@Test
	public void testGetAtomicTime() {
		try {
			assertEquals(formatter.format(new Date()), formatter.format(HelperTime.getAtomicTime()));
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}

		try {
			assertEquals(formatter.format(new Date()), formatter.format(HelperTime.getAtomicTime("ptbtime2.ptb.de"))); //$NON-NLS-1$
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}

		try {
			HelperTime.getAtomicTime(null);
			fail("host is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}


