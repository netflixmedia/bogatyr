/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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

import javax.sound.midi.Sequence;
import javax.sound.sampled.Clip;

import junit.framework.TestCase;
import ch.sisprocom.bogatyr.helper.HelperSound;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public class HelperSoundTest extends TestCase {
	public void testGetAndPlayClip() {
		try {
			final Clip clip = HelperSound.getClip(this.getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.wav"));

			HelperSound.play(clip);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
	public void testGetAndPlaySequence() {
		try {
			final Sequence sequence = HelperSound.getSequence(this.getClass().getResourceAsStream("/res/ch/sisprocom/bogatyr/test/test.mid"));
			
			HelperSound.play(sequence);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	//TODO complete
}


