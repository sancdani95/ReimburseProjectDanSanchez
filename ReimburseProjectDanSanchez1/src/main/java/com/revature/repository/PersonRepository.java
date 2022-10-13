package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.model.Person;
import com.revature.ultility.ConnectionFactory;

public class PersonRepository {

	
	
	//save a new username
	public void save(Person person) {

		PreparedStatement stmt = null;
		final String SQL = "INSERT INTO person values(default, ?, ?, ?)";

		try (Connection connect = ConnectionFactory.getConnection()) {
			stmt = connect.prepareStatement(SQL);
			stmt.setString(1, person.getPerson_username());
			stmt.setString(2, person.getPerson_password());
			stmt.setBoolean(3, person.isPerson_boss());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
}
