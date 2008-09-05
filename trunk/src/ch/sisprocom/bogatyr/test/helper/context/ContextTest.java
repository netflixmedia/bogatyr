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
package ch.sisprocom.bogatyr.test.helper.context;

import junit.framework.TestCase;
import ch.sisprocom.bogatyr.helper.context.Context;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class ContextTest extends TestCase {
	
	public void testAddData() {
		try {
			Context.getInstance().addData("Stefan", "Laubenberger");
			Context.getInstance().addData("Silvan", "Spross");
			
			Context.getInstance().removeData("Stefan");
			
			assertNull(Context.getInstance().getData("Stefan"));
			assertNotNull(Context.getInstance().getData("Silvan"));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testGetApplicationName() {
		assertNotNull(Context.getInstance().getApplicationName());
	}
	
	public void testGetApplicationVersion() {
		assertNotNull(Context.getInstance().getApplicationVersion());
	}
	public void testGetApplicationBuild() {
		assertNotNull(Context.getInstance().getApplicationBuild());
	}
	public void testGetApplicationWorkDirectory() {
		assertNotNull(Context.getInstance().getApplicationWorkDirectory());
	}
}