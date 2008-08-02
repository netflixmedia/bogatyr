package ch.orwell.helloworld;

import java.io.IOException;

import ch.orwell.bogatyr.controller.ApplicationTemplate;
import ch.orwell.bogatyr.helper.localizer.Localizer;
import ch.orwell.bogatyr.helper.logger.Logger;

/**
 * Simple HelloWorld example using the Bogatyr framework
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080730
 */
public class HelloWorld extends ApplicationTemplate {
	// Resources
	private final static String	RES_TEXT  = "HelloWorld.text"; //$NON-NLS-1$

	
	public HelloWorld(String propertiesFileName) throws IOException {
		super(propertiesFileName);
		run();
	}

	public void run() {
		String text = Localizer.getInstance().getValue(RES_TEXT);
		
		System.out.println(text);
		
		Logger.getInstance().writeLog(this, "run", "## " + text + " ##"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		exit(0);
	}
}
