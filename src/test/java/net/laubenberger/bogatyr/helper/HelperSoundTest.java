/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;

import javax.sound.midi.Sequence;
import javax.sound.sampled.Clip;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.resource.ResourceSound;

import org.junit.Test;


/**
 * JUnit test for {@link HelperSound}
 *
 * @author Stefan Laubenberger
 * @version 20101227
 */
public class HelperSoundTest {
	@Test
	public void testGetClip() {
		try {
			assertNotNull(HelperSound.getClip(ResourceSound.WAV.getResourceAsStream()));
			assertNotNull(HelperSound.getClip(ResourceSound.WAV.getResourceAsFile()));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.getClip((File)null);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.getClip((InputStream)null);
			fail("is is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testPlayClip() {
		try {
			HelperSound.play(HelperSound.getClip(ResourceSound.WAV.getResourceAsStream()));
			HelperSound.play(HelperSound.getClip(ResourceSound.WAV.getResourceAsFile()));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.play((Clip)null);
			fail("clip is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetSequence() {
		try {
			assertNotNull(HelperSound.getSequence(ResourceSound.MID.getResourceAsStream()));
			assertNotNull(HelperSound.getSequence(ResourceSound.MID.getResourceAsFile()));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.getSequence((File)null);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.getSequence((InputStream)null);
			fail("is is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testPlaySequence() {
		try {
			HelperSound.play(HelperSound.getSequence(ResourceSound.MID.getResourceAsStream()));
			HelperSound.play(HelperSound.getSequence(ResourceSound.MID.getResourceAsFile()));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.play((Sequence)null);
			fail("sequence is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetSequencer() {
		try {
			assertNotNull(HelperSound.getSequencer(HelperSound.getSequence(ResourceSound.MID.getResourceAsStream())));
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		try {
			HelperSound.getSequencer(null);
			fail("sequence is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetAvailableClipFormats() {
		//System.err.println(HelperCollection.dump(HelperSound.getAvailableClipFormats()));
		assertTrue(2 < HelperSound.getAvailableClipFormats().size());
	}

}