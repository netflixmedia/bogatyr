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
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import net.laubenberger.bogatyr.HelperResource;

import org.junit.Test;

/**
 * JUnit test for {@link HelperCompress}
 * 
 * @author Stefan Laubenberger
 * @version 20101110
 */
public class HelperCompressTest {

	private File fileZip;
	private File fileMid;
	private File fileText;
	private File fileWav;
	private File fileZipExtractDir;

	{
		try {
			fileZip = HelperIO.getTemporaryFile("test", "zip"); //$NON-NLS-1$ //$NON-NLS-2$

			fileMid = HelperIO.getTemporaryFile("test", "mid"); //$NON-NLS-1$ //$NON-NLS-2$
			HelperIO.writeFile(fileMid, getClass().getResourceAsStream(HelperResource.RES_FILE_MID));

			fileText = HelperIO.getTemporaryFile("test", "txt"); //$NON-NLS-1$ //$NON-NLS-2$
			HelperIO.writeFile(fileText, getClass().getResourceAsStream(HelperResource.RES_FILE_TXT));

			fileWav = HelperIO.getTemporaryFile("test", "wav"); //$NON-NLS-1$ //$NON-NLS-2$
			HelperIO.writeFile(fileWav, getClass().getResourceAsStream(HelperResource.RES_FILE_WAV));

			fileZipExtractDir = new File(HelperEnvironment.getOsTempDirectory(), "HelperCompressTest_extract"); //$NON-NLS-1$
			if (!fileZipExtractDir.exists()) {
				HelperIO.delete(fileZipExtractDir);
			}
			fileZipExtractDir.mkdir();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testPassWriteAndExtractZip() {

		try {
			HelperCompress.writeZip(fileZip, fileMid, fileText, fileWav);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}

		try {
			HelperCompress.extractZip(fileZip, fileZipExtractDir);
		} catch (IOException ex) {
			fail(ex.getMessage());
		}

		assertEquals(fileMid.length(), new File(fileZipExtractDir + HelperEnvironment.getOsTempDirectory().getAbsolutePath(), fileMid.getName()).length());
		assertEquals(fileText.length(), new File(fileZipExtractDir + HelperEnvironment.getOsTempDirectory().getAbsolutePath(), fileText
				.getName()).length());
		assertEquals(fileWav.length(), new File(fileZipExtractDir + HelperEnvironment.getOsTempDirectory().getAbsolutePath(), fileWav
				.getName()).length());
	}

	@Test
	public void testFailWriteZip() {

		try {
			HelperCompress.writeZip(null, fileMid, fileText, fileWav);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperCompress.writeZip(fileZip, null);
			fail("files is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperCompress.writeZip(fileZip, new File[0]);
			fail("files is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperCompress.writeZip(fileZip, fileMid, null, fileWav);
			fail("entry is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFailExtractZip() {

		try {
			HelperCompress.extractZip(null, fileZipExtractDir);
			fail("file is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperCompress.extractZip(fileZip, null);
			fail("destinationDirectory is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
