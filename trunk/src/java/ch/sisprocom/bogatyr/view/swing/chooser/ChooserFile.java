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

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JFileChooser.
 * 
 * @author Stefan Laubenberger
 * @version 20090422
 */
public class ChooserFile extends JFileChooser {
	private static final long serialVersionUID = -4453036459563578035L;
	

	public ChooserFile() {
		super();
	}

	public ChooserFile(File currentDirectory, FileSystemView fsv) {
		super(currentDirectory, fsv);
		// TODO Auto-generated constructor stub
	}

	public ChooserFile(File currentDirectory) {
		super(currentDirectory);
		// TODO Auto-generated constructor stub
	}

	public ChooserFile(FileSystemView fsv) {
		super(fsv);
		// TODO Auto-generated constructor stub
	}

	public ChooserFile(String currentDirectoryPath, FileSystemView fsv) {
		super(currentDirectoryPath, fsv);
		// TODO Auto-generated constructor stub
	}

	public ChooserFile(String currentDirectoryPath) {
		super(currentDirectoryPath);
		// TODO Auto-generated constructor stub
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	} 
}