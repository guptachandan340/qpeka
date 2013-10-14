package com.qpeka.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DBResourceHandler {
	
	private static Driver driver = null;
	private static Properties mysqlProperties = null;
	
	private static DBResourceHandler instance = null;
	
	/**
	 * 
	 */
	private DBResourceHandler() {
		try {
			mysqlProperties = new Properties();
			mysqlProperties.load(DBResourceHandler.class.getClassLoader().getResourceAsStream("mysql.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static DBResourceHandler getInstance() {
		return (instance == null ? (instance = new DBResourceHandler()) : instance);
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static synchronized Connection getConnection() throws SQLException {
		if (driver == null) {
			try {
				Class<?> jdbcDriverClass = Class.forName(getInstance().getJDBCDriver());
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
			} catch (Exception e) {
				System.out.println("Failed to initialise JDBC driver");
				e.printStackTrace();
			}
		}

		return DriverManager.getConnection(getInstance().getDBUrl(), getInstance().getDBUser(), getInstance().getDBPassword());
	}

	/**
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * 
	 * @param stmt
	 */
	public static void close(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	/**
	 * Get database driver
	 * @return
	 */
	public String getJDBCDriver() {
		return mysqlProperties.getProperty(SystemConstants.DBDRIVER);
	}
	
	/**
	 * Get database url
	 * @return
	 */
	public String getDBUrl() {
		return mysqlProperties.getProperty(SystemConstants.DBURL);
	}
	
	/**
	 * Get database user
	 * @return
	 */
	public String getDBUser() {
		return mysqlProperties.getProperty(SystemConstants.DBUSER);
	}
	
	/**
	 * Get database password
	 * @return
	 */
	public String getDBPassword() {
		return mysqlProperties.getProperty(SystemConstants.DBPASSWORD);
	}
	
}
