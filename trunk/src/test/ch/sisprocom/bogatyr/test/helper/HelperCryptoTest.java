/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperCrypto;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.UUID;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091016
 */
public class HelperCryptoTest {

	@Test
    public void testGetRandomKey() {
        assertNotNull(HelperCrypto.getRandomKey(HelperNumber.VALUE_16, '1','2','3'));

        try {
        	HelperCrypto.getRandomKey(Integer.MIN_VALUE, '1','2','3');
            fail("digits must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

        try {
        	HelperCrypto.getRandomKey(HelperNumber.VALUE_16, null);
            fail("data is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
   }

	@Test
    public void testGetRandomKeyDefault() {
		assertNotNull(HelperCrypto.getRandomKey(HelperNumber.VALUE_16));

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
