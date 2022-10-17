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
		
		try (Connection connect = ConnectionFactory.getConnection();
				PreparedStatement stmt = connect.prepareStatement(SQLsubmit);) {
			
			stmt.setString(1, ticket.getTicket_username());
			stmt.setInt(2, ticket.getTicket_amount());
			stmt.setString(3, ticket.getTicket_description());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
