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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
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
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFileFormat.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * This is a helper class for sound operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.5.0
 */
public abstract class HelperSound {
	private static final Logger log = LoggerFactory.getLogger(HelperSound.class);

	/**
	 * Returns an audio {@link Clip} from a {@link File} (e.g. "wav").
	 *
	 * @param file for audio clip
	 * @return Audio {@link Clip}
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @see File
	 * @see Clip
	 * @since 0.5.0
	 */
	public static Clip getClip(final File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		log.debug(HelperLog.methodStart(file));
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		final Clip result = getClip(AudioSystem.getAudioInputStream(file));

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns an audio {@link Clip} from a {@link InputStream}.
	 *
	 * @param is {@link InputStream} for audio clip
	 * @return Audio {@link Clip}
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @see InputStream
	 * @see Clip
	 * @since 0.5.0
	 */
	public static Clip getClip(final InputStream is) throws UnsupportedAudioFileException, LineUnavailableException, IOException { //$JUnit$
		log.debug(HelperLog.methodStart(is));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}

		final Clip result = getClip(AudioSystem.getAudioInputStream(is));

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Sequence} from a {@link File} (e.g. "mid").
	 *
	 * @param file for sequence
	 * @return Audio {@link Sequence}
	 * @throws IOException
	 * @throws InvalidMidiDataException
	 * @see File
	 * @see Sequence
	 * @since 0.5.0
	 */
	public static Sequence getSequence(final File file) throws InvalidMidiDataException, IOException {
		log.debug(HelperLog.methodStart(file));
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		final Sequence result = MidiSystem.getSequence(file);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Sequence} from a {@link InputStream}.
	 *
	 * @param is {@link InputStream} for sequence
	 * @return Audio {@link Sequence}
	 * @throws IOException
	 * @throws InvalidMidiDataException
	 * @see InputStream
	 * @see Sequence
	 * @since 0.5.0
	 */
	public static Sequence getSequence(final InputStream is) throws InvalidMidiDataException, IOException { //$JUnit$
		log.debug(HelperLog.methodStart(is));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}

		final Sequence result = MidiSystem.getSequence(is);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Sequencer} to play a {@link Sequence}.
	 *
	 * @param sequence for {@link Sequencer}
	 * @return MIDI {@link Sequencer}
	 * @throws MidiUnavailableException
	 * @throws InvalidMidiDataException
	 * @see Sequence
	 * @see Sequencer
	 * @since 0.5.0
	 */
	public static Sequencer getSequencer(final Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException { //$JUnit$
		log.debug(HelperLog.methodStart(sequence));
		if (null == sequence) {
			throw new RuntimeExceptionIsNull("sequence"); //$NON-NLS-1$
		}

		final Sequencer result = MidiSystem.getSequencer();  // Used to play sequences
		result.open(); // Turn it on.

		// Get a Synthesizer for the Sequencer to send notes to
		final Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();  // acquire whatever resources it needs

		// The Sequencer obtained above may be connected to a Synthesizer
		// by default, or it may not.  Therefore, we explicitly connect it.
		final Transmitter transmitter = result.getTransmitter();
		final Receiver receiver = synth.getReceiver();
		transmitter.setReceiver(receiver);

		// Read the sequence from the file and tell the sequencer about it
		result.setSequence(sequence);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Plays a whole audio {@link Clip} (if no player thread is available).
	 *
	 * @param clip to play
	 * @see Clip
	 * @since 0.5.0
	 */
	public static void play(final Clip clip) { //$JUnit$
		log.debug(HelperLog.methodStart(clip));
		if (null == clip) {
			throw new RuntimeExceptionIsNull("clip"); //$NON-NLS-1$
		}

		clip.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				clip.stop();
			}
		}, clip.getMicrosecondLength() / HelperNumber.NUMBER_1000.longValue());
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Plays a whole {@link Sequence} (if no player thread is available).
	 *
	 * @param sequence to play
	 * @throws InvalidMidiDataException
	 * @throws MidiUnavailableException
	 * @see Sequence
	 * @since 0.5.0
	 */
	public static void play(final Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException { //$JUnit$
		log.debug(HelperLog.methodStart(sequence));
		if (null == sequence) {
			throw new RuntimeExceptionIsNull("sequence"); //$NON-NLS-1$
		}

		final Sequencer sequencer = getSequencer(sequence);
		sequencer.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				sequencer.stop();
			}
		}, sequence.getTickLength());
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Returns all available audio formats of the current machine (e.g. "aiff", "wave").
	 *
	 * @return {@link Collection} containing all available audio {@link Type} of the current machine
	 * @see Type
	 * @since 0.5.0
	 */
	public static Collection<Type> getAvailableAudioFormats() { //$JUnit$
		log.debug(HelperLog.methodStart());

		final Collection<Type> result = Arrays.asList(AudioSystem.getAudioFileTypes());

		log.debug(HelperLog.methodExit(result));
		return result;
	}


	/*
	 * Private methods
	 */

	private static Clip getClip(final AudioInputStream ais) throws LineUnavailableException, IOException {
		log.trace(HelperLog.methodStart(ais));

		try {
			final Line.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			final Clip result = (Clip) AudioSystem.getLine(info);
			result.open(ais);

			log.trace(HelperLog.methodExit(result));
			return result;
		}
		finally {
			ais.close();
		}
	}
}
