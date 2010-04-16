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

import net.laubenberger.bogatyr.AllBogatyrTests;

import org.junit.Test;


/**
 * Junit test
 *
 * @author Stefan Laubenberger
 * @version 20100416
 */
public class EncoderBase64Test {
	@Test
	public void testEncodeAndDecode() {
		final String encoded = EncoderBase64.encode(AllBogatyrTests.DATA);
		final byte[] decoded = EncoderBase64.decode(encoded);

		assertEquals(AllBogatyrTests.DATA, new String(decoded));

//		try {
//			EncoderBase64.encode(""); //$NON-NLS-1$
//			fail("String is empty!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			EncoderBase64.encode(HelperArray.EMPTY_ARRAY_BYTE);
//			fail("byte[] is empty!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			EncoderBase64.decode(""); //$NON-NLS-1$
//			fail("String is empty!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			EncoderBase64.decode(HelperArray.EMPTY_ARRAY_CHAR);
//			fail("char[] is empty!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
}


