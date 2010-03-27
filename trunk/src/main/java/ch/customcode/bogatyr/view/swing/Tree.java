/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.view.swing;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.Activatable;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.util.Hashtable;
import java.util.Vector;


/**
 * This is an extended JTree.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public class Tree extends JTree implements Activatable {
	private static final long serialVersionUID = -4538444845018995986L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
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
	@Override
    public boolean isActive() {
		return !isNotActive;
	}

	@Override
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