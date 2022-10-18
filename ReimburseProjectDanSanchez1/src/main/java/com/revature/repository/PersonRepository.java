package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.revature.model.Person;
import com.revature.ultility.ConnectionFactory;

public class PersonRepository {
	
	
	//save a new user
	public static void save(Person person) {

		//creates SQL string for prepare statement
		final String SQLSave = "INSERT INTO person VAlUES (default, ?, ?, ?)";

		//try statement manages connect method and prepared statement
		try (Connection connect = ConnectionFactory.getConnection();
				PreparedStatement pstmt = connect.prepareStatement(SQLSave);) {
			
			//inputs for prepare statement
			pstmt.setString(1, person.getPerson_username());
			pstmt.setString(2, person.getPerson_password());
			pstmt.setBoolean(3, person.isPerson_boss());
			
			//execute prepared statement
			pstmt.execute();

			//any problems will print a SQL exception
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
	
	
