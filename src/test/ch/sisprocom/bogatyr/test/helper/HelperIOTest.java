/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperIO;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20081027
 */
public class HelperIOTest { //TODO improve and complete
	@Test
	public void testGetFileNames() {
		try {
			HelperIO.getFiles(null, null, false, false, false, true, true);
			fail("null"); //$NON-NLS-1$
		} catch (Exception ex) {}
		
		try {
			HelperIO.getFiles(new File("*blablabla/asdf"), null, false, false, false, true, true);
			fail("Invalid path");
		} catch (Exception ex) {}
		
//		try {
//			System.out.println(HelperGeneral.dumpList(HelperFile.getFiles(new File("C:/Temp/"), ".pdf", false)));
////			fail("Invalid path");
//		} catch (Exception ex) {}

	}
}


