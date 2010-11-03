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

import java.io.InputStream;

import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.helper.HelperString;

import org.junit.Test;

/**
 * JUnit test for {@link LauncherFile}
 * 
 * @author Stefan Laubenberger
 * @version 20101103
 */
public class LauncherFileTest {
	private static final String EXTENSION = ".txt"; //$NON-NLS-1$
	private final InputStream is = getClass().getResourceAsStream("/net/laubenberger/bogatyr/test.txt"); //$NON-NLS-1$
	
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
			LauncherFile.open(is, EXTENSION);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testPassEdit() {
		try {
			LauncherFile.edit(is, EXTENSION);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testPassPrint() {
		try {
			LauncherFile.print(is, EXTENSION);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testFailOpen() {
		// InputStream
		try {
			LauncherFile.open(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.open(is, null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.open(is, HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		// Byte-array
		try {
			LauncherFile.open(AllBogatyrTests.DATA.getBytes(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.open(AllBogatyrTests.DATA.getBytes(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFailEdit() {
		// InputStream
		try {
			LauncherFile.edit(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.edit(is, null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.edit(is, HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		// Byte-array
		try {
			LauncherFile.edit(AllBogatyrTests.DATA.getBytes(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.edit(AllBogatyrTests.DATA.getBytes(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFailPrint() {
		// InputStream
		try {
			LauncherFile.print(null);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.print(is, null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.print(is, HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		// Byte-array
		try {
			LauncherFile.print(AllBogatyrTests.DATA.getBytes(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherFile.print(AllBogatyrTests.DATA.getBytes(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
