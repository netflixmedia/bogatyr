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
package ch.sisprocom.bogatyr.service.provider;

import java.io.IOException;
import java.util.List;

import com.ibm.mq.MQException;

/**
 * This interface connects to an MQ-Server and allows to send and receive messages.
 * 
 * @author Stefan Laubenberger
 * @version 20090402
 */
public interface IProviderMq {
   /**
     * Sends a message to a mq manager and queue.
     *
     * @param data       to send
     * @param managerOut manager for sending
     * @param queueOut   queue for sending
     * @param managerIn  manager for receiving
     * @param queueIn    queue for receiving
     * @throws IOException
     * @throws MQException
     */
    void sendMessage(final byte[] data, final String managerOut, final String queueOut, final String managerIn, final String queueIn) throws Exception;

    /**
     * Receives messages from a mq manager and queue.
     *
     * @param managerIn manager for receiving
     * @param queueIn   queue for receiving
     * @return list containing all messages
     * @throws Exception
     */
    List<byte[]> receiveMessages(final String managerIn, final String queueIn) throws Exception;
}