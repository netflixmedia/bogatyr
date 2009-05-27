/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.chooser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JFileChooser.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.7.0
 */
public class ChooserFile extends JFileChooser {
	private static final long serialVersionUID = -4453036459563578035L;
	

	public ChooserFile() {
		super();
	}

	public ChooserFile(final File currentDirectory, final FileSystemView fsv) {
		super(currentDirectory, fsv);
	}

	public ChooserFile(final File currentDirectory) {
		super(currentDirectory);
	}

	public ChooserFile(final FileSystemView fsv) {
		super(fsv);
	}

	public ChooserFile(final String currentDirectoryPath, final FileSystemView fsv) {
		super(currentDirectoryPath, fsv);
	}

	public ChooserFile(final String currentDirectoryPath) {
		super(currentDirectoryPath);
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	} 
}