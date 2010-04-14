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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package ch.customcode.bogatyr.service.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.customcode.bogatyr.AllBogatyrTests;
import ch.customcode.bogatyr.helper.HelperArray;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091116
 */
public class ScramblerTest {
	@Test
	public void testScrambleAndUnscramble() {
		final Scrambler scrambler = new ScramblerImpl();
		assertEquals(AllBogatyrTests.DATA, new String(scrambler.unscramble(scrambler.scramble(AllBogatyrTests.DATA.getBytes(), (byte)23), (byte)23)));
		assertEquals(AllBogatyrTests.DATA, new String(scrambler.unscramble(scrambler.scramble(AllBogatyrTests.DATA.getBytes(), (byte)0x6F), (byte)0x6F)));
		assertNotSame(AllBogatyrTests.DATA, new String(scrambler.unscramble(scrambler.scramble(AllBogatyrTests.DATA.getBytes(), (byte)0x6F), (byte)0x5F)));
		
		try {
			scrambler.scramble(null, (byte)23);
			fail("byte[] is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			scrambler.scramble(HelperArray.EMPTY_ARRAY_BYTE, (byte)23);
			fail("byte[] is empty!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			scrambler.unscramble(null, (byte)23);
			fail("byte[] is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			scrambler.unscramble(HelperArray.EMPTY_ARRAY_BYTE, (byte)23);
			fail("byte[] is empty!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
//	@Test
//	public void testScrambleFile() {
//	
//		final String id_scrambling = "SCR";
//		final String id_unscrambling = "UCR";
//		final String id_copy = "CPY";
//		
//		final Scrambler scrambler = new ScramblerImpl();
//		final File input = new File("/Users/Shared/Transfer/movies/test.txt");
//		final File output = new File("/Users/Shared/Transfer/movies/test.scrambled.txt");
//		final File output2 = new File("/Users/Shared/Transfer/movies/test.unscrambled.txt");
//		final File output3 = new File("/Users/Shared/Transfer/movies/test.copy.txt");
////		final File input = new File("/Users/Shared/Transfer/movies/Infernal Affairs.mpg");
////		final File output = new File("/Users/Shared/Transfer/movies/Infernal Affairs.scrambled.mpg");
////		final File output2 = new File("/Users/Shared/Transfer/movies/Infernal Affairs.unscrambled.mpg");
////		final File output3 = new File("/Users/Shared/Transfer/movies/Infernal Affairs.copy.mpg");
//		final Profiler profiler = new ProfilerImpl();
//		
//		
//		try {
//			profiler.start();
//			HelperIO.copyFile(input, output3);
//			profiler.profile(id_copy);
//			scrambler.scramble(input, output, (byte)23);
//			profiler.profile(id_scrambling);
//			scrambler.unscramble(output, output2, (byte)23);
//			profiler.profile(id_unscrambling);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(HelperMap.dump(profiler.getProfiles()));
//		
//		System.out.println(UnitTime.convert(Time.NANOSECOND, Time.SECOND, profiler.getProfiles().get(id_copy)));
//		System.out.println(UnitTime.convert(Time.NANOSECOND, Time.SECOND, profiler.getProfiles().get(id_scrambling)));
//		System.out.println(UnitTime.convert(Time.NANOSECOND, Time.SECOND, profiler.getProfiles().get(id_unscrambling)));
//		
//	}
}
