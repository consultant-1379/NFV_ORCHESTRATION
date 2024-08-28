package com.ericsson.oss.nfe.alarmirp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static DBUtils instance; 
 
	public static DBUtils eInstance() {
		if (instance == null)
		{
			instance = new DBUtils();
			ConfigUtils utils = ConfigUtils.instance;
			
		/*	if(!utils.isInitalised)
				throw new RuntimeException("Failed to Load Config Props");*/
		}
		return instance;

	}

	private DBUtils() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your postgresql JDBC Driver?");
 			throw new RuntimeException("Failed to Initialise Postgres DB");
		}
		System.out.println("postgresql Driver Registered!");

		 
	}
	
	public String getQuery(String queryKey) {
		return ConfigUtils.instance.getProperty(queryKey);
	}

	public Connection getDBConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					ConfigUtils.instance.getProperty("dbURL"),
					ConfigUtils.instance.getProperty("dbUsername"),
					ConfigUtils.instance.getProperty("dbPassword"));

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			throw new RuntimeException("Failed to Open Postgres DB Connection");
		}
		return connection;
	}

	public void releaseResources(AutoCloseable closable) {
		try {
			if (closable != null)
				closable.close();
		} catch (Exception eignore) {
			System.out.println("releaseResources failed");
		}
	}
}
