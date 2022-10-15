package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.model.Person;
import com.revature.ultility.ConnectionFactory;

public class PersonRepository {

	
	
	
	
	
	
	
	//save a new user
	public static void save(Person person) {

		final String SQLSave = "INSERT INTO person VAlUES (default, ?, ?, ?)";

		try (Connection connect = ConnectionFactory.getConnection(); 
				PreparedStatement pstmt = connect.prepareStatement(SQLSave);) {
			
			pstmt.setString(1, person.getPerson_username());
			pstmt.setString(2, person.getPerson_password());
			pstmt.setBoolean(3, person.isPerson_boss());
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
