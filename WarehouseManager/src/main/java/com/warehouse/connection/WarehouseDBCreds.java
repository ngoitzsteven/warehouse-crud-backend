package com.warehouse.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is a singleton that loads the properties file and grabs the values for the keys that I
 * want.
 */
public class WarehouseDBCreds {

	private static WarehouseDBCreds instance;
	private String url;
	private String username;
	private String password;
	

// Loading the properties file and grabbing the values for the keys that I want.
	private WarehouseDBCreds() {
		try {
			// Load it into memory immediately so that I have it
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Read the properties (key/value pairs) from the application.properties
			try (InputStream input = WarehouseDBCreds.class.getClassLoader()
					.getResourceAsStream("application.properties")) {
				// Properties object
				Properties props = new Properties();
				props.load(input); // Load in the file we opened
				
				// Grab out the keys that I want
				this.url = props.getProperty("db.url");
				this.username = props.getProperty("db.username");
				this.password = props.getProperty("db.password");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
/**
 * If the instance is null, create a new instance of WarehouseDBCreds. Otherwise, return the existing
 * instance
 * 
 * @return The instance of the class.
 */
	
	public static WarehouseDBCreds getInstance() {
		if (instance == null) {
			instance = new WarehouseDBCreds();
		}
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
/**
 * It returns a connection to the database
 * 
 * @return A connection to the database.
 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	
}
