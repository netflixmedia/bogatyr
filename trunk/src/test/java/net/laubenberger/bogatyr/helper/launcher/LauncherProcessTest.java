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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperEnvironment;
import net.laubenberger.bogatyr.helper.HelperIO;
import net.laubenberger.bogatyr.model.misc.Platform;

import org.junit.Test;

/**
 * JUnit test for {@link LauncherProcess}
 * 
 * @author Stefan Laubenberger
 * @version 20101103
 */
public class LauncherProcessTest {
	private File fileOutput;
	private File fileError;
	private OutputStream osOutput;
	private OutputStream osError;

	{
		try {
			fileOutput = HelperIO.getTemporaryFile();
			fileError = HelperIO.getTemporaryFile();
			osOutput = new FileOutputStream(fileOutput);
			osError = new FileOutputStream(fileError);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testPassCreateAndStartProcess() {

		if (HelperEnvironment.getPlatform() == Platform.WINDOWS) {
			try {
				LauncherProcess.createAndStartProcess(new String[] { "dir" }, osOutput, osError); //$NON-NLS-1$
			} catch (IOException ex) {
				fail(ex.getMessage());
			}

			sleep();
			
			assertTrue(fileOutput.length() > 0);

			try {
				LauncherProcess.createAndStartProcess(new String[] { "dir", "-ä" }, osOutput, osError); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (IOException ex) {
				fail(ex.getMessage());
			}

			sleep();
			
			assertTrue(fileError.length() > 0);
		} else {
			try {
				LauncherProcess.createAndStartProcess(new String[] { "ls", "-l" }, osOutput, osError); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (IOException ex) {
				fail(ex.getMessage());
			}

			sleep();
			
			assertTrue(fileOutput.length() > 0);

			try {
				LauncherProcess.createAndStartProcess(new String[] { "ls", "-ä" }, osOutput, osError); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (IOException ex) {
				fail(ex.getMessage());
			}

			sleep();

			assertTrue(fileError.length() > 0);
		}
	}

	@Test
	public void testFailCreateAndStartProcess() {
		try {
			LauncherProcess.createAndStartProcess(null);
			fail("commands is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherProcess.createAndStartProcess(HelperArray.EMPTY_ARRAY_STRING);
			fail("commands is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherProcess.createAndStartProcess(null, osOutput, osError);
			fail("commands is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			LauncherProcess.createAndStartProcess(HelperArray.EMPTY_ARRAY_STRING, osOutput, osError);
			fail("commands is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherProcess.createAndStartProcess(new String[]{"ls"}, null, osError); //$NON-NLS-1$
			fail("outputStream is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			LauncherProcess.createAndStartProcess(new String[]{"ls"}, osOutput, null); //$NON-NLS-1$
			fail("errorStream is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			// nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			fail(ex.getMessage());
		}
	}
}
