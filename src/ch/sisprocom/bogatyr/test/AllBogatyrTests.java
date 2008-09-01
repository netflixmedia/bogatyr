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
package ch.sisprocom.bogatyr.test;

import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import ch.sisprocom.bogatyr.test.helper.HelperEnvInfoTest;
import ch.sisprocom.bogatyr.test.helper.HelperFileTest;
import ch.sisprocom.bogatyr.test.helper.HelperGeneralTest;
import ch.sisprocom.bogatyr.test.helper.HelperGraphicTest;
import ch.sisprocom.bogatyr.test.helper.HelperImageTest;
import ch.sisprocom.bogatyr.test.helper.HelperMathTest;
import ch.sisprocom.bogatyr.test.helper.HelperNetTest;
import ch.sisprocom.bogatyr.test.helper.HelperPdfTest;
import ch.sisprocom.bogatyr.test.helper.HelperXmlTest;
import ch.sisprocom.bogatyr.test.helper.context.ContextTest;
import ch.sisprocom.bogatyr.test.helper.converter.ConverterBase64Test;
import ch.sisprocom.bogatyr.test.helper.converter.ConverterHexTest;
import ch.sisprocom.bogatyr.test.helper.crypto.CryptoAsymmTest;
import ch.sisprocom.bogatyr.test.helper.crypto.CryptoSymmTest;
import ch.sisprocom.bogatyr.test.helper.crypto.ObfuscatorTest;
import ch.sisprocom.bogatyr.test.helper.crypto.PublicKeyProviderTest;
import ch.sisprocom.bogatyr.test.helper.localizer.LocalizerTest;


/**
 * Junit test suite
 * 
 * @author Stefan Laubenberger
 * @version 20080802
 */
public class AllBogatyrTests extends TestSuite implements Runnable {
	public static final String DATA    = "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
										 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
										 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ" + //$NON-NLS-1$
										 "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz~€‚ƒ„…†‡ˆ‰Š‹Œ‘’“”•–—˜™š›œ Ÿ¡¢£¤¥¦§¨©ª«¬­®¯°±²³µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ"; //$NON-NLS-1$
	public static final int ITERATIONS = 1;
	public static final int THREADS	   = 1;
	
	
	public static Test suite() {
		final TestSuite testSuite = new TestSuite("Bogatyr Test-Suite"); //$NON-NLS-1$
    
		// Add test cases
		testSuite.addTestSuite(ContextTest.class);
//		testSuite.addTestSuite(ControlBrowserTest.class);
		testSuite.addTestSuite(ConverterBase64Test.class);
		testSuite.addTestSuite(ConverterHexTest.class);
		testSuite.addTestSuite(CryptoAsymmTest.class);
		testSuite.addTestSuite(CryptoSymmTest.class);
		testSuite.addTestSuite(ObfuscatorTest.class);
		testSuite.addTestSuite(PublicKeyProviderTest.class);
		testSuite.addTestSuite(LocalizerTest.class);		
		testSuite.addTestSuite(HelperEnvInfoTest.class);
		testSuite.addTestSuite(HelperFileTest.class);
		testSuite.addTestSuite(HelperGeneralTest.class);
		testSuite.addTestSuite(HelperGraphicTest.class);
		testSuite.addTestSuite(HelperImageTest.class);
		testSuite.addTestSuite(HelperMathTest.class);
		testSuite.addTestSuite(HelperNetTest.class);
		testSuite.addTestSuite(HelperPdfTest.class);
//		testSuite.addTestSuite(HelperPreferencesTest.class);
		testSuite.addTestSuite(HelperXmlTest.class);
//		testSuite.addTestSuite(PaginatorTest.class);
//		testSuite.addTestSuite(PrinterTest.class);
//		testSuite.addTestSuite(HelperSoundTest.class);
		
		return new RepeatedTest(testSuite, ITERATIONS);
	}
  
    public static void main(final String[] args) {
    	System.out.println("*** Start all Bogatyr tests ***"); //$NON-NLS-1$
    	final AllBogatyrTests[] tests = new AllBogatyrTests[THREADS];
        for (int ii = 0; ii < tests.length; ii++) {
        	tests[ii] = new AllBogatyrTests ();
        }

        runThreads(tests);
        
        System.out.println("*** All tests completed ***"); //$NON-NLS-1$
    }
    
	public void run() {
		try {
			(new TestRunner()).start(new String[]{"ch.sisprocom.bogatyr.test.AllBogatyrTests"}); //$NON-NLS-1$
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
           System.out.println("Thread join interrupted.");
        }
//        threads = null;
     }
}