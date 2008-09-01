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

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.logger.Logger;
import ch.sisprocom.bogatyr.helper.property.Property;


/**
 * This is the skeleton for sql database connections
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080901
 */
public abstract class ProviderSqlTemplate {	
	private static final String PROPERTY_DRIVER   = "db.driver"; //$NON-NLS-1$
	private static final String PROPERTY_URL      = "db.url"; //$NON-NLS-1$
	private static final String PROPERTY_USER     = "db.user"; //$NON-NLS-1$
	private static final String PROPERTY_PASSWORD = "db.password"; //$NON-NLS-1$
	
	// Server
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	protected ProviderSqlTemplate() throws Exception {
        super();
        init();
	}
	
	public String getUser() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getUser");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getUser", user);  //$NON-NLS-1$

		return user;
	}

	public String getPassword() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getPassword");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getPassword", password);  //$NON-NLS-1$

		return password;
	}

	public void setUser(final String user) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setUser", user);  //$NON-NLS-1$

		this.user = user;

		Logger.getInstance().writeMethodExit(this.getClass(), "setUser");  //$NON-NLS-1$
	}

	public void setPassword(final String password) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setPassword", password);  //$NON-NLS-1$

		this.password = password;

		Logger.getInstance().writeMethodExit(this.getClass(), "setPassword");  //$NON-NLS-1$
	}

	/**
	 * Connects to a database
     *
	 * @return Connection
     * @throws Exception
	 */
    protected Connection connectToDb() throws Exception {		
		Logger.getInstance().writeMethodEntry(this.getClass(), "connectToDb");  //$NON-NLS-1$

		Class.forName(driver).newInstance();
		final Connection con = DriverManager.getConnection(url, user, password);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "connectToDb", con);  //$NON-NLS-1$
        return con;
	}
    
	/**
	 * Executes an update
     *
	 * @param statement
     * @return SQL-Code
     * @throws Exception
	 */
    protected int executeUpdate(final String statement) throws Exception {
		Logger.getInstance().writeMethodEntry(this.getClass(), "executeUpdate", statement);  //$NON-NLS-1$

        Statement stmt = null;
        Connection con = null;
        final int result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			result = stmt.executeUpdate(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}

		Logger.getInstance().writeMethodExit(this.getClass(), "executeUpdate", result);  //$NON-NLS-1$
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "execute", statement);  //$NON-NLS-1$

        Statement stmt = null;
        Connection con = null;
        final boolean result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			result = stmt.execute(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "execute", result);  //$NON-NLS-1$
		return result;
    }   
   
    
	/*
	 * Private methods
	 */
	private void init() throws Exception {
		readProperties();
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
	}

	private void readProperties() throws Exception {
        driver = Property.getInstance().getProperty(PROPERTY_DRIVER);
		if (!HelperGeneral.isValidString(driver)) {
			throw new Exception(getClass().getName() + "::readProperties - PROPERTY_DRIVER + == 'null'"); //$NON-NLS-1$
		}

        url = Property.getInstance().getProperty(PROPERTY_URL);
		if (!HelperGeneral.isValidString(url)) {
			throw new Exception(getClass().getName() + "::readProperties - PROPERTY_URL + == 'null'"); //$NON-NLS-1$
		}

        user = Property.getInstance().getProperty(PROPERTY_USER);
		if (!HelperGeneral.isValidString(user)) {
            user = ""; //$NON-NLS-1$
		}

        password = Property.getInstance().getProperty(PROPERTY_PASSWORD);
		if (!HelperGeneral.isValidString(password)) {
            password = ""; //$NON-NLS-1$
		}
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
