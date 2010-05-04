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

package net.laubenberger.bogatyr.view.swing;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.Vector;


/**
 * This is an extended JTree.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.7.0
 */
public class Tree extends JTree implements Activatable {
	private static final long serialVersionUID = -4538444845018995986L;

	private static final Logger log = LoggerFactory.getLogger(Tree.class);

	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public Tree() {
		super();
		log.trace(HelperLog.constructor());
	}

	public Tree(final Hashtable<?, ?> value) {
		super(value);
		log.trace(HelperLog.constructor(value));
	}

	public Tree(final Object[] value) {
		super(value);
		log.trace(HelperLog.constructor(value));
	}

	public Tree(final TreeModel newModel) {
		super(newModel);
		log.trace(HelperLog.constructor(newModel));
	}

	public Tree(final TreeNode root, final boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
		log.trace(HelperLog.constructor(root, asksAllowsChildren));
	}

	public Tree(final TreeNode root) {
		super(root);
		log.trace(HelperLog.constructor(root));
	}

	public Tree(final Vector<?> value) {
		super(value);
		log.trace(HelperLog.constructor(value));
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
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(!isNotActive));
		return !isNotActive;
	}

	@Override
	public void setActive(final boolean isActive) {
		log.debug(HelperLog.methodStart(isActive));

		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}

		log.debug(HelperLog.methodExit());
	}
}