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
	private int id;
	private String username;
	private String password;
	private boolean boss;
	
	//no-args constructor
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//constructor using all fields
	public Person(String username, String password, boolean boss) {
		super();
		this.username = username;
		this.password = password;
		this.boss = boss;
	}
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isBoss() {
		return boss;
	}
	public void setBoss(boolean boss) {
		this.boss = boss;
	}
	
	//hashCode and equals method
	@Override
	public int hashCode() {
		return Objects.hash(boss, password, username);
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
		return boss == other.boss && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
	//a toString method
	@Override
	public String toString() {
		return "Person [username=" + username + ", password=" + password + ", boss=" + boss + "]";
	}
	
	

}
