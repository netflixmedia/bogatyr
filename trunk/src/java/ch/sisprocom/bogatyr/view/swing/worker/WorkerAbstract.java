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
package ch.sisprocom.bogatyr.view.swing.worker;

import javax.swing.SwingWorker;

/**
 * This class represents a skeleton for the worker.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091121)
 * @since 0.9.0
 */
public abstract class WorkerAbstract<T, V> extends SwingWorker<T, V> {
    private HandlerWorker handlerWorker;

    protected WorkerAbstract() {
        super();
    }

    protected WorkerAbstract(final HandlerWorker handlerWorker) {
        super();

        this.handlerWorker = handlerWorker;
    }

    public HandlerWorker getHandlerWorker() {
        return handlerWorker;
    }

    public void setHandlerWorker(final HandlerWorker handlerWorker) {
        this.handlerWorker = handlerWorker;
    }

    public void start() {
        if (handlerWorker != null) {
            handlerWorker.fireWorkerStart();
        }
        
        execute();
    }

    
    /*
     * Overridden methods
     */
    @Override
    protected void done() {
    	if (handlerWorker != null) {
            handlerWorker.fireWorkerDone();
        }
    }
}
