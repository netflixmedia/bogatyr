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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class HelperCryptoTest {
	
	@Test
	public void testGetHashCode() {
		try {
			assertNotNull(HelperGeneral.getHashCode("MD5", AllBogatyrTests.DATA)); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			HelperGeneral.getHashCode(null, null);
			fail("algo is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			HelperGeneral.getHashCode("MD5", null); //$NON-NLS-1$
			fail("data is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testGetHashCodeDefault() {
		try {
			assertNotNull(HelperGeneral.getHashCode(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			HelperGeneral.getHashCode(null);
			fail("data is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}

	@Test
    public void testGetRandomKey() {
        assertNotNull(HelperGeneral.getRandomKey(HelperNumber.VALUE_16, new char[]{'1','2','3'}));

        try {
            HelperGeneral.getRandomKey(Integer.MIN_VALUE, new char[]{'1','2','3'});
            fail("digits must be greater than 0"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}

        try {
            HelperGeneral.getRandomKey(HelperNumber.VALUE_16, null);
            fail("data is null"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}
    }

	@Test
    public void testGetRandomKeyDefault() {
		assertNotNull(HelperGeneral.getRandomKey(HelperNumber.VALUE_16));

		try {
            HelperGeneral.getRandomKey(Integer.MIN_VALUE);
            fail("digits must be greater than 0"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}
    }
	
	@Test
    public void testGetUUID() {
		final UUID a = HelperGeneral.getUUID();
		final UUID b = HelperGeneral.getUUID();
		
		assertNotNull(a);
		assertNotNull(b);
		
		assertNotSame(a, b);
    }
}
