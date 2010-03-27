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
package ch.customcode.bogatyr;

import java.text.DateFormat;
import java.util.Date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import ch.customcode.bogatyr.helper.HelperArrayTest;
import ch.customcode.bogatyr.helper.HelperCollection;
import ch.customcode.bogatyr.helper.HelperCollectionTest;
import ch.customcode.bogatyr.helper.HelperCryptoTest;
import ch.customcode.bogatyr.helper.HelperEnvironmentTest;
import ch.customcode.bogatyr.helper.HelperGraphicTest;
import ch.customcode.bogatyr.helper.HelperIOTest;
import ch.customcode.bogatyr.helper.HelperImageTest;
import ch.customcode.bogatyr.helper.HelperMapTest;
import ch.customcode.bogatyr.helper.HelperMathTest;
import ch.customcode.bogatyr.helper.HelperNetTest;
import ch.customcode.bogatyr.helper.HelperObjectTest;
import ch.customcode.bogatyr.helper.HelperPdfTest;
import ch.customcode.bogatyr.helper.HelperStringTest;
import ch.customcode.bogatyr.helper.HelperTimeTest;
import ch.customcode.bogatyr.helper.HelperXmlTest;
import ch.customcode.bogatyr.helper.encoder.EncoderBase64Test;
import ch.customcode.bogatyr.helper.encoder.EncoderHexTest;
import ch.customcode.bogatyr.helper.launcher.LauncherBrowserTest;
import ch.customcode.bogatyr.helper.launcher.LauncherFileTest;
import ch.customcode.bogatyr.helper.launcher.LauncherMailTest;
import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.model.context.ContextTest;
import ch.customcode.bogatyr.model.unit.UnitAreaTest;
import ch.customcode.bogatyr.model.unit.UnitLengthTest;
import ch.customcode.bogatyr.model.unit.UnitVolumeTest;
import ch.customcode.bogatyr.model.unit.UnitWeightTest;
import ch.customcode.bogatyr.service.crypto.CertificateProviderTest;
import ch.customcode.bogatyr.service.crypto.CryptoAsymmetricTest;
import ch.customcode.bogatyr.service.crypto.CryptoSymmetricTest;
import ch.customcode.bogatyr.service.crypto.ScramblerTest;
import ch.customcode.bogatyr.view.swing.factory.FormatFactory;


/**
 * Junit test suite
 * 
 * @author SiSprocom GmbH, Stefan Laubenberger
 * @version 20100203
 */
public class AllBogatyrTests implements Runnable {
	public static final String DATA    = "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ"; //$NON-NLS-1$

	public static final int ITERATIONS = 1;
	public static final int THREADS	   = 1;

	
    public static void main(final String[] args) {
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
				ContextTest.class,
				CertificateProviderTest.class,
				CryptoAsymmetricTest.class,
				CryptoSymmetricTest.class,
				ScramblerTest.class,
				EncoderBase64Test.class,
				EncoderHexTest.class,
				LauncherBrowserTest.class,
				LauncherFileTest.class,
				LauncherMailTest.class,
//				PaginatorTest.class,
//				PrinterTest.class,
				UnitAreaTest.class,
				UnitLengthTest.class,
				UnitVolumeTest.class,
				UnitWeightTest.class,
				HelperArrayTest.class,
				HelperCollectionTest.class,
				HelperCryptoTest.class,
				HelperEnvironmentTest.class,
				HelperGraphicTest.class,
				HelperImageTest.class,
				HelperIOTest.class,
				HelperMapTest.class,
				HelperMathTest.class,
				HelperNetTest.class,
				HelperObjectTest.class,
				HelperPdfTest.class,
//				HelperPreferencesTest.class,
//				HelperSoundTest.class,
				HelperStringTest.class,
				HelperTimeTest.class,
				HelperXmlTest.class
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
    private static void runThreads (final AllBogatyrTests[] runnables) {
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
