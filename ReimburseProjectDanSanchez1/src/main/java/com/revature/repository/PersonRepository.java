package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public static Person pullUser(Person person) {
		
		Person user = null;
		ResultSet set = null;
		
		final String SQLlogin = "SELECT * FROM person WHERE person_username = ?";
		
		try(Connection connect = ConnectionFactory.getConnection();
				PreparedStatement stmt = connect.prepareStatement(SQLlogin);) {
			
			stmt.setString(1, person.getPerson_username());
		
			set = stmt.executeQuery();
			
			set.next();
			user = new Person(set.getInt(1),set.getString(2),set.getString(3),set.getBoolean(4));
			
			
	} catch (SQLException e) {
		e.printStackTrace();
	}

		return user;
			
	}
	
	
	public static List<String> checkUser(){
		ResultSet cSet = null;
		List<String> users = new ArrayList<>();
		final String SQLcheck = "SELECT person_username FROM person";
		try(Connection connect = ConnectionFactory.getConnection();
				Statement cstmt = connect.createStatement();) {
			cSet = cstmt.executeQuery(SQLcheck);
			while (cSet.next()) {
				users.add(new String(cSet.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
}
	
	
