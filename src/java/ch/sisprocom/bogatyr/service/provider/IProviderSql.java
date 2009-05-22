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

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public interface IProviderSql {
	/**
	 * Connects to a database
     *
	 * @return Connection
	 * @throws Exception 
	 */
    Connection connectToDb() throws Exception;
    
	/**
	 * Executes an update
     *
	 * @param statement string in SQL
     * @return SQL return code
	 * @throws Exception 
	 */
    int executeUpdate(final String statement) throws Exception;
    
    /**
	 * Executes an SQL command
     *
	 * @param statement string in SQL
     * @return true/false
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
	 */
    boolean execute(final String statement) throws Exception;
}