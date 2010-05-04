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

package net.laubenberger.bogatyr.controller;

import java.lang.Thread.UncaughtExceptionHandler;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.extendedObject.ExtendedObjectAbstract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is the skeleton for all Bogatyr based applications.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.1.0
 */
public abstract class ApplicationAbstract extends ExtendedObjectAbstract implements Application {
	static final Logger log = LoggerFactory.getLogger(ApplicationAbstract.class);
	
	static {
		Thread.setDefaultUncaughtExceptionHandler(new Handler());
	}

	{
		log.info(HelperLog.applicationStart());
	}
	
	public void exit(final int returnCode) {
		if (0 == returnCode) {
			log.info(HelperLog.applicationExit(returnCode));
		} else {
			log.warn(HelperLog.applicationExit(returnCode));
		}
		System.exit(returnCode);
	}
	
	static final class Handler implements UncaughtExceptionHandler {
		public void uncaughtException(Thread t, Throwable ex) {
			log.error("uncaught exception occured", ex);
//			log.warn(HelperLog.applicationExit(255));
//			System.exit(255);
		}
	}
}
