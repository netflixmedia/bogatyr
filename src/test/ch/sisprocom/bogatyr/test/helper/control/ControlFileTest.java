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
package ch.sisprocom.bogatyr.test.helper.control;

import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.control.ControlFile;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090527
 */
public class ControlFileTest {
	@Test
	public void testOpen() {
		try {
			ControlFile.open(getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.txt"), ".txt");  //$NON-NLS-1$//$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			ControlFile.open(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testEdit() {
		try {
			ControlFile.edit(getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.txt"), ".txt");  //$NON-NLS-1$//$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			ControlFile.edit(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testPrint() {
		try {
			ControlFile.print(getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.txt"), ".txt"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			ControlFile.print(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
