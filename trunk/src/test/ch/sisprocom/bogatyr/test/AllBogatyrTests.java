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
package ch.sisprocom.bogatyr.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.test.helper.HelperEnvInfoTest;
import ch.sisprocom.bogatyr.test.helper.HelperGeneralTest;
import ch.sisprocom.bogatyr.test.helper.HelperGraphicTest;
import ch.sisprocom.bogatyr.test.helper.HelperIOTest;
import ch.sisprocom.bogatyr.test.helper.HelperImageTest;
import ch.sisprocom.bogatyr.test.helper.HelperMathTest;
import ch.sisprocom.bogatyr.test.helper.HelperPdfTest;
import ch.sisprocom.bogatyr.test.helper.HelperXmlTest;
import ch.sisprocom.bogatyr.test.helper.context.ContextTest;
import ch.sisprocom.bogatyr.test.helper.control.ControlBrowserTest;
import ch.sisprocom.bogatyr.test.helper.converter.ConverterBase64Test;
import ch.sisprocom.bogatyr.test.helper.converter.ConverterHexTest;
import ch.sisprocom.bogatyr.test.helper.crypto.CryptoAsymmTest;
import ch.sisprocom.bogatyr.test.helper.crypto.CryptoSymmTest;
import ch.sisprocom.bogatyr.test.helper.crypto.ObfuscatorTest;
import ch.sisprocom.bogatyr.test.helper.crypto.PublicKeyProviderTest;



/**
 * Junit test suite
 * 
 * @author SiSprocom GmbH, Stefan Laubenberger
 * @version 20081112
 */
public class AllBogatyrTests implements Runnable {
	public static final String DATA    = "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
	 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ"; //$NON-NLS-1$

	public static final int ITERATIONS = 1;
	public static final int THREADS	   = 1;

	private static final DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$

	
    public static void main(final String[] args) {
    	System.out.println("+---------------------------+");
    	System.out.println("|  Start all Bogatyr tests  |");
    	System.out.println("|  " + formatter.format(new Date()) + "    |");
    	System.out.println("+---------------------------+");
    	System.out.println("Iterations:\t" + ITERATIONS);
    	System.out.println("Threads:\t" + THREADS);
    	
    	final AllBogatyrTests[] tests = new AllBogatyrTests[THREADS];
        for (int ii = 0; ii < tests.length; ii++) {
        	tests[ii] = new AllBogatyrTests();
        }

        for (int ii = 0; ITERATIONS > ii; ii++) {
            runThreads(tests);
        }
        
        System.out.println("+-------------------------------+");
        System.out.println("|  All Bogatyr tests completed  |");
    	System.out.println("|  " + formatter.format(new Date()) + "        |");
        System.out.println("+-------------------------------+");
    }
    
	public void run() {
		final Result result = JUnitCore.runClasses(
				ContextTest.class,
				ControlBrowserTest.class,
				ConverterBase64Test.class,
				ConverterHexTest.class,
				CryptoAsymmTest.class,
				CryptoSymmTest.class,
				ObfuscatorTest.class,
				PublicKeyProviderTest.class,
				HelperEnvInfoTest.class,
				HelperIOTest.class,
				HelperGeneralTest.class,
				HelperGraphicTest.class,
				HelperImageTest.class,
				HelperMathTest.class,
//				HelperNetTest.class,
				HelperPdfTest.class,
//				HelperPreferencesTest.class,
				HelperXmlTest.class
//				PaginatorTest.class,
//				PrinterTest.class,
//				HelperSoundTest.class
		);
		
        System.out.println("** Test run completed **");
        System.out.println("Time:\t" + result.getRunTime());
        System.out.println("Total:\t" + result.getRunCount());
        System.out.println("Failed:\t" + result.getFailureCount());
        System.out.println(HelperGeneral.dump(result.getFailures()));
	}
	
	
	/*
	 * Private methods
	 */
    private static void runThreads (final AllBogatyrTests[] runnables) {
        if (runnables == null) {
           throw new IllegalArgumentException("runnables == null");
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
           System.out.println("Thread join interrupted.");
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        threads = null;
     }
}
