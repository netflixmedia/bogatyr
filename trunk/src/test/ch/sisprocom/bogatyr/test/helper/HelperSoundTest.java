/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.sound.midi.Sequence;
import javax.sound.sampled.Clip;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperSound;


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
			final Clip clip = HelperSound.getClip(getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.wav")); //$NON-NLS-1$

			assertNotNull(clip);
			
			HelperSound.play(clip);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}

	@Test
	public void testGetAndPlaySequence() {
		try {
			final Sequence sequence = HelperSound.getSequence(getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.mid")); //$NON-NLS-1$
			
			assertNotNull(sequence);
			
			HelperSound.play(sequence);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
	@Test
	public void testGetAvailableAudioFormats() {
		assertNotNull(HelperSound.getAvailableAudioFormats());
	}

}


