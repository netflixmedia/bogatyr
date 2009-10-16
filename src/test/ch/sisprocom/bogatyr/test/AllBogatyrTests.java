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
package ch.sisprocom.bogatyr.test;

import ch.sisprocom.bogatyr.helper.HelperCollection;
import ch.sisprocom.bogatyr.test.helper.HelperArrayTest;
import ch.sisprocom.bogatyr.test.helper.HelperCollectionTest;
import ch.sisprocom.bogatyr.test.helper.HelperCryptoTest;
import ch.sisprocom.bogatyr.test.helper.HelperEnvironmentTest;
import ch.sisprocom.bogatyr.test.helper.HelperGraphicTest;
import ch.sisprocom.bogatyr.test.helper.HelperIOTest;
import ch.sisprocom.bogatyr.test.helper.HelperImageTest;
import ch.sisprocom.bogatyr.test.helper.HelperMathTest;
import ch.sisprocom.bogatyr.test.helper.HelperNetTest;
import ch.sisprocom.bogatyr.test.helper.HelperObjectTest;
import ch.sisprocom.bogatyr.test.helper.HelperPdfTest;
import ch.sisprocom.bogatyr.test.helper.HelperStringTest;
import ch.sisprocom.bogatyr.test.helper.HelperTimeTest;
import ch.sisprocom.bogatyr.test.helper.HelperXmlTest;
import ch.sisprocom.bogatyr.test.helper.context.ContextTest;
import ch.sisprocom.bogatyr.test.helper.crypto.CryptoAsymmetricTest;
import ch.sisprocom.bogatyr.test.helper.crypto.CryptoSymmetricTest;
import ch.sisprocom.bogatyr.test.helper.crypto.ObfuscatorTest;
import ch.sisprocom.bogatyr.test.helper.crypto.ProviderCertificateTest;
import ch.sisprocom.bogatyr.test.helper.encoder.EncoderBase64Test;
import ch.sisprocom.bogatyr.test.helper.encoder.EncoderHexTest;
import ch.sisprocom.bogatyr.test.helper.launcher.LauncherBrowserTest;
import ch.sisprocom.bogatyr.test.helper.launcher.LauncherFileTest;
import ch.sisprocom.bogatyr.test.helper.launcher.LauncherMailTest;
import ch.sisprocom.bogatyr.test.helper.unit.UnitAreaTest;
import ch.sisprocom.bogatyr.test.helper.unit.UnitLengthTest;
import ch.sisprocom.bogatyr.test.helper.unit.UnitVolumeTest;
import ch.sisprocom.bogatyr.test.helper.unit.UnitWeightTest;
import ch.sisprocom.bogatyr.test.view.swing.NumberFieldTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Junit test suite
 * 
 * @author SiSprocom GmbH, Stefan Laubenberger
 * @version 20091016
 */
public class AllBogatyrTests implements Runnable {
	public static final String DATA    = "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ"; //$NON-NLS-1$

	public static final int ITERATIONS = 1;
	public static final int THREADS	   = 1;

	private static final DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$

	
    public static void main(final String[] args) {
    	System.out.println("+---------------------------+"); //$NON-NLS-1$
    	System.out.println("|  Start all Bogatyr tests  |"); //$NON-NLS-1$
    	System.out.println("|  " + formatter.format(new Date()) + "    |");  //$NON-NLS-1$//$NON-NLS-2$
    	System.out.println("+---------------------------+"); //$NON-NLS-1$
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
    	System.out.println("|  " + formatter.format(new Date()) + "        |"); //$NON-NLS-1$ //$NON-NLS-2$
        System.out.println("+-------------------------------+"); //$NON-NLS-1$
    }
    
	@Override
    public void run() {
		final Result result = JUnitCore.runClasses(
				ContextTest.class,
				CryptoAsymmetricTest.class,
				CryptoSymmetricTest.class,
				ObfuscatorTest.class,
				ProviderCertificateTest.class,
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
				HelperMathTest.class,
				HelperNetTest.class,
				HelperObjectTest.class,
				HelperPdfTest.class,
//				HelperPreferencesTest.class,
//				HelperSoundTest.class,
				HelperStringTest.class,
				HelperTimeTest.class,
				HelperXmlTest.class,
				NumberFieldTest.class
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
        if (runnables == null) {
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
