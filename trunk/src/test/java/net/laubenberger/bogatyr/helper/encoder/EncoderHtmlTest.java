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

package net.laubenberger.bogatyr.helper.encoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.helper.HelperString;

import org.junit.Test;


/**
 * JUnit test for {@link EncoderHtml}
 *
 * @author Stefan Laubenberger
 * @version 20101108
 */
public class EncoderHtmlTest {
	//TODO improve test for encode, but how?
	
	@Test
	public void testPassEncode() {
		assertEquals(HelperString.EMPTY_STRING, EncoderHtml.encode(HelperString.EMPTY_STRING));
//		System.err.println(EncoderHtml.encode(AllBogatyrTests.DATA).length());
		assertEquals(2692, EncoderHtml.encode(AllBogatyrTests.DATA).length());
	}

	@Test
	public void testFailEncode() {
		try {
			EncoderHtml.encode(null);
			fail("input is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}


