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

package net.laubenberger.bogatyr.helper.launcher;

import static org.junit.Assert.fail;
import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.resource.ResourceMisc;

import org.junit.Test;

/**
 * JUnit test for {@link LauncherFile}
 * 
 * @author Stefan Laubenberger
 * @version 20101119
 */
public class LauncherFileTest {
//	@BeforeClass
//	public static void init() {
//		try {
//			LauncherFile.deleteTemporaryFiles();
//		} catch (IOException ex) {
//			fail(ex.getMessage());
//		}
//	}
	
	@Test
	public void testPassOpen() {
		try {
			LauncherFile.open(ResourceMisc.TXT.getResourceAsFile());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testPassEdit() {
		try {
			LauncherFile.edit(ResourceMisc.TXT.getResourceAsFile());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testPassPrint() {
		try {
			LauncherFile.print(ResourceMisc.TXT.getResourceAsFile());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testFailOpen() {
		try {
			LauncherFile.open(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.open(ResourceMisc.TXT.getResourceAsStream(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.open(ResourceMisc.TXT.getResourceAsStream(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		// Byte-array
		try {
			LauncherFile.open(AllBogatyrTests.DATA.getBytes(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.open(AllBogatyrTests.DATA.getBytes(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFailEdit() {
		try {
			LauncherFile.edit(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.edit(ResourceMisc.TXT.getResourceAsStream(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.edit(ResourceMisc.TXT.getResourceAsStream(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		// Byte-array
		try {
			LauncherFile.edit(AllBogatyrTests.DATA.getBytes(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.edit(AllBogatyrTests.DATA.getBytes(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFailPrint() {
		try {
			LauncherFile.print(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.print(ResourceMisc.TXT.getResourceAsStream(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.print(ResourceMisc.TXT.getResourceAsStream(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		// Byte-array
		try {
			LauncherFile.print(AllBogatyrTests.DATA.getBytes(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.print(AllBogatyrTests.DATA.getBytes(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
