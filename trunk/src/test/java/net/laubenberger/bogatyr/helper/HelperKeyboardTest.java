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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * JUnit test for {@link HelperKeyboard}
 *
 * @author Stefan Laubenberger
 * @version 20101106
 */
public class HelperKeyboardTest {
	@Test
	public void testIsKeyPrintable() {
		assertTrue(HelperKeyboard.isKeyPrintable(65)); //A

		assertFalse(HelperKeyboard.isKeyPrintable(-10));
		assertFalse(HelperKeyboard.isKeyPrintable(8));
		assertFalse(HelperKeyboard.isKeyPrintable(33));
		assertFalse(HelperKeyboard.isKeyPrintable(112));
		assertFalse(HelperKeyboard.isKeyPrintable(127));
	}
}


