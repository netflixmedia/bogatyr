/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is the skeleton for sql database connections
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20081029
 */
public abstract class ProviderSqlAbstract {
	private static final Logger log = Logger.getLogger(ProviderSqlAbstract.class);
	
	// Server
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	protected ProviderSqlAbstract(String driver, String url, String user, String password) throws Exception {
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

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Connects to a database
     *
	 * @return Connection
     * @throws Exception
	 */
    protected Connection connectToDb() throws Exception {		
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, user, password);
	}
    
	/**
	 * Executes an update
     *
	 * @param statement
     * @return SQL-Code
     * @throws Exception
	 */
    protected int executeUpdate(final String statement) throws Exception {
        Statement stmt = null;
        Connection con = null;
        final int result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			log.debug(stmt.toString());
			
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
	 * @param statement
     * @return true/false
     * @throws Exception
	 */
    protected boolean execute(final String statement) throws Exception {
        Statement stmt = null;
        Connection con = null;
        final boolean result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			log.debug(stmt.toString());
			
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
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
