/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperGeneral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090511
 */
public class ProviderSql  implements IProviderSql { //TODO document in Wiki!
//	private static final Logger log = Logger.getLogger(ProviderSqlAbstract.class);
	
	// Server
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	protected ProviderSql(final String driver, final String url, final String user, final String password) {
        super();
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
	}
	
	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setDriver(final String driver) {
		this.driver = driver;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public void setPassword(final String password) {
		this.password = password;
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
	/**
	 * Connects to a database
     *
	 * @return Connection
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
    public Connection connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {		
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, user, password);
	}
    
	/**
	 * Executes an update
     *
	 * @param statement string in SQL
     * @return SQL-Code
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
    public int executeUpdate(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection con = null;
        final int result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
//			log.debug(stmt.toString());
			
			result = stmt.executeUpdate(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
		return result;
    }
	
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
    public boolean execute(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException  {
        Statement stmt = null;
        Connection con = null;
        final boolean result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
//			log.debug(stmt.toString());
			
			result = stmt.execute(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
		return result;
    }  
}
