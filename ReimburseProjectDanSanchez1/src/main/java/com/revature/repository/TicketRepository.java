package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.model.Ticket;
import com.revature.ultility.ConnectionFactory;

public class TicketRepository {

	//creates new ticket item
	public static void submit(Ticket ticket) {
		
		//create SQL statement to submit new ticket
		final String SQLsubmit = "INSERT INTO ticket VALUES (default, ?, ?, ?, default)";
		
		//try-catch block to manage connection and the prepared statement
		try (Connection connect = ConnectionFactory.getConnection();
				PreparedStatement stmt = connect.prepareStatement(SQLsubmit);) {
			
			//insert variables into prepared statement
			stmt.setString(1, ticket.getTicket_username());
			stmt.setInt(2, ticket.getTicket_amount());
			stmt.setString(3, ticket.getTicket_description());
			
			//execute prepared statement
			stmt.execute();
			
			//catch block to catch sql exceptions
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void respond(Ticket ticket) {
		
		final String SQLrespond = "UPDATE ticket SET ticket_status = ? WHERE ticket_id = ?";
		
		try (Connection connect = ConnectionFactory.getConnection();
				PreparedStatement rstmt = connect.prepareStatement(SQLrespond);) {
			
			rstmt.setString(1, ticket.getTicket_status());
			rstmt.setInt(2, ticket.getTicket_id());
			
			rstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
