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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.sound.midi.Sequence;
import javax.sound.sampled.Clip;

import org.junit.Test;

import net.laubenberger.bogatyr.HelperResource;
import net.laubenberger.bogatyr.helper.HelperSound;


/**
 * JUnit test for {@link HelperSound}
 *
 * @author Stefan Laubenberger
 * @version 20101103
 */
public class HelperSoundTest {
	@Test
	public void testGetAndPlayClip() {
		try {
			final Clip clip = HelperSound.getClip(getClass().getResourceAsStream(HelperResource.RES_FILE_WAV));

			assertNotNull(clip);

			HelperSound.play(clip);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetAndPlaySequence() {
		try {
			final Sequence sequence = HelperSound.getSequence(getClass().getResourceAsStream(HelperResource.RES_FILE_MID));

			assertNotNull(sequence);

			HelperSound.play(sequence);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetAvailableClipFormats() {
		assertNotNull(HelperSound.getAvailableClipFormats());
	}

}


