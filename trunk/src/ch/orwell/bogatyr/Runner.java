/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr;

import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * This starts starts every Application
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public class Runner {
	
	/**
	 * The only main() method to run every application.
	 * 
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
