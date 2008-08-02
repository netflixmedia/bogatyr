/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.Clip;

import junit.framework.TestCase;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080728
 */
public class HelperSoundTest extends TestCase {
	public void testGetAndPlayClip() {
		try {
			final Clip clip = HelperSound.getClip(this.getClass().getResourceAsStream("/res/test.wav"));

			HelperSound.play(clip);
//			clip.stop();
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
	public void testGetAndPlaySequence() {
		try {
			final Sequence sequence = HelperSound.getSequence(this.getClass().getResourceAsStream("/res/test.mid"));
			final Sequencer sequencer = HelperSound.getSequencer(sequence);
			
			sequencer.start();
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
    public static void main(String[] args) {
    	new HelperSoundTest().testGetAndPlayClip();
    	new HelperSoundTest().testGetAndPlaySequence();
    }
	//TODO complete
}


