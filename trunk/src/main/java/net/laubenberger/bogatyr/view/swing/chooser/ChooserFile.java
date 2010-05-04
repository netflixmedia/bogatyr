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

package net.laubenberger.bogatyr.view.swing.chooser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JFileChooser.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.7.0
 */
public class ChooserFile extends JFileChooser {
	private static final long serialVersionUID = -4453036459563578035L;

	private static final Logger log = LoggerFactory.getLogger(ChooserFile.class);

	/*
		 * Superclass constructors
		 */

	public ChooserFile() {
		super();
		log.trace(HelperLog.constructor());
	}

	public ChooserFile(final File currentDirectory, final FileSystemView fsv) {
		super(currentDirectory, fsv);
		log.trace(HelperLog.constructor(currentDirectory, fsv));
	}

	public ChooserFile(final File currentDirectory) {
		super(currentDirectory);
		log.trace(HelperLog.constructor(currentDirectory));
	}

	public ChooserFile(final FileSystemView fsv) {
		super(fsv);
		log.trace(HelperLog.constructor(fsv));
	}

	public ChooserFile(final String currentDirectoryPath, final FileSystemView fsv) {
		super(currentDirectoryPath, fsv);
		log.trace(HelperLog.constructor(currentDirectoryPath, fsv));
	}

	public ChooserFile(final String currentDirectoryPath) {
		super(currentDirectoryPath);
		log.trace(HelperLog.constructor(currentDirectoryPath));
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}