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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.context.Context;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20081027
 */
public class ContextTest {
	
	@Test
	public void testAddData() {
		try {
			Context.getInstance().addData("Stefan", "Laubenberger");
			Context.getInstance().addData("Silvan", "Spross");
			
			Context.getInstance().removeData("Stefan");
			
			assertNull(Context.getInstance().getData("Stefan"));
			assertNotNull(Context.getInstance().getData("Silvan"));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
//	@Test
//	public void testGetApplicationName() {
//		assertNotNull(Context.getInstance().getApplicationName());
//	}
//	
//	@Test
//	public void testGetApplicationVersion() {
//		assertNotNull(Context.getInstance().getApplicationVersion());
//	}
//	
//	@Test
//	public void testGetApplicationBuild() {
//		assertNotNull(Context.getInstance().getApplicationBuild());
//	}
//	
//	@Test
//	public void testGetApplicationWorkDirectory() {
//		assertNotNull(Context.getInstance().getApplicationWorkDirectory());
//	}
}
