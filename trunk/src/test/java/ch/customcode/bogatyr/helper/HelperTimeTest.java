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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package ch.customcode.bogatyr.helper;

import static org.junit.Assert.fail;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperTime;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100203
 */
public class HelperTimeTest {
	@Test
	public void testGetAtomicTime() {
//		try {
//			assertEquals(HelperTime.getFormattedDate(new Date(), HelperTime.FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE), HelperTime.getFormattedDate(HelperTime.getAtomicTime(), HelperTime.FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE));
//		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
//
//		try {
//			assertEquals(HelperTime.getFormattedDate(new Date(), HelperTime.FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE), HelperTime.getFormattedDate(HelperTime.getAtomicTime("ptbtime2.ptb.de"), HelperTime.FORMAT_DAY_MONTH_YEAR_HOUR_MINUTE)); //$NON-NLS-1$
//		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}

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


