package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;
/**
 * *1. static method to interact with JDBC API to return Connection object
 *  2. environment vars used so user doesnt enter personal info
 * @author david
 *
 */
public class ConnectionUtility {

	// private constructor to restrict instantiation
	private ConnectionUtility() {

	}

	// 1.
	public static Connection getConnection() throws SQLException {

		DriverManager.registerDriver(new Driver());	//// register MariaDB driver

		// 2.
		String url = System.getenv("db_url");
		String username = System.getenv("db_username");
		String password = System.getenv("db_password");

		
		Connection connection = DriverManager.getConnection(url, username, password);

		return connection;
	}
}
