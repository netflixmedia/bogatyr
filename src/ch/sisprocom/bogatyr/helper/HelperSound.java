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
package ch.sisprocom.bogatyr.helper;

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
import javax.sound.sampled.*;

import ch.sisprocom.bogatyr.helper.logger.Logger;



/**
 * This is a helper class for sound operations
 * 
 * @author Stefan Laubenberger
 * @version 20080810
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
    public static Clip getClip(final File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Logger.getInstance().writeMethodEntry(HelperSound.class, "getClip", file);  //$NON-NLS-1$

		final Clip result = getClip(AudioSystem.getAudioInputStream(file));
		
		Logger.getInstance().writeMethodExit(HelperSound.class, "getClip", result);  //$NON-NLS-1$
    	return result;
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
		Logger.getInstance().writeMethodEntry(HelperSound.class, "getClip", in);  //$NON-NLS-1$

		final Clip result = getClip(AudioSystem.getAudioInputStream(in));
		
		Logger.getInstance().writeMethodExit(HelperSound.class, "getClip", result);  //$NON-NLS-1$
    	return result;
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
		Logger.getInstance().writeMethodEntry(HelperSound.class, "getSequence", file);  //$NON-NLS-1$

		final Sequence result = MidiSystem.getSequence(file);
		
		Logger.getInstance().writeMethodExit(HelperSound.class, "getSequence", result);  //$NON-NLS-1$
    	return result;
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
		Logger.getInstance().writeMethodEntry(HelperSound.class, "getSequence", in);  //$NON-NLS-1$

		final Sequence result = MidiSystem.getSequence(in);
		
		Logger.getInstance().writeMethodExit(HelperSound.class, "getSequence", result);  //$NON-NLS-1$
    	return result;
	}
	
    /**
     * Returns a sequencer to play a sequence
     *
     * @param sequence for sequencer
     * @return Sequencer
     * @throws javax.sound.midi.MidiUnavailableException
     * @throws javax.sound.midi.InvalidMidiDataException
     */
	public static Sequencer getSequencer(final Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
		Logger.getInstance().writeMethodEntry(HelperSound.class, "getSequencer", sequence);  //$NON-NLS-1$

		final Sequencer sequencer = MidiSystem.getSequencer();  // Used to play sequences
        sequencer.open();                       // Turn it on.

        // Get a Synthesizer for the Sequencer to send notes to
        final Synthesizer synth = MidiSystem.getSynthesizer( );
        synth.open();  // acquire whatever resources it needs
        
        // The Sequencer obtained above may be connected to a Synthesizer
        // by default, or it may not.  Therefore, we explicitly connect it.
        final Transmitter transmitter = sequencer.getTransmitter( );
        final Receiver receiver = synth.getReceiver( );
        transmitter.setReceiver(receiver);
        
        // Read the sequence from the file and tell the sequencer about it
        sequencer.setSequence(sequence);
        
		Logger.getInstance().writeMethodExit(HelperSound.class, "getSequencer", sequencer);  //$NON-NLS-1$
        return sequencer;
	}
	
    /**
     * Plays a whole audio clip (if no player thread is available)
     *
     * @param clip to play
     */
	public static void play(final Clip clip) {
		Logger.getInstance().writeMethodEntry(HelperSound.class, "play", clip);  //$NON-NLS-1$

		clip.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				clip.stop();
			}
		}, (int)(clip.getMicrosecondLength()/1000));

		Logger.getInstance().writeMethodExit(HelperSound.class, "play");  //$NON-NLS-1$
	}

	/**
     * Plays a whole sequence (if no player thread is available)
     *
     * @param sequence to play
	 * @throws InvalidMidiDataException 
	 * @throws MidiUnavailableException 
     */
	public static void play(final Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
		Logger.getInstance().writeMethodEntry(HelperSound.class, "play", sequence);  //$NON-NLS-1$

		final Sequencer sequencer = getSequencer(sequence);
		sequencer.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				sequencer.stop();
			}
		}, (int)sequence.getTickLength());

		Logger.getInstance().writeMethodExit(HelperSound.class, "play");  //$NON-NLS-1$
	}
	
	/*
	 * Private methods
	 */
	private static Clip getClip(final AudioInputStream ain) throws LineUnavailableException, IOException {
		Logger.getInstance().writeMethodEntry(HelperSound.class, "getClip", ain);  //$NON-NLS-1$

		Clip clip;

        try {
            final Line.Info info = new DataLine.Info(Clip.class, ain.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ain);
        }
        finally {
            ain.close( );
        }

        Logger.getInstance().writeMethodExit(HelperSound.class, "getClip", clip);  //$NON-NLS-1$
        return clip;
	}
}
