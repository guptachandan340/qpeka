package com.qpeka.db.conf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class ResourceManager {
	
	private static String JDBC_DRIVER = null;
	private static String JDBC_URL = null;
	private static String JDBC_USER = null;
	private static String JDBC_PASSWORD = null;
	private static String FILE_PROFILEPIC = null;
	private static Driver driver = null;
	private static Properties mysqlProperties = null;
	private static Properties filesProperties = null;
	
	static {
		try {
			// Creating properties for mysql.properties file
			mysqlProperties = new Properties();
			mysqlProperties.load(ResourceManager.class.getClassLoader()
					.getResourceAsStream("mysql.properties"));
			
			// Creating properties for system.properties file
			filesProperties = new Properties();
			filesProperties.load(ResourceManager.class.getClassLoader()
					.getResourceAsStream("system.properties"));

			// Assigning mysql Properties values
			JDBC_DRIVER = mysqlProperties.getProperty("db_driver");
			JDBC_URL = mysqlProperties.getProperty("db_url");
			JDBC_USER = mysqlProperties.getProperty("db_user");
			JDBC_PASSWORD = mysqlProperties.getProperty("db_password");
			
			// Assigning system Properties values
			setFILE_PROFILEPIC(filesProperties.getProperty("profilePicFolder"));
			
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() throws SQLException {
		if (driver == null) {
			try {
				Class<?> jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
			} catch (Exception e) {
				System.out.println("Failed to initialise JDBC driver");
				e.printStackTrace();
			}
		}

		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}

	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void close(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	/**
	 * @return the fILE_PROFILEPIC
	 */
	public static String getFILE_PROFILEPIC() {
		return FILE_PROFILEPIC;
	}

	/**
	 * @param fILE_PROFILEPIC the file_Profilepic to set
	 */
	public static void setFILE_PROFILEPIC(String file_Profilepic) {
		FILE_PROFILEPIC = file_Profilepic;
	}

	public static void main(String[] args) {
		 ClassLoader loader = Thread.currentThread().getContextClassLoader();
	}
}
