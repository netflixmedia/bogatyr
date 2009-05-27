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
package ch.sisprocom.bogatyr.view.swing;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JTree.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.7.0
 */
public class Tree extends JTree implements IComponentActivate {
	private static final long serialVersionUID = -4538444845018995986L;
	
	private boolean isNotActive;


	public Tree() {
		super();
	}

	public Tree(final Hashtable<?, ?> value) {
		super(value);
	}

	public Tree(final Object[] value) {
		super(value);
	}

	public Tree(final TreeModel newModel) {
		super(newModel);
	}

	public Tree(final TreeNode root, final boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
	}

	public Tree(final TreeNode root) {
		super(root);
	}

	public Tree(final Vector<?> value) {
		super(value);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	

	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public boolean isActive() {
		return !isNotActive;
	}

	public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}
}