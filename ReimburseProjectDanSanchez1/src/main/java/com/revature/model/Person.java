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


public class Person {
	
	//private fields
	private int person_id;
	private String person_username;
	private String person_password;
	private boolean person_boss;
	
	//no-args constructor
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructor using all of the fields
	public Person(int person_id, String person_username, String person_password, boolean person_boss) {
		super();
		this.person_id = person_id;
		this.person_username = person_username;
		this.person_password = person_password;
		this.person_boss = person_boss;
	}

	
	//getters and setters
	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getPerson_username() {
		return person_username;
	}

	public void setPerson_username(String person_username) {
		this.person_username = person_username;
	}

	public String getPerson_password() {
		return person_password;
	}

	public void setPerson_password(String person_password) {
		this.person_password = person_password;
	}

	public boolean isPerson_boss() {
		return person_boss;
	}

	public void setPerson_boss(boolean person_boss) {
		this.person_boss = person_boss;
	}

	
	//hashCode and equals method
	@Override
	public int hashCode() {
		return Objects.hash(person_boss, person_id, person_password, person_username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return person_boss == other.person_boss && person_id == other.person_id
				&& Objects.equals(person_password, other.person_password)
				&& Objects.equals(person_username, other.person_username);
	}

	
	//toString method
	@Override
	public String toString() {
		return "Person [person_id=" + person_id + ", person_username=" + person_username + ", person_password="
				+ person_password + ", person_boss=" + person_boss + "]";
	}
	
	
}
