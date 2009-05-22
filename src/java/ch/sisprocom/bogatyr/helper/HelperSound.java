/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This is a helper class for sound operations.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class HelperSound {

    /**
     * Returns an audio {@link Clip} from a {@link File} (e.g. "wav").
     *
     * @param file for audio clip
     * @return Audio clip
     * @throws IOException
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     */
    public static Clip getClip(final File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		
		return getClip(AudioSystem.getAudioInputStream(file));
	}

    /**
     * Returns an audio {@link Clip} from a {@link InputStream}.
     *
     * @param is stream for audio clip
     * @return Audio clip
     * @throws IOException
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     */
	public static Clip getClip(final InputStream is) throws UnsupportedAudioFileException, LineUnavailableException, IOException { //$JUnit
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		
		return getClip(AudioSystem.getAudioInputStream(is));
	}

    /**
     * Returns a {@link Sequence} from a {@link File} (e.g. "mid").
     *
     * @param file for sequence
     * @return Audio sequence
     * @throws IOException
     * @throws InvalidMidiDataException
     */
	public static Sequence getSequence(final File file) throws InvalidMidiDataException, IOException {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		
		return MidiSystem.getSequence(file);
	}

    /**
     * Returns a {@link Sequence} from a {@link InputStream}.
     *
     * @param is stream for sequence
     * @return Audio sequence
     * @throws IOException
     * @throws InvalidMidiDataException
     */
	public static Sequence getSequence(final InputStream is) throws InvalidMidiDataException, IOException { //$JUnit
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		
		return MidiSystem.getSequence(is);
	}
	
    /**
     * Returns a {@link Sequencer} to play a {@link Sequence}.
     *
     * @param sequence for sequencer
     * @return MIDI sequencer
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     */
	public static Sequencer getSequencer(final Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException { //$JUnit
		if (null == sequence) {
			throw new IllegalArgumentException("sequence is null!"); //$NON-NLS-1$
		}
		
		final Sequencer sequencer = MidiSystem.getSequencer();  // Used to play sequences
        sequencer.open(); // Turn it on.

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
        return sequencer;
	}
	
    /**
     * Plays a whole audio {@link Clip} (if no player thread is available).
     *
     * @param clip to play
     */
	public static void play(final Clip clip) { //$JUnit
		if (null == clip) {
			throw new IllegalArgumentException("clip is null!"); //$NON-NLS-1$
		}
		
		clip.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				clip.stop();
			}
		}, clip.getMicrosecondLength()/1000L);
	}

	/**
     * Plays a whole {@link Sequence} (if no player thread is available).
     *
     * @param sequence to play
	 * @throws InvalidMidiDataException 
	 * @throws MidiUnavailableException 
     */
	public static void play(final Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException { //$JUnit
		if (null == sequence) {
			throw new IllegalArgumentException("sequence is null!"); //$NON-NLS-1$
		}
		
		final Sequencer sequencer = getSequencer(sequence);
		sequencer.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				sequencer.stop();
			}
		}, sequence.getTickLength());
	}
	
    /**
     * Returns all available audio formats of the current machine (e.g. "aiff", "wave").
     *
     * @return List of all available audio formats of the current machine
     */
	public static Collection<Type> getAvailableAudioFormats() { //$JUnit
    	return Arrays.asList(AudioSystem.getAudioFileTypes());
	}

	
	/*
	 * Private methods
	 */
	private static Clip getClip(final AudioInputStream ais) throws LineUnavailableException, IOException {
		Clip clip;

        try {
            final Line.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
        }
        finally {
            ais.close( );
        }
        return clip;
	}
}