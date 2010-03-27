/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper.encoder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.customcode.bogatyr.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100209
 */
public class EncoderHexTest {
	@Test
	public void testEncodeAndDecode() {
		final String encoded = EncoderHex.encode(AllBogatyrTests.DATA.getBytes());
		final byte[] decoded = EncoderHex.decode(encoded);
		
		assertEquals(AllBogatyrTests.DATA, new String(decoded));
		
//		try {
//			EncoderHex.encode(null);
//			fail("byte[] is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			EncoderHex.encode(HelperArray.EMPTY_ARRAY_BYTE);
//			fail("byte[] is empty!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			EncoderHex.decode(null);
//			fail("String is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
//
//		try {
//			EncoderHex.decode(""); //$NON-NLS-1$
//			fail("String is empty!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
}


