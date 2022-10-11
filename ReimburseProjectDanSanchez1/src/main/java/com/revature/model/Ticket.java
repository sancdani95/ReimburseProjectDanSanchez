package com.revature.model;

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
	
	private String name;
	private String date;
	private int amount;
	private String reason;
	private String status;
	

}
