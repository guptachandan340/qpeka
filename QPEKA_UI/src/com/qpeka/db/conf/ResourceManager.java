package com.qpeka.db.conf;

import java.io.IOException;
import java.io.InputStream;
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
	private static Driver driver = null;
	private static InputStream inputStream = null;
	private static Properties properties = null;

	static {
		try {
			inputStream = ResourceManager.class.getClassLoader()
					.getResourceAsStream("mysql.properties");
			properties = new Properties();
			properties.load(inputStream);

			// Assigning Properties values
			JDBC_DRIVER = properties.getProperty("db_driver");
			JDBC_URL = properties.getProperty("db_url");
			JDBC_USER = properties.getProperty("db_user");
			JDBC_PASSWORD = properties.getProperty("db_password");

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

	public static void main(String[] args) {
		Properties prop = new Properties();
		/*
		 * ClassLoader loader = Thread.currentThread().getContextClassLoader();
		 * InputStream stream = loader.getResourceAsStream("mysql.properties");
		 */

		try {
			InputStream stream = ResourceManager.class.getClassLoader()
					.getResourceAsStream("mysql.properties");
			prop.load(stream);
			System.out.println(prop.getProperty("db_password"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
