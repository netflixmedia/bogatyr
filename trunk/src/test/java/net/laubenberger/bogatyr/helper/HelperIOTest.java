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

package net.laubenberger.bogatyr.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.junit.Test;


/**
 * JUnit test for {@link HelperIO}
 *
 * @author Stefan Laubenberger
 * @version 20101119
 */
public class HelperIOTest { //TODO complete tests for all methods
	@Test
	public void testgetTemporaryFile() {
		try {
			assertNotNull(HelperIO.getTemporaryFile(getClass().getSimpleName(), "tmp"));  //$NON-NLS-1$
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}

		try {
			assertNotNull(HelperIO.getTemporaryFile("tmp"));  //$NON-NLS-1$
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}
		
		try {
			assertNotNull(HelperIO.getTemporaryFile());
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}

		try {
			HelperIO.getTemporaryFile(null, "tmp"); //$NON-NLS-1$
			fail("name is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperIO.getTemporaryFile(HelperString.EMPTY_STRING, "tmp"); //$NON-NLS-1$
			fail("name is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperIO.getTemporaryFile(getClass().getSimpleName(), null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperIO.getTemporaryFile(getClass().getSimpleName(), HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		
		try {
			HelperIO.getTemporaryFile(null);
			fail("extension is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperIO.getTemporaryFile(HelperString.EMPTY_STRING);
			fail("extension is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetFiles() {
		assertTrue(HelperIO.getFiles(HelperEnvironment.getOsTempDirectory(), null, -1).size() > 0);

		try {
			HelperIO.getFiles(null, null, -1);
			fail("path is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testWriteLine() {
		try {
			final File file = HelperIO.getTemporaryFile(getClass().getSimpleName(), "txt");  //$NON-NLS-1$
			HelperIO.writeLine(file, AllBogatyrTests.DATA);
			assertEquals(AllBogatyrTests.DATA, HelperIO.readFileAsString(file));
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}

		try {
			HelperIO.writeLine(null, AllBogatyrTests.DATA);
			fail("file is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

//		try {
//			HelperIO.writeLine(HelperIO.getTemporaryFile("bogatyr_" + getClass().getSimpleName(), ".txt"), null);  //$NON-NLS-1$//$NON-NLS-2$
//			fail("line is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}

		try {
			HelperIO.writeLine(HelperIO.getTemporaryFile(getClass().getSimpleName(), "txt"), null, AllBogatyrTests.DATA);  //$NON-NLS-1$
			fail("encoding is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testWriteReadFileAsByteArray() {
		try {
			final File file = HelperIO.getTemporaryFile(getClass().getSimpleName(), ".ba");  //$NON-NLS-1$
			HelperIO.writeFile(file, AllBogatyrTests.DATA.getBytes(), false);
			assertEquals(AllBogatyrTests.DATA, new String(HelperIO.readFile(file)));
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}

		try {
			HelperIO.readFile(null);
			fail("file is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testWriteReadFileAsString() {
		try {
			final File file = HelperIO.getTemporaryFile(getClass().getSimpleName(), ".string");  //$NON-NLS-1$
			HelperIO.writeFile(file, AllBogatyrTests.DATA, false);
			assertEquals(AllBogatyrTests.DATA, HelperIO.readFileAsString(file));
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}

		try {
			HelperIO.readFileAsString(null);
			fail("file is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperIO.readFileAsString(HelperIO.getTemporaryFile(getClass().getSimpleName(), ".string"), null); //$NON-NLS-1$
			fail("encoding is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}


	@Test
	public void testWriteReadFileAsStream() {
		try {
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final File file = HelperIO.getTemporaryFile(getClass().getSimpleName(), ".stream");  //$NON-NLS-1$
			final File fileResult = HelperIO.getTemporaryFile(getClass().getSimpleName(), ".stream");  //$NON-NLS-1$
			HelperIO.writeFile(file, AllBogatyrTests.DATA, false);
			HelperIO.readFileAsStream(file, os);
			HelperIO.writeFile(fileResult, HelperIO.convertOutputToInputStream(os), false);
			assertEquals(AllBogatyrTests.DATA, HelperIO.readFileAsString(fileResult));
		} catch (IOException ex) {
			fail(ex.getLocalizedMessage());
		}

		try {
			HelperIO.readFileAsStream(null, new ByteArrayOutputStream());
			fail("file is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperIO.readFileAsStream(HelperIO.getTemporaryFile(getClass().getSimpleName(), ".stream"), null);  //$NON-NLS-1$
			fail("os is null!"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}


