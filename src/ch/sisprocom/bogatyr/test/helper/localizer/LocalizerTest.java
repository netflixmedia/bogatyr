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
package ch.sisprocom.bogatyr.test.helper.localizer;

import junit.framework.TestCase;
import ch.sisprocom.bogatyr.helper.localizer.Localizer;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class LocalizerTest extends TestCase { //TODO improve

	public void testGetLocales() {
		assertNotNull(Localizer.getLocales());
	}

	public void testGetCountries() {
		assertNotNull(Localizer.getCountries());
//		System.out.println(HelperGeneral.dumpList(Localizer.getCountries()));
	}
}


