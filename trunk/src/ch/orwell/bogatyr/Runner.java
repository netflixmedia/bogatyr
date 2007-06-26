package ch.orwell.bogatyr;

import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * This starts starts every Application
 *
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070623
 */
public class Runner {
	
	/**
	 * The only main() method to run every application.
	 * @param args The arguments given to start an application.
	 */
	
	public static void main(String args[]) {
		if (args.length == 2) { // checks the number of arguments - <Application> and <standard.properties> are needed
			try {
				GeneralHelper.createObject(args[0], new String[]{args[1]});
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(666);
			}
		} else {
			System.err.println("Error: wrong number of arguments!"); //$NON-NLS-1$
			System.err.println("usage: Runner <Application> <standard.properties>"); //$NON-NLS-1$
			System.exit(1);
		}
	}
}
