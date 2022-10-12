package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	//main driver method
		public static void main(String[] args) {
			
			//creates variable for statement fuction
			Statement stmt = null;
			
			final String SQL = "UPDATE person SET person_name = " + name + " where person_id = 3";
			
			
			try(Connection conn = DriverManager.getConnection(
					System.getenv("url"), 
					System.getenv("db_username"), 
					System.getenv("db_password")
				)) {
				stmt = conn.createStatement();
				stmt.executeQuery(SQL);
		
		
				conn.close();
				stmt.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			
			}
			
		}
}
