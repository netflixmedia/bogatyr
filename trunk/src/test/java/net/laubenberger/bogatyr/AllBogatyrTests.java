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

package net.laubenberger.bogatyr;

import java.text.DateFormat;
import java.util.Date;

import net.laubenberger.bogatyr.helper.HelperArrayTest;
import net.laubenberger.bogatyr.helper.HelperCollection;
import net.laubenberger.bogatyr.helper.HelperCollectionTest;
import net.laubenberger.bogatyr.helper.HelperCompressTest;
import net.laubenberger.bogatyr.helper.HelperCryptoTest;
import net.laubenberger.bogatyr.helper.HelperEnvironmentTest;
import net.laubenberger.bogatyr.helper.encoder.EncoderBase64Test;
import net.laubenberger.bogatyr.helper.encoder.EncoderHexTest;
import net.laubenberger.bogatyr.helper.encoder.EncoderHtmlTest;
import net.laubenberger.bogatyr.helper.launcher.LauncherBrowserTest;
import net.laubenberger.bogatyr.helper.launcher.LauncherFileTest;
import net.laubenberger.bogatyr.helper.launcher.LauncherMailTest;
import net.laubenberger.bogatyr.helper.launcher.LauncherProcessTest;
import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.view.swing.factory.FormatFactory;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


/**
 * JUnit test suite
 *
 * @author Stefan Laubenberger
 * @version 20101103
 */
public class AllBogatyrTests implements Runnable {
	public static final String DATA = "!$'()*+,-./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~°¢£§•¶ß®©™´¨≠ÆØ∞±≤≥µ∂∑∏π∫ªºΩæø¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷◊ÿŸ⁄€‹›ﬁﬂ‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ˜¯˘˙˚¸˝˛ˇ" + //$NON-NLS-1$
			"!$'()*+,-./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~°¢£§•¶ß®©™´¨≠ÆØ∞±≤≥µ∂∑∏π∫ªºΩæø¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷◊ÿŸ⁄€‹›ﬁﬂ‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ˜¯˘˙˚¸˝˛ˇ" + //$NON-NLS-1$
			"!$'()*+,-./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~°¢£§•¶ß®©™´¨≠ÆØ∞±≤≥µ∂∑∏π∫ªºΩæø¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷◊ÿŸ⁄€‹›ﬁﬂ‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ˜¯˘˙˚¸˝˛ˇ" + //$NON-NLS-1$
			"!$'()*+,-./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~°¢£§•¶ß®©™´¨≠ÆØ∞±≤≥µ∂∑∏π∫ªºΩæø¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷◊ÿŸ⁄€‹›ﬁﬂ‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ˜¯˘˙˚¸˝˛ˇ"; //$NON-NLS-1$

	public static final int ITERATIONS = 1;
	public static final int THREADS = 1;


	public static void main(final String[] args) {
		PropertyConfigurator.configure("src/test/configuration/log4j.properties"); //$NON-NLS-1$

		final DateFormat df = FormatFactory.createDateFormat(FormatFactory.PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_MILLISECOND);

		System.out.println("+---------------------------+"); //$NON-NLS-1$
		System.out.println("|  Start all Bogatyr tests  |"); //$NON-NLS-1$
		System.out.println("|  " + df.format(new Date()) + "  |");  //$NON-NLS-1$//$NON-NLS-2$
		System.out.println("+---------------------------+"); //$NON-NLS-1$
		System.out.println("Information:\t" + Constants.BOGATYR); //$NON-NLS-1$
		System.out.println("Iterations:\t" + ITERATIONS); //$NON-NLS-1$
		System.out.println("Threads:\t" + THREADS); //$NON-NLS-1$

		final AllBogatyrTests[] tests = new AllBogatyrTests[THREADS];
		for (int ii = 0; ii < tests.length; ii++) {
			tests[ii] = new AllBogatyrTests();
		}

		for (int ii = 0; ITERATIONS > ii; ii++) {
			runThreads(tests);
		}

		System.out.println("+-------------------------------+"); //$NON-NLS-1$
		System.out.println("|  All Bogatyr tests completed  |"); //$NON-NLS-1$
		System.out.println("|  " + df.format(new Date()) + "      |"); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println("+-------------------------------+"); //$NON-NLS-1$
	}

	@Override
	public void run() {
		final Result result = JUnitCore.runClasses(
				EncoderBase64Test.class,
				EncoderHexTest.class,
				EncoderHtmlTest.class,
				LauncherBrowserTest.class,
				LauncherFileTest.class,
				LauncherMailTest.class,
				LauncherProcessTest.class,
				HelperArrayTest.class,
				HelperCollectionTest.class,
				HelperCompressTest.class,
				HelperCryptoTest.class,
				HelperEnvironmentTest.class
//				HelperGraphicTest.class,
//				HelperImageTest.class,
//				HelperIOTest.class,
//				HelperMapTest.class,
//				HelperMathTest.class,
//				HelperNetTest.class,
//				HelperObjectTest.class,
//				HelperPdfTest.class,
//				HelperSoundTest.class,
//				HelperStringTest.class,
//				HelperTimeTest.class,
//				HelperXmlTest.class,
//				ContextTest.class,
//				CertificateProviderTest.class,
//				CryptoAsymmetricTest.class,
//				CryptoSymmetricTest.class,
//				ScramblerTest.class,
////				PaginatorTest.class,
////				PrinterTest.class,
//				UnitAreaTest.class,
//				UnitLengthTest.class,
//				UnitVolumeTest.class,
//				UnitWeightTest.class
		);

		System.out.println("** Test run completed **"); //$NON-NLS-1$
		System.out.println("Time:\t" + result.getRunTime()); //$NON-NLS-1$
		System.out.println("Total:\t" + result.getRunCount()); //$NON-NLS-1$
		System.out.println("Failed:\t" + result.getFailureCount()); //$NON-NLS-1$
		System.out.println(HelperCollection.dump(result.getFailures()));
	}


	/*
	 * Private methods
	 */

	private static void runThreads(final AllBogatyrTests[] runnables) {
		if (null == runnables) {
			throw new IllegalArgumentException("runnables == null"); //$NON-NLS-1$
		}
		Thread[] threads = new Thread[runnables.length];

		for (int ii = 0; ii < threads.length; ii++) {
			threads[ii] = new Thread(runnables[ii]);
		}

		for (final Thread thread : threads) {
			thread.start();
		}

		try {
			for (final Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException ignore) {
			System.out.println("Thread join interrupted."); //$NON-NLS-1$
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		threads = null;
	}
}