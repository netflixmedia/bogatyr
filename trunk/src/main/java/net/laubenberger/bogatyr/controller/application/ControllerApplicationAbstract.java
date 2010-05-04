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

package net.laubenberger.bogatyr.controller.application;

import net.laubenberger.bogatyr.controller.ControllerAbstract;
import net.laubenberger.bogatyr.model.application.ModelApplication;

/**
 * The skeleton for the application controller.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.9.0
 */
public abstract class ControllerApplicationAbstract extends ControllerAbstract implements ControllerApplication {
	final ModelApplication model;

	protected ControllerApplicationAbstract(final ModelApplication model) {
		super();
		this.model = model;
	}


	/*
	 * Implemented methods
	 */

	@Override
	public ModelApplication getModel() {
		return model;
	}
}
