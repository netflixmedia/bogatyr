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

package net.laubenberger.bogatyr.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Test;

import net.laubenberger.bogatyr.helper.HelperCrypto;


/**
 * Junit test
 *
 * @author Stefan Laubenberger
 * @version 20100416
 */
public class HelperCryptoTest {

	@Test
	public void testGetRandomKey() {
		assertNotNull(HelperCrypto.getRandomKey(16, '1', '2', '3'));

		try {
			HelperCrypto.getRandomKey(Integer.MIN_VALUE, '1', '2', '3');
			fail("digits must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperCrypto.getRandomKey(16, null);
			fail("data is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetRandomKeyDefault() {
		assertNotNull(HelperCrypto.getRandomKey(16));

		try {
			HelperCrypto.getRandomKey(Integer.MIN_VALUE);
			fail("digits must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetUUID() {
		final UUID a = HelperCrypto.getUUID();
		final UUID b = HelperCrypto.getUUID();

		assertNotNull(a);
		assertNotNull(b);

		assertNotSame(a, b);
	}
}
