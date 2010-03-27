/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.sound.midi.Sequence;
import javax.sound.sampled.Clip;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperSound;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public class HelperSoundTest {
	@Test
	public void testGetAndPlayClip() {
		try {
			final Clip clip = HelperSound.getClip(getClass().getResourceAsStream("/ch/customcode/bogatyr/test.wav")); //$NON-NLS-1$

			assertNotNull(clip);
			
			HelperSound.play(clip);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}

	@Test
	public void testGetAndPlaySequence() {
		try {
			final Sequence sequence = HelperSound.getSequence(getClass().getResourceAsStream("/ch/customcode/bogatyr/test.mid")); //$NON-NLS-1$
			
			assertNotNull(sequence);
			
			HelperSound.play(sequence);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
	@Test
	public void testGetAvailableAudioFormats() {
		assertNotNull(HelperSound.getAvailableAudioFormats());
	}

}


