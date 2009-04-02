/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
import java.util.ArrayList;
import java.util.List;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.HelperXml;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mqbind.MQC;


/**
 * This class connects to an IBM MQ-Server and allows to send and receive messages.
 * 
 * @author Stefan Laubenberger
 * @version 20090402
 */
public class ProviderMq implements IProviderMq { //TODO document in Wiki!

	public ProviderMq(final String hostname, final int port, final String channel) {
        super();
        // set up MQ environment
        MQEnvironment.hostname = hostname;
        MQEnvironment.port = port;
        MQEnvironment.channel = channel;
    }

	public String getHostname() {
		return MQEnvironment.hostname;
	}

	public int getPort() {
		return MQEnvironment.port;
	}

	public String getChannel() {
		return MQEnvironment.channel;
	}

	public void setHostname(final String hostname) {
		MQEnvironment.hostname = hostname;
	}

	public void setPort(final int port) {
		MQEnvironment.port = port;
	}

	public void setChannel(final String channel) {
		MQEnvironment.channel = channel;
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	
    /*
     * Implemented methods
     */
	public void sendMessage(final byte[] data, final String managerOut, final String queueOut, final String managerIn, final String queueIn) throws IOException, MQException {
        synchronized (this) {

            // Create a connection to the queue manager
            final MQQueueManager mqManager = new MQQueueManager(managerOut);

            // int options = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
            final int options = MQC.MQOO_OUTPUT;

            final MQQueue mqQueue = mqManager.accessQueue(queueOut, options, null,
                    null, // no dynamic q name
                    null);

            final MQMessage message = new MQMessage();
            message.replyToQueueManagerName = managerIn;
            message.replyToQueueName = queueIn;
            message.messageFlags = MQC.MQMF_LAST_MSG_IN_GROUP;
            message.format = "MQSTR"; //$NON-NLS-1$
            // message.messageId = ???
            // message.correlationId = ???
            // message.expiry = ???
            message.write(data);

            message.write(data);

            final MQPutMessageOptions pmo = new MQPutMessageOptions();

            // put the message on the queue
            mqQueue.put(message, pmo);

            mqQueue.close();
            mqManager.disconnect();
        }
    }

    public List<byte[]> receiveMessages(final String managerIn, final String queueIn) throws MQException, IOException {
        synchronized (this) {

            final List<byte[]> list = new ArrayList<byte[]>();
//		byte[] data;

            // create a connection to the queue manager
            final MQQueueManager mqManager = new MQQueueManager(managerIn);

            // create a queue manager object and access the queue that will be used for the putting of messages.
            final int options = MQC.MQOO_INPUT_EXCLUSIVE | MQC.MQOO_BROWSE;

            final MQQueue mqQueue = mqManager.accessQueue(queueIn, options, null, null, null);

            // set up our options to browse for the first message
            final MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options = MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_FIRST;
            // gmo.options = MQC.MQGMO_NO_SYNCPOINT; // Set no sync point
            // gmo.options = MQC.MQGMO_CONVERT; // Handles ASCII/EBCDIC
            // gmo.options = MQC.MQGMO_WAIT; // Wait until message arrives
            // gmo.waitInterval = 10000;

            final MQMessage message = new MQMessage();

            // set up a loop exit flag
            boolean done = false;

            do {
                try {
                    // Reset the message and IDs to be empty
                    message.clearMessage();
                    message.correlationId = MQC.MQCI_NONE;
                    message.messageId = MQC.MQMI_NONE;

                    // Browse the message
                    mqQueue.get(message, gmo);

                    gmo.options = MQC.MQGMO_MSG_UNDER_CURSOR;
                    mqQueue.get(message, gmo);

                    list.add(HelperXml.getValidXmlString(message.readString(message.getMessageLength())).getBytes());

                    // Reset the options to browse the next message
                    gmo.options = MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_NEXT;
                } catch (MQException ex) {

                    if (2033 == ex.reasonCode) {
                        done = true;
                    } else {
                        throw ex;
                    }
                }
            } while (!done);

            // before the program ends, the open queue will be closed and the queue manager will be disconnected.
            mqQueue.close();
            mqManager.disconnect();

            return list;
        }
    }
}