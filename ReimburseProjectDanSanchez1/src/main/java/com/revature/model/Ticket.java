package com.revature.model;

import java.util.Objects;

/*
 * Java Bean design pattern
 * 1) has private fields
 * 2) a no-args constructor
 * 3) a constructor using all of the fields
 * 4) getters and setters
 * 5) a hashCode and equals method
 * 6) a toString method
 * 7) should implement the Serializable interface, but it is often ignored
 */

public class Ticket {
	
	//private fields
	private int ticket_id;
	private String ticket_username;
	private int ticket_amount;
	private String ticket_description;
	private String ticket_status;
	
	//no-args constructor
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructor using all of the fields
	public Ticket(int ticket_id, String ticket_username, int ticket_amount, String ticket_description,
			String ticket_status) {
		super();
		this.ticket_id = ticket_id;
		this.ticket_username = ticket_username;
		this.ticket_amount = ticket_amount;
		this.ticket_description = ticket_description;
		this.ticket_status = ticket_status;
	}

	
	//getters and setters
	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTicket_username() {
		return ticket_username;
	}

	public void setTicket_username(String ticket_username) {
		this.ticket_username = ticket_username;
	}

	public int getTicket_amount() {
		return ticket_amount;
	}

	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public String getTicket_description() {
		return ticket_description;
	}

	public void setTicket_discription(String ticket_description) {
		this.ticket_description = ticket_description;
	}

	public String getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
	}

	
	//hashCode and equals methods
	@Override
	public int hashCode() {
		return Objects.hash(ticket_amount, ticket_description, ticket_id, ticket_status, ticket_username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return ticket_amount == other.ticket_amount && Objects.equals(ticket_description, other.ticket_description)
				&& ticket_id == other.ticket_id && Objects.equals(ticket_status, other.ticket_status)
				&& Objects.equals(ticket_username, other.ticket_username);
	}

	
	//toString Method
	@Override
	public String toString() {
		return "Ticket [ticket_id=" + ticket_id + ", ticket_username=" + ticket_username + ", ticket_amount="
				+ ticket_amount + ", ticket_discription=" + ticket_description + ", ticket_status=" + ticket_status
				+ "]";
	}

}
