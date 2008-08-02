/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

import java.io.File;

import junit.framework.TestCase;
import ch.orwell.bogatyr.helper.HelperFile;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080527
 */
public class HelperFileTest extends TestCase {
	
	public void testGetFileNames() {
		try {
			HelperFile.getFiles(null, null, false, false, false, true, true);
			fail("null"); //$NON-NLS-1$
		} catch (Exception ex) {}
		
		try {
			HelperFile.getFiles(new File("*blablabla\\asdf"), null, false, false, false, true, true);
			fail("Invalid path");
		} catch (Exception ex) {}
		
//		try {
//			System.out.println(HelperGeneral.dumpList(HelperFile.getFiles(new File("C:/Temp/"), ".pdf", false)));
////			fail("Invalid path");
//		} catch (Exception ex) {}

	}
	//TODO complete all tests
}


