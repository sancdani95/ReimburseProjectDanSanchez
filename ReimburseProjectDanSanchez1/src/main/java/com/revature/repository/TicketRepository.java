package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Person;
import com.revature.model.Ticket;
import com.revature.ultility.ConnectionFactory;

public class TicketRepository {

	//creates new ticket item
	public static void submit(Ticket ticket, Person person) {
		
		//create SQL statement to submit new ticket
		final String SQLsubmit = "INSERT INTO ticket VALUES (default, ?, ?, ?, default)";
		
		//try-catch block to manage connection and the prepared statement
		try (Connection connect = ConnectionFactory.getConnection();
				PreparedStatement stmt = connect.prepareStatement(SQLsubmit);) {
			
			//insert variables into prepared statement
			stmt.setString(1, person.getPerson_username());
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
			
			
			
			//if (status.equals("pending")) {
			
			rstmt.setString(1, ticket.getTicket_status());
			rstmt.setInt(2, ticket.getTicket_id());
			
			rstmt.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String rStatus(Ticket ticket) {
		ResultSet set = null;
		String status = null;
		
		final String SQLticket = "SELECT ticket_status FROM ticket WHERE ticket_id = ?";
		
		try (Connection connect = ConnectionFactory.getConnection();
				PreparedStatement sstmt = connect.prepareStatement(SQLticket);){
			
			sstmt.setInt(1, ticket.getTicket_id());
			
			set = sstmt.executeQuery();
			set.next();
			status = set.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	//pull submitted ticket(s)
		public static List<Ticket> findTicket(Person person) {
			
			//Creates objects that are needed
			List<Ticket> ticketList = new ArrayList<>();
			ResultSet set = null;
			
			
			//SQL prepared statement for employee ticket
			final String SQLEmployee = "SELECT * FROM ticket WHERE ticket_username = ?";
			
			//SQL statement if manager is asking
			final String SQLManager = "SELECT * FROM ticket WHERE ticket_status = 'pending'";		
			
			
			//Try method to manage the connection to the db and the prepared statement
			try (Connection connect = ConnectionFactory.getConnection();
					PreparedStatement pstmt = connect.prepareStatement(SQLEmployee);
					Statement stmt = connect.createStatement();) {
				
				
				boolean boss = person.isPerson_boss();
				
				
				//execute prepared statement
				if (boss) {
					
					set = stmt.executeQuery(SQLManager);
				}
				else {
					pstmt.setString(1, person.getPerson_username());
					set = pstmt.executeQuery();
				}
				
				
				
				//a while loop to store tickets that are returned
				while (set.next()) {
					ticketList.add(
							new Ticket(set.getInt(1), set.getString(2), set.getInt(3), set.getString(4), set.getString(5)));
				}
				
				//any problems will print a SQL exception
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return ticketList;
		}
}
