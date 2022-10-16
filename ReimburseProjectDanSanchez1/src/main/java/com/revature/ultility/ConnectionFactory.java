package com.revature.ultility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {
		
		Connection connect = DriverManager.getConnection(
				System.getenv("url"), 
				System.getenv("db_username"), 
				System.getenv("db_password"));
		
		return connect;
	}
}
