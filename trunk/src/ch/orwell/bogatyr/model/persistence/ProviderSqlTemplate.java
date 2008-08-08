/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.localizer.Localizer;
import ch.orwell.bogatyr.helper.logger.Logger;
import ch.orwell.bogatyr.helper.property.Property;



/**
 * This is the skeleton for sql database connections
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080605
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
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public void setPassword(final String password) {
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

        Logger.getInstance().writeDebug(this.getClass(), "executeUpdate", statement); //$NON-NLS-1$

        Statement stmt = null;
        Connection con = null;
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			return stmt.executeUpdate(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
    }
	
    /**
	 * Executes an SQL command
     *
	 * @param statement
     * @return true/false
     * @throws Exception
	 */
    protected boolean execute(final String statement) throws Exception {

        Logger.getInstance().writeDebug(this.getClass(), "execute", statement); //$NON-NLS-1$

        Statement stmt = null;
        Connection con = null;
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			return stmt.execute(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
    }   
   
    
	/*
	 * Private methods
	 */
	private void init() throws Exception {
		readProperties();
		Logger.getInstance().writeDebug(this.getClass(), "init", Localizer.getInstance().getValue(Localizer.RES_INSTANCIATED)); //$NON-NLS-1$
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
