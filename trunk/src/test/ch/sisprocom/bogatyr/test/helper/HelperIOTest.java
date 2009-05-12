/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090510
 */
public class HelperIOTest { //TODO improve and complete
	@Test
	public void testgetTemporaryFile() {
		try {
			assertNotNull(HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "tmp"));  //$NON-NLS-1$//$NON-NLS-2$
		} catch (IOException ex) {fail(ex.getLocalizedMessage());}
		
		try {
			HelperIO.getTemporaryFile(null, "tmp"); //$NON-NLS-1$
			fail("name is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		try {
			HelperIO.getTemporaryFile("bogatyr_HelperIOTest", null); //$NON-NLS-1$
			fail("extension is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}

	@Test
	public void testGetFiles() {
		assertNotNull(HelperIO.getFiles(HelperEnvInfo.getOsTempDirectory(), null, false, false, false, true, true));
		
		try {
			HelperIO.getFiles(null, new String[]{"tmp"}, false, false, false, true, true); //$NON-NLS-1$
			fail("path is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		try {
			HelperIO.getFiles(new File("*blablabla/asdf"), null, false, false, false, true, true); //$NON-NLS-1$
			fail("Invalid path"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testWriteLine() {
		try {
			final File file = HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "txt");  //$NON-NLS-1$//$NON-NLS-2$
			HelperIO.writeLine(file, AllBogatyrTests.DATA);
			assertEquals(AllBogatyrTests.DATA, HelperIO.readFileAsString(file));
		} catch (IOException ex) {fail(ex.getLocalizedMessage());}
		
		try {
			HelperIO.writeLine(null, AllBogatyrTests.DATA);
			fail("file is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			HelperIO.writeLine(HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "txt"), null);  //$NON-NLS-1$//$NON-NLS-2$
			fail("line is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			HelperIO.writeLine(HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "txt"), null, AllBogatyrTests.DATA);  //$NON-NLS-1$//$NON-NLS-2$
			fail("encoding is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testWriteReadFileAsByteArray() {
		try {
			final File file = HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "ba");  //$NON-NLS-1$//$NON-NLS-2$
			HelperIO.writeFile(file, AllBogatyrTests.DATA.getBytes(), false);
			assertEquals(AllBogatyrTests.DATA, new String(HelperIO.readFile(file)));
		} catch (IOException ex) {fail(ex.getLocalizedMessage());}
		
		try {
			HelperIO.readFile(null);
			fail("file is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testWriteReadFileAsString() {
		try {
			final File file = HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "string");  //$NON-NLS-1$//$NON-NLS-2$
			HelperIO.writeFile(file, AllBogatyrTests.DATA, false);
			assertEquals(AllBogatyrTests.DATA, HelperIO.readFileAsString(file));
		} catch (IOException ex) {fail(ex.getLocalizedMessage());}
		
		try {
			HelperIO.readFileAsString(null);
			fail("file is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			HelperIO.readFileAsString(HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "string"), null); //$NON-NLS-1$ //$NON-NLS-2$
			fail("encoding is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	
	@Test
	public void testWriteReadFileAsStream() {
		try {
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final File file = HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "stream");  //$NON-NLS-1$//$NON-NLS-2$
			final File fileResult = HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "stream");  //$NON-NLS-1$//$NON-NLS-2$
			HelperIO.writeFile(file, AllBogatyrTests.DATA, false);
			HelperIO.readFileAsStream(file, os);
			HelperIO.writeFile(fileResult, HelperIO.convertOutputToInputStream(os), false);
			assertEquals(AllBogatyrTests.DATA, HelperIO.readFileAsString(fileResult));
		} catch (IOException ex) {fail(ex.getLocalizedMessage());}
		
		try {
			HelperIO.readFileAsStream(null, new ByteArrayOutputStream());
			fail("file is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			HelperIO.readFileAsStream(HelperIO.getTemporaryFile("bogatyr_HelperIOTest", "stream"), null);  //$NON-NLS-1$//$NON-NLS-2$
			fail("os is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
}


