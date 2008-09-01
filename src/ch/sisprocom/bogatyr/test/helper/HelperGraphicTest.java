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
package ch.sisprocom.bogatyr.test.helper;

import junit.framework.TestCase;
import ch.sisprocom.bogatyr.helper.HelperGraphic;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080803
 */
public class HelperGraphicTest extends TestCase { //TODO improve
	
	public void testGetFonts() {
		assertNotNull(HelperGraphic.getFonts());
//		System.out.println(HelperGeneral.dumpList(HelperGraphic.getFonts()));
	}
}


