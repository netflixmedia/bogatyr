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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



/**
 * This is a helper class for sound operations
 * 
 * @author Stefan Laubenberger
 * @version 20080728
 */
public abstract class HelperSound {

    /**
     * Returns an audio clip from file (e.g. WAV files)
     *
     * @param file for audio clip
     * @return Audio clip
     * @throws java.io.IOException
     * @throws javax.sound.sampled.LineUnavailableException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     */
    public static Clip getClip(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		return getClip(AudioSystem.getAudioInputStream(file));
	}

    /**
     * Returns an audio clip from stream
     *
     * @param in stream for audio clip
     * @return Audio clip
     * @throws java.io.IOException
     * @throws javax.sound.sampled.LineUnavailableException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     */
	public static Clip getClip(final InputStream in) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		return getClip(AudioSystem.getAudioInputStream(in));
	}

    /**
     * Returns a sequence from file (e.g. MIDI files)
     *
     * @param file for sequence
     * @return Sequence
     * @throws java.io.IOException
     * @throws javax.sound.midi.InvalidMidiDataException
     */
	public static Sequence getSequence(final File file) throws InvalidMidiDataException, IOException {
		return MidiSystem.getSequence(file);
	}

    /**
     * Returns a sequence from stream
     *
     * @param in stream for sequence
     * @return Sequence
     * @throws java.io.IOException
     * @throws javax.sound.midi.InvalidMidiDataException
     */
	public static Sequence getSequence(final InputStream in) throws InvalidMidiDataException, IOException {
		return MidiSystem.getSequence(in);
	}
	
    /**
     * Returns a sequencer to play a sequence
     *
     * @param sequence for sequencer
     * @return Sequencer
     * @throws javax.sound.midi.MidiUnavailableException
     * @throws javax.sound.midi.InvalidMidiDataException
     */
	public static Sequencer getSequencer(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
		Sequencer sequencer = MidiSystem.getSequencer();  // Used to play sequences
        sequencer.open();                       // Turn it on.

        // Get a Synthesizer for the Sequencer to send notes to
        Synthesizer synth = MidiSystem.getSynthesizer( );
        synth.open();  // acquire whatever resources it needs
        
        // The Sequencer obtained above may be connected to a Synthesizer
        // by default, or it may not.  Therefore, we explicitly connect it.
        Transmitter transmitter = sequencer.getTransmitter( );
        Receiver receiver = synth.getReceiver( );
        transmitter.setReceiver(receiver);
        
        // Read the sequence from the file and tell the sequencer about it
        sequencer.setSequence(sequence);
        
        return sequencer;
	}
	
    /**
     * Plays an audio clip (if no player thread is available)
     *
     * @param clip to play
     */
	public static void play(final Clip clip) {
		clip.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				clip.stop();
			}
		}, (int)(clip.getMicrosecondLength()/1000));
	}
	
	/*
	 * Private methods
	 */
	private static Clip getClip(final AudioInputStream ain) throws LineUnavailableException, IOException {
		Clip clip;

        try {
            DataLine.Info info = new DataLine.Info(Clip.class, ain.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ain);
        }
        finally {
            ain.close( );
        }
        return clip;
	}
}
